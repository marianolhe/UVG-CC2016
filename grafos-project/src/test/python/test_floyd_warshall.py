import unittest
from Grafo import Grafo
from FloydMarshall import FloydWarshall

class TestFloydWarshall(unittest.TestCase):
    
    def setUp(self):
        self.grafo = Grafo()
        self.grafo.agregar_arco("A", "B", 3)
        self.grafo.agregar_arco("B", "C", 2)
        self.grafo.agregar_arco("A", "C", 6)
        self.floyd = FloydWarshall(self.grafo)
    
    def test_ejecutar_algoritmo(self):
        resultado = self.floyd.ejecutar()
        self.assertTrue(resultado)
        self.assertTrue(self.floyd.ejecutado)
    
    def test_obtener_ruta_mas_corta(self):
        self.floyd.ejecutar()
        distancia, ruta = self.floyd.obtener_ruta("A", "C")
        self.assertEqual(distancia, 5)  # A->B->C = 3+2 = 5
        self.assertEqual(ruta, ["A", "B", "C"])
    
    def test_centro_grafo(self):
        self.floyd.ejecutar()
        centro, excentricidad = self.floyd.obtener_centro_grafo()
        self.assertIsNotNone(centro)
        self.assertGreater(excentricidad, 0)

if __name__ == '__main__':
    unittest.main()