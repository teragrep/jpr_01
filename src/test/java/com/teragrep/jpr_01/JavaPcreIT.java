package com.teragrep.jpr_01;

import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import static org.junit.jupiter.api.Assertions.*;

class JavaPcreIT {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPcreIT.class);
    
    @Test
    void pcre2_versioncheck_test() {
        JavaPcre s1 = new JavaPcre();
        s1.pcre2_versioncheck();
    }

    @Test
    void pcre2_options_test() {
        JavaPcre s1 = new JavaPcre();
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ANCHORED);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ALLOW_EMPTY_CLASS);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ALT_BSUX);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ALT_CIRCUMFLEX);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ALT_VERBNAMES);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_AUTO_CALLOUT);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_CASELESS);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_DOLLAR_ENDONLY);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_DOTALL);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_DUPNAMES);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ENDANCHORED);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_EXTENDED);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_EXTENDED_MORE);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_FIRSTLINE);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_LITERAL);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_MATCH_INVALID_UTF);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_MATCH_UNSET_BACKREF);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_MULTILINE);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NEVER_BACKSLASH_C);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NEVER_UCP);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NEVER_UTF);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NO_AUTO_CAPTURE);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NO_AUTO_POSSESS);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NO_DOTSTAR_ANCHOR);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NO_START_OPTIMIZE);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_NO_UTF_CHECK);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_UCP);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_UNGREEDY);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_USE_OFFSET_LIMIT);
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_UTF);

        Assertions.assertFalse(s1.get_match_options().JPCRE2_ANCHORED);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_COPY_MATCHED_SUBJECT);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_ENDANCHORED);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NOTBOL);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NOTEOL);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NOTEMPTY);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NOTEMPTY_ATSTART);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NO_JIT);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_NO_UTF_CHECK);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_PARTIAL_HARD);
        Assertions.assertFalse(s1.get_match_options().JPCRE2_PARTIAL_SOFT);

        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_ALT_BSUX);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_ESCAPED_CR_IS_LF);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_MATCH_LINE);
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_MATCH_WORD);



        s1.get_compile_options().JPCRE2_ANCHORED = true;
        Assertions.assertTrue(s1.get_compile_options().JPCRE2_ANCHORED);
        s1.get_match_options().JPCRE2_ANCHORED = true;
        Assertions.assertTrue(s1.get_compile_options().JPCRE2_ANCHORED);
        s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;
        Assertions.assertTrue(s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK);
        s1.get_compile_options().JPCRE2_ANCHORED = false;
        Assertions.assertFalse(s1.get_compile_options().JPCRE2_ANCHORED);
        s1.get_match_options().JPCRE2_ANCHORED = false;
        Assertions.assertFalse(s1.get_match_options().JPCRE2_ANCHORED);
        s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = false;
        Assertions.assertFalse(s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK);

    }

    @Test
    void pcre2_extraoptions_test(){
        JavaPcre s1 = new JavaPcre();
        s1.gcontext_create();
        s1.ccontext_create();
        s1.get_extra_options().JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;
        s1.ccontext_set_extra_options();

        s1.ccontext_free();
        s1.gcontext_free();
    }

    @Test
    void pcre2_context_test() {
        JavaPcre s1 = new JavaPcre();
        Assertions.assertNull(s1.get_gcontext());
        Assertions.assertNull(s1.get_ccontext());
        Assertions.assertNull(s1.get_mcontext());
        s1.gcontext_create();
        s1.ccontext_create();
        s1.mcontext_create();
        Assertions.assertNotEquals(null, s1.get_gcontext());
        Assertions.assertNotEquals(null, s1.get_ccontext());
        Assertions.assertNotEquals(null, s1.get_mcontext());


        s1.compile_java("From:([^@]+)@([^\r]+)");

        s1.jcompile_free();
        s1.mcontext_free();
        s1.ccontext_free();
        s1.gcontext_free();
        // no exception handling to make sure assertion-functions are reached properly

        Assertions.assertNull(s1.get_gcontext());
        Assertions.assertNull(s1.get_ccontext());
        Assertions.assertNull(s1.get_mcontext());
    }

    @Test
    void pcre2_compile_test() {
        JavaPcre s1 = new JavaPcre();
        s1.compile_java("From:([^@]+)@([^\r]+)");
        Assertions.assertNotEquals(null, s1.get_re());
        s1.jcompile_free();
        Assertions.assertNull(s1.get_re());

        s1.compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        Assertions.assertNotEquals(null, s1.get_re());
        s1.jcompile_free();
        Assertions.assertNull(s1.get_re());

        s1.compile_java("From:([^@]+)@(?<sposti>[^\r]+)");
        Assertions.assertNotEquals(null, s1.get_re());
        s1.jcompile_free();
        Assertions.assertNull(s1.get_re());

        s1.compile_java("");
        Assertions.assertNotEquals(null, s1.get_re());
        s1.jcompile_free();
        Assertions.assertNull(s1.get_re());
    }

    @Test
    void pcre2_compile_test_with_group_data() {
        JavaPcre s1 = new JavaPcre();
        s1.compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        Map<String, Integer> expected_nameTable = new HashMap<>();
        expected_nameTable.put("nimi", 1);
        expected_nameTable.put("sposti", 2);
        Assertions.assertNotEquals(null, s1.get_re());
        Map<String, Integer> nameTable = s1.get_name_table();
        Assertions.assertEquals(2, nameTable.size());
        Assertions.assertEquals(expected_nameTable, nameTable);
        s1.jcompile_free();
        Assertions.assertNull(s1.get_re());
        Assertions.assertEquals(0, nameTable.size());
    }

    @Test
    void pcre2_translator_test() {
        // broken
//        LibJavaPcre.ErrorStruct.ByValue errorstuff;
//        errorstuff = LibJavaPcre.INSTANCE.pcre2_translate_error_code(-33);
//        System.out.println("Test buffer output: "+(String)errorstuff.buffer);

        // Working
        final PointerByReference ptrRef = new PointerByReference();
        JavaPcre.LibJavaPcre.INSTANCE.pcre2_translate_error_code_alternative(-33, ptrRef);
        final Pointer p = ptrRef.getValue();
        if (p == null) {
            throw new NullPointerException("Error happened while allocating memory to error string");
        }
        final String val = p.getString(0);
        Assertions.assertEquals("bad offset value", val);
        JavaPcre.LibJavaPcre.INSTANCE.errorcleanup(p);
        Assertions.assertEquals("bad offset value", val);
    }

    @Test
    void pcre2_compile_failure_test() {
        JavaPcre s1 = new JavaPcre();
        try {
            s1.compile_java("From:(?<nimi>[^@]+@(?<sposti>[^\r]+)");
        }catch (Exception e){
            Assertions.assertEquals("missing closing parenthesis near index 35\nFrom:(?<nimi>[^@]+@(?<sposti>[^\r]+)", e.getLocalizedMessage());
            return;
        }
        throw new IllegalStateException("Exception handling broke down");
    }


    @Test
    void singlematch_java_failure_test() {
        JavaPcre s1 = new JavaPcre();
        s1.compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly
        // Error trigger: The value of startoffset was greater than the length of the subject.
        s1.set_offset(200);

        try {
            s1.singlematch_java("From:regular.expression@example.com\r\n" +
                    "From:exddd@43434.com\r\n" +
                    "From:7853456@exgem.com\r\n", s1.get_offset());
        }catch (Exception e){
            Assertions.assertEquals("Matching error -33: bad offset value", e.getLocalizedMessage());
            s1.jcompile_free();
            return;
        }
        throw new IllegalStateException("Exception handling broke down");
    }

    @Test
    void singlematch_java_failure_test2() {
        JavaPcre s1 = new JavaPcre();
        s1.set_offset(0);
        // Match pattern is not compiled to trigger an error

        try {
            s1.singlematch_java("From:regular.expression@example.com\r\n" +
                    "From:exddd@43434.com\r\n" +
                    "From:7853456@exgem.com\r\n", s1.get_offset());
        }catch (Exception e){
            //LOGGER.error(e.getMessage());
            Assertions.assertEquals("Match pattern is not compiled", e.getLocalizedMessage());
            return;
        }
        throw new IllegalStateException("Exception handling broke down");
    }

    @Test
    void singlematch_java_failure_test3() {
        JavaPcre s1 = new JavaPcre();
        s1.compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly
        s1.set_offset(0);

        // subject is null to trigger an error
        try {
            s1.singlematch_java(null, s1.get_offset());
        }catch (Exception e){
            //LOGGER.error(e.getMessage());
            Assertions.assertEquals("Subject is null", e.getLocalizedMessage());
            s1.jcompile_free();
            return;
        }
        throw new IllegalStateException("Exception handling broke down");
    }

    @Test
    void pcre2_singlematch_test1() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler/match options and context at default values.
        s1.compile_java("From:([^@]+)@([^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly
        s1.set_offset(0);

        // function for getting a single match group
        s1.singlematch_java("From:regular.expression@example.com\r\n" +
                                  "From:exddd@43434.com\r\n" +
                                  "From:7853456@exgem.com\r\n", s1.get_offset());
        a = 0;
        for(Map.Entry<Integer,String>it:s1.get_match_table().entrySet()) {
            if (a==0) {
                Assertions.assertEquals(0, a);
                Assertions.assertEquals("From:regular.expression@example.com", it.getValue());
            }
            if (a==1) {
                Assertions.assertEquals(1, a);
                Assertions.assertEquals("regular.expression", it.getValue());
            }
            if (a==2) {
                Assertions.assertEquals(2, a);
                Assertions.assertEquals("example.com", it.getValue());
            }
            a += 1;
        }
    }

    @Test
    void pcre2_singlematch_2namedgroups_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.compile_java("From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly
        s1.set_offset(0);

        // function for getting a single match group
        s1.singlematch_java("From:regular.expression@example.com\r\nFrom:exddd@43434.com\r\nFrom:7853456@exgem.com\r\n", s1.get_offset());
        // no exception handling to make sure assertion-functions are reached properly
        a = 0;
        for(Map.Entry<Integer,String>it:s1.get_match_table().entrySet()) {
            Assertions.assertTrue(a==0 || a==1 || a==2);
            if (a==0) {
                Assertions.assertEquals("From:regular.expression@example.com", it.getValue());
            }
            if (a==1) {
                Assertions.assertEquals("regular.expression", it.getValue());
            }
            if (a==2) {
                Assertions.assertEquals("example.com", it.getValue());
            }
            a += 1;
        }
        if (a==0) {
            throw new IllegalStateException("No match when there should be a match");
        }else{
            if (s1.get_name_table().size() > 0) {
            }
            for(Map.Entry<String,Integer>it:s1.get_name_table().entrySet()) {
                Assertions.assertTrue(it.getValue()==1 || it.getValue()==2);
                if (it.getValue()==1) {
                    Assertions.assertEquals("nimi", it.getKey());
                }
                else if (it.getValue()==2) {
                    Assertions.assertEquals("sposti", it.getKey());
                }
            }
        }
        s1.jcompile_free();
    }

    @Test
    void pcre2_singlematch_1namedgroup_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.

        s1.compile_java("From:([^@]+)@(?<sposti>[^\r]+)");
        s1.set_offset(0);

        // function for getting a single match group
        s1.singlematch_java("From:regular.expression@example.com\r\n" +
                                  "From:exddd@43434.com\r\n" +
                                  "From:7853456@exgem.com\r\n", s1.get_offset());
        a = 0;
        //System.out.print("Match group:\n");
        for(Map.Entry<Integer,String>it:s1.get_match_table().entrySet()) {
            Assertions.assertTrue(a==0 || a==1 || a==2);
            if (a==0) {
                Assertions.assertEquals("From:regular.expression@example.com", it.getValue());
            }
            if (a==1) {
                Assertions.assertEquals("regular.expression", it.getValue());
            }
            if (a==2) {
                Assertions.assertEquals("example.com", it.getValue());
            }
            a += 1;
        }
        if (a==0) {
            //System.out.print("No match!\n");
        }else{
            if (s1.get_name_table().size() > 0) {
                //System.out.print("named groups:\n");
            }
            for(Map.Entry<String,Integer>it:s1.get_name_table().entrySet()) {
                Assertions.assertEquals(2, it.getValue());
                Assertions.assertEquals("sposti", it.getKey());
            }
        }
        s1.jcompile_free();
    }

    @Test
    void pcre2_singlematch_nomatch_test() {
        int a;
        String subject = "nomatch";
        String pattern = "From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        JavaPcre s1 = new JavaPcre(); // also initializes all the compiler and matching options at default pcre2 values (all options disabled by default).

        Assertions.assertFalse(s1.get_matchfound());

        // The compile function:
        s1.compile_java(pattern);
        // no exception handling to make sure assertion-functions are reached properly

        /* change matching options and parameters before matching: */
        s1.set_offset(0);                              /* offset at where to start the match */
        // a simple loop for getting all match groups at once:
        boolean matchfound = true;  // for allowing the initial run of matching
        int groupcounter = 0;            // simple counter for match group numbering.
        int previousoffset;         // integer for storing the previous offset.

        while (matchfound) {
            if (groupcounter == 0) {
                // the matching function for first match:
                Assertions.assertFalse(s1.get_matchfound());
                s1.singlematch_java(subject, s1.get_offset());
                // no exception handling to make sure assertion-functions are reached properly
                Assertions.assertFalse(s1.get_matchfound());
            }else{
                // the matching function for concurrent matches:
                previousoffset = s1.get_offset();
                s1.set_offset(s1.get_ovector1()); /* Start at end of previous match */
                if (s1.get_ovector0() == s1.get_ovector1())
                {
                    if (s1.get_ovector0() == subject.length()) break;
                    s1.get_match_options().JPCRE2_NOTEMPTY_ATSTART = true;
                    s1.get_match_options().JPCRE2_ANCHORED = true;
                }

                else
                {
                    //int startchar = previousoffset;
                    if (s1.get_offset() <= previousoffset)
                    {
                        if (previousoffset >= subject.length()) break;   /* Reached end of subject.   */
                        s1.set_offset(previousoffset + 1);                  /* Advance by one character. */
                        if (s1.get_utf8())                    /* If UTF-8, it may be more  */
                        {                                           /*   than one code unit.     */
                            int temp = s1.get_offset();
                            for (; temp < subject.length(); temp++) {
                                if (s1.check_utf8(subject.charAt(temp))){
                                    s1.set_offset(temp);
                                    break;
                                }
                            }
                        }
                    }
                }


                s1.singlematch_java(subject, s1.get_offset());
                // no exception handling to make sure assertion-functions are reached properly
            }
            if (s1.get_JPCRE2_ERROR_NOMATCH()){
                if (s1.checkmatchoptionzero()){
                    break;
                }                       /* All matches found */
                if (groupcounter > 1) {                                      /* only check for (a) and (b) complications from concurrent matches */
                    s1.set_ovector1(s1.get_offset() + 1);                                      /* Advance one code unit */
                    if (s1.get_crlf_is_newline() &&               /* If CRLF is a newline & */
                            s1.get_offset() < subject.length()-1 &&               /* we are at CRLF, */
                            subject.charAt(s1.get_offset()) == '\r' &&
                            subject.charAt(s1.get_offset() - 1) == '\n')
                    {
                        int temp = s1.get_ovector1();
                        s1.set_ovector1(temp + 1);
                    }                                        /* Advance by one more. */
                    else if (s1.get_utf8()){                            /* Otherwise, ensure we */
                        while (s1.get_ovector1() < subject.length()) {              /* advance a whole UTF-8 */
                            if (s1.check_utf8(subject.charAt(s1.get_ovector1()))) { /* character. */
                                break;
                            }else{
                                int temp = s1.get_ovector1();
                                s1.set_ovector1(temp + 1);
                            }
                        }
                    }
                    matchfound = true;
                    continue;
                }
            }
            matchfound = s1.get_matchfound();
        }
        Assertions.assertTrue(s1.get_JPCRE2_ERROR_NOMATCH());
        s1.jcompile_free();
    }

    @Test
    void pcre2_matchall_test() {
        int a;
        String subject = "From:regular.expression@example.com\r\n" + "From:exddd@43434.com\r\n" + "From:7853456@exgem.com\r\n";
        String pattern = "From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        JavaPcre s1 = new JavaPcre(); // also initializes all the compiler and matching options at default pcre2 values (all options disabled by default).

        // The compile function:
        s1.compile_java(pattern);
        // no exception handling to make sure assertion-functions are reached properly

        /* change matching options and parameters before matching: */
        s1.set_offset(0);                              /* offset at where to start the match */

        // a simple loop for getting all match groups at once:
        boolean matchfound = true;  // for allowing the initial run of matching
        int groupcounter = 0;            // simple counter for match group numbering.
        int previousoffset;         // integer for storing the previous offset.

        while (matchfound) {
            if (groupcounter == 0) {
                // the matching function for first match:
                Assertions.assertFalse(s1.get_matchfound());
                s1.singlematch_java(subject, s1.get_offset());
                // no exception handling to make sure assertion-functions are reached properly
                Assertions.assertTrue(s1.get_matchfound());
            }else{
                // the matching function for concurrent matches:
                previousoffset = s1.get_offset();
                s1.set_offset(s1.get_ovector1()); /* Start at end of previous match */

                if (s1.get_ovector0() == s1.get_ovector1())
                {
                    if (s1.get_ovector0() == subject.length()) break;
                    s1.get_match_options().JPCRE2_NOTEMPTY_ATSTART = true;
                    s1.get_match_options().JPCRE2_ANCHORED = true;
                }

                else
                {
                    if (s1.get_offset() <= previousoffset)
                    {
                        if (previousoffset >= subject.length()) break;   /* Reached end of subject.   */
                        s1.set_offset(previousoffset + 1);                  /* Advance by one character. */
                        if (s1.get_utf8())                    /* If UTF-8, it may be more  */
                        {                                           /*   than one code unit.     */
                            int temp = s1.get_offset();
                            for (; temp < subject.length(); temp++) {
                                if (s1.check_utf8(subject.charAt(temp))) {
                                    s1.set_offset(temp);
                                    break;
                                }
                            }
                        }
                    }
                }


                s1.singlematch_java(subject, s1.get_offset());
                // no exception handling to make sure assertion-functions are reached properly
            }
            if (s1.get_JPCRE2_ERROR_NOMATCH()){
                if (s1.checkmatchoptionzero()){
                    break;
                }                       /* All matches found */
                if (groupcounter > 1) {                                      /* only check for (a) and (b) complications from concurrent matches */
                    s1.set_ovector1(s1.get_offset() + 1);                                      /* Advance one code unit */
                    if (s1.get_crlf_is_newline() &&               /* If CRLF is a newline & */
                            s1.get_offset() < subject.length()-1 &&               /* we are at CRLF, */
                            subject.charAt(s1.get_offset()) == '\r' &&
                            subject.charAt(s1.get_offset() - 1) == '\n')
                    {
                        int temp = s1.get_ovector1();
                        s1.set_ovector1(temp + 1);
                    }                                        /* Advance by one more. */
                    else if (s1.get_utf8()){                            /* Otherwise, ensure we */
                        while (s1.get_ovector1() < subject.length()) {              /* advance a whole UTF-8 */
                            if (s1.check_utf8(subject.charAt(s1.get_ovector1()))) { /* character. */
                                break;
                            }else{
                                int temp = s1.get_ovector1();
                                s1.set_ovector1(temp + 1);
                            }
                        }
                    }
                    matchfound = true;
                    continue;
                }
            }


            a = 0;  /* simple counter for substring numbering */
            Assertions.assertTrue(s1.get_matchfound());
            matchfound = s1.get_matchfound();

            // when match is found, print match group data
            groupcounter += 1;
            Assertions.assertTrue(groupcounter==1 || groupcounter==2 || groupcounter==3);
            for (Map.Entry<Integer, String> it : s1.get_match_table().entrySet()) {
                Assertions.assertTrue(a==0 || a==1 || a==2);
                if (a==0 && groupcounter == 1) {
                    Assertions.assertEquals("From:regular.expression@example.com", it.getValue());
                }
                if (a==1 && groupcounter == 1) {
                    Assertions.assertEquals("regular.expression", it.getValue());
                }
                if (a==2 && groupcounter == 1) {
                    Assertions.assertEquals("example.com", it.getValue());
                }
                if (a==0 && groupcounter == 2) {
                    Assertions.assertEquals("From:exddd@43434.com", it.getValue());
                }
                if (a==1 && groupcounter == 2) {
                    Assertions.assertEquals("exddd", it.getValue());
                }
                if (a==2 && groupcounter == 2) {
                    Assertions.assertEquals("43434.com", it.getValue());
                }
                if (a==0 && groupcounter == 3) {
                    Assertions.assertEquals("From:7853456@exgem.com", it.getValue());
                }
                if (a==1 && groupcounter == 3) {
                    Assertions.assertEquals("7853456", it.getValue());
                }
                if (a==2 && groupcounter == 3) {
                    Assertions.assertEquals("exgem.com", it.getValue());
                }
                a += 1;
            }

            // print named group data if available
            if (s1.get_name_table().size() > 0) {
                for (Map.Entry<String, Integer> it : s1.get_name_table().entrySet()) {
                    Assertions.assertTrue(it.getValue()==1 || it.getValue()==2);
                    if (it.getValue()==1) {
                        Assertions.assertEquals("nimi", it.getKey());
                    }
                    else if (it.getValue()==2) {
                        Assertions.assertEquals("sposti", it.getKey());
                    }
                }
            }
        }
        s1.jcompile_free();
    }

    @Test
    void pcre2_matchall_clrf_test() {
        int a;
        String subject = "From:regular.expression@example.com\r\nFrom:exddd@43434.com\r\nFrom:7853456@exgem.com\r\n";
        String pattern = "(*ANYCRLF)From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)"; // (*ANYCRLF) in the pattern enables the PCRE2_NEWLINE_ANYCRLF newline-option
        JavaPcre s1 = new JavaPcre(); // also initializes all the compiler and matching options at default pcre2 values (all options disabled by default).

        s1.get_compile_options().JPCRE2_UTF = true;                           // enable PCRE2_UTF option for compiling, always disabled by default.

        // The compile function:
        s1.compile_java(pattern);
        // no exception handling to make sure assertion-functions are reached properly


        /* change matching options and parameters before matching: */
        s1.set_offset(0);                              /* offset at where to start the match */


        // a simple loop for getting all match groups at once:
        boolean matchfound = true;  // for allowing the initial run of matching
        int groupcounter = 0;            // simple counter for match group numbering.
        int previousoffset;         // integer for storing the previous offset.

        while (matchfound) {
            if (groupcounter == 0) {
                // the matching function for first match:
                s1.singlematch_java(subject, s1.get_offset());
                // no exception handling to make sure assertion-functions are reached properly
            }else{
                // the matching function for concurrent matches:
                previousoffset = s1.get_offset();
                s1.set_offset(s1.get_ovector1()); /* Start at end of previous match */

                if (s1.get_ovector0() == s1.get_ovector1())
                {
                    if (s1.get_ovector0() == subject.length()) break;
                    s1.init_match_options();
                    s1.get_match_options().JPCRE2_NOTEMPTY_ATSTART = true;
                    s1.get_match_options().JPCRE2_ANCHORED = true;
                }

                else
                {
                    if (s1.get_offset() <= previousoffset)
                    {
                        if (previousoffset >= subject.length()) break;   /* Reached end of subject.   */
                        s1.set_offset(previousoffset + 1);                  /* Advance by one character. */
                        if (s1.get_utf8())                    /* If UTF-8, it may be more  */
                        {                                           /*   than one code unit.     */
                            int temp = s1.get_offset();
                            for (; temp < subject.length(); temp++) {
                                if (s1.check_utf8(subject.charAt(temp))) {
                                    s1.set_offset(temp);
                                }
                                    break;
                            }
                        }
                    }
                }
                s1.singlematch_java(subject, s1.get_offset());
            }
            if (s1.get_JPCRE2_ERROR_NOMATCH()){
                if (s1.checkmatchoptionzero()){
                    break;
                }                       /* All matches found */
                if (groupcounter > 1) {                                      /* only check for (a) and (b) complications from concurrent matches */
                    s1.set_ovector1(s1.get_offset() + 1);                             /* Advance one code unit */
                    Assertions.assertTrue(s1.get_crlf_is_newline()); // should be true when (ANYCRLF*) is starting the pattern
                    if (s1.get_crlf_is_newline() &&               /* If CRLF is a newline & */
                            s1.get_offset() < subject.length()-1 &&               /* we are at CRLF, */
                            subject.charAt(s1.get_offset()) == '\r' &&
                            subject.charAt(s1.get_offset() - 1) == '\n')
                    {
                        int temp = s1.get_ovector1();
                        s1.set_ovector1(temp + 1);
                    }                                        /* Advance by one more. */
                    else if (s1.get_utf8()){                            /* Otherwise, ensure we */
                        while (s1.get_ovector1() < subject.length()) {              /* advance a whole UTF-8 */
                            if (s1.check_utf8(subject.charAt(s1.get_ovector1()))) { /* character. */
                                break;
                            }else{
                                int temp = s1.get_ovector1();
                                s1.set_ovector1(temp + 1);
                            }
                        }
                    }
                    matchfound = true;
                    continue;
                }
            }


            a = 0;  /* simple counter for substring numbering */
            Assertions.assertTrue(s1.get_matchfound());
            matchfound = s1.get_matchfound();

            // when match is found, print match group data
            groupcounter += 1;


            for (Map.Entry<Integer, String> it : s1.get_match_table().entrySet()) {
                Assertions.assertTrue(groupcounter==1 || groupcounter==2 || groupcounter==3);
                if (a==0 && groupcounter == 1) {
                    Assertions.assertEquals("From:regular.expression@example.com", it.getValue());
                }
                if (a==1 && groupcounter == 1) {
                    Assertions.assertEquals("regular.expression", it.getValue());
                }
                if (a==2 && groupcounter == 1) {
                    Assertions.assertEquals("example.com", it.getValue());
                }
                if (a==0 && groupcounter == 2) {
                    Assertions.assertEquals("From:exddd@43434.com", it.getValue());
                }
                if (a==1 && groupcounter == 2) {
                    Assertions.assertEquals("exddd", it.getValue());
                }
                if (a==2 && groupcounter == 2) {
                    Assertions.assertEquals("43434.com", it.getValue());
                }
                if (a==0 && groupcounter == 3) {
                    Assertions.assertEquals("From:7853456@exgem.com", it.getValue());
                }
                if (a==1 && groupcounter == 3) {
                    Assertions.assertEquals("7853456", it.getValue());
                }
                if (a==2 && groupcounter == 3) {
                    Assertions.assertEquals("exgem.com", it.getValue());
                }
                a += 1;
            }

            // print named group data if available
            if (s1.get_name_table().size() > 0) {
                for (Map.Entry<String, Integer> it : s1.get_name_table().entrySet()) {
                    Assertions.assertTrue(it.getValue()==1 || it.getValue()==2);
                    if (it.getValue()==1) {
                        Assertions.assertEquals("nimi", it.getKey());
                    }
                    else if (it.getValue()==2) {
                        Assertions.assertEquals("sposti", it.getKey());
                    }
                }
            }
        }
        s1.jcompile_free();
    }




    @Test
    void pcre2_utf8andcrlf_test() {
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        String pattern = "(*ANYCRLF)From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        s1.get_compile_options().JPCRE2_UTF = true;
        s1.compile_java(pattern);
        // no exception handling to make sure assertion-functions are reached properly
        boolean testi = s1.get_utf8();
        Assertions.assertTrue(testi);
        boolean testi2 = s1.get_crlf_is_newline();
        Assertions.assertTrue(testi2);
        s1.jcompile_free();

        pattern = "From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        s1.get_compile_options().JPCRE2_UTF = false;
        s1.compile_java(pattern);
        // no exception handling to make sure assertion-functions are reached properly
        testi = s1.get_utf8();
        Assertions.assertFalse(testi);
        testi2 = s1.get_crlf_is_newline();
        Assertions.assertFalse(testi2);

        s1.jcompile_free();
    }
    @Test
    void pcre2_matchall_nomatch_test() {
        int a;
        JavaPcre s1 = new JavaPcre(); // also initializes the compiler options at default values.
        s1.compile_java("nomatch");
        // no exception handling to make sure assertion-functions are reached properly
        s1.set_offset(0);
        s1.singlematch_java("From:regular.expression@example.com\r\nFrom:exddd@43434.com\r\nFrom:7853456@exgem.com\r\n", s1.get_offset());
        // no exception handling to make sure assertion-functions are reached properly
        Assertions.assertFalse(s1.get_matchfound());
        s1.jcompile_free();
    }

    @Test
    void jcompile_free() {
        JavaPcre s1 = new JavaPcre();
        Assertions.assertNull(s1.get_re());
        s1.compile_java("From:([^@]+)@([^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly

        Assertions.assertNotEquals(null, s1.get_re());
        s1.jcompile_free();
        // no exception handling to make sure assertion-functions are reached properly
        Assertions.assertNull(s1.get_re());
    }

    @Test
    void jcompile_free_error(){
        JavaPcre s1 = new JavaPcre();
        s1.compile_java("From:([^@]+)@([^\r]+)");
        // no exception handling to make sure assertion-functions are reached properly
        // TODO: do this to all exceptions that are capable of blocking assertions.

        try {
            s1.jcompile_free();
        }catch(Exception e){
            Assertions.assertEquals("No data to free", e.getLocalizedMessage());
        }
        try {
            s1.jcompile_free();
        }catch(Exception e){
            Assertions.assertEquals("No data to free", e.getLocalizedMessage());
            return;
        }
        throw new IllegalStateException("Exception handling broke down");
    }
}
