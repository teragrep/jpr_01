package org.teragrep;

import org.junit.jupiter.api.Test;

import java.util.Map;

// import static org.junit.jupiter.api.Assertions.*;

class JavaPcreTest {

    @Test
    void pcre2_versioncheck_test() {
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_versioncheck();
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
        s1.pcre2_compile_java("From:(?<nimi>[^@]+@(?<sposti>[^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern failed.\n");
            System.out.print("Error is intended to be at pattern offset 35 in this test case, missing )-symbol in the pattern\n");
        }else
            s1.pcre2_Jcompile_free();
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
        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");
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