import sys
import os
from typing import Dict, List, Tuple, Optional
import copy

class Grafo:
    """
    Implementación de un grafo dirigido usando matriz de adyacencia
    para el sistema de logística con diferentes condiciones climáticas
    """
    
    def __init__(self):
        self.vertices = {}  # Mapeo de nombre -> índice
        self.indices = {}   # Mapeo de índice -> nombre
        self.matriz_adyacencia = []
        self.num_vertices = 0
        self.INF = float('inf')
        
    def agregar_vertice(self, nombre: str) -> int:
        """Agrega un vértice al grafo y retorna su índice"""
        if nombre not in self.vertices:
            indice = self.num_vertices
            self.vertices[nombre] = indice
            self.indices[indice] = nombre
            self.num_vertices += 1
            
            # Expandir matriz de adyacencia
            for fila in self.matriz_adyacencia:
                fila.append(self.INF)
            
            nueva_fila = [self.INF] * self.num_vertices
            nueva_fila[indice] = 0  # Distancia a sí mismo es 0
            self.matriz_adyacencia.append(nueva_fila)
            
            return indice
        return self.vertices[nombre]
    
    def agregar_arco(self, origen: str, destino: str, peso: float):
        """Agrega un arco dirigido entre dos vértices"""
        i = self.agregar_vertice(origen)
        j = self.agregar_vertice(destino)
        self.matriz_adyacencia[i][j] = peso
    
    def eliminar_arco(self, origen: str, destino: str):
        """Elimina un arco entre dos vértices"""
        if origen in self.vertices and destino in self.vertices:
            i = self.vertices[origen]
            j = self.vertices[destino]
            self.matriz_adyacencia[i][j] = self.INF
    
    def obtener_peso(self, origen: str, destino: str) -> float:
        """Obtiene el peso del arco entre dos vértices"""
        if origen in self.vertices and destino in self.vertices:
            i = self.vertices[origen]
            j = self.vertices[destino]
            return self.matriz_adyacencia[i][j]
        return self.INF
    
    def mostrar_matriz(self):
        """Muestra la matriz de adyacencia"""
        print("\nMatriz de Adyacencia:")
        print("     ", end="")
        for j in range(self.num_vertices):
            print(f"{self.indices[j]:>8}", end="")
        print()
        
        for i in range(self.num_vertices):
            print(f"{self.indices[i]:>8}", end=" ")
            for j in range(self.num_vertices):
                if self.matriz_adyacencia[i][j] == self.INF:
                    print(f"{'∞':>8}", end="")
                else:
                    print(f"{self.matriz_adyacencia[i][j]:>8.1f}", end="")
            print()
    
    def obtener_vertices(self) -> List[str]:
        """Retorna la lista de nombres de vértices"""
        return list(self.vertices.keys())