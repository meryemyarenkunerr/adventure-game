package Armors;

public class Armor {
    private int id;
    private int blocking;
    private int price;
    private String name;

    public Armor(int id, int blocking, int price, String name) {
        this.setId(id);
        this.setBlocking(blocking);
        this.setPrice(price);
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBlocking() {
        return blocking;
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Armor[] armors()
    {
        return new Armor[]{new Light(), new Medium(), new Heavy()};
    }
}
