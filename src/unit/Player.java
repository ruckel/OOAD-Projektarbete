package unit;

import main.GamePanel;
import main.InputHandler;
import ui.EndScreen;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;

    private boolean invincible;
    private int invincibleCount;

    //Shooting
    private int laserCount = 0;
    private int laserCoolDown = 0;
    boolean shoot = false;

    public Player(GamePanel gp, InputHandler inputHandler){
        super(gp);
        this.inputHandler = inputHandler;
        image = loadImage("player");

        positionY = gp.height - (gp.size + gp.size / 10);
        positionX = gp.width / 2 - gp.size / 2;

        setUpLasers();
        setUpHitBox();
        setUpStats();
    }
    private void setUpLasers(){
        for (int i = 0; i < gp.lasers.length; i++) {
            gp.lasers[i] = new Laser(gp);
        }
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

    public void update() {
        if(shoot){
            laserCoolDown++;
            if(laserCoolDown == 20){
                shoot = false;
                laserCoolDown = 0;
            }
        } else if(inputHandler.shoot){
            shoot = true;
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

        if(!alive){
            System.out.println("YOU CRASHED AND DIED!NYBÖRJARE");
            alive = true;
        }
    }

    public void draw(Graphics2D g2){

        g2.drawImage(image, positionX, positionY, null);

        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        g2.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
}
