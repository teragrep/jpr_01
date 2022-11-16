package org.teragrep;

import java.util.*;
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

public class JavaPcre {
    // Initializing the interface.
    // By making the interface this way you can call the external C-function in the Java-functions that are made inside this class.
    public interface Libpcre2demo extends Library {
        Libpcre2demo INSTANCE = (Libpcre2demo)Native.load("pcre2demo", Libpcre2demo.class);
        // Initialize options struct values as false (zero), as it is the default setting for pcre2_compile().
        @FieldOrder({ "JPCRE2_ANCHORED", "JPCRE2_ALLOW_EMPTY_CLASS", "JPCRE2_ALT_BSUX", "JPCRE2_ALT_CIRCUMFLEX", "JPCRE2_ALT_VERBNAMES", "JPCRE2_AUTO_CALLOUT", "JPCRE2_CASELESS", "JPCRE2_DOLLAR_ENDONLY", "JPCRE2_DOTALL", "JPCRE2_DUPNAMES", "JPCRE2_ENDANCHORED", "JPCRE2_EXTENDED", "JPCRE2_EXTENDED_MORE", "JPCRE2_FIRSTLINE", "JPCRE2_LITERAL", "JPCRE2_MATCH_INVALID_UTF", "JPCRE2_MATCH_UNSET_BACKREF", "JPCRE2_MULTILINE", "JPCRE2_NEVER_BACKSLASH_C", "JPCRE2_NEVER_UCP", "JPCRE2_NEVER_UTF", "JPCRE2_NO_AUTO_CAPTURE", "JPCRE2_NO_AUTO_POSSESS", "JPCRE2_NO_DOTSTAR_ANCHOR", "JPCRE2_NO_START_OPTIMIZE", "JPCRE2_NO_UTF_CHECK", "JPCRE2_UCP", "JPCRE2_UNGREEDY", "JPCRE2_USE_OFFSET_LIMIT", "JPCRE2_UTF" })
        class OptionsStruct extends Structure {
            public boolean JPCRE2_ANCHORED = false;
            public boolean JPCRE2_ALLOW_EMPTY_CLASS = false;
            public boolean JPCRE2_ALT_BSUX = false;
            public boolean JPCRE2_ALT_CIRCUMFLEX = false;
            public boolean JPCRE2_ALT_VERBNAMES = false;
            public boolean JPCRE2_AUTO_CALLOUT = false;
            public boolean JPCRE2_CASELESS = false;
            public boolean JPCRE2_DOLLAR_ENDONLY = false;
            public boolean JPCRE2_DOTALL = false;
            public boolean JPCRE2_DUPNAMES = false;
            public boolean JPCRE2_ENDANCHORED = false;
            public boolean JPCRE2_EXTENDED = false;
            public boolean JPCRE2_EXTENDED_MORE = false;
            public boolean JPCRE2_FIRSTLINE = false;
            public boolean JPCRE2_LITERAL = false;
            public boolean JPCRE2_MATCH_INVALID_UTF = false;
            public boolean JPCRE2_MATCH_UNSET_BACKREF = false;
            public boolean JPCRE2_MULTILINE = false;
            public boolean JPCRE2_NEVER_BACKSLASH_C = false;
            public boolean JPCRE2_NEVER_UCP = false;
            public boolean JPCRE2_NEVER_UTF = false;
            public boolean JPCRE2_NO_AUTO_CAPTURE = false;
            public boolean JPCRE2_NO_AUTO_POSSESS = false;
            public boolean JPCRE2_NO_DOTSTAR_ANCHOR = false;
            public boolean JPCRE2_NO_START_OPTIMIZE = false;
            public boolean JPCRE2_NO_UTF_CHECK = false;
            public boolean JPCRE2_UCP = false;
            public boolean JPCRE2_UNGREEDY = false;
            public boolean JPCRE2_USE_OFFSET_LIMIT = false;
            public boolean JPCRE2_UTF = false;
        }

        @FieldOrder({ "numVals", "vals", "ovector" })
        public static class RegexStruct extends Structure {
            public static class ByValue extends RegexStruct implements Structure.ByValue {}

            public int numVals;
            public Pointer vals; // char**
            public Pointer ovector;
        }
         RegexStruct.ByValue example_getStrings();

        //void example_cleanup(RegexStruct.ByValue sVal);
        void RegexStruct_cleanup(RegexStruct.ByValue sVal);

        Pointer pcre2_jcompile(String pattern, int i, OptionsStruct options); // returns pointer to compiled pattern re

        RegexStruct.ByValue pcre2_single_jmatch(String subject, Pointer re, int offset); // returns pointer to a single match data.

        RegexStruct.ByValue pcre2_all_jmatch(String subject, Pointer re); // returns pointer to an array of match data.

        void pcre2_jmatch(String subject, Pointer re, boolean findall);

        void printString(String myString);

        void pcre2_jcompile_free(Pointer re);

        void pcre2_versioncheck();
        void pcre2_jmatch_free(Pointer match_data);

    }

    // Add functions here

