public class Field {
    private int size;
    private Block[][] curArray;
    private Block[][] nextArray;//завела 2 массива по текущему смотрю на клетки и их соседей, а в следующем делаю все изменения
    //------------------------------
    public Field(int size){
        this.size=size+2;//добавляю рамочку чтоб отдельно не париться с боковыми и угловыми клетками
        curArray = new Block[this.size][this.size];
        nextArray = new Block[this.size][this.size];
        for(int i=0;i<this.size; i++){
            for(int j=0; j<this.size; j++){
                curArray[i][j]=nextArray[i][j]=new Block(j,i);
            }
        }
    }

    public Block[][] getCurArray() {
        return curArray;
    }

    public void changeArraysForNextRound(){
        Block[][]tmp = curArray;
        curArray=nextArray;
        nextArray=tmp;
    }
    public void paintBlock(int xn, int yn){//используется только в начале игры
        curArray[yn][xn]=new ColoredBlock(xn, yn);
        nextArray[yn][xn]=new ColoredBlock(xn, yn);
    }
    public void printField(){
        for(int i=1; i<size-1; i++){
            for(int j=1; j<size-1; j++){
                curArray[i][j].printBlock();
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void NewRound(){
        for (int i = 1; i < size-1 ; i++) {
            for (int j = 1; j < size-1; j++) {
                nextArray[i][j]=curArray[i][j].nextStatus(this);
            }
        }
        this.changeArraysForNextRound();
    }
}
