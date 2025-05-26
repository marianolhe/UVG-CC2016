import os
from typing import List
from Grafo import Grafo
from FloydMarshall import FloydWarshall

class SistemaLogistica:
    """
    Sistema principal para gestión de rutas logísticas
    """
    
    def __init__(self):
        self.grafo = Grafo()
        self.floyd = None
        self.condiciones_clima = {
            'normal': 0,
            'lluvia': 1, 
            'nieve': 2,
            'tormenta': 3
        }
        self.datos_originales = {}  # Para almacenar todos los tiempos por condición
        self.clima_actual = 'normal'
    
    def cargar_archivo(self, nombre_archivo: str):
        """Carga el grafo desde un archivo de texto"""
        try:
            with open(nombre_archivo, 'r', encoding='utf-8') as archivo:
                for linea in archivo:
                    partes = linea.strip().split()
                    if len(partes) >= 6:  # ciudad1 ciudad2 normal lluvia nieve tormenta
                        ciudad1 = partes[0]
                        ciudad2 = partes[1]
                        tiempos = [float(partes[i]) for i in range(2, 6)]
                        
                        # Almacenar todos los tiempos
                        clave = (ciudad1, ciudad2)
                        self.datos_originales[clave] = tiempos
                        
                        # Agregar arco con tiempo normal por defecto
                        self.grafo.agregar_arco(ciudad1, ciudad2, tiempos[0])
            
            self.actualizar_floyd()
            print(f"Archivo {nombre_archivo} cargado exitosamente.")
            
        except FileNotFoundError:
            print(f"Error: No se pudo encontrar el archivo {nombre_archivo}")
        except Exception as e:
            print(f"Error al cargar el archivo: {e}")
    
    def crear_archivo_ejemplo(self):
        """Crea un archivo de ejemplo para pruebas"""
        contenido = """Guatemala Antigua 2 3 4 8
Guatemala Quetzaltenango 4 5 7 12
Guatemala Escuintla 1 2 3 6
Antigua Chimaltenango 1 2 2 4
Quetzaltenango Huehuetenango 3 4 6 10
Escuintla Mazatenango 2 3 4 7
Chimaltenango Solola 2 3 5 8
Huehuetenango Quiche 4 5 8 15
Mazatenango Retalhuleu 1 2 3 5
Solola Quetzaltenango 3 4 6 9
Guatemala Chimaltenango 2 3 4 7
Antigua Escuintla 3 4 6 10"""
        
        with open('logistica.txt', 'w', encoding='utf-8')  as archivo:
            archivo.write(contenido)
        print("Archivo logistica.txt de ejemplo creado.")
    
    def actualizar_floyd(self):
        """Actualiza el algoritmo de Floyd-Warshall"""
        self.floyd = FloydWarshall(self.grafo)
        self.floyd.ejecutar()
    
    def cambiar_clima(self, nuevo_clima: str):
        """Cambia las condiciones climáticas del grafo"""
        if nuevo_clima not in self.condiciones_clima:
            print("Condición climática no válida")
            return
        
        self.clima_actual = nuevo_clima
        indice_clima = self.condiciones_clima[nuevo_clima]
        
        # Actualizar pesos en el grafo según el clima
        for (ciudad1, ciudad2), tiempos in self.datos_originales.items():
            self.grafo.agregar_arco(ciudad1, ciudad2, tiempos[indice_clima])
        
        self.actualizar_floyd()
        print(f"Clima cambiado a: {nuevo_clima}")
    
    def mostrar_ruta_mas_corta(self, origen: str, destino: str):
        """Muestra la ruta más corta entre dos ciudades"""
        if not self.floyd:
            print("Error: Debe cargar el grafo primero")
            return
        
        distancia, ruta = self.floyd.obtener_ruta(origen, destino)
        
        if distancia == float('inf'):
            print(f"No hay ruta disponible de {origen} a {destino}")
        else:
            print(f"\nRuta más corta de {origen} a {destino}:")
            print(f"Distancia total: {distancia:.1f} horas")
            print(f"Ruta: {' -> '.join(ruta)}")
    
    def mostrar_centro_grafo(self):
        """Muestra el centro del grafo"""
        if not self.floyd:
            print("Error: Debe cargar el grafo primero")
            return
        
        centro, excentricidad = self.floyd.obtener_centro_grafo()  # CORREGIDO
        print(f"\nCentro del grafo: {centro}")
        print(f"Excentricidad: {excentricidad:.1f}")
    
    def agregar_conexion(self, ciudad1: str, ciudad2: str, tiempos: list):
        """Agrega una nueva conexión entre ciudades"""
        clave = (ciudad1, ciudad2)
        self.datos_originales[clave] = tiempos
        
        # Usar el tiempo según el clima actual
        indice_clima = self.condiciones_clima[self.clima_actual]
        self.grafo.agregar_arco(ciudad1, ciudad2, tiempos[indice_clima])
        
        self.actualizar_floyd()
        print(f"Conexión agregada: {ciudad1} -> {ciudad2}")
    
    def eliminar_conexion(self, ciudad1: str, ciudad2: str):
        """Elimina una conexión entre ciudades"""
        clave = (ciudad1, ciudad2)
        if clave in self.datos_originales:
            del self.datos_originales[clave]
        
        self.grafo.eliminar_arco(ciudad1, ciudad2)
        self.actualizar_floyd()
        print(f"Conexión eliminada: {ciudad1} -> {ciudad2}")
    
    def menu_principal(self):
        """Menú principal del sistema"""
        while True:
            print("\n" + "="*50)
            print("SISTEMA DE OPTIMIZACIÓN LOGÍSTICA")
            print("="*50)
            print("1. Consultar ruta más corta")
            print("2. Mostrar centro del grafo") 
            print("3. Modificar grafo")
            print("4. Cambiar condiciones climáticas")
            print("5. Mostrar matriz de adyacencia")
            print("6. Finalizar programa")
            
            try:
                opcion = input("\nSeleccione una opción: ").strip()
                
                if opcion == '1':
                    self.menu_consultar_ruta()
                elif opcion == '2':
                    self.mostrar_centro_grafo()
                elif opcion == '3':
                    self.menu_modificar_grafo()
                elif opcion == '4':
                    self.menu_cambiar_clima()
                elif opcion == '5':
                    self.grafo.mostrar_matriz()
                elif opcion == '6':
                    print("¡Gracias por usar el sistema!")
                    break
                else:
                    print("Opción no válida")
                    
            except KeyboardInterrupt:
                print("\n\n¡Hasta luego!")
                break
            except Exception as e:
                print(f"Error: {e}")
    
    def menu_consultar_ruta(self):
        """Menú para consultar rutas"""
        origen = input("Ingrese ciudad de origen: ").strip()
        destino = input("Ingrese ciudad de destino: ").strip()
        self.mostrar_ruta_mas_corta(origen, destino)
    
    def menu_modificar_grafo(self):
        """Menú para modificar el grafo"""
        print("\nModificaciones disponibles:")
        print("1. Interrumpir tráfico entre ciudades")
        print("2. Establecer nueva conexión")
        print("3. Cambiar clima entre par de ciudades")
        
        opcion = input("Seleccione opción: ").strip()
        
        if opcion == '1':
            ciudad1 = input("Ciudad origen: ").strip()
            ciudad2 = input("Ciudad destino: ").strip()
            self.eliminar_conexion(ciudad1, ciudad2)
            
        elif opcion == '2':
            ciudad1 = input("Ciudad origen: ").strip()
            ciudad2 = input("Ciudad destino: ").strip()
            
            try:
                print("Ingrese tiempos (normal, lluvia, nieve, tormenta):")
                normal = float(input("Tiempo normal: "))
                lluvia = float(input("Tiempo con lluvia: "))
                nieve = float(input("Tiempo con nieve: "))
                tormenta = float(input("Tiempo con tormenta: "))
                
                tiempos = [normal, lluvia, nieve, tormenta]
                self.agregar_conexion(ciudad1, ciudad2, tiempos)
                
            except ValueError:
                print("Error: Ingrese valores numéricos válidos")
                
        elif opcion == '3':
            ciudad1 = input("Ciudad origen: ").strip()
            ciudad2 = input("Ciudad destino: ").strip()
            print("Condiciones: normal, lluvia, nieve, tormenta")
            clima = input("Nuevo clima: ").strip().lower()
            
            if (ciudad1, ciudad2) in self.datos_originales:
                tiempos = self.datos_originales[(ciudad1, ciudad2)]
                if clima in self.condiciones_clima:
                    indice = self.condiciones_clima[clima]
                    self.grafo.agregar_arco(ciudad1, ciudad2, tiempos[indice])
                    self.actualizar_floyd()
                    print(f"Clima actualizado entre {ciudad1} y {ciudad2}")
                else:
                    print("Condición climática no válida")
            else:
                print("Conexión no encontrada")
    
    def menu_cambiar_clima(self):
        """Menú para cambiar condiciones climáticas globales"""
        print(f"\nClima actual: {self.clima_actual}")
        print("Condiciones disponibles: normal, lluvia, nieve, tormenta")
        nuevo_clima = input("Nuevo clima: ").strip().lower()
        self.cambiar_clima(nuevo_clima)


def main():
    """Función principal"""
    sistema = SistemaLogistica()
    
    print("SISTEMA DE OPTIMIZACIÓN LOGÍSTICA")
    print("Algoritmo de Floyd-Warshall")
    print("-" * 40)
    
    # Intentar cargar archivo o crear uno de ejemplo
    if os.path.exists('logistica.txt'):
        print("Cargando logistica.txt...")
        sistema.cargar_archivo('logistica.txt')
    else:
        print("No se encontró logistica.txt. Creando archivo de ejemplo...")
        sistema.crear_archivo_ejemplo()
        sistema.cargar_archivo('logistica.txt')
    
    # Iniciar menú principal
    sistema.menu_principal()


if __name__ == "__main__":
    main()