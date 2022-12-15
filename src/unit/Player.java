package unit;

import main.GamePanel;
import main.InputHandler;

import java.awt.*;

public class Player extends Unit{
    private final InputHandler inputHandler;

    //Shooting
    private int laserCount = 0;
    public Laser[] lasers = new Laser[10];
    boolean shoot = false;
    private int shootCoolDown = 0;

    public Player(GamePanel gp, InputHandler inputHandler){
        super(gp);
        this.inputHandler = inputHandler;
        for (int i = 0; i < 10; i++) {
            //lasers[i] = new Laser();
        }

        hitBox.x = 10;
        hitBox.y = 30;

        hitBox.width = 72;
        hitBox.height = 50;

        speed = 5;

        //image = loadSprite("player");
    }

    public void update() {
//        if (shoot){
//            shootCoolDown++;
//            if (shootCoolDown == 20){
//                shoot = false;
//                shootCoolDown = 0;
//            }
//        } else if (inputHandler.shoot){
//            shoot = true;
//            laserCount++;
//            if (laserCount == 10){
//                laserCount = 0;
//            }
//        }
//
//        if(inputHandler.left){
//            if (screenX > gp.size / 4){
//                screenX -= speed;
//            }
//        } else if (inputHandler.right) {
//            if (screenX < gp.width - gp.size / 4) {
//                screenX += speed;
//            }
//        }
//
//        if (invincible){
//            spriteCount++;
//            if (spriteCount == 30){
//                spriteCount = 0;
//                invincible = false;
//            }
//        }
    }

    public void draw(Graphics2D g2){

//        if (spriteCount < 5){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
//        } else if (spriteCount < 10){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        } else if (spriteCount < 15){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
//        } else if (spriteCount < 20){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        } else if (spriteCount < 25){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
//        } else if (spriteCount < 30){
//            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
//        }

        g2.drawImage(image, screenX, screenY, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
    }
}
