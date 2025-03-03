import simpy
import random

class Process:
    def __init__(self, env, pid, memory, cpu, start_times, end_times, cpu_quantum):
        """
        Inicializa un nuevo proceso con un identificador único, memoria requerida y cantidad de instrucciones.
        """
        self.env = env
        self.pid = pid
        self.memory_required = random.randint(1, 10)  # Memoria solicitada entre 1 y 10 MB
        self.instructions_remaining = random.randint(1, 10)  # Instrucciones totales entre 1 y 10
        self.memory = memory
        self.cpu = cpu
        self.start_times = start_times
        self.end_times = end_times
        self.cpu_quantum = cpu_quantum
        self.start_times.append(env.now)  # Registra el tiempo de llegada del proceso
        self.action = env.process(self.lifecycle())  # Inicia el ciclo de vida del proceso

    def lifecycle(self):
        """
        Simula el ciclo de vida del proceso en el sistema operativo.
        """
        yield self.memory.get(self.memory_required)  # Solicita memoria RAM
        
        while self.instructions_remaining > 0:
            with self.cpu.request() as req:
                yield req  # Solicita el CPU
                executed = min(self.instructions_remaining, self.cpu_quantum)  # Determina instrucciones a ejecutar
                yield self.env.timeout(1)  # Simula la ejecución por 1 unidad de tiempo
                self.instructions_remaining -= executed  # Reduce las instrucciones pendientes
            
                if self.instructions_remaining == 0:
                    yield self.memory.put(self.memory_required)  # Libera la memoria asignada
                    self.end_times.append(self.env.now)  # Registra el tiempo de finalización
                    return
                
                random_event = random.randint(1, 21)
                if random_event == 1:
                    yield self.env.timeout(2)  # Simula operación de I/O
