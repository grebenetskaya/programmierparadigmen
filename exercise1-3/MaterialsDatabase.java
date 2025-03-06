import java.util.ArrayList;
import java.util.List;
/**
 * The MaterialsDatabase class serves as a repository of available material types used in building construction.
 * It initializes a predefined list of material types that are commonly used in buildings, such as concrete, wood,
 * glass, and metal, which can be accessed and utilized across various parts of the simulation.
 */
public class MaterialsDatabase {
    public List<MaterialType> materialTypes;

    public MaterialsDatabase() {
        this.materialTypes = new ArrayList<>();
        this.materialTypes.add(new Concrete());
        this.materialTypes.add(new Wood());
        this.materialTypes.add(new Glass());
        this.materialTypes.add(new Brick());
        this.materialTypes.add(new NaturalStone());
        this.materialTypes.add(new Plasterboard());
        this.materialTypes.add(new Metal());
        this.materialTypes.add((new Asphalt()));
    }

    public List<MaterialType> getMaterialTypes(){
        return this.materialTypes;
    }
}
