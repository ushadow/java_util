package edu.mit.yingyin.util;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class MatrixTest{
  @Test
  public void testRotate1By1Matrix() {
    int[] m1 = {1};
    int[] expected = {1};
    Matrix.rot90(m1, 1);
    assertArrayEquals(expected, m1);
  }
  
  @Test
  public void testRotate2By2Matrix() {
    int[] m1 = {1, 2, 3, 4};
    int[] expected = {2, 4, 1, 3};
    Matrix.rot90(m1, 2);
    assertArrayEquals(expected, m1);
  }
  
  @Test
  public void testRotate3By3Matrix() {
    int[] m1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    int[] expected = {3, 6, 9, 2, 5, 8, 1, 4, 7};
    Matrix.rot90(m1, 3);
    assertArrayEquals(expected, m1);
  }
  
  @Test
  public void testRotate4By4Matrix() {
    int[] m1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
    int[] expected = {4, 8, 12, 16, 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13};
    Matrix.rot90(m1, 4);
    assertArrayEquals(expected, m1);
  }
  
  @Test
  public void testRotate5By5Matrix() {
    int[] m1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22, 23, 24, 25};
    int[] expected = {5, 10, 15, 20, 25, 4, 9, 14, 19, 24, 3, 8, 13, 18, 23, 2,
                      7, 12, 17, 22, 1, 6, 11, 16, 21};
    Matrix.rot90(m1, 5);
    assertArrayEquals(expected, m1);
  }
}
