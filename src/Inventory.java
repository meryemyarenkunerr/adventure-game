import Armors.Armor;
import Weapons.Weapon;

public class Inventory {
    private Weapon weapon;
    private Armor armor;
    private boolean food;
    private boolean water;
    private boolean wood;
    public Inventory() {
        this.weapon = new Weapon(0, 0, 0, "Punch");
        this.armor = new Armor(0, 0, 0, "Nothing");
        this.setWood(false);
        this.setWater(false);
        this.setFood(false);
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isWood() {
        return wood;
    }

    public void setWood(boolean wood) {
        this.wood = wood;
    }
}
