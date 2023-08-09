import Monsters.Zombie;

public class Cave extends BattleLocation{
    public Cave(Player player, int countMonster) {
        super(player, 3, "Cave", new Zombie(), "Food", countMonster);
    }
}
