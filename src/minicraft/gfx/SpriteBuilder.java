package minicraft.gfx;

public class SpriteBuilder {
	private int color = 0;
	private int sx = 0;
	private int sy = 0;
	private int sw = 1;
	private int sh = 1;
	private int mirror = 0;
	private boolean onepixel = false;
	private int[][] mirrors;
	private Sprite.Px[][] pixels;
	
	
	public SpriteBuilder setColor(int color) {
		this.color = color;
		return this;
	}
	
	public SpriteBuilder setPos(int pos) {
		int x = pos%32;
		int y = pos/32;
		setSx(x);
		setSy(y);
		return this;
	}
	
	public SpriteBuilder setSx(int sx) {
		this.sx = sx;
		return this;
	}
	
	public SpriteBuilder setSy(int sy) {
		this.sy = sy;
		return this;
	}
	
	public SpriteBuilder setSw(int sw) {
		this.sw = sw;
		return this;
	}
	
	public SpriteBuilder setSh(int sh) {
		this.sh = sh;
		return this;
	}
	
	public SpriteBuilder setMirror(int mirror) {
		this.mirror = mirror;
		return this;
	}
	
	public SpriteBuilder setOnepixel(boolean onepixel) {
		this.onepixel = onepixel;
		return this;
	}
	
	public SpriteBuilder setMirrors(int[][] mirrors) {
		this.mirrors = mirrors;
		return this;
	}
	
	public SpriteBuilder setPixels(Sprite.Px[][] pixels) {
		this.pixels = pixels;
		return this;
	}
	
	public Sprite createSprite() {
		return new Sprite(sx, sy, sw, sh, color, mirror, onepixel);
	}
}
