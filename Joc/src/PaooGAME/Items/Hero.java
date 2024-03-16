package PaooGAME.Items;
import PaooGAME.Game;
import PaooGAME.Graphics.ImageLoader;
import PaooGAME.Graphics.LoadAnim;
import PaooGAME.Inputs.Keyboard;
import java.awt.*;
import java.awt.image.BufferedImage;

import static PaooGAME.Coliziune.*;
import static PaooGAME.Game.*;


public class Hero extends Items {

    public Game game;
    static final int linii = 27, coloane = 8;
    private int aniTick, aniIndex;// daca avem  120 fps - 5 animatii pe linie =>24
    private final int aniSpeed=10;
    private int playerAction = LoadAnim.IDLE;
    private String HeroAnim="/heroSprite.png";
    private boolean moving =false;

    private final float  Herospeed=2.0f;  // adica inainteaza cu 2 pixeli pe harta
    private  float xDrawOffset=39* Game.SCALE; // de unde ar trebui sa inceapa drept de coliziune
    private float yDrawOffset=33* Game.SCALE;

        // pt saritura  -variabile- si gravitatie
    private float airSpeed=0f;    // daca suntem pe sol va ramane 0, daca sarimp prim jumpSpeed
    private final float  gravity=0.04009f *  Game.SCALE; // variaza in functie de cat de rapid vreau sa ajunga la sol,   daca nu am gravitatie care ma aduce la sol , salt infinit
    private final float jumpSpeed=-2.25f *Game.SCALE; // cand vreau sa sara va merge in sus pe axa Y , de aici vine minus
    private final float fallSpeedAfterCollision=0.5f * Game.SCALE;
    private boolean inAir=false;
    private boolean attackChecked=false;
    private int statusBarWidth=(int)(128*2);
    private int statusBarHeight=(int)(21*2);
    private int statusBarX=0;
    private int statusBarY=0;
    private int healthBarWidth=(int)((216));
    private int healthBarHeight=(int)(4*Game.SCALE);
    private int healthStartX=(int)(15*2);
    private int healthStartY=(int)(18);
    public static BufferedImage statusBarImg;

    private int maxLife=300;
    private int currentLife=maxLife;
    private int healthWidth=healthBarWidth;
    public boolean inlife=true;
    public Rectangle attackHitbox;
    public static int xp=0;
    public void drawStBar(Graphics g){
        g.drawImage(statusBarImg,statusBarX,statusBarY,statusBarWidth,statusBarHeight,null);
        g.setColor(Color.red);
        g.fillRect(healthStartX+statusBarX,healthStartY+statusBarY,healthWidth,healthBarHeight);
    }
   public void updateLifeBarDimension(){
        healthWidth=(int)((currentLife/(float)maxLife)*healthBarWidth);
    }
    public void changeLife(int attack_value){
        currentLife=currentLife-attack_value;
        if(currentLife<=0){
            currentLife=0;
            GameOver=true;
        }
    }

    public Hero(float x, float y,int width, int height) {
        super(x, y,width,height);
        ImageLoader.InitItem(linii, coloane,HeroAnim);
        initHitbox(x,y,30*Game.SCALE,35*Game.SCALE);
        // ca sa scad distanta intre erou si margine iarba de scad latimea drept de coliziune
        // urmeaza pt partea din dreapta
        initAttackHitbox();
    }
    public void Spawn(){
        this.hitbox.x=0;
    }

    private void initAttackHitbox() {
        attackHitbox=new Rectangle((int)x,(int)y,(int)(30*Game.SCALE),(int)(25*Game.SCALE));
    }

    public void InitG(Game game)
    {
        this.game=game;
    }
    public void update() {
        if(currentLife<=0){
           // System.out.println("Game Over");
        }

        updateLifeBarDimension();
        updateAttackHitbox();
        updatePos();
        if(playerAction==LoadAnim.ATTACK_2 || playerAction==LoadAnim.LEFT_ATTACK_2)
        {
            checkAttack(game);
        }
        updateAnimationTick();
        setAnimation();

    }
    // daca indexul animatiei imi ajunge la 3 verific daca inamicul este lovit
    private void checkAttack(Game game) {
        if (aniIndex !=1 && aniIndex!=3 && aniIndex!=5 || attackChecked) { // vreau sa verific cand indexul animatiei=1 -3-5 pt ele se poate produce atacul
            return;}
        attackChecked=true;
        game.checkIsEnemyHit(attackHitbox); //verific daca inamicul este lovit si ii scad viata

    }

    public void updateAttackHitbox() {
        if(Keyboard.right){
            attackHitbox.x= this.hitbox.x+hitbox.width+7;

        }
            else if(Keyboard.left) {
            attackHitbox.x = this.hitbox.x - hitbox.width - 7;
        }
            attackHitbox.y=this.hitbox.y+10;
    }

    public void render(Graphics g,int xCameraOffset) {

        g.drawImage(ImageLoader.AnimationHero[playerAction][aniIndex], (int)(hitbox.x-xDrawOffset)-xCameraOffset, (int) (hitbox.y-yDrawOffset), width, height, null);  // cum ma uit pe imagine
        //drawHitbox(g,xCameraOffset);
        //drawAttackHitbox(g,xCameraOffset);
        drawStBar(g);
    }

