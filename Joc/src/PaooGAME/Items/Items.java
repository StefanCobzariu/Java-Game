package PaooGAME.Items;

import PaooGAME.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static PaooGAME.Coliziune.IsSolid;
import static PaooGAME.Game.TILES_SIZE;
import static PaooGAME.Tiles.Tile.TILES_DEFAULT_SIZE;

public abstract class Items {   // metode comune pt enemy , hero
    protected float x, y; // ca sa putem avea acces la ele(pozitiile pe harta) din subclase
    protected int width;//latime img entitate
    protected int height;//inaltime imagine entitate

    protected Rectangle hitbox; // dreptunghiul de coliziune


    // public  void update();

    //public  void render(Graphics g, int xCameraOffset);


    public Items(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /// pt verificare desenez drept de coliziune
    protected void drawHitbox(Graphics g, int xCameraOffset) {
        g.setColor(Color.PINK);
        g.drawRect(hitbox.x - xCameraOffset, hitbox.y, hitbox.width, hitbox.height);
    }

    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle((int) x, (int) y, (int) width, (int) height);  // creez drept in jurul pers
    }

    /*   protected void updateHitbox() //actualizeaza coordonatele pt hitbox in functie de coordonatele personajului
       {
           hitbox.x=(int)this.x;
           hitbox.y=(int)this.y;
       }
   */
    public Rectangle getHitbox() {
        return hitbox;
    }

    public float GetItemsPosNextToWall(Rectangle hitbox, float xSpeed) {       // dreapta
        int currentTile = (int) (hitbox.x / TILES_SIZE);
        if (xSpeed > 0) {// verific unde are loc coliziunea (in partea dreapta sau stanga), daca e zero nu avem coliziune
            int tileXPos = currentTile * TILES_SIZE;        // astefl vom avea diferenta intre dim un tile si dim pt erou
            int xOffset = (int) (TILES_SIZE - hitbox.width);   // dim pt erou
            return tileXPos + xOffset - 1;
        } else {
            return currentTile * TILES_SIZE;
        }
    }

    public float GetItemsPosUnderRoofOrAboveFloor(Rectangle hitbox, float airSpeed) {       // dreapta
        int currentTile = (int) (hitbox.y / TILES_SIZE);
        if (airSpeed > 0) {// verific daca merg in jos sau sus
            // >0 - cadere - atingere pamant
            int tileYPos = currentTile * TILES_SIZE;
            int yOffset = (int) (TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else { //saritura - atingere marigne superioara
            return currentTile * TILES_SIZE;
        }
    }
    // verific daca entitatea atinge pamantul
    public boolean IsItemTouchingGround(Rectangle hitbox, Game game) {
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, game))
            if (!IsSolid(hitbox.x + hitbox.width + 1, hitbox.y, game))
                return false; // nu este pe pamant
        return true;
    }

    public float GetHitboxXc()  // coordonata x a drept de coliziune
    {
        return this.x;
    }

    public static boolean IsFloor(Rectangle hitbox, float xSpeed,Game game) {
        if(xSpeed>0)  // la dreapta
        {
            return IsSolid(hitbox.x+ hitbox.width + xSpeed, hitbox.y+ hitbox.height +1, game);
        }

        return IsSolid(hitbox.x+xSpeed, hitbox.y+ hitbox.height +1, game);
    }
    // verific daca tile-ul este solid
    public static boolean isTileSolid(int xTile, int yTile, Game game) {
        int maxWidth=78* TILES_DEFAULT_SIZE;
        int id = game.map1.tiles[game.currentMap][(int) xTile][(int) yTile];
            // am 19 tiles  care au id intre 0-18 , verific daca e tile

        if (id != 12) {
                return true;
            }
        return false;
    }

}
