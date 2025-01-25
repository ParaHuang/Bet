package end;

public class BetService {
	//return the Customer object if customer id is the same or session key is the same and it's not expired
	//otherwise return null
	public Customer findCustomer(Object info) {
		return AppData.CUSTOMERS.stream()
				.filter(c -> (c.getCustomerId()+"").equals(info.toString()) || (c.getSessionKey().equals(info.toString()) && !c.isExpired()))
				.findFirst()
				.orElse(null);
	}

	//return the Offer object if bet offer id is the same
	//otherwise return null
	public Offer findOffer(int betOfferId) {
		return AppData.OFFERS.stream()
				.filter(o -> o.getBetOfferId() == betOfferId)
				.findFirst()
				.orElse(null);
	}

	//return session key if customer exists
	//otherwise return other prompt
	public String getSessionKey(int customerId) {
		Customer customer = findCustomer(customerId);
		return customer != null ? customer.getSessionKey() : "customer id is invalid";
	}

	//update stake if every information is correct
	//otherwise return other prompts
	public String offerBet(int betOfferId, String customerInfo, String stakeStr) {
		if (stakeStr == null || !stakeStr.matches("\\d+")) {
			return "empty or invalid stake!";
		}
		String sessionKey = customerInfo.split("=")[1];
		if(findCustomer(sessionKey) == null) {
			return "invalid or expired session Key!";
		}

		int stake = Integer.parseInt(stakeStr);
		Offer offer = findOffer(betOfferId);
		if (offer == null) {
			return "invalid bet offer Id!";
		}
		offer.replaceOffer(sessionKey, stake);
		return "";
	}


	//update first 20 high stakes if betOfferId is correct
	//otherwise return other prompt
	public String getHighStakes(int betOfferId) {
		Offer offer = findOffer(betOfferId);
		return offer != null ? offer.getHighStakes() : "invalid bet offer Id!";
	}

}
