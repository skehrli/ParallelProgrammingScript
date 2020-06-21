

class C {
    private int x = 0;
    private int y = 0;
    
    void f() {
        x = 1; // line A
        y = 1; // line B
    }
    void g() {
        int a = y; // line C
        int b = x; // line D
        assert(b >= a);
    }

    static <T> T peek(Stack<T> s){
        T ans = s.pop();
        s.push(ans);
        return ans;
    }

    static <T> T spaceholder(T x, T y){

        push(x);

        push(y);

        T e = pop();


        T ans = pop();
        
        push(ans);

        return ans;



    }

    
    
}

