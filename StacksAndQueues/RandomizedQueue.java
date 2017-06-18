import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Mariano on 18/6/17.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {


    protected int size;
    protected int last;
    protected Item [] items;


    private class RandomizedQueueIterator implements Iterator<Item>{

        private int index;
        private Item[] randomArray;

        public RandomizedQueueIterator(){
            this.randomArray = items.clone();
            StdRandom.shuffle(randomArray,0,size);
            this.index = 0;
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



    public RandomizedQueue(){
        this.size = 0;
        this.items =(Item[]) new Object [10];
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

        if(this.isEmpty()){
            this.last = 0;
            this.items[this.last] = item;
        }else{
            this.last++;
            this.items[this.last] = item;
            if(this.size==this.items.length){
                Item[] aux = this.items;
                this.items = (Item[]) new Object[this.items.length*2];
                for(int i=0;i<aux.length;i++){
                    this.items[i] = aux[i];
                }
            }

        }

        this.size++;

    }

    public Item dequeue(){
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't deque from empty queue");
        }
        int randomIndex = StdRandom.uniform(this.size-1);
        if(randomIndex==this.last){
            this.last--;
        }
        Item item = this.items[randomIndex];
        this.size--;
        if(this.size==this.items.length/4){
            Item[] aux = this.items;
            this.items = (Item[]) new Object[this.items.length/2];
            for(int i=0;i<aux.length;i++){
                this.items[i] = aux[i];
            }
        }
        return item;
    }

    public Item sample() {
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't show items from empty queue");
        }
        int randomIndex = StdRandom.uniform(this.size-1);
        Item item = this.items[randomIndex];
        return item;


    }
    public Iterator<Item> iterator(){
        return new RandomizedQueueIterator();
    }
    public static void main(String[] args){
        RandomizedQueue<Integer> randomQueue = new RandomizedQueue<>();
        randomQueue.enqueue(1);
        randomQueue.enqueue(2);
        randomQueue.enqueue(3);
        randomQueue.enqueue(4);
        for(Integer value: randomQueue){
            System.out.println(value);
        }


    }
}
