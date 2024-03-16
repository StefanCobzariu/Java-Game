package PaooGAME;

import static PaooGAME.Items.Items.isTileSolid;
import static PaooGAME.Tiles.Tile.TILES_DEFAULT_SIZE;

//offset dreptunghi 32
public class Coliziune{
    Game game;
    Coliziune(Game game)
    {
            this.game=game;
    }
    // reurnez daca tile-ul este solid
        public static boolean IsSolid(float x , float y,Game game){
        int maxWidth=78* TILES_DEFAULT_SIZE; //pixeli

        if(x<0 || x>=(maxWidth) ) // ca sa ramanem in fereastra
            return true;

        if(y<0 || y>=Game.GAME_HEIGHT)
            return true;
        float xIndex=x/48;
        float yIndex=y/48;

        return isTileSolid((int)xIndex,(int)yIndex,game);

    }
    // verific daca cele 4 colturi ale unei enititati(ex - eroul) nu se intesecteaza cu un obiect tile solid
    public static boolean CanMoveHere(float x, float y,float width,float height,Game game){
        if(!IsSolid(x, y, game)  && !IsSolid(x + width, y + height, game) && !IsSolid(x+width,y,game) && !IsSolid(x,y+height,game)) {
            return true;  // daca niciunul dintre colturi nu se afla pe un tile solid - returnez true , adica poate fi mutat
        }
        return false; // daca exista macar un colt pe un tile solid , atunci returnez false - nu poate fi mutat

    }
}
