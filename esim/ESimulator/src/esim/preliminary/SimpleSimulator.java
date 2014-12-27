package esim.preliminary;

import java.io.*;

class Firm
{
    double QS = 0;
    double PS = 0;
    double techL = 1;

    Firm(double outputQuantity, double techLevel)
    {
        QS = outputQuantity;
        PS = (outputQuantity - 18) / 4;
        techL = techLevel;
    }

    public String toString()
    {
        return "Firm- QS: " + QS + ", Psupply: " + PS + ".";
    }
}

class Market
{
    final double P_INC_RATIO = 0.1;
    final double P_DEC_RATIO = 0.08;
    double MQ = 0;
    double MP = 0;
    Firm f;
    Household h;
    boolean marketSurplus = false;
    boolean marketEquilib = false;

    Market(Firm someFirm, Household someHousehold)
    {
        f = someFirm;
        h = someHousehold;
    }

    void putOnMarket(double percent)
    {
        if (percent > 1 || percent < 0) percent = 1;

        // MQ=MQ+(percent * f.QS);
        MQ = (percent * f.QS);
        MP = f.PS;

        f.QS = f.QS - f.QS * percent;
    }

    void buyOffMarket()
    {
        /*
         * if (marketEquilib) h.QD=h.QD + 30-5 * MP; else if (marketSurplus) { h.PD=h.PD-h.PD*P_DEC_RATIO; h.QD=h.QD=h.QD + 30-5 * }
         */

        h.QD = Math.ceil(30 - 5 * MP);

        if (h.QD > MQ)
        {
            // MQ=0;
            // h.QD=h.QD-MQ;
            marketSurplus = false;
            marketEquilib = false;
        }
        else if (h.QD < MQ)
        {
            // MQ=MQ-h.QD;
            // h.QD=0;
            marketSurplus = true;
            marketEquilib = false;
        }
        else
        {
            // h.QD=0;
            // MQ=0;
            marketEquilib = true;
            marketSurplus = false;
        }
    }

    void generateOutput()
    {
        if (marketEquilib)
        {
            f.PS = MP;
            f.QS = Math.floor(18 + 4 * f.PS);
        }
        else if (marketSurplus)
        {
            f.PS = f.PS - f.PS * P_INC_RATIO;
            f.QS = Math.floor(f.QS + 18 + 4 * f.PS);
        }
        else
        {
            f.PS = f.PS + f.PS * P_DEC_RATIO;
            f.QS = Math.floor(f.QS + 18 + 4 * f.PS);
        }
    }

    void turnHandler()
    {

        System.out.println(f.toString());
        System.out.println(h.toString());
        this.putOnMarket(1);
        System.out.println("Market Quantity: " + MQ + ", Market Price: " + MP);
        this.buyOffMarket();
        System.out.println("Surplus? " + marketSurplus + ", Equilibrium? " + marketEquilib);
        this.generateOutput();
        System.out.println(f.toString());
        System.out.println(h.toString());

    }
    /*
     * void addFirm(Firm someFirm) { f=someFirm; MQ=MQ+someFirm.QS; }
     * 
     * void addHousehold(Household someHousehold) { h=someHousehold; }
     */
}

class Household
{
    double QD = 0;
    double PD = 0;

    Household(double quantityDemanded)
    {
        QD = quantityDemanded;
        PD = (30 - quantityDemanded) / 5;
    }

    public String toString()
    {
        return "Household- QD: " + QD + ".";
    }
}

public class SimpleSimulator
{

    public static void main(String[] args)
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int turn = 1;
        String rl = "y";
        Household h1 = new Household(50);
        Firm f1 = new Firm(28, 1);
        Market market1 = new Market(f1, h1);

        while (rl.equals("y"))
        {
            System.out.println("Turn: " + turn);
            market1.turnHandler();

            System.out.print("Continue? ");

            try
            {
                rl = br.readLine();
            }
            catch (IOException ioe)
            {
                System.out.println("input error");
                System.exit(1);
            }
            System.out.println();

            turn++;
        }

    }

}
