package esim.model;

/**
 * A class to describe a demand for a good.
 * 
 * @author David
 * 
 */
public class Demand
{
    private Good aGood;
    private double aAmount;
    private Money aPriceCeiling;
    private double aIncreaseFactor;

    /**
     * @param pGood
     *            the Good.
     * @param pAmount
     *            the Amount.
     * @param pPriceCeiling
     *            the Price Ceiling.
     * @param pIncreaseFactor
     *            the Increase Factor.
     */
    public Demand(Good pGood, double pAmount, double pPriceCeiling, double pIncreaseFactor)
    {
        this.aGood = pGood;
        this.aAmount = pAmount;
        this.aPriceCeiling = new Money(pPriceCeiling);
        this.aIncreaseFactor = pIncreaseFactor;
    }

    /**
     * 
     */
    public void increment()
    {
        aAmount += aIncreaseFactor;
    }

    /**
     * @return the aGood.
     */
    public Good getGood()
    {
        return aGood;
    }

    /**
     * @return the Amount.
     */
    public double getAmount()
    {
        return aAmount;
    }

    /**
     * @param pAmount
     *            the Amount to set.
     */
    public void setAmount(double pAmount)
    {
        this.aAmount = pAmount;
    }

    /**
     * @return the Price Ceiling.
     */
    public Money getPriceCeiling()
    {
        return aPriceCeiling;
    }

    /**
     * @param pPriceCeiling
     *            the Price Ceiling to set.
     */
    public void setPriceCeiling(Money pPriceCeiling)
    {
        this.aPriceCeiling = pPriceCeiling;
    }
}
