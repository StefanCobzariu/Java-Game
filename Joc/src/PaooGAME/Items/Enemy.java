package PaooGAME.Items;

import PaooGAME.Game;
import PaooGAME.Graphics.*;

import static  PaooGAME.Coliziune.CanMoveHere;

import java.awt.*;

import static PaooGAME.Directions.*;
import static PaooGAME.Graphics.LoadAnimEnemy.*;
import  static PaooGAME.Items.Hero.xp;

public abstract class Enemy extends Items {
    protected boolean inLife=true;
    public int aniTick, aniIndex = 3;
    private int idEnemy;
    public Game game;
    protected boolean inAir = false;
    protected final int aniSpeed = 24; // viteza animatie
    private final int linii1 = 7, coloane1 = 6; // linii , coloane pt imaginea cu animatiile cand pun ptr stanga sa inmultesc cu 2 liniile
    protected int enemyAction = RUNNING_ENEMY;

    private String s1 = "/Enemy1.png";
    private String s2 = "/FinalBoss1.png";
    private String s3 = "/FinalBoss2.png";
    private String s4 = "/FinalBoss3.png";

    public static final int ENEMY_WIDTH = 48;
    public static final int ENEMY_HEIGHT = 48;


    protected boolean firstUpdate = true;
    protected float gravity = 0.04009f * Game.SCALE;
    protected float fallSpeed;
    protected float walkSpeed = 1f * Game.SCALE;  // viteza cu care se deplaseaza inamicul
    public int direction = RIGHT;
    protected int tileY;
    protected float attackRangeEnemy1 = 30;// distanta de atac de la inamic spre erou- de un tile
    protected float attackRangeEnemyBoss1 = 60;// distanta de atac de la inamic spre erou- de un tile
    protected float attackRangeEnemyBoss2 = 70;// distanta de atac de la inamic spre erou- de un tile
    protected float attackRangeEnemyBoss3 = 50;// distanta de atac de la inamic spre erou- de un tile

    protected float Range = attackRangeEnemyBoss2 * 4;
    protected int maxLife;

    public int getCurrentLife() {
        return currentLife;
    }

    protected int currentLife;
    protected boolean attackChecked;
    //functie pt detectare intersectie coliziune

    public Enemy(float x, float y, int width, int height, int idEnemy) {
        super(x, y, width, height);
        this.idEnemy = idEnemy;
        ImageLoaderEnemy1.InitEnemy1(linii1, coloane1, s1);
        ImageLoaderEnemyBoss1.InitBossEnemy1(8,6,s2);
        ImageLoaderEnemyBoss2.InitBossEnemy2(8,6,s3);
        ImageLoaderEnemyBoss3.InitBossEnemy3(8,6,s4);

        //initHitbox(x,y,16* Game.SCALE,35*Game.SCALE);
      //  ImageLoaderEnemyBoss1.InitEnemy(8,6,"/FinalBoss1.png",AnimationEnemyBoss1);
    }
    static boolean IntersectWithAttackHitbox(Rectangle hitbox1,Rectangle hitbox2){
        //verific daca cele 2 se int pe axa X   // Verific dacă dreptunghiurile se intersectează pe axa Y
        if((hitbox1.x + hitbox1.width > hitbox2.x && hitbox1.x < hitbox2.x + hitbox2.width) && (hitbox1.y + hitbox1.height > hitbox2.y && hitbox1.y < hitbox2.y + hitbox2.height))
        {
            return  true;
        }

        return false;
    }
    protected void checkHeroIsHit(Rectangle attackHitboxEnemy, Hero hero) {
        if(IntersectWithAttackHitbox(attackHitboxEnemy,hero.getHitbox())){  // daca hitboxul atacului intersecteaza hitboxul personajului - scad viata eroului
            hero.changeLife(getEnemyDamage(idEnemy)); // tipul de inamic
            attackChecked=true;
        }
    }

    public void InitG(Game game) {
        this.game = game;
    }

    public static int getMaxLife(int enemyId) {
        if (enemyId == ID_ENEMY_1) {
            return 10;
        } else if (enemyId == ID_ENEMYBOSS_1) {
            return 100;
        } else if (enemyId == ID_ENEMYBOSS_2) {
            return 65;
        }else if (enemyId == ID_ENEMYBOSS_3) {
            return 75;
        }
        return 1;
    }
    //cat scad din viata eroului
    public static int getEnemyDamage(int enemyId) {
        if (enemyId == ID_ENEMY_1) {
            return 10;
        } else if (enemyId == ID_ENEMYBOSS_1) {
            return 22;
        } else if (enemyId == ID_ENEMYBOSS_2) {
            return 25;
        }else if (enemyId == ID_ENEMYBOSS_3) {
            return 30;
        }
        return 1;
    }



