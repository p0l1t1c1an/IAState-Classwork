
// Written by Jacob Boicken

import java.io.IOException;
import java.io.File;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList;

import java.util.Arrays;

public class RobotPath {

    private int srcY;
    private int srcX; 

    private int dstY;
    private int dstX;

    private Grid grid;

    // North, East, South, West
    private static int dRow[] = { -1, 0, 1, 0 };
    private static int dCol[] = { 0, 1, 0, -1 };

    public RobotPath() {
        grid = new Grid();
    }

    public void readInput(String filename) throws IOException {
        File f = new File(filename);
        Scanner s = new Scanner(f);
        
        s.next();
        grid.setHeight(s.nextInt());
        
        s.next();
        grid.setWidth(s.nextInt());
      
        grid.initialize();

        s.next();
        srcY = s.nextInt();
        srcX = s.nextInt();

        s.next();
        dstY = s.nextInt();
        dstX = s.nextInt();
        
        s.next();
        while(s.hasNextInt()) {
            int y = s.nextInt();
            int x = s.nextInt();
            Node temp = grid.getNode(y, x);
            temp.setObstacle(true);
        }

        s.close();
    }

    public void planShortest() {
        grid.bfs(srcY, srcX);

        LinkedList<Point> points = new LinkedList<Point>();
        Node v = grid.getNode(dstY, dstX);
        v.setVisitedReverse(true);
        points.add(new Point(dstY, dstX));
        
        while(!points.isEmpty()) {
            Point p = points.poll();
            Node u = grid.getNode(p.y, p.x);
            for(int i = 0; i < 4; i++) {
                int ty = p.y + dRow[i];
                int tx = p.x + dCol[i];

                if(tx >= 0 && ty >= 0 && tx < grid.getWidth() && ty < grid.getHeight()) {
                    Node w = grid.getNode(ty, tx);
                    if(w.getLayer() == u.getLayer() -1) {
                        switch(i) {
                            case 0:
                                w.setSouth(true);
                                break;

                            case 1:
                                w.setWest(true);
                                break;

                            case 2:
                                w.setNorth(true);
                                break;

                            case 3:
                                w.setEast(true);
                                break;

                            default:
                                break; 
                        }
                        if(!w.getVisitedReverse() && !w.getObstacle()) {
                            w.setVisitedReverse(true);
                            points.add(new Point(ty, tx));
                        }
                    }
                }
            }
        } 
    }

    public void quickPlan() {
        quickDFS(srcY, srcX); 
    }

    private boolean quickDFS(int y, int x) {
        if(y == dstY && x == dstX) {
            return true;
        }

        Node v = grid.getNode(y, x);
        v.setVisited(true);

        Point points[] = new Point[4];
        // North, East, South, West ordering for dRow and dCol
        // If all distances were the same prefered order is NWES
        // Smallest row is North, Smallest Column is West

        Point north = new Point(y + dRow[0], x + dCol[0]);
        points[0] = north;

        Point east = new Point(y + dRow[1], x + dCol[1]);
        points[1] = east;

        Point south = new Point(y + dRow[2], x + dCol[2]);
        points[2] = south;

        Point west = new Point(y + dRow[3], x + dCol[3]);
        points[3] = west;

        Arrays.sort(points,
            (Point a, Point b) -> {
                long distA = ((a.y - dstY) * (a.y - dstY)) + ((a.x - dstX) * (a.x - dstX)); // Distance Squared 
                long distB = ((b.y - dstY) * (b.y - dstY)) + ((b.x - dstX) * (b.x - dstX)); // Distbnce Squbred 
                
                int distSign = Long.signum(distA - distB);

                if(distSign == 0) {
                    int rowSign = Integer.signum(a.y - b.y);
                    if(rowSign == 0) {
                        int colSign = Integer.signum(a.x - b.x);
                        return colSign;
                    } 
                    return rowSign;
                }
                return distSign;
            }
        );

        for(int i = 0; i < 4; i++) {
            Point p = points[i];  
            if(p.x >= 0 && p.y >= 0 && p.x < grid.getWidth() && p.y < grid.getHeight()) {
                Node u = grid.getNode(p.y, p.x);
                if(!u.getObstacle() && !u.getVisited()) {
                    if(quickDFS(p.y, p.x)) {
                        if(p == north) {
                            v.setNorth(true);
                        }
                        else if(p == south) {
                            v.setSouth(true);
                        }
                        else if(p == east) {
                            v.setEast(true);
                        }
                        else if(p == west) {
                            v.setWest(true);
                        }
                        return true;
                    }
                } 
            }
        }

        return false;
    }

