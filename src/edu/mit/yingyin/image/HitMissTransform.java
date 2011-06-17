package edu.mit.yingyin.image;

import java.awt.Point;

public class HitMissTransform {
  /**
   * Returns true if the 8 neighbours of p match the kernel 0 is background 1 is
   * foreground 2 is don't care.
   * 
   * @param p
   *          the point at the centre of the 9 pixel neighbourhood
   * @param pixels
   *          the 2D array of the image
   * @param w
   *          the width of the image
   * @param h
   *          the height of the image
   * @param kernel
   *          the array of the kernel values
   * @return True if the kernel and image match.
   */
  public static boolean kernelMatch(Point p, int[][] pixels, int w, int h,
      int[] kernel) {
    int matched = 0;
    for (int j = -1; j < 2; ++j) {
      for (int i = -1; i < 2; ++i) {
        if (kernel[((j + 1) * 3) + (i + 1)] == 2) {
          ++matched;
        } else if ((p.x + i >= 0) && (p.x + i < w) && (p.y + j >= 0) && 
                   (p.y + j < h) && 
                   (((pixels[p.x + i][p.y + j] == BinaryFast.foreground) && 
                     (kernel[((j + 1) * 3)+ (i + 1)] == 1)) || 
                    ((pixels[p.x + i][p.y + j] == BinaryFast.background) && 
                     (kernel[((j + 1) * 3) + (i + 1)] == 0)))) {
          ++matched;
        }
      }
    }
    return matched == 9;
  }
}
