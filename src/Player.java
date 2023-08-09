import Characters.Archery;
import Characters.GameCharacter;
import Characters.Knight;
import Characters.Samurai;

import java.util.Scanner;

public class Player {
    protected Scanner sc = new Scanner(System.in);
    private int damage;
    private int health;
    private int money;
    private int blocking;
    private int originalHealth;
    private String characterName;
    private String playerName;
    private Inventory inventory;

    public Player(String playerName)
    {
        this.setPlayerName(playerName);
        this.setInventory(new Inventory());
    }

    public int getDamage() {
        return damage + getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBlocking() {
        return blocking + getInventory().getArmor().getBlocking();
    }

    public void setBlocking(int blocking) {
        this.blocking = blocking;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }

    public void selectCharacter()
    {
        GameCharacter[] characterList = {new Samurai(), new Archery(), new Knight()};
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Characters : ");
        for (GameCharacter g: characterList) {
            System.out.printf("ID : %d\t\t| Name : %s \t\t| Damage : %d\t\t| Health : %d\t\t| Money : %d\n", g.getId(), g.getName(), g.getDamage(), g.getHealth(), g.getMoney());
        }
        System.out.print("Enter the character ID : ");
        int characterID = sc.nextInt();
        switch (characterID)
        {
            case 1:
                System.out.println("You selected Samurai, he is the most powerful warrior in the Japan Civilization.");
                initPlayer(new Samurai());
                break;
            case 2:
                System.out.println("You selected Archery. She has the sharpest eyes in the world!");
                initPlayer(new Archery());
                break;
            case 3:
                System.out.println("You selected Knight. He has the strongest will in the world!");
                initPlayer(new Knight());
                break;
            default:
                System.out.println("You entered invalid id, so we initialize default character as Samurai.");
                initPlayer(new Samurai());
                break;
        }
        printfPlayerInfo();
    }

    public void initPlayer(GameCharacter gameCharacter)
    {
        this.setDamage(gameCharacter.getDamage());
        this.setHealth(gameCharacter.getHealth());
        this.setCharacterName(gameCharacter.getName());
        this.setMoney(gameCharacter.getMoney());
        this.setOriginalHealth(gameCharacter.getHealth());
    }

    public void printfPlayerInfo()
    {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Player Information");
        System.out.printf("Name : %s\nCharacter Name : %s\nWeapon : %s\nTotal Damage : %d\nTotal Blocking : %d\nTotal Health : %d\nTotal Money : %d\n",
                getPlayerName(), getCharacterName(), getInventory().getWeapon().getName(), getDamage(), getBlocking(), getHealth(), getMoney());
        System.out.println("-----------------------------------------------------------------------------------------------");
    }
}
