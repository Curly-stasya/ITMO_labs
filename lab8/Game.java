import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends JFrame {
    public final static int windowSize=600;

    public Game(){
        setSize(windowSize, windowSize+20);//надбавка на меню
        setResizable(false);//запрещаем менять размер окна
        setTitle("Tag Game");
        setLocationRelativeTo(null);//располагаем по центру
        setDefaultCloseOperation(EXIT_ON_CLOSE);//программа завершает работу после закрытия окна

        PlayingField field = new PlayingField(this);
        field.setFocusable(true);
        setJMenuBar(new Menu(this, field));//добавляю панель меню
        getContentPane().add(field);//добавляю игровое поле

        setVisible(true);
    }


    public static void main(String[] args) {
        new Game();
    }
}

