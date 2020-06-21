

public class BooleanFlags extends Thread {
    static int cnt = 0;
    static boolean flag = false;

    public void run(){
        for(int i = 0; i<1000; i++) {
            while(flag != false);
            flag = true;
            cnt++;
            flag = false;
        }
    }
}

