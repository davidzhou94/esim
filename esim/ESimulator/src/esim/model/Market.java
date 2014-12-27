package esim.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class describing the Market.
 * 
 * @author David
 * 
 */
public class Market
{
    private List<Offer> aOffers;

    /**
     * Constructor.
     */
    public Market()
    {
        aOffers = new ArrayList<Offer>();
    }

    /**
     * 
     * @param pOffer
     *            the Offer to add to the market.
     */
    public void addOffer(Offer pOffer)
    {
        aOffers.add(pOffer);
    }

    /**
     * 
     * @param pGood
     *            the Good to find Offers for.
     * @return The list of offers.
     */
    public List<Offer> getOffers(Good pGood)
    {
        List<Offer> lReturn = new ArrayList<Offer>();

        for (Offer o : aOffers)
        {
            if (o.getGood().equals(pGood))
            {
                lReturn.add(o);
            }
        }

        return lReturn;
    }

    /**
     * Makes a purchase from the cheapest offers for a given good. It is possible that not all or even any of the amount demanded is actually
     * purchased. The Market will make the transaction for both the consumer and the firm.
     * 
     * @param pPerson
     *            the person making the purchase.
     * @param pGood
     *            the good being purchased.
     * @param pAmount
     *            the amount being purchased.
     * @param pPriceCeiling
     *            the maximum price.
     * @return the amount that was purchased.
     */
    public double greedyPurchase(Person pPerson, Good pGood, double pAmount, Money pPriceCeiling)
    {
        double lAmount = 0;
        List<Offer> lOffers = this.getOffers(pGood);
        Collections.sort(lOffers);

        int i = 0;
        Offer currentOffer = lOffers.get(i);
        while (currentOffer.getPrice().getValue() <= pPriceCeiling.getValue() && lAmount < pAmount)
        {
            if (currentOffer.getAmount() < (pAmount - lAmount))
            {
                pPerson.decreaseBalance(currentOffer.getPrice().getValue() * currentOffer.getAmount());
                pPerson.addInventory(pGood, currentOffer.getAmount());
                lAmount += currentOffer.getAmount();
                currentOffer.purchaseAll();
            }
            else
            {
                pPerson.decreaseBalance(currentOffer.getPrice().getValue() * (pAmount - lAmount));
                pPerson.addInventory(pGood, pAmount - lAmount);
                currentOffer.purchaseAmount(pAmount - lAmount);
                lAmount = pAmount;
            }
            i++;
            currentOffer = lOffers.get(i);
        }

        return lAmount;
    }
}
