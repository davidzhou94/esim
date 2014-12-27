package esim.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A class that describes a Firm.
 * @author David
 *
 */
public class Firm
{
    private String aName;
    private Set<Good> aProducts;
    private List<Job> aWorkers;
    private Map<Good, Double> aInventory;
    private Map<Good, Offer> aOffers;
    private Money aBalance;
    
    /**
     * 
     * @param pName The name of the firm.
     */
    public Firm(String pName)
    {
        aName = pName;
        aProducts = new HashSet<Good>();
        aWorkers = new ArrayList<Job>();
        aInventory = new Hashtable<Good, Double>();
        aOffers = new Hashtable<Good, Offer>();
        aBalance = new Money(0);
    }
    
    /**
     * 
     * @return The name of the firm.
     */
    public String getName()
    {
        return aName;
    }
    
    /**
     * 
     * @param pAmount The amount of money to increase the balance by.
     */
    public void increaseBalance(double pAmount)
    {
        aBalance.transact(pAmount);
    }
    
    /**
     * 
     * @param pAmount The amount of money to decrease the balance by.
     */
    public void decreaseBalance(double pAmount)
    {
        aBalance.transact(-1 * pAmount);
    }
    /**
     * 
     * @param pGood The good to add.
     */
    public void addProduct(Good pGood)
    {
        aProducts.add(pGood);
    }
    
    /**
     * 
     * @param pWorker The worker to add.
     */
    public void addWorker(Job pWorker)
    {
        aWorkers.add(pWorker);
    }
}
