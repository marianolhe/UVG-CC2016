file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/PostfixCalculatorImpl.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/PostfixCalculatorImpl.java
text:
```scala
package uvg;

public class PostfixCalculatorImpl implements ICalculadora {
    private IStack<Integer> stack;

    //Constructor que inicializa la pila
    public PostfixCalculatorImpl(IStack<Integer> stack) {
        if (stack == null) {
            throw new IllegalArgumentException("La pila no puede ser null");
        }
        this.stack = stack;
    }

    @Override
    public int evaluate(String expression) throws PostfixCalculatorException {
        if (expression == null) {
            throw new PostfixCalculatorException("La expresión no puede ser null", PostfixCalculatorException.ERROR_EXPRESION_NULA);
        }
        
        stack.clear(); // Limpiar la pila para una nueva evaluación
        String[] tokens = expression.trim().split("\\s+");  // Dividir la expresión en tokens
        
        for (String token : tokens) {
            try {
                if (isOperator(token)) {
                     // Asegurar que haya suficientes operandos en la pila
                    if (stack.isEmpty()) {
                        throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                    }
                    int b = stack.pop();
                    if (stack.isEmpty()) {
                        throw new PostfixCalculatorException("Operandos insuficientes", PostfixCalculatorException.ERROR_OPERANDOS_INSUFICIENTES);
                    }
                    int a = stack.pop();
                    stack.push(executeOperation(a, b, token)); // Ejecutar la operación y apilar el resultado
                } else {
                    stack.push(Integer.parseInt(token)); // Apilar el número en la pila
                }
            } catch (NumberFormatException e) {
                throw new PostfixCalculatorException("Carácter inválido: " + token, PostfixCalculatorException.ERROR_CARACTER_INVALIDO);
            }
        }
        
        // Verificación del resultado final
        if (stack.isEmpty()) {
            throw new PostfixCalculatorException("Expresión vacía", PostfixCalculatorException.ERROR_EXPRESION_VACIA);
        }
        
        int result = stack.pop();
        if (!stack.isEmpty()) {
            throw new PostfixCalculatorException("Expresión mal formada", PostfixCalculatorException.ERROR_EXPRESION_MALFORMADA);
        }
        
        return result; // Retornar el resultado final
    
    }

    // Verificar si el token es un operador
    private boolean isOperator(String token) {
        return token.matches("[+\\-*/%]");
    }

    // Verificar si el token es un operador
    private int executeOperation(int a, int b, String operator) throws PostfixCalculatorException {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) {
                    throw new PostfixCalculatorException("División por cero", PostfixCalculatorException.ERROR_DIVISION_CERO);
                }
                return a / b;
            case "%":
                if (b == 0) {
                    throw new PostfixCalculatorException("Módulo por cero", PostfixCalculatorException.ERROR_MODULO_CERO);
                }
                return a % b;
            default:
                throw new PostfixCalculatorException("Operador inválido: " + operator, PostfixCalculatorException.ERROR_OPERADOR_INVALIDO);
        }
    }
}

```



#### Error stacktrace:

```
scala.collection.Iterator$$anon$19.next(Iterator.scala:973)
	scala.collection.Iterator$$anon$19.next(Iterator.scala:971)
	scala.collection.mutable.MutationTracker$CheckedIterator.next(MutationTracker.scala:76)
	scala.collection.IterableOps.head(Iterable.scala:222)
	scala.collection.IterableOps.head$(Iterable.scala:222)
	scala.collection.AbstractIterable.head(Iterable.scala:935)
	dotty.tools.dotc.interactive.InteractiveDriver.run(InteractiveDriver.scala:164)
	dotty.tools.pc.MetalsDriver.run(MetalsDriver.scala:45)
	dotty.tools.pc.WithCompilationUnit.<init>(WithCompilationUnit.scala:31)
	dotty.tools.pc.SimpleCollector.<init>(PcCollector.scala:345)
	dotty.tools.pc.PcSemanticTokensProvider$Collector$.<init>(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector$lzyINIT1(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.Collector(PcSemanticTokensProvider.scala:63)
	dotty.tools.pc.PcSemanticTokensProvider.provide(PcSemanticTokensProvider.scala:88)
	dotty.tools.pc.ScalaPresentationCompiler.semanticTokens$$anonfun$1(ScalaPresentationCompiler.scala:109)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator