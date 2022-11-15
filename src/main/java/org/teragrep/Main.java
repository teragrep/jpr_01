package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/*This is the test module for testing the pcre-regex library from Java.
* The functions called here will represent the functions that are available to use in the aforementioned library.*/
public class Main {
    public static void main(String[] args) {
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
//        s1.pcre2_versioncheck();
//        s1.compile_options.JPCRE2_ANCHORED = true;

        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)", 0);
        // function for getting a single match group
        //s1.pcre2_singlematch_java("From:regular.expressions@example.com\r\n"+"From:exddd@43434.com\r\n"+"From:7853456@exgem.com\r\n", 0);
        s1.pcre2_matchall_java("From:regular.expressions@example.com\r\n"+"From:exddd@43434.com\r\n"+"From:7853456@exgem.com\r\n", 0);


        // function for getting all the match groups.
        //s1.pcre2_matchall_java("From:regular.expressions@example.com\r\n"+"From:exddd@43434.com\r\n"+"From:7853456@exgem.com\r\n");

//        s1.testingregextoc();


        // TODO: check where the release of compile/match data from memory should be implemented. Separate Java functions for it are below.
        // DON'T CALL THESE FUNCTIONS ANYWHERE ELSE AS LONG AS THEY ARE UNCOMMENTED RIGHT HERE!
        s1.pcre2_jmatch_free();
        s1.pcre2_Jcompile_free();
    }
}