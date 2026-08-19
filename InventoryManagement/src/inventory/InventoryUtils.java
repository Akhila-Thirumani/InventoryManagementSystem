package inventory;



import java.util.Collection;

public final class InventoryUtils {

    private InventoryUtils() {
        // Utility class
    }

    public static <T> void displayCollection(Collection<T> collection) {

        if (collection.isEmpty()) {
            System.out.println("Collection is empty.");
            return;
        }

        for (T item : collection) {
            System.out.println(item);
        }
    }

    public static <T> int countElements(Collection<T> collection) {
        return collection.size();
    }

    public static <T> boolean containsElement(
            Collection<T> collection, T element) {

        return collection.contains(element);
    }
}