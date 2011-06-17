package edu.mit.yingyin.image;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;

public class ThinningTransform {
  /**
   * Takes an image and a kernel and thins it once.
   * 
   * @param b the BinaryFast input image
   * @param kernel the thinning kernel
   * @return the thinned BinaryFast image
   */
  public static BinaryFast thinBinaryRep(BinaryFast b, int[] kernel) {
    Point p;
    HashSet<Point> result = new HashSet<Point>();
    HashSet<Point> inputHashSet = new HashSet<Point>();
    if (HitMissTransform.kernelNo0s(kernel)) {
      for (int j = 0; j < b.h; ++j) {
        for (int i = 0; i < b.w; ++i) {
          if (b.pixels[i][j] == BinaryFast.foreground) {
            inputHashSet.add(new Point(i, j));
          }
        }
      }
    } else {
      Iterator<Point> it = b.foregroundEdgePixels.iterator();
      while (it.hasNext()) {
        inputHashSet.add(it.next());
      }
    }
    result = HitMissTransform.HitMissHashSet(b, inputHashSet, kernel);
    Iterator<Point> it = result.iterator();
    while (it.hasNext()) {
      p = new Point((Point) it.next());
      // make p a background pixel and update the edge sets
      b.removePixel(p);
      b.foregroundEdgePixels.remove(p);
      b.backgroundEdgePixels.add(p);
      // check if new foreground pixels are exposed as edges
      for (int j = -1; j < 2; ++j) {
        for (int k = -1; k < 2; ++k) {
          if (p.x + j >= 0 && p.y + k > 0 && p.x + j < b.w && p.y + k < b.h
              && b.pixels[p.x + j][p.y + k] == BinaryFast.foreground) {
            Point p2 = new Point(p.x + j, p.y + k);
            b.foregroundEdgePixels.add(p2);
          }
        }
      }
    }
    return b;
  }

  /**
   * Takes an image and a kernel and thins it the specified number of times.
   * 
   * @param b
   *          the BinaryFast input image
   * @param kernel
   *          the thinning kernel
   * @param iterations
   *          required
   * @return the thinned BinaryFast image
   */
  public static BinaryFast thinImage(BinaryFast binary, int[] kernel,
      int iterations) {
    for (int i = 0; i < iterations; ++i) {
      binary = thinBinaryRep(binary, kernel);
    }
    binary.generateBackgroundEdgeFromForegroundEdge();
    return binary;
  }
}
