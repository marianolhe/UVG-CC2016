# --- simulacion.py ---
import simpy
import random

# Datos de tiempos (en minutos) y costos aproximados (en quetzales)
TRIAGE_TIME = 3
CONSULTA_TIME = 10
COSTO_MEDICO = 5  # por minuto
COSTO_ENFERMERO = 2  # por minuto

class SalaEmergencia:
    def __init__(self, env, medicos, enfermeros):
        self.env = env
        self.medicos = simpy.Resource(env, medicos)
        self.enfermeros = simpy.Resource(env, enfermeros)

    def triage(self, paciente):
        yield self.env.timeout(TRIAGE_TIME)

    def consulta(self, paciente):
        yield self.env.timeout(CONSULTA_TIME)


def paciente(env, nombre, sala):
    print(f"{nombre} llega a la sala a las {env.now:.2f} min")
    with sala.enfermeros.request() as enfermero:
        yield enfermero
        print(f"{nombre} inicia triage a las {env.now:.2f} min")
        yield env.process(sala.triage(nombre))

    with sala.medicos.request() as medico:
        yield medico
        print(f"{nombre} inicia consulta a las {env.now:.2f} min")
        yield env.process(sala.consulta(nombre))
        print(f"{nombre} termina a las {env.now:.2f} min")


def generar_pacientes(env, sala):
    for i in range(10):
        yield env.timeout(random.randint(1, 5))
        env.process(paciente(env, f"Paciente {i+1}", sala))


def run_simulacion(medicos=2, enfermeros=2):
    env = simpy.Environment()
    sala = SalaEmergencia(env, medicos, enfermeros)
    env.process(generar_pacientes(env, sala))
    env.run(until=100)


if __name__ == '__main__':
    run_simulacion()
