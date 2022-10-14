package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;

public class JavaPcre {
    // Initializing the interface.
    // By making the interface this way you can call the external C-function in the Java-functions that are made inside this class.
    public interface pcre2demo extends Library {

        // TODO: write a structure for option parameters that can be passed to c. struct / structure which contains boolean values for ALL the options should do the trick.
        public static class OptionsStruct extends Structure {
            public static class ByReference extends OptionsStruct implements Structure.ByReference {}

            public boolean PCRE2_ANCHORED;
            public boolean PCRE2_ALLOW_EMPTY_CLASS;
            public boolean PCRE2_ALT_BSUX;
            public boolean PCRE2_ALT_CIRCUMFLEX;
            public boolean PCRE2_ALT_VERBNAMES;
            public boolean PCRE2_AUTO_CALLOUT;
            public boolean PCRE2_CASELESS;
            public boolean PCRE2_DOLLAR_ENDONLY;
            public boolean PCRE2_DOTALL;
            public boolean PCRE2_DUPNAMES;
            public boolean PCRE2_ENDANCHORED;
            public boolean PCRE2_EXTENDED;
            public boolean PCRE2_EXTENDED_MORE;
            public boolean PCRE2_FIRSTLINE;
            public boolean PCRE2_LITERAL;
            public boolean PCRE2_MATCH_INVALID_UTF;
            public boolean PCRE2_MATCH_UNSET_BACKREF;
            public boolean PCRE2_MULTILINE;
            public boolean PCRE2_NEVER_BACKSLASH_C;
            public boolean PCRE2_NEVER_UCP;
            public boolean PCRE2_NEVER_UTF;
            public boolean PCRE2_NO_AUTO_CAPTURE;
            public boolean PCRE2_NO_AUTO_POSSESS;
            public boolean PCRE2_NO_DOTSTAR_ANCHOR;
            public boolean PCRE2_NO_START_OPTIMIZE;
            public boolean PCRE2_NO_UTF_CHECK;
            public boolean PCRE2_UCP;
            public boolean PCRE2_UNGREEDY;
            public boolean PCRE2_USE_OFFSET_LIMIT;
            public boolean PCRE2_UTF;
        }
        // unless otherwise specified, ByReference is assumed - but it can't hurt to be explicit
        public void example3_sendStruct(OptionsStruct.ByReference sval);


        pcre2demo INSTANCE = (pcre2demo)
                Native.load("pcre2demo", pcre2demo.class);

        public void pcre2_jcompile(String pattern, int i);

        public void printString(String myString);

        public void pcre2_versioncheck();
    }
    // Add functions here
    final pcre2demo.OptionsStruct.ByReference e3ref = new pcre2demo.OptionsStruct.ByReference();

    public static void printString(String myString){
        pcre2demo.INSTANCE.printString(myString);
    }

    public static void pcre2_versioncheck(){
        pcre2demo.INSTANCE.pcre2_versioncheck();
    }

    public static void pcre2_compile_java(String pattern, int size){
        pcre2demo.INSTANCE.pcre2_jcompile(pattern, 0);
    }

}