package core;

public class TextureFrame {
	private float xPos;
	private float yPos;
	private float wSize;
	private float hSize;
	
	public TextureFrame(float xPos, float yPos, float wSize, float hSize, float pxFactor) {
		this.xPos = xPos * pxFactor;
		this.yPos = yPos * pxFactor;
		this.wSize = wSize * pxFactor;
		this.hSize = hSize * pxFactor;
	}
	public float getxPos() {
		return xPos;
	}
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}
	public float getyPos() {
		return yPos;
	}
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}
	public float getwSize() {
		return wSize;
	}
	public void setwSize(float wSize) {
		this.wSize = wSize;
	}
	public float gethSize() {
		return hSize;
	}
	public void sethSize(float hSize) {
		this.hSize = hSize;
	}
}
