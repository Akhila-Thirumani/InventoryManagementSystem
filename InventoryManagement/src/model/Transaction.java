
package model;
import java.util.Date;

public class Transaction {
	 private String type;
	    private String sku;
	    private int oldQuantity;
	    private int newQuantity;
	    private Date timestamp;

	    public Transaction(String type, String sku, int oldQuantity, int newQuantity) {
	        this.type = type;
	        this.sku = sku;
	        this.oldQuantity = oldQuantity;
	        this.newQuantity = newQuantity;
	        this.timestamp = new Date();
	    }

	    public String getType() {
	        return type;
	    }

	    public String getSku() {
	        return sku;
	    }

	    public int getOldQuantity() {
	        return oldQuantity;
	    }
	    public int getNewQuantity() {
	        return newQuantity;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	    @Override
	    public String toString() {
	        return String.format(
	                "%s: %s - Quantity changed from %d to %d at %s",
	                type, sku, oldQuantity, newQuantity, timestamp
	        );
	    }


}
