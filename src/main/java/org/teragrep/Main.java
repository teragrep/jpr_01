package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Main {
    public static void main(String[] args) {
        //JavaPcre.printString("cat|dog the cat sat on the mat");
        int g = 0;
        JavaPcre.Libpcre2demo.OptionsStruct pt = JavaPcre.pcre2_init_options();
        pt.JPCRE2_ANCHORED = true;
        pt.JPCRE2_ALLOW_EMPTY_CLASS = true;
        JavaPcre.pcre2_versioncheck();
        JavaPcre.pcre2_compile_java("From:([^@]+)@([^\r]+)", g, pt);
    }
}