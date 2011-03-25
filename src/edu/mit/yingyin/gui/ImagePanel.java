package edu.mit.yingyin.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	protected ImageView parent = null;
	
	protected BufferedImage myimg = null;
		
	public ImagePanel(ImageView iv, Dimension d) {
	  setLayout(null);
	  setOpaque(false);
	  setPreferredSize(d);
	  parent = iv;
	}
	    
	public void setParent(ImageView imf) { parent = imf; }
	    
  public void setImage(BufferedImage img) {
    this.myimg = img;
    repaint();
  }
  
  public void update(Graphics g) { paint(g); }
  
  public void update() {
  	// Validates this container and all of its subcomponents.
  	// The validate method is used to cause a container to lay out its 
    // subcomponents again. 
  	// It should be invoked when this container's subcomponents are modified 
    // (added to or removed from the container, or layout-related information 
    // changed) after the container has been displayed. 
  	validate();
  	repaint();
  }
  
  public void paint(Graphics g) {
    if (myimg != null) {
  	  ((Graphics2D)g).drawImage(myimg, null, 0, 0);
    }
  }
}
