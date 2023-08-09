import Monsters.Vampire;

public class Forest extends BattleLocation{
    public Forest(Player player, int countMonster) {
        super(player, 4, "Forest", new Vampire(), "Wood", countMonster);
    }
}
