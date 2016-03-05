package SimplyTools.API;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ImageHelper {

	/**
	 * Get icon in BufferedImage from ItemStack
	 * 
	 * @param item
	 * @return icon in BufferedImage
	 * @throws IOException
	 */

	public static BufferedImage getTextureOfItem(ItemStack item) throws IOException {
		String resLoc;
		if(item.getItem() instanceof ItemBlock) {
			resLoc = "textures/blocks/" + item.getIconIndex().getIconName().substring(item.getIconIndex().getIconName().indexOf(":") + 1, item.getIconIndex().getIconName().length()) + ".png";
		} else {
			resLoc = "textures/items/" + item.getIconIndex().getIconName().substring(item.getIconIndex().getIconName().indexOf(":") + 1, item.getIconIndex().getIconName().length()) + ".png";
		}
		if(item.getIconIndex().getIconName().indexOf(":") > 0) {
			return getTextureOfResource(new ResourceLocation(item.getIconIndex().getIconName().substring(0, item.getIconIndex().getIconName().indexOf(":")).toLowerCase(), resLoc));
		} else {
			return getTextureOfResource(new ResourceLocation(resLoc));
		}
	}

	/**
	 * Get icon in BufferedImage from ResourceLocation
	 * 
	 * @param resource
	 * @return icon in BufferedImage
	 * @throws IOException
	 */
	public static BufferedImage getTextureOfResource(ResourceLocation resource) throws IOException {
		return ImageIO.read(Minecraft.getMinecraft().getResourceManager().getResource(resource).getInputStream());
	}

	private static ARGBXY[] getTexureMapFromItem(ItemStack item, int blur) throws IOException {
		System.out.println(blur + "blur");
		ARGBXY[] ret = new ARGBXY[blur * blur];
		BufferedImage texture = getTextureOfItem(item);
		int tWidth = texture.getWidth();
		int tHeight = texture.getHeight();
		TextureXY center = getCenterOfTexture(tWidth, tHeight, blur);
		int c = 0;
		for(int i = 0; i < center.maxX - center.minX; i++) {
			for(int j = 0; j < center.maxY - center.minY; j++) {
				ARGBXY toRet = new ARGBXY(0, 0, 0, 0, 0, 0);
				int pixel = texture.getRGB(i, j);
				toRet.a = (pixel >> 24) & 0xff;
				toRet.r = (pixel >> 16) & 0xff;
				toRet.b = (pixel) & 0xff;
				toRet.g = (pixel >> 8) & 0xff;
				toRet.x = center.minX + i;
				toRet.y = center.minY + j;
				System.out.println(c);
				ret[c] = toRet;
				c++;
			}
		}
		return ret;
	}

	private static TextureXY getCenterOfTexture(int width, int heigth, int blur) {
		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		do {
			if(blur % 2 == 0) {
				minX = width - width / 2 - Math.round(((blur + 0F) / 2F) + 0.1F);
				minY = heigth - heigth / 2 - Math.round(((blur + 0F) / 2F) + 0.1F);
				maxX = width - width / 2 + Math.round(((blur + 0F) / 2F) + 0.1F);
				System.out.println(Math.round(((blur + 0F) / 2F) + 0.1F));
				maxY = heigth - heigth / 2 + Math.round(((blur + 0F) / 2F) + 0.1F);
			} else {
				minX = width - width / 2 - Math.round(((blur + 0F) / 2F) + 0.1F);
				minY = heigth - heigth / 2 - Math.round(((blur + 0F) / 2F) + 0.1F);
				maxX = width - width / 2 + Math.round(((blur + 0F) / 2F) + 0.1F) - 1;
				System.out.println(Math.round(((blur + 0F) / 2F) + 0.1F));
				maxY = heigth - heigth / 2 + Math.round(((blur + 0F) / 2F) + 0.1F) - 1;
			}
		} while((maxX - minX) * (maxY - minY) != blur * blur);
		TextureXY ret = new TextureXY(minX, minY, maxX, maxY);
		return ret;
	}

	/**
	 * Simply print alpha, red, green and blue of pixel
	 * 
	 * @param pixel
	 */

	public static void printARGBOfPixel(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		System.out.println("ARGB: " + alpha + ", " + red + ", " + green + ", " + blue);
	}

	/**
	 * From pixel int to ARGB color
	 * 
	 * @param pixel
	 * @return ARGB of pixel
	 */

	public static ARGB getPixelARGB(int pixel) {
		int alpha = (pixel >> 24) & 0xff;
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		return new ARGB(alpha, red, green, blue);
	}

	private static PatternColorMap getPatternColorMapFromItemTexture(ItemStack item) throws IOException {
		int blur = 4;
		PatternColorMap ret = new PatternColorMap(new ARGB(0, 0, 0, 0), new ARGB(0, 0, 0, 0), new ARGB(0, 0, 0, 0));
		do {
			ARGBXY[] textureMap = getTexureMapFromItem(item, blur);
			ret = logicOfGetPatternColorMap(textureMap, item);
			blur++;
		} while(ret.blackColor.equals(ret.cornerColor) || ret.blackColor.equals(ret.defColor) || ret.defColor.equals(ret.cornerColor));
		return ret;
	}

	private static PatternColorMap logicOfGetPatternColorMap(ARGBXY[] textureMap, ItemStack item) throws IOException {
		PatternColorMap ret = new PatternColorMap(new ARGB(0, 0, 0, 0), new ARGB(0, 0, 0, 0), new ARGB(0, 0, 0, 0));
		if(textureMap.length != 0) {
			int centerX = getTextureOfItem(item).getWidth() / 2;
			int centerY = getTextureOfItem(item).getHeight() / 2;

			for(int i = 0; i < textureMap.length; i++) {
				if(textureMap[i].x == centerX && textureMap[i].y == centerY) {
					ret.defColor = textureMap[i].getARGB();
				}
			}

			for(int i = 0; i < textureMap.length; i++) {
				if(!textureMap[i].getARGB().equals(ret.defColor)) {
					if(ret.blackColor.equals(ARGB.NULLCOLOR)) {
						ret.blackColor = textureMap[i].getARGB();
					} else {
						if(ret.blackColor.a <= textureMap[i].a && textureMap[i].a != 0 && ret.blackColor.a < ret.defColor.a) {
							ret.blackColor = textureMap[i].getARGB();
						} else if(ret.cornerColor.equals(ARGB.NULLCOLOR) && ret.blackColor.getColor().getRGB() > textureMap[i].getARGB().getColor().getRGB()) {
							ret.cornerColor = getPixelARGB(textureMap[i].getARGB().getColor().brighter().getRGB());
						}
					}
				}
			}

		}
		return ret;
	}

	/**
	 * Create texture from ItemStack with texture and ItemStack with pattern
	 * texture
	 * 
	 * @param texItem
	 *            ItemStack with texture
	 * @param baseItem
	 *            ItemStack with pattern texture
	 * @return texture in BufferedImage
	 * @throws NullPointerException
	 *             if anything happend
	 */

	public static BufferedImage drawCustomTexture(ItemStack texItem, ItemStack baseItem) throws NullPointerException {
		ARGB def = new ARGB(40, 101, 101, 101);
		ARGB black = new ARGB(50, 128, 128, 128);
		ARGB corner = new ARGB(30, 76, 76, 76);
		BufferedImage texture = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
		try {
			texture = ImageHelper.getTextureOfItem(baseItem);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PatternColorMap map = null;
		try {
			map = getPatternColorMapFromItemTexture(texItem);
		} catch (IOException e) {
			System.err.println("Oops! IOExeption!");
			throw new NullPointerException();
		}
		if(texture.getWidth() == 32 && texture.getHeight() == 32) {
			for(int i = 0; i < 32; i++) {
				for(int j = 0; j < 32; j++) {
					if(ImageHelper.getPixelARGB(texture.getRGB(i, j)).equals(def)) {
						texture.setRGB(i, j, ((Color) map.defColor.getColor()).getRGB());
					} else if(ImageHelper.getPixelARGB(texture.getRGB(i, j)).equals(black)) {
						texture.setRGB(i, j, ((Color) map.blackColor.getColor()).getRGB());
					} else if(ImageHelper.getPixelARGB(texture.getRGB(i, j)).equals(corner)) {
						texture.setRGB(i, j, ((Color) map.cornerColor.getColor()).getRGB());
					}
				}
			}
		} else {
			System.err.println("Yay! Texture error!!");
			throw new NullPointerException();
		}
		return texture;
	}
}
