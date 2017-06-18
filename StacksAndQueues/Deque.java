import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Mariano on 18/6/17.
 */
public class Deque<Item> implements Iterable<Item>{

    private Node<Item> first;
    private Node<Item> last;
    private Iterator<Item> iterator;
    private int size;

    private class Node<Item> {
        private Node<Item> next;
        private Node<Item> previous;
        private Item value;


        public Node(Item value){
            this.next = next;
        }
    }

    private class DequeIterator implements Iterator<Item>{

        private Node<Item> current = getFirst();

        @Override
        public boolean hasNext() {
            return current==null;
        }

        @Override
        public Item next() {
            if(current==null){
                throw new NoSuchElementException("The deque is empty");
            }
            Item item = current.value;
            this.current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("The remove operation is not supported");
        }
    }

    public Deque(){
         this.size = 0;
         this.iterator = new DequeIterator();
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    public int size() {
        return this.size;
    }
    public void addFirst(Item item) {
        if(item==null){
            throw new NullPointerException("You can't add a null value");
        }
        Node<Item> newItem;
        if(this.isEmpty()){
            newItem = new Node<Item>(item);
            this.first = newItem;
            this.last = newItem;
        }else{
            Node<Item> aux = this.first;
            newItem = new Node<Item>(item);
            newItem.next = aux;
            aux.previous = newItem;
            this.first = newItem;
        }
        this.size++;
    }
    public void addLast(Item item) {
        if(item==null){
            throw new NullPointerException("You can't add a null value");
        }
        Node<Item> newItem;
        if(this.isEmpty()){
            newItem = new Node<Item>(item);
            this.first = newItem;
            this.last = newItem;
        }else{
            Node<Item> aux = this.last;
            newItem = new Node<Item>(item);
            aux.next = newItem;
            newItem.previous = aux;
            this.last = newItem;
        }
        this.size++;
    }
    public Item removeFirst() {
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't remove an item from empty deque");
        }
        Item value;
        if(this.size==1){
            value = this.first.value;
            this.first = null;
            this.last = null;
        }else{
            Node<Item> aux = this.first.next;
            aux.previous = null;
            value = this.first.value;
            this.first = aux;
        }
        this.size--;
        return value;
    }
    public Item removeLast() {
        if(this.isEmpty()){
            throw new NoSuchElementException("You can't remove an item from empty deque");
        }

        Item value;
        if(this.size==1){
            value = this.first.value;
            this.first = null;
            this.last = null;
        }else{
            Node<Item> aux = this.last.previous;
            aux.next = null;
            value = this.last.value;
            this.last = aux;
        }
        this.size--;
        return value;
    }

    protected Node<Item> getFirst(){
        return this.first;
    }

    public Iterator<Item> iterator(){
        return this.iterator;
    }

}
