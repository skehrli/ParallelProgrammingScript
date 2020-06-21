

class Flaky {
    private int turn;
    private boolean busy = false;
    public void lock(){
        int me = ThreadID.get();
        do {
            do {
                turn = me;
            } while(busy);
            busy = true;
        } while (turn != me);
    }    
    public void unlock(){
        busy = false;
    }
}