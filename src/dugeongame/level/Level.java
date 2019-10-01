package dugeongame.level;

public class Level {
	private Chunk[][] chunk;
	
	public Level(Chunk[][] map) {
		this.chunk = map;
	}
	
	public void render() {
		for(int i = 0 ; i < chunk.length ; i++) {
			for(int j = 0 ; j < chunk[i].length ; i++) {
				chunk[i][j].render();
			}
		}
	}
}
