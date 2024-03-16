package PaooGAME.Items;
import PaooGAME.Game;
import PaooGAME.Graphics.ImageLoaderEnemy1;
import PaooGAME.Graphics.ImageLoaderEnemyBoss1;
import PaooGAME.Graphics.ImageLoaderEnemyBoss2;
import PaooGAME.Graphics.ImageLoaderEnemyBoss3;
import java.awt.*;
import java.util.ArrayList;
import static PaooGAME.Camera.xCameraOffset;


import static PaooGAME.Game.GameCompleted;
import static PaooGAME.Game.db;
import static PaooGAME.Items.Enemy1.IntersectWithAttackHitbox;
import static PaooGAME.Items.Hero.xp;

// clasa in care imi creez inamicii , le dau update + desenez
public class EnemyManager {
    private ArrayList<Enemy1> enemyes1=new ArrayList<>();
    private EnemyBoss1 boss1;
    private EnemyBoss2 boss2;
    private EnemyBoss3 boss3;

    public EnemyManager(Game game){
      //  enemyes1=getEnemyes1(game.currentMap);

                boss1 = new EnemyBoss1(3600, 336);
                enemyes1=getEnemyes1(game.currentMap);
                boss2 = new EnemyBoss2(48*67, 340);
                boss3 = new EnemyBoss3(48*67, 0);



    }

  public void update(Game game,Hero hero) {
      try{

          if(game.currentMap==0) {

             // if (boss1 != null){
                  if (boss1.isInLife()) {
                      //System.out.println("Viata inamic 1:  " + boss1.currentLife);
                      boss1.update(game, hero);
                      for(int i=0;i<enemyes1.size();++i){    // dau un id pt fiecare mapa , daca id este diferit se sterge
                          if(enemyes1.get(i).isInLife()) {
                              enemyes1.get(i).update(game, hero);
                          }else {enemyes1.remove(enemyes1.get(i));}
                      }
                  } else {

                      enemyes1.clear();
                      game.currentMap = 1;
                      db.BazaDate(game,hero);
                     // DBCreator db=new DBCreator();
                      game.map1.LoadWorld(game);
                      hero.Spawn();
                      enemyes1=getEnemyes1(game.currentMap);

                  }

          }
          if(game.currentMap==1) {
              game.currentLevel=game.currentMap+1;

              //   if (boss1 != null){
                  if( boss2.isInLife()) {
                  // boss1 = null;
                  boss2.update(game, hero);
                      for(int i=0;i<enemyes1.size();++i){    // dau un id pt fiecare mapa , daca id este diferit se sterge
                          if(enemyes1.get(i).isInLife()) {
                              enemyes1.get(i).update(game, hero);
                          }else {enemyes1.remove(enemyes1.get(i));}
                      }
              }else{
                      enemyes1.clear();
                      game.currentMap=2;
                      db.BazaDate(game,hero);
                      game.map1.LoadWorld(game);
                      hero.Spawn();
                      enemyes1=getEnemyes1(game.currentMap);
                  }
          }

          if(game.currentMap==2) {
              game.currentLevel=game.currentMap+1;
              if( boss3.isInLife()) {
                  // boss2 = null;
                  boss3.update(game, hero);
                  for(int i=0;i<enemyes1.size();++i){    // dau un id pt fiecare mapa , daca id este diferit se sterge
                      if(enemyes1.get(i).isInLife()) {
                          enemyes1.get(i).update(game, hero);
                      }else {enemyes1.remove(enemyes1.get(i));}
                  }
              }else{
                  for(int i=0;i<enemyes1.size();++i)
                  {
                      enemyes1.remove(enemyes1.get(i));
                  }
                  GameCompleted=true;
                  db.BazaDate(game,hero);

              }

          }
      }catch (NullPointerException e)
      {System.out.println("obiect null");}
  }




