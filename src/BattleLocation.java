import Armors.Heavy;
import Armors.Light;
import Armors.Medium;
import Monsters.Monster;
import Weapons.Gun;
import Weapons.Rifle;
import Weapons.Sword;

import java.util.Random;

public class BattleLocation extends Location{
    private final Random random = new Random();
    private Monster monster;
    private String award;
    private int countMonster;
    public BattleLocation(Player player, int id, String name, Monster monster, String award, int countMonster) {
        super(player, id, name);
        setMonster(monster);
        setAward(award);
        setCountMonster(countMonster);
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getCountMonster() {
        return countMonster;
    }

    public void setCountMonster(int countMonster) {
        this.countMonster = countMonster;
    }

    @Override
    boolean onLocation() {
        if (this.getName().equals("River") && this.getPlayer().getInventory().isWater())
        {
            System.out.println("You already have the award of the River, you cannot come back here!");
            System.out.println("-----------------------------------------------------------------------------------------------");
            return true;
        } else if (this.getName().equals("Forest") && this.getPlayer().getInventory().isWood()) {
            System.out.println("You already have the award of the Forest, you cannot come back here!");
            System.out.println("-----------------------------------------------------------------------------------------------");
            return true;
        } else if (this.getName().equals("Cave") && this.getPlayer().getInventory().isFood()) {
            System.out.println("You already have the award of the Cave, you cannot come back here!");
            System.out.println("-----------------------------------------------------------------------------------------------");
            return true;
        }

        if (!this.getName().equals("Mine"))
            System.out.println("You are in the " + this.getName() + ". Be careful, there can be a war here, but you can achieve " + this.getAward() + ".");
        else
            System.out.println("You are in the " + this.getName() + ". Be careful, there can be a war here, but in the Mine you can get some money, an Armor or a Weapon.");

        System.out.printf("There are %d %ss here.\n", getCountMonster(), getMonster().getName());
        getPlayer().printfPlayerInfo();
        while (true)
        {
            System.out.printf("Do you want to fight against to the %s ? (y/n) : ", getMonster().getName());
            String select = sc.nextLine();
            if (select.equals("y"))
            {
                System.out.println("There we go again, we start!");
                System.out.println("-----------------------------------------------------------------------------------------------");
                if (fight(countMonster))
                {
                    whereAreYou(this.getName());
                    this.getPlayer().printfPlayerInfo();
                    return true;
                }
                return false;
            }
            else if (select.equals("n"))
            {
                System.out.println("You are right, there are too many monster here. It is good to us to run away.");
                System.out.println("-----------------------------------------------------------------------------------------------");
                return true;
            }
            else
                System.out.println("You selected an invalid operation. Please select a valid operation from the menu.");

            if (this.getPlayer().getHealth() < 0)
                return false;
        }
    }

    public boolean fight(int countMonster)
    {
        if (this.getName().equals("Mine"))
        {
            int randomDamage = (int)Math.floor(Math.random() * (6 - 3 + 1) + 3);
            this.getMonster().setDamage(randomDamage);
        }
        for (int i = 1; i <= countMonster; i++)
        {
            playerInformation();
            monsterInformation(i);
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0)
            {
                System.out.print("Do you want to continue to fight ? (y/n) : ");
                String select = sc.nextLine().toLowerCase();
                System.out.println();
                if (select.equals("y"))
                {
                    if (startFirst())
                    {
                        System.out.println("You hit the monster!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getDamage());
                        afterHit(i);
                        if (this.getMonster().getHealth() > 0)
                        {
                            System.out.println("The monster hits you!");
                            int totalDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlocking();
                            if (totalDamage < 0)
                                totalDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - totalDamage);
                            afterHit(i);
                        }
                    } else
                    {
                        if (this.getMonster().getHealth() > 0)
                        {
                            System.out.println("The monster hits you!");
                            int totalDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlocking();
                            if (totalDamage < 0)
                                totalDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - totalDamage);
                            afterHit(i);
                        }
                        System.out.println("You hit the monster!");
                        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getDamage());
                        afterHit(i);
                    }
                }
            }
            if (this.getMonster().getHealth() == 0)
            {
                System.out.printf("You defeat the %d. monster!\n", i);
                System.out.println("-----------------------------------------------------------------------------------------------");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getMoney());
            }
            else
            {
                System.out.println("You are death!");
                return false;
            }
        }
        return true;
    }

    public void playerInformation()
    {
        System.out.println("Player's Information");
        System.out.printf("Total Damage : %d\t\t| Health : %d\t\t| Blocking : %d\t\t| Money : %d\n",
                this.getPlayer().getDamage(), this.getPlayer().getHealth(), this.getPlayer().getBlocking(), this.getPlayer().getMoney());
        System.out.println();
    }

    public void monsterInformation(int index)
    {
        System.out.println();
        System.out.printf("%d. Monster's Information\n", index);
        System.out.printf("Damage : %d\t\t| Health : %d\t\t| Money : %d\n", this.getMonster().getDamage(), this.getMonster().getHealth(), this.getMonster().getMoney());
        System.out.println();
    }

    public void afterHit(int index)
    {
        System.out.printf("Your health : %d\n%d. monster's health : %d\n", this.getPlayer().getHealth(), index, this.getMonster().getHealth());
        System.out.println();
    }

    public void whereAreYou(String locationName)
    {
        switch (locationName) {
            case "Cave":
                System.out.println("You defeat all monster in the Cave!");
                System.out.println("We gave you an award, it is Food");
                this.getPlayer().getInventory().setFood(true);
                break;
            case "River":
                System.out.println("You defeat all monster in the River!");
                System.out.println("We gave you an award, it is Water");
                this.getPlayer().getInventory().setWater(true);
                break;
            case "Forest":
                System.out.println("You defeat all monster in the Forest!");
                System.out.println("We gave you an award, it is Wood");
                this.getPlayer().getInventory().setWood(true);
                break;
            default:
                System.out.println("You defeat all monster in the Mine!");
                int minesAward = random.nextInt(100);
                getMinesAward(minesAward);
                break;
        }
    }

    public boolean startFirst()
    {
        Random random = new Random();
        return random.nextBoolean();
    }

    public void getMinesAward(int awardIndex)
    {
        int whichAward = random.nextInt(100);
        if (awardIndex < 15) {
            System.out.println("You won a weapon!");
            if (whichAward < 20) {
                System.out.println("You won a Rifle!");
                System.out.printf("You have the %s now, do you want to change it to a rifle ? (y/n) : ", this.getPlayer().getInventory().getWeapon().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your weapon changed...");
                    this.getPlayer().getInventory().setWeapon(new Rifle());
                } else
                    System.out.println("Good luck with your own weapon..");
            } else if (whichAward < 50) {
                System.out.println("You won a Sword!");
                System.out.printf("You have the %s now, do you want to change it to a sword ? (y/n) : ", this.getPlayer().getInventory().getWeapon().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your weapon changed...");
                    this.getPlayer().getInventory().setWeapon(new Sword());
                } else
                    System.out.println("Good luck with your own weapon..");
            } else {
                System.out.println("You won a Gun!");
                System.out.printf("You have the %s now, do you want to change it to a gun ? (y/n) : ", this.getPlayer().getInventory().getWeapon().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your weapon changed...");
                    this.getPlayer().getInventory().setWeapon(new Gun());
                } else
                    System.out.println("Good luck with your own weapon..");
            }
        } else if (awardIndex < 30) {
            System.out.println("You won an armor!");
            if (whichAward < 20) {
                System.out.println("You won a Heavy Armor!");
                System.out.printf("You have the %s now, do you want to change it to a heavy armor ? (y/n) : ", this.getPlayer().getInventory().getArmor().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your armor changed...");
                    this.getPlayer().getInventory().setArmor(new Heavy());
                } else
                    System.out.println("Good luck with your own armor..");
            } else if (whichAward < 50) {
                System.out.println("You won a Medium Armor!");
                System.out.printf("You have the %s now, do you want to change it to a medium armor ? (y/n) : ", this.getPlayer().getInventory().getArmor().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your armor changed...");
                    this.getPlayer().getInventory().setArmor(new Medium());
                } else
                    System.out.println("Good luck with your own armor..");
            } else {
                System.out.println("You won a Light Armor!");
                System.out.printf("You have the %s now, do you want to change it to a light armor ? (y/n) : ", this.getPlayer().getInventory().getArmor().getName());
                String selectWeapon = sc.nextLine().toLowerCase();
                if (selectWeapon.equals("y")) {
                    System.out.println("Your armor changed...");
                    this.getPlayer().getInventory().setArmor(new Light());
                } else
                    System.out.println("Good luck with your own armor..");
            }
        } else if (awardIndex < 55) {
            System.out.println("You won some money!");
            if (whichAward < 20) {
                System.out.println("You won 10 money!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            } else if (whichAward < 50) {
                System.out.println("You won 5 money!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            } else {
                System.out.println("You won 1 money!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        } else {
            System.out.println("You won nothing! Try again!");
        }
    }
}
