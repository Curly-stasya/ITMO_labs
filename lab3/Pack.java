import java.util.Random;
public class Pack {
    private Card[] pack;

    Pack(){
        pack = new Card[36];
        for(int i=0; i<36;i++){
            pack[i]= new Card(i);
        }
    }
    public Card[] getPack(){
        return pack;
    }
    public void mix(){
        Random random=new Random();
        int mixer;
        Card tmp;
        for(int i=0; i<36;i++){
            mixer=random.nextInt(30);
            tmp=pack[i];
            pack[i]=pack[(i+mixer)%36];
            pack[(i+mixer)%36]=tmp;
        }
    }
    /*public void distribute(){
        for(int i=0;i<34;i++){//последние 2 карты остались с дефолтным значением -1 => конец последовательности
            pack[i].setNext(i+2);
        }
    }*/
    public void distribute(Player first, Player second){
        int range1=first.getStart()+32;
        int range2=second.getStart()+32;
        for(int i=first.getStart(); i<=range1;i+=2){
            first.AddCard(pack,pack[i]);
        }
        for(int j=second.getStart(); j<=range2;j+=2){
            second.AddCard(pack,pack[j]);
        }
    }
    public void PackPrint(){
        for(int i=0; i<36;i++){
            pack[i].CardPrint();
        }
        System.out.println(" ");
    }
}
