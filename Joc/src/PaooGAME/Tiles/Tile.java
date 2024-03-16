package PaooGAME.Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {
    public final static int TILES_DEFAULT_SIZE=48;
    public final static int TILES_IN_WIDTH =26;// harta in nr de dale vizibile care vor fi desenate de camera
    public final static int TILES_IN_HEIGHT=14; // harta in nr de dale (in inaltime)care vor fi desenate de camera
    public final static int TILE_WIDTH=48;
    public final static int TILE_HEIGHT=48;

    public static Tile[] tiles   = new Tile[TILES_DEFAULT_SIZE];       /*!< Vector de referinte de tipuri de dale.*/


    public static Tile gTile1= new grassTile1(0);
    public static Tile gTile2= new grassTile2(1);
    public static Tile gTile3= new grassTile3(2);
    public static Tile gTile4= new grassTile4(3);
    public static Tile sTile1=new soilTile1(4);
    public static Tile sTile2=new soilTile2(5);
    public static Tile sTile3=new soilTile3(6);
    public static Tile sTile4=new soilTile4(7);
    public static Tile lTile1=new leavesTile1(8);
    public static Tile lTile2=new leavesTile2(9);
    public static Tile lTile3=new leavesTile3(10);
    public static Tile lTile4=new leavesTile4(11);
    public static Tile tTile=new transparentTile(12);  // transparent
    public static Tile eTile=new edgeTile1(13);   // margine frunze/ iarba dreapta
    public static Tile eTile2=new edgeTile2(14); // margine frunze/ iarba stanga
    public static Tile rTile1=new ramaTile1(15);
    public static Tile rTile2=new ramaTile2(16);
    public static Tile rTile3=new ramaTile3(17);
    public static Tile rTile4=new ramaTile4(18);



    public BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;
    public Tile(BufferedImage image, int idd)
    {
        img = image;
        id = idd;

        tiles[id] = this;
    }
    public void Draw(Graphics g, int x, int y)
    {
        /// Desenare dala

        /*
        g Contextul grafic in care sa se realizeze desenarea
        x coordonata x unde se deseneaza dala in cadrul ferestrei
     */

        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }
    public int GetId()
    {
        return id;
    }
}


