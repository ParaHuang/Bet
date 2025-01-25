# Main Thought
***The key point of homework is figure out what information we need to save.***
## 1.Customer information (Customer.java)
fields: customerId(int), sessionKey(String), lastestUpdatedTime(long)

within 10 mins, customer requests the session key, lastestUpdatedTime will be updated as the currentTime in millisecond. If it's longer than 10 mins, then sessionKey would be replaced by the first part of a UUID, also update lastestUpdatedTime again.
## 2.Bet offer information (Offer.java)
fields: betOfferId(int), offers (Map<String,Integer>)

offers saves sessionKey as key, highest stake of this session on this offer as value. Therefore when customer post a stake on a offer, the program would replace the stake already saved in offers if it's lower than the one just offered.

## 3.others
<table align="center">
    <tr>
      <th>Start class</th>    
      <th colspan="3" style="text-align:center;">Server<br>⬇️</th>
   </tr>
   <tr>
        <th>BetController</th>    
        <th>/{customerid}/session<br>⬇️</th>    
        <th>/{betofferid}/stake<br>⬇️</th>    
        <th>/{betofferid}/highstakes<br>⬇️</th>    
    </tr>
   <tr>
        <th>BetService</th>    
        <th style="text-align:center;">getSessionKey(customerId)<br>⬇️</th>    
        <th style="text-align:center;">offerBet(betOfferId,customerInfo,stakeStr)<br>⬇️</th>    
        <th style="text-align:center;">getHighStakes(betOfferId)<br>⬇️</th>    
    </tr>
   <tr>
        <th>Entity class</th>    
        <th style="text-align:center;">Customer</th>    
        <th colspan="2" style="text-align:center;">Offer</th>    
    </tr>
   <tr>
        <th>methods</th>    
        <th style="text-align:center;">getSessionKey()</th>    
        <th style="text-align:center;">replaceOffer(sessionKeyy,stake)</th>    
        <th style="text-align:center;">getHighStakes(betOfferId)</th>    
    </tr>
   <tr>
        <th>process</th>    
        <th style="text-align:center;">
             op
        </th>    
        <th style="text-align:center;">replaceOffer(sessionKeyy,stake)</th>    
        <th style="text-align:center;">getHighStakes(betOfferId)</th>    
    </tr>
</table>


