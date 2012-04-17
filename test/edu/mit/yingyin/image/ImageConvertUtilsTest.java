package edu.mit.yingyin.image;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferUShort;
import java.nio.FloatBuffer;
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
    int width = 10;
    int height = 10;
    int[] depth = new int[width * height];
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
  
  @Test
  public void testHistogramToBufferedImageUShort() {
    int width = 5;
    int height = 5;
    int length = width * height;
    short[] array = new short[length];
    float[] histogram = new float[width];
    
    for (int i = 0; i < array.length; i++) {
      array[i] = (short)((length - 1) / 5);
    }
    
    float total = (histogram.length - 1) * histogram.length / 2;
    histogram[0] = 0;
    for (int i = 1; i < histogram.length; i++)
      histogram[i] = histogram[i - 1] + i / total;
    
    BufferedImage bi = new BufferedImage(width, height, 
                                         BufferedImage.TYPE_USHORT_GRAY);
    ImageConvertUtils.histogramToBufferedImageUShort(array, histogram, bi);
    short[] imageArray = ((DataBufferUShort)bi.getRaster().getDataBuffer()).
        getData();
    for (int i = 0; i < imageArray.length; i++)
      assertEquals((short)(65535 * histogram[array[i]]), imageArray[i]);
  }
  
  @Test
  public void testFloatBuffer2UShortGrayBufferedImage() {
    int width = 5;
    int height = 5;
    int capacity = width * height;
    BufferedImage image = new BufferedImage(width, height, 
                                            BufferedImage.TYPE_USHORT_GRAY);
    FloatBuffer fb = FloatBuffer.allocate(capacity);
    fb.rewind();
    for (int i = 0; i < capacity; i++)
      fb.put((float)(i * 0.1));
    ImageConvertUtils.floatBuffer2UShortGrayBufferedImage(fb, image, width);
    short[] array = ((DataBufferUShort)image.getRaster().getDataBuffer())
        .getData();
    for (int i = 0; i < capacity; i++) {
      float value = (float)(i * 0.1);
      // Allows for rounding error.
      assertEquals(value * 65535/ 2.4, array[i] & 0xffff, 0.5);
    }
  }
}
