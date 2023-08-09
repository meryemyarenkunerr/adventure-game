import Monsters.Bear;

public class River extends BattleLocation{
    public River(Player player, int countMonster) {
        super(player, 5, "River", new Bear(), "Water", countMonster);
    }
}
