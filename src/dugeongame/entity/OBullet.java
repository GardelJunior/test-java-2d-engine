package dugeongame.entity;
import core.GameObject;
import core.Mathf;
import core.Texture;
import core.TextureFrame;

public class OBullet extends GameObject{
	private float xVel,yVel;
	private int timer = 35;
	
	private boolean dead = false;
	
	public OBullet(Texture t) {
		//addAnimation("idle", new TextureFrame(0,32,16,16,t.getPxFactor()));
		addAnimation("idle", 
				new TextureFrame(0,48,16,16,t.getPxFactor()),
				new TextureFrame(0,48 + 16,16,16,t.getPxFactor())
		);
		setCurrentAnimation("idle");
		setzRotation(Mathf.randomInRangei(0, 360));
	}
	
	@Override
	public void update() {
		if(timer > 0) {
			timer--;
		}else {
			dead = true;
		}
		x += xVel;
		y += yVel;
	}



	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	public boolean isDead() {
		return dead;
	}
}
