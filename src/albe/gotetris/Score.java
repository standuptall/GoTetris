/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package albe.gotetris;

/**
 *
 * @author Alberto
 */
public class Score {
    public Score() {
        this.punteggio = 0;
        this.level = 1;
    }
    public int getLevel() {
        return level;
    }
    public void rise() {
        int oldLevel = level;
        punteggio+=level*100;
        level = (punteggio/1000)+1;
        GoTetris.frame.canvas.printScore(level, punteggio);
        if (oldLevel<level)
            schedule();
    }
    public void schedule() {
        GoTetris.lag = 500-(50*(level-1));
        GoTetris.timer.cancel();
        GoTetris.task = new MyTask();
        GoTetris.timer = new java.util.Timer();
        GoTetris.timer.schedule( GoTetris.task, 1000, GoTetris.lag);
        GoTetris.frame.canvas.updateScreen(GoTetris.frame.canvas.getGraphics());
    }
    public void printAll() {
        GoTetris.frame.canvas.printScore(level, punteggio);
    }
    private int punteggio;
    private int level;
}
