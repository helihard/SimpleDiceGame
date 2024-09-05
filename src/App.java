import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {

    // method for initialising simple dice game
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
        System.out.println("The game, Mrs Hudson, is on!");
        return players;
    }

    // method for game rounds
    private static void takeTurn(ArrayList<Player> players, Scanner scanner) {
        // change to 5 rounds after testing
        for (int i = 0; i < 5; i++) {
                System.out.println("-------------------");
                System.out.println("Spelomgång " + (i + 1) + "!");
                System.out.println("-------------------");
            for (Player player : players) {
                System.out.println(player.getName() + ", gissa på ett värde:");
                int guess = Integer.parseInt(scanner.nextLine().trim());
                //System.out.println("current guess: " + guess);
                player.rollDice();
                int dieValue = player.getDieValue();
                //System.out.println("current value: " + dieValue);
                if (guess == dieValue) {
                    player.increaseScore();
                    System.out.println("Värdet av ditt slag är " + dieValue + ". Du gissade rätt!");
                } else {
                    System.out.println("Värdet av ditt slag är " + dieValue + ". Du gissade fel!");
                }
            }
            /*System.out.println("Current score: ");
            for (Player player : players) {
                System.out.println(String.format("%s: %s points", player.getName(), player.getScore()));
            }*/
        }
    }

    // method for getting winner of game
    private static ArrayList<Player> getWinners(ArrayList<Player> players) {
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                return Integer.compare(p2.getScore(), p1.getScore());
            }       
        });

        int highestScore = players.get(0).getScore();
        ArrayList<Player> winners = new ArrayList<>();

        if (highestScore == 0) {
            System.out.println("Nobody won this game. Better luck next time!");
        } else {
            for (Player player : players) {
                if (player.getScore() == highestScore) {
                    winners.add(player);
                } else {
                    break;
                }
            }
            if (winners.size() == 1) {
                Player winner = winners.get(0);
                System.out.println(String.format("The winner takes it all! %s: %s points", winner.getName(), winner.getScore()));
            } else {
                System.out.println("We have several winners!");
                for (Player winner : winners) {
                    System.out.println(String.format("%s: %s points", winner.getName(), winner.getScore()));
                }
            } 
        }
        System.out.println("All scores:");
        for (Player player : players) {
            System.out.println(String.format("%s: %s points", player.getName(), player.getScore()));
        }
        return winners;
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Player> players = initialize(scanner);
        takeTurn(players, scanner);
        getWinners(players);
        scanner.close();
    }
}
