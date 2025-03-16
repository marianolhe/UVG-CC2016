package uvg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Clase principal que maneja la gestión de Pokémon utilizando diferentes estructuras de datos.
 */
public class PokemonManager {
    private Map<String, Pokemon> pokemons;

    /**
     * Constructor que inicializa el mapa de Pokémon.
     * Se usa la interfaz Map para permitir flexibilidad en la implementación.
     * Dependiendo del tipo de mapa elegido:
     *  - HashMap: Inserción y búsqueda rápidas (O(1)), pero sin orden.
     *  - TreeMap: Ordenado automáticamente por clave (O(log n)), pero más lento.
     *  - LinkedHashMap: Mantiene orden de inserción con velocidad similar a HashMap.
     * La elección de la estructura se basa en el uso requerido, priorizando eficiencia.
     * @param mapType Tipo de Mapa a utilizar ("HashMap", "TreeMap", "LinkedHashMap").
     */
    public PokemonManager(String mapType) {
        this.pokemons = MapFactory.getMap(mapType);
    }

    /**
     * Carga datos de Pokémon desde un archivo CSV.
     * Se asume que el archivo tiene formato válido: Nombre, Tipo1, Habilidad.
     * @param filename Nombre del archivo CSV con los datos.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public void loadFromCSV(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine(); // Saltar la cabecera
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 3) {
                pokemons.put(data[0], new Pokemon(data[0], data[1], data[2]));
            }
        }
        br.close();
    }
    
    /**
     * Agrega un nuevo Pokémon al mapa.
     * @param name Nombre del Pokémon.
     * @param type1 Tipo del Pokémon.
     * @param ability Habilidad especial del Pokémon.
     */
    public void addPokemon(String name, String type1, String ability) {
        pokemons.put(name, new Pokemon(name, type1, ability));
    }
    
    /**
     * Busca un Pokémon por su nombre.
     * Complejidad: O(1) en HashMap, O(log n) en TreeMap, O(1) en LinkedHashMap.
     * @param name Nombre del Pokémon a buscar.
     * @return El Pokémon encontrado o null si no existe.
     */
    public Pokemon getPokemon(String name) {
        return pokemons.get(name);
    }
    
    /**
     * Retorna una lista de Pokémon ordenados por su tipo.
     * Se usa una lista auxiliar para realizar el ordenamiento manualmente.
     * Complejidad: O(n log n) debido al uso de Collections.sort().
     * @return Lista de Pokémon ordenados por Tipo1.
     */
    public List<Pokemon> getPokemonsSortedByType() {
        List<Pokemon> list = new ArrayList<>(pokemons.values());
        list.sort(Comparator.comparing(Pokemon::getType1));
        return list;
    }
    
    /**
     * Busca Pokémon por habilidad.
     * Se hace una búsqueda lineal recorriendo la estructura actual.
     * Complejidad: O(n), ya que recorre toda la lista de Pokémon.
     * @param ability Habilidad a buscar.
     * @return Lista de Pokémon con la habilidad especificada.
     */
    public List<Pokemon> getPokemonsByAbility(String ability) {
        List<Pokemon> result = new ArrayList<>();
        for (Pokemon p : pokemons.values()) {
            if (p.getAbility().equalsIgnoreCase(ability)) {
                result.add(p);
            }
        }
        return result;
    }
}