/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.awt.*;

/**
 *
 * @author Alberto
 */
public class Block {
    public Block(boolean[][] map,java.awt.Color fcolor) {
        rig = GoTetris.grid.numRig-2;
        col = 0;
        this.colore = fcolor;
        this.map = map;
    }
    public boolean checkBounds() {
        for(int i=0;i<4;i++)
            for (int k=0;k<4;k++) {
                try {
                   if (map[i][k]) {
                       if ((col+k>(GoTetris.grid.numCol-1))||
                           (col+k<0)   )
                           return true;
                       /*if (rig+i==GoTetris.grid.numRig) {
                           gameOver = true;         //BUG
                           return false;
                         }
                        * 
                        */
                       
                       if ((rig+i<0)||(GoTetris.grid.isCellFilled(rig+i,col+k))) {
                           incastrato = true;
                           return false;    
                       }
                   }
                }
                catch(Exception e) {
                    
                }
            }
        return false;
    }
    public String checkBoundsAfterRotation() {
        for(int i=0;i<4;i++)
            for (int k=0;k<4;k++) {
                try {
                   if (map[i][k]) {
                       if (col+k>GoTetris.grid.numCol)
                           return "sinistra";
                       if (col+k<0)
                           return "destra";
                       if ((rig+i<0)||(GoTetris.grid.isCellFilled(rig+i,col+k))) {
                           return "forbidden";
                       }
                   }
                }
                catch(Exception e) {
                    
                }
            }
        return "ok";
    }
   
    
    public boolean[][] getMap(){
        return map;
    }
    public int getRig() {
        return rig;
    }
    public int getCol(){
        return col;
    }
    public Color getColor(){
        return colore;
    }
    public boolean isIncastrato() {
        return incastrato;
    }
    public boolean isGameOver() {
        return gameOver;
    }
    public void paintOnly (int rig, int col, Color color) {
        for(int i=0;i<4;i++)                       //disegna la nuova posizione
            for (int k=0;k<4;k++) {
                try {
                   if (map[i][k]) {
                       Graphics g = GoTetris.frame.canvas.getGraphics();
                       g.setColor(color);
                       GoTetris.frame.canvas.paintSquare(g, rig+i, col+k);
                   }
                       
                }
                catch(Exception e ) {
                    
                }
            }
    }
    public void repaint(int oldRig, int oldCol) {
        if (checkBounds()) {                   //controlla se con i tasti muovo oltre i limiti della griglia
            rig = oldRig;
            col = oldCol;
            return;
        }
        if (incastrato) {                        //controlla se l'oggetto è arrivato a terra
            rig = oldRig;
            col = oldCol;
            GoTetris.grid.fillCells(this.map, this.colore, oldRig, oldCol);
            return;
        }                                       //cancella la vecchia posizione
        if (gameOver)
            return;
        for(int i=0;i<4;i++)
            for (int k=0;k<4;k++) {
                try {
                   if (map[i][k]) {
                       Graphics g = GoTetris.frame.canvas.getGraphics();
                       g.setColor(Colori.SFONDO);
                       GoTetris.frame.canvas.paintSquare(g, oldRig+i, oldCol+k);
                   }
                       
                }
                catch(Exception e ) {
                    
                }
            }
        for(int i=0;i<4;i++)                       //disegna la nuova posizione
            for (int k=0;k<4;k++) {
                try {
                   if (map[i][k]) {
                       Graphics g = GoTetris.frame.canvas.getGraphics();
                       g.setColor(this.colore);
                       GoTetris.frame.canvas.paintSquare(g, rig+i, col+k);
                   }
                       
                }
                catch(Exception e ) {
                    
                }
            }
            
               
    }
    
    public void setMap(boolean aa,boolean ab,boolean ac,boolean ad,
                       boolean ba,boolean bb,boolean bc,boolean bd,
                       boolean ca,boolean cb,boolean cc,boolean cd,
                       boolean da,boolean db,boolean dc,boolean dd) {
        map[0][0] = aa; map[1][0] = ba; map[2][0] = ca;  map[3][0] = da;
        map[0][1] = ab; map[1][1] = bb; map[2][1] = cb;  map[3][1] = db;
        map[0][2] = ac; map[1][2] = bc; map[2][2] = cc;  map[3][2] = dc;
        map[0][3] = ad; map[1][3] = bd; map[2][3] = cd;  map[3][3] = dd;
    }
    public void setRig(int rig) {
        this.rig = rig;
    }
    public void setCol(int col){
        this.col = col;
    }
    private int rig,col;
    private java.awt.Color colore;
    private boolean map[][] = new boolean[4][4];
    private boolean incastrato = false;
    private boolean gameOver = false;
}
