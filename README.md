# skon
*A basic interpreter named after the delicious English treat: the scone.*

>I try to keep this README up-to-date, but I know myself well enough to ensure you that I will miss things, skip over information or just outright forget to update this document. I don't ask for forgiveness, just understand that this document is, at the moment of writing, a livig document and might not hold all information.

## What is skon?

Skon is an interpreted programming language built from scratch in Java. **Currently in early development as a learning project.**


## Current Status?

- Lexical analysis (tokenisation)
- Parser (coming soon)
- Interpreter (coming soon)


## Building and Running
```bash
# Compile
javac skon/Skon.java

# Run a script file
java skon.Skon script.skon

# Start the REPL
java skon.Skon
```
## Usage
```bash
# REPL mode - interactive prompt
>>> print "Hello, world!"

# File mode
skon examples/hello.skon
```

## A bit of background information

### The Scanner Class

The core of the scanner is a *loop*. It looks at the first character of the source code, figures out what *lexeme* it belongs to, and consumes it and any following characters that are part of the lexeme. At the end of the lexeme, the scanner emits a *token*.

> lexeme = the raw text
> 
> literal = parsed value
> 
> lexical grammar = the rules that determine how a particular language groups chararcters into lexemes

#### REGEX

We're using *regular expressions*, defining a regex for each kind of lexeme and using those to match characters.

We use the same rules as C for identifiers. The regex matches: 
```[a-zA-Z_][a-zA-Z_0-9]```

#### Strings

skon supports multi-line strings. For now, it seems too complex to forbid them. This is a functionality that I might add later.

At this time, skon also does not support escape sequences like ```\n```

#### Number literals

For now, all numbers are represented using Java's Double type.

skon does not allow leading or trailing decimal points
```
.1234
1234.
```

Negative numbers like ```-123``` is not a number literal, but an expression instead.

*note: the digit lexemes are defined in the default case*

#### Reserved words and identifiers

#TODO:
explain **Maximal munch**, **reserved word**

### Parser: Tree-walk Interpreter

The scanner takes a series of raw source code and transforms them into a series of tokens.
Here, in the parser, we'll transform them tino a more complex representation.

Basis of the syntactic grammar.
*TODO: add features, new syntax --> after parser works.*

```
expression      -> literal | unary | binary | grouping ;
literal         -> NUMBER | STRING | "true" | "false" | "nil" ;
grouping        -> "(" expression ")" ;
unary           -> ( "-" | "!") expression ;
binary          -> expression operator expression ;
operator        -> "==" | "!=" | "<" | "<=" | ">" | ">="
                   | "+" | "-" | "*" | "/" ;
```

## Exit Values

Following [UNIX "sysexit.h" conventions](https://manpages.ubuntu.com/manpages/noble/man3/sysexits.h.3head.html):


> 64: Incorrect usage (wrong command-line arguments)
>
> 65: Data format error (syntax/runtime errors in script)


#### Acknowledgments

*This interpreter is made by the guidance of the book: "[Crafting Interpreters](https://craftinginterpreters.com)" by Robert Nystrom*