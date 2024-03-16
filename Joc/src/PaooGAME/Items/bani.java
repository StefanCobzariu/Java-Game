package PaooGAME.Items;

import PaooGAME.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class bani extends Items{
    public static int COINS_WIDTH=150;
    public static int COINS_HEIGTH=32;


    public bani(int x, int y){
        super(x,y,COINS_WIDTH,COINS_HEIGTH);
    }

    public float GetX(){
        return this.x;
    }
    public float GetY(){
        return this.y;
    }

    public void Draw(Graphics g,int xCameraOffset){
        BufferedImage coins= ImageLoader.LoadImage("/Coins.png");
        g.drawImage(coins,(int)GetX()-xCameraOffset,(int)GetY(),COINS_WIDTH,COINS_HEIGTH,null);
    }

    // de facut coliziune cu eroul ca sa dispara
}
