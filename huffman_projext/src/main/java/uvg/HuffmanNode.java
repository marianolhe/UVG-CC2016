
package uvg; 
import java.io.*;

/**
 * Representa un nodo en el árbol de Huffman.
 * Implementa Comparable para que los nodos puedan ser comparados por frecuencia.
 */
class HuffmanNode implements Comparable<HuffmanNode>, Serializable {
    char character; // Carácter que representa el nodo
    int frequency;  // Frecuencia del carácter en el archivo original
    HuffmanNode left, right; // Hijos izquierdo y derecho

    // Constructor de un nodo con un carácter y su frecuencia
    HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    // Método para verificar si el nodo es una hoja (es decir, no tiene hijos)
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // Implementación del método compareTo para ordenar los nodos por frecuencia
    @Override
    public int compareTo(HuffmanNode o) {
        return this.frequency - o.frequency;
    }
}