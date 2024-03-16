package PaooGAME.Graphics;
import PaooGAME.Inputs.Keyboard;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static PaooGAME.Game.stBar;
import static PaooGAME.Items.Hero.statusBarImg;

public class ImageLoader { /*! \fn  public static BufferedImage loadImage(String path)
        \brief Incarca o imagine intr-un obiect BufferedImage si returneaza o referinta catre acesta.
        \param path Calea relativa pentru localizarea fisierul imagine.
     */
    public static BufferedImage[][] AnimationHero;  // matrice in care incarc toate subimaginile
    public static BufferedImage[][] AnimationHeroLeft;
    public static BufferedImage LoadImage(String path) {
        BufferedImage image = null;
        try {
            if (path == null) {
                throw new IllegalArgumentException("Path cannot be null.");
            }
            image = ImageIO.read((Objects.requireNonNull(ImageLoader.class.getResource(path))));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
// metoda cu ajutorul careia imi incarc animatiile intr-o matrice
    public static void InitItem(int linii, int coloane,String s1) {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage(s1));
        AnimationHero = new BufferedImage[linii][coloane];


        for (int i = 0; i < AnimationHero.length; ++i)
        {
                for (int j = 0; j < AnimationHero[i].length; ++j)   // contine un vectori de subimagini pt o i ipostaza a prot
                {
                    AnimationHero[i][j] = sheet.crop(j, i);
                }
        }


        statusBarImg=LoadImage(stBar);

    }
}
