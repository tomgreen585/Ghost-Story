/* Code for Week 3
 * Name:Karsten
 */

import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** Draws little pictures on the graphics pane
 */
public class Ghost{
    /**
     * Used to reference BLINKY
     */
    public static final String BLINKY = "Blinky";
    /**
     * Used to reference PINKY
     */
    public static final String PINKY = "Pinky";
    /**
     * Used to reference INKY
     */
    public static final String INKY = "Inky";
    /**
     * Used to reference POKEY
     */
    public static final String POKEY = "Pokey";

    /**
     * Used to reference looking up
     */
    public static final String UP = "UP";
    /**
     * Used to reference looking down
     */
    public static final String DOWN = "DOWN";
    /**
     * Used to reference looking left
     */
    public static final String LEFT = "LEFT";
    /**
     * Used to reference looking right
     */
    public static final String RIGHT = "RIGHT";
    /**
     * Used to reference looking straight
     */
    public static final String STRAIGHT = "STRAIGHT";

    private double x,y;
    private double wd,ht;
    private double speed;

    private String name = BLINKY;
    private String direction = LEFT;

    private boolean scared = false;
    
    private int sleep = 200;

    /**
     * Ghost constructor <br/>(used when using new Ghost(...) )
     * 
     * @param x Left most postion of the Ghost
     * @param y Bottom most position of the Ghost
     * @param width Width of the Ghost
     * @param height Height of the body (without the head) of the Ghost
     */
    public Ghost(double x, double y, double width, double height) {
        this(x,y,width,height,BLINKY, LEFT);
    }
    
    /**
     * Ghost constructor <br/>(used when using new Ghost(...) )
     * 
     * @param x Left most postion of the Ghost
     * @param y Bottom most position of the Ghost
     * @param width Width of the Ghost
     * @param height Height of the body (without the head) of the Ghost
     * @param name the name of the Ghost (using the constant Ghost names)
     */
    public Ghost(double x, double y, double width, double height, String name) {
        this(x,y,width,height,name, LEFT);
    }
    
    /**
     * Ghost constructor <br/>(used when using new Ghost(...) )
     * 
     * @param x Left most postion of the Ghost
     * @param y Bottom most position of the Ghost
     * @param width Width of the Ghost
     * @param height Height of the body (without the head) of the Ghost
     * @param name the name of the Ghost (using the constant Ghost names)
     * @param direction the direction the Ghost is looking (using the constant Ghost directions)
     */
    public Ghost(double x, double y, double width, double height, String name, String direction) {
        this.x = x;
        this.y = y;
        wd = width;
        ht = height;

        speed = (wd + ht)/4;
        this.name = name;
        this.direction = direction;

        draw(false);
    }
    
    /** 
     * Erase a Ghost by drawing white over its body
     */
    public void erase() {
        double left = x; 
        double topRect = y - ht;
        double topOval = topRect-wd/2;

        double yCurtainOval = y - (wd/2)/4;
        double curtainSize = wd/4;
        
        UI.setColor(Color.WHITE);
        //Body and head
        UI.fillOval(left, topOval, wd, wd);
        UI.fillRect(left, topRect, wd, ht);
        //Blinky's "curtain"
        for(int i=0; i<4;i++){
            UI.fillOval(left+curtainSize*i, yCurtainOval, curtainSize, curtainSize);
        }
    }

    /**
     * Draw Ghost at its current location with its current scare status and type
     */
    public void draw() {
        draw(true);
    }   
    
