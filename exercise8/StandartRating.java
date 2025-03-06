package Aufgabe8;
import java.util.Set;
/**
 * This class implements a  standard rating function for a cube arrangement.
 * It calculates a total score by summing up contributions from:
 *  1) "Thermal surface" aspects:
 *     - +1 point for each neighboring cube in the four cardinal directions (N, S, E, W).
 *     - Additional small bonuses for free east/west/south sides.
 *  2) "View" (or "sight") aspects:
 *     - +1 point for each free side.
 *     - Additional +0.5 if up to 5 fields in that direction (straight line) are free.
 *
 */
public class StandartRating implements RatingFunction {

    /**
     * Rates the given arrangement by iterating over all coordinates
     * that have height > 0 and calculating two main scores:
     * thermal and sight. The final score is the sum of both.
     *
     * @param arrangement the arrangement to be rated
     * @return the total rating score
     */
    @Override
    public double rate(CubeArrangement arrangement) {
        double totalScore = 0.0;

        // Go through all positions where there is at least one cube (height > 0)
        Set<Coord> allCoords = getAllCoords(arrangement);

        for (Coord c : allCoords) {
            int h = arrangement.getHeightAt(c);
            if (h <= 0) {
                // Skip positions with no cube (just in case)
                continue;
            }

            // 1) Thermal surface (simplified):
            //    +1 point per neighboring cube (N, S, E, W)
            //    +0.2, +0.1, +0.5 for free east/west/south sides
            int neighborCount = countNeighbors(arrangement, c);
            double thermoScore = neighborCount;

            // If the side is free, add a small bonus
            if (!arrangement.hasCubeAt(c.x + 1, c.y)) { // east side free
                thermoScore += 0.2;
            }
            if (!arrangement.hasCubeAt(c.x - 1, c.y)) { // west side free
                thermoScore += 0.1;
            }
            if (!arrangement.hasCubeAt(c.x, c.y - 1)) { // south side free
                thermoScore += 0.5;
            }

            // 2) Sight (also simplified):
            //    +1 point per free side
            //    additional +0.5 if up to 5 fields in that direction are free
            double sightScore = 0.0;

            // North
            if (!arrangement.hasCubeAt(c.x, c.y + 1)) {
                sightScore += 1.0;
                if (isFreeLine(arrangement, c, 0, +1, 5)) {
                    sightScore += 0.5;
                }
            }
            // South
            if (!arrangement.hasCubeAt(c.x, c.y - 1)) {
                sightScore += 1.0;
                if (isFreeLine(arrangement, c, 0, -1, 5)) {
                    sightScore += 0.5;
                }
            }
            // East
            if (!arrangement.hasCubeAt(c.x + 1, c.y)) {
                sightScore += 1.0;
                if (isFreeLine(arrangement, c, +1, 0, 5)) {
                    sightScore += 0.5;
                }
            }
            // West
            if (!arrangement.hasCubeAt(c.x - 1, c.y)) {
                sightScore += 1.0;
                if (isFreeLine(arrangement, c, -1, 0, 5)) {
                    sightScore += 0.5;
                }
            }

            // Sum up the contributions from thermal and sight for this cube
            totalScore += (thermoScore + sightScore);
        }

        return totalScore;
    }

    /**
     * Retrieves all coordinates with height > 0 from the given arrangement.
     * This delegates to an actual method inside CubeArrangement
     * that returns all occupied coordinates, so we do NOT parse the string output.
     *
     * @param arrangement the arrangement to inspect
     * @return a set of coordinates where height > 0
     */
    private Set<Coord> getAllCoords(CubeArrangement arrangement) {
        return arrangement.getAllOccupiedCoords();
    }

    /**
     * Counts how many neighbors (N, S, E, W) of the given coordinate c are occupied.
     *
     * @param arr the arrangement to check
     * @param c   the coordinate whose neighbors are examined
     * @return the number of occupied neighboring cells
     */
    private int countNeighbors(CubeArrangement arr, Coord c) {
        int count = 0;
        if (arr.hasCubeAt(c.x + 1, c.y)) count++;
        if (arr.hasCubeAt(c.x - 1, c.y)) count++;
        if (arr.hasCubeAt(c.x, c.y + 1)) count++;
        if (arr.hasCubeAt(c.x, c.y - 1)) count++;
        return count;
    }

    /**
     * Checks if a straight line of up to 'distance' fields in the specified direction (dx, dy)
     * from the start coordinate is free (i.e., no cubes occupy those cells).
     *
     * @param arr      the arrangement to check
     * @param start    the starting coordinate
     * @param dx       the change in x per step
     * @param dy       the change in y per step
     * @param distance how many steps to check in that direction
     * @return true if all checked cells are free, false otherwise
     */
    private boolean isFreeLine(CubeArrangement arr, Coord start,
                               int dx, int dy, int distance) {
        int x = start.x;
        int y = start.y;

        for (int i = 1; i <= distance; i++) {
            x += dx;
            y += dy;
            // If we encounter a cube, sight is considered blocked
            if (arr.hasCubeAt(x, y)) {
                return false;
            }
        }
        return true;
    }
}

