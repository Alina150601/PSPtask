import java.util.ArrayList;

public class EgorAlinaArrayList<T> extends ArrayList<T> {

    public boolean compareInnerObjects(int indexOne, int indexTwo) {
        return this.get(indexOne).equals(this.get(indexTwo));
    }
}
