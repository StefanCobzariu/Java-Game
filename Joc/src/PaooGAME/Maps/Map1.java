package PaooGAME.Maps;

import java.awt.*;

import PaooGAME.Camera;
import PaooGAME.Game;
import PaooGAME.Tiles.Tile;

import java.io.*;

import static PaooGAME.Game.GAME_HEIGHT;
import static PaooGAME.Game.GAME_WIDTH;

public class Map1 {

    public static int MAP_WIDTH=GAME_WIDTH*3;
    private int width; //latimea hartii in numar de dale
    private int height; //inaltimea hartii in numar de dale

    //private int [][] tiles;//referinta catre o matrice cu codurile dalelor ce vor construi harta
    public int[][][] tiles;//referinta catre o matrice cu codurile dalelor ce vor construi harta

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public Map1(Game game) {

        LoadWorld(game);
    }
    public Map1 UpdateMap(Game game) {
        return new Map1(game);

    }

    public void Draw(Graphics g, int xCameraOffset,int mapId) {
        ///Se parcurge matricea de dale (codurile aferente) si se deseneaza harta respectiva
        for (int y = 0; y < (GAME_HEIGHT  / Tile.TILE_HEIGHT); y++) {

            for (int x = 0; x < (MAP_WIDTH/ Tile.TILE_WIDTH); x++) {

                GetTile(x, y,mapId).Draw(g, x * Tile.TILE_HEIGHT- xCameraOffset, y * Tile.TILE_WIDTH);
            }
        }
    }

    public Tile GetTile(int x, int y,int mapId) {
        if (x < 0 || y < 0 || x >= (MAP_WIDTH/Tile.TILE_WIDTH) || y >= height) {
            return Tile.tTile;
        }
        Tile t = Tile.tiles[tiles[mapId][x][y]];
        if (t == null) {
            return Tile.tTile;
        }
        return t;
    }


    public void LoadWorld(Game game) {
        ///Se stabileste latimea hartii in numar de dale.
        width = 78; // dim in latime a hartii

        ///Se stabileste inaltimea hartii in numar de dale
        height = 14;

        ///Se construieste matricea de coduri de dale
        tiles = new int[game.maxMaps][width][height];

        try {
            InputStream is=null;
            if(game.currentMap==0) {
                 is = getClass().getResourceAsStream("/Map1.txt");
            }
            else if(game.currentMap==1)
            {

                 is = getClass().getResourceAsStream("/Map2.txt");


            }else if(game.currentMap==2)
            {

                is = getClass().getResourceAsStream("/Map3.txt");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int i = 0,j=0;
            while (i < width && j < height) {
                String line = br.readLine();
                while (i < width) {
                    String[] numbers = line.split(" "); // delimitatorul , valorile in fisier trebuie delimitate de 1 sing spatiu
                    int ide = Integer.parseInt(numbers[i]);
                    tiles[game.currentMap][i][j] = ide;
                    i++;
                }
                if (i == width) {
                    i = 0;
                    j++;
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Fisierul nu a fost gasit.");
            e.printStackTrace();
        }
    }

}

