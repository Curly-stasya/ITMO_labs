/*Дан целочисленный двумерный массив размера M*N, заполненный построчно случайными значениями в диапазоне от -99 до 99. Этот массив вывести на экран
* Заменить исходный массив новым(если нужно), в котором значение в столбцах оказались бы в строках, а значения в строках - в столбах
* Получившийся массив вывести на экран. M и N задаются в main()*/

/*Алгорит:
создаю новый массив,меняя размеры изначального
поэлементно переношу значения из старого

* */
import java.util.Random;
public class Solution {
    public static void main(String[] argc){
        int M=4;
        int N=5;
        Random random = new Random();
        int[][] array = new int[M][N];
        for (int i=0; i<M; i++){ //заполение матрицы псевдорандомными значениями
            for (int j=0; j<N;j++){
                array[i][j]= random.nextInt()%100;
            }
        }
        print_2dimArr(array);
        System.out.println("Транспонированная матрица:");
        print_2dimArr(transposition(array));

    }
    public static void print_2dimArr(int [][] arr){//вывод двумерного массива
        for (int i=0; i< arr.length;i++){
            for(int j=0;j<arr[0].length; j++){
                System.out.printf("%5d", arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static int[][] transposition(int[][] matrix){//транспонирование матрицы
        int[][] matrixT = new int[matrix[0].length][matrix.length];//транспонированная матрица
        for(int i=0;i<matrixT.length;i++){
            for (int j=0;j<matrixT[0].length; j++){
                matrixT[i][j]=matrix[j][i];
            }
        }
        return matrixT;
    }
}
