package PaooGAME;

import PaooGAME.Items.Enemy1;
import PaooGAME.Items.Hero;
import PaooGAME.Items.Coin;

import java.awt.*;
import java.util.ArrayList;

import static PaooGAME.Items.Hero.xp;
// clasa in care imi creez obiectele ,update + desenat
public class ObjectManager {
    private static ObjectManager singleton;
    private ArrayList<Coin> coin=new ArrayList<>();
    public ObjectManager(Game game){
        coin=getCoin(game.currentMap);
    }

    public void update(Hero hero){
        updateList(hero);
    }
    public void updateList(Hero hero){               // pt for each (Coin c: coin)
                                                               // creez o lista auxiliara in care pun obiectele pe care vreau sa le sterg
                                                                 // nu pot modifica lista intr-un for-each
        for (int i=0;i< coin.size();++i) {                                //ConcurrentModificationException - apare cand modific lista în timp ce o parcurg.Rezolvare problemă -o iterație cu iterator
            if (hero.collisionWithCoins(coin.get(i)) )
            {                                                        // sau pot crea o lista de elemnte in care salevz elemntele care urmeaza sa fie sterse
                coin.remove(coin.get(i));
                xp=xp+10;
                break;
            }
        }

    }

    public void drawCoin(Graphics g, int xCameraOffset,Hero hero) {
        for (int i=0; i< coin.size();i++) {
            {
                coin.get(i).Draw(g, xCameraOffset);

            }

        }

    }

    public static ArrayList<Coin> getCoin(int map){

        ArrayList<Coin> list= new ArrayList<>();
        switch(map) {
            case 0:           // pt harta de la primul nivel
                list.add(new Coin(100, 48*7));
                list.add(new Coin(230, 48*7));
                list.add(new Coin(320, 48*7));
                list.add(new Coin(650, 48*7));
                list.add(new Coin(710, 48*4));
                list.add(new Coin(820, 48*4));
                list.add(new Coin(760, 48*7));
                list.add(new Coin(810, 48*7));
                list.add(new Coin(900, 48*7));
                list.add(new Coin(990, 300));
                list.add(new Coin(1040, 48*7));
                list.add(new Coin(1120, 48*7));
                list.add(new Coin(1120, 48*2));
                list.add(new Coin(1380, 48*7));
                list.add(new Coin(1450, 48*7));
                list.add(new Coin(1550, 48*7));
                list.add(new Coin(1650, 48*2));
                list.add(new Coin(1700, 48*7));
                list.add(new Coin(1870, 48*1));
                list.add(new Coin(1960, 48*7));
                list.add(new Coin(2020, 48*7));
                list.add(new Coin(2260, 48*5));
                list.add(new Coin(2260, 48*7));
                list.add(new Coin(2260, 48*7));
                list.add(new Coin(2490, 48 * 2));
                list.add(new Coin(2680, 48 * 2));
             //  list.add(new Coin(2690, 48*7 ));
                list.add(new Coin(2723, 48*7));
                list.add(new Coin(2750, 48 * 2));
                list.add(new Coin(3000, 48 * 7));
                list.add(new Coin(3000, 48 * 3));
                list.add(new Coin(3100, 48 * 7));
                list.add(new Coin(3200, 48 * 7));
                list.add(new Coin(3300, 48 * 4));
                list.add(new Coin(3410, 48*7));
                list.add(new Coin(3560, 48*7));
                list.add(new Coin(3280, 48*7));

                list.add(new Coin(3467, 48*7));

                break;
            case 1:       // pt harta de la al doilea nivel
                list.add(new Coin(15*48, 48*4));
                list.add(new Coin(12*48, 48));
                list.add(new Coin(8*48, 48));
                list.add(new Coin(7*48, 48));
                list.add(new Coin(6*48, 48));
                list.add(new Coin(200, 48));

                list.add(new Coin(110, 48*7));
                list.add(new Coin(170, 48*7));
                list.add(new Coin(270, 48*7));
                list.add(new Coin(350, 48*7));
                list.add(new Coin(500, 48*7));
                list.add(new Coin(400, 48*7));
                list.add(new Coin(460, 48*7));
                list.add(new Coin(670, 48*7));
                list.add(new Coin(700, 48*7));
                list.add(new Coin(1000, 48*7));
                list.add(new Coin(1500, 48*7));
                list.add(new Coin(3390, 48*7));
                list.add(new Coin(3460, 48*7));
                list.add(new Coin(3560, 48*7));
                list.add(new Coin(3180, 48*7));
                list.add(new Coin(20*48, 48*2));
                list.add(new Coin(35*48, 48*2));
                list.add(new Coin(26*48, 48*2));
                list.add(new Coin(28*48, 48*2));

                list.add(new Coin(35*48, 48*7));
                list.add(new Coin(33*48, 48*7));
                list.add(new Coin(31*48, 48*7));
                list.add(new Coin(29*48, 48*7));
                list.add(new Coin(27*48, 48*7));
                list.add(new Coin(25*48, 48*7));
                list.add(new Coin(24*48, 48*7));
                list.add(new Coin(23*48, 48*7));

                list.add(new Coin(35*48, 48*2));
                list.add(new Coin(41*48, 48*2));
                list.add(new Coin(45*48, 48*7));
                list.add(new Coin(46*48, 48*7));
                list.add(new Coin(47*48, 48*7));

                list.add(new Coin(51*48, 48*7));
                list.add(new Coin(52*48, 48*7));
                list.add(new Coin(53*48, 48*7));

                list.add(new Coin(47*48, 48*4));
                list.add(new Coin(48*48, 48*4));
                list.add(new Coin(49*48, 48*4));
                list.add(new Coin(55*48, 48*2));
                list.add(new Coin(56*48, 48*2));
                list.add(new Coin(61*48, 48*1));
                list.add(new Coin(3180, 48*7));



                break;
            case 2:
                list.add(new Coin(0, 150));
                list.add(new Coin(10, 350));
                list.add(new Coin(150, 350));
                list.add(new Coin(430, 350));
                list.add(new Coin(620, 350));
                list.add(new Coin(970, 350));
                list.add(new Coin(1500, 350));
                list.add(new Coin(1215, 350));
                list.add(new Coin(45, 350));
                list.add(new Coin(202, 350));
                list.add(new Coin(559, 350));
                list.add(new Coin(502, 350));


                list.add(new Coin(44*48, 48*7));
                list.add(new Coin(46*48, 48*7));
                list.add(new Coin(48*48, 48*7));
                list.add(new Coin(28*48, 48*3));

                list.add(new Coin(32*48, 48));
                list.add(new Coin(34*48, 48));


                list.add(new Coin(40*48, 48*3));
                list.add(new Coin(41*48, 48*3));

                list.add(new Coin(54*48, 48*7));
                list.add(new Coin(56*48, 48*7));
                list.add(new Coin(58*48, 48*7));


                list.add(new Coin(50*48, 48*3));
                list.add(new Coin(52*48, 48*3));
                list.add(new Coin(54*48, 48*3));
                list.add(new Coin(56*48, 48*3));
                list.add(new Coin(58*48, 48*3));
                list.add(new Coin(60*48, 48*3));
                list.add(new Coin(62*48, 48*3));
                list.add(new Coin(64*48, 48*3));
                list.add(new Coin(67*48, 48*3));
                list.add(new Coin(68*48, 48*3));
                list.add(new Coin(68*48, 48*3));
                list.add(new Coin(210, 48*2));


                break;
        }
        return list;
    }

}
