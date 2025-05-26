import unittest
from Grafo import Grafo

class TestGrafo(unittest.TestCase):
    
    def setUp(self):
        self.grafo = Grafo()
    
    def test_agregar_vertice(self):
        indice = self.grafo.agregar_vertice("A")
        self.assertEqual(indice, 0)
        self.assertTrue(self.grafo.existe_vertice("A"))
        self.assertEqual(self.grafo.num_vertices, 1)
    
    def test_agregar_arco(self):
        self.grafo.agregar_arco("A", "B", 5.0)
        self.assertTrue(self.grafo.existe_arco("A", "B"))
        self.assertEqual(self.grafo.obtener_peso("A", "B"), 5.0)
    
    def test_eliminar_arco(self):
        self.grafo.agregar_arco("A", "B", 3.0)
        self.grafo.eliminar_arco("A", "B")
        self.assertFalse(self.grafo.existe_arco("A", "B"))

if __name__ == '__main__':
    unittest.main()