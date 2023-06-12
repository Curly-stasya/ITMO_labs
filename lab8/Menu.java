import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

class Menu extends JMenuBar implements ActionListener {
    //наследуемся от JMenuBar тк это меню верхнего уровня - контейнер для меню
    //заполняем контейнер пунктами меню JMenu - File, Help
    //Для каждого пункта вводим мнемоники через setMnemonic
    //В меню File - выпадающее меню New и Exit (JMenuItem) + разделитель(JSeparator)
    //В меню Help - выпадающее меню About (JMenuItem)
    //для каждого пункта выпадающего меню регистрируем источник -  вызываем addActionListener();
    //обработку событий реализуем в переопределенном методе actionPerformed
    private static final Font font = new Font("Arial", Font.PLAIN, 16);
    private final PlayingField field;
    private final Game game;

    public Menu(Game game, PlayingField field ) { // горячие клавиши и меню
        this.game= game;
        this.field=field;
        //----------------основное меню-------------------//
        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        //добавляем
        this.add(file);
        this.add(help);
        //устанавливаем шрифт
        file.setFont(font);
        help.setFont(font);
        //устанавливаем мнемоники
        file.setMnemonic(KeyEvent.VK_F);// alt + F
        help.setMnemonic(KeyEvent.VK_H);// alt + H

        //----------------выпадающее меню-------------

        JMenuItem new_ = new JMenuItem("New"); //Новая игра, перемешивание костяшек
        JMenuItem exit = new JMenuItem("Exit"); //Выход, закрытие окна, конец программы
        JMenuItem about = new JMenuItem("About"); //Информация обо мне любимой

        //добавляем выпадающее меню в нужные вкладки основного
        file.add(new_);
        file.addSeparator();
        file.add(exit);
        help.add(about);

        //устанавливаем шрифт
        new_.setFont(font);
        exit.setFont(font);
        about.setFont(font);

        //добавляем горячие клавиши (акселераторы)
        new_.setMnemonic(KeyEvent.VK_N);
        exit.setMnemonic(KeyEvent.VK_E);
        about.setMnemonic(KeyEvent.VK_A);
        new_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));//Shift + N
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));//Shift + E
        about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK));//Shift + A

        //регистрируем приемники
        new_.addActionListener(this);
        exit.addActionListener(this);
        about.addActionListener(this);

    }
    public void actionPerformed(ActionEvent event) { // обработка событий
        String commannd = event.getActionCommand();
        switch (commannd) {
            case ("New")://новая игра- обновление поля
                field.mix();
                field.reset();
                break;
            case ("Exit")://закрытие программы
                System.exit(0);
                break;
            case ("About")://вывод диалогового окна JOptionPane.showMessageDialog с инфой об авторе
                JPanel dialogPanel= new JPanel();
                JLabel text = new JLabel("Смирнягина Настасья Р3168 2023");
                text.setFont(font);
                dialogPanel.add(text);
                dialogPanel.add(new JLabel(new ImageIcon("res/star.png")));//TODO найти щеночка
                dialogPanel.setVisible(true);
                JOptionPane.showMessageDialog(game, dialogPanel, "About author", JOptionPane.PLAIN_MESSAGE);
                break;
        }
    }
}
