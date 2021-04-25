package comparators;

import data.Product;

import java.util.Comparator;
import java.util.Map;

public class UnitOfMeasureComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        return ((Map.Entry<Integer, Product>) o1).getValue().getUnitOfMeasure().compareTo(((Map.Entry<Integer, Product>) o2).getValue().getUnitOfMeasure());
    }
}
