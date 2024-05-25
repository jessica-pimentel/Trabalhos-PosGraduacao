import java.util.Scanner;

public class ExSaidaDados
{
	public static void main (String args[])
	{
		System.out.println("Insira o primeiro numero: ");
		Scanner sc = new Scanner(System.in);
		double valor1 = sc.nextDouble();
		System.out.println("Insira o segundo numero: ");
		Scanner sc2 = new Scanner(System.in);
		double valor2 = sc.nextDouble();
		
		double soma = valor1 + valor2;
		System.out.printf("Soma = %.2f", soma);
		
	}
}