# mp-bit-trees-maven

A mini-project exploring bit trees (a form of binary tree) and their use in translating between alphabets, particularly in translating to and from braille.

**Authors**

- Khanh Do
- Samuel A. Rebelsky (starter code)

**Instructions for use**

In the terminal run

```bash
alias ba="java -cp target/classes edu.grinnell.csc207.main.BrailleASCII"
```

There are 3 modes:

- braille
- ascii
- unicode

To use this program run

```bash
ba [mode] [to_decode_string]
```

Example usage:

```bash
$ ba braille hello
110010100010111000111000101010
$ ba ascii 110010100010111000111000101010
HELLO
$ ba unicode hello
⠓⠑⠇⠇⠕
$ ba unicode "hello world"
⠓⠑⠇⠇⠕⠀⠺⠕⠗⠇⠙

$ ba braille abc123
100000110000100100
Trouble translating because No corresponding value
$ ba ascii 11001010001011100011100010101
Invalid length of bit string
```

---

This code may be found at <https://github.com/khanhdo05/mp-bit-trees-maven>. The original code may be found at <https://github.com/Grinnell-CSC207/mp-bit-trees-maven>.

This assignment is adapted from the Including A11y in CS project entitled “Braille Binary Tree“.

## Knowledge

Braille is a tactile writing system used by people with visual impairments. Traditionally, braille was written by embossing paper. Today, people typically use a braille writer, such as a portable braille notetaker or computer that prints with a braille embosser. As for reading computer screens, braille users have the option of using refreshable braille displays. What makes it refreshable is whenever the user moves their cursor through keyboard or voice commands, the display updates the dots in the braille cells.
