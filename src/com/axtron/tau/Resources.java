package com.axtron.tau;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import static com.axtron.tau.util.ImageUtil.getStreamedImage;
import com.axtron.tau.util.Relation;

public class Resources 
{
	private static final String APPICON_16 = 	"/com/axtron/resources/test-icon-16.png";
	private static final String APPICON_32 = 	"/com/axtron/resources/test-icon-32.png";
	private static final String APPICON_48 = 	"/com/axtron/resources/test-icon-48.png";
	private static final String APPICON_64 = 	"/com/axtron/resources/test-icon-64.png";
	
	private static final String TAB_CLOSE_IDLE = 	"/com/axtron/resources/close-idle12x12.png";
	private static final String TAB_CLOSE_ACTIVE = 	"/com/axtron/resources/close-active12x12.png";
	private static final String TAB_CLOSE_DOWN = 	"/com/axtron/resources/close-active-down12x12.png";
	private static final String PRE_GLASS = 		"/com/axtron/resources/preloader_glass_lines.gif";
	
	// exisitng images for relations removed due to NDA
	private static final String RELATION_UNKNOWN = 	"/com/axtron/resources/processor-icon-100.png";
	
	private static final Images images = new Images();
	public static Images getImages() {return images;}
	public static class Images
	{
		private final Map<String, BufferedImage> flyweight = new HashMap<String, BufferedImage>();
		
		private BufferedImage getImage(String path) 
		{
			if (flyweight.containsKey(path))
			{
				return flyweight.get(path);
			}
			BufferedImage ico = getStreamedImage(path);
			flyweight.put(path, ico);
			return ico;
		}
		
		public BufferedImage getCloseIdle() {return getImage(TAB_CLOSE_IDLE);}
		public BufferedImage getCloseActive() {return getImage(TAB_CLOSE_ACTIVE);}
		public BufferedImage getCloseActiveDown() {return getImage(TAB_CLOSE_DOWN);}
		
		public BufferedImage getRelation(Relation rel) 
		{
			switch(rel)
			{
				// exisitng relations removed due to NDA
			default:break;
			}
			
			return getImage(RELATION_UNKNOWN);
		}
		
		public Image getApplicationIcon16() {return getImage(APPICON_16);}
		public Image getApplicationIcon32() {return getImage(APPICON_32);}
		public Image getApplicationIcon48() {return getImage(APPICON_48);}
		public Image getApplicationIcon64() {return getImage(APPICON_64);}
		
	}
	
	private static final Icons icon = new Icons();
	public static Icons getIcons() {return icon;}
	public static class Icons
	{
		private final Map<String, Icon> flyweight = new HashMap<String, Icon>();
		private Icon getIcon(String path) 
		{
			if (flyweight.containsKey(path))
			{
				return flyweight.get(path);
			}
			Icon ico = new ImageIcon( Loader.getResource(path) );
			flyweight.put(path, ico);
			return ico;
		}
		
		public Icon getPreloadBar() 
		{
			return getIcon(PRE_GLASS);
		}
	}
}
