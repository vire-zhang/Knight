package javabook;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class InfiniteRecurse {

    public String toString() {
        return "infiniteRecurse address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {
        List<InfiniteRecurse> v = new ArrayList<InfiniteRecurse>();
        for(int i = 0; i < 10; ++i) {
            v.add(new InfiniteRecurse());
        }
        System.out.println(v);

        byte[] il = {'7', '9', '8'};
        System.out.println(il);
        String ill = new String(il);
        System.out.println(ill);
        PrintStream out = System.err;
        out.println("tt");
    }
}
