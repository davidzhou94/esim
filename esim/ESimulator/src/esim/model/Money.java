package esim.model;

/**
 * A simple class that represents money, eventually this can contain the logic
 * for recording transaction histories and amount constraints and so forth.
 * 
 * @author David
 * 
 */
public class Money {
    private int aValue;

    /**
     * Get value.
     * 
     * @return The value
     */
    public int getValue() {
        return aValue;
    }

    /**
     * Set value.
     * 
     * @param pValue
     *            The value to be set
     */
    public void setValue(int pValue) {
        aValue = pValue;
    }

    /**
     * Transact a certain amount
     * 
     * @param pAmount
     *            The amount to transact
     */
    public void transact(int pAmount) {
        aValue = aValue + pAmount;
    }

    @Override
    public boolean equals(Object pOther) {
        return (this.aValue == ((Money) pOther).aValue);
    }
}
