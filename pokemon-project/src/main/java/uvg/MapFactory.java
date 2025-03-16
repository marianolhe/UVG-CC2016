package uvg;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Clase fábrica que devuelve distintos tipos de Map según se requiera.
 */
class MapFactory {
    /**
     * Método que genera un Map según el tipo especificado.
     * @param type Tipo de Map a utilizar ("TreeMap", "LinkedHashMap" o "HashMap").
     * @return Un Map del tipo especificado.
     */
    public static Map<String, Pokemon> getMap(String type) {
        switch (type) {
            case "TreeMap": return new TreeMap<>();
            case "LinkedHashMap": return new LinkedHashMap<>();
            default: return new HashMap<>();
        }
    }
}