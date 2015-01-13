package esim.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that contains the logic and rules for manipulating the model of the economy.
 * 
 * @author David
 * 
 */
public final class Engine
{
    private static Engine aInstance;
    private static final double LENGTH_OF_DAY = 8.0;
    
    private Market aMarket;
    private GoodDirectory aGoodDirectory;
    private List<Firm> aFirms;
    private List<Person> aPeople;
    private int aDaysElapsed;
    
    /**
     * Constructor.
     */
    private Engine()
    {
        aMarket = new Market();
        aGoodDirectory = new GoodDirectory();
        aFirms = new ArrayList<Firm>();
        aPeople = new ArrayList<Person>();
        aDaysElapsed = 0;
    }

    /**
     * 
     * @return the static instance
     */
    public static Engine getInstance()
    {
        if (aInstance == null)
        {
            aInstance = new Engine();
        }
        return aInstance;
    }

    /**
     * Adds all goods. The two lists must be the same length.
     * 
     * @param pNames List of Good names
     * @param pValues List of Good values
     */
    public void addAllGoods(List<String> pNames, List<Money> pValues)
    {
        if (pNames.size() == pValues.size())
        {
            for (int i = 0; i < pNames.size(); i++)
            {
                this.addGood(new Good(pNames.get(i), pValues.get(i)));
            }
        }
    }

    /**
     * Adds all goods. The two lists must be the same length.
     * 
     * @param pGoods List of Good objects
     */
    public void addAllGoods(List<Good> pGoods)
    {
        for (int i = 0; i < pGoods.size(); i++)
        {
            this.addGood(pGoods.get(i));
        }
    }
    
    /**
     * Adds the Good.
     * @param pGood A single good object to add
     */
    public void addGood(Good pGood)
    {
        aGoodDirectory.addGood(pGood);
    }
    
    /**
     * 
     * @param pFirm A single Firm object to add.
     */
    public void addFirm(Firm pFirm)
    {
        aFirms.add(pFirm);
    }
    
    /**
     * 
     * @param pFirms The list of Firm objects to add
     */
    public void addAllFirms(List<Firm> pFirms)
    {
        aFirms.addAll(pFirms);
    }
    
    /**
     * 
     * @param pPerson A single Person object to add.
     */
    public void addPerson(Person pPerson)
    {
        aPeople.add(pPerson);
    }
    
    /**
     * 
     * @param pPeople The list of Person objects to add
     */
    public void addAllPeople(List<Person> pPeople)
    {
        aPeople.addAll(pPeople);
    }
    
    /**
     * Asks every Person to visit the market and make purchases.
     */
    private void sendAllPeopleToShop()
    {
        List<Person> lConsumers = new ArrayList<Person>(aPeople);
        
        for (Person p : lConsumers)
        {
            p.goShopping(aMarket);
        }
    }
    
    /**
     * Asks every Person to go to work.
     */
    private void sendAllPeopleToWork()
    {
        List<Person> lWorkers = new ArrayList<Person>(aPeople);
        
        for (Person p : lWorkers)
        {
            p.goToWorkForAllJobs(LENGTH_OF_DAY);
        }
    }
    
    public void advanceDay()
    {
        this.sendAllPeopleToWork();
        
        this.sendAllPeopleToShop();
    }
}
