//1, 2, 3, 4, 5, 6, 7, 8, 9, 153, 370, 371, 407, 1634, 8208, 9474, 54 748, 92 727, 93 084, 548 834,
//1 741 725, 4 210 818, 9 800 817, 9 926 315, 24 678 050, 24 678 051, 88 593 477, 146 511 208,
// 472 335 975, 534 494 836, 912 985 153, 4 679 307 774.
public class Armstrong {
    public static void main(String[] args){
        int N=912985153;
        long sum=0;
        int digits=numDigits(N);
        int copy=N;
        for(int i=0;i<digits;i++){
            sum+=degree(copy%10, digits);
            copy/=10;
        }
        System.out.println((N==sum)?"Number is an armstrong number":"Number isn't an armstrong number");
    }
    public static int numDigits(int number){
        int digits=0;
        while (number>0){
            digits++;
            number/=10;
        }
        return digits;
    }
    public static long degree(int number, int degree){
        long res=1;
        if (number==0 || number==1) return number;
        for (int i = 0; i < degree; i++) {
            res *= number;
        }
        return res;
    }
}
