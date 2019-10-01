package dugeongame.entity;
import core.GameObject;
import core.Texture;
import core.TextureFrame;

public class OArma extends GameObject{
	
	public OArma(Texture t) {
		this.texture = t;
		//addAnimation("idle", new TextureFrame(0,0,16,16,t.getPxFactor()));
		addAnimation("idle", new TextureFrame(0,194,30,30,t.getPxFactor()));
		setCurrentAnimation("idle");
		setFramesPerSecond(0);
	}
}
