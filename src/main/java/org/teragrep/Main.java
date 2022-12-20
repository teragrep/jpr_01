package org.teragrep;

import java.util.Map;

/*This is the test module for testing the pcre-regex library from Java.
* The functions called here will represent the functions that are available to use in the aforementioned library.*/
public class Main {
    public static void main(String[] args) {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
//        s1.pcre2_versioncheck();
//        s1.compile_options.JPCRE2_ANCHORED = true;

        //s1.pcre2_compile_java("From:([^@]+)@([^\r]+)", 0);
        s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        //s1.pcre2_compile_java("From:([^@]+)@(?<sposti>[^\r]+)", 0);
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;


        // function for getting all match groups at once.
        boolean matchfound = true;
        int laskuri = 1;
        while (matchfound) {
            s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                                      "From:exddd@43434.com\r\n" +
                                      "From:7853456@exgem.com\r\n", s1.offset);
            a = 0;
            System.out.print("Match group "+laskuri+":\n");
            laskuri += 1;
            for(Map.Entry<Integer,String>it:s1.match_table.entrySet()) {
                System.out.print(a+": "+it.getValue()+"\n");
                a += 1;
            }
            if (a==0) {
                matchfound = false;
                System.out.print("No match!\n");
            }else{
                if (s1.name_table.size() > 0) {
                    System.out.print("named groups:\n");
                }
                for(Map.Entry<String,Integer>it:s1.name_table.entrySet()) {
                    System.out.println( it.getKey() + " which corresponds to substring " + it.getValue() );
                }
            }
        }

        // Make sure where the release of compile/match data from memory should be implemented which are presented below.
        // DON'T CALL THESE FUNCTIONS ANYWHERE ELSE AS LONG AS THEY ARE UNCOMMENTED RIGHT HERE!
        System.out.print("compile and match data cleanup!\n");
        //s1.pcre2_jmatch_free(); // match data is freed automatically when match has been completed.
        s1.pcre2_Jcompile_free(); // Compiled pattern data is not freed automatically as it is used multiple times by matching.
    }
}