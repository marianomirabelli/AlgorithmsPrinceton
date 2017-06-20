import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Mariano on 18/6/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {


     private int size;
     private Item [] items;


    private class RandomizedQueueIterator implements Iterator<Item>{

        private int index;
        private Item[] randomArray;

        public RandomizedQueueIterator(){
            this.randomArray = items.clone();
            this.index = 0;
            if(size>0){
                StdRandom.shuffle(randomArray,0,size);
            }

        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove operation is not supported");
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Item next() {
            if(index >= size){
                throw new NoSuchElementException("There are not more elements to iterate");
            }
            Item item = this.randomArray[index];
            index++;
            return item;
        }
    }


    @SuppressWarnings("unchecked")
    public RandomizedQueue(){
        this.size = 0;
        this.items =(Item[]) new Object [1];
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public int size(){
        return size;
    }

    public void enqueue(Item item){
        if(item==null){
            throw new NullPointerException("You can't add null elements");
        }
        this.items[this.size] = item;
        this.size+=1;
        if(this.size>=this.items.length-1){
          resize(this.items.length*2);
        }
    }

    public Item dequeue(){
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't deque from empty queue");
        }
       int randomIndex = StdRandom.uniform(this.size);
       Item item = this.items[randomIndex];
       this.size-=1;
       this.items[randomIndex] = this.items[size];
       this.items[this.size]=null;
        if(this.size<=this.items.length/4){
            resize(this.items.length/2);

        }
       return item;


    }

    public Item sample() {
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't show items from empty queue");
        }
        int randomIndex = StdRandom.uniform(this.size);
        Item item = this.items[randomIndex];
        return item;


    }
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }

    @SuppressWarnings("unchecked")
    private void resize(int N){
        Item[] aux = this.items;
        this.items = (Item[]) new Object[N];
        for(int i=0;i<this.size;i++){
            this.items[i] = aux[i];
        }

    }


    public static void main(String[] args){
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        int index = 0;
        while(index<15){
            randomQueue.enqueue(new Integer(index));
            index++;
        }

        for(Integer value : randomQueue){
            System.out.println(randomQueue.sample());
        }

        System.out.println("<---------------------------------->");
       index = 0;
        while(index<15){
            System.out.println(randomQueue.dequeue());
            index++;
        }


    }
}
