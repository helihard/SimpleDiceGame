import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class App {

    // method for initialising simple dice game
    private static ArrayList<Player> initialize(Scanner scanner) {
        ArrayList<Player> players = new ArrayList<>();
        int maxNumberOfPlayers = 6;
        int maxNumberOfDice = 6;
        int maxNumberOfSides =  12;
        int numberOfPlayers = getValidNumberInput(scanner, String.format("How many players for this game? (Max: %s)", maxNumberOfPlayers), "players", maxNumberOfPlayers);
        int numberOfDice = getValidNumberInput(scanner, String.format("How many dice for each player? (Max: %s)", maxNumberOfDice), "dice per player", maxNumberOfDice);
        int numberOfSides = getValidNumberInput(scanner, String.format("How many sides of each die? (Max: %s)", maxNumberOfSides), "sides per die", maxNumberOfSides);

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = getValidPlayerName(scanner, i + 1);
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

    // helper method for initialize method
    // for getting valid number input (error handling)
    private static int getValidNumberInput(Scanner scanner, String prompt, String type, int max) {
        int number = -1;
        boolean isValidInput = false;

        while (!isValidInput) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            try {
                number = Integer.parseInt(input);
                if (number < 1) {
                    System.out.println(String.format("Invalid input. Please enter a number between 1 and %s for %s.", max, type));
                } else if (number > max) {
                    System.out.println(String.format("The max number of %s is %s. Please enter a number between 1 and %s.", type, max, max));
                } else {
                    isValidInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return number;
    }

    // helper method for initialize method
    // for getting valid name input (error handling) 
    private static String getValidPlayerName(Scanner scanner, int playerNumber) {
        String playerName = "";
        boolean isValidInput = false;
        
        while (!isValidInput) {
            System.out.println(String.format("Add name of player %s:", playerNumber));
            playerName = scanner.nextLine().trim();

            if (playerName.isEmpty()) {
                System.out.println("You need to provide a name. Please try again.");
            } else {
                isValidInput = true;
            }
        }
        return playerName;
    }

    // method for game rounds
    private static void takeTurn(ArrayList<Player> players, Scanner scanner) {
        for (int i = 0; i < 5; i++) {
                System.out.println("-------------------");
                System.out.println("Game round " + (i + 1) + "!");
                System.out.println("-------------------");
            for (Player player : players) {
                int guess = -1;
                boolean isValidInput = false;
                int minThrowValue = getMinThrowValue(player);
                int maxThrowValue = getMaxThrowValue(player);

                while (!isValidInput) {
                    System.out.println(String.format("%s, make a guess about the value of your roll (between %s and %s):", player.getName(), minThrowValue, maxThrowValue));
                    String input = scanner.nextLine().trim();

                    if (!input.isEmpty()) {
                        try {
                            guess = Integer.parseInt(input);

                            if (guess < minThrowValue) {
                                System.out.println(String.format("The smallest possible value of your throw is %s. Please make another guess.", minThrowValue));
                            } else if (guess > maxThrowValue) {
                                System.out.println(String.format("The largest possible value of your throw is %s. Please make another guess.", maxThrowValue));
                            } else {
                                isValidInput = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number.");
                        }
                    } else {
                        System.out.println("Input cannot be empty. Please enter a valid number.");
                    }
                }
                player.rollDice();
                int dieValue = player.getDieValue();
                
                if (guess == dieValue) {
                    player.increaseScore();
                    System.out.println(String.format("The value of your throw is %s. Your guess was correct!", dieValue));
                } else {
                    System.out.println(String.format("The value of your throw is %s. Your guess was incorrect!", dieValue));
                }
            }

            if (i < 4) {
                System.out.println("Current score: ");
                for (Player player : players) {
                    System.out.println(String.format("%s: %s points", player.getName(), player.getScore()));
                }
            }
        }
    }

    // helper method for takeTurn() 
    public static int getMaxThrowValue(Player player) {
        int maxValue = 0;
        ArrayList<Die> dice = player.getDice();

        for (Die die : dice) {
            int numberOfSides = die.getSides();
            maxValue += numberOfSides;
        }
        return maxValue;
    }

    // helper method for takeTurn()
    public static int getMinThrowValue(Player player) {
        return player.getDice().size();
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
                System.out.println("We have a tie!");
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
