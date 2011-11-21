package edu.mit.yingyin.gui;

import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Main frame for displaying images.
 * @author Ying
 * 
 */
public class ImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	protected ImageComponent ic = null;
	protected StatusBar sb = null;
	
	/**
	 * Creates a frame for displaying images.
	 * @param title title of the main frame.
	 * @param d dimention of the image.
	 */
	public ImageFrame(String title, Dimension d) {
		super(title);
		ic = new ImageComponent(d);
    initialize();
	}
	
	/**
	 * Creates a frame for displaying images.
	 * @param title title of the main frame.
	 * @param ic <code>ImageComponent</code> to be displayed.
	 */
	public ImageFrame(String title, ImageComponent ic) {
    super(title);
    this.ic = ic;
    initialize();
	}
	
	public ImageFrame(String title, BufferedImage bi) {
	  super(title);
	  ic = new ImageComponent(new Dimension(bi.getWidth(), bi.getHeight()));
	  ic.setImage(bi);
	  initialize();
	}
	
	public void show(BufferedImage bi) {
		if(ic != null)
			ic.setImage(bi);
	}
	
	public void setStatus(String status) {
		sb.setMessage(status);
	}
	
	public void showUI() {
		pack();
		setVisible(true);
	}
	
	public void addMouseListenerToImageComponent(MouseListener ml) {
	  if (ic != null)
	    ic.addMouseListener(ml);
	}
	
	private void initialize() {
	  sb = new StatusBar();
    getContentPane().add(sb, java.awt.BorderLayout.SOUTH);
    getContentPane().add(ic);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    showUI();
	}
}
