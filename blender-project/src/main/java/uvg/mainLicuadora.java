package uvg;
import java.util.Scanner;

public class mainLicuadora {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        
        LicuadoraI licuadoraI = new LicuadoraI(0, 0, 0, false);
        
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("╔══════════════════════════════════════════╗");
            System.out.println("║                Licuadora                 ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Encender                             ║");
            System.out.println("║  2. Llenar la licuadora                  ║");
            System.out.println("║  3. Incrementar velocidad                ║");
            System.out.println("║  4. Bajar velocidad                      ║");
            System.out.println("║  5. Consultar velocidad                  ║");
            System.out.println("║  6. Consultar si la licuadora esta llena ║");
            System.out.println("║  7. Servir                               ║");
            System.out.println("║  8. Vaciar                               ║");
            System.out.println("║  0. Apagar                               ║");
            System.out.println("╚══════════════════════════════════════════╝");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1:
                    licuadoraI.encender();
                    break;
                case 2:
                    licuadoraI.llenar(0);
                    break;
                case 3:
                    licuadoraI.incrementarVelocidad();
                    break;
                case 4:
                    licuadoraI.decrementarVelocidad();
                    break;
                case 5:
                    licuadoraI.consultarVelocidad(licuadoraI.getVelocidad());
                    break;
                case 6:
                    licuadoraI.estaLlena();
                    break;
                case 7:
                    System.out.print("¿Cuánto volumen desea servir? ");
                    double volumenRestado = scanner.nextDouble();
                    licuadoraI.servir(volumenRestado);
                    break;
                case 8:
                    licuadoraI.vaciar();
                    break;
                case 0:
                    licuadoraI.apagar();
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();  
    }
}
