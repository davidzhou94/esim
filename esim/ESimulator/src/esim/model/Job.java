package esim.model;

/**
 * A class that describes a Job being worked by a person at a firm.
 * 
 * @author David
 * 
 */
public class Job
{
    private Person aWorker;
    private double aUnpaidTime;
    private double aUnpaidProd;
    private double aTimePayMultiplier;
    private double aProdPayMultiplier;

    /**
     * 
     * @param pWorker
     *            The worker for this job.
     * @param pProdPayMultiplier
     *            The production pay multiplier.
     * @param pTimePayMultiplier
     *            The time pay multiplier.
     */
    public Job(Person pWorker, double pProdPayMultiplier, double pTimePayMultiplier)
    {
        aWorker = pWorker;
        aUnpaidTime = 0;
        aUnpaidTime = 0;
        aTimePayMultiplier = pTimePayMultiplier;
        aProdPayMultiplier = pProdPayMultiplier;
    }

    /**
     * 
     * @param pTime
     *            The time to add.
     */
    public void addTimeWorked(double pTime)
    {
        aUnpaidTime += pTime;
    }

    /**
     * 
     * @param pProduction
     *            The production to add.
     */
    public void addProduction(double pProduction)
    {
        aUnpaidProd += pProduction;
    }

    /**
     * Pays the person working the job the outstanding balance.
     */
    public void payOutstandingBalance()
    {
        double lAmount = 0;
        lAmount = aUnpaidTime * aTimePayMultiplier + aUnpaidProd * aProdPayMultiplier;
        aWorker.increaseBalance(lAmount);

        aUnpaidProd = 0;
        aUnpaidTime = 0;
    }

    /**
     * Generates production according to the worker's output factor.
     * 
     * @param pTime
     *            The amount of time to work.
     */
    public void generateProduction(double pTime)
    {
        this.addProduction(aWorker.getOutput() * pTime);
        this.addTimeWorked(pTime);
    }
}
