import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        SimpleDiceGame game = new SimpleDiceGame(scanner);
        game.takeTurn();
        game.printWinners();
        scanner.close();
    }
}
