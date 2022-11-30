public class Player {
    private int start;

    //TO DO static
    private Card[] array;

    //-------------------
    public Player( Pack arr){
        array= arr.getPack();
        start=-1;
    }
    public int getStart(){return start;}
    public void setStart(int num){
        start=num;
    }
    public void playerPrint(){//вывод последовательности игроков
        if (start==-1){
            System.out.println("empty order");
        }
        else {
            int i = start;
            while (array[i].getNext() != -1) {
                array[i].CardPrint();
                i = array[i].getNext();
            }
            array[i].CardPrint();
        }
        System.out.println(" ");
    }

    int getLastCard(){
        int i=start;
        while (array[i].getNext() != -1) {
            i = array[i].getNext();
        }
        return i;
    }

    public void addCard(Card card) {//добавление карты в последовательность
        int index = card.getNext();//просто номер карты в массиве
        if (start ==-1) {//последовательность пустая?
            start = index;
            array[start].setNext(-1);
            return;
        }
        array[this.getLastCard()].setNext(index);
        array[index].setNext(-1);
    }
    public Card getFirstCard(){//получение первой карты
        Card tmp=array[start];
        int tmpStart=start;
        start = array[start].getNext();
        tmp.setNext(tmpStart);

        return tmp;
    }
    public void addOrder(Player order){//добавление последовательности в конец другой последовательности
        int i;
        if (start==-1){//последовательность пустая?
            i=start=0;
        }
        else{
            i=this.getLastCard();
        }
        array[i].setNext(order.getStart());
    }
}
