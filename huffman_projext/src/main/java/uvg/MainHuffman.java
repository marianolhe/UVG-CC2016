package uvg; 
import java.io.*;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Clase principal con menú interactivo para el compresor Huffman.
 * Usa selectores de archivos gráficos para mejorar la experiencia.
 */
public class MainHuffman {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HuffmanCompressor compressor = new HuffmanCompressor();
    private static final JFileChooser fileChooser = new JFileChooser();
    
    public static void main(String[] args) {
        boolean running = true;
        
        // Configurar el selector de archivos para que inicie en el directorio actual
        fileChooser.setCurrentDirectory(new File("."));
        
        while (running) {
            clearScreen();
            System.out.println("====== COMPRESOR HUFFMAN ======");
            System.out.println("1. Comprimir archivo");
            System.out.println("2. Descomprimir archivo");
            System.out.println("3. Salir");
            System.out.print("\nSeleccione una opción: ");
            
            try {
                int option = Integer.parseInt(scanner.nextLine());
                
                switch (option) {
                    case 1:
                        compressFile();
                        break;
                    case 2:
                        decompressFile();
                        break;
                    case 3:
                        running = false;
                        System.out.println("¡Hasta pronto!");
                        break;
                    default:
                        System.out.println("Opción no válida. Presione Enter para continuar...");
                        scanner.nextLine();
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor ingrese un número válido. Presione Enter para continuar...");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Presione Enter para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void compressFile() {
        clearScreen();
        System.out.println("==== COMPRIMIR ARCHIVO ====\n");
        System.out.println("Se abrirá un selector de archivos para elegir el archivo a comprimir.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        
        // Selector para archivo a comprimir
        fileChooser.setDialogTitle("Seleccionar archivo a comprimir");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nNo se seleccionó ningún archivo. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String inputFile = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Archivo seleccionado: " + inputFile);
        
        // Selector para archivo comprimido
        fileChooser.setDialogTitle("Guardar archivo comprimido");
        fileChooser.setSelectedFile(new File(inputFile + ".huff"));
        
        result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nOperación cancelada. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String outputFile = fileChooser.getSelectedFile().getAbsolutePath();
        if (!outputFile.endsWith(".huff")) {
            outputFile += ".huff";
        }
        System.out.println("Archivo comprimido se guardará como: " + outputFile);
        
        // Selector para archivo de árbol
        fileChooser.setDialogTitle("Guardar árbol de Huffman");
        fileChooser.setSelectedFile(new File(inputFile + ".hufftree"));
        
        result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nOperación cancelada. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String treeFile = fileChooser.getSelectedFile().getAbsolutePath();
        if (!treeFile.endsWith(".hufftree")) {
            treeFile += ".hufftree";
        }
        System.out.println("Árbol de Huffman se guardará como: " + treeFile);
        
        try {
            // Guardar el tamaño del archivo original para mostrar estadísticas
            long originalSize = Files.size(Paths.get(inputFile));
            
            // Comprimir el archivo
            compressor.compress(inputFile, outputFile, treeFile);
            
            // Calcular y mostrar estadísticas
            long compressedSize = Files.size(Paths.get(outputFile));
            double compressionRatio = 100.0 - ((double)compressedSize / originalSize) * 100.0;
            
            String message = String.format(
                "¡Archivo comprimido exitosamente!\n" +
                "Tamaño original: %s\n" +
                "Tamaño comprimido: %s\n" +
                "Ratio de compresión: %.2f%%", 
                formatSize(originalSize), 
                formatSize(compressedSize), 
                compressionRatio
            );
            
            System.out.println("\n" + message);
            JOptionPane.showMessageDialog(null, message, "Compresión Exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException e) {
            String errorMsg = "Error al comprimir el archivo: " + e.getMessage();
            System.out.println("\n" + errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
    }
    
    private static void decompressFile() {
        clearScreen();
        System.out.println("==== DESCOMPRIMIR ARCHIVO ====\n");
        System.out.println("Se abrirá un selector de archivos para elegir el archivo comprimido.");
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        
        // Selector para archivo comprimido
        fileChooser.setDialogTitle("Seleccionar archivo comprimido (.huff)");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos Huffman", "huff"));
        
        int result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nNo se seleccionó ningún archivo. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String inputFile = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Archivo comprimido seleccionado: " + inputFile);
        
        // Selector para archivo de árbol
        fileChooser.setDialogTitle("Seleccionar árbol de Huffman (.hufftree)");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Árboles Huffman", "hufftree"));
        
        // Intentar sugerir el archivo de árbol correspondiente
        String suggestedTreeFile = inputFile.replaceAll("\\.huff$", ".hufftree");
        fileChooser.setSelectedFile(new File(suggestedTreeFile));
        
        result = fileChooser.showOpenDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nNo se seleccionó el archivo de árbol. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String treeFile = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Árbol de Huffman seleccionado: " + treeFile);
        
        // Selector para guardar archivo descomprimido
        fileChooser.setDialogTitle("Guardar archivo descomprimido");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        
        // Sugerir nombre para el archivo descomprimido
        String suggestedOutputFile = inputFile.replaceAll("\\.huff$", "_recuperado.txt");
        fileChooser.setSelectedFile(new File(suggestedOutputFile));
        
        result = fileChooser.showSaveDialog(null);
        if (result != JFileChooser.APPROVE_OPTION) {
            System.out.println("\nOperación cancelada. Presione Enter para volver al menú principal...");
            scanner.nextLine();
            return;
        }
        
        String outputFile = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Archivo descomprimido se guardará como: " + outputFile);
        
        try {
            // Descomprimir el archivo
            compressor.decompress(inputFile, treeFile, outputFile);
            
            String message = String.format(
                "¡Archivo descomprimido exitosamente!\n" +
                "Archivo guardado como: %s\n" +
                "Tamaño del archivo: %s", 
                outputFile, 
                formatSize(Files.size(Paths.get(outputFile)))
            );
            
            System.out.println("\n" + message);
            JOptionPane.showMessageDialog(null, message, "Descompresión Exitosa", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (IOException | ClassNotFoundException e) {
            String errorMsg = "Error al descomprimir el archivo: " + e.getMessage();
            System.out.println("\n" + errorMsg);
            JOptionPane.showMessageDialog(null, errorMsg, "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("\nPresione Enter para volver al menú principal...");
        scanner.nextLine();
    }
    
    private static String formatSize(long bytes) {
        final String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double size = bytes;
        
        while (size > 1024 && unitIndex < units.length - 1) {
            size /= 1024;
            unitIndex++;
        }
        
        return String.format("%.2f %s", size, units[unitIndex]);
    }
    
    private static void clearScreen() {
        // Intenta limpiar la pantalla en diferentes sistemas operativos
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Si falla la limpieza de pantalla, imprime varias líneas en blanco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}