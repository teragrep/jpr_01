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
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.Structure.FieldOrder;
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

        Pointer pcre2_jcompile(String pattern, int i, OptionsStruct options, Pointer ccontext); // returns pointer to compiled pattern re

        RegexStruct.ByValue pcre2_single_jmatch(String subject, Pointer re, int offset, MatchOptionsStruct match_options, Pointer mcontext); // returns pointer to a single match data.

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

    }

    // Add functions here

    String pattern;
    int pattern_size;
    int offset;
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
        gcontext = null; // default value for when context is not used in compile or match
        ccontext = null; // default value for when context is not used in compile
        mcontext = null; // default value for when context is not used in match
    }
    // Make another constructor if/when memory management is implemented to the context functions.

    // checks the installed PCRE2 library version for compatibility.
    public void pcre2_versioncheck(){
        LibJavaPcre.INSTANCE.pcre2_versioncheck();
    }

//    public void pcre2_init_options(){
//        compile_options = new LibJavaPcre.OptionsStruct();
//    }

    public void pcre2_gcontext_create(){
        gcontext = LibJavaPcre.INSTANCE.pcre2_gcontext_create();
    }
    public void pcre2_ccontext_create(){
        if (gcontext == null){
            LOGGER.error("General context is not initialized properly!");
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
            LOGGER.error("General context is not initialized properly!");
            throw new IllegalArgumentException("General context is not initialized properly");
        } else {
            mcontext = LibJavaPcre.INSTANCE.pcre2_mcontext_create(gcontext);
        }
    }
    public void pcre2_gcontext_free(){
        LibJavaPcre.INSTANCE.pcre2_gcontext_free(gcontext);
        gcontext = null;
    }
    public void pcre2_ccontext_free(){
        LibJavaPcre.INSTANCE.pcre2_ccontext_free(ccontext);
        ccontext = null;
    }
    public void pcre2_mcontext_free(){
        LibJavaPcre.INSTANCE.pcre2_mcontext_free(mcontext);
        mcontext = null;
    }

    // TODO: Add functionality for retrieving the compile error information from C.
    public void pcre2_compile_java(String pat){
        pattern = pat;
        re = LibJavaPcre.INSTANCE.pcre2_jcompile(pattern, pattern_size, compile_options, ccontext);
        if (re == null){
            LOGGER.error("Compiling of the match pattern failed.");
        }
    }

    // This is the main function for getting a single regex match group.
    public void pcre2_singlematch_java(String a, int b){
        name_table = new LinkedHashMap<>();
        subject = a;
        offset = b;
        match_table = new LinkedHashMap<>();
        int ind = 0;

        // Matching error code labels are stored in the header file of the pcre2 library.
        LibJavaPcre.RegexStruct.ByValue regex_val = LibJavaPcre.INSTANCE.pcre2_single_jmatch(subject, re, offset, match_options, mcontext);
        if (regex_val.rc < 0) {
            switch(regex_val.rc){
                case -1: LOGGER.debug("PCRE2_ERROR_NOMATCH"); break; // rc = -1 should be equal to rc = PCRE2_ERROR_NOMATCH in C.
                case -2: LOGGER.debug("PCRE2_ERROR_PARTIAL"); break;
                case -31: LOGGER.error("PCRE2_ERROR_BADMAGIC"); break;
                case -32: LOGGER.error("PCRE2_ERROR_BADMODE"); break;
                case -33: LOGGER.error("PCRE2_ERROR_BADOFFSET"); break;
                case -34: LOGGER.error("PCRE2_ERROR_BADOPTION"); break;
                case -36: LOGGER.error("PCRE2_ERROR_BADUTFOFFSET"); break;
                case -37: LOGGER.error("PCRE2_ERROR_CALLOUT"); break;
                case -53: LOGGER.error("PCRE2_ERROR_DEPTHLIMIT"); break;
                case -63: LOGGER.error("PCRE2_ERROR_HEAPLIMIT"); break;
                case -65: LOGGER.error("PCRE2_ERROR_INTERNAL"); break;
                case -46: LOGGER.error("PCRE2_ERROR_JIT_STACKLIMIT"); break;
                case -47: LOGGER.error("PCRE2_ERROR_MATCHLIMIT"); break;
                case -48: LOGGER.error("PCRE2_ERROR_NOMEMORY"); break;
                case -51: LOGGER.error("PCRE2_ERROR_NULL"); break;
                case -52: LOGGER.error("PCRE2_ERROR_RECURSELOOP"); break;
                default: LOGGER.error("Matching error:" + regex_val.rc); break; // anything lower than -1 is a matching error.
            }
            LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val);
        } else {
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
            offset = regex_ovector[1];
            LibJavaPcre.INSTANCE.RegexStruct_cleanup(regex_val);
        }
    }

    // Not used, for now.
    public void pcre2_jmatch_free(){
        LibJavaPcre.INSTANCE.pcre2_jmatch_free(match_data);
    }

    // frees the compiled pattern.
    public void pcre2_Jcompile_free(){
        if (re != null){
            LibJavaPcre.INSTANCE.pcre2_jcompile_free(re);
            LibJavaPcre.INSTANCE.pcre2_ccontext_free(ccontext);
        }else{
            throw new IllegalStateException("No data to free");
        }
    }

}