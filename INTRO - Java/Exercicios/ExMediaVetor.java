import java.util.Scanner;

public class ExMediaVetor
{
	public static void main (String args[])
	{
		System.out.println("Informe a quantidade de números. ");
		Scanner sc = new Scanner(System.in);
		int quantidade = sc.nextInt();		
		
		double vetor[] = new double[quantidade];
		
		for(int i = 0; i < quantidade; i++)
		{
			System.out.println("Informe o número: " + (i+1));
			vetor[i] = sc.nextDouble();
		}
		double soma = 0;
		
		for(double elemento : vetor)
		{
			soma += elemento;
		}
		double media = soma/quantidade;
		System.out.println("A média é: " + media);
		System.out.println("Os números digitados foram: ");
		
		for(double numero : vetor)
		{
			System.out.print(numero + " ");
		}
	}
}