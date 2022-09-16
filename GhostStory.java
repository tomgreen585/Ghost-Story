
/**
 * Write a description of class GhostStory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import ecs100.*;

public class GhostStory
{

    public void moveOneGhost() {
        Ghost g = new Ghost(100, 200, 50, 50);
        g.move();
        g.move();

    }

    public void moveTwoGhosts() {
        UI.clearGraphics();
        Ghost g1 = new Ghost(100,200,50,50);
        Ghost g2 = new Ghost(100,400,50,50);

        g1.lookDown();
        g2.lookUp();
        g2.setPinky();

        g2.move();
        g2.move();
        g2.move();

        int i=0;
        while(i<6) {
            g1.move();
            i = i+1;
        }

        /*
        for(int i=0; i<6; i++) {
        g1.move();
        }*/

    }

    public void moveTwoGhostsMethods() {
        Ghost g1 = new Ghost(100,200,50,50);
        Ghost g2 = new Ghost(200,300,50,50, "Inky"); 

        g2.lookStraight();

        this.moveSquare(g1,10); 
        this.moveSquare(g2,5); 

    }

    public void moveTwoGhostsDistance() {
        Ghost g1 = new Ghost(100,200,50,50);
        Ghost g2 = new Ghost(400,200,50,50, "Pinky");

        g1.lookRight();

        while(this.getDistance(g1,g2) > 50) {
            g1.move();
        }  

        g2.setScared(true);
        g2.lookRight();
        g2.move();
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

    //I did not manage to do this in lecture, but this method makes the code cleaner.
    public void moveSteps(Ghost ghost, int count) {
        int i = 0;
        while(i<count) {
            ghost.move();
            i=i+1;
        }
    }

    //Using pythagora
    public double getDistance(Ghost g1, Ghost g2) {
        Double a2 = (g1.getMiddleX() - g2.getMiddleX()) * (g1.getMiddleX() - g2.getMiddleX());
        Double b2 = (g1.getMiddleY() - g2.getMiddleY()) * (g1.getMiddleY() - g2.getMiddleY());

        return Math.sqrt(a2 + b2);
    }

    /**
     * Inspired by: https://www.geeksforgeeks.org/find-two-rectangles-overlap/
     * (Not part of reference: Found by googling "two squares overlab")
     */
    public boolean ghostCollide(Ghost g1, Ghost g2) {

        return false; //TODO
    }

    public void setupGUI() {
        UI.addButton("Move One", this::moveOneGhost);
        UI.addButton("Move Two", this::moveTwoGhosts);
        UI.addButton("Move Two Methods", this::moveTwoGhostsMethods);
        UI.addButton("Move Two Distance", this::moveTwoGhostsDistance);
        UI.addButton("Clear", UI::clearGraphics);
    }

    public static void main(String[] args) {
        new GhostStory().setupGUI();
    }
}
