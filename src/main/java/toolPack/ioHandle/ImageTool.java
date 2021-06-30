package toolPack.ioHandle;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class ImageTool {

	public Map<Integer, Integer> rgbCounter(BufferedImage bi) {
		return rgbCounter(bi, 0, bi.getWidth(), 0, bi.getHeight());
	}

	public Map<Integer, Integer> rgbCounter(BufferedImage bi, int startX, int endX, int startY, int endY) {
		Integer tmpRGB = null;
		Map<Integer, Integer> rgbCountingMap = new HashMap<>(); 
		for(int tmpX = startX; tmpX < endX; tmpX++) {
			for(int tmpY = startY; tmpY < endY; tmpY++) {
				tmpRGB = bi.getRGB(tmpX, tmpY);
				if(rgbCountingMap.containsKey(tmpRGB)) {
					rgbCountingMap.put(tmpRGB, rgbCountingMap.get(tmpRGB) + 1);
				} else {
					rgbCountingMap.put(tmpRGB, 1);
				}
			}
		}
		
		return rgbCountingMap;
	}
	
	public boolean hasRGB(BufferedImage bi, int rgb) {
		return hasRGB(bi, rgb, 0, bi.getWidth(), 0, bi.getHeight());
	}
	
	public boolean hasRGB(BufferedImage bi, int rgb, int startX, int endX, int startY, int endY) {
		Integer tmpRGB = null;
		for(int tmpX = startX; tmpX < endX; tmpX++) {
			for(int tmpY = startY; tmpY < endY; tmpY++) {
				tmpRGB = bi.getRGB(tmpX, tmpY);
				if(rgb == tmpRGB) {
					return true;
				}
			}
		}
		
		return false;
	}
}
