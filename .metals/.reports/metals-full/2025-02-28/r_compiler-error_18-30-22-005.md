file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/infixtopostfix/src/main/java/uvg/VectorStack.java
### java.util.NoSuchElementException: next on empty iterator

occurred in the presentation compiler.

presentation compiler configuration:


action parameters:
offset: 444
uri: file:///C:/Users/maria/OneDrive/Documentos/ALG/UVG-CC2016/infixtopostfix/src/main/java/uvg/VectorStack.java
text:
```scala
//Código exactamente igual al de la hoja de trabajo 2; Calculator Project en el repositorio
// VectorStack.java
package uvg;
import java.util.Vector;

/**
 * Implementación de la interface IStack usando Vector.
 * @param <T> tipo de dato a almacenar en la pila
 */
public class VectorStack<T> implements IStack<T> {
    private Vector<T> vector;
    
    /**
     * Constructor que inicializa una nueva pila vacía.
     */
    pu@@blic VectorStack() {
        vector = new Vector<>();
    }
    
    @Override
    public void push(T item) {
        if (item == null) {
            throw new IllegalArgumentException("No se puede insertar un elemento null");
        }
        vector.add(item); // Add the item to the top of the stack
    }
    
    @Override
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return vector.remove(vector.size() - 1); // Remove and return the top item
    }
    
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return vector.get(vector.size() - 1); // Return the top item without removing it
    }
    
    @Override
    public boolean isEmpty() {
        return vector.isEmpty(); // Check if the stack is empty
    }
    
    @Override
    public void clear() {
        vector.clear(); // Remove all items from the stack
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
	dotty.tools.pc.HoverProvider$.hover(HoverProvider.scala:40)
	dotty.tools.pc.ScalaPresentationCompiler.hover$$anonfun$1(ScalaPresentationCompiler.scala:376)
```
#### Short summary: 

java.util.NoSuchElementException: next on empty iterator