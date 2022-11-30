import java.util.Random;
public class Pack {
    private Card[] pack;

    //------------------------------
    public Pack(){
        pack = new Card[36];
        for(int i=0; i<36;i++){
            pack[i]= new Card(i);
        }
    }
    public Card[] getPack(){
        return pack;
    }
    public void mix(){ //Перемешивание колоды
        Random random=new Random();
        int mixer;
        Card tmp;
        for(int i=0; i<36;i++){//выбираю 2 рандомные карты и меняю местами
            mixer=random.nextInt(100);
            tmp=pack[i];
            pack[i]=pack[(i+mixer)%36];
            pack[(i+mixer)%36]=tmp;
        }
    }
    public void distribute(Player first, Player second){//Раздача карт игрокам
        for(int i=0; i<=34;i+=2){
            pack[i].setNext(i);
            first.addCard(pack[i]);
            pack[i].setNext(i+1);
            second.addCard(pack[i+1]);
        }
    }
    public void PackPrint(){//Вывод колоды
        for(int i=0; i<36;i++){
            pack[i].CardPrint();
        }
        System.out.println(" ");
    }
}
