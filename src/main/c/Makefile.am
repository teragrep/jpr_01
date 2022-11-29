# Build a libtool library, libJavaPcre.la.
pkglib_LTLIBRARIES = libJavaPcre.la
libJavaPcre_la_SOURCES = JavaPcre.c
libJavaPcre_la_CPPFLAGS = -I$(top_srcdir) $(PTHREADS_CFLAGS) $(RSRT_CFLAGS) $(JAVA_INCLUDES)
libJavaPcre_la_LDFLAGS = -module -avoid-version $(JAVA_LIBS)
libJavaPcre_la_LIBADD = -lpcre2-8