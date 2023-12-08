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

#define PCRE2_CODE_UNIT_WIDTH 8

#include <stdio.h>
#include <string.h>
#include <pcre2.h>

// compiler commands for this file:
// gcc -c -Wall -Werror -fpic src/main/c/JavaPcre.c -o src/main/c/JavaPcre.o -lpcre2-8
// gcc -shared -Wl,-soname,libJavaPcre.so -o src/main/c/libJavaPcre.so src/main/c/JavaPcre.o -lpcre2-8

// Autotools setup commands for this file:
// autoreconf --install
// ./configure
// make

// None of the above matters if maven works as intended and executes the autotools scripts automatically.

typedef struct OptionsStruct_TAG {
    int JPCRE2_ANCHORED;
    int JPCRE2_ALLOW_EMPTY_CLASS;
    int JPCRE2_ALT_BSUX;
    int JPCRE2_ALT_CIRCUMFLEX;
    int JPCRE2_ALT_VERBNAMES;
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
} OptionsStruct;

typedef struct ExtraOptionsStruct_TAG {
    int JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK;
    int JPCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES;
    int JPCRE2_EXTRA_ALT_BSUX;
    int JPCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL;
    int JPCRE2_EXTRA_ESCAPED_CR_IS_LF;
    int JPCRE2_EXTRA_MATCH_LINE;
    int JPCRE2_EXTRA_MATCH_WORD;
} ExtraOptionsStruct;

typedef struct MatchOptionsStruct_TAG {
    int JPCRE2_ANCHORED;
    int JPCRE2_COPY_MATCHED_SUBJECT;
    int JPCRE2_ENDANCHORED;
    int JPCRE2_NOTBOL;
    int JPCRE2_NOTEOL;
    int JPCRE2_NOTEMPTY;
    int JPCRE2_NOTEMPTY_ATSTART;
    int JPCRE2_NO_JIT;
    int JPCRE2_NO_UTF_CHECK;
    int JPCRE2_PARTIAL_HARD;
    int JPCRE2_PARTIAL_SOFT;
} MatchOptionsStruct;

typedef struct ErrorStruct_TAG {
    char* buffer;
} ErrorStruct;

typedef struct CompileData_TAG {
    pcre2_code *re;
    int errornumber;
    int erroroffset;
} CompileData;

typedef struct GroupData_TAG {
    char** names;
    int* namesnum;
    int namescount;
} GroupData;

// regex struct/array implementation starts here
typedef struct RegexStruct_TAG {
    int numVals;
    char** vals;
    int* ovector;
    char** names;
    int* namesnum;
    int namescount;
    int rc;
} RegexStruct;

void RegexStruct_cleanup(RegexStruct sVal)
{
    int loop = 0;
    for (loop=0; loop<sVal.numVals; loop++)
    {
        free(sVal.vals[loop]);
        //printf("(C) cleaning up sVal.vals[loop]...\n");
    }
    loop = 0;
    for (loop=0; loop<sVal.namescount; loop++){
        free(sVal.names[loop]);
    }
    free(sVal.vals);
    free(sVal.ovector);
    if (sVal.namescount>0){
        free(sVal.names);
        free(sVal.namesnum);
    }
}
// regex struct/array implementation ends here.

void *pcre2_gcontext_create(){
    pcre2_general_context *gcontext;
    gcontext = pcre2_general_context_create(NULL, NULL, NULL);
    return gcontext;
}

void pcre2_gcontext_free(pcre2_general_context *gcontext){
    pcre2_general_context_free(gcontext);
}

void *pcre2_ccontext_create(pcre2_general_context *gcontext){
    pcre2_compile_context *ccontext;
    ccontext = pcre2_compile_context_create(gcontext);
    return ccontext;
}

void pcre2_ccontext_free(pcre2_compile_context *ccontext){
    pcre2_compile_context_free(ccontext);
}