    public void draw(Graphics g,int xCameraOffset,Game game){
        drawEnemy1(g,xCameraOffset,game);
    }
    public void drawEnemy1(Graphics g,int xCameraOffset,Game game) {


        if(game.currentMap==0) {
            if (boss1 != null) {
                if (boss1.isInLife()) {
                    g.drawImage(ImageLoaderEnemyBoss1.AnimationEnemyBoss1[boss1.getEnemyAction()][boss1.aniIndex], (int) (boss1.getHitbox().x - boss1.getxDrawOffset() - xCameraOffset), (int) (boss1.getHitbox().y - boss1.getyDrawOffset()), 148, 110, null);
                 //   boss1.drawHitbox(g, xCameraOffset);
                 //   boss1.drawAttackHitboxEnemyBoss1(g, xCameraOffset);
                    for (int i=0;i<enemyes1.size();++i) {
                        if (enemyes1.get(i)!=null && enemyes1.get(i).isInLife()) {
                            g.drawImage(ImageLoaderEnemy1.AnimationEnemy[enemyes1.get(i).getEnemyAction()][enemyes1.get(i).aniIndex], (int) (enemyes1.get(i).getHitbox().x - enemyes1.get(i).getxDrawOffset() - xCameraOffset), (int) (enemyes1.get(i).getHitbox().y - enemyes1.get(i).getyDrawOffset()), 110, 105, null);  // cum ma uit pe imagine
                            // enemyes1.get(i).drawHitbox(g, xCameraOffset);
                          //  enemyes1.get(i).drawAttackHitboxEnemy1(g, xCameraOffset);
                        }

                    }
                }
            }
        }
        if(game.currentMap==1) {
            if (boss2 != null) {
                if (boss2.isInLife()) {
                    g.drawImage(ImageLoaderEnemyBoss2.AnimationEnemyBoss2[boss2.getEnemyAction()][boss2.aniIndex], (int) (boss2.getHitbox().x - boss2.getxDrawOffset() - xCameraOffset), (int) (boss2.getHitbox().y - boss2.getyDrawOffset()), 140, 100, null);
                  //  boss2.drawHitbox(g, xCameraOffset);
                  //  boss2.drawAttackHitboxEnemyBoss2(g, xCameraOffset);
                    for (int i=0;i<enemyes1.size();++i) {
                        if (enemyes1.get(i)!=null && enemyes1.get(i).isInLife()) {
                            g.drawImage(ImageLoaderEnemy1.AnimationEnemy[enemyes1.get(i).getEnemyAction()][enemyes1.get(i).aniIndex], (int) (enemyes1.get(i).getHitbox().x - enemyes1.get(i).getxDrawOffset() - xCameraOffset), (int) (enemyes1.get(i).getHitbox().y - enemyes1.get(i).getyDrawOffset()), 110, 105, null);  // cum ma uit pe imagine
                            // enemyes1.get(i).drawHitbox(g, xCameraOffset);
                            // enemyes1.get(i).drawAttackHitboxEnemy1(g, xCameraOffset);
                        }

                    }

                }
            }
        }

        if(game.currentMap==2) {
            if (boss3 != null) {
                if (boss3.isInLife()) {
                    g.drawImage(ImageLoaderEnemyBoss3.AnimationEnemyBoss3[boss3.getEnemyAction()][boss3.aniIndex], (int) (boss3.getHitbox().x - boss3.getxDrawOffset() - xCameraOffset), (int) (boss3.getHitbox().y - boss3.getyDrawOffset()), 96, 68, null);
                   // boss3.drawHitbox(g, xCameraOffset);
                //    boss3.drawAttackHitboxEnemyBoss3(g, xCameraOffset);
                    for (int i=0;i<enemyes1.size();++i) {
                        if (enemyes1.get(i)!=null && enemyes1.get(i).isInLife()) {
                            g.drawImage(ImageLoaderEnemy1.AnimationEnemy[enemyes1.get(i).getEnemyAction()][enemyes1.get(i).aniIndex], (int) (enemyes1.get(i).getHitbox().x - enemyes1.get(i).getxDrawOffset() - xCameraOffset), (int) (enemyes1.get(i).getHitbox().y - enemyes1.get(i).getyDrawOffset()), 110, 105, null);  // cum ma uit pe imagine
                            // enemyes1.get(i).drawHitbox(g, xCameraOffset);
                        //    enemyes1.get(i).drawAttackHitboxEnemy1(g, xCameraOffset);
                        }

                    }

                }
            }
        }

    }
    public static ArrayList<Enemy1> getEnemyes1(int map) {
        ArrayList<Enemy1> list = new ArrayList<>();

        switch (map) {
            case 0: // Harta 1

                list.add(new Enemy1(1500, 48 * 2));
                list.add(new Enemy1(720, 48 * 4));
                list.add(new Enemy1(810, 48 * 4));
                list.add(new Enemy1(2500, 48 * 2));
                list.add(new Enemy1(680, 48 * 7));
                list.add(new Enemy1(720, 48 * 7));
                list.add(new Enemy1(810, 48 * 7));
                list.add(new Enemy1(1700, 48 * 2));
                list.add(new Enemy1(2040, 48 * 7));
                list.add(new Enemy1(2490, 48 * 7));
                list.add(new Enemy1(2750, 48 * 2));
                list.add(new Enemy1(3000, 48 * 3));

                list.add(new Enemy1(2680, 48 * 2));
                list.add(new Enemy1(3380, 48 * 7));
                list.add(new Enemy1(3300, 48 * 7));

                list.add(new Enemy1(3504, 48 * 4));

                break;

            case 1: // Harta 2


                list.add(new Enemy1(100, 48 * 2));
                list.add(new Enemy1(204, 48 * 5));
                list.add(new Enemy1(504, 48 * 4));
                //list.add(new Enemy1(3000, 48 * 2));
                list.add(new Enemy1(20*48, 48*2));
                list.add(new Enemy1(35*48, 48*2));
                list.add(new Enemy1(15*48, 48*2));
                list.add(new Enemy1(13*48, 48*2));
                list.add(new Enemy1(7*48, 48));
                list.add(new Enemy1(6*48, 48));
                list.add(new Enemy1(200, 48));

                list.add(new Enemy1(26*48, 48*2));
                list.add(new Enemy1(3204, 48 * 2));
                list.add(new Enemy1(54*48, 0));
                list.add(new Enemy1(38*48, 48*2));
                list.add(new Enemy1(37*48, 48*2));
                list.add(new Enemy1(51*48, 0));
                list.add(new Enemy1(52*48, 0));
                list.add(new Enemy1(53*48, 0));
                list.add(new Enemy1(49*48, 0));

                list.add(new Enemy1(50*48, 0));
                list.add(new Enemy1(50*48, 0));
                list.add(new Enemy1(35*48, 48*5));
                list.add(new Enemy1(35*48, 48*5));
                list.add(new Enemy1(35*48, 48*5));
                list.add(new Enemy1(35*48, 48*5));
                list.add(new Enemy1(35*48, 48*5));
                //list.add(new Enemy1(56*48, 0));
                //list.add(new Enemy1(57*48, 0));
                //list.add(new Enemy1(58*48, 0));
                list.add(new Enemy1(59*48, 0));
                list.add(new Enemy1(60*48, 0));
                list.add(new Enemy1(67*48, 0));
                list.add(new Enemy1(57*48, 0));
                list.add(new Enemy1(68*48, 0));
                list.add(new Enemy1(69*48, 0));
               // list.add(new Enemy1(70*48, 0));
               // list.add(new Enemy1(71*48, 0));


                break;

            case 2: // Harta 3
                list.add(new Enemy1(0, 0));
                list.add(new Enemy1(220, 0));
                list.add(new Enemy1(170, 0));
                list.add(new Enemy1(1500, 48 * 3));
                list.add(new Enemy1(120, 48 * 4));
                list.add(new Enemy1(3204, 48 * 3));
                list.add(new Enemy1(3550, 48 * 1));
                list.add(new Enemy1(3204, 48 * 3));
                list.add(new Enemy1(32*48, 48));
                list.add(new Enemy1(34*48, 48));
                list.add(new Enemy1(28*48, 48*3));


                list.add(new Enemy1(40*48, 48*3));
                list.add(new Enemy1(41*48, 48*3));


                list.add(new Enemy1(50*48, 48 * 2));
                list.add(new Enemy1(57*48, 48 * 3));
                list.add(new Enemy1(56*48, 48 * 2));

        }

        return list;
    }




