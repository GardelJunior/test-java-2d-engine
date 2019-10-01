package dugeongame.level;

import core.Texture;
import core.TextureFrame;

public class Tile {
	public static final int SIZE = 64;
	
	private TextureFrame tileTexture;
	private boolean isStatic = true;
	
	public Tile(int x, int y, int w, int h, Texture t) {
		this.tileTexture = t.getTextureFrameAsTile(x, y, w, h);
	}
	
	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}
	
	public float getxPos() {
		return tileTexture.getxPos();
	}
	
	public float getyPos() {
		return tileTexture.getyPos();
	}
	
	public float getwSize() {
		return tileTexture.getwSize();
	}
	
	public float gethSize() {
		return tileTexture.gethSize();
	}
	
	public void update() {
		
	}
}
