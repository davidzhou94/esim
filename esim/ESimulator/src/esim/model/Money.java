package esim.model;

/**
 * A simple class that represents money, eventually this can contain the logic for recording transaction histories and amount constraints and so
 * forth.
 * 
 * @author David
 * 
 */
public class Money
{
    private double aValue;

    /**
     * Constructor.
     * 
     * @param pValue
     *            The value to initialize with.
     */
    public Money(double pValue)
    {
        aValue = pValue;
    }

    /**
     * Get value.
     * 
     * @return The value.
     */
    public double getValue()
    {
        return aValue;
    }

    /**
     * Set value.
     * 
     * @param pValue
     *            The value to be set.
     */
    public void setValue(double pValue)
    {
        aValue = pValue;
    }

    /**
     * Transact a certain amount.
     * 
     * @param pAmount
     *            The amount to transact
     */
    public void transact(double pAmount)
    {
        aValue += pAmount;
    }

    @Override
    public boolean equals(Object pOther)
    {
        return this.aValue == ((Money) pOther).aValue;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }
}
