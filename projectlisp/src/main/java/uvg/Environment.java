package uvg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Entorno de ejecución que mantiene variables y funciones.
 */
public class Environment {
    private final Map<String, Object> bindings; // Mapa que guarda las variables y funciones
    private final Environment outer; // Referencia al entorno externo (si lo hay)

    /**
     * Constructor que crea un entorno vacío.
     */
    public Environment() {
        this(null);
    }

    /**
     * Constructor que permite crear un entorno con un entorno externo (para entornos anidados).
     * @param outer El entorno externo en el que se buscarán las variables si no están en el entorno actual.
     */    
    public Environment(Environment outer) {
        this.bindings = new HashMap<>();
        this.outer = outer;
        if (outer == null) {
            setupGlobalEnvironment(); // Si no hay entorno externo, se configura el entorno global
        }
    }

    /**
     * Configura el entorno global con operaciones básicas (como suma, resta, etc.).
     */    
    private void setupGlobalEnvironment() {
        // Operadores aritméticos básicos
        bindings.put("+", (BiFunction<List<Object>, Environment, Object>) this::sum);
        bindings.put("-", (BiFunction<List<Object>, Environment, Object>) this::subtract);
        bindings.put("*", (BiFunction<List<Object>, Environment, Object>) this::multiply);
        bindings.put("/", (BiFunction<List<Object>, Environment, Object>) this::divide);
        
        // Comparadores
        bindings.put("=", (BiFunction<List<Object>, Environment, Object>) this::equals);
        bindings.put("<", (BiFunction<List<Object>, Environment, Object>) this::lessThan);
        bindings.put(">", (BiFunction<List<Object>, Environment, Object>) this::greaterThan);
    }

    /**
     * Realiza la suma de todos los argumentos.
     * @param args Los valores a sumar.
     * @param env El entorno actual.
     * @return La suma de los valores.
     */    
    private Object sum(List<Object> args, Environment env) {
        return args.stream()
                .mapToInt(arg -> ((Number) arg).intValue())
                .sum();
    }

    /**
     * Realiza la resta de los argumentos.
     * @param args Los valores a restar.
     * @param env El entorno actual.
     * @return El resultado de la resta.
     */    
    private Object subtract(List<Object> args, Environment env) {
        if (args.isEmpty()) return 0;
        if (args.size() == 1) return -((Number) args.get(0)).intValue();
        
        int result = ((Number) args.get(0)).intValue();
        for (int i = 1; i < args.size(); i++) {
            result -= ((Number) args.get(i)).intValue();
        }
        return result;
    }

    /**
     * Realiza la multiplicación de todos los argumentos.
     * @param args Los valores a multiplicar.
     * @param env El entorno actual.
     * @return El resultado de la multiplicación.
     */
    private Object multiply(List<Object> args, Environment env) {
        return args.stream()
                .mapToInt(arg -> ((Number) arg).intValue())
                .reduce(1, (a, b) -> a * b);
    }

    /**
     * Realiza la división de los argumentos.
     * @param args Los valores a dividir.
     * @param env El entorno actual.
     * @return El resultado de la división.
     */    
    private Object divide(List<Object> args, Environment env) {
        if (args.isEmpty()) throw new RuntimeException("División requiere al menos un argumento");
        
        int result = ((Number) args.get(0)).intValue();
        for (int i = 1; i < args.size(); i++) {
            int divisor = ((Number) args.get(i)).intValue();
            if (divisor == 0) throw new RuntimeException("División por cero");
            result /= divisor;
        }
        return result;
    }

    /**
     * Verifica si todos los argumentos son iguales.
     * @param args Los valores a comparar.
     * @param env El entorno actual.
     * @return True si todos los argumentos son iguales, false en caso contrario.
     */    
    private Object equals(List<Object> args, Environment env) {
        if (args.size() < 2) return true;
        Object first = args.get(0);
        return args.stream().skip(1).allMatch(arg -> arg.equals(first));
    }

    /**
     * Verifica si el primer argumento es menor que el segundo.
     * @param args Los valores a comparar.
     * @param env El entorno actual.
     * @return True si el primer valor es menor que el segundo, false en caso contrario.
     */    
    private Object lessThan(List<Object> args, Environment env) {
        if (args.size() != 2) throw new RuntimeException("< requiere exactamente 2 argumentos");
        return ((Number) args.get(0)).intValue() < ((Number) args.get(1)).intValue();
    }

    /**
     * Verifica si el primer argumento es mayor que el segundo.
     * @param args Los valores a comparar.
     * @param env El entorno actual.
     * @return True si el primer valor es mayor que el segundo, false en caso contrario.
     */    
    private Object greaterThan(List<Object> args, Environment env) {
        if (args.size() != 2) throw new RuntimeException("> requiere exactamente 2 argumentos");
        return ((Number) args.get(0)).intValue() > ((Number) args.get(1)).intValue();
    }

    /**
     * Define una nueva variable en el entorno.
     * @param name El nombre de la variable.
     * @param value El valor de la variable.
     */    
    public void define(String name, Object value) {
        bindings.put(name, value);
    }

    /**
     * Busca el valor de una variable en el entorno.
     * @param name El nombre de la variable.
     * @return El valor de la variable.
     */    
    public Object lookup(String name) {
        if (bindings.containsKey(name)) {
            return bindings.get(name);
        }
        if (outer != null) {
            return outer.lookup(name);
        }
        throw new RuntimeException("Variable no definida: " + name);
    }

    /**
     * Crea un nuevo entorno que extiende el entorno actual con las variables definidas.
     * @param params Los nombres de las variables.
     * @param args Los valores de las variables.
     * @return Un nuevo entorno con las variables definidas.
     */    
    public Environment extend(List<String> params, List<Object> args) {
        Environment extended = new Environment(this);
        for (int i = 0; i < params.size(); i++) {
            extended.define(params.get(i), args.get(i));
        }
        return extended;
    }
}