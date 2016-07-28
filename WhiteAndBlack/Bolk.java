/**
 *本类制作方格按钮，通过value属性来确定方格的颜色
 *value属性默认为false，颜色默认为white
 *
 * */
import java.awt.Color;
import javax.swing.JButton;

public class Bolk extends JButton{

    private boolean value = false;


    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public void set_Color(){
        if(value){
            this.setBackground(Color.BLACK);
        }else{
            this.setBackground(Color.WHITE);
        }
    }
}
