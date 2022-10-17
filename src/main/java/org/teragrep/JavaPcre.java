package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;

public class JavaPcre {
    // Initializing the interface.
    // By making the interface this way you can call the external C-function in the Java-functions that are made inside this class.
    public interface Libpcre2demo extends Library {
        Libpcre2demo INSTANCE = (Libpcre2demo)Native.load("pcre2demo", Libpcre2demo.class);
        // TODO: write a structure for option parameters that can be passed to c. struct / structure which contains boolean values for ALL the options should do the trick.
        @FieldOrder({ "JPCRE2_ANCHORED", "JPCRE2_ALLOW_EMPTY_CLASS" })
        public static class OptionsStruct extends Structure {
            public int JPCRE2_ANCHORED, JPCRE2_ALLOW_EMPTY_CLASS;
//            public boolean JPCRE2_ALT_BSUX = false;
//            public boolean JPCRE2_ALT_CIRCUMFLEX = false;
//            public boolean JPCRE2_ALT_VERBNAMES = false;
//            public boolean JPCRE2_AUTO_CALLOUT = false;
//            public boolean JPCRE2_CASELESS = false;
//            public boolean JPCRE2_DOLLAR_ENDONLY = false;
//            public boolean JPCRE2_DOTALL = false;
//            public boolean JPCRE2_DUPNAMES = false;
//            public boolean JPCRE2_ENDANCHORED = false;
//            public boolean JPCRE2_EXTENDED = false;
//            public boolean JPCRE2_EXTENDED_MORE = false;
//            public boolean JPCRE2_FIRSTLINE = false;
//            public boolean JPCRE2_LITERAL = false;
//            public boolean JPCRE2_MATCH_INVALID_UTF = false;
//            public boolean JPCRE2_MATCH_UNSET_BACKREF = false;
//            public boolean JPCRE2_MULTILINE = false;
//            public boolean JPCRE2_NEVER_BACKSLASH_C = false;
//            public boolean JPCRE2_NEVER_UCP = false;
//            public boolean JPCRE2_NEVER_UTF = false;
//            public boolean JPCRE2_NO_AUTO_CAPTURE = false;
//            public boolean JPCRE2_NO_AUTO_POSSESS = false;
//            public boolean JPCRE2_NO_DOTSTAR_ANCHOR = false;
//            public boolean JPCRE2_NO_START_OPTIMIZE = false;
//            public boolean JPCRE2_NO_UTF_CHECK = false;
//            public boolean JPCRE2_UCP = false;
//            public boolean JPCRE2_UNGREEDY = false;
//            public boolean JPCRE2_USE_OFFSET_LIMIT = false;
//            public boolean JPCRE2_UTF = false;
        }
        OptionsStruct translate(OptionsStruct pt, int x, int y);
        public void pcre2_jcompile(String pattern, int i, OptionsStruct options);

        public void printString(String myString);

        public void pcre2_versioncheck();

    }
    // Add functions here

    public static void printString(String myString){
        Libpcre2demo.INSTANCE.printString(myString);
    }

    public static void pcre2_versioncheck(){
        Libpcre2demo.INSTANCE.pcre2_versioncheck();
    }

    public static void pcre2_compile_java(String pattern, int size){
        Libpcre2demo.OptionsStruct pt = new Libpcre2demo.OptionsStruct();
        pt.JPCRE2_ALLOW_EMPTY_CLASS = 0;
        pt.JPCRE2_ANCHORED = 1;

        Libpcre2demo.INSTANCE.pcre2_jcompile(pattern, 0, pt);
    }

}