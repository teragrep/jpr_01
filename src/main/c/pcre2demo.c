
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

void RegexStruct_array_cleanup(RegexStruct* pStruct, int pNumofstructs)
{
    // every malloc requires a free!
    for(int i=0; i<pNumofstructs; i++){
        for(int j=0; j<pStruct[i].numVals; j++){
            free(pStruct[i].vals[j]);
        }
        free(pStruct[i].vals);
        free(pStruct[i].ovector);
    }
    //printf("(C) cleaning up memory...\n");
    free(pStruct);
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












/* This is the matching function. This does only one match, further matching will be done below */
void pcre2_all_jmatch(char *b, pcre2_code *re, RegexStruct** ppStructs, int* pNumofstructs){
    pcre2_match_data *match_data;
    PCRE2_SPTR subject;     /* the appropriate width (in this case, 8 bits). */
    PCRE2_SPTR name_table;

    int crlf_is_newline;
    int rc;
    int find_all = 1;
    int i;
    int utf8;
    int hits = 0;

    uint32_t option_bits;
    uint32_t namecount;
    uint32_t name_entry_size;
    uint32_t newline;

    PCRE2_SIZE subject_length;
    PCRE2_SIZE *ovector;

    match_data = pcre2_match_data_create_from_pattern(re, NULL);

    subject = (PCRE2_SPTR)b;
    subject_length = (PCRE2_SIZE)strlen((char *)subject);
/* Now run the match. */

/* When matching is complete, rc will contain the length of the array returned by pcre2_get_ovector_pointer() */
    rc = pcre2_match(
            re,                   /* the compiled pattern */
            subject,              /* the subject string */
            subject_length,       /* the length of the subject */
            0,                    /* start at offset 0 in the subject */
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
        pcre2_match_data_free(match_data);   /* Release memory used for the match */
        pcre2_code_free(re);                 /*   data and the compiled pattern. */
        return;
    }

/* Match succeeded. Get a pointer to the output vector, where string offsets
are stored. */

    ovector = pcre2_get_ovector_pointer(match_data);
    hits++;
    printf("Match succeeded at offset %d\n", (int)ovector[0]);
/*************************************************************************
* We have found the first match within the subject string. If the output *
* vector wasn't big enough, say so. Then output any substrings that were *
* captured.                                                              *
*************************************************************************/

/* The output vector wasn't big enough. This should not happen, because we used
pcre2_match_data_create_from_pattern() above. */

    if (rc == 0)
        printf("ovector was not big enough for all the captured substrings\n");

/* Since release 10.38 PCRE2 has locked out the use of \K in lookaround
assertions. However, there is an option to re-enable the old behaviour. If that
is set, it is possible to run patterns such as /(?=.\K)/ that use \K in an
assertion to set the start of a match later than its end. In this demonstration
program, we show how to detect this case, but it shouldn't arise because the
option is never set. */

    if (ovector[0] > ovector[1])
    {
        printf("\\K was used in an assertion to set the match start after its end.\n"
               "From end to start the match was: %.*s\n", (int)(ovector[0] - ovector[1]),
               (char *)(subject + ovector[1]));
        printf("Run abandoned\n");
        pcre2_match_data_free(match_data);
        pcre2_code_free(re);
        return;
    }

/* Show substrings stored in the output vector by number. Obviously, in a real
application you might want to do things other than print them. */

    for (i = 0; i < rc; i++)
    {
        PCRE2_SPTR substring_start = subject + ovector[2*i];
        PCRE2_SIZE substring_length = ovector[2*i+1] - ovector[2*i];
        printf("%2d: %.*s\n", i, (int)substring_length, (char *)substring_start);
    }

//    for (i = 0; i < rc; i++)
//    {
//        PCRE2_UCHAR buffer[256];
//        PCRE2_SIZE *bufflen;
//        bufflen = malloc(sizeof(PCRE2_SIZE));
//        pcre2_substring_copy_bynumber(match_data, i, buffer, bufflen);
//        printf("%2d: %s\n", i, buffer);
//        free(bufflen);
//    }


/**************************************************************************
* That concludes the basic part of this demonstration program. We have    *
* compiled a pattern, and performed a single match. The code that follows *
* shows first how to access named substrings, and then how to code for    *
* repeated matches on the same subject.                                   *
**************************************************************************/

/* See if there are any named substrings, and if so, show them by name. First
we have to extract the count of named parentheses from the pattern. */

    (void)pcre2_pattern_info(
            re,                   /* the compiled pattern */
            PCRE2_INFO_NAMECOUNT, /* get the number of named substrings */
            &namecount);          /* where to put the answer */

    if (namecount == 0) printf("No named substrings\n"); else
    {
        PCRE2_SPTR tabptr;
        printf("Named substrings\n");

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
        for (i = 0; i < namecount; i++)
        {
            int n = (tabptr[0] << 8) | tabptr[1];
            printf("(%d) %*s: %.*s\n", n, name_entry_size - 3, tabptr + 2,
                   (int)(ovector[2*n+1] - ovector[2*n]), subject + ovector[2*n]);
            tabptr += name_entry_size;
        }
    }


/*************************************************************************
* If the "-g" option was given on the command line, we want to continue  *
* to search for additional matches in the subject string, in a similar   *
* way to the /g option in Perl. This turns out to be trickier than you   *
* might think because of the possibility of matching an empty string.    *
* What happens is as follows:                                            *
*                                                                        *
* If the previous match was NOT for an empty string, we can just start   *
* the next match at the end of the previous one.                         *
*                                                                        *
* If the previous match WAS for an empty string, we can't do that, as it *
* would lead to an infinite loop. Instead, a call of pcre2_match() is    *
* made with the PCRE2_NOTEMPTY_ATSTART and PCRE2_ANCHORED flags set. The *
* first of these tells PCRE2 that an empty string at the start of the    *
* subject is not a valid match; other possibilities must be tried. The   *
* second flag restricts PCRE2 to one match attempt at the initial string *
* position. If this match succeeds, an alternative to the empty string   *
* match has been found, and we can print it and proceed round the loop,  *
* advancing by the length of whatever was found. If this match does not  *
* succeed, we still stay in the loop, advancing by just one character.   *
* In UTF-8 mode, which can be set by (*UTF) in the pattern, this may be  *
* more than one byte.                                                    *
*                                                                        *
* However, there is a complication concerned with newlines. When the     *
* newline convention is such that CRLF is a valid newline, we must       *
* advance by two characters rather than one. The newline convention can  *
* be set in the regex by (*CR), etc.; if not, we must find the default.  *
*************************************************************************/

    if (!find_all)     /* Check for -g */
    {
        pcre2_match_data_free(match_data);  /* Release the memory that was used */
        pcre2_code_free(re);                /* for the match data and the pattern. */
        return;                           /* Exit the program. */
    }

/* Before running the loop, check for UTF-8 and whether CRLF is a valid newline
sequence. First, find the options with which the regex was compiled and extract
the UTF state. */

    (void)pcre2_pattern_info(re, PCRE2_INFO_ALLOPTIONS, &option_bits);
    utf8 = (option_bits & PCRE2_UTF) != 0;

/* Now find the newline convention and see whether CRLF is a valid newline
sequence. */

    (void)pcre2_pattern_info(re, PCRE2_INFO_NEWLINE, &newline);
    crlf_is_newline = newline == PCRE2_NEWLINE_ANY ||
                      newline == PCRE2_NEWLINE_CRLF ||
                      newline == PCRE2_NEWLINE_ANYCRLF;

/* Loop for second and subsequent matches */

    for (;;)
    {
        uint32_t options = 0;                   /* Normally no options */
        PCRE2_SIZE start_offset = ovector[1];   /* Start at end of previous match */

        /* If the previous match was for an empty string, we are finished if we are
        at the end of the subject. Otherwise, arrange to run another match at the
        same point to see if a non-empty match can be found. */

        if (ovector[0] == ovector[1])
        {
            if (ovector[0] == subject_length) break;
            options = PCRE2_NOTEMPTY_ATSTART | PCRE2_ANCHORED;
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
            PCRE2_SIZE startchar = pcre2_get_startchar(match_data);
            if (start_offset <= startchar)
            {
                if (startchar >= subject_length) break;   /* Reached end of subject.   */
                start_offset = startchar + 1;             /* Advance by one character. */
                if (utf8)                                 /* If UTF-8, it may be more  */
                {                                       /*   than one code unit.     */
                    for (; start_offset < subject_length; start_offset++)
                        if ((subject[start_offset] & 0xc0) != 0x80) break;
                }
            }
        }

        /* Run the next matching operation */

        rc = pcre2_match(
                re,                   /* the compiled pattern */
                subject,              /* the subject string */
                subject_length,       /* the length of the subject */
                start_offset,         /* starting offset in the subject */
                options,              /* options */
                match_data,           /* block for storing the result */
                NULL);                /* use default match context */

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

        if (rc == PCRE2_ERROR_NOMATCH)
        {
            if (options == 0) break;                    /* All matches found */
            ovector[1] = start_offset + 1;              /* Advance one code unit */
            if (crlf_is_newline &&                      /* If CRLF is a newline & */
                start_offset < subject_length - 1 &&    /* we are at CRLF, */
                subject[start_offset] == '\r' &&
                subject[start_offset + 1] == '\n')
                ovector[1] += 1;                          /* Advance by one more. */
            else if (utf8)                              /* Otherwise, ensure we */
            {                                         /* advance a whole UTF-8 */
                while (ovector[1] < subject_length)       /* character. */
                {
                    if ((subject[ovector[1]] & 0xc0) != 0x80) break;
                    ovector[1] += 1;
                }
            }
            continue;    /* Go round the loop again */
        }

        /* Other matching errors are not recoverable. */

        if (rc < 0)
        {
            printf("Matching error %d\n", rc);
            pcre2_match_data_free(match_data);
            pcre2_code_free(re);
            return;
        }

        /* Match succeeded */

        printf("\nMatch succeeded again at offset %d\n", (int)ovector[0]);
        hits++;

        /* The match succeeded, but the output vector wasn't big enough. This
        should not happen. */

        if (rc == 0)
            printf("ovector was not big enough for all the captured substrings\n");

        /* We must guard against patterns such as /(?=.\K)/ that use \K in an
        assertion to set the start of a match later than its end. In this
        demonstration program, we just detect this case and give up. */

        if (ovector[0] > ovector[1])
        {
            printf("\\K was used in an assertion to set the match start after its end.\n"
                   "From end to start the match was: %.*s\n", (int)(ovector[0] - ovector[1]),
                   (char *)(subject + ovector[1]));
            printf("Run abandoned\n");
            pcre2_match_data_free(match_data);
            pcre2_code_free(re);
            return;
        }

        /* As before, show substrings stored in the output vector by number, and then
        also any named substrings. */

        for (i = 0; i < rc; i++)
        {
            PCRE2_SPTR substring_start = subject + ovector[2*i];
            size_t substring_length = ovector[2*i+1] - ovector[2*i];
            printf("%2d: %.*s\n", i, (int)substring_length, (char *)substring_start);
        }

        if (namecount == 0) printf("No named substrings\n"); else
        {
            PCRE2_SPTR tabptr = name_table;
            printf("Named substrings\n");
            for (i = 0; i < namecount; i++)
            {
                int n = (tabptr[0] << 8) | tabptr[1];
                printf("(%d) %*s: %.*s\n", n, name_entry_size - 3, tabptr + 2,
                       (int)(ovector[2*n+1] - ovector[2*n]), subject + ovector[2*n]);
                tabptr += name_entry_size;
            }
        }
    }      /* End of loop to find second and subsequent matches */

    printf("\n");
    pcre2_match_data_free(match_data);
    pcre2_code_free(re);
    printf("Total number of hits: %d\n", hits);

    // DONE: put structure implementation here so it can be passed back to java.
    *pNumofstructs = hits;
    *ppStructs = (RegexStruct*)malloc(sizeof(RegexStruct) * (*pNumofstructs));
    memset(*ppStructs, 0, sizeof(RegexStruct) * (*pNumofstructs));

//    for(int i=0; i<*pNumofstructs; i++){
//            for(int j=0; j<pStruct[i].numVals; j++){
//                free(pStruct[i].vals[j]);
//            }
//            free(pStruct[i].vals);
//        }

    return;
}

void pcre2_versioncheck(){
    char version[255];
    pcre2_config(PCRE2_CONFIG_VERSION, version);
    printf("This library was programmed for PCRE2 version 10.40\nThe current PCRE2 version in use is: %s\n", version);
}

void printString(char *myString) {
    printf("Printing a test word: %s\n", myString);
}

void Options_sendStruct(const OptionsStruct* soptions){
    // note: printfs called from C won't be flushed
    // to stdout until the Java process completes
    printf("(C) %d\n", soptions->JPCRE2_ANCHORED);
}



int main(void) {
    pcre2_versioncheck();
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


