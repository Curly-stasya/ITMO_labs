/*Условие задачи:
Колода карт состоит из 36 листов.
Колода перемешивается, а затем раздается полностью двум игрокам: 1-ая карта – 1-ому игроку, 2-ая карта – 2-ому игроку, 3-ая карта – 1-ому игроку, 4-ая карта – 2-ому игроку и т. д.
Напишите программу, используя объектно-ориентированную методологию,которая создает объект колода, выводит колоду на экран, перемешивает колоду, вновь выводит ее на экран.
Далее создает объекты игроков, раздает карты колоды игрокам.
При раздаче картыдобавляются в конец последовательности, для этого выполняется проход от начала до конца последовательности и только затем добавляется очередная карта.
Раздав все карты, программа выводит на экран последовательности карт каждого игрока.
Затем выполняются три раунда игры, во время каждого раунда игроки кладут по одной карте (первой в их последовательности) на стол (объект последовательность карт).
Если достоинство карты 1-го игрока оказывается больше достоинства карты 2-го игрока, то вся последовательность карт стола помещается в конец последовательности карт 1-го игрока.
Если же достоинство карты 2-го игрока оказывается больше или равно достоинству карты 1-го игрока, то вся последовательность карт стола помещается в конец последовательности карт 2-го игрока.
После добавления последовательности карт стола в конец последовательности карт игрока, она становится пустой.
Во время каждого раунда необходимо выводить на экран исходное состояние последовательностей карт игроков,последовательность карт стола, и состояние последовательностей карт игроков после присоединения последовательности карт стола.
Используйте для хранения колоды и последовательностей карт игроков и стола только один массив из 36 элементов.*/

public class Game {
    public static void round(Pack koloda, Player player1, Player player2,Player table, int number){
        int tmp;
        System.out.println("//------------------//  Раунд " + number + "  //------------------//");

        tmp=koloda.getPack()[player1.getStart()].getNext();//запоминаю индекс 2ой карты в последовательности
        table.addCard(player1.getFirstCard());
        System.out.println('\n' + "Первый игрок после удаления первого элемента:");
        player1.playerPrint();

        tmp=koloda.getPack()[player2.getStart()].getNext();
        table.addCard(player2.getFirstCard());
        System.out.println("Второй игрок после удаления первого элемента:");
        player2.playerPrint();

        System.out.println('\n'+ "Стол:");
        table.playerPrint();

        if (koloda.getPack()[table.getStart()].getRealValue() > koloda.getPack()[koloda.getPack()[table.getStart()].getNext()].getRealValue()) {//сравниваю достаинства 1ой и 2ой карты стола
            player1.addOrder(table);
        } else {
            player2.addOrder(table);
        }
        System.out.println('\n' + "Первый игрок после " + number + " раунда: ");
        player1.playerPrint();
        System.out.println("Второй игрок после " + number + " раунда: ");
        player2.playerPrint();
        System.out.println(" ");
        table.setStart(-1);//очищаю последовательность стола
    }

    public static void main(String[] argc) {
        Pack koloda = new Pack();
        System.out.println("Колода:");
        koloda.PackPrint();
        koloda.mix();
        System.out.println('\n' + "Перетасованная колода:");
        koloda.PackPrint();
        Player player1 = new Player(koloda);
        Player player2 = new Player(koloda);
        koloda.distribute(player1, player2);
        System.out.println('\n' + "Первый игрок: ");
        player1.playerPrint();
        System.out.println();
        System.out.println("Второй игрок: ");
        player2.playerPrint();
        System.out.println();
        //Game game = new Game();
        //----------------//   3 раунда игры   //---------------//
        Player table=new Player(koloda);
        for (int i = 1; i <= 3; i++) {
            round(koloda,player1,player2,table,i);
        }
    }
}




