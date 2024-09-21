public class App {
    public static void main(String[] args) throws Exception {
        SimpleDiceGame game = new SimpleDiceGame();
        game.takeTurn();
        game.printWinners();
        game.close();
    }
}
