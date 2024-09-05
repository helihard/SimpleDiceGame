import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static ArrayList<Player> initialize(Scanner scanner) {
        ArrayList<Player> players = new ArrayList<>();
        int numberOfPlayers, numberOfDice, numberOfSides;

        System.out.println("Hur många spelare vill spela?");
        numberOfPlayers = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Hur många tärningar ska varje spelare ha?");
        numberOfDice = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("Hur många sidor ska varje tärning ha?");
        numberOfSides = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName;
            System.out.println("Ange namn på spelare " + (i + 1) + ":");
            playerName = scanner.nextLine().trim();
            Player player = new Player(playerName);
            players.add(player);
        }

        for (Player player : players) {
            for (int i = 0; i < numberOfDice; i++) {
                player.addDie(numberOfSides);
            }
        }
        return players;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        initialize(scanner);
    }
}
