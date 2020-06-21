

public class CircularBufferSTM<T> {
    private final Ref.View<Integer> count = STM.newRef(0);
    private final Ref.View<Integer> tail = STM.newRef(0);
    private final Ref.View<Integer> head = STM.newRef(0);
    private TArray.View<T> items;
    public CircularBufferSTM(int capacity){
        items = STM.newTArray(capacity);
    }
    public void enq(final T x){
        STM.atomic(new Runnable(){
            public void run(){
                if(count.get() == items.length())
                    STM.retry();
                items.update(tail.get(), x);
                tail.set((tail.get()+1) % items.length());
                STM.increment(count, 1);
            }
        });
    }
    public T deq(){
        return STM.atomic(new Callable<T>(){
            public T call(){
            if (count.get() == 0)
                STM.retry();
            T item = items.refViews().apply(head.get()).get();
            items.update(head.get(), null);
            head.set(next(head.get()));
            STM.increment(count, -1);
            return item;
            }
        });
    }
}


