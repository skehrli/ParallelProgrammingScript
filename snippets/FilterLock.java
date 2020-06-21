
class FilterLock{
    volatile int[] level;
    volatile int[] victim;
    volatile int n;
    public FilterLock(int n){
        this.n = n;
        level = new int[n];                           // (1)
        victim = new int[n];                          // (2)
        for(int i = 0; i < n; i++){
            level[i] = 0;
        }
    }
    boolean Others(int id, int lev){
        for(int k = 0; k < n; k++){
            if(k != id && level[k] >= lev) return true;
        }
        return false;
    }
    public void lock(int id) {
        for(int i = 1; i < n; i++){                   // (3)
            level[id] = i;
            victim[i] = id;
            while(Others(id, i) && victim[i] == id){} // (4)
        }
    }
    public void unlock(int id){
        level[id] = 0;
    }
}

