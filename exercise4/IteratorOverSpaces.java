package Aufgabe4;

import java.util.*;

public class IteratorOverSpaces implements EscapeIterator {
    /**
     * Returns 'true' if the iteration has more elements, 'false' otherwise.
     *
     * @return 'true' if the iteration has more elements, 'false' otherwise.
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * Returns the next element of the iteration. Throws a 'java.util.NoSuchElementException'
     * if the iteration has no more elements.
     *
     * @return the next element of the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    @Override
    public Space next() {
        return null;
    }
 /*   private final PriorityQueue<Map.Entry<Space, Double>> queue;
    private final Map<Space, Double> distances;
    private final Map<Space, Space> predecessors;
    private final Set<Space> visited;
    private final boolean useLift;
    private final boolean entering;
    private Space current;
    private final int maxEntries = 10;
    private int entriesProcessed = 0;

    public IteratorOverSpaces(Space start, boolean lift, boolean enter) {
        this.queue = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getValue));
        this.distances = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.visited = new HashSet<>();
        this.useLift = lift;
        this.entering = enter;
        this.current = start;


        initializeTraversal(start);
    }

    private void initializeTraversal(Space start) {
        if (!entering) {
            distances.put(start, 0.0);
            queue.add(new AbstractMap.SimpleEntry<>(start, 0.0));
            dijkstra();
        } else {

            for (Space publicRoad : getStartingSpaces(start)) {
                distances.put(publicRoad, 0.0);
                queue.add(new AbstractMap.SimpleEntry<>(publicRoad, 0.0));
            }
            dijkstra();
        }
    }

    private void dijkstra() {
        while (!queue.isEmpty()) {
            Map.Entry<Space, Double> entry = queue.poll();
            Space space = entry.getKey();
            double distance = entry.getValue();

            if (visited.contains(space)) {
                continue;
            }
            visited.add(space);

            for (Space adjacent : getAdjazentSpaces(space)) {
                if (!useLift && adjacent instanceof Lift) {
                    continue; // Skip lifts if not allowed
                }

                double newDist = distances.get(space) + distanceBetween(space, adjacent);
                if (newDist < distances.getOrDefault(adjacent, Double.MAX_VALUE)) {
                    distances.put(adjacent, newDist);
                    predecessors.put(adjacent, space);
                    queue.add(new AbstractMap.SimpleEntry<>(adjacent, newDist));
                }
            }
        }
    }

    private double distanceBetween(Space a, Space b) {
        return 1.0;
    }

    private List<Space> getAdjazentSpaces(Space space) {
        if (entering) {
            return getSpacesThatConnectTo(space);
        } else {
            return getSpacesConnectedFrom(space);
        }
    }

    private List<Space> getSpacesConnectedFrom(Space space) {
        // шось написати ??????
        return new ArrayList<>(getAdjazentSpaces(space));
    }

    private List<Space> getSpacesThatConnectTo(Space space) {
        List<Space> result = new ArrayList<>();
        for (Space potentialSource : getAllSpacesInSystem()) { //?????
            if (getAdjazentSpaces(potentialSource).contains(space)) {
                result.add(potentialSource);
            }
        }
        return result;
    }

    private List<Space> getStartingSpaces(Space space) {
        List<Space> result = new ArrayList<>();
        for (Space adjacent : getAdjazentSpaces(space)) {
            if (adjacent instanceof PublicRoad) {
                result.add(adjacent);
            }
        }
        return result;
    }

    @Override
    public boolean hasNext() {
        return current != null && visited.contains(current) && entriesProcessed < maxEntries;
    }

    @Override
    public Space next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more spaces in the iteration");
        }

        Space nextSpace = current;
        current = predecessors.get(current);
        entriesProcessed++;


        if (entriesProcessed >= maxEntries) {
            current = null;
        }

        return nextSpace;
    }*/
}
