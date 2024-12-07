import java.util.*;
/**
 * <h1>Player class</h1>
 * This class, extends PlayerHand class and use for players object or computer.
 * @author Hossein Tatar
 * @since 2024-12-5
 * @version 1.0
 */
public class Player extends PlayerHand {
    /** <b>playerName</b> is a final String type of name of player or be "computer" if opponent be computer.*/
    private final String playerName;
    /** <b>rechargingEnergyTime</b> is an int and show the chance of time player can recharge energy of cards.*/
    private int rechargingEnergyTime;
    /** <b>playerLog</b> shows the activities of player during the game for her opponent and us.*/
    protected StringBuilder playerLog;
    /**
     * constructor of Player class in that we create player name and an initial hand for that and value of recharging energy time.
     * @param playerName is name of the player
     * @return nothing
     */
    public Player(String playerName) {
        super();
        this.playerName = playerName;
        this.playerLog = new StringBuilder();
        this.rechargingEnergyTime = 3;
    }
    /** This method gets the name of the player
     * @return a String type that is player name. */
    public String getPlayerName() { return playerName; }
    /** This method gets the time player can recharge that cards
     * @return an int type that is left chance. */
    public int getRechargingEnergyTime() { return rechargingEnergyTime; }
    /** In this method, we use a chance of recharging time for a card
     * @return nothing */
    public void setRechargingEnergyTime() { rechargingEnergyTime--; }
    /**
     * We use this method to choose 10 cards from 30 card to begin the game
     * @param chosenCards is a Set of Integers that shows index of card
     * @return a boolean type that shows the chosen process was success or failed
     */
    public boolean chooseTenCards(Set<Integer> chosenCards) {
        if (chosenCards.size() == 10 && checkExistingCards(chosenCards)) {
            HashMap<Integer, Card> playerHand = new HashMap<>();
            for (int cardIndex : chosenCards)
                playerHand.put(cardIndex, hand.get(cardIndex));
            hand = playerHand;
            return true;
        } return false;
    }
    /** This method is an overloaded method of choose ten cards that uses for <b>computer player</b> that implements randomly
     * @return nothing */
    public void chooseTenCards() {
        HashMap<Integer, Card> playerHand = new HashMap<>();
        while (playerHand.size() < 10) {
            int randCardIndex = makeRandomCard();
            playerHand.put(randCardIndex, hand.get(randCardIndex));
        } hand = playerHand;
    }
    /** In this method, we make a random card for <b>computer player</b> with index of that.
     * @return an int shows index of that random card. */
    public int makeRandomCard() {
        while (true) {
            int randCardIndex = (int) (Math.random() * 30);
            if (hand.containsKey(randCardIndex + 1))    return (randCardIndex + 1);
        }
    }
    /**
     * In this method, We handle the attacking process by checking the conditions and updating the energy of cards
     * @param attackingCards is a HashMap of Integer and Set of Integers that shows the type of attacking,
     *      and index of that card.
     * @return an int that is total hits of cards, ' -1 ' means the Attacking process was failed
     */
    public int attack(HashMap<Integer, Set<Integer>> attackingCards) {
        if (checkExistingCards(attackingCards.get(1)) && checkExistingCards(attackingCards.get(2))) {
            int[] cardHits = checkAttackingCondition(attackingCards);
            if (cardHits[0] != -1) {
                for (int i = 1; i < 3; i++)
                    for (int index : attackingCards.get(i))
                        hand.get(index).energy -= cardHits[0];
                return cardHits[1];
            }
        } return -1;
    }
    /** This method handles attacking process for <b>computer player</b> that implements randomly for
     * making an attack hand and attack with that to a random opponent card.
     * @param opponent that is a Player object and opponent of computer
     * @return nothing */
    public void DoRandomAttackingProcess(Player opponent) {
        HashMap<Integer, Set<Integer>> attackingCards = new HashMap<>();
        attackingCards.put(1, new HashSet<>());
        attackingCards.put(2, new HashSet<>());
        Set<Integer> cardIndexes = new HashSet<>();
        for (int i =1;i<3;i++){
            int randNumber = (int) (Math.random() * hand.size() / 2);
            while (randNumber + 1 > 0) {
                int randCardIndex = makeRandomCard();
                if (!cardIndexes.contains(randCardIndex)) {
                    cardIndexes.add(randCardIndex);
                    attackingCards.get(i).add(randCardIndex);
                }
                randNumber -= 1;
            }
        } int damage = attack(attackingCards);
        if (damage == -1)      DoRandomAttackingProcess(opponent);
        else{
            int opponentCardIndex = opponent.makeRandomCard();
            opponent.damagedCard(opponentCardIndex, damage);
            addLogActivities(("\the hits normalType with cards %s and strongType with cards %s," +
                    " the damaged card from %s player is %d with total damage %d hit. ")
                    .formatted(attackingCards.get(1), attackingCards.get(2), opponent.getPlayerName(), opponentCardIndex, damage));
        }
    }
    /**
     * This method handles the damaged card and updates the life of that and check if the life is zero or less,
     * that card be dead and delete from player's hand
     * @param cardIndex is index of damaged card
     * @param damage is an int type that shows tha damage amount
     * @return nothing
     */
    public void damagedCard(int cardIndex, int damage) {
        if (hand.get(cardIndex).life <= damage)    hand.remove(cardIndex);
        else    hand.get(cardIndex).life -= damage;
    }
    /**
     * This method uses for that time we want to use a chance of recharging a card
     * @param cardIndex is an int index of card we want to recharge that
     * @return nothing
     */
    public void rechargeEnergy(int cardIndex) {
        hand.get(cardIndex).energy = hand.get(cardIndex).initialEnergy;
        setRechargingEnergyTime();
    }
    /**
     * This method uses to check the condition of ending the Game.
     * if the hand of player is blank, that time is game over.
     * @return is a boolean type return the blanking the hand or not
     * */
    public boolean blankHand() {
        return !hand.isEmpty();
    }
    /**
     * This method uses to record the activity of player to show in end of that turn.
     * @param log a String that is the activity of player during the game.
     * @return nothing
     */
    public void addLogActivities(String log) {
        playerLog.append(log);
    }
}