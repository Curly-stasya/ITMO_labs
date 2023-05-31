import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends JFrame {
    private int windowSize;
    private final PlayingField field;
    private final Game game;
    private static final Color Background_COLOR = new Color(255, 205, 193,255);
    private static final Color Tile_COLOR = new Color(255, 233, 227, 255);
    private static final Color Text_COLOR = new Color(255, 133, 105, 255);
    private static final Font font = new Font("Arial", Font.PLAIN, 16);


    private class PlayingField extends JPanel {

        //размер окна
        //массив костяшек
        //позиция пустой костяшки
        //Для расположения костяшек используется менеджер компоновки GridLayout
            //поэтому размер костяшки мне не важен, он определяется автоматически



        //private static final Color FOREGROUND_COLOR = new Color(80, 182, 125);

        private Tile[] tiles = new Tile[16];
        private int blankPos;
        //------------------------------------------------

        public PlayingField(){
            //задаю компоновку
            //игровое поле - массив кнопок с текстом поверх
            //в цикле 1-15 (те все кроме последней)
                //инициализирую массив костяшек значениями на 1 больше(те с 1)
                //регистрирую приемник кликов мышью
                //регистрирую приемник клавиатуры
            //последнюю костяшку- дырку инициализирую отдельно, у нее нет никакого значения
            //перемешивание mix()
            setLayout(new GridLayout(4, 4, 4,4));
            setBackground(Background_COLOR);
            for(int i=0; i<15; i++){
                tiles[i]=new Tile(i+1);
                tiles[i].addActionListener(new MouseClick());
                tiles[i].setFocusable(false);
                this.add(tiles[i]);
            }
            tiles[15]=new Tile();
            tiles[15].location=15;
            blankPos=15;
            gameOver();
            mix();
            reset();

        }
        //перемешивание пятнашек
        private void mix(){
            swap(15);//помещаю пустую костяшку на последнюю позицию
            Random random=new Random();
            int mixer;
            Tile tmp;
            for(int i=0; i<15;i++){//перемешиваю все костяшки кроме последней
                mixer=random.nextInt(15);
                swap(i,mixer);
            }
        }

        private void reset() //сброс костяшек. очищение и перерисовка
        {
            removeAll();//очищаем
            for (int i = 0; i < 16; i++) {
                this.add(tiles[i]);
                tiles[i].location = i;
            }
            revalidate();//помечаем добавленные компоненты как некорректные чтобы в дальнейшем их отрисовать заново
            //repaint();
            gameOver();
            //System.out.println("reset ending");

        }

        private void swap(int index){//передаю индекс костяшки, которая будет меняться с пустой
            Tile tmp=tiles[blankPos];

            tiles[blankPos]=tiles[index];//на месте пустой теперь переданная
            tiles[index]=tmp;//на месте переданной пустая

            tiles[index].location=blankPos;
            tiles[blankPos].location=index;
            blankPos=index;
            //reset();


        }

        private void swap(int A, int B){//передаю индекс костяшки, которая будет меняться с пустой
            Tile tmp=tiles[B];

            tiles[B]=tiles[A];//на месте A теперь B
            tiles[A]=tmp;//на месте B теперь A

            tiles[B].location=A;
            tiles[A].location=B;

            //reset();
        }

        public void gameOver(){
            if (blankPos!=15) return;
            for(int i=0; i<15; i++){
                if(!tiles[i].getText().equals(Integer.toString(i+1))) {
                    System.out.println(tiles[i] + " " + (i + 1));
                    return;
                }
            }
            JPanel greetPanel= new JPanel();
            greetPanel.setLayout(new GridLayout(2,1,1,1));
            JLabel text = new JLabel("Вы выиграли!");
            text.setFont(font);
            text.setHorizontalAlignment(JLabel.CENTER);
            greetPanel.add(text);
            greetPanel.add(new JLabel(new ImageIcon("res/win.png")));
            greetPanel.setVisible(true);
            JOptionPane.showMessageDialog(game, greetPanel, "Congratulations", -1);

            mix();
        }



    }
    private class Menu extends JMenuBar implements ActionListener{
        //наследуемся от JMenuBar тк это меню верхнего уровня - контейнер для меню
        //заполняем контейнер this пунктами меню JMenu - File, Help
            //Для каждого пункта вводим мнемоники через setMnemonic
            //В меню File - выпадающее меню New, Exit (JMenuItem), разделитель(JSeparator)
            //В меню Help - выпадающее меню About (JMenuItem)
            //для каждого пункта выпадающего меню регистрируем источник -  вызываем addActionListener(this);
                //обработку событий реализуем в переопределенном методе actionPerformed

        public Menu() { // горячие клавиши и меню

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

            //добавляем мнемоники и акселераторы
            new_.setMnemonic(KeyEvent.VK_N);
            exit.setMnemonic(KeyEvent.VK_E);
            about.setMnemonic(KeyEvent.VK_A);
            new_.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.SHIFT_DOWN_MASK));//Shift + N
            exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK));//Shift + E
            about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.SHIFT_DOWN_MASK));//Shift + A

            new_.addActionListener(this);
            exit.addActionListener(this);
            about.addActionListener(this);

        }
        public void actionPerformed(ActionEvent event) { // обработка событий
            String commannd = event.getActionCommand();
            switch (commannd) {
                case ("New"):
                    //field.change(15);
                    field.mix();
                    field.reset();
                    break;
                case ("Exit"):
                    System.exit(0);
                    break;
                case ("About"):
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

    private class Tile extends JButton{
        private int location;

        public Tile() {//используется для свободной клетки

            setVisible(false);
        }
        private Tile(int N){
            //создание кнопки
            //добавление текста, его форматирование
            //запоминание ее расположения
            setText(Integer.toString(N)); // цифры
            setFont(new Font("Arial", Font.PLAIN, windowSize*5/32));// 5/8 высоты костяшки
            setBackground(Tile_COLOR);
            setForeground(Text_COLOR);
            location = N - 1;
            //setBorderPainted(false);
        }
    }


    //Обработка событий

    private class KeyBoard extends KeyAdapter{//обработка нажатия стрелочек
        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case(KeyEvent.VK_UP):
                    if(field.blankPos<12){//пустая ячейка не в нижнем ряду
                        field.swap(field.blankPos+4);//меняю пустую с костяшкой под ней
                        System.out.println("вверх");
                    }
                    break;
                case(KeyEvent.VK_DOWN):
                    if(field.blankPos>3){//пустая ячейа не в верхнем ряду
                        field.swap(field.blankPos-4);//меняю пустую с костяшкой под ней
                        System.out.println("вниз");
                    }
                    break;
                case(KeyEvent.VK_RIGHT):
                    if(field.blankPos%4!=0){//пустая ячейка не в крайнем левом столбце
                        field.swap(field.blankPos-1);//меняю пустую с костяшкой слева
                        System.out.println("вправо");
                    }
                    break;
                case(KeyEvent.VK_LEFT):
                    if(field.blankPos%4!=3){//пустая ячейка не в правом крайнем столбце
                        field.swap(field.blankPos+1);//меняю пустую с костяшкой справа
                        System.out.println("влево");
                    }
                    break;
            }
            field.reset();//сбрасываем и отправляем на перерисовку
            //super.keyPressed(e);
        }
    }

    private class MouseClick implements ActionListener {//обработка киков мышью

        @Override
        public void actionPerformed(ActionEvent e) {
            Tile currentTile = (Tile)e.getSource();//определяем на какую конкретно костяшку нажали
            //System.out.println("Нажали на "+ currentTile.location+ ", пустая на "+ field.blankPos);
            if(currentTile.location==field.blankPos-1 ||
                    currentTile.location==field.blankPos+1 ||
                    currentTile.location==field.blankPos+4 ||
                    currentTile.location==field.blankPos-4){//если кликнули на костяшку слева, справа, снизу ли сверху от пустой
                field.swap(currentTile.location);
            }
            field.reset();
        }
    }

    public Game(){
        game=this;
        windowSize=600;
        setSize(windowSize, windowSize+20);
        //setLocation((dim.width - width) / 2, height / 2);
        setResizable(false);
        setTitle("GameOfFifteen");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setJMenuBar(new Menu());
        field=new PlayingField();
        getContentPane().add(field);

        addKeyListener(new KeyBoard());

        setFocusable(true);
        setVisible(true);
    }

    public static void main(String[] args) {

        new Game();
    }


}

