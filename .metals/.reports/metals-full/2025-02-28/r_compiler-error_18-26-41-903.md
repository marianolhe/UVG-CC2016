file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/PostfixCalculatorException.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
uri: file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/PostfixCalculatorException.java
text:
```scala
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