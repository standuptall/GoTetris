/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.util.*;
import java.awt.event.*;
import albe.gotetris.GoTetris;
import java.awt.Color;

/**
 *
 * @author Alberto
 */
public class MyTask extends TimerTask {
    public Random random = new Random();
    public Block block;
    public Block nextBlock;
    @Override
    public void run() {
        try{
            if (GoTetris.grid.currentBlock.getMap()[0][0]); //istruzione senza senso
        }
        catch(Exception e) {
            java.awt.Graphics g = GoTetris.frame.canvas.getGraphics();
            g.setColor(Colori.SFONDO);
            g.fillRect(0, 0, GoTetris.frame.getWidth(),GoTetris.frame.getHeight());
            int ran = random.nextInt(7);
            switch (ran) {
                case 0: {nextBlock = new Block(Grid.I,Color.CYAN);break;}
                case 1: {nextBlock = new Block(Grid.J,Color.BLUE);break;}
                case 2: {nextBlock = new Block(Grid.L,Color.ORANGE);break;}
                case 3: {nextBlock = new Block(Grid.O,Color.YELLOW);break;}
                case 4: {nextBlock = new Block(Grid.S,Color.GREEN);break;}
                case 5: {nextBlock = new Block(Grid.T,Color.MAGENTA);break;}
                case 6: {nextBlock = new Block(Grid.Z,Color.RED);break;}
                default:{nextBlock = new Block(Grid.I,Color.CYAN);break;}
                
            }
            GoTetris.grid.addNextBlock(nextBlock);
        }
        if (!GoTetris.grid.getCurrentBlock().isIncastrato()) 
            GoTetris.grid.down();
        else {
            GoTetris.grid.checkFilledRows();
            Date data = new Date();
            GoTetris.score.printAll();
            random.setSeed(data.getTime());
            int ran = random.nextInt(7);
            block = nextBlock;
            switch (ran) {
                case 0: {nextBlock = new Block(Grid.I,Color.CYAN);break;}
                case 1: {nextBlock = new Block(Grid.J,Color.BLUE);break;}
                case 2: {nextBlock = new Block(Grid.L,Color.ORANGE);break;}
                case 3: {nextBlock = new Block(Grid.O,Color.YELLOW);break;}
                case 4: {nextBlock = new Block(Grid.S,Color.GREEN);break;}
                case 5: {nextBlock = new Block(Grid.T,Color.MAGENTA);break;}
                case 6: {nextBlock = new Block(Grid.Z,Color.RED);break;}
                default:{nextBlock = new Block(Grid.I,Color.CYAN);break;}
                
            }
            GoTetris.frame.canvas.paintNextBlock(nextBlock.getMap(),nextBlock.getColor());
            GoTetris.grid.addNextBlock(block);
            try {
                if (GoTetris.grid.getCurrentBlock().isGameOver()) {
                    System.out.print("gameover");
                    this.cancel();
                }
                    
            }
            catch (Exception e) {
                
            }
            
        }
        
    }

}
