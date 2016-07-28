import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class FailureWindow extends JFrame implements ActionListener{
    JDialog mDalog;
    Label label;
    JButton button;

    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension dim = toolkit.getScreenSize();
    private int screenWidth = dim.width;//获取屏幕的宽
    private int screenHeight = dim.height;//获取屏幕的高
    private int x = (screenWidth - 350)/2;
    private int y = (screenHeight - 150)/2;


    FailureWindow(){
        mDalog = new JDialog(this,"结果",true);
        mDalog.setBounds(x,y,350,150); 
        mDalog.setLayout(new FlowLayout()); 
        label = new Label();
        button = new JButton("确定");
        button.addActionListener(this);
        mDalog.add(label);
        mDalog.add(button);
    }

    public void makeVisible(int grade){
        label.setText("您在此游戏中点击黑块的数量为"+grade+"是否重新开始");
        mDalog.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        Game game  = new Game();
        Game.frame = new SetWindows();
        //重新启动游戏后初始化成绩和线程标记。
        Game.grade = 0;
        Game.flag  = true;
        Game.time  = 1000;
        new Thread(){
            public void run(){
                while(Game.flag){
                    game.down(Game.frame.mBolk);
                    game.creatBlackBolk(Game.frame.mBolk);
                    try {
                        Thread.sleep(Game.time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Game.delay();
                }
            }
        }.start();
        setVisible(false);

        /* Game.thread.start();  */
    }
}