void pcre2_ccontext_set_extra_options(pcre2_compile_context *ccontext, ExtraOptionsStruct *temp){
    uint32_t extra_options = 0;

    if (temp->JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK != 0) {
        extra_options |= PCRE2_EXTRA_ALLOW_LOOKAROUND_BSK;
        }
    if (temp->JPCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES != 0) {
        extra_options |= PCRE2_EXTRA_ALLOW_SURROGATE_ESCAPES;
        }
    if (temp->JPCRE2_EXTRA_ALT_BSUX != 0) {
        extra_options |= PCRE2_EXTRA_ALT_BSUX;
        }
    if (temp->JPCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL != 0) {
        extra_options |= PCRE2_EXTRA_BAD_ESCAPE_IS_LITERAL;
        }
    if (temp->JPCRE2_EXTRA_ESCAPED_CR_IS_LF != 0) {
        extra_options |= PCRE2_EXTRA_ESCAPED_CR_IS_LF;
        }
    if (temp->JPCRE2_EXTRA_MATCH_LINE != 0) {
        extra_options |= PCRE2_EXTRA_MATCH_LINE;
        }
    if (temp->JPCRE2_EXTRA_MATCH_WORD != 0) {
        extra_options |= PCRE2_EXTRA_MATCH_WORD;
        }

    pcre2_set_compile_extra_options(ccontext, extra_options);
}

void *pcre2_mcontext_create(pcre2_general_context *gcontext){
    pcre2_match_context *mcontext;
    mcontext = pcre2_match_context_create(gcontext);
    return mcontext;
}

void pcre2_mcontext_free(pcre2_match_context *mcontext){
    pcre2_match_context_free(mcontext);
}

// Seems to be broken
//ErrorStruct pcre2_translate_error_code(int errorcode) {
//    ErrorStruct errorvals;
//    PCRE2_UCHAR buffer[256];
//    pcre2_get_error_message(errorcode, buffer, sizeof(buffer));
//    printf("(TEST) PCRE2 function failed: %s\n", buffer);
//    errorvals.buffer = buffer;
//    printf("(TEST) PCRE2 function failed: %s\n", errorvals.buffer);
//    return errorvals;
//}

// This version seems to work
void pcre2_translate_error_code_alternative(int errorcode, char** ppszVal) {
    *ppszVal = (char*)malloc(sizeof(char) * 256);
    if (*ppszVal != NULL){
        memset(*ppszVal, '\0', sizeof(char) * 256);
        PCRE2_UCHAR buffer[256];
        pcre2_get_error_message(errorcode, buffer, sizeof(buffer));
        strcpy(*ppszVal, buffer);
    }
}

void errorcleanup(char* pszVal)
{
	free(pszVal);
}

