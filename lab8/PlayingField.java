import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayingField extends JPanel {
    //игра
    //поле - массив костяшек
    //позиция пустой костяшки
    //шрифт,цвета
    //Для расположения костяшек используется менеджер компоновки GridLayout
        //поэтому размер костяшки мне не важен, он определяется автоматически
    private static final Color Background_COLOR = new Color(255, 205, 193,255);
    private static final Font font = new Font("Arial", Font.PLAIN, 16);
    private final Game game;
    private final Tile[] tiles = new Tile[16];
    private int blankPos;

    public int getBlankPos(){
        return blankPos;
    }

    //------------------------------------------------
    public PlayingField(Game game){
        //задаю компоновку GridLayout
        //игровое поле - массив кнопок с текстом поверх
        //в цикле 1-15 (те все кроме последней) инициализирую костяшки значениями на 1 больше(нумерация костяшек начинается с 1)
        //регистрирую приемник кликов мышью addActionListener(new MouseClick(this))
        //последнюю костяшку-дырку инициализирую отдельно через дефолтный конструктор(она не видима)
        //перемешиваю mix()
        //сбрасываю и заново отрисовываю reset()

        this.game=game;
        //располагаю костяшки в сетке 4*4 с расстоянием в 4 по вертикали и горизонтали
        setLayout(new GridLayout(4, 4, 4,4));
        setBackground(Background_COLOR);
        for(int i=0; i<15; i++){
            tiles[i]=new Tile(i+1);
            tiles[i].addActionListener(new MouseClick());
            tiles[i].setFocusable(false);
            this.add(tiles[i]);
        }
        tiles[15]=new Tile();
        tiles[15].setLocation_(15);
        blankPos=15;
        //gameOver();//проверяем не сложилась ли выигрышная позиция
        mix();
        reset();
        setFocusable(true);
        addKeyListener(new KeyBoard());
    }
    //перемешивание пятнашек
    public void mix(){
        //фиксирую пустую на нужном месте
        //тасую все остальные

        swap(15);//помещаю пустую костяшку на последнюю позицию (правый нижний)
        Random random=new Random();
        int mixer;
        for(int i=0; i<15;i++){//перемешиваю все костяшки кроме последней
            mixer=random.nextInt(15);
            swap(i,mixer);//меняю костяшку местами с рандомной
        }
    }

    public void reset(){ //сброс костяшек. очищение и перерисовка
        //удаляем все с фрейма
        //в цикле добавляем обратно все костяшки
        //обновляем значения их позиций
        //помечаем добавленные компоненты некорректные чтобы отрисовать заново
        //проверяем не сложились ли пятнашки

        removeAll();//очищаем фрейм
        for (int i = 0; i < 16; i++) {
            this.add(tiles[i]);
            tiles[i].setLocation_(i);
        }
        revalidate();//помечаем добавленные компоненты как некорректные чтобы в дальнейшем их отрисовать заново
        gameOver();//проверяем не сложилась ли выигрышная позиция

    }

    //обмен пустой с любой другой
    public void swap(int index){//передаю индекс костяшки, которая будет меняться с пустой

        //меняю местами сами костяшки
        //меняю и их позиции
        //обновляю значение позиции пустой

        Tile tmp=tiles[blankPos];

        tiles[blankPos]=tiles[index];//на месте пустой теперь переданная
        tiles[index]=tmp;//на месте переданной пустая

        tiles[index].setLocation_(blankPos);
        tiles[blankPos].setLocation_(index);
        blankPos=index;
    }

    //обмен двух костяшек местами - только для перемешивания
    public void swap(int A, int B){//передаю индексы костяшек, которые будут меняться местами

        //меняю костяшки
        //меняю их позиции

        Tile tmp=tiles[B];

        tiles[B]=tiles[A];//на месте A теперь B
        tiles[A]=tmp;//на месте B теперь A

        tiles[B].setLocation_(A);
        tiles[A].setLocation_(B);
    }

    public void gameOver(){//проверка текущего состояния массива костяшек на выигрышность

        //убеждаюсь что пустая на пояледней позиции массива
        //В цикле проверяю каждую костяшку
            //номер на костяшке (текст на кнопке) должен отличаться от ее позиции в массиве ровно на 1 (массив начинается с 0, а нумерация с 1)
        //работа с диалоговым окном
            //создаю панель, на нее добавляю текст и картинку

        //вовожу окно с сообщением о победе JOptionPane.showMessageDialog()
        //составляю новую игру- перемешиваю костяшки

        if (blankPos!=15) return;
        for(int i=0; i<15; i++){
            if(!tiles[i].getText().equals(Integer.toString(i+1))) {
                return;
            }
        }
        //диалоговое окно с сообщением о победе
        JPanel greetPanel= new JPanel();
        greetPanel.setLayout(new GridLayout(2,1,1,1));
        JLabel text = new JLabel("Вы выиграли!");
        text.setFont(font);
        text.setHorizontalAlignment(JLabel.CENTER);
        greetPanel.add(text);
        greetPanel.add(new JLabel(new ImageIcon("res/win.png")));
        greetPanel.setVisible(true);
        JOptionPane.showMessageDialog(game, greetPanel, "Congratulations", JOptionPane.PLAIN_MESSAGE);

        //перемешиваю костяшки для дальнейшего продолжения игры
        mix();
    }
    //Обработка событий клавиатуры
    private class KeyBoard extends KeyAdapter {//обработка нажатия стрелочек
        PlayingField field= PlayingField.this;

        @Override
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            switch (key){
                case(KeyEvent.VK_UP):
                    if(field.getBlankPos()<12){//пустая ячейка не в нижнем ряду
                        field.swap(field.getBlankPos()+4);//меняю пустую с костяшкой под ней
                        System.out.println("вверх");
                    }
                    break;
                case(KeyEvent.VK_DOWN):
                    if(field.getBlankPos()>3){//пустая ячейа не в верхнем ряду
                        field.swap(field.getBlankPos()-4);//меняю пустую с костяшкой под ней
                        System.out.println("вниз");
                    }
                    break;
                case(KeyEvent.VK_RIGHT):
                    if(field.getBlankPos()%4!=0){//пустая ячейка не в крайнем левом столбце
                        field.swap(field.getBlankPos()-1);//меняю пустую с костяшкой слева
                        System.out.println("вправо");
                    }
                    break;
                case(KeyEvent.VK_LEFT):
                    if(field.getBlankPos()%4!=3){//пустая ячейка не в правом крайнем столбце
                        field.swap(field.getBlankPos()+1);//меняю пустую с костяшкой справа
                        System.out.println("влево");
                    }
                    break;
            }
            field.reset();//сбрасываем и отправляем на перерисовку
            //super.keyPressed(e);
        }
    }

    //обработка событий мыши
    private class MouseClick implements ActionListener {//обработка кликов мышью
        PlayingField field = PlayingField.this;
        @Override
        public void actionPerformed(ActionEvent e) {
            //определяем на какую конкретно костяшку нажали
            //если кликнули на костяшку слева, справа, снизу ли сверху от пустой
            //меняю костяшку, на которую нажали с пустой
            //обновляю поле

            Tile currentTile = (Tile)e.getSource();//определяем на какую конкретно костяшку нажали
            if (currentTile.getLocation_()==field.getBlankPos()-1 ||
                    currentTile.getLocation_()==field.getBlankPos()+1 ||
                    currentTile.getLocation_()==field.getBlankPos()+4 ||
                    currentTile.getLocation_()==field.getBlankPos()-4){//если кликнули на костяшку слева, справа, снизу ли сверху от пустой
                field.swap(currentTile.getLocation_());//меняю костяшку, на которую нажали с пустой
            }
            field.reset();//обновление поля
        }
    }
}
