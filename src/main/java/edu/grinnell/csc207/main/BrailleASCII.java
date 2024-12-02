package edu.grinnell.csc207.main;

import java.io.PrintWriter;

import edu.grinnell.csc207.util.BrailleAsciiTables;

/**
 * Entry point of the program that takes two command-line parameters, the first of which represents
 * the target character set and the second of which represents the source characters, and that
 * translates the text.
 *
 * @author Khanh Do
 */
public class BrailleASCII {
  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run the program.
   *
   * @param args command-line arguments
   */
  @SuppressWarnings("ConvertToTryWithResources")
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    PrintWriter err = new PrintWriter(System.err, true);

    if (args.length != 2) {
      throw new IllegalArgumentException(
          "Invalid number of arguments: got " + args.length + ", needs 2");
    } // if

    try {
      switch (args[0].toLowerCase()) {
        case "ascii":
          pen.println(convertToASCII(args[1], err));
          break;
        case "braille":
          pen.println(convertToBraille(args[1], err));
          break;
        case "unicode":
          pen.println(convertToUnicode(args[1], err));
          break;
        default:
          throw new IllegalArgumentException("Invalid target character set: " + args[0]);
      } // switch
    } catch (RuntimeException e) {
      err.println("Trouble translating because " + e.getMessage());
    } // try/catch

    pen.close();
  } // main(String[]

  /**
   * Convert the given string to braille.
   *
   * @param toConvert the string to convert
   * @param pen the PrintWriter to which the output should be sent
   *
   * @return the braille representation of the given string
   *
   * @throws RuntimeException if the string of bits is invalid in any way
   */
  static String convertToBraille(String toConvert, PrintWriter pen) throws RuntimeException {
    String result = "";
    for (char c : toConvert.toCharArray()) {
      result += BrailleAsciiTables.toBraille(c);
    } // for

    return result;
  } // convertToBraille(String)

  /**
   * Convert the given string to ASCII.
   *
   * @param toConvert the string to convert
   * @param pen the PrintWriter to which the output should be sent
   *
   * @return the ASCII representation of the given string
   *
   * @throws RuntimeException if the string of bits is invalid in any way
   */
  static String convertToASCII(String toConvert, PrintWriter pen) throws RuntimeException {
    String result = "";
    for (int i = 0; i < toConvert.length(); i += 6) {
      result += BrailleAsciiTables.toAscii(toConvert.substring(i, i + 6));
    } // for

    return result;
  } // convertToASCII(String)

  /**
   * Convert the given string to Unicode.
   *
   * @param toConvert the string to convert
   * @param pen the PrintWriter to which the output should be sent
   *
   * @return the Unicode representation of the given string
   *
   * @throws RuntimeException if the string of bits is invalid in any way
   */
  static String convertToUnicode(String toConvert, PrintWriter pen) throws RuntimeException {
    String result = "";
    for (String bits : toConvert.split(" ")) {
      String braille = convertToBraille(bits, pen);

      for (int i = 0; i < braille.length(); i += 6) {
        result += BrailleAsciiTables.toUnicode(braille.substring(i, i + 6));
      } // for
      result += " ";
    } // for

    return result;
  } // convertToUnicode(String)
} // class BrailleASCII
