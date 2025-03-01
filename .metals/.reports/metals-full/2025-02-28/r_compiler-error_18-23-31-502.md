file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/IStack.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 675
uri: file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/calculator-project/src/main/java/uvg/IStack.java
text:
```scala
package uvg;

import java.lang.IllegalArgumentException;

// IStack.java
/**
 * Interface genérica para implementar una estructura de datos tipo Pila (Stack).
 * @param <T> tipo de dato a almacenar en la pila
 */



public interface IStack<T> {


/**
 * Añade un elemento al tope de la pila.
 * @param item elemento a añadir
 * @throws IllegalArgumentException si el elemento es null
 */
void push(T item);

/** 
 * Remueve y retorna el elemento del tope de la pila.
 * @return el elemento del tope de la pila
 * @throws IllegalStateException si la pila está vacía
 */
T pop();

/**
 * Retorna el elemento del tope sin removerlo.
 * @return el @@elemento del tope de la pila
 * @throws IllegalStateException si la pila está vacía
 **/
T peek();

/**
 * Verifica si la pila está vacía.
 * @return true si la pila está vacía, false en caso contrario
 */

boolean isEmpty();

/**

Elimina todos los elementos de la pila.
*/
void clear();
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
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:376)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator