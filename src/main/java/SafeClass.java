package main.java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*Este problema é focado no seguinte: Devemos evitar o overflow de Strings, pois a entrada testada pode ser muito
grande e derrubar nosso programa. Assim, tratamos os métodos para eles tratarem entradas muito grandes.        */

public class SafeClass {
	
	String FileName;
	
	public SafeClass(String file)
	{
		FileName = file;
	}
	
	
	public void safeMethod ()
	{
		boolean GoOn = true;
		
		do
		{
			System.out.print("Digite a operacao desejada para realizar no arquivo: R para ler e W para escrever." 
					+ "Digite S para sair.");
		
			try
			{
				
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader brstd = new BufferedReader(isr);
        
				char op = (char) brstd.read();
					//Este método evita entradas muito grandes, já que lê apenas o primeiro caractere.

				switch(op)
				{
					case 'r':
					case 'R': 
						
						FileReader fr = new FileReader(FileName);
						BufferedReader br = new BufferedReader(fr);
						
						char[] currentline = new char[10001];
						
						int N_LinesRead = 0;
						
						while (br.read(currentline, 0, 10000) != -1 &&  N_LinesRead < 10000)
						{
							System.out.println(currentline);
							N_LinesRead++;
						}
						
						br.close();
						fr.close();
						
							//Lê até 10000 linhas, no máximo 10000 caracteres em cada linha. 
						
						break;
					
					case 'w':
					case 'W': 
						
						FileWriter fileWriter = new FileWriter(FileName);
						BufferedWriter buffWrite = new BufferedWriter(fileWriter);
						
						System.out.println("Escreva algo: ");

						isr = new InputStreamReader(System.in);
						brstd = new BufferedReader(isr);
						
						char[] linha = new char[10000];
						
						brstd.read(linha, 0, 10000);
									    
					    System.out.print("Vc escreveu: ");
					    System.out.print(linha);
					    System.out.print("\n");
					  
					    int i;
					    for (i = 0; linha[i] != 0; i++)
					    	buffWrite.append(linha[i]);
					    
						
					    buffWrite.close();
					    fileWriter.close();
					    
					    break;
						
					default:
						GoOn = false;
						
				}
			
			}
			catch (IOException e)
			{
				System.out.println("Expection :" + e);
			}
		}
		while(GoOn);
        
		
	}
	
	
	public static void main (String args[])
	{
		SafeClass Program = new SafeClass("teste.txt");
		
		Program.safeMethod();
	}
	
}