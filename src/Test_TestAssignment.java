import java.lang.*;
import java.util.*;
import java.io.*;
public class Test_TestAssignment {
    public static void main(String[] args) {
        Integer b = new Integer(4);
        Integer[] testlist = {1, 2, b };
        Integer[] testList2 = Arrays.copyOf(testlist,3);
        Integer[] testclone= testlist.clone();
        testlist[0] = 10;
        Integer i = new Integer(15);
        testlist[1] = i;
        i = 45;
        b = 10;
        for(int x = 0; x<3;x++) {
            System.out.println(testlist[x]);
            System.out.println(testList2[x]);
            System.out.println(testclone[x]);
        }
    }
}
