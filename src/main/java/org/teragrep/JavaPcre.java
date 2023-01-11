/*
   Java PCRE2 Library JPR-01
   Copyright (C) 2022  Fail-Safe IT Solutions Oy

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package org.teragrep;

import java.util.*;
import java.util.regex.PatternSyntaxException;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
import com.sun.jna.ptr.PointerByReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaPcre {

    private static final Logger LOGGER = LoggerFactory.getLogger(JavaPcre.class);

    // Initializing the interface.
    // By making the interface this way you can call the external C-function in the Java-functions that are made inside this class.
    public interface LibJavaPcre extends Library {
        LibJavaPcre INSTANCE = Native.load("JavaPcre", LibJavaPcre.class);
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

        @FieldOrder({ "JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK", "JPCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES", "JPCRE2_EXTRA_ALT_BSUX", "JPCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL", "JPCRE2_EXTRA_ESCAPED_CR_IS_LF", "JPCRE2_EXTRA_MATCH_LINE", "JPCRE2_EXTRA_MATCH_WORD" })
        class ExtraOptionsStruct extends Structure {
            public boolean JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = false;
            public boolean JPCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES = false;
            public boolean JPCRE2_EXTRA_ALT_BSUX = false;
            public boolean JPCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL = false;
            public boolean JPCRE2_EXTRA_ESCAPED_CR_IS_LF = false;
            public boolean JPCRE2_EXTRA_MATCH_LINE = false;
            public boolean JPCRE2_EXTRA_MATCH_WORD = false;
        }

        @FieldOrder({ "JPCRE2_ANCHORED", "JPCRE2_COPY_MATCHED_SUBJECT", "JPCRE2_ENDANCHORED", "JPCRE2_NOTBOL", "JPCRE2_NOTEOL", "JPCRE2_NOTEMPTY", "JPCRE2_NOTEMPTY_ATSTART", "JPCRE2_NO_JIT", "JPCRE2_NO_UTF_CHECK", "JPCRE2_PARTIAL_HARD", "JPCRE2_PARTIAL_SOFT" })
        class MatchOptionsStruct extends Structure {
            public boolean JPCRE2_ANCHORED = false;
            public boolean JPCRE2_COPY_MATCHED_SUBJECT = false;
            public boolean JPCRE2_ENDANCHORED = false;
            public boolean JPCRE2_NOTBOL = false;
            public boolean JPCRE2_NOTEOL = false;
            public boolean JPCRE2_NOTEMPTY = false;
            public boolean JPCRE2_NOTEMPTY_ATSTART = false;
            public boolean JPCRE2_NO_JIT = false;
            public boolean JPCRE2_NO_UTF_CHECK = false;
            public boolean JPCRE2_PARTIAL_HARD = false;
            public boolean JPCRE2_PARTIAL_SOFT = false;
        }

        @FieldOrder({ "re", "errornumber", "erroroffset" })
        class CompileData extends Structure {
            public static class ByValue extends CompileData implements Structure.ByValue {}
            public Pointer re;
            public int errornumber;
            public int erroroffset;
        }

        @FieldOrder({ "buffer" })
        class ErrorStruct extends Structure {
            public static class ByValue extends ErrorStruct implements Structure.ByValue {}
            public String buffer;
        }

        @FieldOrder({ "numVals", "vals", "ovector", "names", "namesnum", "namescount", "rc" })
        class RegexStruct extends Structure {
            public static class ByValue extends RegexStruct implements Structure.ByValue {}
            public int numVals;
            public Pointer vals; // char**
            public Pointer ovector;
            public Pointer names; // char**
            public Pointer namesnum;
            public int namescount;
            public int rc;
        }

        void RegexStruct_cleanup(RegexStruct.ByValue sVal);

        CompileData.ByValue pcre2_jcompile(String pattern, int i, OptionsStruct options, Pointer ccontext); // returns struct containing compiled pattern re

        RegexStruct.ByValue pcre2_single_jmatch(String subject, Pointer re, int offset, MatchOptionsStruct match_options, Pointer mcontext); // returns pointer to a single match data.
        ErrorStruct.ByValue pcre2_translate_error_code(int errorcode);

        void pcre2_jcompile_free(Pointer re);

        void pcre2_versioncheck();

        void pcre2_jmatch_free(Pointer match_data);
        Pointer pcre2_gcontext_create();
        void pcre2_gcontext_free(Pointer gcontext);
        Pointer pcre2_ccontext_create(Pointer gcontext);
        void pcre2_ccontext_set_extra_options(Pointer ccontext, ExtraOptionsStruct extra_options);
        void pcre2_ccontext_free(Pointer ccontext);
        Pointer pcre2_mcontext_create(Pointer gcontext);
        void pcre2_mcontext_free(Pointer mcontext);
        int pcre2_get_utf8(Pointer re);
        int pcre2_get_crlf_is_newline(Pointer re);
        int pcre2_check_utf8(char temp);
        void pcre2_translate_error_code_alternative(int errorcode, PointerByReference val);
        void errorcleanup(Pointer p);

    }

    // Add functions here

    String pattern;
    int pattern_size;
    int ovector0;
    int ovector1;
    int offset;
    boolean matchfound;
    boolean JPCRE2_ERROR_NOMATCH;
    String subject;
    Pointer re;
    Pointer match_data;
    Pointer gcontext;
    Pointer ccontext;
    Pointer mcontext;
    LibJavaPcre.OptionsStruct compile_options;
    LibJavaPcre.ExtraOptionsStruct extra_options;
    LibJavaPcre.MatchOptionsStruct match_options;
    Map<String, Integer> name_table;
    Map<Integer, String> match_table;

    JavaPcre(){
        compile_options = new LibJavaPcre.OptionsStruct(); // initializes pcre2_compile options with default values of PCRE2 library.
        match_options = new LibJavaPcre.MatchOptionsStruct(); // initializes pcre2_match options with default values of PCRE2 library.
        extra_options = new LibJavaPcre.ExtraOptionsStruct(); // initializes pcre2_compile extra options with default values of PCRE2 library.
        pattern_size = 0; // default pattern size, value 0 will set the pcre2_compile length option to PCRE2_ZERO_TERMINATED.
        re = null;
        gcontext = null; // default value for when context is not used in compile or match
        ccontext = null; // default value for when context is not used in compile
        mcontext = null; // default value for when context is not used in match
        matchfound = false;
    }
    // Make another constructor if/when memory management is implemented to the context functions.

    // checks the installed PCRE2 library version for compatibility.
    public void pcre2_versioncheck(){
        LibJavaPcre.INSTANCE.pcre2_versioncheck();
    }

//    public void pcre2_init_options(){
//        compile_options = new LibJavaPcre.OptionsStruct();
//    }

    public boolean pcre2_get_utf8() {
        int temp = LibJavaPcre.INSTANCE.pcre2_get_utf8(re);
        return temp != 0;
    }

    public boolean pcre2_get_crlf_is_newline() {
        int temp = LibJavaPcre.INSTANCE.pcre2_get_crlf_is_newline(re);
        return temp != 0;
    }

    public boolean check_utf8(char temp) {
        int tempboolean = LibJavaPcre.INSTANCE.pcre2_check_utf8(temp);
        return tempboolean != 0;
    }

    public void pcre2_gcontext_create(){
        gcontext = LibJavaPcre.INSTANCE.pcre2_gcontext_create();
    }
    public void pcre2_ccontext_create(){
        if (gcontext == null){
            throw new IllegalArgumentException("General context is not initialized properly");
        } else {
            ccontext = LibJavaPcre.INSTANCE.pcre2_ccontext_create(gcontext);
        }
    }
    public void pcre2_ccontext_set_extra_options(){
        LibJavaPcre.INSTANCE.pcre2_ccontext_set_extra_options(ccontext, extra_options);
    }
    public void pcre2_mcontext_create(){
        if (gcontext == null){
            throw new IllegalArgumentException("General context is not initialized properly");
        } else {
            mcontext = LibJavaPcre.INSTANCE.pcre2_mcontext_create(gcontext);
        }
    }
    public void pcre2_gcontext_free(){
        if (gcontext == null){
            throw new IllegalArgumentException("No general context data to free.");
        } else {
            LibJavaPcre.INSTANCE.pcre2_gcontext_free(gcontext);
            gcontext = null;
        }
    }
    public void pcre2_ccontext_free(){
        if (ccontext == null){
            throw new IllegalArgumentException("No compiler context data to free.");
        } else {
            LibJavaPcre.INSTANCE.pcre2_ccontext_free(ccontext);
            ccontext = null;
        }
    }
    public void pcre2_mcontext_free(){
        if (mcontext == null){
            throw new IllegalArgumentException("No match context data to free.");
        } else {
            LibJavaPcre.INSTANCE.pcre2_mcontext_free(mcontext);
            mcontext = null;
        }
    }

    public void pcre2_translate_error_test(){
        // broken
//        LibJavaPcre.ErrorStruct.ByValue errorstuff;
//        errorstuff = LibJavaPcre.INSTANCE.pcre2_translate_error_code(-33);
//        System.out.println("Test buffer output: "+(String)errorstuff.buffer);

        final PointerByReference ptrRef = new PointerByReference();
        LibJavaPcre.INSTANCE.pcre2_translate_error_code_alternative(-33, ptrRef);
        final Pointer p = ptrRef.getValue();
        if (p == null) {
            throw new NullPointerException("Error happened while allocating memory to error string");
        }
        final String val = p.getString(0);
        System.out.println("ERRORTEST alternative: " + val);
        LibJavaPcre.INSTANCE.errorcleanup(p);
        System.out.println("ERRORTEST alternative after cleanup: " + val);
    }

    public void pcre2_compile_java(String pat){
        pattern = pat;
        LibJavaPcre.CompileData.ByValue comp_val = LibJavaPcre.INSTANCE.pcre2_jcompile(pattern, pattern_size, compile_options, ccontext);
        //re = LibJavaPcre.INSTANCE.pcre2_jcompile(pattern, pattern_size, compile_options, ccontext);
        re = comp_val.re;
        if (re == null){
//            LibJavaPcre.ErrorStruct.ByValue errorstuff;
//            errorstuff = LibJavaPcre.INSTANCE.pcre2_translate_error_code(comp_val.errornumber);
//            throw new PatternSyntaxException(errorstuff.buffer, pattern, comp_val.erroroffset);
            final PointerByReference ptrRef = new PointerByReference();
            LibJavaPcre.INSTANCE.pcre2_translate_error_code_alternative(comp_val.errornumber, ptrRef);
            final Pointer p = ptrRef.getValue();
            if (p == null) {
                throw new NullPointerException("Error happened while allocating memory to error string");
            }
            final String val = p.getString(0);
            LibJavaPcre.INSTANCE.errorcleanup(p);
            throw new PatternSyntaxException(val, pattern, comp_val.erroroffset);
        }
    }

    public boolean checkoptionzero(){
        return !compile_options.JPCRE2_ANCHORED && !compile_options.JPCRE2_ALLOW_EMPTY_CLASS && !compile_options.JPCRE2_ALT_BSUX && !compile_options.JPCRE2_ALT_CIRCUMFLEX && !compile_options.JPCRE2_ALT_VERBNAMES && !compile_options.JPCRE2_AUTO_CALLOUT && !compile_options.JPCRE2_CASELESS && !compile_options.JPCRE2_DOLLAR_ENDONLY && !compile_options.JPCRE2_DOTALL && !compile_options.JPCRE2_DUPNAMES && !compile_options.JPCRE2_ENDANCHORED && !compile_options.JPCRE2_EXTENDED && !compile_options.JPCRE2_EXTENDED_MORE && !compile_options.JPCRE2_FIRSTLINE && !compile_options.JPCRE2_LITERAL && !compile_options.JPCRE2_MATCH_INVALID_UTF && !compile_options.JPCRE2_MATCH_UNSET_BACKREF && !compile_options.JPCRE2_MULTILINE && !compile_options.JPCRE2_NEVER_BACKSLASH_C && !compile_options.JPCRE2_NEVER_UCP && !compile_options.JPCRE2_NEVER_UTF && !compile_options.JPCRE2_NO_AUTO_CAPTURE && !compile_options.JPCRE2_NO_AUTO_POSSESS && !compile_options.JPCRE2_NO_DOTSTAR_ANCHOR && !compile_options.JPCRE2_NO_START_OPTIMIZE && !compile_options.JPCRE2_NO_UTF_CHECK && !compile_options.JPCRE2_UCP && !compile_options.JPCRE2_UNGREEDY && !compile_options.JPCRE2_USE_OFFSET_LIMIT && !compile_options.JPCRE2_UTF;
    }


    int JPCRE2_AUTO_CALLOUT;
    int JPCRE2_CASELESS;
    int JPCRE2_DOLLAR_ENDONLY;
    int JPCRE2_DOTALL;
    int JPCRE2_DUPNAMES;
    int JPCRE2_ENDANCHORED;
    int JPCRE2_EXTENDED;
    int JPCRE2_EXTENDED_MORE;
    int JPCRE2_FIRSTLINE;
    int JPCRE2_LITERAL;
    int JPCRE2_MATCH_INVALID_UTF;
    int JPCRE2_MATCH_UNSET_BACKREF;
    int JPCRE2_MULTILINE;
    int JPCRE2_NEVER_BACKSLASH_C;
    int JPCRE2_NEVER_UCP;
    int JPCRE2_NEVER_UTF;
    int JPCRE2_NO_AUTO_CAPTURE;
    int JPCRE2_NO_AUTO_POSSESS;
    int JPCRE2_NO_DOTSTAR_ANCHOR;
    int JPCRE2_NO_START_OPTIMIZE;
    int JPCRE2_NO_UTF_CHECK;
    int JPCRE2_UCP;
    int JPCRE2_UNGREEDY;
    int JPCRE2_USE_OFFSET_LIMIT;
    int JPCRE2_UTF;

    // This is the main function for getting a single regex match group.
    public void pcre2_singlematch_java(String a, int b){
        if (re == null) {
            throw new IllegalStateException("Match pattern is not compiled");
        }
        if (a == null) {
            throw new IllegalStateException("Subject is null");
        }
        name_table = new LinkedHashMap<>();
        subject = a;
        offset = b;
        match_table = new LinkedHashMap<>();
        int ind = 0;

        // Matching error code labels are stored in the header file of the pcre2 library.
        LibJavaPcre.RegexStruct.ByValue regex_val = LibJavaPcre.INSTANCE.pcre2_single_jmatch(subject, re, offset, match_options, mcontext);
        if (regex_val.rc < 0) {
            matchfound = false;
            int errorcode = regex_val.rc;

            // Broken method for getting error description
            //LibJavaPcre.ErrorStruct.ByValue errorstuff;
            //errorstuff = LibJavaPcre.INSTANCE.pcre2_translate_error_code(regex_val.rc);


            // alternative and working method for getting the error description:
            final PointerByReference ptrRef = new PointerByReference();
            LibJavaPcre.INSTANCE.pcre2_translate_error_code_alternative(errorcode, ptrRef);
            final Pointer p = ptrRef.getValue();
            if (p == null) {
                throw new NullPointerException("Error happened while allocating memory to error string");
            }
            final String val = p.getString(0);
            LibJavaPcre.INSTANCE.errorcleanup(p);

            switch(regex_val.rc){
                case -1: JPCRE2_ERROR_NOMATCH = true; LOGGER.debug("Matching error -1: " + val); LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val); break; // rc = -1 should be equal to rc = PCRE2_ERROR_NOMATCH in C.
                case -2: JPCRE2_ERROR_NOMATCH = false; LOGGER.debug("Matching error -2: " + val); LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val); break; // rc = -2 should be partial match.
                default: JPCRE2_ERROR_NOMATCH = false; LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val); throw new MatchException("Matching error " + errorcode + ": " + val); // anything lower than -1 is a matching error that is not recoverable.
            }
        } else {
            JPCRE2_ERROR_NOMATCH = false;
            matchfound = true;
            final String[] regex_vals = regex_val.vals.getStringArray(0, regex_val.numVals);
            final int[] regex_ovector = regex_val.ovector.getIntArray(0, (regex_val.numVals + 2));
            if (regex_val.namescount > 0) {
                final String[] regex_names = regex_val.names.getStringArray(0, regex_val.namescount);
                final int[] namesnum = regex_val.namesnum.getIntArray(0, regex_val.namescount);
                for (int namesloop = 0; namesloop < regex_val.namescount; namesloop++) {
                    name_table.put(regex_names[namesloop], namesnum[namesloop]);
                }
            }
            for (int regexloop = 0; regexloop < regex_val.numVals; regexloop++) {
                match_table.put(ind++, regex_vals[regexloop]);
            }
            ovector0 = regex_ovector[0];
            ovector1 = regex_ovector[1];
            LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val);
        }
    }

    // Not used, for now.
/*    public void pcre2_jmatch_free(){
        LibJavaPcre.INSTANCE.pcre2_jmatch_free(match_data);
    }*/

    // frees the compiled pattern.
    public void pcre2_Jcompile_free(){
        if (re != null){
            LibJavaPcre.INSTANCE.pcre2_jcompile_free(re);
            re = null;
        }else{
            throw new IllegalStateException("No data to free");
        }
    }

}