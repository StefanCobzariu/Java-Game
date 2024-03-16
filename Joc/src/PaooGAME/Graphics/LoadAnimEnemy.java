package PaooGAME.Graphics;

public class LoadAnimEnemy {
    public static final int ID_ENEMY_1 = 1;
    public static final int ID_ENEMYBOSS_1 = 2;
    public static final int ID_ENEMYBOSS_2 = 3;
    public static final int ID_ENEMYBOSS_3 = 4;


    // inamic comun
    public static final int IDLE_ENEMY = 0;
    public static final int RUNNING_ENEMY = 1;
    public static final int ATTACK_ENEMY = 2;

    public static final int DEAD_ENEMY = 3;
    public static final int LEFT_IDLE_ENEMY = 4;
    public static final int LEFT_RUNNING_ENEMY = 5;

    public static final int LEFT_ATTACK_ENEMY = 6;

    public static final int LEFT_DEAD_ENEMY = 7;

    // final boss lvl1
    public static final int IDLE_ENEMYBOSS1 = 0;
    public static final int RUNNING_ENEMYBOSS1 = 1;

    public static final int ATTACK_ENEMYBOSS1 = 2;
    public static final int DEAD_ENEMYBOSS1 = 3;

    public static final int LEFT_IDLE_ENEMYBOSS1 = 4;
    public static final int LEFT_RUNNING_ENEMYBOSS1 = 5;
    public static final int LEFT_ATTACK_ENEMYBOSS1 = 6;

    public static final int LEFT_DEAD_ENEMYBOSS1 = 7;


    //  public static BufferedImage[][] Animation;  // matrice in care incarc toate subimaginile
    public static int GetSpriteAmountEnemy(int player_action, int idEnemy) { // imi returneaza cate imagini am pt fiecare actiune
        if (idEnemy == 1) {
            if (player_action == IDLE_ENEMY ||player_action == LEFT_IDLE_ENEMY || player_action == DEAD_ENEMY || player_action == LEFT_DEAD_ENEMY) {
                return 3;
            } else if (player_action == RUNNING_ENEMY || player_action == ATTACK_ENEMY || player_action == LEFT_ATTACK_ENEMY || player_action == LEFT_RUNNING_ENEMY) {
                return 5;
            }
        } else if (idEnemy == 2) {
            if (player_action == IDLE_ENEMY ||  player_action == LEFT_IDLE_ENEMY)
                return 3;
            else if (player_action== DEAD_ENEMY ||player_action== LEFT_DEAD_ENEMY ||player_action== ATTACK_ENEMY ||player_action == LEFT_ATTACK_ENEMY ||player_action == RUNNING_ENEMY || player_action == LEFT_RUNNING_ENEMY)
                    return 5;
            }
        else if (idEnemy == 3) {
            if (player_action == IDLE_ENEMY ||  player_action == LEFT_IDLE_ENEMY)
                return 3;
            else if (player_action== DEAD_ENEMY ||player_action== LEFT_DEAD_ENEMY ||player_action== ATTACK_ENEMY ||player_action == LEFT_ATTACK_ENEMY ||player_action == RUNNING_ENEMY || player_action == LEFT_RUNNING_ENEMY)
                return 5;
        }
        else if (idEnemy == 4) {
            if (player_action == IDLE_ENEMY ||  player_action == LEFT_IDLE_ENEMY)
                return 3;
            else if (player_action== DEAD_ENEMY ||player_action== LEFT_DEAD_ENEMY ||player_action== ATTACK_ENEMY ||player_action == LEFT_ATTACK_ENEMY ||player_action == RUNNING_ENEMY || player_action == LEFT_RUNNING_ENEMY)
                return 5;
        }

            return 0;
        }
    }
