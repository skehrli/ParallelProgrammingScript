
class PetersonLock {
    private volatile boolean[] flag = new boolean[2];
    private volatile int victim;
    public void lock(int id){
        flag[id] = true;                     // 1.
        victim = id;                         // 2.
        while(flag[1-id] && victim == id) {} // 3.
    }
    public void unlock(int id){
        flag[id] = false;                    // 4.
    }
}

