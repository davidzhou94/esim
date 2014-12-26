package esim.preliminary;

import java.io.*;

public class SimplestModel {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String rl = "y";
        double p = 0;
        double qS;
        double qD;

        while (!rl.equals("n")) {

            System.out.print("Market Price: ");

            try {
                rl = br.readLine();
                p = Double.parseDouble(rl);
            } catch (IOException ioe) {
                System.out.println("input error");
                System.exit(1);
            }

            qS = 1460 + 125 * p;
            qD = 2900 - 115 * p;
            System.out.println("qs: " + qS + ", qd: " + qD);

            if (qS == qD) System.out.println("equilibrium price!");
            else if (qS < qD) System.out.println("shortage!");
            else if (qS > qD) System.out.println("surplus!");

            System.out.print("Continue? ");
            rl = br.readLine();
        }
    }
}