    String pattern;
    int pattern_size;
    int offset;
    String subject;
    Pointer re;
    Pointer match_data;
    boolean findall;
    Libpcre2demo.OptionsStruct compile_options;
    //Libpcre2demo.RegexStruct.ByValue regexstuff;

    JavaPcre(){
        compile_options = new Libpcre2demo.OptionsStruct(); // initializes pcre2_compile options with default values of PCRE2 library.
    }

    // regex struct testing starts here.
    public void testingregextoc(){
        Libpcre2demo.RegexStruct.ByValue regex_val = Libpcre2demo.INSTANCE.example_getStrings();
        System.out.println("example: retrieved " + regex_val.numVals + " values:");
        final String[] regex_vals = regex_val.vals.getStringArray(0, regex_val.numVals);
        for (int regexloop=0; regexloop<regex_val.numVals; regexloop++) {
            System.out.println("\t" + regex_vals[regexloop]);
        }
        System.out.println("\t(regex cleanup)");
        Libpcre2demo.INSTANCE.RegexStruct_cleanup(regex_val);
    }
    // regex struct testing ends here.

    public void printString(String myString){
        Libpcre2demo.INSTANCE.printString(myString);
    } // FOR TESTING

    public void pcre2_versioncheck(){
        Libpcre2demo.INSTANCE.pcre2_versioncheck();
    } // checks the installed PCRE library version for compatibility.

    public void pcre2_init_options(){
        compile_options = new Libpcre2demo.OptionsStruct();
    } // FOR TESTING

    public void pcre2_compile_java(String pat, int size){
        pattern = pat;
        pattern_size = size;
        re = Libpcre2demo.INSTANCE.pcre2_jcompile(pattern, pattern_size, compile_options);
    }

    // This is the main function for getting a single regex match group. This should be complete now, don't touch it apart from the return variables.
    public Map<Integer, String> pcre2_singlematch_java(String a, int b){
        subject = a;
        offset = b;
        Map<Integer, String> rv = new LinkedHashMap<>();
        int ind = 1;

        Libpcre2demo.RegexStruct.ByValue regex_val = Libpcre2demo.INSTANCE.pcre2_single_jmatch(subject, re, offset);
        final String[] regex_vals = regex_val.vals.getStringArray(0, regex_val.numVals);
        final int[] regex_ovector = regex_val.ovector.getIntArray(0, (regex_val.numVals + 2));
        System.out.println("Retrieved " + regex_val.numVals + " values:");
        if (regex_val.numVals == 0) {
            System.out.println("matching error or no match.");
        } else {
            // do the stuff you need to do here
            // TODO: change the function so that regex_vals can be returned to main function properly. Easy to do inside Java.
            System.out.println("\tMatch starts at: " + regex_ovector[0]);
            System.out.println("\tMatch ends at: " + regex_ovector[1]);
            for (int regexloop = 0; regexloop < regex_val.numVals; regexloop++) {
                System.out.println("\t" + regexloop + ": " + regex_vals[regexloop]);
                rv.put(ind++, regex_vals[regexloop]);
            }
            offset = regex_ovector[1];
        }
        System.out.println("\t(single regex cleanup)");
        Libpcre2demo.INSTANCE.RegexStruct_cleanup(regex_val);

        return rv;
    }

    // ABANDONED SOLUTION!
    // Add the functionality for matching ALL the groups from the subject, similar to how it is done in pcre2_singlematch_java() but in an array of structs.
    public Map<Integer, String> pcre2_matchall_java(String a, int b){
        subject = a;
        offset = b;
        Map<Integer, String> rv = new LinkedHashMap<>();
        int ind = 1;
        boolean matchfound = true;


        while (matchfound) {
            Libpcre2demo.RegexStruct.ByValue regex_val = Libpcre2demo.INSTANCE.pcre2_single_jmatch(subject, re, offset);
            final String[] regex_vals = regex_val.vals.getStringArray(0, regex_val.numVals);
            final int[] regex_ovector = regex_val.ovector.getIntArray(0, (regex_val.numVals + 2));
            System.out.println("Retrieved " + regex_val.numVals + " values:");
            if (regex_val.numVals == 0) {
                System.out.println("matching error or no match.");
                matchfound = false;
            } else {
                // do the stuff you need to do here
                // TODO: change the function so that regex_vals can be returned to main function properly. Easy to do inside Java.
                System.out.println("\tMatch starts at: " + regex_ovector[0]);
                System.out.println("\tMatch ends at: " + regex_ovector[1]);
                rv.put(ind++, regex_vals[0]);
                for (int regexloop = 0; regexloop < regex_val.numVals; regexloop++) {
                    System.out.println("\t" + regexloop + ": " + regex_vals[regexloop]);
                }
                offset = regex_ovector[1];
            }
            System.out.println("\t(single regex cleanup)");
            Libpcre2demo.INSTANCE.RegexStruct_cleanup(regex_val);
        }

        return rv;
    }

    public void pcre2_jmatch_free(){
        Libpcre2demo.INSTANCE.pcre2_jmatch_free(match_data);
    }

    public void pcre2_Jcompile_free(){
        Libpcre2demo.INSTANCE.pcre2_jcompile_free(re);
    }

}