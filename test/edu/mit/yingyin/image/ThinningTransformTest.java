package edu.mit.yingyin.image;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import edu.mit.yingyin.util.Matrix;

public class ThinningTransformTest {
  private static int[] THINNING_KERNEL_ORTH = {0, 0, 0, 2, 1, 2, 1, 1, 1};
  
  @Test
  public void testThinBinaryOnce() {
    byte[][] pixels = {{0, 0, 0, 0, 0},
                       {0, 1, 1, 1, 0},
                       {0, 1, 1, 1, 0},
                       {0, 1, 1, 1, 0},
                       {0, 0, 0, 0, 0}};
    byte[][] expected1 = {{0, 0, 0, 0, 0},
                         {0, 1, 0, 1, 0},
                         {0, 1, 1, 1, 0},
                         {0, 1, 1, 1, 0},
                         {0, 0, 0, 0, 0}};
    BinaryFast bf = new BinaryFast(pixels, 5, 5);
    ThinningTransform.thinBinaryOnce(bf, THINNING_KERNEL_ORTH);
    assertArrayEquals(expected1, pixels);
    Matrix.rot90(THINNING_KERNEL_ORTH, 3);
    Matrix.rot90(THINNING_KERNEL_ORTH, 3);
    ThinningTransform.thinBinaryOnce(bf, THINNING_KERNEL_ORTH);
    byte[][] expected2 = {{0, 0, 0, 0, 0},
                          {0, 1, 0, 1, 0},
                          {0, 1, 1, 1, 0},
                          {0, 1, 0, 1, 0},
                          {0, 0, 0, 0, 0}};
    assertArrayEquals(expected2, pixels);
  }
}
