/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Debug.java
 *
 * Created on 19-nov-2011, 18.06.39
 */
package albe.gotetris;

/**
 *
 * @author Alberto
 */
public class Debug extends javax.swing.JFrame {
    MyCanvas canvas; 
    DebugTask task = new DebugTask();
    /** Creates new form Debug */
    public Debug() {
        initComponents();
        canvas = new MyCanvas();
        canvas.setSize(GoTetris.grid.weight+(GoTetris.grid.posSpace*10), GoTetris.grid.height+GoTetris.grid.posSpace);
        this.add(canvas);
        this.pack();
        this.setSize(GoTetris.grid.weight+(GoTetris.grid.posSpace*10)+10, GoTetris.grid.height+GoTetris.grid.posSpace+10);
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Debug().setVisible(true);
            }
        });
    }
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        task.start();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}