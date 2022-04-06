/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
/**
 *
 * @author Alberto
 */
public class BlockCollection  {
    private boolean[][] struttura;
    private int numRig,numCol;
    public BlockCollection(int numRig,int numCol) {
        this.numRig = numRig;
        this.numCol = numCol;
        struttura = new boolean[numRig+1][numCol+1];
    }
    public void editCell(int rig, int col, boolean value) {
        struttura[rig][col] = value;
    }
    public void fillCell (int rig, int col) {
        struttura[rig][col] = true;
        
    }
    public boolean isCellFilled(int rig,int col) {
        try {
            return struttura[rig][col];
        }
        catch (Exception e) {
            return false;
        }
        
        
    }
    public int size() {
        int count = 0;
        for (int i=0; i<numRig;i++)
            for (int k=0;k<numCol;k++)
                if (struttura[i][k])
                  count++;
        return count;
    }
    public void voidCell (int rig, int col) {
        struttura[rig][col] = false;
    }
}
