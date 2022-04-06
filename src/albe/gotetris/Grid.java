/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.awt.*;
import java.util.*;

/**
 *
 * @author Alberto
 */
public class Grid {
    public int numeroRighe = 20;
    public int numeroColonne = 10;
    public int dimQuad = 30;  //dimensione del singolo quadratino
    public int spacing = 5;  //spazio tra un quadrato e un altro
    
    /***********NON MODIFICARE!!!!!!!!MODIFICARE SOLO I VALORI SOPRA ********************/
    public int numRig = numeroRighe;
    public int numCol = numeroColonne;
    public int posSpace = dimQuad + spacing; //numero di pixel che deve percorrere un quadrato per spostarsi
    public int height = posSpace*(numRig -1) + spacing;  //altezza della griglia
    public int weight = posSpace*(numCol -1) + spacing; //larghezza della griglia
    Block currentBlock;
    BlockCollection filledCells = new BlockCollection(numRig,numCol);
    public Color mappaColori[][] = new Color[numRig+1][numCol+1];
    
    public static final boolean[][] I = {{false,true,false,false},{false,true,false,false},{false,true,false,false},{false,true,false,false}};
    public static final boolean[][] O = {{false,false,false,false},{true,true,false,false},{true,true,false,false},{false,false,false,false}};
    public static final boolean[][] J = {{true,true,true,false},{true,false,false,false},{false,false,false,false},{false,false,false,false}};
    public static final boolean[][] L = {{true,true,true,false},{false,false,true,false},{false,false,false,false},{false,false,false,false}};
    public static final boolean[][] S = {{true,true,false,false},{false,true,true,false},{false,false,false,false},{false,false,false,false}};
    public static final boolean[][] T = {{true,true,true,false},{false,true,false,false},{false,false,false,false},{false,false,false,false}};
    public static final boolean[][] Z = {{false,true,true,false},{true,true,false,false},{false,false,false,false},{false,false,false,false}};
    
    public Grid() {
        for (int i=0;i<numRig;i++)
            for (int k=0;i<numRig;i++)
                mappaColori[i][k] = Colori.SFONDO;
    }
    public void addNextBlock(Block block) {
        currentBlock = block;
        GoTetris.frame.canvas.updateScreen(GoTetris.frame.canvas.getGraphics());
    }    
    public void checkFilledRows() {
        for (int i=0;i<numRig+1;i++)
            if (isRowFilled(i)){
                GoTetris.score.rise();
                deleteLine(i);
                i= -1;
            }
                
    }
    public void deleteLine (int rig) {
        for (int i=0;i<numCol;i++) {
            filledCells.voidCell(rig, i);
        }
        upgrade(GoTetris.frame.canvas.getGraphics(),rig);
    }
    public void down () {
        currentBlock.setRig(currentBlock.getRig()-1);
        currentBlock.repaint(currentBlock.getRig()+1, currentBlock.getCol());
    }
    public void fillCells(boolean[][] map, Color color, int rig, int col) {
        for (int i=0;i<4;i++)
            for (int k=0;k<4;k++) {
               if (currentBlock.getMap()[i][k]){
                   filledCells.fillCell(rig+i, col+k);
                   mappaColori[rig+i][col+k] = color;
               }
                    
               if (col>numCol)
                   numCol = numCol;
            }
       
    }
    public Block getCurrentBlock() {
        return currentBlock;
    }
    public BlockCollection getFilledCells() {
        return filledCells;
    }
    public Point getPoint(int rig,int col) {
        if ((rig>numRig)||(col>numCol))
            throw new java.lang.IndexOutOfBoundsException();
        Point punto = new Point(0,0);
        punto.y = height - (rig * posSpace) - posSpace;
        punto.x = (col * posSpace) + spacing + 2;
        return punto;
    }
    
