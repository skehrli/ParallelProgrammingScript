class Counter2 {
    int cnt = 0;
    public synchronized void incr(){
        cnt++;
    }
}