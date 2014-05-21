package Utility;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class ImageLoader {
	
	public static ImageIcon loadImage(String path){
			return new ImageIcon(Toolkit.getDefaultToolkit().getImage(ImageLoader.class.getClassLoader().getResource(path)));
	}

}
