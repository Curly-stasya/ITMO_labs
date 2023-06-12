import javax.swing.*;
import java.awt.*;

public class Tile extends JButton {
    private static final Color Tile_COLOR = new Color(255, 233, 227, 255);
    private static final Color Text_COLOR = new Color(255, 133, 105, 255);
    private int location;

    public int getLocation_(){
        return location;
    }
    public void setLocation_(int loc){
        location=loc;
    }

    public Tile() {//используется для свободной клетки
        setVisible(false);
    }
    public Tile(int N){//создание кнопки
        //добавление текста, его форматирование
        //запоминание ее расположения
        setText(Integer.toString(N)); // цифры
        setFont(new Font("Arial", Font.PLAIN, Game.windowSize *5/32));//размер шрифта составляет 5/8 высоты костяшки
        setBackground(Tile_COLOR);
        setForeground(Text_COLOR);
        location = N - 1;
    }

}