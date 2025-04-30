
package uvg; 
import java.io.*;

/**
 * Clase para escribir bits en un archivo de manera eficiente.
 */
class BitOutputStream implements Closeable {
    private OutputStream out;   // OutputStream original
    private int currentByte;    // El byte actual que estamos escribiendo
    private int numBitsFilled;  // Número de bits ocupados en el byte actual

    // Constructor
    public BitOutputStream(OutputStream out) {
        this.out = out;
        this.currentByte = 0;
        this.numBitsFilled = 0;
    }

    // Método para escribir un bit en el archivo de salida
    public void writeBit(int b) throws IOException {
        if (b != 0 && b != 1)
            throw new IllegalArgumentException("Argumento debe ser 0 o 1");
        currentByte = (currentByte << 1) | b;
        numBitsFilled++;
        if (numBitsFilled == 8) {
            out.write(currentByte);  // Escribe el byte completo
            numBitsFilled = 0;
            currentByte = 0;
        }
    }

    // Cierra el stream de salida
    @Override
    public void close() throws IOException {
        while (numBitsFilled != 0)
            writeBit(0);  // Rellenar los bits restantes con 0s
        out.close();
    }
}
