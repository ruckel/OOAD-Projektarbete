//package unit;
//
//import java.util.Random;
//
//public class UnitLoader {
//
//    public Obstacle[] obstacles = new Obstacle[20];
//
//    private int obstacleCount = 0;
//    private int loopCount = 0;
//
//    private final int screenWidth;
//
//    private int obstacleInterval = 50;
//
//
//    public UnitLoader(int screenY, int endOfScreen, int screenWidth){
//        for (int i = 0; i < 20; i++) {
//            obstacles[i] = new Obstacle(screenY, endOfScreen);
//        }
//        this.screenWidth = screenWidth;
//    }
//
//    public void update(){
//        loopCount++;
//        if (loopCount == obstacleInterval){
//            loopCount = 0;
//            obstacles[obstacleCount].setObstacle(randomSpawnX(), randomSpeed());
//            obstacleCount++;
//            if (obstacleCount == 20){
//                obstacleCount = 0;
//            }
//        }
//    }
//    public void setObstacleInterval(int interval){
//        obstacleInterval = interval;
//    }
//
//    private int randomSpeed() {
//        int speed;
//        Random rng = new Random();
//        int randomNr = rng.nextInt(1, 101);
//        if (randomNr < 20){
//            speed = 4;
//        } else if (randomNr < 50){
//            speed = 5;
//        } else if (randomNr < 80){
//            speed = 6;
//        } else {
//            speed = 7;
//        }
//        return speed;
//    }
//
//    private int randomSpawnX() {
//        Random rng = new Random();
//        return rng.nextInt(5, screenWidth);
//    }
//}
