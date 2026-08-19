package comparators;

import java.util.Comparator;

import model.Product;

public class NameComparator implements Comparator<Product> {
	@Override
	public int compare(Product product1, Product product2) {
		return product1.getName().compareTo(product2.getName());

	}
	

}

