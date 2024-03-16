package PaooGAME.Items;
import PaooGAME.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Coin extends Items{
    Rectangle hitbox;
    public static int COIN_WIDTH=25;
    public static int COIN_HEIGTH=32;
    public boolean active;

   public boolean GetActive(){return this.active;};
    public Coin(int x, int y){
        super(x,y,COIN_WIDTH,COIN_HEIGTH);
        InitHitbox();
          active=true;
    }
    public void InitHitbox( ){
        hitbox=new Rectangle((int)GetX()-1,(int)GetY()+3,COIN_WIDTH-5,COIN_HEIGTH-6);

    }
    public void DrawHitbox(Graphics g,int xOffsetCamera){
        g.setColor(Color.PINK);
        g.drawRect(this.hitbox.x-xOffsetCamera,this.hitbox.y,this.hitbox.width,this.hitbox.height);
    }
    public float GetX(){
        return this.x;
    }
    public float GetY(){
        return this.y;
    }

    public void Draw(Graphics g,int xCameraOffset){
        BufferedImage coins= ImageLoader.LoadImage("/Coin.png");
        g.drawImage(coins,(int)GetX()-xCameraOffset,(int)GetY(),COIN_WIDTH,COIN_HEIGTH,null);
       // DrawHitbox(g,xCameraOffset);
    }

    // de facut coliziune cu eroul ca sa dispara
}

