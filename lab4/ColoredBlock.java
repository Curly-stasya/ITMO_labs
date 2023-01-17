public class ColoredBlock extends Block{

    public ColoredBlock(int xn, int yn){
        super(xn, yn);
    }
    public void printBlock(){
        System.out.print('\u2B1B');//точто так же 1 или 0
    }
    public int Painted(){
        return 1;
    }
    public Block nextStatus(Field field){
        if (field.getCurArray()[y][x].numberOfNeighbors(field.getCurArray())<2 ||
                field.getCurArray()[y][x].numberOfNeighbors(field.getCurArray())>3)
            return new Block(x,y);
        return this;
    }
}
