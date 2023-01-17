public class Block {
    protected int x;
    protected int y;

    public Block(int xn, int yn){
        x=xn;
        y=yn;
    }
    public int Painted(){
        return 0;
    }
    public void printBlock(){
        System.out.print('\u2B1C');//можешь поменять на 1 или 0, ему это кажется даже больше понравится
    }
    public int numberOfNeighbors(Block[][] field_array) {
        int counter = 0;
        int start_i=y-1;
        int start_j= x-1;
        int stop_i=y+1;
        int stop_j=x+1;
        for (int i=start_i; i<=stop_i; i++){
            for(int j=start_j; j<=stop_j; j++){
                counter += field_array[i][j].Painted();
            }
        }
        counter -= field_array[y][x].Painted();//убираю из расчета саму клетку
        return counter;
    }
    public Block nextStatus(Field field){//по количеству соседей понимаю какой должна стать клетка
        if (field.getCurArray()[y][x].numberOfNeighbors(field.getCurArray())==3)
            return new ColoredBlock(x,y);
        return this;
    }
}

