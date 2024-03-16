package PaooGAME.Items;

import PaooGAME.Game;
import PaooGAME.Graphics.LoadAnimEnemy;

import java.awt.*;

import static PaooGAME.Directions.LEFT;
import static PaooGAME.Directions.RIGHT;
import static PaooGAME.Graphics.LoadAnimEnemy.*;

public class Enemy1 extends Enemy {
    private final float xDrawOffset = 40 * Game.SCALE; // de unde ar trebui sa inceapa drept de coliziune
    private final float yDrawOffset = 34 * Game.SCALE;  // diferenta de unde incepe sprite-ul si de unde incepe exact desenul

    private Rectangle attackHitbox;
    private int attackOffset = 5;

    Enemy1(float x, float y) {  // x,y diferite pt fiecare inamic
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ID_ENEMY_1);
        initHitbox(x, y, 26, 33);
        initAttackHitbox();
        maxLife = getMaxLife(ID_ENEMY_1);  // stabilesc viata maxima pt fiecare inamic
        currentLife = maxLife;     // la inceput viata inamicului va fi maxima
    }

    private void initAttackHitbox() {
        attackHitbox = new Rectangle((int) x, (int) y, 25, 18);
    }

    public void update(Game game, Hero hero) {
        updateMoveEnemy(game, hero);
        updateAnimationTickEnemy();
        updateAttackHitbox();
    }

    private void updateAttackHitbox() {
        if (this.getDirection() == LEFT) {
            attackHitbox.x = this.hitbox.x - this.hitbox.width + 12;
        } else if (this.getDirection() == RIGHT) {
            attackHitbox.x = this.hitbox.x + this.hitbox.width - 12;
        }
        attackHitbox.y = this.hitbox.y + 10;
    }

    private void updateMoveEnemy(Game game, Hero hero) {   // updateaza pozitia inamicului
        if (firstUpdate) {
            firstUpdateCheck(game); // nu atinge pamantul => in aer
        }
        if (inAir) {   // verific daca este in aer
            updateInAir(game);
        } // cade pe pamant ,
        else {     //patrulare                              daca este pe pamant va merge stanga - dreapta
            if (enemyAction == IDLE_ENEMY) {
                if(direction==RIGHT)
                    changeAnimAction(LoadAnimEnemy.RUNNING_ENEMY);
                else if(direction==LEFT)
                    changeAnimAction(LEFT_RUNNING_ENEMY);

                // daca sta pe loc idle il fac sa se mistechangeAnimAction(LoadAnimEnemy.RUNNING_ENEMY1);
            } else if (enemyAction == LoadAnimEnemy.RUNNING_ENEMY ||enemyAction == LoadAnimEnemy.LEFT_RUNNING_ENEMY) {
                if (IsVisibleHero(game, hero))
                {
                    moveDirEnemyToPlayer(hero);
                    if (direction == RIGHT ) {
                        changeAnimAction(RUNNING_ENEMY);
                    } else if (direction == LEFT ) {
                        changeAnimAction(LEFT_RUNNING_ENEMY);
                    }

                    if (closeEnoughToAttack(hero,attackRangeEnemy1)) {
                        if (direction == RIGHT)
                            changeAnimAction(ATTACK_ENEMY);
                        else if (direction == LEFT)
                            changeAnimAction(LEFT_ATTACK_ENEMY);
                    }
                }
                moveX(game);
            }
            else if (enemyAction == ATTACK_ENEMY || enemyAction == LEFT_ATTACK_ENEMY) {
                if (aniIndex == 0) {
                    attackChecked = false;
                }
                if (aniIndex == 2 && attackChecked == false)   // verific daca ranesc eroul cand ajung cu index anim la 2
                {
                    checkHeroIsHit(this.attackHitbox, hero); // verifica daca eroul este atins de drept de coliziune al atacului inamicului si ii scade viata
                }
            }
        }
    }


    public void drawAttackHitboxEnemy1(Graphics g, int xCameraOffset) {
        g.setColor(Color.blue);
        g.drawRect(attackHitbox.x - xCameraOffset, attackHitbox.y, attackHitbox.width, attackHitbox.height);
    }


    public float getxDrawOffset() {
        return xDrawOffset;
    }

    public float getyDrawOffset() {
        return yDrawOffset;
    }
}



// prima data verigfic daca inamicul se afla prin preajma - de facut verificare
// daca se afla in zona prestabilita , inamicul se apropie (asta daca nu sunt obstacole sau margini , trebuie sa fie vizibil)
//verific daca jucatorul se afla in zona de atac , daca este in zona de atac - atac (trebuie sa verific si daca s-a teriminat)
// daca nu ma intorc sa verific din nou daca eroul este in preajma - la atac trebuie sa schimb si animatia
// mereu execut un atac , se incarca toate imaginile , la final se reiau toate verificarile si daca
// daca este cazul reiau atacul


