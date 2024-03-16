package PaooGAME.Graphics;

import java.awt.image.BufferedImage;

public class ImageLoaderEnemyBoss3 {


    public static BufferedImage[][] AnimationEnemyBoss3;
    public static void InitBossEnemy3(int linii, int coloane, String s1) {
        SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage(s1));
        AnimationEnemyBoss3 = new BufferedImage[linii][coloane];
        for (int i = 0; i < AnimationEnemyBoss3.length; ++i) {
            for (int j = 0; j < AnimationEnemyBoss3[i].length; ++j)   // contine un vectori de subimagini pt o i ipostaza a prot
            {
                AnimationEnemyBoss3[i][j] = sheet1.crop(j, i);
            }
        }
    }
}
