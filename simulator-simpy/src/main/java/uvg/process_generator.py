import simpy
import random
from process import Process

class ProcessGenerator:
    def __init__(self, env, memory, cpu, interval, start_times, end_times, num_processes, cpu_quantum):
        """
        Inicializa el generador de procesos con un número máximo de procesos.
        """
        self.env = env
        self.memory = memory
        self.cpu = cpu
        self.interval = interval
        self.process_count = 0
        self.start_times = start_times
        self.end_times = end_times
        self.num_processes = num_processes
        self.cpu_quantum = cpu_quantum
        self.action = env.process(self.generate())

    def generate(self):
        """
        Genera nuevos procesos en intervalos de tiempo exponenciales hasta alcanzar el límite.
        """
        while self.process_count < self.num_processes:
            yield self.env.timeout(random.expovariate(1.0 / self.interval))
            Process(self.env, self.process_count, self.memory, self.cpu, self.start_times, self.end_times, self.cpu_quantum)
            self.process_count += 1