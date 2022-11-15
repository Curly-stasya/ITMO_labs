/*класс карт
 * поля val и next
 * каждую карту перенумеровали 0-35
 *
 * класс колоды
 * массив на 36 элементов
 *
 * методы колоды:
 * перемешивание (проходим по массиву, генерируем случайно число и меняем карты) +
 * раздача +
 * вывод колоды +
 *
 * класс игрок
 * храним только start
 *
 * методы игрока:
 * добавление карты в конец последовательности +
 * извлечение 1ой карты из последовательности
 * присоединение к концу последовательности другой последовательности
 * отображение последовательности на экране +
 * */
public class Game {
    public static void main(String[] argc){
        //Card first=new Card(37);
        //first.CardPrint();
        Pack koloda=new Pack();
        koloda.PackPrint();
        koloda.mix();
        System.out.println('\n'+"Перетасованная колода:");
        koloda.PackPrint();
        Player player1 =new Player(0);
        Player player2 =new Player(1);
        koloda.distribute(player1,player2);
        System.out.println("Первый игрок: ");
        player1.PlayerPrint(koloda.getPack());
        System.out.println();
        System.out.println("Второй игрок: ");
        player2.PlayerPrint(koloda.getPack());
        //----------------//   3 раунда игры   //---------------//
        Player table = new Player();
    }
}
