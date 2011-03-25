package edu.mit.yingyin.gui;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 * Main frame for display webcam images. Allows saving of the image.
 * @author Ying
 * 
 */
public class ImageView extends JFrame {

	private static final long serialVersionUID = 1L;
	protected ImagePanel ip = null;
	protected StatusBar sb = null;
	
	/*menu*/
	private JMenu menuOptions;
	private JMenu menuFile;
	
	/**
	 * Create an instance of main frame for displaying image frame captured from 
	 * webcam
	 * @param title Title of the main frame
	 * @param _wdf	Webcam driver for Firei cambera, it has to be initialized 
	 *     before passed as a parameter.
	 */
	public ImageView(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
    sb = new StatusBar();
    getContentPane().add(sb, java.awt.BorderLayout.SOUTH);

    JMenuBar menuBar = new JMenuBar();
    menuOptions = new JMenu("Options");
    menuFile = new JMenu("File");
  
    menuBar.add(menuFile);
    menuBar.add(menuOptions);
    
    setJMenuBar(menuBar);
	}
	
	public void addImagePanel(ImagePanel ip) {
		this.ip = ip;
		getContentPane().add(ip);
	}
	
	public void setImage(BufferedImage bi) {
		if(ip!=null)
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
