/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.awt.*;
import java.util.*;
import albe.gotetris.GoTetris;

/**
 *
 * @author Alberto
 */
public class MyCanvas extends Canvas {
    private Point punto = new Point(0,0);
    private boolean sfondo = false;
    public  MyCanvas() {
        super();
    }
    @Override
    public void paint(Graphics g) {
            super.paint(g);
            sfondo = false;   
            paints(g);
    }
    public void paints(Graphics g) {
            this.getGraphics().setColor(Colori.SFONDO);
            this.getGraphics().fillRect(1, 1, GoTetris.grid.weight+GoTetris.grid.posSpace-GoTetris.grid.spacing,GoTetris.grid.height);
            //g.fillRect(punto.x, punto.y, GoTetris.grid.dimQuad, GoTetris.grid.dimQuad);  
            //this.getGraphics().drawRect(1, 1, GoTetris.grid.weight+GoTetris.grid.posSpace-GoTetris.grid.spacing, GoTetris.grid.height-1);
            //this.getGraphics().drawRect(GoTetris.grid.weight+GoTetris.grid.posSpace+GoTetris.grid.spacing, 1, 8*GoTetris.grid.posSpace, 8*GoTetris.grid.posSpace);
            
    }
    public void deletePaint(Graphics g) {
            g.setColor(Colori.SFONDO);
            g.fillRect(punto.x, punto.y, GoTetris.grid.dimQuad, GoTetris.grid.dimQuad); 
            g.setColor(Colori.SFONDO);
            g.drawRect(1, 1, GoTetris.grid.weight+GoTetris.grid.posSpace-GoTetris.grid.spacing, GoTetris.grid.height-1);
            g.drawRect(GoTetris.grid.weight+GoTetris.grid.posSpace+GoTetris.grid.spacing, 1, 8*GoTetris.grid.posSpace, 8*GoTetris.grid.posSpace);
            
    }
    
    public void paintNextBlock(boolean[][] map, Color color) {
        Graphics g = this.getGraphics();
        punto.x = GoTetris.grid.weight+GoTetris.grid.posSpace*3;
        punto.y = GoTetris.grid.posSpace*2;
        g.setColor(Colori.SFONDO);   //Cancella il precedente
        g.fillRect(punto.x, punto.y , GoTetris.grid.dimQuad*5, GoTetris.grid.dimQuad*5);
        g.setColor(color);
        for (int i=0;i<4;i++) 
            for (int k=0;k<4;k++) {
                if (map[i][k]) 
                    g.fillRect(punto.x + i*GoTetris.grid.posSpace, punto.y + k*GoTetris.grid.posSpace, GoTetris.grid.dimQuad, GoTetris.grid.dimQuad);
            }
        
    }
    public void printScore(int level, int score) {
        Graphics g = this.getGraphics();
        punto.x = GoTetris.grid.weight+GoTetris.grid.posSpace;
        punto.y = GoTetris.grid.posSpace*2+300;
        g.setColor(Colori.SFONDO);
        g.fillRect(punto.x, punto.y-30, 250, 30);
        String leve = String.valueOf(level);
        String scor = String.valueOf(score);
        Font font3 = new Font("SansSerif", Font.PLAIN,  20);
        g.setFont(font3);
        g.setColor(Colori.TESTO);
        g.drawString("LEVEL: "+leve, punto.x, punto.y);
        g.drawString("SCORE: "+scor, punto.x+100, punto.y);
    }
    public void paintSquare(Graphics g,int rig, int col) {
        punto = GoTetris.grid.getPoint(rig, col);
        g.fillRect(punto.x, punto.y, GoTetris.grid.dimQuad, GoTetris.grid.dimQuad);
        //paints(g);
    }
    public void deleteSquare(Graphics g,int rig, int col) {
        punto = GoTetris.grid.getPoint(rig, col);
        deletePaint(g);
    }
    public void updateScreen(Graphics g) {
        paints(g);
        GoTetris.grid.upgradeScreen(g);
    }
}
