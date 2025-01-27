package uvg;
import java.util.Scanner;

/**
 * Clase que implementa la interfaz Licuadora y desarrolla los métodos
 * @author Marian Olivares, Marcela Ordoñez
 * @version 2.0
 */

public class LicuadoraI implements Licuadora {
    
    /** 
     * Objetos necesarios
     * @param velocidad indica la velocidad de la licuadora
     * @param volumen indica el volumen de la licuadora
     * @param volumenRestado indica el volumen restado de la licuadora
     * @param estado indica el estado de la licuadora ya sea apagada o encendida
     */

        private int velocidad;
        private int volumen;
        private int volumenRestado;
        private boolean estado;
        Scanner scanner = new Scanner(System.in);

    /**
     * Constructores de los objetos
     */

    public LicuadoraI(int velocidad, int volumen, int volumenRestado, boolean estado){
        this.velocidad = 0;
        this.volumen = 0;
        this.volumenRestado = 0;
        this.estado = false;
    }

    public int getVelocidad(){
        return velocidad;
    }
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }

    public int getVolumen(){
        return volumen;
    }
    public void setVolumen(int volumen){
        this.volumen = volumen;
    }

    public int getVolumenRestado(){
        return volumenRestado;
    }
    public void setVolumenRestado(int volumenRestado){
        this.volumenRestado = volumenRestado;
    }

    public boolean getEstado(){
        return estado;
    }
    public void setEstado(boolean estado){
        this.estado = estado;
    }



    /**
     * Métodos de la Intefaz Licuadora
     */



    /**
     * Método que enciende la licuadora
     */

    public void encender(){
        estado = true;
        System.out.println("La licuadora esta encendida");
    }//cierre del metodo

    /**
     * Método que apaga la licuadora
     */

    public void apagar(){
        estado = false;
        System.out.println("La licuadora está apagada");
    }//cierre del metodo

    /**
     * Método que verifica si la licuadora esta encendida
     * @return estado de la licuadora (apagada o encendida)
     */

    public boolean estaEncendida(){
        if (estado){
            System.out.println("La licuadora esta encendida");
        } else{
            System.out.println("La licuadora esta apagada");
        }
        return estado;
    }//cierre del metodo

    /**
     * Método que llena la licuadora
     * @return volumen que fue añadido a la licuadora
     */

    public double llenar(double volumen){
        System.out.println("¿Qué volumen desea agregar a la licuadora?")
        volumen = volumen + scanner.nextDouble();
        return volumen;
    } //cierre del metodo

    /**
     * Método que incrementa la velocidad de la licuadora
     */

    public int incrementarVelocidad(){
        int vmax = 10;
        if (velocidad < vmax){
            velocidad++;
            System.out.println("La velocidad ha sido incrementada a " + velocidad);
            return velocidad;
        } else {
            System.out.println("La velocidad no puede ser incrementada");
            return velocidad;
        }
    }//cierre del metodo

    /**
     * Método que decrementa la velocidad de la licuadora
     */

    public int decrementarVelocidad(){
        int vmin = 0;
        if (velocidad > vmin){
            velocidad--;
            System.out.println("La velocidad ha sido decrementada a " + velocidad);
        } else {
            System.out.println("La velocidad no puede ser decrementada");
        }
        return velocidad;
    }//cierre del metodo

    /**
     * Método que consulta la velocidad de la licuadora
     * @return velocidad de la licuadora
     */

    public int consultarVelocidad(int velocidad){
        if (!estado){
            System.out.println("La licuadora esta apagada. Velocidad: 0");
            return 0;
        }else{
            System.out.println("La velocidad de la licuadora es: "+velocidad);
            return velocidad;
        }
    }//cierre del metodo

    /**
     * Método que verifica si la licuadora esta llena
     * @return true si la licuadora esta llena, false si no lo esta
     */

    public boolean estaLlena(){
        if (volumen > 0){
            System.out.println("La licuadora esta llena");
            return true;
        }else{
            System.out.println("La licuadora no esta llena");
            return false;
        }
    }//cierre del metodo

    /**
     * Método que vacia la licuadora
     * @return volumen de la licuadora, el cual debería ser 0
     */

    public double vaciar(){
        if (volumen > 0){
            System.out.println("La licuadora ya se encuentra vacía");
            return 0;
        } else{
            volumen = 0;
            System.out.println("La licuadora se ha vaciado");
            return volumen;
        }

    }

    /**
     * Método que sirve el licuado
     * @param volumenRestado indica el volumen que se desea servir
     * @return volumen restante de la licuadora
     * @return volumen servido de la licuadora
     */

    public double servir(double volumenRestado){
        if (volumen >= volumenRestado){
            System.out.println("Se ha servido "+volumenRestado+" de licuado");
            volumen -= volumenRestado;
            return volumenRestado;
        } else{
            System.out.println("No se puede servir "+volumenRestado+" de licuado. Hay "+volumen+" de líquido");
            double servido = volumen; 
            volumen = 0;
            return servido;
        }
    }//cierre del metodo


}//cierre de la clase
