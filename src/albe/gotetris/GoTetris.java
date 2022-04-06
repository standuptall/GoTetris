/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;
import java.awt.event.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
/**
 *
 * @author Alberto
 */
public class GoTetris {
    public static Grid grid = new Grid();
    public static Frame frame= new Frame();
    public static int lag = 500;
    public static Score score = new Score();
    public static Timer timer = new Timer();
    public static TimerTask task = new MyTask();
    public GoTetris() {
        
    }
    
    
    public static void main(String[] args) {
        
        frame.setVisible(true);
        timer.schedule( task, 1000, lag);
        task.run();
    }
}
