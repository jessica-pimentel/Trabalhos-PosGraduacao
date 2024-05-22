import java.util.Scanner;

public class ExMedia
{
	public static void main (String args[])
	{
		int n = 0;
		double valor, soma = 0.0, media;
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Digite um número ou s para sair: ");
			String entrada = sc.next();
			if(entrada.equalsIgnoreCase("S"))
				break;
			try
			{
				valor = Double.parseDouble(entrada);
				soma += valor;
				n++;
				media = soma/n;
				System.out.println("Média: " + media);
			}
			catch(Exception ex)
			{
				System.out.println(entrada+ " não é válido");
			}
		}
		while(true);
	}
}