    public void checkIsEnemyHit(Rectangle attackHitBox,Game game){  // verific daca hitboxul atacului eroului se intesecteaza cu cel al inamicului

        for(Enemy1 e: enemyes1){                           // daca se intersecteaza
            if(e.isInLife()){
                if(e.getCurrentLife()>0){

                // if(IntersectWithAttackHitbox(e.getHitbox(),attackHitBox))
               // if(attackHitBox.intersects(e.getHitbox())) // daca e true am lovit un inamic
                if(IntersectWithAttackHitbox(attackHitBox,e.getHitbox()))
                {
                    e.hurt(15);
                    return;
                }
                }
            }
        }
            if(game.currentMap==0) {
                if (boss1!=null && boss1.isInLife()) {
                    if(boss1.getCurrentLife()>0){
                    if (IntersectWithAttackHitbox(attackHitBox, boss1.getHitbox())) {
                        boss1.hurt(15);
                        return;
                    }
                    }
                }
            }
            else if(game.currentMap==1) {
                if (boss2!=null && boss2.isInLife()) {
                    if(boss2.getCurrentLife()>0){
                        if (IntersectWithAttackHitbox(attackHitBox, boss2.getHitbox())) {
                            boss2.hurt(15);
                            return;
                        }
                    }
                }
            }
            else if(game.currentMap==2){
            if(boss3!=null && boss3.isInLife()){
                if(boss3.getCurrentLife()>0){
                    if(IntersectWithAttackHitbox(attackHitBox,boss3.getHitbox())) {
                        boss3.hurt(15);
                        return;}
                    }
                }
            }
        }

}


