
class BakeryLock {
    volatile boolean[] flag;
    volatile int[] label;
    final int n;
    public BakeryLock(int n){
        this.n = n;
        flag = new boolean[n];
        label = new int[n];
    }
    boolean Conflict(int id){
        for(int i = 0; i < n; i++)
            if(i != id && flag[i]){
                int diff = label[i] - label[id];
                if(diff < 0 || diff == 0 && i < id)
                    return true;
            }
        return false;
    }
    public void lock(int id){
        flag[id] = true;
        // Find maximum label and increment by 1
        label[id] = label[0];
        for(int i = 1; i < n; i++){
            label[id] = Math.max(label[i],label[id]);
        }
        label[id]++;
        // Waiting section
        while(Conflict(id)){}
    }
    public void unlock(int id){
        flag[id] = false;
    }
}


