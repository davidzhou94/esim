package esim.model;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * A class that describes a person.
 * 
 * @author David
 * 
 */
public class Person
{
    private String aName;
    private Money aBalance;
    private Map<Good, Double> aInventory;
    private double aOutput;
    private List<Demand> aDemands;
    private List<Job> aJobs;

    /**
     * 
     * @param pName
     *            The name of the person.
     * @param pOutput
     *            The output of the person.
     */
    public Person(String pName, double pOutput)
    {
        aName = pName;
        aBalance = new Money(0);
        aInventory = new Hashtable<Good, Double>();
        aOutput = pOutput;
        aDemands = new ArrayList<Demand>();
        aJobs = new ArrayList<Job>();
    }

    public double getOutput()
    {
        return aOutput;
    }

    /**
     * 
     * @param pGood
     *            The good to add.
     * @param pAmount
     *            The amount to add.
     */
    public void addInventory(Good pGood, double pAmount)
    {
        double lAmount = pAmount;
        if (aInventory.containsKey(pGood))
        {
            lAmount += aInventory.get(pGood);
        }
        aInventory.put(pGood, lAmount);
    }

    /**
     * 
     * @param pAmount
     *            The amount of money to increase the balance by.
     */
    public void increaseBalance(double pAmount)
    {
        aBalance.transact(pAmount);
    }

    /**
     * 
     * @param pAmount
     *            The amount of money to decrease the balance by.
     */
    public void decreaseBalance(double pAmount)
    {
        aBalance.transact(-1 * pAmount);
    }

    /**
     * 
     * @param pDemand
     *            The demand to add.
     */
    public void addDemand(Demand pDemand)
    {
        aDemands.add(pDemand);
    }

    /**
     * Generates demands for this person.
     */
    public void generateDemand()
    {
        for (Demand d : aDemands)
        {
            d.increment();
        }
    }
}
