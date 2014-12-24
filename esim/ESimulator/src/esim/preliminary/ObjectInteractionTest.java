package esim.preliminary;

import java.io.*;
import java.util.*;

class someFamily implements Comparable<someFamily>
{
	private double Pd = 0;
	private double Qd = 0;
	private int a = 60;
	private int b = 12;

	someFamily()
	{

	}

	someFamily(double marketP)
	{
		a = 1;
		b = 1;
		this.setP(marketP);
	}

	someFamily(int A, int B)
	{
		if (A >= 1 && B > 0)
		{
			a = A;
			b = B;
			this.setP((1 + Math.random() * (a / b - 1)));
		}
		else
		{
			a = 1;
			b = 1;
			this.setP(1);
		}
	}

	public void setVars(int varA, int varB)
	{
		if (varA > 0 && varB > 0)
		{
			a = varA;
			b = varB;
		}
	}

	public void setP(double p)
	{
		Pd = Math.ceil(p * 100) / 100;
		if (p >= 0 && p <= a / b) Qd = Math.ceil(a - b * Pd);
		else Qd = 0;
	}

	public void setQ(int q)
	{
		Qd = (double) q;
		if (q <= a && q >= 0) Pd = Math.round(((a - q) / b) * 100) / 100;
		else Pd = 0;
	}

	public double getP()
	{
		return Pd;
	}

	public double getQ()
	{
		return Qd;
	}

	public int getA()
	{
		return a;
	}

	public int getB()
	{
		return b;
	}

	public String toString()
	{
		return ("DFunction: Qd=" + a + "-" + b + "p, Qd=" + Qd + ", Pd=" + Pd);
	}

	@Override
	public int compareTo(someFamily otherFamily)
	{
		return (int) (otherFamily.getP() * 100 - this.getP() * 100);
	}
}

class someFirm implements Comparable<someFirm>
{
	private double Ps = 0;
	private double Qs = 0;
	private int a = 90;
	private int b = 15;

	someFirm()
	{

	}

	someFirm(double marketP)
	{
		this.setP(marketP);
	}

	someFirm(int A, int B)
	{
		if (A >= 1 && B > 0)
		{
			a = A;
			b = B;
			this.setP((a / b) + (Math.random() * 10 * a));
		}
		else
		{
			a = 1;
			b = 1;
			this.setP(1);
		}
	}

	public void setVars(int varA, int varB)
	{
		if (varA > 0 && varB > 0)
		{
			a = varA;
			b = varB;
		}
	}

	public void setP(double p)
	{
		Ps = (Math.ceil(p * 100)) / 100;
		if (Math.floor(b * p - a) >= 0) Qs = Math.floor(b * p - a);
		else Qs = 0;
	}

	public void setQ(int q)
	{
		Qs = (double) q;
		if (q >= 0) Ps = Math.round(((a + q) / b) * 100) / 100;
		else Ps = 0;
	}

	public double getP()
	{
		return Ps;
	}

	public double getQ()
	{
		return Qs;
	}

	public int getA()
	{
		return a;
	}

	public int getB()
	{
		return b;
	}

	public String toString()
	{
		return ("SFunction: Qs=-" + a + "+" + b + "p, Qs=" + Qs + ", Ps=" + Ps);
	}

	@Override
	public int compareTo(someFirm otherFirm)
	{
		return (int) (this.getP() * 100 - otherFirm.getP() * 100);
	}
}

public class ObjectInteractionTest
{

	static double aggDa = 0;
	static double aggDb = 0;
	static double aggSa = 0;
	static double aggSb = 0;

	// populates a vector of someFamily with randomly generated a, b values
	static Vector<someFamily> populateFamilyVector(int numFamilies,
			double maxValueA, double maxValueB)
	{
		Vector<someFamily> familyVector = new Vector<someFamily>(numFamilies);
		for (int i = 0; i <= numFamilies - 1; i++)
		{
			familyVector.addElement(new someFamily((int) Math.round(1
					+ Math.random() * maxValueA), (int) Math.round(1
					+ Math.random() * maxValueB)));
			aggDa = aggDa + familyVector.get(i).getA();
			aggDb = aggDb + familyVector.get(i).getB();
		}
		return familyVector;
	}

	// populates a vector of someFirm with randomly generated a, b values
	static Vector<someFirm> populateFirmVector(int numFirms, double maxValueA,
			double maxValueB)
	{
		Vector<someFirm> firmVector = new Vector<someFirm>(numFirms);
		for (int i = 0; i <= numFirms - 1; i++)
		{
			firmVector.addElement(new someFirm((int) Math.round(1
					+ Math.random() * (maxValueA - 1)), (int) Math.round(1
					+ Math.random() * (maxValueB - 1))));
			aggSa = aggSa + firmVector.get(i).getA();
			aggSb = aggSb + firmVector.get(i).getB();
		}
		return firmVector;
	}

