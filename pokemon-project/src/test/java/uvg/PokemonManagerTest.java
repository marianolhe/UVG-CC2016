package uvg; 
/**
 * Pruebas unitarias para la clase PokemonManager.
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class PokemonManagerTest {
    @Test
    void testAddPokemon() {
        PokemonManager manager = new PokemonManager("HashMap");
        manager.addPokemon("Pikachu", "Eléctrico", "Static");
        assertNotNull(manager.getPokemon("Pikachu"));
    }

    @Test
    void testGetPokemon() {
        PokemonManager manager = new PokemonManager("TreeMap");
        manager.addPokemon("Charizard", "Fuego", "Blaze");
        assertEquals("Charizard", manager.getPokemon("Charizard").getName());
    }

    @Test
    void testGetPokemonsSortedByType() {
        PokemonManager manager = new PokemonManager("LinkedHashMap");
        manager.addPokemon("Bulbasaur", "Planta", "Overgrow");
        manager.addPokemon("Squirtle", "Agua", "Torrent");
        List<Pokemon> sortedList = manager.getPokemonsSortedByType();
        assertEquals("Agua", sortedList.get(0).getType1());
    }
}
