import java.util.Scanner;


public class FibonacciCalculator {

	public static void main(String[] args) {
		System.out.print("Введите порядковый номер числа Фиббоначчи, которое желаете получить: ");
		Scanner sc = new Scanner(System.in);
		if(sc.hasNextInt())
		{
			System.out.print("Ваше число: "+fibb(sc.nextInt()));
		}
		else{
			System.out.print("Вы ввели не целое число");
		}
		sc.close();
	}
	
	public static int fibb (long number){
		if (number == 0) return 0;
		else
			if (number == 1) return 1;
			else return (fibb(number - 1) + fibb(number - 2));
	}

}