	// populates the marketAsks array
	static double[][] populateMarketAsks(Vector<someFirm> firmVector,
			int marketSize)
	{
		double[][] asksArray = new double[marketSize][3];
		for (int i = 0; i <= marketSize - 1; i++)
		{
			asksArray[i][0] = firmVector.get(i).getP();
			asksArray[i][1] = firmVector.get(i).getQ();
			asksArray[i][2] = i;
		}
		Arrays.sort(asksArray, new java.util.Comparator<double[]>()
		{
			public int compare(double[] a, double[] b)
			{
				return Double.compare(a[0], b[0]);
			}
		});
		return asksArray;
	}

	// populates the marketBids array
	static double[][] populateMarketBids(Vector<someFamily> familyVector,
			int marketSize)
	{
		double[][] bidsArray = new double[marketSize][3];
		for (int i = 0; i <= marketSize - 1; i++)
		{
			bidsArray[i][0] = familyVector.get(i).getP();
			bidsArray[i][1] = familyVector.get(i).getQ();
			bidsArray[i][2] = i;
		}
		Arrays.sort(bidsArray, new java.util.Comparator<double[]>()
		{
			public int compare(double[] a, double[] b)
			{
				return Double.compare(b[0], a[0]);
			}
		});
		return bidsArray;
	}

	// creates CSV printout of total bid/ask Q by price level
	static void printCSV(int stop, int arraySize, double[][] array)
	{
		for (int i = 0; i <= stop; i++)
		{
			double Qtotal = 0;
			for (int j = 0; j <= arraySize - 1; j++)
			{
				if (Math.floor(array[j][0]) == i)
				{
					Qtotal = Qtotal + array[j][1];
				}
			}
			System.out.println(i + "," + Qtotal);
		}
	}

	public static void main(String args[]) throws IOException
	{
		final int H_LIM_A = 50;
		final int H_LIM_B = 10;
		final int TOTAL_FAMILIES = 20000;
		final int F_LIM_A = 64;
		final int F_LIM_B = 16;
		final int TOTAL_FIRMS = 200;

		Vector<someFirm> allFirms = populateFirmVector(TOTAL_FIRMS, F_LIM_A,
				F_LIM_B);
		Vector<someFamily> allFamilies = populateFamilyVector(TOTAL_FAMILIES,
				H_LIM_A, H_LIM_B);

		// prints all Family objects
		// for (int i=0;i<=TOTAL_FAMILIES-1;i++)
		// System.out.println(allFamilies.get(i).toString());

		// prints all Firm objects
		// for (int i=0;i<=TOTAL_FIRMS-1;i++)
		// System.out.println(allFirms.get(i).toString());

		double[][] marketBids = populateMarketBids(allFamilies, TOTAL_FAMILIES);
		double[][] marketAsks = populateMarketAsks(allFirms, TOTAL_FIRMS);

		// System.out.println("Aggregate D Function: "+aggDa+"+"+aggDb+"p");
		// System.out.println("Aggregate S Function: "+aggSa+"+"+aggSb+"p");

		// printCSV (100, TOTAL_FAMILIES, marketBids);
		// for (int i=0;i<=TOTAL_FAMILIES-1;i++)
		// System.out.println(marketAsks[i][0]+", "+marketAsks[i][1]+", "+marketAsks[i][2]);
		// for (int i=0;i<=TOTAL_FIRMS-1;i++)
		// System.out.println(marketBids[i][0]+", "+marketBids[i][1]+", "+marketBids[i][2]);

		// market purchasing mechanism
		int j = 0;
		for (int i = 0; i <= TOTAL_FAMILIES - 1; i++)
		{
			if (marketBids[i][0] >= marketAsks[j][0])
			{
				if (marketBids[i][1] == marketAsks[j][1])
				{
					marketAsks[j][0] = 0;
					marketAsks[j][1] = 0;
					j++;
				}
				else if (marketBids[i][1] > marketAsks[j][1])
				{
					double temp = marketBids[i][1];
					while (temp - marketAsks[j][1] >= 0)
					{
						temp = temp - marketAsks[j][1];
						marketAsks[j][0] = 0;
						marketAsks[j][1] = 0;
						j++;
					}
					marketAsks[j][1] = marketAsks[j][1] - marketBids[i][1];
				}
				else if (marketBids[i][1] < marketAsks[j][1])
				{
					marketAsks[j][1] = marketAsks[j][1] - marketBids[i][1];
				}
			}
			else
			{
				System.out.println("Price: " + marketAsks[j][0]);
				break;
			}
		}
	}
}
