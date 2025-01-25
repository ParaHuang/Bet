package end;

import java.util.UUID;

public class Customer {
	private int customerId;
	private String sessionKey;
	private long latestUpdatedTime;
	public int getCustomerId() {
		return customerId;
	}
	public String getSessionKey() {
		update();
		return sessionKey;
	}
	public Customer(int customerId) {
		this.customerId = customerId;
	}
	public void setLatestUpdatedTime() {
		latestUpdatedTime = System.currentTimeMillis();;
	}
	
	public boolean isExpired() {
		long currentTime = System.currentTimeMillis();
		return currentTime-latestUpdatedTime > 10*60*1000;
	}
	
	public void update() {
		if(isExpired()) {
			sessionKey = UUID.randomUUID().toString().split("-")[0].toUpperCase();
		}
		latestUpdatedTime = System.currentTimeMillis();
	}
	
	
	
}
