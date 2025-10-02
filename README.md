# skon
*A basic interpreter named after the delicious English treat: the scone.*


## What is skon?

Skon is an interpreted programming language built from scratch in Java. Currently in early development as a learning project.


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


### Exit Values

Following [UNIX "sysexit.h" conventions](https://manpages.ubuntu.com/manpages/noble/man3/sysexits.h.3head.html):

- 64: Incorrect usage (wrong command-line arguments)
= 65: Data format error (syntax/runtime errors in script)


#### Acknowledgments

*This interpreter is made by the guidance of the book: "[Crafting Interpreters](https://craftinginterpreters.com)" by Robert Nystrom*