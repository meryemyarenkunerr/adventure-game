import Armors.Armor;
import Weapons.Weapon;

public class ToolStore extends SafeLocation {
    public ToolStore(Player player) {
        super(player, 2, "Tool Store");
    }

    @Override
    public boolean onLocation() {

        System.out.println("You are in the Tool Store. You can buy everything you need, if you have enough money.");
        boolean menu = true;
        while (menu)
        {
            System.out.println("Menu : ");
            System.out.println("1. Weapons\n2. Armors\n3. Quit");
            System.out.print("Enter a valid number from the menu : ");
            int select = sc.nextInt();
            while (select < 1 || select > 3)
            {
                System.out.println("Please select a valid number from menu!");
                select = sc.nextInt();
            }
            switch (select)
            {
                case 1:
                    printWeapons();
                    break;
                case 2:
                    printArmors();
                    break;
                case 3:
                    System.out.println("Do not forget to come more money next time!");
                    menu = false;
                    break;
            }
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        return true;
    }

    public void printWeapons()
    {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Weapons : ");
        for (Weapon w : Weapon.weapons()) {
            System.out.printf("ID : %d\t\t| Name : %s\t\t| Damage : %d\t\t| Price : %d\n", w.getId(), w.getName(), w.getDamage(), w.getPrice());
        }
        System.out.println("0. Exit");
        buyWeapon();
    }

    public void buyWeapon()
    {
        System.out.print("Enter the weapon ID : ");
        int weaponId = sc.nextInt();
        while (weaponId < 0 || weaponId > Weapon.weapons().length)
        {
            System.out.println("Please select a valid number from menu!");
            weaponId = sc.nextInt();
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        Weapon selectedWeapon = null;
        for (Weapon w : Weapon.weapons()) {
            if (w.getId() == weaponId) {
                selectedWeapon = w;
            }
        }
        if (selectedWeapon != null)
        {
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney())
                System.out.println("You do not have enough money to buy this weapon.");
            else
            {
                System.out.println("You have enough money to buy this weapon.");
                System.out.println("The weapon is bought..");
                int totalMoney = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(totalMoney);
                System.out.println("Your money : " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
                this.getPlayer().printfPlayerInfo();
            }
        }
        else {
            System.out.println("You are directing to the main menu.");
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }

    public void printArmors()
    {
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Armors : ");
        for (Armor a : Armor.armors()) {
            System.out.printf("ID : %d\t\t| Name : %s\t\t| Blocking : %d\t\t| Price : %d\n", a.getId(), a.getName(), a.getBlocking(), a.getPrice());
        }
        System.out.println("0. Exit");
        buyArmor();
    }

    public void buyArmor()
    {
        System.out.print("Enter the armor ID : ");
        int armorId = sc.nextInt();
        while (armorId < 0 || armorId > Armor.armors().length)
        {
            System.out.println("Please select a valid number from menu!");
            armorId = sc.nextInt();
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        Armor selectedArmor = null;
        for (Armor a : Armor.armors()) {
            if (a.getId() == armorId) {
                selectedArmor = a;
            }
        }
        if (selectedArmor != null)
        {
            if (selectedArmor.getPrice() > this.getPlayer().getMoney())
                System.out.println("You do not have enough money to buy this armor.");
            else
            {
                System.out.println("You have enough money to buy this armor.");
                System.out.println("The armor is bought..");
                int totalMoney = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(totalMoney);
                System.out.println("Your money : " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setArmor(selectedArmor);
                this.getPlayer().printfPlayerInfo();
            }
        }
        else {
            System.out.println("You are directing to the main menu.");
            System.out.println("-----------------------------------------------------------------------------------------------");
        }
    }
}
