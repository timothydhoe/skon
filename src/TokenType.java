package skon;

/*
 * TokenType.java - Token Type Definitions
 * 
 * Enumerates all possible token types in the Skon language.
 * Used by the Scanner to classify each piece of source code.
 * 
 * Pipeline position: Lexical Analysis (used by Scanner to categorize tokens)
 */

enum TokenType {
    // single-character tokens (operators and punctuation)
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // one or two character tokens
    BANG, BANG_EQUAL,           // ! !=
    EQUAL, EQUAL_EQUAL,         // = ==
    GREATER, GREATER_EQUAL,     // > >=
    LESS, LESS_EQUAL,           // < <=

    // literals
    IDENTIFIER, STRING, NUMBER,

    // Reserved keywords
    AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NULL, OR,
    PRINT, RETURN, SUPER, THIS, SKON, TRUE, VAR, WHILE,

    EOF
}