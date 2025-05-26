import copy
from typing import Tuple, List, Optional
from Grafo import Grafo

class FloydWarshall:
    """
    Implementación del algoritmo Floyd-Warshall para encontrar 
    los caminos más cortos entre todos los pares de vértices
    """
    
    def __init__(self, grafo: Grafo):
        self.grafo = grafo
        self.distancias = []
        self.predecesores = []
        self.INF = float('inf')
        self.ejecutado = False
    
    def ejecutar(self) -> bool:
        """
        Ejecuta el algoritmo de Floyd-Warshall
        Retorna True si se ejecutó correctamente, False si hay problemas
        """
        n = self.grafo.num_vertices
        
        if n == 0:
            print("Error: El grafo está vacío")
            return False
        
        # Inicializar matrices de distancias y predecesores
        self.distancias = copy.deepcopy(self.grafo.matriz_adyacencia)
        self.predecesores = [[-1 for _ in range(n)] for _ in range(n)]
        
        # Inicializar predecesores
        for i in range(n):
            for j in range(n):
                if i != j and self.distancias[i][j] != self.INF:
                    self.predecesores[i][j] = i
        
        # Algoritmo principal de Floyd-Warshall
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if (self.distancias[i][k] != self.INF and 
                        self.distancias[k][j] != self.INF and
                        self.distancias[i][k] + self.distancias[k][j] < self.distancias[i][j]):
                        
                        self.distancias[i][j] = self.distancias[i][k] + self.distancias[k][j]
                        self.predecesores[i][j] = self.predecesores[k][j]
        
        self.ejecutado = True
        return True
    
    def obtener_distancia(self, origen: str, destino: str) -> float:
        """Obtiene la distancia más corta entre dos vértices"""
        if not self.ejecutado:
            print("Error: Debe ejecutar el algoritmo primero")
            return self.INF
            
        if origen not in self.grafo.vertices or destino not in self.grafo.vertices:
            print(f"Error: Uno o ambos vértices ({origen}, {destino}) no existen")
            return self.INF
        
        i = self.grafo.vertices[origen]
        j = self.grafo.vertices[destino]
        
        return self.distancias[i][j]
    
    def obtener_ruta(self, origen: str, destino: str) -> Tuple[float, List[str]]:
        """Obtiene la distancia y ruta más corta entre dos vértices"""
        if not self.ejecutado:
            print("Error: Debe ejecutar el algoritmo primero")
            return self.INF, []
            
        if origen not in self.grafo.vertices or destino not in self.grafo.vertices:
            print(f"Error: Uno o ambos vértices ({origen}, {destino}) no existen")
            return self.INF, []
        
        i = self.grafo.vertices[origen]
        j = self.grafo.vertices[destino]
        
        distancia = self.distancias[i][j]
        
        if distancia == self.INF:
            return self.INF, []
        
        # Reconstruir la ruta
        ruta = self._reconstruir_ruta(i, j)
        nombres_ruta = [self.grafo.indices[idx] for idx in ruta]
        
        return distancia, nombres_ruta
    
    def _reconstruir_ruta(self, origen: int, destino: int) -> List[int]:
        """Reconstruye la ruta desde origen hasta destino usando índices"""
        if self.predecesores[origen][destino] == -1:
            return [origen, destino] if origen != destino else [origen]
        
        ruta_intermedia = self._reconstruir_ruta(origen, self.predecesores[origen][destino])
        ruta_final = self._reconstruir_ruta(self.predecesores[origen][destino], destino)
        
        # Evitar duplicar el vértice intermedio
        return ruta_intermedia + ruta_final[1:]
    
    def mostrar_matriz_distancias(self):
        """Muestra la matriz de distancias mínimas"""
        if not self.ejecutado:
            print("Error: Debe ejecutar el algoritmo primero")
            return
            
        print("\nMatriz de Distancias Mínimas:")
        print("     ", end="")
        for j in range(self.grafo.num_vertices):
            print(f"{self.grafo.indices[j]:>8}", end="")
        print()
        
        for i in range(self.grafo.num_vertices):
            print(f"{self.grafo.indices[i]:>8}", end=" ")
            for j in range(self.grafo.num_vertices):
                valor = self.distancias[i][j]
                if valor == self.INF:
                    print(f"{'∞':>8}", end="")
                else:
                    print(f"{valor:>8.1f}", end="")
            print()
    
    def detectar_ciclos_negativos(self) -> bool:
        """Detecta si hay ciclos negativos en el grafo"""
        if not self.ejecutado:
            print("Error: Debe ejecutar el algoritmo primero")
            return False
            
        for i in range(self.grafo.num_vertices):
            if self.distancias[i][i] < 0:
                return True
        return False
    
    def obtener_centro_grafo(self) -> Tuple[str, float]:
        """
        Encuentra el centro del grafo (vértice con menor excentricidad)
        Retorna el nombre del vértice y su excentricidad
        """
        if not self.ejecutado:
            print("Error: Debe ejecutar el algoritmo primero")
            return "", self.INF
        
        centro = ""
        min_excentricidad = self.INF
        
        for i in range(self.grafo.num_vertices):
            max_distancia = 0
            for j in range(self.grafo.num_vertices):
                if i != j and self.distancias[i][j] != self.INF:
                    max_distancia = max(max_distancia, self.distancias[i][j])
            
            if max_distancia < min_excentricidad:
                min_excentricidad = max_distancia
                centro = self.grafo.indices[i]
        
        return centro, min_excentricidad