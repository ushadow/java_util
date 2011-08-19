package edu.mit.yingyin.image;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferUShort;
import java.util.Arrays;

import org.junit.Test;

public class ImageConvertUtilsTest {

  @Test
  public void testDepthToGrayBufferedImageSameDepth() {
    int[] depth = new int[100];
    int width = 10;
    int height = 10;
    Arrays.fill(depth, 15);
    BufferedImage image = ImageConvertUtils.depthToGrayBufferedImage(depth, 
        width, height);
    assertEquals(width, image.getWidth());
    assertEquals(height, image.getHeight());
    short[] imageArray = ((DataBufferUShort)image.getRaster().getDataBuffer()).
        getData();
    for (int i = 0; i < width * height; i++)
      assertEquals(0, imageArray[i]);
  }
  
  @Test
  public void testDepthToGrayBufferedImageIncreasingDepth() {
    int[] depth = new int[100];
    int width = 10;
    int height = 10;
    for (int i = 0; i < width * height; i++)
      depth[i] = i;
    
    BufferedImage image = ImageConvertUtils.depthToGrayBufferedImage(depth, 
        width, height);
    short[] imageArray = ((DataBufferUShort)image.getRaster().getDataBuffer()).
        getData();
    for (int i = 2; i < width * height; i++) {
      assertTrue((imageArray[i] & 0xffff) <= (imageArray[i - 1] & 0xffff));
    }
  }
}