    public void drawAttackHitbox(Graphics g,int xCamerOffet) {
        g.setColor(Color.red);
        g.drawRect((int)attackHitbox.x-xCamerOffet,(int)attackHitbox.y,(int)attackHitbox.width,(int)attackHitbox.height);
    }

    public void updateAnimationTick()  // va fi adaugata in loop
    {
        aniTick++;
        if (aniTick >= aniSpeed) // se actualizeaza urmatorul cadru din animatie cand conditia este indeplinita
        {
            aniTick = 0;
            aniIndex++; // mereu cand ne schimabm animatia trebuie sa resetam indexul animatiei
            if (aniIndex >= LoadAnim.GetSpriteAmount(playerAction))//Assets.idleAnim.length) // sa fie egal cu  numarul de sprite-uri din animamtie
            {
                aniIndex = 0;
                Keyboard.attacking=false;
                attackChecked=false;
            }
        }
    }
    private void setAnimation() {
        int startAni=playerAction;
        if (moving) { playerAction = LoadAnim.RUNNING;
            if(Keyboard.left){playerAction=LoadAnim.LEFT_RUNNING;}
        } else {
            playerAction = LoadAnim.IDLE;
        }
        if(inAir){
            if(airSpeed<0 )
            {   playerAction = LoadAnim.JUMP;
                if(Keyboard.left){playerAction=LoadAnim.LEFT_JUMP;}
            }
            else
            {
                playerAction = LoadAnim.FALLING;
                if(Keyboard.left){playerAction=LoadAnim.LEFT_FALLING;}
            }
        }
        if(Keyboard.attacking)  // daca am tasta apasata de atac
        {   playerAction = LoadAnim.ATTACK_2;  // atacul va fi in partea dreapta
            if(Keyboard.left){playerAction=LoadAnim.LEFT_ATTACK_2;} // atacul in partea stanga functioneaza doar daca ma misc spre stanga si tin apasat pe

        }
        if(startAni != playerAction) // daca se schimba aniamtia tick + anim index trebuie resetati
        {
            aniTick=0;
            aniIndex=0;
        }
    }
    public boolean collisionWithCoins(Coin coin) {
        try {
            if (this.hitbox.intersects(coin.hitbox)) {


               return true;
            }

        } catch (Exception e) {
            System.out.println("obiect null");
        }
        return false;

    }

        public void resetDirBooleans () {
            Keyboard.left = false;
            Keyboard.right = false;
            Keyboard.up = false;
            Keyboard.down = false;
            //Keyboard.jump=false;

        }
        private void updatePos () {
            moving = false;
            if (Keyboard.jump)
                jump();
            if (!inAir) // verificam daca stam pe loc
            {
                if ((!Keyboard.left && !Keyboard.right) || (Keyboard.right && Keyboard.left)) {
                    return;
                }
            }
            float xSpeed = 0;
            if (Keyboard.left) {
                xSpeed = xSpeed - Herospeed;
            }
            if (Keyboard.right) {
                xSpeed = xSpeed + Herospeed;
            }
            if (!inAir) {   // daca nu este in aer  , trebuie sa verificam dupa fiecare miscare , daca nu  va pluti inn aer
                if (!IsItemTouchingGround(hitbox, game)) {
                    inAir = true;
                }
            }
            // verific daca este in aer
            if (inAir) {
                // deplasarea in aer - airSpeed
                if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, game)) { // verific daca ma pot deplasa in aer , daca nu am coliziune
                    hitbox.y = (int) (hitbox.y + airSpeed);  // mut eroul in sus sau in jos , daca AirSpeed are val pozitiva in sus , daca e negativa in jos
                    airSpeed = airSpeed + gravity; // simulare cadere, am nevoie de gravitatie pt ajunge la sol , daca e prea mare nu ma pot desprinde de la sol
                    updateXPos(xSpeed);           // la fiecare cadru se actualizeaza airSpeed cu adg gravitatiei, iar eroul cade din ce in ce mai repede pana loveste solul
                } else {
                    hitbox.y = (int) GetItemsPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                    // verific daca lovim pamantul , daca lovim pamantul , trebuie sa resetam starea (inAir de ex - sa fie false)
                    // cazul in care lovim pamantul
                    if (airSpeed > 0) // a atins solul si nu mai este in aer , deci pun pe false InAir
                    {
                        resetInAir();
                    } else // cazul in care lovim marginea superioara
                    {
                        airSpeed = fallSpeedAfterCollision;  // actualizez viteza in aer cu viteza de cadere dupa coliziunea cu marginea superioara
                    }
                    updateXPos(xSpeed);
                }
            } else {
                updateXPos(xSpeed);  // daca nu este in aer ma preocupa doar sa modific poz pe axa x
            }
            moving = true;
        }

    private void jump() {
        if(inAir)   // daca am sarit deja nu sar din nou (adica daca sunt in aer)
            return;
       inAir=true;
       airSpeed=jumpSpeed;   // ajustez viteza de deplasare in aer cu viteza sariturii

    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;

    }
    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x+ xSpeed, hitbox.y , hitbox.width,hitbox.height, game))
        {
            hitbox.x= (int) (hitbox.x+xSpeed);
        }
        else{                 // daca avem coliziune , exista spatiu intre perete si drept de colizune
                  hitbox.x= (int) GetItemsPosNextToWall(hitbox,xSpeed);                         // vreau ca dreptunghiul sa fie lipit de perete de exemplu
        }
    }
    public int  GetCurrentLife(){
        return this.currentLife;
    }

}


