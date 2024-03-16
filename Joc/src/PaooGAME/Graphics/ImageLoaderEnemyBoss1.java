package PaooGAME.Graphics;

import java.awt.image.BufferedImage;

public class ImageLoaderEnemyBoss1 {
   
    
        public static BufferedImage[][] AnimationEnemyBoss1;
        public static void InitBossEnemy1(int linii, int coloane, String s1) {
            SpriteSheet sheet1 = new SpriteSheet(ImageLoader.LoadImage(s1));
            AnimationEnemyBoss1 = new BufferedImage[linii][coloane];
            for (int i = 0; i < AnimationEnemyBoss1.length; ++i) {
                for (int j = 0; j < AnimationEnemyBoss1[i].length; ++j)   // contine un vectori de subimagini pt o i ipostaza a prot
                {
                    AnimationEnemyBoss1[i][j] = sheet1.crop(j, i);
                }
            }
        }
}


