package org.teragrep;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import static org.junit.jupiter.api.Assertions.*;

class JavaPcreTest {

    @Test
    void pcre2_versioncheck_test() {
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_versioncheck();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPcre.class);
    @Test
    void pcre2_slf4j_test() {
        LOGGER.error("Testing error");
        LOGGER.warn("Testing warn");
        LOGGER.info("Testing info");
        LOGGER.debug("Testing debug");
        LOGGER.trace("Testing trace");
    }

    @Test
    void pcre2_options_test() {
        JavaPcre s1 = new JavaPcre();
        s1.compile_options.JPCRE2_ANCHORED = true;
        s1.match_options.JPCRE2_ANCHORED = true;
        s1.compile_options.JPCRE2_ANCHORED = false;
        s1.match_options.JPCRE2_ANCHORED = false;
    }

    @Test
    void pcre2_extraoptions_test(){
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_gcontext_create();
        s1.pcre2_ccontext_create();
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;
        s1.pcre2_ccontext_set_extra_options();

        s1.pcre2_ccontext_free();
        s1.pcre2_gcontext_free();
    }

    @Test
    void pcre2_context_test() {
        JavaPcre s1 = new JavaPcre();
        if (s1.gcontext == null)
            System.out.print("gcontext null.\n");
        if (s1.ccontext == null)
            System.out.print("ccontext null.\n");
        if (s1.mcontext == null)
            System.out.print("mcontext null.\n");
        s1.pcre2_gcontext_create();
        s1.pcre2_ccontext_create();
        s1.pcre2_mcontext_create();
        if (s1.gcontext == null)
            System.out.print("gcontext null.\n");
        else
            System.out.print("gcontext not null.\n");
        if (s1.ccontext == null)
            System.out.print("ccontext null.\n");
        else
            System.out.print("ccontext not null.\n");
        if (s1.mcontext == null)
            System.out.print("mcontext null.\n");
        else
            System.out.print("mcontext not null.\n");

        s1.pcre2_mcontext_free();
        s1.pcre2_ccontext_free();
        s1.pcre2_gcontext_free();
        if (s1.gcontext == null)
            System.out.print("gcontext null.\n");
        if (s1.ccontext == null)
            System.out.print("ccontext null.\n");
        if (s1.mcontext == null)
            System.out.print("mcontext null.\n");
    }