CompileData pcre2_jcompile(char *a, size_t k, OptionsStruct *temp, pcre2_compile_context *ccontext){ // , const OptionsStruct* sval
    pcre2_code *re;
    PCRE2_SPTR pattern;
    pattern = (PCRE2_SPTR)a;
    //char *terminated;
    int errornumber;
    PCRE2_SIZE erroroffset;
    CompileData reVal;

    // constructing the uint32_t option0 parameter for compile function from OptionsStruct values.
    uint32_t option0 = 0;

    if (temp->JPCRE2_ANCHORED != 0) {
        option0 |= PCRE2_ANCHORED;
    }
    if (temp->JPCRE2_ALLOW_EMPTY_CLASS != 0) {
        option0 |= PCRE2_ALLOW_EMPTY_CLASS;
    }
    if (temp->JPCRE2_ALT_BSUX != 0) {
        option0 |= PCRE2_ALT_BSUX;
    }
    if (temp->JPCRE2_ALT_CIRCUMFLEX != 0) {
        option0 |= PCRE2_ALT_CIRCUMFLEX;
    }
    if (temp->JPCRE2_ALT_VERBNAMES != 0) {
        option0 |= PCRE2_ALT_VERBNAMES;
    }
    if (temp->JPCRE2_AUTO_CALLOUT != 0) {
        option0 |= PCRE2_AUTO_CALLOUT;
    }
    if (temp->JPCRE2_CASELESS != 0) {
        option0 |= PCRE2_CASELESS;
    }
    if (temp->JPCRE2_DOLLAR_ENDONLY != 0) {
        option0 |= PCRE2_DOLLAR_ENDONLY;
    }
    if (temp->JPCRE2_DOTALL != 0) {
        option0 |= PCRE2_DOTALL;
    }
    if (temp->JPCRE2_DUPNAMES != 0) {
        option0 |= PCRE2_DUPNAMES;
    }
    if (temp->JPCRE2_ENDANCHORED != 0) {
        option0 |= PCRE2_ENDANCHORED;
    }
    if (temp->JPCRE2_EXTENDED != 0) {
        option0 |= PCRE2_EXTENDED;
    }
    if (temp->JPCRE2_EXTENDED_MORE != 0) {
        option0 |= PCRE2_EXTENDED_MORE;
    }
    if (temp->JPCRE2_FIRSTLINE != 0) {
        option0 |= PCRE2_FIRSTLINE;
    }
    if (temp->JPCRE2_LITERAL != 0) {
        option0 |= PCRE2_LITERAL;
    }
    if (temp->JPCRE2_MATCH_INVALID_UTF != 0) {
        option0 |= PCRE2_MATCH_INVALID_UTF;
    }
    if (temp->JPCRE2_MATCH_UNSET_BACKREF != 0) {
        option0 |= PCRE2_MATCH_UNSET_BACKREF;
    }
    if (temp->JPCRE2_MULTILINE != 0) {
        option0 |= PCRE2_MULTILINE;
    }
    if (temp->JPCRE2_NEVER_BACKSLASH_C != 0) {
        option0 |= PCRE2_NEVER_BACKSLASH_C;
    }
    if (temp->JPCRE2_NEVER_UCP != 0) {
        option0 |= PCRE2_NEVER_UCP;
    }
    if (temp->JPCRE2_NEVER_UTF != 0) {
        option0 |= PCRE2_NEVER_UTF;
    }
    if (temp->JPCRE2_NO_AUTO_CAPTURE != 0) {
        option0 |= PCRE2_NO_AUTO_CAPTURE;
    }
    if (temp->JPCRE2_NO_AUTO_POSSESS != 0) {
        option0 |= PCRE2_NO_AUTO_POSSESS;
    }
    if (temp->JPCRE2_NO_DOTSTAR_ANCHOR != 0) {
        option0 |= PCRE2_NO_DOTSTAR_ANCHOR;
    }
    if (temp->JPCRE2_NO_START_OPTIMIZE != 0) {
        option0 |= PCRE2_NO_START_OPTIMIZE;
    }
    if (temp->JPCRE2_NO_UTF_CHECK != 0) {
        option0 |= PCRE2_NO_UTF_CHECK;
    }
    if (temp->JPCRE2_UCP != 0) {
        option0 |= PCRE2_UCP;
    }
    if (temp->JPCRE2_UNGREEDY != 0) {
        option0 |= PCRE2_UNGREEDY;
    }
    if (temp->JPCRE2_USE_OFFSET_LIMIT != 0) {
        option0 |= PCRE2_USE_OFFSET_LIMIT;
    }
    if (temp->JPCRE2_UTF != 0) {
        option0 |= PCRE2_UTF;
    }

    size_t pattern_length = PCRE2_ZERO_TERMINATED; // default value for finding correct size_t of pattern
    if (k>0) {
        pattern_length = k;
    }


    re = pcre2_compile(
            pattern,               /* the pattern */
            pattern_length,        /* value 0 indicates pattern is zero-terminated, anything higher indicates actual pattern length */
            option0,               /* options, default is 0 */
            &errornumber,          /* for error number */
            &erroroffset,          /* for error offset */
            ccontext);             /* NULL to use default compile context */


/* Compilation failed: print the error message and exit. */
// Pass the compiledata along with errornumber/erroroffset to java, where another function can be called to get error message.
    if (re == NULL)
    {
        //PCRE2_UCHAR buffer[256];
        //pcre2_get_error_message(errornumber, buffer, sizeof(buffer));
//        printf("PCRE2 compilation failed at offset %d: %s\n", (int)erroroffset, buffer);
        reVal.re = NULL;
        reVal.errornumber = errornumber;
        reVal.erroroffset = (int)erroroffset;
        return reVal;
    }
    reVal.re = re;
    reVal.errornumber = 0;
    reVal.erroroffset = 0;
    return reVal;
}

void pcre2_jcompile_free(pcre2_code *re){
    pcre2_code_free(re);
}

int pcre2_get_utf8(pcre2_code *re){
    uint32_t option_bits;
    (void)pcre2_pattern_info(re, PCRE2_INFO_ALLOPTIONS, &option_bits);
    int utf8 = (option_bits & PCRE2_UTF) != 0;
    return utf8;
}

