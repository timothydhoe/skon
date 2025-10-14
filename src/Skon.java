package skon;

/*
 * Skon.java - Main Entry Point & Execution Controller
 * 
 * Handles command-line interface, file/REPL execution modes, and error reporting.
 * This is the top-level coordinator that manages the entire interpreter pipeline.
 * 
 * Pipeline position: Entry point → runs Scanner → (future: Parser → Interpreter)
 */

// Imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Skon {
    // Tracks if an error occurred during execution
    static boolean hadError = false;

    // Main entry point - determines execution mode
    public static void main(String[] args) throws IOException {
        // Validate command-line arguments
        if (args.length > 1) {
            System.out.println("Usage: skon [script]");
            System.exit(64); // Exit code: incorrect usage
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            System.out.println("=== Welcome to skon. ===");
            System.out.println("Ctrl+D to exit.");
            runPrompt();
        }
    }

    // Execute a script from a file
    private static void runFile(String path) throws IOException {
        // Read entire file into memory
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        // Exit with error code if script had errors
        if (hadError) System.exit(65);
    }

// Interactive REPL mode - Read-Eval-Print Loop
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) { // Infinite loop until EOF
            System.out.print(">>> ");
            String line = reader.readLine();
            if (line == null) break; // Ctrl-D/Ctrl-Z exits
            run(line);

            // Reset error flag - don't kill REPL session on error
            hadError = false;
        }
    }

    // Core execution pipeline
    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // TODO : Add parser (syntax analysis)
        // TODO : Add interpreter/evaluator (execution)

        // TEMP: Print tokens for debugging
        // to check if tokens are assigned the correct token 
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    // Report error at a specific line
    static void error(int line, String message) {
        report(line, "", message);
    }

    // Format and print error message to stderr
    private static void report(int line, String where, String message) {
        System.err.println(
            "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    //TODO: Add column tracking for precise error location
    // (e.g., "Error at line 5, column 12")
}

