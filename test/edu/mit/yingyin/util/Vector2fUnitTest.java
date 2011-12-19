package edu.mit.yingyin.util;

import static org.junit.Assert.*;

import javax.vecmath.Vector2f;

import org.junit.Test;


public class Vector2fUnitTest {
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

}
