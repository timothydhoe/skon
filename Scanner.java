package skon

/*
 * Scanner.java - Lexical Analyzer
 * 
 * Scans through raw source code character-by-character and converts it into 
 * a list of tokens. Handles single/multi-character operators, literals, 
 * keywords, and whitespace.
 * 
 * Pipeline position: Lexical Analysis (first stage)
 * Input: Raw source code (String)
 * Output: List of Tokens
 */

/*
 * Notes:
 * - Regex: regular expressions (for pattern matching)
 * - Lexeme: the actual text snippet from source code (e.g., "123", "while")
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static skon.TokenType.*;

class Scanner {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;      // Points to first char of lexeme
    private int current = 0;    // Points to current char
    private int line = 1;       // Track current line (error handling)

    Scanner(String source) {
        this.source = source;
    }

    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // We're at the beginning of the next lexeme.
            start = current;
            scanToken();
        }

        // append EOF token to mark end of input.
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    // TODO: ScanToken() -> heart of the scanner!
        // simple switch where FOR NOW every lexeme is only a single char.

    private boolean isAtEnd() {
        return current>= source.length()
    }

    // TODO: scantoken() helper functions
        // - consume next char and return it
        // - output: grab text and create token
}