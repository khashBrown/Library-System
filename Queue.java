public class Queue <E> 
{
    private LinkedList <E> list;
    public Queue() {list = new LinkedList<E>();}
    
    public void enqueue(E data) {
        list.addLast(data);
    }
    
    public E dequeue() {
        return list.removeFirst();
    }
    
    public E getFront() {
        return list.getFirst();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
