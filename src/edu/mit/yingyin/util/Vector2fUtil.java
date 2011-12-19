package edu.mit.yingyin.util;

import javax.vecmath.Tuple2f;
import javax.vecmath.Vector2f;

/**
 * Utility class for 2D vector math.
 * @author yingyin
 *
 */
public class Vector2fUtil {
  public static float dot(Vector2f a, Vector2f b) {
    return a.x * b.x + a.y * b.y;
  }
  
  public static float lengthSquared(Tuple2f a) {
    float dx = a.x;
    float dy = a.y;
    return (dx * dx + dy * dy);
  }
  
  /**
   * Calculates angle in radian between two vectors.
   * @param a vector with nonzero length
   * @param b vector with nonzero length
   * @return
   */
  public static float angle(Vector2f a, Vector2f b) {
    float denom = (float)Math.sqrt(Vector2fUtil.lengthSquared(a) * 
                                   Vector2fUtil.lengthSquared(b));
    float result = Vector2fUtil.dot(a, b) / denom;
    return (float)Math.acos(result);
  }
}
