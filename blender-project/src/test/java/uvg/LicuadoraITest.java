package uvg;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LicuadoraITest {

    private LicuadoraI licuadora;

    @BeforeEach
    void setUp() {
        licuadora = new LicuadoraI((byte) 0, (byte) 0, (byte) 0, "false");
    }

    @Test
    void testEncender() {
        licuadora.encender();
        assertTrue(licuadora.getEstado(), "La licuadora debería estar encendida");
    }

    @Test
    void testApagar() {
        licuadora.apagar();
        assertFalse(licuadora.getEstado(), "La licuadora debería estar apagada");
    }

    @Test
    void testIncrementarVelocidad() {
        licuadora.setVelocidad((byte) 5);
        licuadora.incrementarVelocidad();
        assertEquals(6, licuadora.getVelocidad(), "La velocidad debería incrementarse en 1");
    }

    @Test
    void testNoIncrementarVelocidadMasAllaDelLimite() {
        licuadora.setVelocidad((byte) 10);
        licuadora.incrementarVelocidad();
        assertEquals(10, licuadora.getVelocidad(), "La velocidad no debería exceder el límite de 10");
    }

    @Test
    void testLlenar() {
        licuadora.setVolumen((byte) 5);
        double nuevoVolumen = licuadora.llenar(3.0);
        assertEquals(8, nuevoVolumen, "El volumen debería ser la suma del actual más el agregado");
    }

    @Test
    void testVaciar() {
        licuadora.setVolumen((byte) 5);
        double volumenVaciado = licuadora.vaciar();
        assertEquals(0, volumenVaciado, "El volumen debería ser 0 después de vaciar la licuadora");
    }

    @Test
    void testServirDentroDeLosLimites() {
        licuadora.setVolumen((byte) 8);
        double servido = licuadora.servir(5);
        assertEquals(5, servido, "El volumen servido debería ser 5");
        assertEquals(3, licuadora.getVolumen(), "El volumen restante debería ser 3");
    }

    @Test
    void testServirMasDeLoDisponible() {
        licuadora.setVolumen((byte) 4);
        double servido = licuadora.servir(6);
        assertEquals(4, servido, "El volumen servido debería ser todo el contenido disponible");
        assertEquals(0, licuadora.getVolumen(), "El volumen restante debería ser 0");
    }

    @Test
    void testEstaLlena() {
        licuadora.setVolumen((byte) 5);
        assertTrue(licuadora.estaLlena(), "La licuadora debería estar llena");
    }
}


}
