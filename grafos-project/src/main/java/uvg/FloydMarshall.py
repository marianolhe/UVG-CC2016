class FloydWarshall:
    """
    Implementación del algoritmo de Floyd-Warshall para encontrar
    las rutas más cortas entre todos los pares de vértices
    """
    
    def __init__(self, grafo: Grafo):
        self.grafo = grafo
        self.distancias = []
        self.predecesores = []
        self.INF = float('inf')
    
    def ejecutar(self):
        """Ejecuta el algoritmo de Floyd-Warshall"""
        n = self.grafo.num_vertices
        
        # Inicializar matrices de distancias y predecesores
        self.distancias = copy.deepcopy(self.grafo.matriz_adyacencia)
        self.predecesores = [[-1 for _ in range(n)] for _ in range(n)]
        
        # Inicializar predecesores
        for i in range(n):
            for j in range(n):
                if i != j and self.distancias[i][j] != self.INF:
                    self.predecesores[i][j] = i
        
        # Algoritmo de Floyd-Warshall
        for k in range(n):
            for i in range(n):
                for j in range(n):
                    if (self.distancias[i][k] != self.INF and 
                        self.distancias[k][j] != self.INF and
                        self.distancias[i][k] + self.distancias[k][j] < self.distancias[i][j]):
                        
                        self.distancias[i][j] = self.distancias[i][k] + self.distancias[k][j]
                        self.predecesores[i][j] = self.predecesores[k][j]
    
    def obtener_ruta(self, origen: str, destino: str) -> Tuple[float, List[str]]:
        """Obtiene la distancia y ruta más corta entre dos vértices"""
        if origen not in self.grafo.vertices or destino not in self.grafo.vertices:
            return self.INF, []
        
        i = self.grafo.vertices[origen]
        j = self.grafo.vertices[destino]
        
        if self.distancias[i][j] == self.INF:
            return self.INF, []
        
        # Reconstruir la ruta
        ruta = []
        actual = j
        while actual != -1:
            ruta.append(self.grafo.indices[actual])
            actual = self.predecesores[i][actual]
        
        ruta.reverse()
        return self.distancias[i][j], ruta
    
    def calcular_centro_grafo(self) -> str:
        """Calcula el centro del grafo (vértice con menor excentricidad)"""
        if self.grafo.num_vertices == 0:
            return ""
        
        excentricidades = []
        
        for i in range(self.grafo.num_vertices):
            max_distancia = 0
            for j in range(self.grafo.num_vertices):
                if i != j and self.distancias[i][j] != self.INF:
                    max_distancia = max(max_distancia, self.distancias[i][j])
                elif i != j and self.distancias[i][j] == self.INF:
                    max_distancia = self.INF
                    break
            excentricidades.append(max_distancia)
        
        # Encontrar el vértice con menor excentricidad
        min_excentricidad = min(excentricidades)
        centro_indice = excentricidades.index(min_excentricidad)
        
        return self.grafo.indices[centro_indice]