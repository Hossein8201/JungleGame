/**
 * <b>Main class</b>
 * In this class, We start the program and main body of program is here
 * @author Hossein Tatar
 * @since 2024-12-5
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Jungle Game! :) \n");
        System.out.println("In this game, each player starts with 30 random cards representing various animals. ");
        System.out.println("Each animal has unique attributes such as normal hit, strong hit, energy, and life.");
        System.out.println("Players choose 20 cards and play with a hand of 10 cards. During each turn, ");
        System.out.println("players can either attack an opponent's animal or recharge the energy of one of their own.");
        System.out.println("The ' GOAL ' is to strategically use your animals to defeat your opponent by depleting their animal's life.");
        System.out.println("Plan your attacks carefully and manage your energy wisely to win the game.");
        System.out.println("Let the best strategist prevail!!!!\n");
        GameSystem console = new GameSystem();
        console.startGame();
    }
}