
/**
 * Write a description of class GhostsStory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import ecs100.*;
import java.util.*;

public class GhostsStory
{

    public void many() {
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

        for(int i = 100; i<=600; i = i + 50) {
            ghosts.add(new Ghost(100,i,25,25));

        }

        for(Ghost g: ghosts) {
            g.lookRight();
            if(g.getY() % 100 == 0) {
                this.moveSteps(g,3);
            } else {
                this.moveSteps(g,6);
            }
        }

    }

    public void manyUserGivenPostions() {
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
        ArrayList<Double> pos = UI.askNumbers("Enter positions (x enter, y enter):");

        int i = 0;
        while(i +1 < pos.size()) {
            double x = pos.get(i++);
            double y = pos.get(i++);
            ghosts.add(new Ghost(x,y,25,25));
        }
    }

    public void manyRandom() {
        //Create a list of Ghosts with random positions
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();
        for(int i = 0; i<10; i = i + 1) {
            double x = Math.random()*300;
            double y = 50+ Math.random()*300;

            Ghost g = new Ghost(x,y,25,25);
            this.randomGhostType(g);
            ghosts.add(g);

    
        }

        //Move them using Squares
        //Update Ghosts to scare when they collide

    }

    public void randomGhostType(Ghost g) {
        double random = Math.random();
        if(random < 0.25) {
            g.setInky();
        } else if(random >= 0.25 && random < 0.5) {
            g.setPinky();
        } else if(random > 0.75) {
            g.setPokey();
        }
    }

    public void many2D() {
        ArrayList<Ghost> ghosts = new ArrayList<Ghost>();

        //draw Ghosts in a square
        for(int x =100; x <=600; x = x + 100) {
            for(int y = 100; y<=600; y = y + 50) {
                Ghost g = new Ghost(x,y,25,25);
                this.randomGhostType(g);
                ghosts.add(g);
            }
        }

        for(Ghost g: ghosts) {
            moveSquareAware(g, 5, ghosts);
        }
    }

    public void moveSquare(Ghost g, int count) {
        g.lookRight();
        this.moveSteps(g, count);
        g.lookDown();
        this.moveSteps(g, count);
        g.lookLeft();
        this.moveSteps(g, count);
        g.lookUp();
        this.moveSteps(g, count);
        g.lookStraight();
    }

    //Move in square redrawing all Ghosts in case they collide.
    public void moveSquareAware(Ghost g, int count, ArrayList<Ghost> ghosts) {
        g.lookRight();
        this.moveStepsAware(g, count, ghosts);
        g.lookDown();
        this.moveStepsAware(g, count, ghosts);
        g.lookLeft();
        this.moveStepsAware(g, count, ghosts);
        g.lookUp();
        this.moveStepsAware(g, count, ghosts);
        g.lookStraight();
    }

    /**
     * Move the Ghost a number of steps forward
     * @param ghost the ghost that should move
     * @param count the number of "steps"
     */
    public void moveSteps(Ghost ghost, int count) {
        int i = 0;
        while(i<count) {
            ghost.move();
            i=i+1;
        }
    }

    /**
     * Move the Ghost a number of steps forward, redrawing all Ghosts in case they collide
     * @param ghost the ghost that should move
     * @param count the number of "steps"
     * @param ghosts list of ghosts of the environment
     */
    public void moveStepsAware(Ghost ghost, int count, ArrayList<Ghost> ghosts) {
        int i = 0;
        while(i<count) {
            ghost.move();
            //Draw all ghosts to avoid missing parts
            for(Ghost g: ghosts) {
                g.draw(false);
            }
            i=i+1;
        }
    }

    /**
     * Get the distance between two ghosts (using the middle of the ghosts)
     *    using Pythagoras (a*a + b*b = c*c <=> c = sqrt(a*a + b*b)) 
     * @param g1 Ghost #1
     * @param g2 Ghost #2
     */
    public double getDistance(Ghost g1, Ghost g2) {
        Double a2 = (g1.getMiddleX() - g2.getMiddleX()) * (g1.getMiddleX() - g2.getMiddleX());
        Double b2 = (g1.getMiddleY() - g2.getMiddleY()) * (g1.getMiddleY() - g2.getMiddleY());

        return Math.sqrt(a2 + b2);
    }

    /**
     * Two squares don't collide if
     *    one of them is to the left of the other, or
     *    one of them is below the other
     *    
     * Inspired by: https://www.geeksforgeeks.org/find-two-rectangles-overlap/
     */
    public boolean ghostCollide(Ghost g1, Ghost g2) {
        if(g1.getLeft() > g2.getRight() || g2.getLeft() > g1.getRight()) return false;
        if(g1.getTop() > g2.getBottom() || g2.getTop() > g1.getBottom()) return false;
        return true;
    }

    public void setupGUI() {
        UI.addButton("Many" , this::many);
        UI.addButton("Many (User)" , this::manyUserGivenPostions);
        UI.addButton("Many (Random)" , this::manyRandom);
        UI.addButton("Many (2D)" , this::many2D);
        UI.addButton("Clear", UI::clearGraphics);
    }

    public static void main(String[] args) {
        new GhostsStory().setupGUI();
    }
}
