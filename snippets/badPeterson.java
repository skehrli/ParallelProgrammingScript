public class badPeterson {
    
    boolean[] entryFlag = new boolean[2];
    
    public void someProcess(){
        doStuff();
        while(true){
            entryFlag[threadID.get()] = true;
            while(entryFlag[1-threadID.get()]){}
            critical_section();
            entryFlag[threadID.get()] = false;
        }
    }

}