    protected void updateAnimationTickEnemy() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
        }
        if (aniIndex >= LoadAnimEnemy.GetSpriteAmountEnemy(enemyAction, idEnemy)) { // decat nr de animatii ale unei actiuni
            aniIndex = 0;

            if (enemyAction == ATTACK_ENEMY || enemyAction == LEFT_ATTACK_ENEMY)
                if(direction==RIGHT)
                enemyAction = RUNNING_ENEMY;
            else
                enemyAction=LEFT_RUNNING_ENEMY;
            else if(enemyAction == DEAD_ENEMY)
            {
                inLife=false;
            }
        }
    }

    // public void render(Graphics g, int xCameraOffset){
    //  g.drawImage(ImageLoaderEnemy.AnimationEnemy[enemyAction][aniIndex], (int)x-xCameraOffset, (int)y, width, height, null);  // cum ma uit pe imagine
    //}
    protected int getEnemyAction() {
        return this.enemyAction;
    }
    protected void changeDirectionToWalk() {

        if (direction == LEFT) {
            direction = RIGHT;

        } else if (direction == RIGHT) {
            direction = LEFT;
        }
    }
    // prin metoda asta inamicii cad la inceputul (daca sunt in aer)jocului
    protected void firstUpdateCheck(Game game) {
        if (!IsItemTouchingGround(this.hitbox, game)) { // nu atinge pamantul => in aer
            inAir = true;
            firstUpdate = false;
        }
    }

    protected void updateInAir(Game game) {
        if (CanMoveHere(hitbox.x, hitbox.y, hitbox.width, hitbox.height, game)) {
            hitbox.y = (int) (hitbox.y + fallSpeed);
            fallSpeed = fallSpeed + gravity;
        } else {  // daca atinge pamantul
            inAir = false;
            hitbox.y = (int) GetItemsPosUnderRoofOrAboveFloor(this.hitbox, fallSpeed);
            tileY = hitbox.y / Game.TILES_SIZE;
        }
    }

    protected void moveX(Game game) {
        float xSpeed = 0;
        if (direction == LEFT) {
            xSpeed = -walkSpeed;  // daca merge la stanga - viteza de miscare scad pe axa x
        } else {
            xSpeed = walkSpeed;  //// daca merge la dreapta - viteza de miscare scad pe axa x
        }
        // verific daca pozitia viitoare a dreptunghiului de coliziune se poate muta
        if (CanMoveHere(this.hitbox.x + xSpeed, this.hitbox.y, this.hitbox.width, this.hitbox.height, game) && IsFloor(this.hitbox, xSpeed, game)) {  // verific daca inamicul poat sa mearga la stanga sau la dreapta

            this.hitbox.x += xSpeed;// verific marginilie , daca am groapa sa nu ma duc in ea
            return;  // daca ambele false - return
        }
        // pt margini

        if (direction == LEFT) {
            changeAnimAction(RUNNING_ENEMY);
        } else if (direction == RIGHT) {
            changeAnimAction(LEFT_RUNNING_ENEMY);
        }
        changeDirectionToWalk();

    }

    protected void changeAnimAction(int enemyAction) {
        this.enemyAction = enemyAction;
        aniTick = 0;
        aniIndex = 0; // resetam animatia
    }

    // metoda verifica daca juctaorul este vizibil pt inamic
    protected boolean IsVisibleHero(Game game, Hero hero) {
        int heroTileY = hero.getHitbox().y / Game.TILES_SIZE;
        int difH = tileY - heroTileY; // diferenta intre inatimile celor doua
        // verific asta ca sa stiu daca sunt la aceeasi inaltime   // daca este urcat pe un tile nu este vizibil pentru inamic// verific daca eroul este in zona patrulata de inamic                                                                                //daca nu sunt obiecte
        if ((heroTileY + difH == tileY) && (IsPlayerInArea(hero)) && (IsClearArea(game, this.hitbox, hero.hitbox, tileY)))       //daca nu sunt obiecte
        {
            return true;
        }
        return false;
    }

    protected void moveDirEnemyToPlayer(Hero hero) {
        /*if(direction==RIGHT) {
            if (hero.getHitbox().x > this.hitbox.x) {
                this.direction = RIGHT;
            } else if (hero.getHitbox().x < this.hitbox.x) {
                this.direction = LEFT;
                changeAnimAction(LEFT_RUNNING_ENEMY1);
            }
        }
            else if (direction == LEFT) {
                if (hero.getHitbox().x > this.hitbox.x) {
                    this.direction = RIGHT;
                    changeAnimAction(RUNNING_ENEMY1);
                } else if (hero.getHitbox().x < this.hitbox.x) {
                    this.direction = LEFT;
                }
            }*/
        if (hero.getHitbox().x > this.hitbox.x) {
            this.direction = RIGHT;
        } else if (hero.getHitbox().x < this.hitbox.x) {
            this.direction = LEFT;

        }
    }

    /*protected boolean closeEnoughToAttack(Hero hero,float attackRange) {
        int modulDistance = Math.abs(hero.getHitbox().x - (this.getHitbox().x));
        int minDistance=0;   // imi iau o distanta minima de respectat pentru a putea face un atac  - nu vreau ca inamicul sa atace daca este suprapus peste personaj
        switch(idEnemy){
            case 1 :
                minDistance=15;
            case 2:
                minDistance=25;
            case 3:
                minDistance=60;
            case 4:
                minDistance=70;

        }
        if (modulDistance <= attackRange) {
            if (direction == RIGHT && hero.getHitbox().x > this.getHitbox().x) {      // daca inamicul este in partea stanga a eroului cu directia inainte (spre dreapta)
                // Inamicul se află în spatele eroului și orientat spre dreapta
                if (modulDistance < minDistance) {
                    // Inamicul se suprapune cu eroul sau cu distanta, mergi câțiva pași înapoi
                    changeDirectionToWalk();
                    changeAnimAction(LEFT_RUNNING_ENEMY);
                    return  false;
                }
                return true;
            } else if (direction == LEFT && hero.getHitbox().x < this.getHitbox().x) {
                // Inamicul se află în spatele eroului și orientat spre stânga
                if (modulDistance <= minDistance) {
                    // Inamicul se suprapune cu eroul, mergi câțiva pași în față
                    changeDirectionToWalk();
                    changeAnimAction(RUNNING_ENEMY);
                    return  false;
                }
                return true;
            }
        }

        return false;

    }
*/
    protected boolean closeEnoughToAttack(Hero hero,float attackRange) {
        int modulDistance = Math.abs(hero.getHitbox().x - this.getHitbox().x);
        if (modulDistance <= attackRange) {
            return true;
        } else return false;
    }
    protected boolean IsPlayerInArea(Hero hero) {

        int modulDistance = Math.abs(hero.getHitbox().x - this.getHitbox().x);
        if (modulDistance <= Range) {
            return true;
        } else return false;
    }
    public static boolean IsAllTilesWakable(int xStart, int xEnd,int y,Game game)
    {
        for (int i = 0; i < xEnd - xStart; i++) {  // verifc tileurile dintre cele 2 entitati
            if (isTileSolid(xStart + i, y, game))  // ma duc de la pozitia x a primului obiecct spre cel de-al doilea s
                return false;// si verific daca este un tile( obstacol) pe dist dinter cele 2
            if (!isTileSolid(xStart + 1, y + 1, game)) // verific daca dedesub este tile
                 return false;
        }
    return  true;
    }

    public static boolean IsClearArea(Game game, Rectangle hitbox1, Rectangle hitbox2, int tileY) {
        int XTile1 =(int) (hitbox1.x / Game.TILES_SIZE);
        int XTile2 = (int)(hitbox2.x / Game.TILES_SIZE);
        if (XTile1 > XTile2) {
            return IsAllTilesWakable(XTile2,XTile1,tileY,game);
        }else{
            return IsAllTilesWakable(XTile1,XTile2,tileY,game);
        }

    }

    public int getDirection() {
        return direction;
    }

    public void hurt(int amount) {
        currentLife = currentLife - amount;
        int id=this.idEnemy;
        if (currentLife <= 0) {
            currentLife=0;
            switch (id){
                case 1: xp+=15;
                    break;
                case 2:xp+=30;
                    break;
                case 3:xp+=40;
                    break;
                case 4:xp+=50;
                    break;

            }
            changeAnimAction(DEAD_ENEMY);
        }

    }

    boolean isInLife(){

        return inLife;
    }
}



