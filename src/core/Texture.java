package core;

import static org.lwjgl.opengl.GL11.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public class Texture {
	private float pxFactor;
	private int width;
	private int height;
	private int texture;
	private static int currentBind = 0;
	
	public Texture(String filename) {
		try {
			BufferedImage img;
			img = ImageIO.read(new File("./resources/textures" + filename));
			
			this.width = img.getWidth();
			this.height = img.getHeight();
			
			this.pxFactor = 1.0f / (float) this.width;
			
			int[] pixels = new int[img.getWidth() * img.getHeight()];
			img.getRGB(0, 0, img.getWidth(), img.getHeight(), pixels, 0, img.getWidth());
			
			try(MemoryStack s = MemoryStack.stackPush()){
				ByteBuffer colorBuffer = MemoryUtil.memCalloc(pixels.length * 4);
				//ARGB (BufferedImage) -> RGBA (OpenGL)
				// 8 bits (Alfa) 8 bits (Red) 8 bit (Green) 8 Bit (Blue)
				for(int pixel : pixels) {
					colorBuffer.put((byte) (pixel >> 16 & 0xff));
					colorBuffer.put((byte) (pixel >> 8 & 0x00ff));
					colorBuffer.put((byte) (pixel & 0x000000ff));
					colorBuffer.put((byte) (pixel >> 24 & 0xff));
				}
				
				colorBuffer.flip();
				
				/* Aloca uma textura na memória do OpenGL */
				texture = glGenTextures();
				glBindTexture(GL_TEXTURE_2D, texture);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
				glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
				
				/* Manda os pixels para o OpenGL gerar uma textura */
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, img.getWidth(), img.getHeight(), 0,GL_RGBA, GL_UNSIGNED_BYTE, colorBuffer);
				
				glBindTexture(GL_TEXTURE_2D, 0);
				
				MemoryUtil.memFree(colorBuffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public TextureFrame getTextureFrameAsTile(int tileX, int tileY, int w, int h) {
		return new TextureFrame(tileX, tileY, w, h, pxFactor);
	}
	
	public void bind() {
		if(currentBind != texture) {
			glBindTexture(GL_TEXTURE_2D, texture);
			currentBind = texture;
		}
	}
	
	public static void unbind() {
		glBindTexture(GL_TEXTURE_2D, 0);
		currentBind = 0;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getTexture() {
		return texture;
	}

	public float getPxFactor() {
		return pxFactor;
	}
}
