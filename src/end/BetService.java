package end;

public class BetService {

	public Customer findCustomer(Object info) {
		return AppData.CUSTOMERS.stream()
				.filter(c -> (c.getCustomerId()+"").equals(info.toString()) || (c.getSessionKey().equals(info.toString()) && !c.isExpired()))
				.findFirst()
				.orElse(null);
	}

	public Offer findOffer(int betOfferId) {
		return AppData.OFFERS.stream()
				.filter(o -> o.getBetOfferId() == betOfferId)
				.findFirst()
				.orElse(null);
	}

	public String getSessionKey(int customerId) {
		Customer customer = findCustomer(customerId);
		return customer != null ? customer.getSessionKey() : "customer id is invalid";
	}

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

	public String getHighStakes(int betOfferId) {
		Offer offer = findOffer(betOfferId);
		return offer != null ? offer.getHighStakes() : "invalid bet offer Id!";
	}

}
