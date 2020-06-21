import java.util.concurrent.atomic.AtomicReference;

import org.w3c.dom.Node;

public class NodePool {
    AtomicReference<Node> top = new AtomicReference<Node>();
    
    public Node get(Long item){
        Node head, next;
        do {
            head = top.get();
            if (head == null) return new Node(item);
            next = head.next;
        } while(!top.compareAndSet(head, next));
        head.item = item;
        return head;
    }

    public void put(Node n) {
        Node head;
        do {
            head = top.get();
            n.next = head;
        } while (!top.compareAndSet(head, n));
    }
}

