package uvg; 
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class HuffmanTest {

    private final HuffmanCompressor compressor = new HuffmanCompressor();

    @Test
    void testBuildFrequencyMap() {
        String text = "aabbbbcc";
        Map<Character, Integer> freqMap = compressor.buildFrequencyMap(text);

        assertEquals(2, freqMap.get('a'));
        assertEquals(4, freqMap.get('b'));
        assertEquals(2, freqMap.get('c'));
    }

    @Test
    void testHuffmanCompressionAndDecompression() throws Exception {
        String originalText = "este es un texto de prueba para huffman";

        // Crear archivo temporal con texto original
        Path tempInput = Files.createTempFile("input", ".txt");
        Files.write(tempInput, originalText.getBytes());

        // Rutas de salida
        Path tempCompressed = Files.createTempFile("compressed", ".huff");
        Path tempTree = Files.createTempFile("tree", ".hufftree");
        Path tempDecompressed = Files.createTempFile("decompressed", ".txt");

        // Comprimir y descomprimir
        compressor.compress(tempInput.toString(), tempCompressed.toString(), tempTree.toString());
        compressor.decompress(tempCompressed.toString(), tempTree.toString(), tempDecompressed.toString());

        // Leer resultado
        String decompressedText = Files.readString(tempDecompressed);

        assertEquals(originalText, decompressedText);

        // Limpiar archivos
        tempInput.toFile().delete();
        tempCompressed.toFile().delete();
        tempTree.toFile().delete();
        tempDecompressed.toFile().delete();
    }

    @Test
    void testBitOutputAndInput() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (BitOutputStream bos = new BitOutputStream(baos)) {
            bos.writeBit(1);
            bos.writeBit(0);
            bos.writeBit(1);
            bos.writeBit(1);
            bos.writeBit(0);
        }

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        try (BitInputStream bis = new BitInputStream(bais)) {
            assertEquals(1, bis.readBit());
            assertEquals(0, bis.readBit());
            assertEquals(1, bis.readBit());
            assertEquals(1, bis.readBit());
            assertEquals(0, bis.readBit());
        }
    }
}
