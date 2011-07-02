package edu.mit.yingyin.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;

import org.junit.Test;

public class FileUtilTest {
  @Test
  public void testSetExtension() {
    String fileName = "a.png";
    String newName = FileUtil.setExtension(fileName, "txt");
    assertEquals("a.txt", newName);
  }
  
  @Test
  public void testSetExtensionOriginalNameHasNoExt() {
    String fileName = "a";
    String newName = FileUtil.setExtension(fileName, "txt");
    assertEquals("a.txt", newName);
  }
  
  @Test
  public void testGetExtension() {
    File f = new File("a.txt");
    String ext = FileUtil.getExtension(f);
    assertEquals("txt", ext);
  }
  
  @Test 
  public void testGetExtentsionNoExt() {
    File f = new File("a");
    String ext = FileUtil.getExtension(f);
    assertNull(ext);
  }
}
