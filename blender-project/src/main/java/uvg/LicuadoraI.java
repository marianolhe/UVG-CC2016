package uvg;
/**
 * Clase que implementa la interfaz Licuadora y desarrolla los métodos
 * @author Marian Olivares, Marcela Ordoñez
 * @version 1.0
 */

public class LicuadoraI implements Licuadora{
    /** Objetos necesarios
        * @param velocidad indica la velocidad de la licuadora
        */@param volumen indica el volumen de la licuadora
        */@param volumenRestado indica el volumen restado de la licuadora
        */@param estado indica el estado de la licuadora ya sea apagada o encendida
        */

        private byte velocidad;
        private byte volumen;
        private byte volumenRestado;
        private String estado;

    /**
     * Constructores de los objetos
     */

    public LicuadoraI(byte velocidad, byte volumen, byte volumenRestado, String estado){
        this.velocidad = 0;
        this.volumen = 0;
        this.volumenRestado = 0;
        this.estado = "apagada";
    }

    public byte get velocidad(){
        return velocidad;
    }
    public void set velocidad(byte velocidad){
        this.velocidad = velocidad;
    }

    public byte get volumen(){
        return volumen;
    }
    public void set volumen(byte volumen){
        this.volumen = volumen;
    }

    public byte get volumenRestado(){
        return volumenRestado;
    }
    public void set volumenRestado(byte volumenRestado){
        this.volumenRestado = volumenRestado;
    }

    public String get estado(){
        return estado;
    }
    public void set estado(String estado){
        this.estado = estado;
    }



    /**
     * Métodos de la Intefaz Licuadora
     */

    public void encender(){
        System.out.println("La licuadora esta encendida");
    }

    public void apagar(){
        System.out.println("La licuadora esta apagada");
    }

    public boolean estaEncendida(){
        return true;
    }

    public double llenar(double volumen){
        return volumen;
    }

    public int incrementarVelocidad(){
        return velocidad;
    }

    public int decrementarVelocidad(){
        return velocidad;
    }

    public int consultarVelocidad(){
        return velocidad;
    }

    public boolean estaLlena(){
        return true;
    }

    public double vaciar(){
        return 0;
    }

    public double servir(double volumenRestado){
        return volumenRestado;
    }


}
