import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {

    // method for initialising simple dice game
    private static ArrayList<Player> initialize(Scanner scanner) {
        ArrayList<Player> players = new ArrayList<>();
        int numberOfPlayers, numberOfDice, numberOfSides;

        System.out.println("How many players for this game?");
        numberOfPlayers = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("How many dice for each player?");
        numberOfDice = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("How many sides of each die?");
        numberOfSides = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName;
            System.out.println("Add name of player " + (i + 1) + ":");
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
        for (int i = 0; i < 5; i++) {
                System.out.println("-------------------");
                System.out.println("Game round " + (i + 1) + "!");
                System.out.println("-------------------");
            for (Player player : players) {
                System.out.println(player.getName() + ", make a guess about the value of your roll:");
                int guess = Integer.parseInt(scanner.nextLine().trim());
                player.rollDice();
                int dieValue = player.getDieValue();
                if (guess == dieValue) {
                    player.increaseScore();
                    System.out.println("The value of your throw is " + dieValue + ". Your guess was correct!");
                } else {
                    System.out.println("The value of your throw is " + dieValue + ". Your guess was incorrect!");
                }
            }
            /*System.out.println("Current score: ");
            for (Player player : players) {
                System.out.println(String.format("%s: %s points", player.getName(), player.getScore()));
            }*/
        }
    }

    // method for getting winner(s) of game
    private static ArrayList<Player> getWinners(ArrayList<Player> players) {
        Collections.sort(players, new PlayerComparator());
        return players;
    }

    // printer method for getWinners
    private static void printWinners(ArrayList<Player> players) {
        int highestScore = players.get(0).getScore();
        ArrayList<Player> winners = new ArrayList<>();
        System.out.println("-------------------");
        System.out.println("Game over!");
        System.out.println("-------------------");
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
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        
        ArrayList<Player> players = initialize(scanner);
        takeTurn(players, scanner);
        printWinners(getWinners(players));
        scanner.close();
    }
}
