import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Mariano on 18/6/17.
 */
public class Permutation {

    public static void main(String [] args){
        Integer k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        while(!StdIn.isEmpty()){
            queue.enqueue(StdIn.readString());
        }
        for(int i=0; i<k; i++){
            StdOut.print(queue.dequeue());
            StdOut.print("\n");

        }

    }

}
