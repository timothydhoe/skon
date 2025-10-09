package skon;

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

import skon.Skon;

import static skon.TokenType.*;

class Scanner {
    private final String source ;    // raw source code string
    private final List<Token> tokens = new ArrayList<>();       // Collected tokens
    private int start = 0;          // Points to first char of lexeme
    private int current = 0;        // Points to current char
    private int line = 1;           // Track current line (error handling)

    Scanner(String source) {
        this.source = source;
    }

    // Main scanning loop - processes entire source code
    List<Token> scanTokens() {
        while (!isAtEnd()) {
            // Reset starting pointer for next lexeme
            start = current;
            scanToken();    // Process one token
        }

        // append EOF token to mark end of input.
        tokens.add(new Token(EOF, "", null, line));
        return tokens;
    }

    private void scanToken() {
        char c = advance();     // Consume next character
        switch (c) {
            // single-character tokens
            case '(': addToken(LEFT_PAREN); break;
            case ')': addToken(RIGHT_PAREN); break;
            case '{': addToken(LEFT_BRACE); break;
            case '}': addToken(RIGHT_BRACE); break;
            case ',': addToken(COMMA); break;
            case '.': addToken(DOT); break;
            case '-': addToken(MINUS); break;
            case '+': addToken(PLUS); break;
            case ';': addToken(SEMICOLON); break;
            case '*': addToken(STAR); break;
            // Operators
            case '!':
                addToken(match('=') ? BANG_EQUAL : BANG); break;
            case '=':
                addToken(match('=') ? EQUAL_EQUAL : EQUAL); break;
            case '<':
                addToken(match('=') ? LESS_EQUAL : LESS); break;
            case '>':
                addToken(match('=') ? GREATER_EQUAL : GREATER); break;
            // SLASH + multiple use cases
            case '/':
            if (match('/')) {
                // A comment goes until the end of the line
                while (peek() != '\n' && !isAtEnd()) advance();
            }   else {
                addToken(SLASH);
            } break;
            // Ignore whitespace
            case ' ':
            case '\r':
            case '\t':
                break;
            // Go back to loop and increment line
            case '\n':
                line ++;
                break;

            // Strings
            case '""': string(); break;

            default:
                if (isDigit(c)) {
                    number();
                } else {
                // Report unrecognised characters 
                Skon.error(line, "Unexpected character.");
                }
                break;
        }
    }

    // Used for numbers
    private void number() {
        while (isDigit(peek())) advance();

        // look for fractional part
        if (peek() == '.' && isDigit(PeekNext())) {
            // consume the "."
            advance();

            while (isDigit(peek())) advance;
        }

        addToken(NUMBER, Double.parseDouble(source.substring(start, current)));
    }

    // Used for Strings
    private void string() {
        while (peek() != '"' && !isAtEnd()) {
            if (peek() == '\n') line++; // skon allows multi-line strings.
            advance();
        }

        if (isAtEnd) {
            Skon.error(line, "Unterminated string.");
            return;
        }

        advance(); // The closing ".

        // Trim surrouding quotes using .substring
        String value = source.substring(start + 1, current - 1);
        addToken(STRING, value);
    }

    // Used for two-character operators like "!=", "==", ">="
    private boolean match(char expected) {
        if (isAtEnd()) return false;
        if (source.charAt(current) != expected) return false;

        current ++;
        return true;
    }

    // lookahead: returns current char without consuming it
    // does NOT move the current pointer forward
    private char peek() {
        if (isAtEnd()) return '\0'; // Returns null terminator '\0' if at end of source
        return source.charAt(current);
    }

    // lookahead: Looks ahead at two characters.
    // used for fractional numbers "." making sure there's a digit after it.
    private char peekNext() {
        if (current + 1 >= source.length()) return '\0';
        return source.charAt(current + 1);
    }

    // Return for digit lexeme
    private boolean isDigit(char c) {
        return c >= '0' && c < '9';
    }

    // Check if all characters are consumed
    private boolean isAtEnd() {
        return current >= source.length();
    }

    // Consume and return current character, the move forward
    private char advance() {
        return source.charAt(current++); // Returns char at current, then increments
    }

    // Overload for tokens without a literal value (operators/keywords)
    private void addToken(TokenType type) {
        addToken(type, null);
    }

    // Create a token and add it to the token list.
    private void addToken(TokenType type, Object literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal, line));
    }
}