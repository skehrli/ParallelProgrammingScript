

public class MysteryStack<T> {
    AtomicReference<Node> top = new AtomicReference<Node>(null);
    static final int MIN_DELAY = 1;
    static final int MAX_DELAY = 42;
    Backoff backoff = new Backoff(MIN_DELAY,MAX_DELAY);

    protected boolean tryPush(Node node){
        Node oldtop = top.get();
        node.next = oldtop;
        return top.compareAndSet(oldtop, node);
    }
    public void push(T value){
        Node node = new Node(value);
        while(true){
            if(tryPush(node)){
                return;
            } else {
                backoff.backoff();
            }
        }
    }
}