package edu.grinnell.csc207.util;

/**
 * A leaf in a binary bit tree that stores the value.
 *
 * @author Khanh Do
 */
public class BitTreeLeaf extends BitTreeNode {
  /**
   * The value stored in the leaf.
   */
  String value;

  /**
   * Construct a new leaf with the given value.
   *
   * @param value the value to be stored in the leaf
   */
  public BitTreeLeaf(String value) {
    this.value = value;
  } // BitTreeLeaf(String)

  /**
   * Get the value stored in the leaf.
   *
   * @return the value stored in the leaf
   */
  public String getValue() {
    return this.value;
  } // getValue()

  /**
   * Set the value stored in the leaf.
   *
   * @param value the value to be stored in the leaf
   */
  public void setValue(String value) {
    this.value = value;
  } // setValue(String)
} // class BitTreeLeaf
