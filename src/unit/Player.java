package unit;

import Sound.SoundTracks;
import main.GamePanel;
import main.InputHandler;
import main.Utility;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;

    public int score = 0;
    public boolean invincible = false;
    public int invincibilityCount = 0;

    //Shooting
    private int laserCount = 0;
    private int laserCoolDown = 0;
    boolean shoot = false;

    public Player(InputHandler inputHandler, int size, int positionY){
        this.inputHandler = inputHandler;
        image = new Utility().loadImage("player", size, size);
        hurt = new Utility().loadImage("playerhurt", size, size);

        this.positionY = positionY;
        positionX = size * 5;

        setUpHitBox();
        setUpStats();
    }
    private void setUpStats(){
        //antal pixlar
        speed = 5;
        lives = 3;
        alive = true;
    }
    private void setUpHitBox(){
        hitBox.x = 5;
        hitBox.y = 20;
        hitBox.width = 50;
        hitBox.height = 30;
        defaultHitBoxX = hitBox.x;
        defaultHitBoxY = hitBox.y;
    }

    public void update(GamePanel gp) {

        if (invincible && invincibilityCount == 60*4){
            invincible = false;
            invincibilityCount = 0;
        }else if (invincible && invincibilityCount <= 60*4 ) {
            invincibilityCount++;
        }
        if(shoot){
            laserCoolDown++;
            if(laserCoolDown == 20){
                shoot = false;
                laserCoolDown = 0;
            }
        } else if(inputHandler.shoot){
            shoot = true;
            gp.sound.playSoundEffect(SoundTracks.LASER);
            gp.lasers[laserCount].setUpLaser(positionX - 1, 7);
            laserCount++;
            if (laserCount == 10){
                laserCount = 0;
            }
        }
        //om spelaren trycker vänster så minskar positionen med antal pixlar speed //samma med höger fast ökar speed
        if (inputHandler.left){
            if(positionX > gp.size / 8){
                positionX -= speed;
            }
        } else if (inputHandler.right){
            //om x blir större än bredden så förflyttas man inte för att inte hamna utanför rutan
            if(positionX < gp.width - (gp.size + gp.size / 8)){
                positionX += speed;
            }
        }

        updateHitBox();

    }

    public void draw(Graphics2D g2){

        if(invincible){
            g2.drawImage(hurt, positionX, positionY, null);
        }

        if(invincible){
            if (invincibilityCount < 30){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            } else if (invincibilityCount < 60){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            } else if (invincibilityCount < 90){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            } else if (invincibilityCount < 120){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            } else if (invincibilityCount < 150){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            } else if (invincibilityCount < 180){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            } else if (invincibilityCount < 210){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            } else if (invincibilityCount < 240){
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            }
        }

        g2.drawImage(image, positionX, positionY, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void incrementScore(){
        score++;
    }
    public void reset(){
        score = 0;
        lives = 3;
        invincible = false;
        invincibilityCount = 0;
    }
    public int getScore(){
        return score;
    }

}
