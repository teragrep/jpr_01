pkglib_LTLIBRARIES = libJavaPcre.la

libJavaPcre_la_SOURCES = JavaPcre.c
libJavaPcre_la_CPPFLAGS = -I$(top_srcdir) $(PTHREADS_CFLAGS) $(RSRT_CFLAGS) $(JAVA_INCLUDES)
libJavaPcre_la_LIBADD = $(lpcre2-8)
libJavaPcre_la_LDFLAGS =