import simpy
import random
import numpy as np
from process_generator import ProcessGenerator

RANDOM_SEED = 1

# Función para ejecutar la simulación con configuraciones personalizadas
def run_simulation(num_processes, interval, memory_capacity, cpu_capacity, cpu_quantum):
    random.seed(RANDOM_SEED)
    env = simpy.Environment()
    memory = simpy.Container(env, init=memory_capacity, capacity=memory_capacity)
    cpu = simpy.Resource(env, capacity=cpu_capacity)
    start_times = []  # Lista para almacenar tiempos de inicio
    end_times = []  # Lista para almacenar tiempos de finalización
    
    ProcessGenerator(env, memory, cpu, interval, start_times, end_times, num_processes, cpu_quantum)
    env.run()
    
    # Calcular tiempos de procesamiento
    turnaround_times = [end - start for start, end in zip(start_times, end_times)]
    avg_time = np.mean(turnaround_times) if turnaround_times else 0
    std_dev = np.std(turnaround_times) if turnaround_times else 0
    print(f'Procesos: {num_processes}, Intervalo: {interval}, RAM: {memory_capacity}, CPUs: {cpu_capacity}, Quantum: {cpu_quantum}, Tiempo Promedio: {avg_time:.2f}, Desviación Estándar: {std_dev:.2f}')
