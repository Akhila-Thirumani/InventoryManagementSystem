package comparators;

import java.util.Comparator;

import model.Product;

public class ValueComparator implements Comparator<Product> {
	
	@Override
	public int compare(Product product1, Product product2) {
		return Double.compare(
			    product2.getInventoryValue(),
			    product1.getInventoryValue()
			);
		
	 }
	

}
