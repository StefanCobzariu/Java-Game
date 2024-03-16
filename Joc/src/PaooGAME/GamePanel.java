package PaooGAME;
import javax.swing.*;
import java.awt.*;

import PaooGAME.Inputs.*;

import static PaooGAME.Game.*;

public class GamePanel extends JPanel {  //teoretic pt locul unde se deseneaza - panza unui tablou , Gwindow- rama
    //private Mouse MouseInputs;
 //   private int frames;
   // private long lastCheck=0;

    private Game game;
    public GamePanel(Game game) {


        setPanelSize();
       // importImg();
        //MouseInputs=new Mouse(this);
        this.game=game;
// problema cu pornirea cred ca aici
        addKeyListener(new Keyboard(this));  // asc evenimentul generat de Keyboard
       // addMouseListener(MouseInputs);
       // addMouseMotionListener(MouseInputs);


    }
    /*
    public void updateGame(){
        // ceea ce tine de logica jocului
        updateAnimationTick();
        setAnimation();
        updatePos();


    }
    a
     */
    public void paintComponent (Graphics g) {
        super.paintComponent(g);// apel pt metoda din superclasa  //coordonate unde se deseneaza// cat de mare sa il desene
        // coordonate unde sa fie desenat pe harta
        //pe imagine va fiy  x
        //g.drawImage( Assets.Animation[playerAction][aniIndex], (int)xC, (int)yC,256,256, null);  // cum ma uit pe imagine
        game.render(g);
    }
    public Game getGame(){
        return game;
    }

    public void setPanelSize(){
        Dimension size=new Dimension(GAME_WIDTH,GAME_HEIGHT); // incapsuleaza latimea + lg intr-un sinf obiect
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
        System.out.println("size:"+GAME_WIDTH+" :" +GAME_HEIGHT );

    }





    // lastCheck - timpul de data trecuta

    // ex Runnable implementata de clasa Thread
    // INterfata Runnable trebuie implementata de orice clasa
    // ,a carei instante sunt insatntiate pt a fi executate de un thread.
    // Clasa trebuie sa defineasca o metoda fara argumente numita run.

    //Game - loop - doua componente - Thread and Runnable - probleme cu repaintloop pt ca nu este intr0un thread separat
    // toate se intampla intr-un thread(fir de executie -inputs, randare ,update)
    // dar noi vrem ca game loop sa fie intr-un thread separat, astfel nu va interfera cu aktceva si ruleaza consistent
    // threadul are grija de task-uri specifive in proces (unde componenetele sunt active)
}

