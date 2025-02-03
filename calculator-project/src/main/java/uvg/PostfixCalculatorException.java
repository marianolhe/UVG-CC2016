// PostfixCalculatorException.java
package uvg;
/**
 * Excepción personalizada para manejar errores en la calculadora postfix.
 */
public class PostfixCalculatorException extends Exception {
    public static final int ERROR_DIVISION_CERO = 1;
    public static final int ERROR_OPERANDOS_INSUFICIENTES = 2;
    public static final int ERROR_CARACTER_INVALIDO = 3;
    public static final int ERROR_EXPRESION_VACIA = 4;
    public static final int ERROR_EXPRESION_MALFORMADA = 5;
    public static final int ERROR_MODULO_CERO = 6;
    public static final int ERROR_OPERADOR_INVALIDO = 7;
    public static final int ERROR_EXPRESION_NULA = 8;

    private int codigoError;

public PostfixCalculatorException(String mensaje, int codigoError) {
    super(mensaje);
    this.codigoError = codigoError;
}

public int getCodigoError() {
    return codigoError;
}
}