package com.axtron.tau.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.axtron.tau.Loader;

public class ImageUtil 
{
	public static boolean isImage(String entry) 
	{
		String i = entry.toLowerCase();
		if (i.endsWith(".jpg")) return true;
		if (i.endsWith(".jpeg")) return true;
		if (i.endsWith(".png")) return true;
		return false;
	}
	
	public static BufferedImage getStreamedImage(String path) 
	{
		InputStream is = Loader.getResourceAsStream(path);
        BufferedImage img = null;
		try 
		{
			img = ImageIO.read(is);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		if (img==null) return null;
		
		return img;
	}
	
	public static ImageIcon getStreamedIcon(String path) 
	{
		BufferedImage img = getStreamedImage(path);
		ImageIcon ico = new ImageIcon(img);
		return ico;
	}
	
	public static BufferedImage loadImage(String path) 
	{
		BufferedImage img = null;
		
		File f = new File(path);
		try {img = ImageIO.read(f);}
		catch (IOException e)
		{e.printStackTrace();}
		
		return img;
	}
	
	public static BufferedImage resizeImage(BufferedImage img, int w, int h) throws IOException 
	{
		float aspect 		= (float)img.getWidth() / (float)img.getHeight();
		float inv_aspect 	= 1f / aspect;
		int width 			= w;
		int height 			= h;
		
		// scale the height based on the width.
		int deltaW = img.getWidth() - w;
		boolean wider = deltaW > 0;
		if (wider) 
		{
			height = (int) ((float)w * inv_aspect);
		}
		
		// scale the width based on the height
		int deltaH = height - h;
		boolean higher = deltaH > 0;
		if (higher) 
		{
			width = (int) ((float)h * aspect);
			height = (int) ((float)width * inv_aspect);
		}
		
		int offX = (w-width) >> 1;
		int offY = (h-height) >> 1;
		
		BufferedImage resizedImage 	= new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D 		= resizedImage.createGraphics();
	    graphics2D.setBackground(Color.WHITE);
	    graphics2D.clearRect(0, 0, w, h);
	    graphics2D.drawImage(img, offX, offY, width, height, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	public static BufferedImage resizeImage(ImageIcon icon, int w, int h) throws IOException 
	{
		BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		icon.paintIcon(null, g, 0,0);
		g.dispose();
		return resizeImage(bi,w,h);
	}
	
	public static BufferedImage resizeImage(String path, int width, int height)
	{
		if (!isImage(path)) return null;
		
		BufferedImage im = loadImage(path);
		if (im==null)
		{
			return null;// error
		}
		
		BufferedImage rs = null;
		try 
		{
			rs = resizeImage(im, width, height);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		if (rs==null)
		{
			return null;// error
		}
		
		return rs;
	}
}
