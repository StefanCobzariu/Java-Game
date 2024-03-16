package PaooGAME;
import PaooGAME.GameWindow.GameWindow;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import PaooGAME.Graphics.AssetsTiles;
import PaooGAME.Items.*;

import static PaooGAME.Camera.*;
import static PaooGAME.Graphics.ImageLoader.LoadImage;
import static PaooGAME.Tiles.Tile.*;

import PaooGAME.Maps.Map1;
import PaooGAME.Tiles.Tile;
//thread-urile reprezintă un mecanism prin care
//un calculator poate sa ruleze mai multe lucruri simultan.
//Clasa principala a intregului proiect. Implementeaza Game - Loop (Update -> Draw)
 // Interfata este utilizata pentru a crea un nou fir de executie avand ca argument clasa Game.

public class Game implements Runnable {   // implementeaza clasa Runnable pt avea comportament de thread
    private GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private GamePanel gamePanel;
    private Thread gameThread;    /*!< Referinta catre thread-ul(GAMELOOP) de update si draw al ferestrei*/
    private Hero hero;
    public BufferedImage bf = LoadImage("/padure.png");
    public static final float SCALE= 1.0f;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);

   // public final static int TILES_IN_WIDTH =26;// harta in nr de dale vizibile care vor fi desenate de camera
   // public final static int TILES_IN_HEIGHT=14; // harta in nr de dale (in inaltime)care vor fi desenate de camera
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH  ;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT ;
    private static final String img1="/1.png";
    private static final String img2="/2.png";
    private static final String img3="/3.png";
    private static final String img4="/4.png";
    private static final String img5="/5.png";
    public static final String stBar= "/stBar.png";
    public final int maxMaps=3; // cate harti pot crea maxim
    public  int currentMap=0 ;
    public  int currentLevel=currentMap+1 ;

    public EnemyManager en1;
    private ArrayList<Coin> coin;

    private ObjectManager ob1;




    private  BufferedImage []bckg1={LoadImage(img1),LoadImage(img2),LoadImage(img3),LoadImage(img4),LoadImage(img5)};
    private BufferedImage piramida=LoadImage("/piramida.png");
    private Tile t;
    public Map1 map1;
    public Map1 map2;
    public Map1 map3;
    public static int lvlXp=0;

    public static DBCreator db;

    public static Coliziune coliziune;
    public static  Camera c1 = new Camera();
    private static Game singleton;
    public static boolean GameOver=false;
    public static boolean GameCompleted=false;
    public static int scorFinal =0;
    public void checkIsEnemyHit(Rectangle attackHitbox){
       en1.checkIsEnemyHit(attackHitbox,this); //
    }


    public static Game GetSingleton(){
        if(Game.singleton==null){
            Game.singleton=new Game();
        }
        return Game.singleton;
    }

    private Game() {
        this.InitClasses();
        gamePanel = new GamePanel(this);
        wnd = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();  //folosind asta dacă apăs orice fel de tastă sau dau orice intrare, intrarea este auzită de ascultătorul respectiv pentru acea componentă.(de keylistner) si o face sa poata fi vizibila
        // Assets.Init();
       // startGameLoop();
    }

    private void InitClasses() {
        db=new DBCreator();
        hero = new Hero(0, 48 * 7, (int) (96 * SCALE), (int) (96 * SCALE));
        // enemy1 =new Enemy(0, 0, (int) (96 * SCALE), (int) (96 * SCALE),1);
        en1 = new EnemyManager(this);


        map1 = new Map1(this);

        AssetsTiles.InitTile();
        hero.InitG(this);


        ob1=new ObjectManager(this);
        coliziune = new Coliziune(this);


    }
    protected void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {   //actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
    if(!GameCompleted && !GameOver) {
        hero.update();
        if (en1 != null) {
            en1.update(this, hero);
        } else {
            System.out.println("inamici comuni nuli");
        }

        ob1.update(hero);
        updateCamera(hero);
    }

    }

    public void render(Graphics g) {
        if (!GameCompleted && !GameOver ) {
            if (g != null) {

                g.drawImage(bckg1[0], 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
                for (int i = 0; i < 3; ++i) {   // desenez de 3 ori cu distanta de 500 pixeli
                    g.drawImage(bckg1[1], i * 500, 0, 500, 384, null);
                }
                for (int i = 0; i < 6; ++i) {
                    g.drawImage(bckg1[2], i * 700 - (int) (xCameraOffset * 0.1), 0, 750, 384, null);
                }
                for (int i = 0; i < 6; ++i) {
                    g.drawImage(bckg1[3], i * 700 - (int) (xCameraOffset * 0.3), 0, 700, 384, null);
                }
                for (int i = 0; i < 6; ++i) {
                    g.drawImage(bckg1[4], i * 650 - (int) (xCameraOffset * 0.5), 0, 650, 384, null);
                }

                if (currentMap == 3) {
                    g.drawImage(piramida, 74 * 48 - xCameraOffset, 107, 350, 350, null);
                }
                map1.Draw(g, xCameraOffset, currentMap);
                g.setColor(Color.pink);
                Font scoreFont = new Font("Arial", Font.BOLD, 30);
                g.setFont(scoreFont);
                g.drawString("Level:" + currentLevel,centerPosX,20);
                hero.render(g, xCameraOffset);
                en1.draw(g, xCameraOffset, this);
                ob1.drawCoin(g, xCameraOffset, hero);
                //  g.drawString("scorul: " + xp);
            }
        }
        else if(GameCompleted){

            g.setColor(Color.green);
            Font titleFont = new Font("Arial", Font.BOLD, 60);
            g.setFont(titleFont);
            g.drawString("Game Completed", centerPosX-220 , 100);

            Font scoreFont = new Font("Arial", Font.BOLD, 40);
            g.setFont(scoreFont);
            g.drawString("scorFinal "+ scorFinal,centerPosX-200,300);
        }
        else if(GameOver){
            g.setColor(Color.green);
            Font titleFont = new Font("Arial", Font.BOLD, 60);
            g.setFont(titleFont);
            g.drawString("Game Over", centerPosX-200 , 100);

            Font scoreFont = new Font("Arial", Font.BOLD, 40);
            g.setFont(scoreFont);
            g.drawString("scorFinal "+ scorFinal,centerPosX-200,300);
        }
    }

    @Override
    public void run() {          //        Clasa Game trebuie sa aiba definita metoda "public void run()", metoda ce va fi apelata in noul thread(fir de executie)
        final int UPS = 120;// Update per s
        final int FPS = 60;
        final double timePerFrame = 1000000000.0 / FPS; // durata unuui FRAME in nanosecunde
        final double timePerUpdadte = 1000000000.0 / UPS;

        // long oldTime=System.nanoTime();   ////System.currentTimeMillis() - returneaza timpul in miliesecunde
        //update are grija de logica jocului misc personaje ,etc . frame- deseneaza personajele , harta , niv
        // facem asta pt a seta FPS - in functie de pc , + daca are lag sa rezolv prb - mecanism de catch -la update
        long oldTime = System.nanoTime(); //pt update
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();
        double dU = 0;
        double dF = 0;
        while (gameThread!=null) {
            // run va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)  update+draw{     // /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            long currentTime = System.nanoTime();// act timp curent
            dU = dU + ((currentTime - oldTime) / timePerUpdadte);  // va fi 1 sau mai mare
            dF = dF + ((currentTime - oldTime) / timePerFrame);
            oldTime = currentTime;
            if (dU >= 1) {
                update();         //actualizeaza variabile, stari, pozitii ale elementelor grafice etc.
                updates++;        // /// Actualizeaza pozitiile elementelor
                dU--;
            }
            if (dF >= 1) {
                gamePanel.repaint();  // are ca efect desenarea pe ecran a actualiz
                frames++;                ///// Deseneaza elementele grafica in fereastra.
                dF--;
            }

            // verificare FPS
            if (System.currentTimeMillis() - lastCheck >= 1000)  //System.currentTimeMillis() - returneaza timpul in miliesecunde 1sec=1000 mili
            {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + "  " + "UPS" + updates);
                frames = 0;
                updates = 0;
            }

        }
    }
    public void windowFocusLost() {
        hero.resetDirBooleans();
    }


}











