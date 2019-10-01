package dugeongame.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import core.Texture;

public class LevelLoader {
	public Level loadLevel(String file, Texture t) {
		Level l = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("./resources/level" + file)));
			String line = null;

			HashMap<Integer, Tile> mapping = new HashMap<>();

			int mode = 0; // Nothing

			Chunk[][] map = null;
			Tile[][] idMapping = null;
			
			int xCells = 0,yCells = 0;
			int chunkW = 0,chunkH = 0;
			
			int lineCounter = 0;
			
			TileFactory tileFactory = null;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("(!.*!)", ""); // Limpa os comentários
				if (line.startsWith("#")) {
					if (line.startsWith("#config ")) {
						mode = 1; // Configurando
						String[] valores = line.split(" ")[1].split(",");
						int wSize = Integer.parseInt(valores[0]);
						int hSize = Integer.parseInt(valores[1]);
						tileFactory = new TileFactory(t, wSize, hSize);
					} else if (line.startsWith("#level ")) {
						mode = 2; // Modo mapa
						String[] valores = line.split(" ")[1].split(",");
						String[] valores2 = line.split(" ")[2].split(",");
						xCells = Integer.parseInt(valores[0]);
						yCells = Integer.parseInt(valores[1]);
						
						chunkW = Integer.parseInt(valores2[0]);
						chunkH = Integer.parseInt(valores2[1]);
						map = new Chunk[yCells/chunkH][xCells/chunkW];
						idMapping = new Tile[yCells][xCells];
					}
				} else {
					if(line.isEmpty()) continue;
					if (mode == 1) {
						String[] valores = line.split("=");
						String[] coord = valores[1].trim().split(",");
						mapping.put(Integer.parseInt(valores[0].trim()),
								tileFactory.genStaticTile(
										Integer.parseInt(coord[0]), 
										Integer.parseInt(coord[1])
								));
					} else if (mode == 2) {
						String[] tiles = line.split(",");
						int yPos = lineCounter++;
						for(int i = 0 ; i < tiles.length ; i++) {
							idMapping[yPos][i] = mapping.get(Integer.parseInt(tiles[i]));
						}
					}
				}
			}
			br.close();
			
			int hChunks = xCells/chunkW;
			int vChunks = yCells/chunkH;
			
			for(int i = 0 ; i < vChunks ; i++) {
				for(int j = 0 ; j < hChunks ; j++) {
					map[i][j] = new Chunk(j * Tile.SIZE, i * Tile.SIZE,j * chunkW,i * chunkH,chunkW,chunkH,idMapping);
				}
			}
			
			for(int i = 0 ; i < map.length ; i++) {
				for(int j = 0 ; j < map[i].length ; j++) {
					if(map[i][j] == null) {
						System.out.println("mapa nullo " + i + " " + j);
					}
				}
			}
			
			return new Level(map);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;
	}
}
