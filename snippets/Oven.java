public class Oven {
    public void putInCasserole(){

        while(true){
            if(ovenIsEmpty()) {
                putCasseroleInOven();
                break;
            }
        }


    }    
}