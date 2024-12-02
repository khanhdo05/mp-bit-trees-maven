package edu.grinnell.csc207.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

/**
 * Trees intended to be used in storing mappings between fixed-length sequences of bits and
 * corresponding values.
 *
 * @author Khanh Do
 */
public class BitTree {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The length of the bit strings that will be stored in the tree.
   */
  int bitLength;

  /**
   * The root of the tree.
   */
  BitTreeNode root;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Builds a tree that will store mappings from strings of n bits to strings.
   *
   * @param n the length of the bit strings that will be stored in the tree
   */
  public BitTree(int n) {
    this.bitLength = n;
    this.root = null;
  } // BitTree(int)

  // +---------------+-----------------------------------------------
  // | Local helpers |
  // +---------------+

  /**
   * Checks if the length of the bit string is equal to the bit length of the tree.
   *
   * @param bits the bit string to be checked
   *
   * @throws IndexOutOfBoundsException if the length of the bit string is not equal to the bit
   *         length
   */
  void checkLengthBits(String bits) throws IndexOutOfBoundsException {
    if (this.bitLength != bits.length()) {
      throw new IndexOutOfBoundsException("Invalid length of bit string, should be "
          + this.bitLength + ", but got " + bits.length() + ".");
    } // if
  } // checkLengthBits(String)

  /**
   * Follows the path through the tree given by bits (adding nodes as appropriate) and adds or
   * replaces the value at the end with value.
   *
   * @param curr the current node
   * @param bits the bit string to be followed
   * @param value the value to be stored at the end of the path
   *
   * @return the current nodes
   */
  BitTreeNode set(BitTreeNode curr, String bits, String value) {
    // When the bit string is empty, we have reached the end of the path.
    if (bits.length() == 0) {
      return new BitTreeLeaf(value);
    } // if

    // If the current node is null, create a new node.
    if (curr == null) {
      curr = new BitTreeNode();
    } // if

    // Follow the path through the tree.
    if (bits.charAt(0) == '0') {
      // Go to the left if it is 0.
      curr.left = set(curr.left, bits.substring(1), value);
    } else if (bits.charAt(0) == '1') {
      // Go to the right if it is 1.
      curr.right = set(curr.right, bits.substring(1), value);
    } // if

    return curr;
  } // set(BitNodeTree, String, String)

  /**
   * Follows the path through the tree given by bits and returns the value at the end.
   *
   * @param curr the current node
   * @param bits the bit string to be followed
   *
   * @return the value at the end of the path
   *
   * @throws IndexOutOfBoundsException if the path does not lead to a leaf or the bit string
   *         contains other than 0 or 1
   */
  String get(BitTreeNode curr, String bits) throws IndexOutOfBoundsException {
    // When the bit string is empty, we have reached the end of the path.
    if (bits.length() == 0) {
      if (!(curr instanceof BitTreeLeaf)) {
        throw new IndexOutOfBoundsException("No corresponding value");
      } // if
      return ((BitTreeLeaf) curr).getValue();
    } // if

    if (curr == null) {
      throw new IndexOutOfBoundsException("No corresponding value");
    } // if

    // Follow the path through the tree.
    if (bits.charAt(0) == '0') {
      // Go to the left if it is 0.
      return get(curr.left, bits.substring(1));
    } else if (bits.charAt(0) == '1') {
      // Go to the right if it is 1.
      return get(curr.right, bits.substring(1));
    } // if

    throw new IndexOutOfBoundsException("The bit string contains values other than 0 or 1.");
  } // get(BitNodeTree, String)

  /**
   * Prints out the contents of the tree in CSV format.
   *
   * @param curr the current node
   * @param bits the bit string
   * @param pen the PrintWriter to which the output should be sent
   */
  void dump(BitTreeNode curr, String bits, PrintWriter pen) {
    if (curr == null) {
      return;
    } // if

    if (curr instanceof BitTreeLeaf bitTreeLeaf) {
      pen.println(bits + "," + bitTreeLeaf.getValue());
    } // if

    dump(curr.left, bits + "0", pen);
    dump(curr.right, bits + "1", pen);
  } // dump(BitNodeTree, String, PrintWriter)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Follows the path through the tree given by bits (adding nodes as appropriate) and adds or
   * replaces the value at the end with value.
   *
   * @param bits a string of 0s and 1s
   * @param value a string to be stored at the end of the path
   *
   * @throws IndexOutOfBoundsException if bits is the inappropriate length or contains values other
   *         than 0 or 1
   */
  public void set(String bits, String value) throws IndexOutOfBoundsException {
    checkLengthBits(bits);

    this.root = set(this.root, bits, value);
  } // set(String, String)

  /**
   * Follows the path through the tree given by bits and returns the value at the end.
   *
   * @param bits a string of 0s and 1s
   *
   * @return the value at the end of the path
   *
   * @throws IndexOutOfBoundsException if the path does not lead to a leaf or the bit string
   *         contains other than 0 or 1
   */
  public String get(String bits) throws IndexOutOfBoundsException {
    checkLengthBits(bits);

    return get(this.root, bits);
  } // get(String, String)

  /**
   * prints out the contents of the tree in CSV format. For example, one row of our braille tree
   * will be “101100,M” (without the quotation marks).
   *
   * @param pen the PrintWriter to which the output should be sent
   */
  public void dump(PrintWriter pen) {
    dump(this.root, "", pen);
  } // dump(PrintWriter)

  /**
   * Reads a series of lines of the form bits,value and stores them in the tree.
   *
   * @param source the input stream from which to read the data
   */
  public void load(InputStream source) throws IOException {
    BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(source));

    for (String line = reader.readLine(); line != null; line = reader.readLine()) {
      String[] parts = line.split(",");
      if (parts.length != 2) {
        continue;
      } // if
      this.set(parts[0], parts[1]);
    } // for
  } // load(InputStream)

} // class BitTree
