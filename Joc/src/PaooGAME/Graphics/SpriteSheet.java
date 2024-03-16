package PaooGAME.Graphics;
import java.awt.image.BufferedImage;

public class SpriteSheet {
    BufferedImage sprite;
    private static final int tileWidth=48;
    private static final int tileHeight=48;

    public SpriteSheet(BufferedImage bf) {
        this.sprite=bf;
    }
    public BufferedImage crop(int x,int y)
    {
        return sprite.getSubimage(x* tileWidth,y*tileHeight, tileWidth ,tileHeight);
    }

}