    public boolean isCellFilled(int rig,int col) {
        return filledCells.isCellFilled(rig, col);
    }
    public boolean isRowFilled(int row) {
        int count =0;
        for (int i=0;i<numCol;i++) {
            if (filledCells.isCellFilled(row, i))
                count++;
        }
        if (count==numCol)
            return true;
        else return false;
    }
    public void left() {
        currentBlock.setCol(currentBlock.getCol()-1);
        currentBlock.repaint(currentBlock.getRig(), currentBlock.getCol()+1);
    }
    public void right() {
        currentBlock.setCol(currentBlock.getCol()+1);
        currentBlock.repaint(currentBlock.getRig(), currentBlock.getCol()-1);
    }
    public void rotateRight() {
        boolean[][] newMap = new boolean[4][4];
        boolean[][] oldMap = currentBlock.getMap();
        currentBlock.paintOnly(currentBlock.getRig(), currentBlock.getCol(),Colori.SFONDO);
        for (int i=0;i<4;i++)
            for(int k=0;k<4;k++) {
                newMap[3-i][k] = (currentBlock.getMap()[i][k]);
            }
        currentBlock.setMap(newMap[0][0],newMap[1][0],newMap[2][0],newMap[3][0],
                            newMap[0][1],newMap[1][1],newMap[2][1],newMap[3][1],
                            newMap[0][2],newMap[1][2],newMap[2][2],newMap[3][2],
                            newMap[0][3],newMap[1][3],newMap[2][3],newMap[3][3]);
        if (currentBlock.checkBoundsAfterRotation().equals("sinistra"))
                do {
                    currentBlock.setCol(currentBlock.getCol()-1);
                } while (currentBlock.checkBoundsAfterRotation().equals("sinistra"));
        if (currentBlock.checkBoundsAfterRotation().equals("destra"))
                do {
                    currentBlock.setCol(currentBlock.getCol()+1);
                } while (currentBlock.checkBoundsAfterRotation().equals("destra"));
        if (currentBlock.checkBoundsAfterRotation().equals("forbidden"))
            currentBlock.setMap(oldMap[0][0],oldMap[1][0],oldMap[2][0],oldMap[3][0],
                                oldMap[0][1],oldMap[1][1],oldMap[2][1],oldMap[3][1],
                                oldMap[0][2],oldMap[1][2],oldMap[2][2],oldMap[3][2],
                                oldMap[0][3],oldMap[1][3],oldMap[2][3],oldMap[3][3]);
        currentBlock.paintOnly(currentBlock.getRig(), currentBlock.getCol(),currentBlock.getColor());
    }
    public void rotateLeft() {
        boolean[][] newMap = new boolean[4][4];
        currentBlock.paintOnly(currentBlock.getRig(), currentBlock.getCol(),Colori.SFONDO);
        for (int i=0;i<4;i++)
            for(int k=0;k<4;k++) {
                newMap[i][3-k] = (currentBlock.getMap()[i][k]);
            }
        currentBlock.setMap(newMap[0][0],newMap[1][0],newMap[2][0],newMap[3][0],
                            newMap[0][1],newMap[1][1],newMap[2][1],newMap[3][1],
                            newMap[0][2],newMap[1][2],newMap[2][2],newMap[3][2],
                            newMap[0][3],newMap[1][3],newMap[2][3],newMap[3][3]);
        currentBlock.paintOnly(currentBlock.getRig(), currentBlock.getCol(),currentBlock.getColor());
    }
   
    public void upgrade(Graphics g,int rig) {
        /*QUESTO METODO SCALA TUTTO IL CONTENUTO SE VIENE RIEMPITA UNA RIGA*/
        
        GoTetris.frame.canvas.getColorModel().getRGB(numCol);
        
       
      
        
        for (int i=rig;i<numRig;i++)    
            for (int k=0;k<numCol;k++) {
                    filledCells.editCell(i, k, filledCells.isCellFilled(i+1, k));    //faccio lo shift  nella struttura dei quadrati occupati
                    try {
                        mappaColori[i][k] = mappaColori[i+1][k];                      //faccio lo shift nella mappa dei coloi
                    }
                    catch(Exception e) {

                    }
             }
        
        for (int i=numRig-1;i>=0;i--){         //aggiorno lo schermo
            try{
                Thread.sleep(25);
            }
            catch(Exception e){
                
            }
            
            for (int k=0;k<numCol;k++) {
                if (mappaColori[i][k]==null)
                    g.setColor(Colori.SFONDO);
                else g.setColor(mappaColori[i][k]);
                GoTetris.frame.canvas.paintSquare(g, i, k);
            }
            
        }
                    
               
    }
    public void upgradeScreen(Graphics g) {
        for (int i=numRig-1;i>=0;i--){         
            for (int k=0;k<numCol;k++) {
                if (mappaColori[i][k]==null)
                    g.setColor(Colori.SFONDO);
                else g.setColor(mappaColori[i][k]);
                GoTetris.frame.canvas.paintSquare(g, i, k);
            }
            
        }
    }
}