    public void output() {
        for(int i = 0; i < grid.getHeight(); i++) {
            for(int j = 0; j < grid.getWidth(); j++) {
                Node n = grid.getNode(i, j);
                if(i == srcY && j == srcX){
                    System.out.print("    S");
                }
                else if(i == dstY && j == dstX){
                    System.out.print("    D");
                }
                else {
                    System.out.print(n.toString());
                }
            }
            System.out.println();
        }
        
        grid.clearStates();        
    }
}


class Point {
    int y, x;
     
    public Point(int y, int x) 
    {
        this.y = y;
        this.x = x;
    }   
}


class Node {

    private int layer;
    private boolean obstacle;
    private boolean visited;
    private boolean visitedReverse;
    private boolean south;
    private boolean north;
    private boolean east;
    private boolean west; 

    public Node() {
        layer = -2; 
        obstacle = false;
        visited = false;
        visitedReverse = false;
        south = false;
        north = false;
        east = false;
        west = false;
    }

    // resets states except obstacle
    public void reset() {
        layer = -2; 
        visited = false;
        visitedReverse = false;
        south = false;
        north = false;
        east = false;
        west = false;
    }

    /*
     * Layer Info
     */

    public int getLayer() {
        return layer;
    }

    public void setLayer(int l) {
        layer = l;
    }

    /*
     * Obstacle Info
     */

    public boolean getObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean o) {
        obstacle = o;
    }
 
    /*
     * Visited Info
     */

    public boolean getVisited() {
        return visited;
    }

    public void setVisited(boolean v) {
        visited = v;
    }

    /*
     * Visted Reverse Info
     */

    public boolean getVisitedReverse() {
        return visitedReverse;
    }

    public void setVisitedReverse(boolean v) {
        visitedReverse = v;
    }

    /*
     * Directional Info
     */

    public boolean getSouth() {
        return south;
    }

    public void setSouth(boolean s) {
        south = s;
    }
 
    public boolean getNorth() {
        return north;
    }

    public void setNorth(boolean n) {
        north = n;
    }

    public boolean getEast() {
        return east;
    }

    public void setEast(boolean e) {
        east = e;
    }

    public boolean getWest() {
        return west;
    }

    public void setWest(boolean w) {
        west = w;
    }
    
    @Override
    public String toString() {
        String temp = "";
        String spaces = "";

        if(obstacle) {
            temp = "    *";
        }
        else {
            if(south) {
                temp += "s";
            }
            if(north) {
                temp += "n";
            }
            
            if(west) {
                temp += "w";
            }
            if(east) {
                temp += "e";
            }

            for(int i = 5; i > temp.length(); i--) {
                spaces += " ";
            }

            temp = spaces + temp;

            if(!north && !south && !east && !west) { 
                temp = "    0";
            }
        }
        
        return temp;
    }
}

class Grid {

    private int width;
    private int height;
    private ArrayList<ArrayList<Node>> matrix; // matrix[y][x]

    private static int dRow[] = { -1, 0, 1, 0 };
    private static int dCol[] = { 0, 1, 0, -1 };

    public Grid() {
        width = 0;
        height = 0;
        matrix = new ArrayList<ArrayList<Node>>(); 
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int w) {
        width = w;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int h) {
        height = h;
    }

    public void initialize() {
        matrix = new ArrayList<ArrayList<Node>>();
        for(int i = 0; i < height; i++) {
            ArrayList<Node> row = new ArrayList<Node>();
            for(int j = 0; j < width; j++) {
                row.add(new Node());
            }
            matrix.add(row);
        }
    }

    public void clearStates() {
        for(int i = 0; i < height; i++) {
            for(int j = 0; j < width; j++) {
                Node v = getNode(i, j);
                v.reset();
            }
        }
    }

    public void bfs(int y, int x) {
        LinkedList<Point> points = new LinkedList<Point>();
        Node v = getNode(y, x);
        v.setVisited(true);
        v.setLayer(0);
        points.add(new Point(y, x));
        
        while(!points.isEmpty()) {
            Point p = points.poll();
            Node u = getNode(p.y, p.x);
            for(int i = 0; i < 4; i++) {
                int ty = p.y + dRow[i];
                int tx = p.x + dCol[i];

                if(tx >= 0 && ty >= 0 && tx < width && ty < height) {
                    Node w = getNode(ty, tx);
                    if(!w.getVisited() && !w.getObstacle()) {
                        w.setVisited(true);
                        w.setLayer(u.getLayer() + 1);
                        points.add(new Point(ty, tx));
                    }
                }
            }
        }
    }

    public Node getNode(int y, int x) {
        return matrix.get(y).get(x);
    }
}
