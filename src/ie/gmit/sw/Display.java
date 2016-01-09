package ie.gmit.sw;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Display 
{
	private String data;
	
	public boolean displayInput() throws IOException
	{		
		Scanner in = new Scanner(System.in);
		boolean valid = false;
		boolean choice = false;
		
		System.out.println("1. Enter a text file\n2. Enter a URL");
		
		while(!valid)
		{
			System.out.print("Your choice: ");
			 int input = in.nextInt();
			 switch(input)
			 {
			 case 1:				 
				 choice = true;
				 in.nextLine();
				 System.out.print("File name (.txt can be omitted): ");
				 boolean exists = false;

				 while(!exists)
				 {
					 String file = in.next();
					 File f;
					 
					 if(!file.contains(".txt"))
						 f = new File("./" + file + ".txt");
					 
					 else
						 f = new File("./" + file);
					 
					 if(f.exists())
					 {
						exists = true;						 
						valid = true;
						data = f.getParent() + "/" + f.getName();
						System.out.println(data);
					 }
					 
					 else
						 System.out.println("Error - " + f.getName() + " not found. Please enter another file name");
				 }
				 break;
				 
			 case 2:
				 valid = true;
				 choice = false;
				 
				 in.nextLine();
				 System.out.print("URL: ");
				 data = in.next();
				 break;
				 
			 default:
				 System.out.println("Not a valid choice - try again");
				 in.nextLine();
				 break;
			 }
		}
		
		in.close();
		return choice;
	}
	
	public String getData()
	{
		return data;
	}

}
