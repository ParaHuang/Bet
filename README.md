# Main Thought
***The key point of homework is figure out what information we need to save.***
## 1.Customer information (Customer.java)
fields: customerId(int), sessionKey(String), lastestUpdatedTime(long)

within 10 mins, if a customer requests the session key, lastestUpdatedTime will be updated as the currentTime in millisecond. if it's longer than 10 mins, then sessionKey would be replaced by the first part of a UUID, also lastestUpdatedTime will be updated again.
## 2.Bet offer information (Offer.java)
fields: betOfferId(int), offers (Map<String,Integer>)

offers saves sessionKey as key, highest stake of this session on this offer as value. Therefore when customer post a stake on a offer, the program would replace the stake already saved in offers if it's lower than the one just offered.


# 2.BetController
# 3.BetService
# 4.Entity class

