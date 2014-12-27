package esim.model;

/**
 * A class that describes a Good.
 * @author David
 *
 */
public class Good
{
    private String aName;
    private Money aValue;
    
    /**
     * @param pName The name of the good.
     * @param pValue The value of the good.
     */
    public Good(String pName, Money pValue)
    {
        this.aName = pName;
        this.aValue = pValue;
    }
    
    /**
     * @return the aName
     */
    public String getName()
    {
        return aName;
    }
    /**
     * @param pName the aName to set
     */
    public void setName(String pName)
    {
        this.aName = pName;
    }
    /**
     * @return the aValue
     */
    public Money getValue()
    {
        return aValue;
    }
    @Override
    public int hashCode()
    {
        return aName.hashCode();
    }
    @Override
    public boolean equals(Object pObj)
    {
        return this.hashCode() == pObj.hashCode();
    }
}
