package dugeongame.level;

import core.Texture;

public class TileFactory {
	
	private Texture t;
	private int w,h;
	
	public TileFactory(Texture t, int w, int h) {
		this.t = t;
		this.w = w;
		this.h = h;
	}
	
	public Tile genStaticTile(int x, int y) {
		return new Tile(x, y, w, h,t);
	}
}
