public class LicuadoraG1 implements Licuadora {
    
    private int velocidad;
    private boolean estado;
    private double capacidadMax;
    private double cantidadActual;

    public LicuadoraG1(int velocidad, double capacidadMax) {
        this.velocidad = velocidad;
        this.estado = false;
        this.capacidadMax = capacidadMax;
        this.cantidadActual = 0;
    }

    @Override
    public void encender() {
        this.estado = true;
        this.velocidad = 1;
    }

    @Override
    public void apagar() {
        this.estado = false;
        this.velocidad = 0;
    }

    @Override
    public double llenar(double volumen) {
        if (this.capacidadMax >= volumen) {
            this.cantidadActual += volumen;
        } else {
            this.cantidadActual = this.capacidadMax;
        }
        return this.cantidadActual;
    }

    @Override
    public int incrementarVelocidad() {
        if (this.velocidad < 3) {
            this.velocidad += 1;
        }
        return this.velocidad;
    }

    @Override
    public int decrecerVelocidad() {
        if (this.velocidad > 1) {
            this.velocidad -= 1;
        }
        return this.velocidad;
    }

    @Override
    public int consultarVelocidad(int velocidad) {
        return this.velocidad;
    }

    @Override
    public boolean estaLlena() {
        return this.cantidadActual > 0;
    }

    @Override
    public void vaciar() {
        this.cantidadActual = 0;
    }

    @Override
    public double servir(double volumenTestado) {
        if (volumenTestado <= this.cantidadActual) {
            this.cantidadActual -= volumenTestado;
        } else {
            this.cantidadActual = 0;
        }
        return this.cantidadActual;
    }
}
