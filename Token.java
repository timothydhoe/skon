package skon;

/*
 * Token.java - Token Data Structure
 * 
 * Represents a single lexical unit (token) from the source code.
 * Contains the token type, original text (lexeme), parsed value (literal), 
 * and line number for error reporting.
 * 
 * Pipeline position: Lexical Analysis (output from Scanner, input to Parser)
 */

// Representing a single token
class Token {
    final TokenType type;   // type of token
    final String lexeme;    // actual text from source
    final Object literal;   // runtime value
    final int line;         // line number

    Token(TokenType type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    // Debug output
    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}