    @Test
    void pcre2_compile_test() {
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }
        s1.pcre2_Jcompile_free();
        s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }else
            s1.pcre2_Jcompile_free();
        s1.pcre2_compile_java("From:([^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }else
            s1.pcre2_Jcompile_free();
        s1.pcre2_compile_java("");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }else
            s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_compile_failure_test() {
        JavaPcre s1 = new JavaPcre();
        try {
            s1.pcre2_compile_java("From:(?<nimi>[^@]+@(?<sposti>[^\r]+)");
        }catch (Exception e){
            System.out.println(e);
        }
        if (s1.re != null) {
            s1.pcre2_Jcompile_free();
        }
    }


    @Test
    void pcre2_match_failure_test() {
        JavaPcre s1 = new JavaPcre();
        try {
            s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        }catch (Exception e){
            System.out.println(e);
            return;
        }
        // Error trigger: The value of startoffset was greater than the length of the subject.
        s1.offset = 200;

        try {
            s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                    "From:exddd@43434.com\r\n" +
                    "From:7853456@exgem.com\r\n", s1.offset);
        }catch (Exception e){
            System.out.println(e);
            return;
        }

        if (s1.re != null) {
            s1.pcre2_Jcompile_free();
        }
    }

    @Test
    void pcre2_match_failure_test2() {
        JavaPcre s1 = new JavaPcre();
        s1.offset = 0;
        // Match pattern is not compiled to trigger an error

        try {
            s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                    "From:exddd@43434.com\r\n" +
                    "From:7853456@exgem.com\r\n", s1.offset);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Test
    void pcre2_match_failure_test3() {
        JavaPcre s1 = new JavaPcre();
        try {
            s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        }catch (Exception e){
            System.out.println(e);
            return;
        }
        s1.offset = 0;

        try {
            s1.pcre2_singlematch_java(null, s1.offset);
        }catch (Exception e){
            System.out.println(e);
            return;
        }

        if (s1.re != null) {
            s1.pcre2_Jcompile_free();
        }
    }

    @Test
    void pcre2_singlematch_test1() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler/match options and context at default values.
        s1.pcre2_gcontext_create();
        s1.pcre2_ccontext_create();
        s1.pcre2_mcontext_create();
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;
        s1.pcre2_ccontext_set_extra_options();
        try {
            s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");
        }catch (PatternSyntaxException e){
            System.out.println(e);
            return;
        }
        s1.offset = 0;

        // function for getting a single match group
        s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                                  "From:exddd@43434.com\r\n" +
                                  "From:7853456@exgem.com\r\n", s1.offset);
        a = 0;
        System.out.print("Match group:\n");
        for(Map.Entry<Integer,String>it:s1.match_table.entrySet()) {
            System.out.print(a+": "+it.getValue()+"\n");
            a += 1;
        }
        if (a==0) {
            System.out.print("No match!\n");
        }else{
            if (s1.name_table.size() > 0) {
                System.out.print("named groups:\n");
            }
            for(Map.Entry<String,Integer>it:s1.name_table.entrySet()) {
                System.out.println( it.getKey() + " which corresponds to substring " + it.getValue() );
            }
        }
        s1.pcre2_mcontext_free();
        s1.pcre2_ccontext_free();
        s1.pcre2_gcontext_free();
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_singlematch_2namedgroups_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;

        // function for getting a single match group
        s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                                  "From:exddd@43434.com\r\n" +
                                  "From:7853456@exgem.com\r\n", s1.offset);
        a = 0;
        System.out.print("Match group:\n");
        for(Map.Entry<Integer,String>it:s1.match_table.entrySet()) {
            System.out.print(a+": "+it.getValue()+"\n");
            a += 1;
        }
        if (a==0) {
            System.out.print("No match!\n");
        }else{
            if (s1.name_table.size() > 0) {
                System.out.print("named groups:\n");
            }
            for(Map.Entry<String,Integer>it:s1.name_table.entrySet()) {
                System.out.println( it.getKey() + " which corresponds to substring " + it.getValue() );
            }
        }
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_singlematch_1namedgroup_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.

        s1.pcre2_compile_java("From:([^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;

        // function for getting a single match group
        s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                                  "From:exddd@43434.com\r\n" +
                                  "From:7853456@exgem.com\r\n", s1.offset);
        a = 0;
        System.out.print("Match group:\n");
        for(Map.Entry<Integer,String>it:s1.match_table.entrySet()) {
            System.out.print(a+": "+it.getValue()+"\n");
            a += 1;
        }
        if (a==0) {
            System.out.print("No match!\n");
        }else{
            if (s1.name_table.size() > 0) {
                System.out.print("named groups:\n");
            }
            for(Map.Entry<String,Integer>it:s1.name_table.entrySet()) {
                System.out.println( it.getKey() + " which corresponds to substring " + it.getValue() );
            }
        }
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_singlematch_nomatch_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.

        s1.pcre2_compile_java("nomatch");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;

        // function for getting a single match group
        try {
            s1.pcre2_singlematch_java("From:regular.expression@example.com\r\n" +
                    "From:exddd@43434.com\r\n" +
                    "From:7853456@exgem.com\r\n", s1.offset);
        }catch (Exception e){
            System.out.println(e);
            return;
        }
        a = 0;
        System.out.print("Match group:\n");
        for(Map.Entry<Integer,String>it:s1.match_table.entrySet()) {
            System.out.print(a+": "+it.getValue()+"\n");
            a += 1;
        }
        if (a==0) {
            System.out.print("No match!\n");
        }else{
            if (s1.name_table.size() > 0) {
                System.out.print("named groups:\n");
            }
            for(Map.Entry<String,Integer>it:s1.name_table.entrySet()) {
                System.out.println( it.getKey() + " which corresponds to substring " + it.getValue() );
            }
        }
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_matchall_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;
        boolean matchfound = true;
        int laskuri = 1;
        while (matchfound) {
//            if (laskuri >1) {
//
//            }
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
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_matchall_clrf_test() {
        int a, utf8, crlf_is_newline;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.compile_options.JPCRE2_UTF = true;
        // TODO: change subject, pattern and options in a way that the recoverable crlf nomatch error can be triggered
        s1.pcre2_compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;
        boolean matchfound = true;
        int laskuri = 0;
        String subject = "From:regular.expression@example.com\r\n" + "From:exddd@43434.com\r\n" + "From:7853456@exgem.com\r\n";
        while (matchfound) {
            try {
                s1.pcre2_singlematch_java(subject, s1.offset);
            }catch (Exception e){
                System.out.println(e);
                boolean temp = s1.matchfound;
                break;
            }
            a = 0;
            System.out.print("\nMatch found: " + s1.matchfound + "\n");
            matchfound = s1.matchfound;
            if (matchfound) {
                laskuri += 1;

                // print match group data
                System.out.print("Match group: " + laskuri + "\n");
                for (Map.Entry<Integer, String> it : s1.match_table.entrySet()) {
                    System.out.print(a + ": " + it.getValue() + "\n");
                    a += 1;
                }

                // print named group data if available
                if (s1.name_table.size() > 0) {
                    System.out.print("named groups:\n");
                    for (Map.Entry<String, Integer> it : s1.name_table.entrySet()) {
                        System.out.println(it.getKey() + " which corresponds to substring " + it.getValue());
                    }
                }

            } else {
                if (s1.checkoptionzero()){break;} // no options set, can only mean all matches found
                if (laskuri > 1) {
                    s1.offset += 1; // advance one code unit
                    System.out.print("Checking concurrent match for utf8 and crlf nomatch error handling\n");
                    if (s1.pcre2_get_crlf_is_newline() &&
                        s1.offset < subject.length()-1 &&
                        subject.charAt(s1.offset) == '\r' &&
                        subject.charAt(s1.offset - 1) == '\n'){
                        s1.offset +=1;
                    }
                    else if (s1.pcre2_get_utf8()){
                        while (s1.offset < subject.length()) {
                            if (s1.check_utf8(subject.charAt(s1.offset))) {
                                break;
                            }else{
                                s1.offset += 1;
                            }
                        }
                    }
                }
                matchfound = true;
            }
        }
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_utf8andcrlf_test() {
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.

        boolean testi = s1.pcre2_get_utf8();
        boolean testi2 = s1.pcre2_get_crlf_is_newline();
    }
    @Test
    void pcre2_matchall_nomatch_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.pcre2_compile_java("nomatch");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
            return;
        }
        s1.offset = 0;
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
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_Jcompile_free() {
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }
        s1.pcre2_Jcompile_free();
    }
}