public class Card {
    private int value;
    private int next;

    //-------------------

    public Card(int num){
        value=num;
        next=-1;
    }
    public void setNext(int num){
        next=num;
    }
    public int getNext(){
        return next;
    }
    public int getRealValue(){
        return (value%9)+6;
    }//возвращает достоинство карты

    public void CardPrint(){//вывод карты
        switch((value%9)+6){//вывод достоинства
            case 11:
                System.out.print("В");
                break;
            case 12:
                System.out.print("Д");
                break;
            case 13:
                System.out.print("K");
                break;
            case 14:
                System.out.print("T");
                break;
            default:
                System.out.print(((value%9)+6));
                break;
        }
        switch (value/9){//вывод масти
            case 0:
                System.out.print("\u2660" + " ");  //пики
                break;
            case 1:
                System.out.print("\u2663"+ " ");//крести
                break;
            case 2:
                System.out.print("\u2665"+ " ");//черви
                break;
            case 3:
                System.out.print("\u2662"+ " ");//буби
                break;
            default:
                System.out.print("ERROR");;
                break;
        }
    }

}
