/**
 * Clase que representa un Pokémon con atributos básicos.
 */
class Pokemon {
    private String name;
    private String type1;
    private String ability;
    
    /**
     * Constructor de la clase Pokémon.
     * @param name Nombre del Pokémon.
     * @param type1 Tipo principal del Pokémon.
     * @param ability Habilidad especial del Pokémon.
     */
    public Pokemon(String name, String type1, String ability) {
        this.name = name;
        this.type1 = type1;
        this.ability = ability;
    }
    
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getAbility() { return ability; }
    
    @Override
    public String toString() {
        return name + " | Tipo: " + type1 + " | Habilidad: " + ability;
    }
}