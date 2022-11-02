package org.teragrep;

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

        @FieldOrder({ "numVals", "vals" })
        public static class RegexStruct extends Structure {
            public static class ByValue extends RegexStruct implements Structure.ByValue {}

            public int numVals;
            public Pointer vals; // char**
        }
        RegexStruct.ByValue example_getStrings();

        void example_cleanup(RegexStruct.ByValue sVal);

        Pointer pcre2_jcompile(String pattern, int i, OptionsStruct options); // returns pointer to compiled pattern re

        Pointer pcre2_jmatch2(String subject, Pointer re); // returns pointer to match data.

        void pcre2_jmatch(String subject, Pointer re, boolean findall);

        void printString(String myString);

        void pcre2_jcompile_free(Pointer re);

        void pcre2_versioncheck();
        void pcre2_jmatch_free(Pointer match_data);

    }

    // Add functions here

    String pattern;
    int pattern_size;
    String subject;
    Pointer re;
    Pointer match_data;
    boolean findall;
    Libpcre2demo.OptionsStruct compile_options;
    //Libpcre2demo.RegexStruct.ByValue regexstuff;

    JavaPcre(){
        compile_options = new Libpcre2demo.OptionsStruct(); // initializes pcre2_compile options with default values of PCRE2 library.
    }
    // TODO: regex struct testing starts here.
    public void testingregextoc(){
        Libpcre2demo.RegexStruct.ByValue regex_val = Libpcre2demo.INSTANCE.example_getStrings();
        System.out.println("example: retrieved " + regex_val.numVals + " values:");
        // getStringArray copies the contents of the C-allocated memory buffer into a Java-managed String[]
        final String[] regex_vals = regex_val.vals.getStringArray(0, regex_val.numVals);
        for (int regexloop=0; regexloop<regex_val.numVals; regexloop++) {
            System.out.println("\t" + regex_vals[regexloop]);
        }
        System.out.println("\t(regex cleanup)");
        Libpcre2demo.INSTANCE.example_cleanup(regex_val);
    }
    // TODO: regex struct testing ends here.

    public void printString(String myString){
        Libpcre2demo.INSTANCE.printString(myString);
    } // FOR TESTING

    public void pcre2_versioncheck(){
        Libpcre2demo.INSTANCE.pcre2_versioncheck();
    }

    public void pcre2_init_options(){
        compile_options = new Libpcre2demo.OptionsStruct();
    } // FOR TESTING

    public void pcre2_compile_java(String pat, int size){
        pattern = pat;
        pattern_size = size;
        re = Libpcre2demo.INSTANCE.pcre2_jcompile(pattern, pattern_size, compile_options);
    }

    public void pcre2_match_java(String a, boolean c){
        subject = a;
        findall = c;

        //Libpcre2demo.INSTANCE.pcre2_jmatch(subject, re, findall);

        match_data = Libpcre2demo.INSTANCE.pcre2_jmatch2(subject, re);
    }

    public void pcre2_jmatch_free(){
        Libpcre2demo.INSTANCE.pcre2_jmatch_free(match_data);
    }

    public void pcre2_Jcompile_free(){
        Libpcre2demo.INSTANCE.pcre2_jcompile_free(re);
    }

}