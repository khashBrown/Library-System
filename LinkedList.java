/***
Coder: Roslan S, UiTM Pahang, roslancs@uitm.edu.my
Year: 2012
***/

public class LinkedList<E> {
   
    private Node<E> head, current, tail;    
    
    public LinkedList() {
        head = current = tail = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }    
    
    public void addFirst(E data) {
        Node newNode = new Node(data);
        newNode.next = this.head;
        this.head = newNode;          
        if(this.tail == null) {
            this.tail = this.head;
        }
    }
    
    public void addLast(E data) {
        Node newNode = new Node(data);
        
        if(this.tail == null) {
            this.head = this.tail = newNode;
        }
        else {
            this.tail.next = newNode;
            this.tail = this.tail.next;
        }       
    }
     
    public E getFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            this.current = this.head;
            return this.current.data;
        }
    }
    
    public E getLast() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            return this.tail.data;
        }
    }
    
    public E getNext() {
        if (this.current == this.tail) {
            return null;
        }
        else {
            this.current = this.current.next;
            return this.current.data;
        }
    }

    public void clear() {
        this.head = this.current = this.tail = null;

    }

    public boolean contains(E data) {
        boolean isContain = false;
        this.current = this.head;
        
        while (this.current != null) {
            if (data.equals(this.current.data)) {
                isContain = true;
                break;
            }
        }
 
        return isContain;
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            this.current = this.head;
            this.head = this.head.next;            
            if (this.head == null)
                this.tail = null;
            return current.data;
        }
    }
        
    public E removeLast() {
        if (this.isEmpty()) 
            return null;
        else if (this.head == this.tail) {
            this.current = this.head;
            this.head = this.tail = null;           
            return current.data;            
        }
        else {
            this.current = this.head;
            while (this.current.next != tail) {
                this.current = this.current.next;                
            }      
            Node<E> temp = this.tail;
            this.tail = this.current;
            this.tail.next = null;            
            return temp.data;
        }
    }
    
    public E removeAfter(E data) {        
        if (this.isEmpty()) {
            return null;
        }
        else if (this.head == this.tail) {
            this.current = this.head;
            this.head = this.tail = null;           
            return current.data;            

        }
        else {
            Node<E> previous = this.head;            
            while (previous.next != null) {
                if (data.equals(previous.data))
                {
                    break;
                }
                previous = previous.next;
            }            
            current = previous.next;
            previous.next = current.next;          
            return current.data;
        }
    }
    
    public String toString() {
        StringBuilder result = new StringBuilder("[");        
        if (this.isEmpty()) {
            result.append("The list is empty]");
        }
        else {
            this.current = this.head;
            while (this.current != null) {
                result.append(this.current.data);
                this.current = this.current.next;
                if (this.current != null)
                    result.append(", ");
                else
                    result.append("]");                
            }            
        }
        return result.toString();
    }
}