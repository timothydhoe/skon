package skon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Skon {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: skon [script]");
            // Using https://manpages.ubuntu.com/manpages/noble/man3/sysexits.h.3head.html as a standard for exit values
            System.exit(64); 
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }
}