package edu.mit.yingyin.util;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

public class GeometryTest {

  @Test
  public void testGetAngleCOfCoincidentPoints() {
    Point A = new Point(0, 0);
    Point B = new Point(1, 0);
    Point C = new Point(0, 0);
    assertEquals(0, Geometry.getAngleC(A, B, C), 0);
    
    A = new Point(1, 0);
    B = new Point(0, 0);
    assertEquals(0, Geometry.getAngleC(A, B, C), 0);
  }  
   
  @Test
  public void testGetAngleCofCollinearPoints() {
    Point A = new Point(0, 0);
    Point B = new Point(1, 0);
    Point C = new Point(2, 0);
    assertEquals(0, Geometry.getAngleC(A, B, C), 0);
    
    A = new Point(3, 0);
    assertEquals(Math.PI, Geometry.getAngleC(A, B, C), 0.001);
    
    B = new Point(3, 2);
    C = new Point(3, -1);
    assertEquals(0, Geometry.getAngleC(A, B, C), 0);
    
    C = new Point(3, 1);
    assertEquals(Math.PI, Geometry.getAngleC(A, B, C), 0.001);
  }
  
  public void testGetAngleCofRightAngle() {
    Point C = new Point(0, 0);
    Point A = new Point(0, 3);
    Point B = new Point(4, 0);
    assertEquals(Math.PI / 2, Geometry.getAngleC(A, B, C), 0.001);
  }
  
  public void testGetAngleCofObtuseAngle() {
    Point C = new Point(0, 0);
    Point A = new Point(0, 1);
    Point B = new Point(-1, 1);
    assertEquals(Math.PI * 3 / 4, Geometry.getAngleC(A, B, C), 0.001);
  }
  
}
