package unit;

import main.GamePanel;
import main.InputHandler;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;

    private boolean invincible;
    private int invincibleCount;

    //Shooting
    private int laserCount = 0;
    private int laserCoolDown = 0;
    boolean shoot = false;
    public Laser[] lasers = new Laser[10];

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
        for (int i = 0; i < 10; i++) {
            lasers[i] = new Laser(gp);
        }
    }
    private void setUpStats(){
        //antal pixlar
        speed = 5;
    }
    private void setUpHitBox(){
        hitBox.x = 10;
        hitBox.y = 30;
        hitBox.width = 72;
        hitBox.height = 50;
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
            lasers[laserCount].setUpLaser(positionX - 1, 7);
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
    }

    public void draw(Graphics2D g2){

        g2.drawImage(image, positionX, positionY, null);

        //g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        //g2.drawRect(screenX + hitBox.x, screenY + hitBox.y, hitBox.width, hitBox.height);
    }
}
