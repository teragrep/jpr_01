
#define PCRE2_CODE_UNIT_WIDTH 8

#include <stdio.h>
#include <string.h>
#include <pcre2.h>

// compiler commands for this file (when working from jpr-01 folder):
// gcc -c -Wall -Werror -fpic src/main/c/pcre2demo.c -o src/main/c/pcre2demo.o -lpcre2-8
// gcc -shared -Wl,-soname,libpcre2demo.so -o src/main/c/libpcre2demo.so src/main/c/pcre2demo.o -lpcre2-8

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

// DONE?: regex struct/array implementation starts here
typedef struct RegexStruct_TAG {
    int numVals;
    char** vals;
    int* ovector;
} RegexStruct;

void RegexStruct_cleanup(RegexStruct sVal)
{
	int loop = 0;
	for (loop=0; loop>sVal.numVals; loop++)
	{
		free(sVal.vals[loop]);
		//printf("(C) cleaning up sVal.vals[loop]...\n");
	}
	free(sVal.vals);
	free(sVal.ovector);
}
// DONE: regex struct/array implementation ends here.

void *pcre2_jcompile(char *a, size_t k, OptionsStruct *temp){ // , const OptionsStruct* sval
    pcre2_code *re;
    PCRE2_SPTR pattern;
    pattern = (PCRE2_SPTR)a;
    //char *terminated;
    int errornumber;
    PCRE2_SIZE erroroffset;

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

    //printf("pcre2_compile starting.\n");
    //printf("options value is: %d\n", option0);
    re = pcre2_compile(
            pattern,               /* the pattern */
            pattern_length,        /* value 0 indicates pattern is zero-terminated, anything higher indicates actual pattern length */
            option0,               /* options */
            &errornumber,          /* for error number */
            &erroroffset,          /* for error offset */
            NULL);                 /* use default compile context */


/* Compilation failed: print the error message and exit. */

    if (re == NULL)
    {
        PCRE2_UCHAR buffer[256];
        pcre2_get_error_message(errornumber, buffer, sizeof(buffer));
        printf("PCRE2 compilation failed at offset %d: %s\n", (int)erroroffset,
               buffer);
        return NULL;
    }
    //printf("pcre2_compile went fine.\n");
    return re;
}

void pcre2_jcompile_free(pcre2_code *re){
    //printf("Releasing data and compiled pattern from memory.\n");
    pcre2_code_free(re);
    //printf("Memory released successfully.\n");
}