int pcre2_get_crlf_is_newline(pcre2_code *re){
    uint32_t newline;
    (void)pcre2_pattern_info(re, PCRE2_INFO_NEWLINE, &newline);
    int crlf_is_newline = newline == PCRE2_NEWLINE_ANY ||
                          newline == PCRE2_NEWLINE_CRLF ||
                          newline == PCRE2_NEWLINE_ANYCRLF;
    return crlf_is_newline;
}

int pcre2_check_utf8(char temp){
    if ((temp & 0xc0) != 0x80){
        return 1;
    }else{
        return 0;
    }
}

// Provides method to access capture group info after compile but before matching. PCRE2_INFO_NAMETABLE is available via pcre2_pattern_info.
GroupData pcre2_get_info_group(pcre2_code *re){
    PCRE2_SPTR name_table;
    uint32_t namecount;
    uint32_t name_entry_size;
    GroupData temp;
    int i;

    (void)pcre2_pattern_info(
                re,                   /* the compiled pattern */
                PCRE2_INFO_NAMECOUNT, /* get the number of named substrings */
                &namecount);          /* where to put the answer */
        // printf("TESTING NAMECOUNT: %d \r\n", namecount);
        if (namecount == 0){
            temp.namescount = namecount;
            temp.names = (char**)malloc(sizeof(char*) * 1);
            temp.namesnum = (int*)malloc(sizeof(int) * 1);
            if (temp.names == NULL || temp.namesnum == NULL) {
                printf("Error: Out of memory\r\n");
                exit(-1);
            }
            memset(temp.names, '\0', sizeof(char*) * 1);
            memset(temp.namesnum, 0, sizeof(int) * 1);
            // printf("TESTING NAMESCOUNT: %d \r\n", temp.namescount);
        } else
        {
            PCRE2_SPTR tabptr;

            /* Before we can access the substrings, we must extract the table for
            translating names to numbers, and the size of each entry in the table. */

            (void)pcre2_pattern_info(
                    re,                       /* the compiled pattern */
                    PCRE2_INFO_NAMETABLE,     /* address of the table */
                    &name_table);             /* where to put the answer */

            (void)pcre2_pattern_info(
                    re,                       /* the compiled pattern */
                    PCRE2_INFO_NAMEENTRYSIZE, /* size of each entry in the table */
                    &name_entry_size);        /* where to put the answer */

            tabptr = name_table;
            temp.namescount = namecount;
            temp.namesnum = (int*)malloc(sizeof(int) * temp.namescount);
            temp.names = (char**)malloc(sizeof(char*) * temp.namescount);
            if (temp.names == NULL || temp.namesnum == NULL) {
                printf("Error: Out of memory\r\n");
                exit(-1);
            }
            for (i = 0; i < namecount; i++)
            {
                int n = (tabptr[0] << 8) | tabptr[1]; // << is a bitwise left shift operator.
                temp.namesnum[i] = n; // stores the numerical value for name-number pairing to the struct.
                // name table is stored in this format:
                // 00 01 d  a  t  e  00 ??
                // 00 05 d  a  y  00 ?? ??
                // etc.
                // first two bytes (00 and 01) are the number of the capturing parenthesis, and ?? is an undefined byte.
                // last 00 byte seems to be the zero termination of the string.
                temp.names[i] = (char*)malloc(sizeof(char) * ((int)name_entry_size - 2));
                if (temp.names[i] == NULL) {
                    printf("Error: Out of memory\r\n");
                    exit(-1);
                }
                memset(temp.names[i], '\0', sizeof(char) * ((int)name_entry_size - 2)); // initializes the string array with null values.
                memcpy(temp.names[i], (char *)(tabptr + 2), (int)(name_entry_size - 3));

                tabptr += name_entry_size;
            }
        }
        // printf("TESTING NAMESCOUNT: %d \r\n", temp.namescount);
        return temp;
}

void free_group_data(GroupData sVal) {
    // printf("(C) cleaning up GroupData sVal.names[loop]...\n");
    int loop;
    for (loop=0; loop<sVal.namescount; loop++){
            free(sVal.names[loop]);
        }
        if (sVal.namescount>0){
            free(sVal.names);
            free(sVal.namesnum);
        }
}


