package uvg.edu.com;
/**
 * Interfaz realizada para comprobar funcionamiento con otros grupos
 * @author toda la clase
 * @version 1.0
 */

public interface Licuadora {
    public interface Licuadora {
        void encender();
        void apagar();
        boolean estaEncendida();
        double llenar(double volumen);
        int incrementarVelocidad();
        int decrementarVelocidad();
        int consultarVelocidad();
        boolean estaLlena();
        double vaciar();
        double servir(double volumenRestado);
        }}
            