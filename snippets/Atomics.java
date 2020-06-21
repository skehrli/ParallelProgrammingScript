class Atomics {
    
    public boolean TAS(memref s){
        if(mem[s] == 0){
            mem[s] = 1;
            return true;
        } else
            return false;
    }    

    public int CAS(memref a, int exptected, int update){
        oldVal = mem[a];
        if(expected == oldVal)
            mem[a] = update;
        return oldVal;
    }


}

