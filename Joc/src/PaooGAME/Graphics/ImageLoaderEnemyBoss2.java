package PaooGAME.Graphics;


import java.awt.image.BufferedImage;

public class ImageLoaderEnemyBoss2 {


    public static BufferedImage[][] AnimationEnemyBoss2;

    public static void InitBossEnemy2(int linii, int coloane, String s1) {
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage(s1));
        AnimationEnemyBoss2 = new BufferedImage[linii][coloane];
        for (int i = 0; i < AnimationEnemyBoss2.length; ++i) {
            for (int j = 0; j < AnimationEnemyBoss2[i].length; ++j)   // contine un vectori de subimagini pt o i ipostaza a prot
            {
                AnimationEnemyBoss2[i][j] = sheet1.crop(j, i);
            }
        }
    }

}



