package uvg; 
import java.io.*;

/**
 * Clase principal con el método main para ejecutar el compresor desde la línea de comandos.
 */
public class MainHuffman {
    public static void main(String[] args) {
        HuffmanCompressor compressor = new HuffmanCompressor();
        try {
            if (args.length < 2) {
                System.out.println("Modo de uso:");
                System.out.println("Para comprimir: java Main compress archivo.txt archivo.huff arbol.hufftree");
                System.out.println("Para descomprimir: java Main decompress archivo.huff arbol.hufftree archivo_recuperado.txt");
                return;
            }

            String mode = args[0];

            if (mode.equals("compress")) {
                if (args.length != 4) {
                    System.out.println("Argumentos inválidos para compresión.");
                    return;
                }
                compressor.compress(args[1], args[2], args[3]);
                System.out.println("Archivo comprimido exitosamente.");

            } else if (mode.equals("decompress")) {
                if (args.length != 4) {
                    System.out.println("Argumentos inválidos para descompresión.");
                    return;
                }
                compressor.decompress(args[1], args[2], args[3]);
                System.out.println("Archivo descomprimido exitosamente.");

            } else {
                System.out.println("Modo inválido. Usa 'compress' o 'decompress'.");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }}