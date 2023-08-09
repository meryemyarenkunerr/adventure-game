import java.util.Random;
import java.util.Scanner;

public class Game {
    protected Scanner sc = new Scanner(System.in);

    public void start()
    {
        System.out.println("Welcome to the Adventure Game ! ");
        System.out.print("Please enter your name : ");
        String name = sc.nextLine();
        Player player = new Player(name);
        System.out.println("Welcome to the our Adventure Game " + player.getPlayerName() + ".");
        player.selectCharacter();
        Location location = null;
        System.out.println("You are in the House now. In the below, there are some places where you will be able to go.");
        System.out.println("Where do you want to go now?");
        while (true)
        {
            Location[] locationList = {new House(player), new ToolStore(player), new Cave(player, 0), new Forest(player, 0), new River(player, 0), new Mine(player , 0)};
            System.out.println("Locations : ");
            for (Location l: locationList) {
                System.out.printf("ID : %d\t\t| Name : %s\n", l.getId(), l.getName());
            }
            System.out.println("0. Exit");
            System.out.print("Enter the location ID : ");
            int locationId = sc.nextInt();
            while (locationId < 0 || locationId > locationList.length) {
                System.out.println("Please enter a valid location ID!");
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.print("Enter the location ID : ");
                locationId = sc.nextInt();
            }
            switch (locationId)
            {
                case 1:
                    System.out.println("You selected to go to the House...");
                    location = new House(player);
                    break;
                case 2:
                    System.out.println("You selected to go to the Tool Store...");
                    location = new ToolStore(player);
                    break;
                case 3:
                    System.out.println("You selected to go to the Cave...");
                    location = new Cave(player, randomMonsterNumber());
                    break;
                case 4:
                    System.out.println("You selected to go to the Forest...");
                    location = new Forest(player, randomMonsterNumber());
                    break;
                case 5:
                    System.out.println("You selected to go to the River...");
                    location = new River(player, randomMonsterNumber());
                    break;
                case 6:
                    System.out.println("You selected to go to the Mine...");
                    location = new Mine(player, randomMonsterNumber());
                    break;
            }
            if (locationId == 0)
            {
                System.out.println("We will be waiting for you!");
                break;
            }
            System.out.println("-----------------------------------------------------------------------------------------------");
            if (!location.onLocation())
            {
                System.out.println("Game Over!");
                break;
            }
            if (player.getInventory().isWater() && player.getInventory().isWood() && player.getInventory().isFood())
            {
                System.out.println("You won this game!");
                break;
            }
        }
    }

    public int randomMonsterNumber()
    {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }
}
