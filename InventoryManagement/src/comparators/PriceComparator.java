package comparators;
import java.util.Comparator;

import model.Product;

public class PriceComparator implements Comparator<Product> {
	@Override
	public int compare(Product product1, Product product2) {
		return Double.compare(product1.getPrice(), product2.getPrice());

	}
	
}
