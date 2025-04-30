package uvg; 
import java.io.*;
/**
 * Clase para leer bits de un archivo de manera eficiente.
 */
class BitInputStream implements Closeable {
    private InputStream in;  // InputStream original
    private int currentByte; // El byte actual que estamos leyendo
    private int numBitsRemaining; // Número de bits restantes en el byte actual

    // Constructor
    public BitInputStream(InputStream in) {
        this.in = in;
        this.numBitsRemaining = 0;
    }

    // Método para leer un bit del archivo de entrada
    public int readBit() throws IOException {
        if (currentByte == -1)
            return -1;  // Fin de archivo
        if (numBitsRemaining == 0) {
            currentByte = in.read();  // Leer el siguiente byte
            if (currentByte == -1)
                return -1;  // Fin de archivo
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;  // Retorna el bit
    }

    // Cierra el stream de entrada
    @Override
    public void close() throws IOException {
        in.close();
    }
}
