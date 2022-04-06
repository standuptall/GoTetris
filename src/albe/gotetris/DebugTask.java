/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.awt.Color;
import java.util.*;
/**
 *
 * @author Alberto
 */
public class DebugTask extends java.lang.Thread {
    @Override
    public void run() {
        while(true) {
            for (int i=0;i<GoTetris.grid.numRig;i++)
                for (int k=0;k<GoTetris.grid.numCol;k++){
                    if (GoTetris.grid.getFilledCells().isCellFilled(i, k)){
                        GoTetris.frame.debug.canvas.paintSquare(GoTetris.frame.debug.canvas.getGraphics(), i, k);
                    }
                        
                    else {
                        GoTetris.frame.debug.canvas.deleteSquare(GoTetris.frame.debug.canvas.getGraphics(), i, k);
                    }
                }
                    
        }
    }
}
