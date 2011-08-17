package edu.mit.yingyin.gui;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * Main frame for display webcam images. Allows saving of the image.
 * @author Ying
 * 
 */
public class ImageViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	protected ImagePanel ip = null;
	protected StatusBar sb = null;
	
	/**
	 * Create an instance of main frame for displaying image frame captured from 
	 * webcam
	 * @param title Title of the main frame
	 * @param _wdf	Webcam driver for Firei cambera, it has to be initialized 
	 *     before passed as a parameter.
	 */
	public ImageViewer(String title, Dimension d) {
		super(title);
    sb = new StatusBar();
    getContentPane().add(sb, java.awt.BorderLayout.SOUTH);
    ip = new ImagePanel(d);
    getContentPane().add(ip);

    showUI();
	}
	
	public void show(BufferedImage bi) {
		if(ip != null)
			ip.setImage(bi);
	}
	
	public void setStatus(String status) {
		sb.setMessage(status);
	}
	
	public void showUI() {
		pack();
		setVisible(true);
	}
}
