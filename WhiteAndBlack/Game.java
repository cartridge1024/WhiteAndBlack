import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game implements ActionListener {

    //此处不设置成static类型，会出现计数混乱的bug，有空找出来。
    static int grade = 0;
    //标记子线程的运行状态。
    static boolean flag = true;
    //初始设置的下落时间间隔，延时函数中对该数进行修改，达到控制游戏速度的功能。
    static int time = 1000;
    //为了方便控制界面，此处只显示本类设置游戏主窗体。
    static SetWindows frame;

    //下落函数
    public void down(Bolk[] mBolk){
        for(int i=mBolk.length-1;i>=4;i--){
            mBolk[i].setValue(mBolk[i-4].getValue());
            mBolk[i].set_Color();
        }
        for(int i=0;i<4;i++){
            mBolk[i].setValue(false);
            mBolk[i].set_Color();
        }
    }

    //在最上面的四个按钮中随机产生一个黑块.
    public void creatBlackBolk(Bolk[] mBolk){
        int random_num = (int)(Math.random()*4);
        mBolk[random_num].setValue(true);
        mBolk[random_num].set_Color();
    }

    //判断点击的是否为黑块。
    public boolean isSuccess(Bolk mbolk){
        if( mbolk.getValue()){
            mbolk.setValue(false);
            mbolk.set_Color();
            return true;
        }else{
            return false;
        }
    }
    
    //方块的点击事件监听。
    public void actionPerformed(ActionEvent event){
        //获取被点击的按钮。
        Bolk bolk = (Bolk)event.getSource();
        if(isSuccess(bolk)){
            grade++;
        }else{
            flag = false;
            frame.setVisible(false);  
            FailureWindow failWindow = new FailureWindow();
            failWindow.makeVisible(grade);
        }
    } 
    
    //延时方法，用来控制游戏中方块的下落速度。
    public static void delay(){
        if(time > 500){
            time = time - 5;
        }else if(time > 200){
            time = time - 3;
        }else if(time > 100){
            time = time - 1;
        }else{
        }
    }

    public static void main(String args[]) {
        frame = new SetWindows();
        Game game = new Game();
        new Thread(){
            public void run(){
                while(flag){
                    game.down(frame.mBolk);
                    game.creatBlackBolk(frame.mBolk);
                    try {
                        Thread.sleep(time);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    delay();
                }
            }
        }.start();
/* 
        Thread thread =  new Thread(){
            public void run(){
                while(flag){
                    game.down(frame.mBolk);
                    game.creatBlackBolk(frame.mBolk);
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    delay();
                }
            }
        };
        thread.start();
 */
    }
}
