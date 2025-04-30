package uvg; 
import java.io.*;
import java.nio.file.Files;
import java.util.*;

/**
 * Clase que implementa la compresión y descompresión de Huffman.
 */
class HuffmanCompressor {

    private Map<Character, String> huffmanCodes = new HashMap<>(); // Map para almacenar los códigos de Huffman

    // Método para comprimir el archivo de texto
    public void compress(String inputPath, String outputPath, String treePath) throws IOException {
        String text = readFile(inputPath);  // Leer el archivo de texto
        Map<Character, Integer> frequencyMap = buildFrequencyMap(text);  // Construir el mapa de frecuencias
        HuffmanNode root = buildHuffmanTree(frequencyMap);  // Construir el árbol de Huffman
        buildCodes(root, "");  // Generar los códigos de Huffman

        // Crear la cadena binaria codificada
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        writeEncodedFile(encodedText.toString(), outputPath);  // Escribir archivo comprimido
        writeTree(root, treePath);  // Guardar el árbol de Huffman
    }

    // Método para descomprimir el archivo de texto
    public void decompress(String encodedPath, String treePath, String outputPath) throws IOException, ClassNotFoundException {
        HuffmanNode root = readTree(treePath);  // Leer el árbol de Huffman
        String bitString = readEncodedFile(encodedPath);  // Leer el archivo comprimido

        // Decodificar el contenido usando el árbol de Huffman
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : bitString.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;  // Navegar por el árbol
            if (current.isLeaf()) {
                decoded.append(current.character);  // Añadir el carácter cuando se alcanza una hoja
                current = root;  // Volver al nodo raíz
            }
        }

        // Escribir el archivo descomprimido
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(decoded.toString());
        }
    }

    // Método para leer un archivo y convertirlo en una cadena
    private String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(new File(path).toPath()));
    }

    // Método para construir el mapa de frecuencias de los caracteres
    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    // Método para construir el árbol de Huffman
    private HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Crear el árbol fusionando los dos nodos con menor frecuencia
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }
        return pq.poll();  // Nodo raíz del árbol
    }

    // Método para generar los códigos de Huffman a partir del árbol
    private void buildCodes(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);  // Almacenar el código binario del carácter
        } else {
            buildCodes(node.left, code + "0");  // Recursión para el hijo izquierdo
            buildCodes(node.right, code + "1");  // Recursión para el hijo derecho
        }
    }

    // Método para escribir el archivo comprimido en formato binario
    private void writeEncodedFile(String bitString, String path) throws IOException {
        try (BitOutputStream bos = new BitOutputStream(new FileOutputStream(path))) {
            for (char bit : bitString.toCharArray()) {
                bos.writeBit(bit == '1' ? 1 : 0);
            }
        }
    }

    // Método para leer un archivo comprimido en formato binario
    private String readEncodedFile(String path) throws IOException {
        StringBuilder bits = new StringBuilder();
        try (BitInputStream bis = new BitInputStream(new FileInputStream(path))) {
            int bit;
            while ((bit = bis.readBit()) != -1) {
                bits.append(bit);
            }
        }
        return bits.toString();
    }

    // Método para guardar el árbol de Huffman en un archivo
    private void writeTree(HuffmanNode root, String path) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(root);
        }
    }

    // Método para leer el árbol de Huffman desde un archivo
    private HuffmanNode readTree(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (HuffmanNode) ois.readObject();
        }
    }
}



