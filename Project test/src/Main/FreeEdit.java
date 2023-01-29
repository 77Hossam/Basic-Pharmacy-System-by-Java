package Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FreeEdit {
	public static double totalincome = 0.0;
	public static int capacity;
	public static int count = 0;
    public static void main(String[] args)
	{
                
		Scanner input = new Scanner(System.in);
		try{
			System.out.println("Welcome to the Pharmacy");
			FileCheck();
			int choice;
			do
			{
				System.out.println("Please pick a number to do an operation from the following");
				System.out.println("1- Add Drug");
				System.out.println("2- Remove Drug");
				System.out.println("3- Place an Order");
				System.out.println("4- get the total sales for one day");
				System.out.println("5- Exit");
				choice = input.nextInt();
				if(choice < 1 || choice > 5)
				{
					System.out.println("Please enter a valid number");
				}
				else if(choice == 1)
				{
					if(capacity > count)
					{
						AddDrug();
					}
					else
					{
						System.out.println("There's no place for another drug, free some space!");
					}
				}
				else if(choice == 2)
				{
					removeDrug();
				}
                else if(choice == 3)
				{
					PlaceOrder();
				}
                else if (choice == 4)
                {
                    TotalSales();
                }
                else if(choice ==5)
                {
                	File f = new File("Pharmacy.txt");
        	        File temp= new File("temp.txt");
        	        PrintWriter pw= new PrintWriter(temp);
        	        Scanner myReader= new Scanner(f);
        	        String FirstLine = myReader.nextLine();
        	        String currentLine;
        	        pw.println(capacity + " " + count);
        	        while(myReader.hasNextLine())
        	        {
        	            currentLine = myReader.nextLine();
        	            if(currentLine == FirstLine)
        	            {
        	            	continue;
        	            }
        	            else
        	            {
        	            	pw.println(currentLine);
        	            }
        	        }
        	        myReader.close();
        	        pw.close();
        	        f.delete();
        	        temp.renameTo(f);
        	        
                	System.out.println("Thanks for using the Pharmacy System!");
                	System.out.println("program terminated due to exit() method.");
                	System.exit(0);
                }
			}
			while (choice != 5);
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	        }
	}
	
	public static void AddDrug()
	{
		Scanner input = new Scanner(System.in);
		int ID = 0, quantity = 0;
		String name = null;
		double price = 0.0;
		String category = null;
		try {
		System.out.println("Enter the drug name: ");
		name = input.next();
		System.out.println("Enter Drug ID: ");
		ID = input.nextInt();
		System.out.println("Enter the price");
		price = input.nextDouble();
		System.out.println("Please choose the category");
		System.out.println("1- cosmetics");
		System.out.println("2- prescription");
		System.out.println("3- other");
		int choice;
		do 
		{
			choice = input.nextInt();
			if(choice == 1)
			{
				category = "cosmetics";
			}
			else if(choice == 2)
			{
				category = "prescription";
			}
			else if(choice == 3)
			{
				category = "other";
			}
			else
			{
				System.out.println("Please enter a valid number");
			}
		}
		while(choice < 1|| choice > 3);
		System.out.println("Enter the available quantity");
		quantity = input.nextInt();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		try {
		FileWriter fw = new FileWriter("Pharmacy.txt", true);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println(ID + " " + name + " " + price + " " + category + " " + quantity);
		pw.close();
		System.out.println("Drug is added successfully!");
		count++;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void PlaceOrder()
	{
		try {
                ArrayList<String> Tokens = new ArrayList<String>();
                System.out.println("Enter ID of the drug you want");
                Scanner m = new Scanner(System.in);
                int IDD = m.nextInt();
                File f = new File("Pharmacy.txt");
                File temp= new File("tempxx.txt");
                PrintWriter pw= new PrintWriter(temp);
                Scanner MyReader = new Scanner(f);
                String Line;
                String FirstLine = MyReader.nextLine();
                pw.println(capacity + " " + count);
                while(MyReader.hasNextLine())
                { 	
                    Line = MyReader.nextLine();
                    Scanner reader = new Scanner(Line);
                    int ID = reader.nextInt();
                    String Name = reader.next();
                    double price = reader.nextDouble();
                    String type = reader.next();
                    int quantity = reader.nextInt();
                	double pp = price*1.2;
                	int new_ava = quantity - 1;
                    if(Line == FirstLine)
                    {
                    	continue;
                    }
                    
                    if(ID == IDD)
                    {
                    	if (quantity != 0 && "prescription".equals(type))
                    	{
                    		System.out.println("This Drug is Avaliable");
                    		System.out.println("You Should Pay => " + " " + price);
                    		System.out.println("Available quantity After purchase = " + " " + new_ava);
                    		totalincome = totalincome + price; 
                        }
                    	else if(quantity != 0 && "cosmetics".equals(type))
                        {
                            System.out.println("This Drug is Avaliable");
                            System.out.println("You Should Pay => " + pp);
                            System.out.println("Available quantity After purchase = " + " " + new_ava);
                            totalincome = totalincome + pp;
                        }
                        	
                        else if(quantity != 0 && "other".equals(type))
                        {
                            System.out.println("This Drug is Avaliable");
                            System.out.println("You Should Pay => " + price);
                            System.out.println("Available quantity After purchase = " +  new_ava);
                            totalincome = totalincome + price;
                        }
                        else
                        {
                            System.out.println("This Drug is out of stock");
                        }
                    	pw.println(ID + " " + Name + " " + price + " " + type + " " + new_ava);
                    }
                    else
                    {
                    	pw.println(Line);
                    }
                }
                MyReader.close();
                pw.close();
                //ft.delete();
                f.delete();
                temp.renameTo(f);    
            }
               
		catch(FileNotFoundException | NumberFormatException e)
		    {
				System.out.println(e.getMessage());
		    }
    }
	
	public static void removeDrug(){
        try{
	        System.out.println("Enter ID of drug to remove: ");
	        Scanner k = new Scanner(System.in);
	        int id;
	        id = k.nextInt();
	        File f = new File("Pharmacy.txt");
	        File temp= new File("temp.txt");
	        PrintWriter pw= new PrintWriter(temp);
	        Scanner myReader= new Scanner(f);
	        String currentLine;
	        String FirstLine = myReader.nextLine();
	        count--;
	        pw.println(capacity + " " + count);
	        while(myReader.hasNextLine())
	        {
	            currentLine = myReader.nextLine();
	            if(currentLine == FirstLine)
	            {
	            	continue;
	            }
	            StringTokenizer st = new StringTokenizer(currentLine," ");
	            if(Integer.parseInt(st.nextToken())==id) continue;
	            pw.println(currentLine);
	        }
	        myReader.close();
	        pw.close();
	        f.delete();
	        temp.renameTo(f);
	        System.out.println("The drug is removed successfully!");
	        }
	        catch(Exception e){
	            System.out.println(e.getMessage());
	        }
	}

	public static void TotalSales()
	{
		System.out.println("Total Sales for today => " + totalincome);
	}
	
	public static void FileCheck() throws FileNotFoundException
	{
		Scanner input = new Scanner(System.in);
		File f = new File("Pharmacy.txt");
		if(f.exists() == false)
		{
			System.out.println("Please enter the capacity: ");
			capacity = input.nextInt();
			PrintWriter pw = new PrintWriter(f);
			pw.println(capacity + " " + count);
			pw.close();
		}
		else
		{
			Scanner MyReader = new Scanner(f);
			int cap, cnt;
			cap = MyReader.nextInt();
			cnt = MyReader.nextInt();
			capacity = cap;
			count = cnt;
			MyReader.close();
		}
	}
}
