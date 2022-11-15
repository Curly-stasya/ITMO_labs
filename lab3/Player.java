public class Player {
    private int start;
    Player(){
        start=0;
    }
    Player(int num){
        start=num;
    }
    public int getStart(){return start;}
    public void PlayerPrint(Card[] arr){
        int i=start;
        while(arr[i].getNext()!=-1){
            arr[i].CardPrint();
            i=arr[i].getNext();
        }
        arr[i].CardPrint();
    }
    public void AddCard(Card[] arr, Card card){
        int i=start;
        while (arr[i].getNext()!=-1){
            i=arr[i].getNext();
        }
        if(i<34)
            arr[i].setNext(i+2);
        else
            System.out.println("out of range");
    }
}
