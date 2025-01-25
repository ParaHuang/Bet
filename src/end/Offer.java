package end;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Offer {
	private int betOfferId;
	//key:sessionKey    value:stake offered by this client
	private Map<String, Integer> offers;
	public int getBetOfferId() {
		return betOfferId;
	}
	public Map<String, Integer> getOffers() {
		return offers;
	}
	public void setOffers(Map<String, Integer> offers) {
		this.offers = offers;
	}
	public Offer(int betOfferId) {
		this.betOfferId = betOfferId;
		offers = new HashMap<String, Integer>();
	}
	
	public void replaceOffer(String sessionKey,int stake) {
		Integer oldStake = offers.get(sessionKey);
		//situations of saving stake
		//1.oldStake is null,which means this customer never had a stake on this offer
		//2.new stake is higher
		if(oldStake == null || oldStake<stake) {
			offers.put(sessionKey, stake);
		}
	}
	
	//save many sessionKey=stake in a list, and sort this like as the rule below:
	Comparator<String> comparator = (o1,o2)-> {
		int s1 = Integer.parseInt(o1.split("=")[1]);
		int s2 = Integer.parseInt(o2.split("=")[1]);
		return s2-s1;
	};
	
	public String getHighStakes() {
		List<String> list = new ArrayList<String>();
		Set<String> keys = offers.keySet();
		//save content of offers as sessionKey=stake into a list
		for(String key:keys) {
			int stake = offers.get(key);
			list.add(key+"="+stake);
		}
		//sort list by descending order
		Collections.sort(list, comparator);
		
		StringBuffer sb = new StringBuffer();
		for(int i=0 ; i<Math.min(list.size(),20) ; i++) {
			sb.append(list.get(i)).append(",");
		}
		String content = sb.toString();
		return content.contains(",") ? content.substring(0,content.length()-1) : content;		
	}
	
	
}
