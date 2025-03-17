package uvg;

import java.util.List;

/**
 * Representa una función definida por el usuario en un entorno específico.
 * Una función tiene parámetros, un cuerpo que es una expresión, 
 * y un entorno donde se encuentra definida.
 */
public class Function {
    private final List<String> params;
    private final Expression body;
    private final Environment env;

    /**
     * Constructor para crear una función.
     * @param params Los nombres de los parámetros de la función.
     * @param body El código (expresión) que se ejecutará cuando se invoque la función.
     * @param env El entorno donde la función fue creada (los valores de las variables en ese momento).
     */  
    public Function(List<String> params, Expression body, Environment env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    /**
     * Aplica (ejecuta) la función con los argumentos dados.
     * @param args Los valores con los que se va a ejecutar la función.
     * @return El resultado de ejecutar la función con esos argumentos.
     * @throws RuntimeException Si el número de argumentos no coincide con los parámetros de la función.
     */    
    public Object apply(List<Object> args) {
        if (args.size() != params.size()) {
            throw new RuntimeException("Se esperaban " + params.size() + 
                                     " argumentos, pero se recibieron " + args.size());
        }

        // Crea un nuevo entorno con los valores de los argumentos para esta ejecución        
        Environment newEnv = env.extend(params, args);
        
        // Ejecuta el cuerpo de la función con el nuevo entorno y devuelve el resultado        
        return body.evaluate(newEnv);
    }
}