// this function contains matching for a single match
RegexStruct pcre2_single_jmatch(char *b, pcre2_code *re, int offset, MatchOptionsStruct *temp, pcre2_match_context *mcontext){
    pcre2_match_data *match_data;
    PCRE2_SPTR subject;     /* the appropriate width (in this case, 8 bits). */
    PCRE2_SPTR name_table;
    //int crlf_is_newline;
    int rc;
    //int utf8;

    uint32_t option_bits;
    uint32_t namecount;
    uint32_t name_entry_size;
    uint32_t newline;

    PCRE2_SIZE subject_length;
    PCRE2_SIZE *ovector;

    RegexStruct sVal;

    // constructing the uint32_t option0 parameter for match function from MatchOptionsStruct values.
    uint32_t option0 = 0;
    if (temp->JPCRE2_ANCHORED != 0) {
        option0 |= PCRE2_ANCHORED;
    }
    if (temp->JPCRE2_COPY_MATCHED_SUBJECT != 0) {
        option0 |= PCRE2_COPY_MATCHED_SUBJECT;
    }
    if (temp->JPCRE2_ENDANCHORED != 0) {
        option0 |= PCRE2_ENDANCHORED;
    }
    if (temp->JPCRE2_NOTBOL != 0) {
        option0 |= PCRE2_NOTBOL;
    }
    if (temp->JPCRE2_NOTEOL != 0) {
        option0 |= PCRE2_NOTEOL;
    }
    if (temp->JPCRE2_NOTEMPTY != 0) {
        option0 |= PCRE2_NOTEMPTY;
    }
    if (temp->JPCRE2_NOTEMPTY_ATSTART != 0) {
        option0 |= PCRE2_NOTEMPTY_ATSTART;
    }
    if (temp->JPCRE2_NO_JIT != 0) {
        option0 |= PCRE2_NO_JIT;
    }
    if (temp->JPCRE2_NO_UTF_CHECK != 0) {
        option0 |= PCRE2_NO_UTF_CHECK;
    }
    if (temp->JPCRE2_PARTIAL_HARD != 0) {
        option0 |= PCRE2_PARTIAL_HARD;
    }
    if (temp->JPCRE2_PARTIAL_SOFT != 0) {
        option0 |= PCRE2_PARTIAL_SOFT;
    }


    match_data = pcre2_match_data_create_from_pattern(re, NULL);

    subject = (PCRE2_SPTR)b;
    subject_length = (PCRE2_SIZE)strlen((char *)subject);

/* Now run the match. */
/* When matching is complete, rc will contain the length of the array returned by pcre2_get_ovector_pointer() */
    rc = pcre2_match(
            re,                   /* the compiled pattern */
            subject,              /* the subject string */
            subject_length,       /* the length of the subject */
            offset,               /* start at offset 0 in the subject */
            option0,              /* default option is 0 */
            match_data,           /* block for storing the result */
            mcontext);            /* NULL to use default match context */

/* Matching failed: handle error cases */
    if (rc < 0)
    {
        switch(rc)
        {
            case PCRE2_ERROR_NOMATCH: break;
                /*
                Handle other special cases if you like
                */
            default: break;
        }
        sVal.numVals = 0;
        sVal.rc = rc;
        sVal.vals = (char**)malloc(sizeof(char*) * 1);
        sVal.names = (char**)malloc(sizeof(char*) * 1);
        sVal.namesnum = (int*)malloc(sizeof(int) * 1);
        sVal.ovector = (int*)malloc(sizeof(int) * 1);
        if (sVal.vals == NULL || sVal.names == NULL || sVal.namesnum == NULL || sVal.ovector == NULL) {
            printf("Error: Out of memory\r\n");
            exit(-1);
        }
        memset(sVal.vals, '\0', sizeof(char*) * 1);
        memset(sVal.names, '\0', sizeof(char*) * 1);
        memset(sVal.namesnum, 0, sizeof(int) * 1);
        memset(sVal.ovector, 0, sizeof(int) * 1);
        sVal.namescount = 0;

        // These cause memory error if the same functions are called later in the main program.
        pcre2_match_data_free(match_data);   /* Release memory used for the match */
        //pcre2_code_free(re);                 /*   data and the compiled pattern. */

        return sVal;
    }

    /* Match succeeded. Get a pointer to the output vector, where string offsets
    are stored. */

    ovector = pcre2_get_ovector_pointer(match_data);
//    printf("\novector[0] %d\n", (int)ovector[0]);
//    printf("ovector[1] %d\n", (int)ovector[1]);
//    printf("ovector[2] %d\n", (int)ovector[2]);
//    printf("ovector[3] %d\n", (int)ovector[3]);
//    printf("ovector[4] %d\n", (int)ovector[4]);


    sVal.numVals = rc;
    sVal.rc = rc;
    sVal.vals = (char**)malloc(sizeof(char*) * sVal.numVals);
    sVal.ovector = (int*)malloc(sizeof(int) * (2 + (sVal.numVals * 2)));

    if (sVal.vals == NULL || sVal.ovector == NULL) {
        printf("Error: Out of memory\r\n");
        exit(-1);
    }
    memset(sVal.vals, '\0', sizeof(char*) * sVal.numVals);
    memset(sVal.ovector, 0, sizeof(int) * (2 + (sVal.numVals * 2)));
    sVal.ovector[0] = (int)ovector[0];
    sVal.ovector[1] = (int)ovector[1];

    int i;
    for (i = 0; i < rc; i++)
    {
        PCRE2_SPTR substring_start = subject + ovector[2*i];
        PCRE2_SIZE substring_length = ovector[2*i+1] - ovector[2*i];
        sVal.ovector[i+2] = (int)ovector[i+2];
        sVal.ovector[i+3] = (int)ovector[i+3];

        sVal.vals[i] = (char*)malloc(sizeof(char) * ((int)substring_length + 1));
        if (sVal.vals[i] == NULL) {
            printf("Error: Out of memory\r\n");
            exit(-1);
        }
        memset(sVal.vals[i], '\0', sizeof(char) * ((int)substring_length + 1)); // initializes the array with null values.
        memcpy(sVal.vals[i], (char *)substring_start, (int)substring_length);
    }

    // NAMED SUBSTRINGS START HERE

    (void)pcre2_pattern_info(
            re,                   /* the compiled pattern */
            PCRE2_INFO_NAMECOUNT, /* get the number of named substrings */
            &namecount);          /* where to put the answer */

    if (namecount == 0){
        sVal.namescount = namecount;
        sVal.names = (char**)malloc(sizeof(char*) * 1);
        sVal.namesnum = (int*)malloc(sizeof(int) * 1);
        if (sVal.names == NULL || sVal.namesnum == NULL) {
            printf("Error: Out of memory\r\n");
            exit(-1);
        }
        memset(sVal.names, '\0', sizeof(char*) * 1);
        memset(sVal.namesnum, 0, sizeof(int) * 1);
    } else
    {
        PCRE2_SPTR tabptr;

        /* Before we can access the substrings, we must extract the table for
        translating names to numbers, and the size of each entry in the table. */

        (void)pcre2_pattern_info(
                re,                       /* the compiled pattern */
                PCRE2_INFO_NAMETABLE,     /* address of the table */
                &name_table);             /* where to put the answer */

        (void)pcre2_pattern_info(
                re,                       /* the compiled pattern */
                PCRE2_INFO_NAMEENTRYSIZE, /* size of each entry in the table */
                &name_entry_size);        /* where to put the answer */

        /* Now we can scan the table and, for each entry, print the number, the name,
        and the substring itself. In the 8-bit library the number is held in two
        bytes, most significant first. */

        tabptr = name_table;
        sVal.namescount = namecount;
        sVal.namesnum = (int*)malloc(sizeof(int) * sVal.namescount);
        sVal.names = (char**)malloc(sizeof(char*) * sVal.namescount);
        if (sVal.names == NULL || sVal.namesnum == NULL) {
            printf("Error: Out of memory\r\n");
            exit(-1);
        }
        for (i = 0; i < namecount; i++)
        {
            int n = (tabptr[0] << 8) | tabptr[1]; // << is a bitwise left shift operator.
            sVal.namesnum[i] = n; // stores the numerical value for name-number pairing to the struct.
            // name table is stored in this format:
            // 00 01 d  a  t  e  00 ??
            // 00 05 d  a  y  00 ?? ??
            // etc.
            // first two bytes (00 and 01) are the number of the capturing parenthesis, and ?? is an undefined byte.
            // last 00 byte seems to be the zero termination of the string.
            sVal.names[i] = (char*)malloc(sizeof(char) * ((int)name_entry_size - 2));
            if (sVal.names[i] == NULL) {
                printf("Error: Out of memory\r\n");
                exit(-1);
            }
            memset(sVal.names[i], '\0', sizeof(char) * ((int)name_entry_size - 2)); // initializes the string array with null values.
            memcpy(sVal.names[i], (char *)(tabptr + 2), (int)(name_entry_size - 3));


            //printf("(%d) %*s: %.*s\n", n, name_entry_size - 3, tabptr + 2, (int)(ovector[2*n+1] - ovector[2*n]), subject + ovector[2*n]);
            tabptr += name_entry_size;
        }
    }
    // (name_entry_size - 3), (tabptr + 2); is the name of the named substring. former value is the length of the string, latter is the pointer to the string.
    // (int)(ovector[2*n+1] - ovector[2*n]), subject + ovector[2*n]); is the contents of the named substring. former value is the length of the string, latter is the pointer to the string.

    // NAMED SUBSTRINGS END

    pcre2_match_data_free(match_data);
    return sVal;
}

