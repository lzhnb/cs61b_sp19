package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;


    /**
     * Should return a color with red = 34, green = 0, blue = 231
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public Clorus(double e) {
        super("clorus");
        color();
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Clorus should lose 0.03 units of energy when moving.
     */
    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Clorus should lose 0.01 units of energy when staying.
     */
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }
    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Clorus replicate() {
        this.energy /= 2;
        Clorus c = new Clorus(energy);
        return c;
    }

    /**
     * Clorus should get attack creature's energy
     */
    public void attack(Creature c) {
        this.energy += c.energy();
    }


    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}
        for (Direction key : neighbors.keySet()) {
            if (neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            } else if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.add(key);
            }
        }

        if (emptyNeighbors.size() == 0) {
            // Rule1
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() > 0) {
            // Rule 2: Otherwise, if any Plips are seen, the Clorus will ATTACK
            // one of them randomly.
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        } else if (energy > 1) {
            // Rule 3: Otherwise, if the Clorus has energy greater than or equal to one,
            // it will REPLICATE to a random empty square.
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        } else {
            // Rule 4: Otherwise, the Clorus will MOVE to a random empty square.
            return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
        }
    }
}
