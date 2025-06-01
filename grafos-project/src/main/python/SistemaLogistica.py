import os
from Grafo import Grafo
from FloydMarshall import FloydWarshall

class SistemaLogistica:
    def __init__(self):
        self.grafo = Grafo()
        self.floyd_warshall = None
        self.cargar_datos_iniciales()
    
    def cargar_datos_iniciales(self):
        """Cargar datos iniciales desde archivo logistica.txt"""
        try:
            # Buscar el archivo en diferentes ubicaciones posibles
            posibles_rutas = [
                "logistica.txt",
                "src/main/resources/logistica.txt", 
                "src/main/python/logistica.txt",
                "../../../logistica.txt"
            ]
            
            archivo_encontrado = None
            for ruta in posibles_rutas:
                if os.path.exists(ruta):
                    archivo_encontrado = ruta
                    break
            
            if not archivo_encontrado:
                print("⚠️  Archivo logistica.txt no encontrado. Usando datos de ejemplo.")
                self.cargar_datos_ejemplo()
                return
            
            print(f"Cargando datos desde: {archivo_encontrado}")
            
            with open(archivo_encontrado, 'r', encoding='utf-8') as archivo:
                for linea in archivo:
                    linea = linea.strip()
                    if linea and not linea.startswith('#'):  # Ignorar líneas vacías y comentarios
                        partes = linea.split(',')
                        if len(partes) >= 3:
                            origen = partes[0].strip()
                            destino = partes[1].strip()
                            try:
                                tiempo = float(partes[2].strip())
                                # Agregar conexiones bidireccionales
                                self.grafo.agregar_arco(origen, destino, tiempo)
                                self.grafo.agregar_arco(destino, origen, tiempo)
                            except ValueError:
                                print(f"⚠️  Línea inválida ignorada: {linea}")
            
            print("✓ Datos cargados exitosamente desde archivo")
            print(f"  Ciudades: {self.grafo.num_vertices}")
            
        except Exception as e:
            print(f"✗ Error al cargar archivo: {e}")
            print("Usando datos de ejemplo...")
            self.cargar_datos_ejemplo()

    def cargar_datos_ejemplo(self):
        """Cargar datos de ejemplo si no hay archivo"""
        conexiones = [
            ("Guatemala", "Antigua", 45),
            ("Guatemala", "Escuintla", 60), 
            ("Guatemala", "Chimaltenango", 55),
            ("Antigua", "Chimaltenango", 25),
            ("Escuintla", "Mazatenango", 75),
            ("Chimaltenango", "Quetzaltenango", 120),
            ("Mazatenango", "Quetzaltenango", 45),
            ("Guatemala", "Coban", 180),
            ("Coban", "Flores", 240)
        ]
        
        for origen, destino, tiempo in conexiones:
            self.grafo.agregar_arco(origen, destino, tiempo)
            self.grafo.agregar_arco(destino, origen, tiempo)
        
        print("✓ Datos de ejemplo cargados")
    
    def mostrar_menu(self):
        print("\n" + "="*40)
        print("    SISTEMA DE LOGÍSTICA DE GUATEMALA")
        print("="*40)
        print("1. Consultar ruta más corta entre ciudades")
        print("2. Mostrar ciudad centro del grafo")
        print("3. Modificar el grafo")
        print("4. Finalizar programa")
        print("-"*40)
        return input("Seleccione una opción (1-4): ")
    
    def consultar_ruta(self):
        print("\n--- CONSULTAR RUTA MÁS CORTA ---")
        origen = input("Ingrese ciudad origen: ")
        destino = input("Ingrese ciudad destino: ")
        
        if not self.grafo.existe_vertice(origen):
            print(f"Error: La ciudad '{origen}' no existe en el sistema")
            return
        
        if not self.grafo.existe_vertice(destino):
            print(f"Error: La ciudad '{destino}' no existe en el sistema")
            return
        
        # Ejecutar Floyd-Warshall si no se ha ejecutado
        if not self.floyd_warshall or not self.floyd_warshall.ejecutado:
            print("Calculando rutas más cortas...")
            self.floyd_warshall = FloydWarshall(self.grafo)
            self.floyd_warshall.ejecutar()
        
        distancia, ruta = self.floyd_warshall.obtener_ruta(origen, destino)
        
        if ruta and len(ruta) > 0:
            print(f"\n✓ Ruta encontrada:")
            print(f"  Distancia: {distancia} minutos")
            print(f"  Ruta: {' → '.join(ruta)}")
        else:
            print("✗ No hay ruta disponible entre estas ciudades")
    
    def mostrar_centro(self):
        print("\n--- CIUDAD CENTRO DEL GRAFO ---")
        
        if not self.floyd_warshall or not self.floyd_warshall.ejecutado:
            print("Calculando centro del grafo...")
            self.floyd_warshall = FloydWarshall(self.grafo)
            self.floyd_warshall.ejecutar()
        
        centro, excentricidad = self.floyd_warshall.obtener_centro_grafo()
        
        if centro:
            print(f"✓ Ciudad centro: {centro}")
            print(f"  Excentricidad: {excentricidad} minutos")
            print(f"  (Distancia máxima desde {centro} a cualquier otra ciudad)")
        else:
            print("✗ No se pudo calcular el centro del grafo")
    
    def modificar_grafo(self):
        print("\n--- MODIFICAR GRAFO ---")
        print("a. Interrupción de tráfico entre ciudades")
        print("b. Establecer nueva conexión")
        print("c. Cambiar clima entre ciudades")
        
        opcion = input("Seleccione opción (a/b/c): ").lower().strip()
        
        if opcion == 'a':
            self.interrumpir_trafico()
        elif opcion == 'b':
            self.nueva_conexion()
        elif opcion == 'c':
            self.cambiar_clima()
        else:
            print("✗ Opción inválida")
            return
        
        # Recalcular rutas después de modificaciones
        print("Recalculando rutas más cortas...")
        self.floyd_warshall = FloydWarshall(self.grafo)
        self.floyd_warshall.ejecutar()
        print("✓ Rutas recalculadas exitosamente")
    
    def interrumpir_trafico(self):
        print("\n-- Interrupción de Tráfico --")
        ciudad1 = input("Ciudad 1: ")
        ciudad2 = input("Ciudad 2: ")
        
        if not self.grafo.existe_vertice(ciudad1) or not self.grafo.existe_vertice(ciudad2):
            print("✗ Una o ambas ciudades no existen")
            return
        
        if self.grafo.existe_arco(ciudad1, ciudad2):
            self.grafo.eliminar_arco(ciudad1, ciudad2)
            self.grafo.eliminar_arco(ciudad2, ciudad1)
            print(f"✓ Tráfico interrumpido entre {ciudad1} y {ciudad2}")
        else:
            print(f"✗ No existe conexión entre {ciudad1} y {ciudad2}")
    
    def nueva_conexion(self):
        print("\n-- Nueva Conexión --")
        ciudad1 = input("Ciudad 1: ")
        ciudad2 = input("Ciudad 2: ")
        
        try:
            tiempo_normal = float(input("Tiempo con clima normal (minutos): "))
            if tiempo_normal <= 0:
                print("✗ El tiempo debe ser positivo")
                return
        except ValueError:
            print("✗ Tiempo inválido")
            return
        
        # Agregar conexión bidireccional
        self.grafo.agregar_arco(ciudad1, ciudad2, tiempo_normal)
        self.grafo.agregar_arco(ciudad2, ciudad1, tiempo_normal)
        print(f"✓ Nueva conexión establecida entre {ciudad1} y {ciudad2}")
        print(f"  Tiempo: {tiempo_normal} minutos")
    
    def cambiar_clima(self):
        print("\n-- Cambiar Clima --")
        ciudad1 = input("Ciudad 1: ")
        ciudad2 = input("Ciudad 2: ")
        
        if not self.grafo.existe_vertice(ciudad1) or not self.grafo.existe_vertice(ciudad2):
            print("✗ Una o ambas ciudades no existen")
            return
        
        if not self.grafo.existe_arco(ciudad1, ciudad2):
            print(f"✗ No existe conexión entre {ciudad1} y {ciudad2}")
            return
        
        print("Climas disponibles:")
        print("  - normal (factor 1.0)")
        print("  - lluvia (factor 1.2)")
        print("  - nieve (factor 1.5)")
        print("  - tormenta (factor 2.0)")
        
        clima = input("Nuevo clima: ").lower().strip()
        
        # Obtener peso base (asumiendo que es el tiempo normal)
        peso_actual = self.grafo.obtener_peso(ciudad1, ciudad2)
        if peso_actual is None:
            print("✗ Error al obtener el peso actual")
            return
        
        multiplicador = self.obtener_multiplicador_clima(clima)
        if multiplicador is None:
            print("✗ Clima inválido")
            return
        
        # Calcular tiempo base (dividir por el multiplicador anterior si es necesario)
        # Para simplicidad, usamos el peso actual como base
        nuevo_tiempo = peso_actual * multiplicador / self.obtener_multiplicador_clima('normal')
        
        self.grafo.agregar_arco(ciudad1, ciudad2, nuevo_tiempo)
        self.grafo.agregar_arco(ciudad2, ciudad1, nuevo_tiempo)
        
        print(f"✓ Clima cambiado a '{clima}' entre {ciudad1} y {ciudad2}")
        print(f"  Nuevo tiempo: {nuevo_tiempo:.1f} minutos")
    
    def obtener_multiplicador_clima(self, clima):
        multiplicadores = {
            'normal': 1.0,
            'lluvia': 1.2,
            'nieve': 1.5,
            'tormenta': 2.0
        }
        return multiplicadores.get(clima)
    
    def mostrar_ciudades(self):
        """Método auxiliar para mostrar las ciudades disponibles"""
        print("\nCiudades disponibles:")
        for i in range(self.grafo.num_vertices):
            for nombre, indice in self.grafo.vertices.items():
                if indice == i:
                    print(f"  - {nombre}")
                    break
    
    def ejecutar(self):
        print("¡Bienvenido al Sistema de Logística de Guatemala!")
        self.mostrar_ciudades()
        
        while True:
            try:
                opcion = self.mostrar_menu()
                
                if opcion == '1':
                    self.consultar_ruta()
                elif opcion == '2':
                    self.mostrar_centro()
                elif opcion == '3':
                    self.modificar_grafo()
                elif opcion == '4':
                    print("\n¡Gracias por usar el Sistema de Logística!")
                    print("Programa finalizado.")
                    break
                else:
                    print("✗ Opción inválida. Por favor seleccione 1-4.")
                
                input("\nPresione Enter para continuar...")
                
            except KeyboardInterrupt:
                print("\n\nPrograma interrumpido por el usuario.")
                break
            except Exception as e:
                print(f"✗ Error inesperado: {e}")

if __name__ == '__main__':
    sistema = SistemaLogistica()
    sistema.ejecutar()