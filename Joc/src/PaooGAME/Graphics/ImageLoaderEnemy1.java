package PaooGAME.Graphics;

import java.awt.image.BufferedImage;

public class ImageLoaderEnemy1 {
    public static BufferedImage[][] AnimationEnemy;




    public static void InitEnemy1(int linii, int coloane, String s1) {
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage(s1));
        AnimationEnemy = new BufferedImage[linii][coloane];
        for (int i = 0; i < AnimationEnemy.length; ++i) {
            for (int j = 0; j < AnimationEnemy[i].length; ++j)   // contine un vectori de subimagini pt o i ipostaza a prot
            {
                AnimationEnemy[i][j] = sheet1.crop(j, i);
            }
        }
    }
}
