

public class BoundedStackSTM<T> {
    private final Ref.View<Integer> count = STM.newRef(0);
    private TArray.View<T> items;
    public BoundedStackSTM(int capacity){
        items = STM.newTArray(capacity);
    }    
    public void push(final T val){
        STM.atomic(new Runnable(){
            public void run(){
                if(count.get() == items.length())
                    STM.retry();
                items.update(count.get(), val);
                STM.increment(count, 1);
            }
        });
    }    
    public T pop(){
        return STM.atomic(new Callable<T>(){
            public T call(){
                if(count.get() == 0)
                    STM.retry();
                T item = items.refViews().apply(count.get()).get();
                items.update(count.get(), null);
                STM.increment(count,-1);
                return item;
            }
        });
    }
}

