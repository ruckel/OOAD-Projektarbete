package unit;

import main.GamePanel;

public class ObstacleFactory {

    private final GamePanel gp;

    public ObstacleFactory(GamePanel gp){
        this.gp = gp;
    }

    public Obstacle getObstacle(Obstacles obstacle){
        if(obstacle == Obstacles.METEORITE){
            return new Meteorite(gp.size,-gp.size);
        } else {
            return new SuperMeteorite(gp.size,-gp.size);
        }
    }
}
