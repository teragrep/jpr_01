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

        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");

        try {
            s1.pcre2_Jcompile_free();
            s1.pcre2_mcontext_free();
            s1.pcre2_ccontext_free();
            s1.pcre2_gcontext_free();
            s1.pcre2_gcontext_free();
        } catch (Exception e){
            System.out.println(e);
        }
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
        // TODO: initializing any kind of string breaks error code translation:
        String aesadg;
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
        // TODO: initializing any kind of string breaks error code translation:
        String aesadg;
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

        // subject is null to trigger an error
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
                s1.offset = s1.ovector1;
            }
        }
        s1.pcre2_Jcompile_free();
    }

    @Test
    void pcre2_matchall_clrf_test() {
        int a;
        String subject = "From:regular.expression@example.com\r\n" + "From:exddd@43434.com\r\n" + "From:7853456@exgem.com\r\n";
        String pattern = "From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        JavaPcre s1 = new JavaPcre(); // also initializes all the compiler and matching options at default pcre2 values (all options disabled by default).

        // change compiler parameters before compiling:
        s1.compile_options.JPCRE2_UTF = true;                           // enable PCRE2_UTF option for compiling, always disabled by default.
        //s1.compile_options.JPCRE2_UTF = false;                          // disable PCRE2_UTF option for compiling
        s1.pattern_size = 0;                                            // default pattern size, value 0 will set the pcre2_compile length option to PCRE2_ZERO_TERMINATED.

        // Optional compiler parameters:
        s1.pcre2_gcontext_create();                                     // initializes general context parameter which is used for generating compiler/match context.
        s1.pcre2_ccontext_create();                                     // initializes compiler context parameter which can contain the extra options that don't fit inside compile_options.
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;      // enables PCRE2_EXTRA_ALLOW_LOOKAROUND_BSK extra_option parameter
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = false;     // disables PCRE2_EXTRA_ALLOW_LOOKAROUND_BSK extra_option parameter
        // etc.
        s1.pcre2_ccontext_set_extra_options();                          // inject the extra_options modifiers to the compiler context.


        // The compile function:
        try {
            s1.pcre2_compile_java(pattern);
        }catch (Exception e){
            System.out.println(e);
            return;
        }


        // change matching options and parameters before matching:
        s1.offset = 0;                              // offset at where to start the match.
        s1.match_options.JPCRE2_ANCHORED = true;    // enable PCRE2_ANCHORED option for matching
        s1.match_options.JPCRE2_ANCHORED = false;   // disable PCRE2_ANCHORED option for matching
        //etc.
        s1.pcre2_mcontext_create();                 // initializes match context, no real use at the moment.


        // a simple loop for getting all match groups at once:
        boolean matchfound = true;  // for allowing the initial run of matching
        int groupcounter = 0;            // simple counter for match group numbering.
        int previousoffset = 0;         // integer for storing the previous offset.

        while (matchfound) {
            if (groupcounter == 0) {
                // the matching function for first match:
                try {
                    s1.pcre2_singlematch_java(subject, s1.offset);
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
            }else{
                // the matching function for concurrent matches:
                previousoffset = s1.offset;
                s1.offset = s1.ovector1; /* Start at end of previous match */

                /* If the previous match was for an empty string, we are finished if we are
                at the end of the subject. Otherwise, arrange to run another match at the
                same point to see if a non-empty match can be found. */

                if (s1.ovector0 == s1.offset)
                {
                    if (s1.ovector0 == subject.length()) break;
                    s1.match_options.JPCRE2_NOTEMPTY_ATSTART = true;
                    s1.match_options.JPCRE2_ANCHORED = true;
                }
                /* If the previous match was not an empty string, there is one tricky case to
                consider. If a pattern contains \K within a lookbehind assertion at the
                start, the end of the matched string can be at the offset where the match
                started. Without special action, this leads to a loop that keeps on matching
                the same substring. We must detect this case and arrange to move the start on
                by one character. The pcre2_get_startchar() function returns the starting
                offset that was passed to pcre2_match(). */

                else
                {
                    int startchar = previousoffset;
                    if (s1.offset <= startchar)
                    {
                        if (startchar >= subject.length()) break;   /* Reached end of subject.   */
                        s1.offset = startchar + 1;                  /* Advance by one character. */
                        if (s1.pcre2_get_utf8())                    /* If UTF-8, it may be more  */
                        {                                           /*   than one code unit.     */
                            for (; s1.offset < subject.length(); s1.offset++)
                                if (s1.check_utf8(subject.charAt(s1.offset))) break;
                        }
                    }
                }


                try {
                    s1.pcre2_singlematch_java(subject, s1.offset);
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
            }

                /* This time, a result of NOMATCH isn't an error. If the value in "options"
                is zero, it just means we have found all possible matches, so the loop ends.
                Otherwise, it means we have failed to find a non-empty-string match at a
                point where there was a previous empty-string match. In this case, we do what
                Perl does: advance the matching position by one character, and continue. We
                do this by setting the "end of previous match" offset, because that is picked
                up at the top of the loop as the point at which to start again.

                There are two complications: (a) When CRLF is a valid newline sequence, and
                the current position is just before it, advance by an extra byte. (b)
                Otherwise we must ensure that we skip an entire UTF character if we are in
                UTF mode. */
            if (s1.JPCRE2_ERROR_NOMATCH){
                if (s1.checkoptionzero()){
                    break;
                }                       /* All matches found */
                if (groupcounter > 1) {                                      /* only check for (a) and (b) complications from concurrent matches */
                    s1.offset += 1;                                     /* Advance one code unit */
                    if (s1.pcre2_get_crlf_is_newline() &&               /* If CRLF is a newline & */
                            s1.offset < subject.length()-1 &&               /* we are at CRLF, */
                            subject.charAt(s1.offset) == '\r' &&
                            subject.charAt(s1.offset - 1) == '\n')
                    {s1.ovector1 +=1;}                                        /* Advance by one more. */
                    else if (s1.pcre2_get_utf8()){                            /* Otherwise, ensure we */
                        while (s1.ovector1 < subject.length()) {              /* advance a whole UTF-8 */
                            if (s1.check_utf8(subject.charAt(s1.ovector1))) { /* character. */
                                break;
                            }else{
                                s1.ovector1 += 1;
                            }
                        }
                    }
                    matchfound = true;
                    continue;
                }
            }
            a = 0;  /* simple counter for substring numbering */
            System.out.print("\nMatch found: " + s1.matchfound + "\n");
            matchfound = s1.matchfound;

            // when match is found:
            // print match group data
            groupcounter += 1;
            System.out.print("Match group: " + groupcounter + "\n");
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
        }

        System.out.print("\nAll matches found!");
        // Make sure to release compiled data from memory. Compiled pattern data is not freed automatically as it is used multiple times by matching.
        // Also remember to free all the optional initialized context data.
        s1.pcre2_mcontext_free();
        s1.pcre2_ccontext_free();
        s1.pcre2_gcontext_free();
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
                s1.offset = s1.ovector1;
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
        try {
            s1.pcre2_Jcompile_free();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    @Test
    void pcre2_Jcompile_free_error(){
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_compile_java("From:([^@]+)@([^\r]+)");
        if (s1.re == null){
            System.out.print("Error! Compiling of the match pattern ended up in error.\n");
//            return;
        }
        try {
            s1.pcre2_Jcompile_free();
        }catch(Exception e){
            System.out.println(e);
        }
        try {
            s1.pcre2_Jcompile_free();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}