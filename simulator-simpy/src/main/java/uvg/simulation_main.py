from simulation import run_simulation

def main():
    """
    Ejecuta la simulación con valores ingresados por el usuario.
    """
    memory_capacity = int(input("Ingrese la cantidad de RAM (ej. 100 o 200): "))
    cpu_capacity = int(input("Ingrese la cantidad de CPUs (ej. 1 o 2): "))
    cpu_quantum = int(input("Ingrese el quantum de CPU (ej. 3 o 6): "))
    num_processes = int(input("Ingrese la cantidad de procesos (ej. 25, 50, 100, 150, 200): "))
    interval = int(input("Ingrese el intervalo de llegada de procesos (ej. 10, 5, 1): "))
    run_simulation(num_processes, interval, memory_capacity, cpu_capacity, cpu_quantum)

main()