// TODO: MAKE THIS THE PRIMARY FUNCTION FOR SINGLE AND SUBSEQUENT MATCHES. Handle it partially in java side.
// this function contains matching for a single match
RegexStruct pcre2_single_jmatch(char *b, pcre2_code *re, int offset){
    pcre2_match_data *match_data;
    PCRE2_SPTR subject;     /* the appropriate width (in this case, 8 bits). */
    //int crlf_is_newline;
    int rc;
    //int utf8;

    uint32_t option_bits;
    uint32_t newline;

    PCRE2_SIZE subject_length;
    PCRE2_SIZE *ovector;

    match_data = pcre2_match_data_create_from_pattern(re, NULL);

    subject = (PCRE2_SPTR)b;
    subject_length = (PCRE2_SIZE)strlen((char *)subject);

    // check if this is the first or subsequent match
    if(offset != 0){

    /* Before running the subsequent matches, check for UTF-8 and whether CRLF is a valid newline
    sequence. First, find the options with which the regex was compiled and extract
    the UTF state. */

        (void)pcre2_pattern_info(re, PCRE2_INFO_ALLOPTIONS, &option_bits);
        //utf8 = (option_bits & PCRE2_UTF) != 0;

    /* Now find the newline convention and see whether CRLF is a valid newline
    sequence. */

        (void)pcre2_pattern_info(re, PCRE2_INFO_NEWLINE, &newline);
//        crlf_is_newline = newline == PCRE2_NEWLINE_ANY ||
//                          newline == PCRE2_NEWLINE_CRLF ||
//                          newline == PCRE2_NEWLINE_ANYCRLF;
    }

/* Now run the match. */

/* When matching is complete, rc will contain the length of the array returned by pcre2_get_ovector_pointer() */
    rc = pcre2_match(
            re,                   /* the compiled pattern */
            subject,              /* the subject string */
            subject_length,       /* the length of the subject */
            offset,               /* start at offset 0 in the subject */
            0,                    /* default options */
            match_data,           /* block for storing the result */
            NULL);                /* use default match context */

/* Matching failed: handle error cases */

    if (rc < 0)
    {
        switch(rc)
        {
            case PCRE2_ERROR_NOMATCH: printf("No match\n"); break;
                /*
                Handle other special cases if you like
                */
            default: printf("Matching error %d\n", rc); break;
        }
        RegexStruct sVal;
        sVal.numVals = 0;
        sVal.vals = (char**)malloc(sizeof(char*) * 1);
        sVal.ovector = (int*)malloc(sizeof(int) * 1);
        if (sVal.vals == NULL) {
            printf("Error: Out of memory\r\n");
            exit(-1);
        }else if(sVal.ovector == NULL) {
                         printf("Error: Out of memory\r\n");
                         exit(-1);
                     }
        memset(sVal.vals, 0, sizeof(char*) * 1);
        memset(sVal.ovector, 0, sizeof(int) * 1);

        // These cause memory error if the same functions are called later in the main program.
        //pcre2_match_data_free(match_data);   /* Release memory used for the match */
        //pcre2_code_free(re);                 /*   data and the compiled pattern. */

        return sVal;
    }

    /* Match succeeded. Get a pointer to the output vector, where string offsets
    are stored. */

    // TODO: add the RegexStruct magic
    ovector = pcre2_get_ovector_pointer(match_data);
    //printf("Match succeeded at offset %d with rc length of %d\n", (int)ovector[0], rc);

    RegexStruct sVal;
    sVal.numVals = rc;
    sVal.vals = (char**)malloc(sizeof(char*) * sVal.numVals);
    sVal.ovector = (int*)malloc(sizeof(int) * (2 + (sVal.numVals * 2)));

    if (sVal.vals == NULL) {
                printf("Error: Out of memory\r\n");
                exit(-1);
            }else if(sVal.ovector == NULL) {
                printf("Error: Out of memory\r\n");
                exit(-1);
            }
    memset(sVal.vals, 0, sizeof(char*) * sVal.numVals);
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
            //printf("%2d: %.*s\n", i, (int)substring_length, (char *)substring_start);

            sVal.vals[i] = (char*)malloc(sizeof(char) * ((int)substring_length + 1));
            if (sVal.vals[i] == NULL) {
                        printf("Error: Out of memory\r\n");
                        exit(-1);
                    }
            memset(sVal.vals[i], 0, sizeof(char) * ((int)substring_length + 1)); // initializes the array with null values.
            memcpy(sVal.vals[i], (char *)substring_start, (int)substring_length);

            // TESTING SECTION
            //strncpy(sVal.vals[i], (char *)substring_start, (int)substring_length);
            //printf("%2d: %s\n", i, sVal.vals[i]);
        }

    //pcre2_match_data_free(match_data);
    return sVal;
    }

void pcre2_jmatch_free(pcre2_match_data *match_data){
//    printf("Releasing match_data from memory.\n");
    pcre2_match_data_free(match_data);
//    printf("Memory released successfully.\n");
}

void pcre2_versioncheck(){
    char version[255];
    pcre2_config(PCRE2_CONFIG_VERSION, version);
    printf("This library was programmed for PCRE2 version 10.40\nThe current PCRE2 version in use is: %s\n", version);
}



int main(void) {
    //pcre2_versioncheck();
    //char testi[] = "From:regular.expressions@example.com\r\n""From:exddd@43434.com\r\n""From:7853456@exgem.com\r\n";
    //char testi[] = "Kissa sanoi miau!";
    //pcre2_java("From:([^@]+)@([^\r]+)", testi, 1);
    //pcre2_code *re;
    // uint32_t options = PCRE2_ANCHORED | PCRE2_NO_UTF_CHECK;
    //re = pcre2_jcompile("From:([^@]+)@([^\r]+)", 0);
    //pcre2_jmatch(testi, re, 1);
    //pcre2_jcompile_free(re);
    return 0;
}


