package unit;

import Sound.SoundTracks;
import main.GamePanel;
import main.InputHandler;
import main.Utility;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;

    private int score = 0;

    //Shooting
    private int laserCount = 0;
    private int laserCoolDown = 0;
    boolean shoot = false;

    public Player(InputHandler inputHandler, int size, int positionY){
        this.inputHandler = inputHandler;
        image = new Utility().loadImage("player", size, size);

        this.positionY = positionY;
        positionX = size * 5;

        setUpHitBox();
        setUpStats();
    }
    private void setUpStats(){
        //antal pixlar
        speed = 5;
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
        g2.drawImage(image, positionX, positionY, null);
    }
    public void incrementScore(){
        score++;
    }
    public void resetScore(){
        score = 0;
    }
    public int getScore(){
        return score;
    }

}
