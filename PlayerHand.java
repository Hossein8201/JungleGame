import java.util.*;
/**
 * <h1>PlayerHand class</h1>
 * In this class, We create the hand of player and display that or check existing a card on it.
 * @author Hossein Tatar
 * @since 2024-12-5
 * @version 1.0
 */
public class PlayerHand {
    /** <b>hand</b> is a HashMap type with Integer type key and value of Card objects.
     * We store the hand of player on it.*/
    protected HashMap<Integer, Card> hand = new HashMap<>();
    /** <b>ANIMALS</b> is an array of Cards type and is a defined value of existing card in this game.<p>
     * We use that to choose our cards from that.*/
    final Card[] ANIMALS = new Card[]{
            new Card("lion",    150, 500, 1000, 900),
            new Card("bear",    130, 600, 900,  850),
            new Card("tiger",   120, 650, 850,  850),
            new Card("vulture", 100, 0,   600,  350),
            new Card("fox",     90,  0,   600,  400),
            new Card("elephant",50,  70,  500,  1200),
            new Card("wolf",    0,   700, 700,  450),
            new Card("boar",    80,  0,   500,  1100),
            new Card("hippo",   110, 0,   360,  1000),
            new Card("cow",     90,  100, 400,  750),
            new Card("rabbit",  80,  0,   350,  200),
            new Card("turtle",  200, 0,   230,  350)
    };
    /** Constructor of player hand and creates ' 30 ' number of cards for player in the start of game.
     * @return nothing */
    public PlayerHand() {
        int i = 0;
        while (i < 30) {
            int rand = (int)(Math.random() * ANIMALS.length);
            if (numberOfCard(ANIMALS[rand]) < 5){
                hand.put(hand.size()+1, new Card(ANIMALS[rand]));
                i++;
            }
        }
    }
    /**
     * This method, calculate the number of an object card in my hand to ensure the number of that be 5 or less
     * @param cardSelected is a card object
     * @return an int that is the number of repeating a card
     */
    public int numberOfCard(Card cardSelected) {
        int count = 0;
        for (Card card : hand.values())
            if (card.name.equals(cardSelected.name))    count++;
        return count;
    }
    /**
     * This method use to create a Set of Integer that are indexes of cards you chosen that.
     * @param cardNames is an array of Strings
     * @return a Set of cardIndexes
     */
    public Set<Integer> getCardIndex(String[] cardNames) {
        Set<Integer> chosenCards = new HashSet<>();
        for (String cardName : cardNames) {
            for (int cardIndex : hand.keySet()) {
                if (cardName.equals(hand.get(cardIndex).name) && !chosenCards.contains(cardIndex)) {
                    chosenCards.add(cardIndex);
                    break;
                }
            }
        } return chosenCards;
    }
    /**
     * This method checks the chosen cards by player to ensure the cards are exist in our hand
     * @param cards is Set type of Integer that shows the Index of card
     * @return a boolean type of existing or not
     */
    public boolean checkExistingCards(Set<Integer> cards) {
        for (Integer cardIndex : cards) {
            if (!hand.containsKey(cardIndex))     return false;
        } return true;
    }
    /**
     * This method checks a single card in our hand to exist it or not
     * @param cardIndex is an int type of index of the card
     * @return a boolean type of existing or not
     */
    public boolean checkExistingCard(int cardIndex) {
        return hand.containsKey(cardIndex);
    }
    /**
     * In this method, We check the cards chosen for attacking and their Hits and reduce of Energy,
     * hove enough Energy and capable to subtract.
     * @param attackingCards is a HashMap of Integer and a Set of Integers.<p>
     *     the Integer key shows the type of hitting, and inner Set Integers is an index of card.
     * @return is an Array of int number that are average reduce of energy and total hit of cards.<p>
     *     if average reduce of energy be -1, that means the attack by this card was failed.
     */
    public int[] checkAttackingCondition(HashMap<Integer, Set<Integer>> attackingCards) {
        int totalHits = 0;
        int numberOfAnimals = 0;
        int averageReduceEnergy;
        for (int attackType : attackingCards.keySet()) {
            if (attackType == 1)
                for (int index : attackingCards.get(1)) {
                    totalHits += hand.get(index).normalHit;
                    numberOfAnimals++;
                }
            else
                for (int index : attackingCards.get(2)) {
                    totalHits += hand.get(index).strongHit;
                    numberOfAnimals++;
                }
        }
        averageReduceEnergy = totalHits / numberOfAnimals;
        for (int i : attackingCards.keySet())
            for (int index : attackingCards.get(i))
                if (hand.get(index).energy < averageReduceEnergy)       return new int[]{-1, averageReduceEnergy};
        return new int[]{averageReduceEnergy, totalHits};
    }
    /**
     * This method uses for choosing the hand of a player process, shows the game hand of a player.
     * also it uses for showing the cards <b>with index of start and end</b> cards.
     * @param playerName is a String of name of player
     * @return nothing
     */
    public void showHand(String playerName) {
        StringBuilder text = new StringBuilder();
        List<String> cardNames = new ArrayList<>();
        text.append("%s's hand to change that is :\n".formatted(playerName));
        for (int cardIndex : hand.keySet()) {
            if (!cardNames.contains(hand.get(cardIndex).name)) {
                cardNames.add(hand.get(cardIndex).name);
                text.append("\t").append(hand.get(cardIndex).toString())
                        .append("Amount: %d".formatted(numberOfCard(hand.get(cardIndex)))).append("\n");
            }
        }
        System.out.println(text);
    }
    /**
     * This method uses to shows the game hand of a player.
     * also it uses for showing the cards <b>with index</b> of every card to choose between the same cards.
     * @param playerName is name of the owner of this game hand
     * @return nothing
     */
    public void displayHand(String playerName) {
        StringBuilder text = new StringBuilder();
        text.append("%s's hand is :\n".formatted(playerName));
        for (int cardIndex : hand.keySet()) {
            text.append("\t").append("%-2d) ".formatted(cardIndex)).append(hand.get(cardIndex).toString()).append("\n");
        }
        System.out.println(text);
    }
}