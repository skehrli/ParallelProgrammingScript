class Stack {
    public Long pop() {
        Node head, next;
        do{
            head = top.get();
            if(head == null) return null;
            next = head.next;
        } while(!top.compareAndSet(head,next));
        return head.item;
    }    

    public void push(Long item){
        Node newNode = new Node(item);
        Node head;
        do {
            head = top.get();
            newNode.next = head;
        } while(!top.compareAndSet(head, newNode));
    }

    
}

