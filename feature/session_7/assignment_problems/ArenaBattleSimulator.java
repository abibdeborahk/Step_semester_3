interface Attackable {

    String attack();

    String attack(String weaponName);
}

interface Defendable {
    String defend();
}

abstract class GameCharacter {

    private static int counter = 1000;
    private final String characterId;

    public GameCharacter() {
        counter++;
        characterId = "CHAR-" + counter;
    }

    public String getCharacterId() {
        return characterId;
    }

    public abstract String getSpecialMove();
}

class Warrior extends GameCharacter implements Attackable, Defendable {

    String name;

    public Warrior(String name) {
        this.name = name;
    }

    @Override
    public String attack() {
        return name + " strikes with a blade";
    }

    // Method Overloading
    @Override
    public String attack(String weaponName) {
        return name + " strikes with an " + weaponName;
    }

    @Override
    public String defend() {
        return name + " raises a shield";
    }

    @Override
    public String getSpecialMove() {
        return name + " unleashes Whirlwind Slash";
    }
}

class Trap implements Defendable {

    String trapType;

    public Trap(String trapType) {
        this.trapType = trapType;
    }

    @Override
    public String defend() {
        return trapType + " triggers automatically";
    }
}

public class ArenaBattleSimulator {

    static void resolveDefense(Defendable[] combatants) {

        for (Defendable c : combatants) {
            System.out.println(c.defend());
        }
    }

    public static void main(String[] args) {

        Warrior w = new Warrior("Kael");

        System.out.println(w.attack());

        System.out.println(w.attack("Iron Sword"));

        System.out.println(w.defend());

        System.out.println(w.getSpecialMove());

        System.out.println("Character ID: " + w.getCharacterId());

        Trap t = new Trap("Spike Pit");

        System.out.println("\n" + t.defend());

        // Mixed array using interface reference
        Defendable[] combatants = {w, t};

        System.out.println("\nResolving Defense:");

        resolveDefense(combatants);

    
    }
}