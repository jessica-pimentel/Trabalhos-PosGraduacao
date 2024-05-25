import java.util.Scanner;

public class ExSaidaFormat
{
	public static void main (String args[])
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite um número: ");
		double numero = sc.nextDouble();
		System.out.printf("Saída com uma casa decimal: %.1f ", numero);
	}
}