import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @author Nihinlolamiwa Fajemilehin
 * @version 2016.02.29
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random rdm;
    private ArrayList<BouncingBall> balls;
    private ArrayList<Color> ballColors;
    private int horizontalCoordinate;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        rdm = new Random();
        balls = new ArrayList<>();
        ballColors = new ArrayList();
        storeColors();
        horizontalCoordinate = 0;
    }

    /**
     * Simulate two bouncing balls
     * @param numberOfBalls The number of balls to draw and bounce
     */
    public void bounce(int numberOfBalls)
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        //Loop through the number of balls to be created
        int i= 1;
        while(numberOfBalls > 0 && i <= numberOfBalls) {
            balls.add(new BouncingBall(getNewHorizontalCoordinate(), 60, getRandomBallSize(), getRandomColor(), ground, myCanvas));
            i++;
        }

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);
            // small delay
            for(BouncingBall ball : balls) {
                ball.draw();
                ball.move();
            }
            
            //get first ball created, but last to leave the screen
            BouncingBall lastBall = balls.get(0);
            
            //stop once first ball has travelled a certain distance on x axis
            if(lastBall.getXPosition() >= 550) {
            finished = true;
            }
        }
    }
   
    /**
     * Method to get random number for balls size
     */
    private int getRandomBallSize()
    {
        int value = rdm.nextInt(90); 
        return value;
    }

    /**
     * Method to get horizontal coordinate for every new ball created
     */
    private int getNewHorizontalCoordinate()
    {
        return horizontalCoordinate = horizontalCoordinate + 100;
    }

    /**
     * Method to add a list of colors to the possible colors of balls
     */
    private void storeColors()
    {
        ballColors.add(Color.GREEN);
        ballColors.add(Color.BLUE);
        ballColors.add(Color.ORANGE);
        ballColors.add(Color.PINK);
        ballColors.add(Color.YELLOW);
        ballColors.add(Color.GRAY);
    }

    /**
     * Method to get a random color for a ball from the
     * list available
     */
    private Color getRandomColor()
    {
        int index = rdm.nextInt(ballColors.size());
        return ballColors.get(index);
    }
}
