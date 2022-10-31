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
        int M=5;
        int N=5;
        int[][] array= initByRandom(M,N);
        print_2dimArr(array);
        System.out.println("Транспонированная матрица:");
        print_2dimArr(transposition(array));
    }

    public static int[][] initByRandom (int rows, int colms){//инициализация матрицы псевдорандомными значениями
        Random random = new Random();
        int[][] arr = new int[rows][colms];
        for (int i=0; i< rows; i++){
            for (int j=0; j<colms;j++){
                arr[i][j]= random.nextInt()%100;
            }
        }
        return arr;
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
        if(matrix.length== matrix[0].length){//квадратная или прямоугольная
            int size=matrix.length;
            int temp;
            for(int i=0;i< matrix.length;i++){
                for(int j=0;(j<size && j!=i); j++){
                    temp=matrix[i][j];
                    matrix[i][j]=matrix[j][i];
                    matrix[j][i]=temp;
                }
                size--;
            }
            return matrix;
        }
        else {
            int[][] matrixT = new int[matrix[0].length][matrix.length];//транспонированная матрица
            for (int i = 0; i < matrixT.length; i++) {
                for (int j = 0; j < matrixT[0].length; j++) {
                    matrixT[i][j] = matrix[j][i];
                }
            }
            return matrixT;
        }
    }
}
