package unit;

import Sound.SoundTracks;
import main.GamePanel;
import main.InputHandler;
import main.Property;
import main.Utility;

import java.awt.*;

public class Player extends Unit{

    private final InputHandler inputHandler;
    Property property = Property.getInstance();
    public int score = 0;
    public int lives = Integer.parseInt(property.getProperty("lives"));
    public boolean invincible = false;
    public int invincibilityCount = 0;

    //Difficulty
    int difficultyMultiplier = Integer.parseInt(property.getProperty("difficulty"));
    boolean superGunCheat = Boolean.parseBoolean(property.getProperty("supergun"));
    int laserCoolDownTimer = Integer.parseInt(property.getProperty("lasercooldown")) - (difficultyMultiplier*4);
    boolean godMode = Boolean.parseBoolean(property.getProperty("godmode"));

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
    public void setUpStats(){
        //antal pixlar
        difficultyMultiplier = Integer.parseInt(property.getProperty("difficulty"));
        speed = 5 * difficultyMultiplier;
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
        difficultyMultiplier = gp.difficulty;
        if (invincible && invincibilityCount == 60*4 / difficultyMultiplier){
            invincible = false;
            invincibilityCount = 0;
        }else if (invincible && invincibilityCount <= 60*4 ) {
            invincibilityCount++;
        }
        if(shoot){
            laserCoolDown++;
            if (superGunCheat){
                laserCoolDownTimer = 1;
            }
            if(laserCoolDown == laserCoolDownTimer){
                shoot = false;
                laserCoolDown = 0;
            }
        } else if(inputHandler.shoot){
            if(gp.player.score >= 0) {
                shoot = true;
                gp.player.decreaseScore(5);
                gp.sound.playSoundEffect(SoundTracks.LASER, Integer.parseInt(property.getProperty("lasersound")));
                gp.lasers[laserCount].setUpLaser(positionX - 1, positionY - 1, 7 * difficultyMultiplier);
                laserCount++;
                if (laserCount == 100) {
                    laserCount = 0;
                }
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

        if (inputHandler.up){
            if(positionY > gp.size / 8){
                positionY -= speed;
            }
        } else if (inputHandler.down) {
            if(positionY < gp.height - (gp.size + gp.size / 8)){
                positionY += speed;
            }

        }

        updateHitBox();

    }

    public void draw(Graphics2D g2){

        if(invincible || godMode){
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
    public void incrementScore(boolean laserHit){
        int addScore = 10;
        if(!laserHit){
            difficultyMultiplier = 1;
            addScore = 1;
        }
        score = score + addScore * difficultyMultiplier;
    }

    public void decreaseScore(int decreaseAmount){
        score = score - decreaseAmount;
    }
    public void resetScore(){
        score = 0;
        lives = Integer.parseInt(property.getProperty("lives"));;
        invincible = false;
        invincibilityCount = 0;
    }
    public int getScore(){
        return score;
    }

    public int getLaserCoolDownTimer() {
        return laserCoolDownTimer;
    }

    public void setLaserCoolDown(int laserCoolDown) {
        this.laserCoolDown = laserCoolDown;
    }
}
