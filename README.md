# skon
*A basic interpreter named after the delicious English treat: the scone.*


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
> print "Hello, world!"

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


### Exit Values

Following [UNIX "sysexit.h" conventions](https://manpages.ubuntu.com/manpages/noble/man3/sysexits.h.3head.html):


> 64: Incorrect usage (wrong command-line arguments)
>
> 65: Data format error (syntax/runtime errors in script)


#### Acknowledgments

*This interpreter is made by the guidance of the book: "[Crafting Interpreters](https://craftinginterpreters.com)" by Robert Nystrom*