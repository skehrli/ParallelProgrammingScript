

public class Kitchen {
    public void getIngredients(Ingredient... ingredients){
        for(Ingredient i : ingredients){
            i.lock.lock();
        }
    } 
    public void releaseIngredients(Ingredient... ingredients){
        for(Ingredient i : ingredients){
            i.lock.unlock()
        }
    }
}

public class Ingredient {
    public final String name;
    public final Lock lock = new ReentrantLock();
}


