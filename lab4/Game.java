public class Game {
    public static Field firstInit(){
        Field game_field=new Field(15);
        game_field.paintBlock(3,8);
        game_field.paintBlock(4,7);
        game_field.paintBlock(5,7);
        game_field.paintBlock(5,8);
        game_field.paintBlock(5,9);
        game_field.paintBlock(9,7);
        game_field.paintBlock(9,8);
        game_field.paintBlock(9,9);
        game_field.paintBlock(10,7);
        game_field.paintBlock(11,8);
        game_field.paintBlock(3,2);
        game_field.paintBlock(4,3);
        game_field.paintBlock(5,1);
        game_field.paintBlock(5,2);
        game_field.paintBlock(5,3);
        game_field.paintBlock(9,1);
        game_field.paintBlock(9,2);
        game_field.paintBlock(9,3);
        game_field.paintBlock(10,3);
        game_field.paintBlock(11,2);
        return game_field;
    }

    public static void main(String[] argv){
        int N=10;
        Field game_field=firstInit();
        System.out.println("Начальное состояние");
        game_field.printField();
        for(int i=1; i<=N;i++){
            System.out.println('\n'+"Раунд "+i);
            game_field.NewRound();
            game_field.printField();
        }
    }
}

