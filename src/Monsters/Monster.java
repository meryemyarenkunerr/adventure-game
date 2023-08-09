package Monsters;

public abstract class Monster {
    private int id;
    private int damage;
    private int health;
    private int money;
    private int originalHealth;
    private String name;

    public Monster(int id, int damage, int health, int money, String name) {
        setId(id);
        setDamage(damage);
        setHealth(health);
        setMoney(money);
        setOriginalHealth(health);
        setName(name);
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0)
            health = 0;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }
}

