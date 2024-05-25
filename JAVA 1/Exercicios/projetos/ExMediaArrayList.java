import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ExMediaArrayList
{
	public static void main (String args[])
	{
		double valor, media, soma=0;
		String entrada;
		List<Double> lista = new ArrayList<>();
		
		do
		{
			Scanner sc = new Scanner(System.in);
			System.out.println("Informe o número " + (lista.size() + 1)+" ou S para sair");
			entrada = sc.next();
			if(entrada.equalsIgnoreCase("S"))
				break;
			
			lista.add(Double.parseDouble(entrada));
		}
		while(true);
		
		for(double num: lista)
		{
			soma += num;
		}
		media = soma/lista.size();
		System.out.println("A média é = " + media);
		System.out.println("Os números digitados foram: ");
		
		for(double numero:lista)
		{
			System.out.print(numero + " ");
		}
	}
}