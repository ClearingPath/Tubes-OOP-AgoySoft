import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import javax.swing.ImageIcon;

import javax.swing.JPanel;

/**
 *
 * @author Rakhmatullah
 */
public class WelcomeScreen extends JPanel {
    
    /**
     * Creates new form WelcomeScreen
     */
    public WelcomeScreen() {
        initComponents();
        setSize(700, 700);
	setBackground(Color.white);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        QuitButton = new javax.swing.JButton();
        CreditsButton = new javax.swing.JButton();
        HelpButton = new javax.swing.JButton();
        HighScoreButton = new javax.swing.JButton();
        StartButton = new javax.swing.JButton();

        QuitButton.setText("Quit");
        QuitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuitButtonMouseClicked(evt);
            }
        });

        CreditsButton.setText("Credits");
        CreditsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CreditsButtonMouseClicked(evt);
            }
        });

        HelpButton.setText("How to Play?");
        HelpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HelpButtonMouseClicked(evt);
            }
        });

        HighScoreButton.setText("High Score");
        HighScoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HighScoreButtonMouseClicked(evt);
            }
        });

        StartButton.setText("Start Game");
        StartButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StartButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(278, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(HelpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(HighScoreButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CreditsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(QuitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(139, Short.MAX_VALUE)
                .addComponent(StartButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HighScoreButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(HelpButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreditsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QuitButton)
                .addGap(22, 22, 22))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void StartButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StartButtonMouseClicked
    	Game.ChangeState(Utilities.StateType.StartScreen);
    	//Game.ChangeState(Utilities.StateType.Playing);
        //setVisible(false);
        //Game.frame.remove(this);
        //Game.play.setVisible(true);
        //Game.frame.add(Game.play);
    }//GEN-LAST:event_StartButtonMouseClicked

    private void QuitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuitButtonMouseClicked
        Game.ChangeState(Utilities.StateType.Quit);
    	//Game.frame.dispose();
    }//GEN-LAST:event_QuitButtonMouseClicked

    private void HighScoreButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HighScoreButtonMouseClicked
        Game.ChangeState(Utilities.StateType.HighScore);
        //setVisible(false);
        //Game.frame.remove(this);
        //Game.topplayer.setVisible(true);
        //Game.frame.add(Game.topplayer);
    }//GEN-LAST:event_HighScoreButtonMouseClicked

    private void HelpButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HelpButtonMouseClicked
        Game.ChangeState(Utilities.StateType.Help);
        //setVisible(false);
        //Game.frame.remove(this);
        //Game.help.setVisible(true);
        //Game.frame.add(Game.help);
    }//GEN-LAST:event_HelpButtonMouseClicked

    private void CreditsButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CreditsButtonMouseClicked
        Game.ChangeState(Utilities.StateType.Credits);
        //setVisible(false);
        //Game.frame.remove(this);
        //Game.credits.setVisible(true);
        //Game.frame.add(Game.credits);
    }//GEN-LAST:event_CreditsButtonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreditsButton;
    private javax.swing.JButton HelpButton;
    private javax.swing.JButton HighScoreButton;
    private javax.swing.JButton QuitButton;
    private javax.swing.JButton StartButton;
    // End of variables declaration//GEN-END:variables
    /*
    String path="/img/mainmenu.png";
    private static final Color BACKGROUND      = Color.black;
    private static final Color BACKGROUND_2    = Color.WHITE;
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();        
        int midY = 100;
        Paint topPaint = new GradientPaint(0, 0, BACKGROUND,0, midY, BACKGROUND_2);
        graphics.setPaint(topPaint);
        graphics.fillRect(0, 0, getWidth(), midY);        
        Paint bottomPaint = new GradientPaint(0, midY + 1, BACKGROUND_2,0, getHeight(), BACKGROUND);
        graphics.setPaint(bottomPaint);
        graphics.fillRect(0, midY, getWidth(), getHeight());
        Image img = new ImageIcon(getClass().getResource(path)).getImage();
        int imgX = img.getWidth(null);
        int imgY = img.getHeight(null);
        graphics.drawImage(img, (getWidth() - imgX) / 2, (getHeight() - imgY) / 2, imgX, imgY, null);
      //  graphics.dispose();
    }*/

}
