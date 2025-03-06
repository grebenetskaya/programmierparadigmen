package Aufgabe4;

import java.util.Set;
/**
 * Escape: Represents shortest paths showing how a Space can be exited to a public road or entered from a public road.
 *
 *
 * - Preconditions: The Space must be accessible and have at least one path to a PublicRoad.
 * - Postconditions: Provides an iterator over Circulation Spaces in the shortest path.
 * - The iterator must return at most 10 entries.
 */
public class Escape {

    private Space pin;  // where we start

    public Escape(Space pin){
        this.pin = pin;
    }

    private Set<Space> distance(){
        return null;
    }
    /**
     * Returns the Space that is to be escaped from or entered into.
     *
     * @return the target Space
     */
    private Space space(){
        return this.pin;
    };
    protected EscapeIterator iterator(boolean lift, boolean enter){
        //return new IteratorOverSpaces(pin, lift, enter);
        return null;
    }
}
