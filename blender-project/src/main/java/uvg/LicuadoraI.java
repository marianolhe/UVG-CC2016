package uvg;

public class LicuadoraI implements Licuadora{
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
