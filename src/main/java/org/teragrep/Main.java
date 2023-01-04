package org.teragrep;

import java.util.Map;

/*This is the test module for testing the pcre-regex library from Java.
* The functions called here will represent the functions that are available to use in the aforementioned library.*/
public class Main {
    public static void main(String[] args) {
        int a;
        String subject = "From:regular.expression@example.com\r\n" + "From:exddd@43434.com\r\n" + "From:7853456@exgem.com\r\n";
        String pattern = "From:(?<nimi>[^@]+)@(?<sposti>[^\r]+)";
        JavaPcre s1 = new JavaPcre(); // also initializes all the compiler options at default pcre2 values.

        // change compiler parameters before compiling:
        s1.compile_options.JPCRE2_UTF = true;                           // enable option for compiling, always disabled by default.
        s1.compile_options.JPCRE2_UTF = false;                          // disable option for compiling
        s1.pattern_size = 0;                                            // default pattern size, value 0 will set the pcre2_compile length option to PCRE2_ZERO_TERMINATED.

        // Optional compiler parameters:
        s1.pcre2_gcontext_create();                                     // initializes general context parameter which is used for generating compiler/match context.
        s1.pcre2_ccontext_create();                                     // initializes compiler context parameter which contains extra options that don't fit inside compile_options.
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = true;      // enables extra_option parameter
        s1.extra_options.JPCRE2_EXTRA_ALLOW_LOOKAROUND_BSK = false;     // disables extra_option parameter
        s1.pcre2_ccontext_set_extra_options();                          // inject the extra_option modifiers to the compiler context.
        // etc.

        try {
            s1.pcre2_compile_java(pattern);
        }catch (Exception e){
            System.out.println(e);
            return;
        }


        // change matching options and parameters before matching:
        s1.offset = 0;                              // offset at where to start the match
        s1.match_options.JPCRE2_ANCHORED = true;    // enabling option for matching
        s1.match_options.JPCRE2_ANCHORED = false;   // disabling option for matching
        s1.pcre2_mcontext_create();                 // initializes match context, no real use at the moment.
        //etc.


        // a simple loop for getting all match groups at once:
        boolean matchfound = true;  // for allowing the initial run of matching
        int laskuri = 1;            // simple counter for match group numbering.
        while (matchfound) {
            try {
                s1.pcre2_singlematch_java(subject, s1.offset);
            }catch (Exception e){
                System.out.println(e);
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

                // handle error recovery for no_match error
            } else {
                if (s1.checkoptionzero()){break;} // if no options set, can only mean all matches found correctly.
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
                matchfound = s1.matchfound;
            }
        }

        // Make sure to release compiled data from memory. Compiled pattern data is not freed automatically as it is used multiple times by matching.
        // Also remember to free all initialized context data.
        s1.pcre2_mcontext_free();
        s1.pcre2_ccontext_free();
        s1.pcre2_gcontext_free();
        s1.pcre2_Jcompile_free();
    }
}