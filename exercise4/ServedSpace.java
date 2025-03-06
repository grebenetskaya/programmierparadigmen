package Aufgabe4;
/**
 * ServedSpace: A served Interior space intended for long-term human occupancy.
 *
 *
 * - Preconditions: Objects of this type must be Interior spaces intended for long-term occupancy.
 * - Postconditions:
 *   - Must fulfill quality standards: adequate lighting, ventilation, heating, minimum heights, movement areas.
 *   - The method alternativeEscape() must return at least 1.1 m² to be compliant.
 */

public abstract class ServedSpace extends Room implements Circulation{
    private double[] windows;
    public ServedSpace(Entity associatedEntity, double[] windows, Space... adjazentSpaces) {
        super(associatedEntity, adjazentSpaces);
        this.windows = windows;
    }

    double alternativeEscape() {
        double result = 0;
        for (double i : windows) {
            if(i >= 1.1){
                result += i;
            }
        }
        return result;
    }
}
