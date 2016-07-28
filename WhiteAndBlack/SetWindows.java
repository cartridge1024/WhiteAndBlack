import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SetWindows extends JFrame {
    Bolk[] mBolk = new Bolk[16];
    JPanel panel;

    public SetWindows(){
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setLayout(new GridLayout(4,4,4,4));
        for(int i=0;i<mBolk.length;i++){
            mBolk[i] = new Bolk();
            mBolk[i].addActionListener(new Game());
            panel.add(mBolk[i]);
        }
        add(BorderLayout.CENTER,panel);
        setSize(400,400);
        WindowUtils.displayOnDesktopCenter(this);
        setVisible(true); 
    }
    
    public static void main(String args[]) {
        SetWindows frame = new SetWindows();
        Game game = new Game();
        new Thread(){
            public void run(){
                while(true){
                    game.down(frame.mBolk);
                    game.creatBlackBolk(frame.mBolk);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
