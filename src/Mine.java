import Monsters.Snake;

public class Mine extends BattleLocation{
    public Mine(Player player, int countMonster) {
        super(player, 6, "Mine", new Snake(), "", countMonster);
    }
}
