package edu.mit.yingyin.util;

import static org.junit.Assert.*;

import javax.vecmath.Point2f;
import javax.vecmath.Vector2f;

import org.junit.Test;


public class Vector2fUtilTest {
  double epsilon = 1e-3;
  @Test
  public void testAngle() {
    Vector2f v1 = new Vector2f(0, 1);
    Vector2f v2 = new Vector2f(0, 2);
    assertEquals(0, Vector2fUtil.angle(v1, v2), epsilon);
    
    v1 = new Vector2f(0, 1);
    v2 = new Vector2f(1, 0);
    assertEquals(Math.PI / 2, Vector2fUtil.angle(v1, v2), epsilon);
    
    v1 = new Vector2f(0, 1);
    v2 = new Vector2f(2, 2);
    assertEquals(Math.PI / 4, Vector2fUtil.angle(v1, v2), epsilon);
  
    v1 = new Vector2f(-1, 0);
    v2 = new Vector2f(1, 0);
    assertEquals(Math.PI, Vector2fUtil.angle(v1, v2), epsilon);
  
    v1 = new Vector2f(-1, 1);
    v2 = new Vector2f(1, 0);
    assertEquals(Math.PI * 3 / 4, Vector2fUtil.angle(v1, v2), epsilon);
  }
  
  @Test
  public void testIntersection() {
    Point2f intersection = Vector2fUtil.intersection(new Point2f(0, 0), 
        new Point2f(1, 0), new Point2f(0, 0), new Point2f(1, 1));
    assertEquals(new Point2f(0, 0), intersection);
    
    intersection = Vector2fUtil.intersection(new Point2f(0, 0), 
        new Point2f(1, 0), new Point2f(0, 0), new Point2f(2, 0));
    assertEquals(null, intersection);
    
    intersection = Vector2fUtil.intersection(new Point2f(1, 2), 
        new Point2f(3, 2), new Point2f(2, 0), new Point2f(2, 5));
    assertEquals(new Point2f(2, 2), intersection);
    
    intersection = Vector2fUtil.intersection(new Point2f(0, 0), 
        new Point2f(1, 1), new Point2f(0, 5), new Point2f(2, 5));
    assertEquals(new Point2f(5, 5), intersection);
  }

}
