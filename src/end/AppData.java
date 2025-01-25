package end;

import java.util.ArrayList;
import java.util.List;

public class AppData {
	public static final List<Customer> CUSTOMERS;
	public static final List<Offer> OFFERS;
	
	static {
		CUSTOMERS = new ArrayList<Customer>();
		for(int i=101 ; i<=200 ; i++) {
			CUSTOMERS.add(new Customer(i));
		}
		
		OFFERS = new ArrayList<Offer>();
		for(int i=1001 ; i<=2000 ; i++) {
			OFFERS.add(new Offer(i));
		}
		
	}
}
