package uvg;

// --- Clase MainVectorHeap.java ---
import java.io.*;

public class MainVectorHeap {
    public static void main(String[] args) {
        VectorHeap<Paciente> cola = new VectorHeap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    cola.add(new Paciente(partes[0].trim(), partes[1].trim(), partes[2].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!cola.isEmpty()) {
            System.out.println("Atendiendo a: " + cola.remove());
        }
    }
}