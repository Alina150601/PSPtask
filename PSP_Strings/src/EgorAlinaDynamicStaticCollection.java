import java.util.ArrayList;

public class EgorAlinaDynamicStaticCollection<T> {

    private ArrayList<T> list;

    private int size;

    public EgorAlinaDynamicStaticCollection(int size) {
        list = new ArrayList<T>();
        this.size = size;
    }

    public int size() {
        return size;
    }

    public void add(T element)
    {
        if (list.size() < size) {
            list.add(element);
        }
        else {
            list.remove(0);
            list.add(element);
        }
    }

    public void remove(T element) {
        list.remove(element);
    }

    public T get(int index) {
        return list.get(index);
    }
}
