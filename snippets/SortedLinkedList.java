import java.util.concurrent.locks.ReentrantLock;

public class SortedLinkedList<T> {
    private Node head;
    public SortedLinkedList(){
        head = new Node(Integer.MIN_VALUE);
        head.next = new Node(Integer.MAX_VALUE);
    }
    public boolean remove(T item){
        int key = item.hashCode();
        while(true){
            Node pred = head;
            Node curr = pred.next;
            while(curr.key < key){
                pred = curr;
                curr = curr.next;
            }
            pred.lock(); curr.lock();
            try{
                if(validate(pred, curr)){
                    if(key == curr.key){
                        // logically remove
                        curr.marked = true;
                        // physically remove
                        pred.next = curr.next;
                        return true;
                    } else {
                        return false;
                    }
                }
            } finally {
                pred.unlock(); curr.unlock();
            }
        }
    }   
    public boolean validate(Node pred, Node curr){
        // Both reachable and local state as expected
        return !pred.marked && !curr.marked && pred.next == curr;
    }
    public boolean contains(T item){
        int key = item.hashCode();
        Node curr = head;
        while(curr.key < key){
            curr = curr.next;
        }
        return curr.key == key && !curr.marked;
    }
}

