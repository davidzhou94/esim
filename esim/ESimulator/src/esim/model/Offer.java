package esim.model;

/**
 * A class describing an offer on the Market.
 * 
 * @author David
 * 
 */
public class Offer implements Comparable<Offer>
{
    private Good aGood;
    private Firm aFirm;
    private double aAmount;
    private Money aPrice;

    /**
     * 
     * @param pGood
     *            the Good.
     * @param pFirm
     *            the Firm.
     * @param pAmount
     *            the Amount of the Offer.
     * @param pPrice
     *            the Price of the Offer.
     */
    public Offer(Good pGood, Firm pFirm, double pAmount, Money pPrice)
    {
        aGood = pGood;
        aFirm = pFirm;
        aAmount = pAmount;
        aPrice = pPrice;
    }

    /**
     * 
     * @return the Good.
     */
    public Good getGood()
    {
        return aGood;
    }

    /**
     * 
     * @return the Amount.
     */
    public double getAmount()
    {
        return aAmount;
    }

    /**
     * 
     * @param pPrice
     *            the new Price.
     */
    public void setPrice(Money pPrice)
    {
        aPrice = pPrice;
    }

    /**
     * 
     * @return the Price.
     */
    public Money getPrice()
    {
        return aPrice;
    }

    /**
     * 
     * @pre pAmount <= aAmount
     * @param pAmount
     *            the Amount to purchase.
     */
    public void purchaseAmount(double pAmount)
    {
        if (pAmount <= aAmount)
        {
            aFirm.increaseBalance(pAmount * aPrice.getValue());
            aAmount -= pAmount;
        }
    }

    /**
     * Purchase the entire amount made available by this good.
     */
    public void purchaseAll()
    {
        this.purchaseAmount(aAmount);
    }

    @Override
    public int compareTo(Offer pOther)
    {
        return Double.compare(this.getPrice().getValue(), pOther.getPrice().getValue());
    }
}
