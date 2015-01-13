package esim.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that caches good objects.
 * 
 * @author David
 * 
 */
public class GoodDirectory
{
    private Map<String, Good> aGoods = new HashMap<String, Good>();

    /**
     * 
     * @param pName
     *            The name to lookup.
     * @return The good.
     * @throws Exception
     *             The good might not exist.
     */
    public Good lookup(String pName) throws Exception
    {
        if (aGoods.containsKey(pName))
        {
            return aGoods.get(pName);
        }
        else
        {
            throw new Exception("Good does not exist");
        }
    }

    /**
     * 
     * @param pName
     *            The name of the good to add.
     * @param pValue
     *            The value of the good to add.
     */
    public void addGood(String pName, Money pValue)
    {
        addGood(new Good(pName, pValue));
    }
    
    /**
     * 
     * @param pGood
     *            The good object to add.
     */
    public void addGood(Good pGood)
    {
        if (!aGoods.containsKey(pGood.getName()))
        {
            aGoods.put(pGood.getName(), pGood);
        }
    }
}