void pcre2_jmatch_free(pcre2_match_data *match_data){
    pcre2_match_data_free(match_data);
}

void pcre2_versioncheck(){
    char version[255];
    pcre2_config(PCRE2_CONFIG_VERSION, version);
    printf("This library was programmed for PCRE2 version 10.40 or newer\nThe current PCRE2 version in use is: %s\n", version);
}

// Main function that is used only for testing the functions included in this library.
int main(void) {
    pcre2_versioncheck();
    char testi[] = "From:regular.expressions@example.com\r\n""From:exddd@43434.com\r\n""From:7853456@exgem.com\r\n";
    //char testi[] = "Kissa sanoi miau!";
    //pcre2_java("From:([^@]+)@([^\r]+)", testi, 1);
    pcre2_code *re;
    // uint32_t options = PCRE2_ANCHORED | PCRE2_NO_UTF_CHECK;
    OptionsStruct kikkare;
    kikkare.JPCRE2_ANCHORED=0;
    kikkare.JPCRE2_ALLOW_EMPTY_CLASS=0;
    kikkare.JPCRE2_ALT_BSUX=0;
    kikkare.JPCRE2_ALT_CIRCUMFLEX=0;
    kikkare.JPCRE2_ALT_VERBNAMES=0;
    kikkare.JPCRE2_AUTO_CALLOUT=0;
    kikkare.JPCRE2_CASELESS=0;
    kikkare.JPCRE2_DOLLAR_ENDONLY=0;
    kikkare.JPCRE2_DOTALL=0;
    kikkare.JPCRE2_DUPNAMES=0;
    kikkare.JPCRE2_ENDANCHORED=0;
    kikkare.JPCRE2_EXTENDED=0;
    kikkare.JPCRE2_EXTENDED_MORE=0;
    kikkare.JPCRE2_FIRSTLINE=0;
    kikkare.JPCRE2_LITERAL=0;
    kikkare.JPCRE2_MATCH_INVALID_UTF=0;
    kikkare.JPCRE2_MATCH_UNSET_BACKREF=0;
    kikkare.JPCRE2_MULTILINE=0;
    kikkare.JPCRE2_NEVER_BACKSLASH_C=0;
    kikkare.JPCRE2_NEVER_UCP=0;
    kikkare.JPCRE2_NEVER_UTF=0;
    kikkare.JPCRE2_NO_AUTO_CAPTURE=0;
    kikkare.JPCRE2_NO_AUTO_POSSESS=0;
    kikkare.JPCRE2_NO_DOTSTAR_ANCHOR=0;
    kikkare.JPCRE2_NO_START_OPTIMIZE=0;
    kikkare.JPCRE2_NO_UTF_CHECK=0;
    kikkare.JPCRE2_UCP=0;
    kikkare.JPCRE2_UNGREEDY=0;
    kikkare.JPCRE2_USE_OFFSET_LIMIT=0;
    kikkare.JPCRE2_UTF=0;

    MatchOptionsStruct kikkare2;
    kikkare2.JPCRE2_ANCHORED=0;
    kikkare2.JPCRE2_COPY_MATCHED_SUBJECT=0;
    kikkare2.JPCRE2_ENDANCHORED=0;
    kikkare2.JPCRE2_NOTBOL=0;
    kikkare2.JPCRE2_NOTEOL=0;
    kikkare2.JPCRE2_NOTEMPTY=0;
    kikkare2.JPCRE2_NOTEMPTY_ATSTART=0;
    kikkare2.JPCRE2_NO_JIT=0;
    kikkare2.JPCRE2_NO_UTF_CHECK=0;
    kikkare2.JPCRE2_PARTIAL_HARD=0;
    kikkare2.JPCRE2_PARTIAL_SOFT=0;


    pcre2_general_context *gcontext;
    gcontext = pcre2_general_context_create(NULL, NULL, NULL);
    pcre2_compile_context *ccontext;
    ccontext = pcre2_compile_context_create(gcontext);
    pcre2_match_context *mcontext;
    mcontext = pcre2_match_context_create(gcontext);



    RegexStruct testStruct;
    int i;
    int offset = 0;
    //init_StructArray(testStructs, pNumofstructs);
    CompileData reVal = pcre2_jcompile("From:(?<username>[^@]+)@(?<emailprovider>[^\r]+)", 0, &kikkare, ccontext);
    //re = pcre2_jcompile("From:(?<username>[^@]+)@(?<emailprovider>[^\r]+)", 0, &kikkare, ccontext);
    re = reVal.re;
    // error handling
    if (re == NULL){
        return 0;
    }

    testStruct = pcre2_single_jmatch(testi, re, offset, &kikkare2, mcontext);
    // Print the first member of the struct array
    printf("Match succeeded at offset %d\n", testStruct.ovector[0] );
    if (testStruct.namescount > 0){
        printf("Printing the named substrings:\n");
        for (i = 0; i < testStruct.namescount; i++){
            printf("\t(%d) %s: %s\n", testStruct.namesnum[i], testStruct.names[i], testStruct.vals[testStruct.namesnum[i]]);
        }
    }else{
        printf("No named substrings found!\n");
    }
    offset = testStruct.ovector[1];
    RegexStruct_cleanup(testStruct);

    // Print the second member of the struct array
    testStruct = pcre2_single_jmatch(testi, re, offset, &kikkare2, mcontext);
    printf("Match succeeded at offset %d\n", testStruct.ovector[0] );
    if (testStruct.namescount > 0){
        printf("Printing the named substrings:\n");
        for (i = 0; i < testStruct.namescount; i++){
            printf("\t(%d) %s: %s\n", testStruct.namesnum[i], testStruct.names[i], testStruct.vals[testStruct.namesnum[i]]);
        }
    }else{
        printf("No named substrings found!\n");
    }
    offset = testStruct.ovector[1];
    RegexStruct_cleanup(testStruct);

    // Print the third member of the struct array
    testStruct = pcre2_single_jmatch(testi, re, offset, &kikkare2, mcontext);
    printf("Match succeeded at offset %d\n", testStruct.ovector[0] );
    if (testStruct.namescount > 0){
        printf("Printing the named substrings:\n");
        for (i = 0; i < testStruct.namescount; i++){
            printf("\t(%d) %s: %s\n", testStruct.namesnum[i], testStruct.names[i], testStruct.vals[testStruct.namesnum[i]]);
        }
    }else{
        printf("No named substrings found!\n");
    }
    offset = testStruct.ovector[1];
    RegexStruct_cleanup(testStruct);

    // Print the fourth member of the struct array if it exists
    testStruct = pcre2_single_jmatch(testi, re, offset, &kikkare2, mcontext);
    printf("Match succeeded at offset %d\n", testStruct.ovector[0] );
    if (testStruct.namescount > 0){
        printf("Printing the named substrings:\n");
        for (i = 0; i < testStruct.namescount; i++){
            printf("\t(%d) %s: %s\n", testStruct.namesnum[i], testStruct.names[i], testStruct.vals[testStruct.namesnum[i]]);
        }
    }else{
        printf("No named substrings found!\n");
    }
    offset = testStruct.ovector[1];
    RegexStruct_cleanup(testStruct);

    pcre2_jcompile_free(re);
        pcre2_match_context_free(mcontext);
        pcre2_compile_context_free(ccontext);
        pcre2_general_context_free(gcontext);

    return 0;
}


