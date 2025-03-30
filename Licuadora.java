package uvg; 
/**
 * Interfaz realizada para comprobar funcionamiento con otros grupos
 * @author toda la clase
 * @version 2.0
 */

 public interface Licuadora {
        void encender();
        void apagar();
        boolean estaEncendida();
        double llenar(double volumen);
        int incrementarVelocidad();
        int decrementarVelocidad();
        int consultarVelocidad(int velocidad);
        boolean estaLlena();
        double vaciar();
        double servir(double volumenRestado);
}

        
            
