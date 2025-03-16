package uvg;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase principal que contiene el método main para ejecutar el programa.
 */
public class MainPokemon {
    /**
     * Método principal para ejecutar el programa.
     * Permite seleccionar el tipo de Map y realizar consultas sobre Pokémon.
     * @param args Argumentos de la línea de comandos.
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el tipo de Mapa (HashMap, TreeMap, LinkedHashMap):");
        String mapType = scanner.nextLine();

        PokemonManager manager = new PokemonManager(mapType);
        manager.loadFromCSV("pokemon-dataset.csv");
        
        System.out.println("Ingrese un nombre de Pokémon a buscar:");
        String name = scanner.nextLine();
        System.out.println(manager.getPokemon(name));
        scanner.close();
    }
}
