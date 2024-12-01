package edu.grinnell.csc207.util;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.NoSuchElementException;

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
      throw new IndexOutOfBoundsException(
          "Error: The length of the bit string is not equal to the bit length of the tree. Should be length "
              + this.bitLength + ", but got " + bits.length() + ".");
    } // if
  } // checkLengthBits(String)

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
   * @throws NoSuchElementException if the path does not lead to a leaf or the bit string contains
   *         other than 0 or 1
   */
  String get(BitTreeNode curr, String bits) throws NoSuchElementException {
    // When the bit string is empty, we have reached the end of the path.
    if (bits.length() == 0) {
      if (!(curr instanceof BitTreeLeaf)) {
        throw new NoSuchElementException("Error: The path does not lead to a leaf.");
      } // if
      return ((BitTreeLeaf) curr).getValue();
    } // if

    // Follow the path through the tree.
    if (bits.charAt(0) == '0') {
      // Go to the left if it is 0.
      return get(curr.left, bits.substring(1));
    } else if (bits.charAt(0) == '1') {
      // Go to the right if it is 1.
      return get(curr.right, bits.substring(1));
    } // if

    throw new NoSuchElementException("Error: The bit string contains values other than 0 or 1.");
  } // get(BitNodeTree, String)

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
   * @throws NoSuchElementException if the path does not lead to a leaf or the bit string contains
   *         other than 0 or 1
   */
  public String get(String bits) throws NoSuchElementException {
    checkLengthBits(bits);

    return get(this.root, bits);
  } // get(String, String)

  /**
   *
   */
  public void dump(PrintWriter pen) {
    // STUB
  } // dump(PrintWriter)

  /**
   *
   */
  public void load(InputStream source) {
    // STUB
  } // load(InputStream)

} // class BitTree
