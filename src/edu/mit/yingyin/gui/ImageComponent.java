package edu.mit.yingyin.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Component for displaying BufferedImage.
 * @author yingyin
 *
 */
public class ImageComponent extends JComponent {
	private static final long serialVersionUID = 1L;
	
	protected BufferedImage myimg = null;
		
	public ImageComponent(Dimension d) {
	  setLayout(null);
	  setOpaque(false);
	  setPreferredSize(d);
	}
	    
  public void setImage(BufferedImage img) {
    this.myimg = img;
    repaint();
  }
  
  @Override
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
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (myimg != null) {
  	  ((Graphics2D)g).drawImage(myimg, null, 0, 0);
  	  g.dispose();
    }
  }
}