    /**
     * Draw Ghost at its current location with its current scare status and type
     * @param sleep true will sleep the UI, false, will continue execution immidiately
     */
    public void draw(boolean sleep) {
        double left = x; 
        double topRect = y - ht;
        double topOval = topRect-wd/2;

        double yCurtainOval = y - (wd/2)/4;
        double curtainSize = wd/4;

        double eyeSize = wd/4;
        double yEye = topRect;
        double eyeDistanceFromSide = wd/8;
        double xLeftEye = left+eyeDistanceFromSide;
        double xRightEye = left+wd-eyeDistanceFromSide-eyeSize;

        //Looking left
        double pupilSize = wd/8;
        double yPupil = y-ht + pupilSize/2;
        double xLeftPupil = xLeftEye;
        double xRightPupil = xRightEye;

        if(direction.equals(RIGHT)) {
            xLeftPupil = xLeftEye + eyeSize - pupilSize;
            xRightPupil = xRightEye + eyeSize - pupilSize;
        } else if(direction.equals(UP)) {
            yPupil = topRect;
            xLeftPupil = xLeftEye + eyeSize/2 - pupilSize/2;
            xRightPupil = xRightEye + eyeSize/2 - pupilSize/2;
        } else if(direction.equals(STRAIGHT)) {
            yPupil = topRect + eyeSize/2 - pupilSize/2;
            xLeftPupil = xLeftEye + eyeSize/2 - pupilSize/2;
            xRightPupil = xRightEye + eyeSize/2 - pupilSize/2;
        } else if(direction.equals(DOWN)) {
            yPupil = topRect + eyeSize - pupilSize;
            xLeftPupil = xLeftEye + eyeSize/2 - pupilSize/2;
            xRightPupil = xRightEye + eyeSize/2 - pupilSize/2;
        }

        //Blinky's body
        if(scared) {
            UI.setColor(Color.BLUE);
        } else {
            if(name.equals(BLINKY)) {
                UI.setColor(Color.red);
            } else if(name.equals(PINKY)) {
                UI.setColor(Color.PINK);
            } else if(name.equals(INKY)) {
                UI.setColor(Color.CYAN);
            } else  if(name.equals(POKEY)){
                UI.setColor(Color.YELLOW);
            } else {
                UI.println("ERROR, " + name + " Ghost does not exist!");
                return;
            }
        }

        UI.fillOval(left, topOval, wd, wd);
        UI.fillRect(left, topRect, wd, ht);

        //Blinky's "curtain"
        for(int i=0; i<4;i++){
            UI.fillOval(left+curtainSize*i, yCurtainOval, curtainSize, curtainSize);
        }

        //Blinky's eyes
        UI.setColor(Color.white);
        UI.fillOval(xLeftEye, yEye, eyeSize, eyeSize);
        UI.fillOval(xRightEye, yEye, eyeSize, eyeSize);

        //Blinky's pupils
        UI.setColor(Color.black);
        UI.fillOval(xLeftPupil, yPupil, pupilSize, pupilSize);
        UI.fillOval(xRightPupil,yPupil, pupilSize, pupilSize);
        
        if(sleep) UI.sleep(200);
    }

    private void updatePosition(double speed) {
        if(direction.equals(LEFT)) x-=speed;
        else if(direction.equals(RIGHT)) x+=speed;
        else if(direction.equals(UP)) y-=speed;
        else if(direction.equals(DOWN)) y+=speed;

        //DON'T MOVE IF MOVING STRAIGHT   
    }

    private void setDirection(String direction) {
        this.direction = direction;
        draw();
    }
    
    /**
     * Move the Ghost using its default speed
     */
    public void move() {move(speed);}

    /**
     * Move the Ghost using specified speed in the direction the Ghost is looking (no movement if the Ghost looks straight)
     * 
     * @param speed The user-specified speed in pixels
     */
    public void move(double speed) {
        erase();
        updatePosition(speed);
        draw();
    }

    /**
     * Set if the Ghost is scared.
     * @param scared true scared Ghost, false normal Ghost
     */
    public void setScared(boolean scared) {
        this.scared = scared;
        draw();
    }

    /**
     * Change the Ghost to look left
     */
    public void lookLeft() {
        setDirection(LEFT);
    }

    /**
     * Change the Ghost to look right
     */
    public void lookRight() {
        setDirection(RIGHT);
    }

    /**
     * Change the Ghost to look up
     */
    public void lookUp() {
        setDirection(UP);
    }

    /**
     * Change the Ghost to look down
     */
    public void lookDown() {
        setDirection(DOWN);
    }

    /**
     * Change the Ghost to look straight
     */
    public void lookStraight() {
        setDirection(STRAIGHT);
    }
    
    /**
     * Get the left most position of the Ghost
     * @return the left most position of the Ghost
     */
    public double getLeft() {return x;}
   
    /**
     * Get the right most position of the Ghost
     * @return the rigth most position of the Ghost
     */
    public double getRight() {return x+wd;}
    
    /**
     * Get the top most position of the Ghost
     * @return the top most position of the Ghost
     */
    public double getTop() { 
        double topRect = y - ht;
        double topOval = topRect-wd/2;
        return topOval;
    }
        
    /**
     * Get the bottom most position of the Ghost
     * @return the bottom most position of the Ghost
     */    
    public double getBottom() {return y+wd/8;}
        
    /**
     * Get the bottom most position of the Ghost
     * @return the bottom most position of the Ghost
     */    
    public double getY() {return y;}
    /**
     * Get the mid-point X position of the Ghost
     * @return the mid-point X position of the Ghost
     */
    public double getMiddleX() {
        return getLeft() + wd/2;
    }
    
    /**
     * Get the mid-point Y position of the Ghost
     * @return the mid-point Y position of the Ghost
     */
    public double getMiddleY() {
        return getTop() + ht/2 + wd/4 + wd/8;
    }
    
    /**
     * Set the Ghost to be Blinky
     */
    public void setBlinky() {
        name = BLINKY;
        draw();
    }
    
    /**
     * Set the Ghost to be Pinky
     */
    public void setPinky() {
        name = PINKY;
        draw();
    }
    
    /**
     * Set the Ghost to be Inky
     */
    public void setInky() {
        name = INKY;
        draw();
    }
    
    /**
     * Set the Ghost to be Pokey
     */
    public void setPokey() {
        name = POKEY;
        draw();
    }
}
