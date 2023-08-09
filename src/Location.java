import java.util.Scanner;

public abstract class Location {
    protected Scanner sc = new Scanner(System.in);
    private Player player;
    private int id;
    private String name;

    public Location(Player player, int id, String name) {
        this.setPlayer(player);
        this.setId(id);
        this.setName(name);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract boolean onLocation();
}
