public class Card {
    private int value;
    private int next;
    Card(){
        value=0;
        next=-1;
    }
    Card(int num){
        value=num;
        next=-1;
    }
    public void setNext(int num){
        next=num;
    }
    public int getNext(){
        return next;
    }
    public void CardPrint(){
        switch((value%9)+6){
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
        switch (value/9){
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
