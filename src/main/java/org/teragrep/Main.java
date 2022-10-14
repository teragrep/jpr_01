package org.teragrep;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class Main {
    public static void main(String[] args) {
        //JavaPcre.printString("cat|dog the cat sat on the mat");
        int g = 0;
        JavaPcre.pcre2_compile_java("From:([^@]+)@([^\r]+)", g);
    }
}