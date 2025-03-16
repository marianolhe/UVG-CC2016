package uvg;

import java.util.List;

/**
 * Representa una función definida por el usuario.
 */
public class Function {
    private final List<String> params;
    private final Expression body;
    private final Environment env;

    public Function(List<String> params, Expression body, Environment env) {
        this.params = params;
        this.body = body;
        this.env = env;
    }

    public Object apply(List<Object> args) {
        if (args.size() != params.size()) {
            throw new RuntimeException("Se esperaban " + params.size() + 
                                     " argumentos, pero se recibieron " + args.size());
        }
        
        Environment newEnv = env.extend(params, args);
        return body.evaluate(newEnv);
    }
}