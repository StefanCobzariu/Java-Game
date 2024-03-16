package PaooGAME.Graphics;

// folosesc static pt a o folosi si in alte clase
// desemna variabile și funcții care aparțin unei clase, dar nu unui obiect anume al clasei.
public class LoadAnim {


    public static final int IDLE = 0;
    public static final int RUNNING = 2;
    public static final int JUMP = 15;
    public static final int FALLING = 4;
    public static final int HIT = 18;
    public static final int DEAD = 19;

    public static final int ATTACK_1 = 5;
    public static final int ATTACK_2 = 9;
    public static final int ATTACK_3 = 10;

    public static final int LEFT_RUNNING = 20;
    public static final int LEFT_JUMP = 25;
    public static final int LEFT_FALLING = 4;
    public static final int LEFT_HIT = 18;
    public static final int LEFT_DEAD = 19;
    public static final int LEFT_ATTACK_1 = 5;
    public static final int LEFT_ATTACK_2 = 22;
    public static final int LEFT_ATTACK_3=21;

    //  public static BufferedImage[][] Animation;  // matrice in care incarc toate subimaginile
    public static int GetSpriteAmount(int player_action) { // imi returneaza cate imagini am pt fiecare actiune
          {
            if (player_action == IDLE || player_action == RUNNING || player_action==LEFT_RUNNING ) {
                return 6;
            } else if (player_action == JUMP ||player_action ==LEFT_JUMP || player_action == HIT || player_action == LEFT_HIT) {
                return 4;
            } else if (player_action == ATTACK_1 || player_action == LEFT_ATTACK_1 ) {
                return 5;
            } else if (player_action == ATTACK_2 || player_action ==LEFT_ATTACK_2 ) {
                return 7;
            } else if (player_action == ATTACK_3|| player_action ==LEFT_ATTACK_3 ) {
                return 7;
            } else if (player_action == FALLING || player_action== LEFT_FALLING) {
                return 2;
            } else if (player_action == DEAD || player_action == LEFT_DEAD) {
                return 7;
            }
        }
       return 1;
    }
}

