/*Дано число N. Напишите программу нахождения всех чисел и их количества в интервале 1-N, делящихся на сумму своих цифр.
  N задается а программе. */

/*Алгоритм:
* прохожусь по всем числам в промежутке
* считаю для каждого сумму цифр
* проверяю условие, в случае выполнение вывожу и увеличиваю счетчик
* вывожу общее кол-во
* */
public class Solution {
    public static void main( String[] args){
        int N=1000;
        int count=0;//кол-во чисел, удовлетворяющих условию
        int sum=0;//сумма цифр
        int copy=0;//копия числа
        System.out.println("Числа, делящиеся на сумму своих цифр:");
        if (N>10) {
        	for(int i=1; i<10; i++) {
        		System.out.println(i);
        	}
        	count=9;
        	int dec=N/10;
        	int last=N%10;
        	for (int i=1;i<dec;i++) {//по всем десяткам
        		copy=i;
                sum=0;
                while (copy > 0) {//подсчет суммы цифр для десятка
                    sum+=(copy%10);
                    copy/=10;
                }
        		for(int j=0; j<10;j++) {//по всем цифрам в разряде единиц
        			if((i*10+j)%sum==0) {//проверка делимости числа на сумму цифр
        				count++;
                        System.out.println(i*10+j);
        			}
        			sum++;
        		}
        	}
        	while (dec > 0) {//подсчет суммы цифр для десятка
                sum+=(dec%10);
                dec/=10;
            }
        	dec=N/10;
        	for(int i=0;i<last;i++){
        		if((dec*10+i)%sum==0) {//проверка делимости числа на сумму цифр
    				count++;
                    System.out.println(dec*10+i);
    			}
        		sum++;
        	}
        }
        else {
        	for(int i=1; i<N;i++) {
        		System.out.println(i);
        	}

        }
        System.out.println("В промежутке от 1 до "+ N + " таких чисел - " + count);
    }
}
