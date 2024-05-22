import java.util.Scanner;

public class ExEntradaFormat
{
	public static void main (String args[])
	{
		System.out.println ("Olá!");
		
		System.out.println ("Digite um inteiro: ");
		Scanner sc = new Scanner(System.in);
		int valor = sc.nextInt();
		
		System.out.println("Valor digitado: " + valor);
	}	

}