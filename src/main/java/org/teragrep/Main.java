package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import java.util.Map;

/*This is the test module for testing the pcre-regex library from Java.
* The functions called here will represent the functions that are available to use in the aforementioned library.*/
public class Main {
    public static void main(String[] args) {
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
//        s1.pcre2_versioncheck();
//        s1.compile_options.JPCRE2_ANCHORED = true;

        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)", 0);

        // function for getting a single match group
        Map<Integer, String> test;
        test = s1.pcre2_singlematch_java("From:regular.expressions@example.com\r\n"+"From:exddd@43434.com\r\n"+"From:7853456@exgem.com\r\n", 0);
        int a = 0;
        for(Map.Entry<Integer,String>it:test.entrySet()) {
            System.out.print(a+": "+it.getValue()+"\n");
            a++;
        }

        // function for getting all match groups at once.
        // TODO: make changes so all the substrings can be included with the mathcdata. ATM only the overall match is stored in the map (rv).
//        Map<Integer, String> rv;
//        rv = s1.pcre2_matchall_java("From:regular.expressions@example.com\r\n"+"From:exddd@43434.com\r\n"+"From:7853456@exgem.com\r\n", 0);
//        for(Map.Entry<Integer,String>it:rv.entrySet()) {
//            System.out.print(it.getValue()+", ");
//        }

//        System.out.println("rv ykkönen: " + rv.get(1));
//        System.out.println("rv kakkonen: " + rv.get(2));
//        System.out.println("rv kolmonen: " + rv.get(3));

        // TODO: check where the release of compile/match data from memory should be implemented. Separate Java functions for it are below.
        // DON'T CALL THESE FUNCTIONS ANYWHERE ELSE AS LONG AS THEY ARE UNCOMMENTED RIGHT HERE!
        s1.pcre2_jmatch_free();
        s1.pcre2_Jcompile_free();
    }
}