/**
 * <h1>Card class</h1>
 * In this class, we create cards and initial the value of that.
 * @author Hossein Tatar
 * @version 1.0
 * @since 2024-12-5
 */
public class Card {
    /** <b>name</b> is a final protected field of name of Animal and never change again*/
    protected final String name;
    /** <b>normalHit</b> is a final protected field of amount of normal hit of Animal and never change again*/
    protected final int normalHit;
    /** <b>normalHit</b> is a final protected field of amount of strong hit of Animal and never change again*/
    protected final int strongHit;
    /** <b>energy</b> is a protected field of amount of energy for the Animal*/
    protected int energy;
    /** <b>initialEnergy</b> is a final protected field of amount of energy for the Animal and never change again*/
    protected final int initialEnergy;
    /** <b>life</b> is a protected field of amount of life of Animal*/
    protected int life;
    /**
     * Constructor of Card class and create a new card with that properties
     * @param name is name of animal
     * @param normalHit is the amount of normal hit of that
     * @param strongHit is the amount of strong hit of that
     * @param energy is the amount of initial energy
     * @param life is the amount of initial life of that
     * @return nothing
     */
    public Card(String name, int normalHit, int strongHit, int energy, int life){
        this.name = name;
        this.normalHit = normalHit;
        this.strongHit = strongHit;
        this.energy = energy;
        this.initialEnergy = energy;
        this.life = life;
    }
    /**
     * Another constructor for Card class to make a copy of an existing card with that properties
     * @param animal is a Card object to make a copy of that
     * @return nothing
     */
    public Card(Card animal){
        this.name = animal.name;
        this.normalHit = animal.normalHit;
        this.strongHit = animal.strongHit;
        this.energy = animal.energy;
        this.initialEnergy = animal.initialEnergy;
        this.life = animal.life;
    }
    /** The ' toString method ' have been override for prints the object of this class the way we want:
     * @return a String type of customized print */
    @Override
    public String toString() {
        return
                "Animal: %-8s   ".formatted(name) +
                "Normal hit: %-4d    ".formatted(normalHit) +
                "Strong hit: %-4d    ".formatted(strongHit) +
                "energy: %-4d    ".formatted(energy) +
                "Life: %-4d    ".formatted(life);
    }
}