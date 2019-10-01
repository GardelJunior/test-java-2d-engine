package dugeongame.entity;
import static org.lwjgl.opengl.GL11.*;

import core.GameObject;
import core.Mathf;
import core.Renderer;
import core.Texture;
import core.TextureFrame;

public class OPlayer extends GameObject {
	
	private TextureFrame shadow;
	public float jumpH = 0;
	public float hSPD = 10;
	public float yVel = 1;
	public boolean jumping = false;
	
	public OPlayer(Texture t) {
		this.texture = t;
		
		setxOffset(HALFSIZE);
		setyOffset(HALFSIZE + HALFSIZE/2);
		setX(400);
		setY(300);
		
		addAnimation("idle", 
				new TextureFrame(368 +  0,32,16,16,t.getPxFactor()),
				new TextureFrame(368 + 16,32,16,16,t.getPxFactor()),
				new TextureFrame(368 + 32,32,16,16,t.getPxFactor()),
				new TextureFrame(368 + 48,32,16,16,t.getPxFactor())
		);
		addAnimation("run", 
				new TextureFrame(432 +  0,32,16,16,t.getPxFactor()),
				new TextureFrame(432 + 16,32,16,16,t.getPxFactor()),
				new TextureFrame(432 + 32,32,16,16,t.getPxFactor()),
				new TextureFrame(432 + 48,32,16,16,t.getPxFactor())
		);
		shadow = new TextureFrame(0, 16, 16, 16, t.getPxFactor());
		setCurrentAnimation("idle");
	}
	
	@Override
	public void update() {
		xScale = Mathf.lerp(xScale, 1 * Mathf.sign(xScale), .4f);
		yScale = Mathf.lerp(yScale, 1 * Mathf.sign(yScale), .4f);
		if(jumping) {
			if(yVel > 0.1) {
				jumpH += hSPD * yVel;
				yVel *= 0.9;
				xScale = Mathf.sign(xScale) * 0.8f;
				yScale = 1.2f;
			}else if(yVel > 0){
				yVel = -0.25f;
			}else {
				jumpH += hSPD * yVel;
				yVel *= 1.2;
				if(jumpH <= 0) {
					jumpH = 0;
					yVel = 1;
					jumping = false;
					xScale = 1.4f * Mathf.sign(xScale);
					yScale = 0.6f;
				}
			}
		}
	}


	@Override
	public void render() {
		glEnable(GL_TEXTURE_2D);
		updateAnimation();
		glPushMatrix();
			glTranslatef(x + 5, y + 5, 0);
			glTranslatef(-xOffset, -yOffset, 0);
			Renderer.renderTexturedQuad(SIZE, SIZE, shadow.getxPos(), shadow.getyPos(), shadow.getwSize(), shadow.gethSize());
		glPopMatrix();
		glPushMatrix();
			transform();
			glTranslatef(0, -jumpH, 0);
			glColor4f(1f, 1f, 1f, 1.0f);
			Renderer.renderTexturedQuad(SIZE, SIZE, current_frame.getxPos(), current_frame.getyPos(), current_frame.getwSize(), current_frame.gethSize());
		glPopMatrix();
		
	}

	public float getJumpH() {
		return jumpH;
	}
}
