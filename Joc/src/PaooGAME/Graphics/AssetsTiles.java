package PaooGAME.Graphics;

import java.awt.image.BufferedImage;

public class AssetsTiles {

    public static BufferedImage grass1;
    public static BufferedImage grass2;
    public static BufferedImage grass3;
    public static BufferedImage grass4;
    public static BufferedImage soil1;
    public static BufferedImage soil2;
    public static BufferedImage soil3;
    public static BufferedImage soil4;
    public static BufferedImage leaves1;
    public static BufferedImage leaves2;
    public static BufferedImage leaves3;
    public static BufferedImage leaves4;
    public static BufferedImage transparent;
    public static BufferedImage edge;
    public static BufferedImage edge2;
    public static BufferedImage rama1;
    public static BufferedImage rama2;
    public static BufferedImage rama3;
    public static BufferedImage rama4;

    public static void InitTile() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/padure.png"));

        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        grass1 = sheet.crop(0, 0);
        grass2 = sheet.crop(1, 0);
        grass3 = sheet.crop(2, 0);
        grass4 = sheet.crop(3, 0);
        transparent=sheet.crop(4,0);
        soil1=sheet.crop(0,1);
        soil2=sheet.crop(1,1);
        soil3=sheet.crop(2,1);
        soil4=sheet.crop(3,1);
        leaves1=sheet.crop(0,2);
        leaves2=sheet.crop(1,2);
        leaves3=sheet.crop(2,2);
        leaves4=sheet.crop(3,2);
        edge=sheet.crop(2,3);
        edge2=sheet.crop(1,3);
        rama1=sheet.crop(0,5);
        rama2=sheet.crop(2,5);
        rama3=sheet.crop(4,5);
        rama4=sheet.crop(0,6);
    }
}
