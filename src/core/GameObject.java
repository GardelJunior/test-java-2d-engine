package core;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameObject {
	
	public static final float SIZE = 64;
	public static final float HALFSIZE = SIZE/2;
	
	protected float x, y;
	protected float width, height;
	protected float xScale = 1, yScale = 1;
	protected float xOffset, yOffset;
	protected float zRotation;
	
	protected Texture texture;
	
	protected float imageIndex = 0, depth;
	protected float framesPerSecond = 1.0f/8.0f;
	
	protected String current_animation = "";
	protected TextureFrame current_frame = new TextureFrame(0, 0, 0, 0, 0);
	protected List<TextureFrame> current_animation_list = new ArrayList<>();
	protected Map<String,List<TextureFrame>> frames = new HashMap<String,List<TextureFrame>>();
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getxScale() {
		return xScale;
	}

	public void setxScale(float xScale) {
		this.xScale = xScale;
	}

	public float getyScale() {
		return yScale;
	}

	public void setyScale(float yScale) {
		this.yScale = yScale;
	}

	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}

	public float getzRotation() {
		return zRotation;
	}

	public void setzRotation(float zRotation) {
		this.zRotation = zRotation;
	}
	
	public float getImageIndex() {
		return imageIndex;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public float getFramesPerSecond() {
		return framesPerSecond;
	}

	public void setFramesPerSecond(float framesPerSecond) {
		this.framesPerSecond = framesPerSecond;
	}

	public void transform() {
		glTranslatef(x, y, 0);
		glRotatef(zRotation, 0, 0, 1);
		glScalef(xScale, yScale, 1);
		glTranslatef(-xOffset, -yOffset, 0);
	}
	
	public void updateAnimation() {
		this.current_frame = current_animation_list.get((int)imageIndex);
		this.imageIndex = this.imageIndex + framesPerSecond;
		if(imageIndex >= current_animation_list.size()) {
			imageIndex = 0;
			onAnimationEnds();
		}
	}
	
	public void render() {
		glEnable(GL_TEXTURE_2D);
		updateAnimation();
		glPushMatrix();
			transform();
			glColor4f(1f, 1f, 1f, 1.0f);
			Renderer.renderTexturedQuad(SIZE, SIZE, current_frame.getxPos(), current_frame.getyPos(), current_frame.getwSize(), current_frame.gethSize());
		glPopMatrix();
	}
	
	public void update() {
		
	}
	
	public float getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	
	public void addAnimation(String name, TextureFrame...frames) {
		this.frames.put(name, Arrays.asList(frames));
	}
	
	public void setCurrentAnimation(String name) {
		if(!current_animation.equals(name)) {
			this.current_animation = name;
			this.current_animation_list = this.frames.get(name);
		}
	}
	
	public void onAnimationEnds() {
		
	}
}
