package javabook.treeSet;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        var parts = new TreeSet<Item>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        var sortByDesc = new TreeSet<Item>(Comparator.comparing(Item::getDescription));

        sortByDesc.addAll(parts);
        System.out.println(sortByDesc);
    }
}
