package PaooGAME.Items;

import PaooGAME.Game;
import PaooGAME.Graphics.LoadAnimEnemy;

import java.awt.*;

import static PaooGAME.Directions.LEFT;
import static PaooGAME.Directions.RIGHT;
import static PaooGAME.Graphics.LoadAnimEnemy.*;

public class EnemyBoss3 extends Enemy {
    private final float xDrawOffset = 25 * Game.SCALE; // de unde ar trebui sa inceapa drept de coliziune
    private final float yDrawOffset = 18 * Game.SCALE;  // diferenta de unde incepe sprite-ul si de unde incepe exact desenul

    private Rectangle attackHitbox;
    private int attackOffset = 5;
    EnemyBoss3(float x, float y){
        super(x, y, ENEMY_WIDTH, ENEMY_HEIGHT, ID_ENEMYBOSS_3);
        initHitbox(x, y, 32, 44);
        initAttackHitbox();
        maxLife = getMaxLife(ID_ENEMYBOSS_3);
        currentLife = maxLife;
    }
    private void initAttackHitbox() {
        attackHitbox = new Rectangle((int) x, (int) y, 32, 20);
    }
    public void update(Game game, Hero hero) {
        updateMoveEnemy(game, hero);
        updateAnimationTickEnemy();
        updateAttackHitbox();
       // System.out.println("lifeBoss3"+currentLife);

    }

    private void updateAttackHitbox() {
        if (this.getDirection() == LEFT) {
            attackHitbox.x = this.hitbox.x - this.hitbox.width + 8;
        } else if (this.getDirection() == RIGHT) {
            attackHitbox.x = this.hitbox.x + this.hitbox.width - 2;
        }
        attackHitbox.y = this.hitbox.y + 10;
    }

    private void updateMoveEnemy(Game game, Hero hero) {   // updateaza pozitia inamicului
        // System.out.println("Apel functie update");
        if (firstUpdate) {
            firstUpdateCheck(game); // nu atinge pamantul => in aer
        }
        if (inAir) {   // verific daca este in aer
            updateInAir(game);
        } // cade pe pamant ,
        else {     //patrulare                              daca este pe pamant va merge stanga - dreapta
            if (enemyAction == IDLE_ENEMY) {

                if (direction == RIGHT)
                    changeAnimAction(LoadAnimEnemy.RUNNING_ENEMY);
                else if (direction == LEFT)
                    changeAnimAction(LEFT_RUNNING_ENEMY);
                // daca sta pe loc idle il fac sa se mistechangeAnimAction(LoadAnimEnemy.RUNNING_ENEMY1);
            } else if (enemyAction == LoadAnimEnemy.RUNNING_ENEMY || enemyAction == LoadAnimEnemy.LEFT_RUNNING_ENEMY) {
                if (IsVisibleHero(game, hero)) {
                    moveDirEnemyToPlayer(hero);
                    if (direction == RIGHT) {
                        changeAnimAction(RUNNING_ENEMY);
                    } else if (direction == LEFT) {
                        changeAnimAction(LEFT_RUNNING_ENEMY);
                    }
                    if (Math.abs(hero.getHitbox().x - this.getHitbox().x) >= 30) {

                        if (closeEnoughToAttack(hero, attackRangeEnemyBoss3)) {
                            if (direction == RIGHT)
                                changeAnimAction(ATTACK_ENEMY);
                            else if (direction == LEFT)
                                changeAnimAction(LEFT_ATTACK_ENEMY);
                        }
                    } else {
                        changeDirectionToWalk();
                        if (direction == RIGHT) {
                            changeAnimAction(RUNNING_ENEMY);
                        } else if (direction == LEFT) {
                            changeAnimAction(LEFT_RUNNING_ENEMY);
                        }

                    }
                }
                moveX(game);
            } else if (enemyAction == ATTACK_ENEMY || enemyAction == LEFT_ATTACK_ENEMY) {
                if (aniIndex == 0) {
                    attackChecked = false;
                }
                if (aniIndex == 3 && attackChecked == false)   // verific daca ranesc eroul cand ajung cu index anim la 3
                {
                    checkHeroIsHit(this.attackHitbox, hero); // verifica daca eroul este atins de drept de coliziune al atacului inamicului si ii scade viata
                }

            }
        }
    }


    public void drawAttackHitboxEnemyBoss3(Graphics g, int xCameraOffset) {
        g.setColor(Color.green);
        g.drawRect(attackHitbox.x - xCameraOffset, attackHitbox.y, attackHitbox.width, attackHitbox.height);
    }


    public float getxDrawOffset() {
        return xDrawOffset;
    }

    public float getyDrawOffset() {
        return yDrawOffset;
    }
}
