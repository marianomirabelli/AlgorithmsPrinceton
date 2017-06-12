import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;



/**
 * Created by Mariano on 3/6/17.
 */
public class PercolationStats {

    private double elements;
    private int trials;
    private double [] monteCarloResults;


    public PercolationStats(int n, int trials) {
        if(n<=0 || trials<=0){
            throw new IllegalArgumentException("The constructor arguments must be greater than 0");
        }
        this.elements = n*n;
        this.trials = trials;
        Percolation percolation;
        boolean isPercolates;
        this.monteCarloResults = new double[trials];
        for(int trialIndex=0;trialIndex<trials;trialIndex++){
            percolation = new Percolation(n);
            isPercolates = false;
            while(!isPercolates){
                int rowIndex = StdRandom.uniform(n) +1;
                int colIndex = StdRandom.uniform(n) +1;
                percolation.open(rowIndex,colIndex);
                isPercolates = percolation.percolates();
            }
            monteCarloResults[trialIndex] = percolation.numberOfOpenSites()/this.elements;
        }
    }

    public double mean(){
         return StdStats.mean(monteCarloResults);
    }

    public double stddev(){
        return StdStats.stddev(this.monteCarloResults);
    }

    public double confidenceLo(){
        return mean()-((1.96*stddev())/Math.sqrt(this.trials));
    }

    public double confidenceHi(){
        return mean()+((1.96*stddev())/Math.sqrt(this.trials));
    }

    public static void main(String [] args){
        if(args.length<2){
            throw new IllegalArgumentException("PercolationStats is waiting for two arguments, N and T");
        }
       try
       {
           int n = Integer.parseInt(args[0]);
           int t = Integer.parseInt(args[1]);
           PercolationStats stats = new PercolationStats(n,t);
           System.out.println("mean= " + stats.mean());
           System.out.println("stddev= " + stats.stddev());
           System.out.println("95% confidence interval " + "[ " + stats.confidenceHi() + "," +stats.confidenceLo()+"]");
       } catch (NumberFormatException ex){
           System.out.println("The parameters must be Integers");
       }

    }
}
