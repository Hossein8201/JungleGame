import java.util.*;
/**
 * <h1>Game System class</h1>
 * This class handles the input parameter from outside and in another definition, it is <b>UI : user interface</b> of program.
 * @author Hossein Tatar
 * @since 2024-12-5
 * @version 1.0
 */
public class GameSystem {
    /** <b>players</b> is two object from Player class.*/
    private Player player1, player2;
    /** <b>scanner</b> is a final Scanner type and use for getting the input parameters.*/
    private final Scanner scanner = new Scanner(System.in);
    /**
     * constructor of Game System, and we use that as <b>default</b> just to create an object from this class.
     * the body of that is blank.
     * @return nothing
     */
    public GameSystem(){}
    /** Start method uses for start a new game by choosing user that or end the game.
     * @return nothing */
    public void startGame(){
            try {
                System.out.print("If you want to play new game, type 'start' to start a new game or type 'exit' to quit: ");
                String input = scanner.nextLine();
                while(input.isEmpty())
                    input = scanner.nextLine();
                if(input.equals("start"))       choosePlayer();
                else if(input.equals("exit"))        endGame();
                else throw new IllegalArgumentException("Invalid input... Please try again");
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
                startGame();
            }
    }
    /** This method asks from user want to play with another user or play with computer
     * @return nothing */
    public void choosePlayer(){
        try {
            System.out.println("Do you want to play with another player or want to play with Computer ? \n1) Player\t2) Computer");
            System.out.print("Your choice : ");     String choice = scanner.nextLine();
            if(choice.equals("1")){
                System.out.print("Enter player1 name: ");       String player1Name = scanner.nextLine();      player1 = new Player(player1Name);
                System.out.print("Enter player2 name: ");       String player2Name = scanner.nextLine();      player2 = new Player(player2Name);
            } else if  (choice.equals("2")){
                System.out.print("Enter player1 name: ");       String player1Name = scanner.nextLine();      player1 = new Player(player1Name);
                player2 = new Player("computer");
            } else throw new IllegalArgumentException("Invalid choice... Please try again");
            // Choose hands of two players and Start game processing :
            chooseHands(player1);
            if (choice.equals("1"))
                chooseHands(player2);
            else{
                player2.chooseTenCards();
                player2.addLogActivities("---> Log : player computer choose the game hand of own and is ready to play. \n");
                System.out.println(player2.playerLog);
            }
            gameProcess();
            //
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
            choosePlayer();
        }
    }
    /**
     * This method uses for choose a 10 card for hand of player
     * @param player is a Player type that should choose your own game hand
     * @return nothing
     */
    public void chooseHands(Player player) {
        System.out.println("In addition, Write the name of ' 10 ' cards you want to play those with spaces between them : ");
        System.out.println("for example : lion lion lion tiger tiger tiger hippo hippo boar boar ");
        System.out.println("*** Be notice you can choose a card if you have that in your hand ***");
        player.showHand(player.getPlayerName());
        while(true) {
            System.out.print("Write your chosen cards here -> ");
            String[] chosenCardNames = scanner.nextLine().split(" ");
            Set<Integer> chosenCards = new HashSet<>(player.getCardIndex(chosenCardNames));
            if (player.chooseTenCards(chosenCards)) {
                System.out.println("The game hand are ready to play :) \n");
                player.addLogActivities("---> Log : player %s choose the game hand of own and is ready to play. \n".formatted(player.getPlayerName()));
                System.out.println(player.playerLog);
                break;
            } else
                System.out.println("The game hand is not correct... Please try again.");
        }
    }
    /** It handles the processing of game and check the ending condition and manage turn of players and change that.
     * @return nothing */
    public void gameProcess() {
        System.out.println("Now, you can choose a or many animal card from your hand to attack with that to an opponent's animal card, or");
        System.out.println("you may choose to recharge the energy of a alive card but be notice you have ' 3 ' chance for that.");
        System.out.println("To show cards, use index of that and use '0' to 'end', ");
        System.out.println("for example : 1 8 6 0");
        System.out.println("and the last, choose a card of your opponent to attack that.\t");
        int turn = 1;
        while (player1.blankHand() && !player2.blankHand()) {
            player1.displayHand(player1.getPlayerName());
            player2.displayHand(player2.getPlayerName());
            player1.playerLog = new StringBuilder("---> Log : ");
            player2.playerLog = new StringBuilder("---> Log : ");
            if (turn == 1) {
                playerAction(player1, player2);
                System.out.println(player1.playerLog);
                turn = 2;
            } else if (!player2.getPlayerName().equals("computer")) {
                playerAction(player2, player1);
                System.out.println(player2.playerLog);
                turn = 1;
            } else {
                computerAction(player2, player1);
                System.out.println(player2.playerLog);
                turn = 1;
            }
        }
        if (player1.blankHand()) {
            player1.displayHand(player1.getPlayerName());
            System.out.printf("$$$$$$$ The winner is %s \n", player1.getPlayerName());
            startGame();
        } else {
            player2.displayHand(player2.getPlayerName());
            System.out.printf("$$$$$$$ The winner is %s \n", player2.getPlayerName());
            startGame();
        }
    }
    /**
     * This method uses for player action and make the actions between attack or recharge.
     * @param player is a Player object, and it is his turn to choose an action
     * @param opponent is a Player object that is another player
     * @return nothing
     */
    public void playerAction(Player player,Player opponent) {
        System.out.printf("Player ' %s ', Its your turn .... \n", player.getPlayerName());
        System.out.println("Choose an action:\t1) Attack\t2) Recharging Energy");
        System.out.print("Your action : ");    int action = scanner.nextInt();
        if (action == 1){
            player.addLogActivities("player %s choose attack action. \n".formatted(player.getPlayerName()));
            attackAction(player, opponent);
        }
        else{
            player.addLogActivities("player %s choose recharge action. \n".formatted(player.getPlayerName()));
            rechargeAction(player);
        }
    }
    /**
     * In this method, We implement the attack process for player in his own turn
     * @param player is a Player object, and it is his turn to choose an action
     * @param opponent is a Player object that is another player
     * @return nothing
     */
    public void attackAction(Player player, Player opponent) {
        HashMap<Integer, Set<Integer>> attackingCards = new HashMap<>();
        int damage;
        while (true) {
            Set<Integer> cardIndexes = new HashSet<>();
            for (int i = 1; i < 3; i++) {
                attackingCards.put(i, new HashSet<>());
                if (i == 1)     System.out.println("Type of Attack is ~~ Normal Hit ~~");
                else    System.out.println("Type of Attack is ~~ Strong Hit ~~");
                System.out.print("Choose your animal's index to attack 'end' with '0' -> ");
                int animalIndex = scanner.nextInt();
                while (animalIndex != 0) {
                    if (!cardIndexes.contains(animalIndex)) {
                        cardIndexes.add(animalIndex);
                        attackingCards.get(i).add(animalIndex);
                    }
                    animalIndex = scanner.nextInt();
                }
            }
            damage = player.attack(attackingCards);
            if (damage == -1)
                System.out.println("The attacking cards don't match or enough energy... Please try again.");
            else
                break;
        }
        int opponentCardIndex;
        while(true) {
            System.out.print("Choose your opponent's card you want to attack that, with its index -> ");
            opponentCardIndex = scanner.nextInt();
            if (!opponent.checkExistingCard(opponentCardIndex))      System.out.println("The opponent card was wrong... Please try again.");
            else{
                opponent.damagedCard(opponentCardIndex, damage);
                break;
            }
        }
        System.out.println("The attack was successful.\n");
        player.addLogActivities(("\the hits normalType with cards %s and strongType with cards %s," +
                " the damaged card from %s player is %d with total damage %d hit. ")
                .formatted(attackingCards.get(1), attackingCards.get(2), opponent.getPlayerName(), opponentCardIndex, damage));
    }
    /**
     * In this method, We implement the recharging process
     * @param player is a Player object, and it is his turn to choose an action
     * @return nothing
     */
    public void rechargeAction(Player player) {
        if (player.getRechargingEnergyTime() > 0) {
            int cardIndex;
            while (true) {
                System.out.print("Choose the card you want to recharge the energy of that with its index : ");
                cardIndex = scanner.nextInt();
                if (player.checkExistingCard(cardIndex)) {
                    player.rechargeEnergy(cardIndex);
                    break;
                } System.out.println("The player card was wrong... Please try again.");
            }
            System.out.println("The recharge was successful.\n");
            player.addLogActivities("\the choose card %d to recharge the energy of that. ".formatted(cardIndex));
        } else {
            System.out.println("The recharge was failed.\n");
            player.addLogActivities("\this chance for recharging the energy of cards are ' ZERO '. ");
        }
    }
    /**
     * This method implements the action of computer randomly
     * @param player is the name of player that is "computer".
     * @param opponent is the name of opponent of computer.
     * @return nothing
     */
    public void computerAction(Player player, Player opponent) {
        int randAction = (int) (Math.random() * 2);
        if (randAction == 0) {
            player.addLogActivities("player computer choose attack action. \n");
            player.DoRandomAttackingProcess(opponent);
            System.out.println("computer attacking was successful.\n");
        } else {
            player.addLogActivities("player computer choose recharge action. \n");
            if (player.getRechargingEnergyTime() > 0) {
                int cardIndex =  player.makeRandomCard();
                player.rechargeEnergy(cardIndex);
                System.out.println("computer recharging was successful.\n");
                player.addLogActivities("\the choose card %d to recharge the energy of that. ".formatted(cardIndex));
            } else {
                System.out.println("The recharge was failed.\n");
                player.addLogActivities("\this chance for recharging the energy of cards are ' ZERO '. ");
            }
        }
    }
    /** This method shows when the program will end and close the program.
     * @return nothing */
    public void endGame(){
        System.out.println("the game has ended successfully.\nGood luck!");
        scanner.close();
        System.exit(0);
    }
}