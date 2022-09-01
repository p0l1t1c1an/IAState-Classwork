package edu.iastate.cs228.hw4;

/**
 *  
 * @author Jacob Boicken
 *
 */

/**
 * 
 * This class evaluates input infix and postfix expressions. 
 *
 */

import java.util.HashMap;
import java.io.File;
import java.util.Scanner;

public class InfixPostfix 
{

	/**
	 * Repeatedly evaluates input infix and postfix expressions.  See the project description
	 * for the input description. It constructs a HashMap object for each expression and passes it 
	 * to the created InfixExpression or PostfixExpression object. 
	 *  
	 * @param args
	 **/
	public static void main(String[] args) 
	{
		int input = 0, trial = 0;
		System.out.println("Evaluation of Infix and Postfix Expressions\n" +
				"keys: 1 (standard input) 2 (file input) 3 (exit)\n" +
				"(Enter I before an infix expression, P before a postfix expression)");

		Scanner sc = new Scanner(System.in);

		while(input != 3)
		{
			System.out.print("Trail " + trial + ": ");
			if(sc.hasNextInt())
			{
				input = sc.nextInt();
			}
			else
			{
				sc.nextLine();
				continue;
			}

			String exp;
			HashMap<Character, Integer> map = new HashMap<Character, Integer>();

			sc.nextLine();

			if(input == 1)
			{
				System.out.print("Expression: ");
				if(sc.hasNextLine())
					exp = sc.nextLine();
				else
					continue;

				exp = Expression.removeExtraSpaces(exp);
				Expression express;
				boolean iOrP;

				try
				{
					iOrP = infixOrPostfix(exp);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}

				if(iOrP)
				{
					express = new PostfixExpression(exp.substring(1)); 
					System.out.println("Postfix Form: " + express);
				}
				else
				{
					express = new InfixExpression(exp.substring(1)); 
					System.out.println("Infix Form: " + express);
					InfixExpression in = (InfixExpression) express;
					try
					{
						in.postfix();
					}
					catch(Exception e)
					{
						e.printStackTrace();
						return;
					}
					System.out.println("Postfix Form: " + in.postfixString());
				}


				int count = 0;
				for(int i = 0; i < exp.length(); ++i)
				{
					char currChar = exp.charAt(i);
					if(Expression.isVariable(currChar))
					{
						if(count == 0)
							System.out.println("where");

						System.out.print(currChar + " = ");

						if(sc.hasNextInt())
						{
							map.put(currChar, sc.nextInt());
						}
						else
						{
							System.out.println("Invalid Input");
							continue;
						}
						++count;
					}
				}

				if(!map.isEmpty())
					express.setVarTable(map);
				
				try
				{
					System.out.println("Expression Value: " + express.evaluate());
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}
			}
			else if(input == 2)
			{

				System.out.print("File: ");
				File file = new File(sc.nextLine());
				
				try
				{
					sc = new Scanner(file);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return;
				}

				while(sc.hasNextLine())
				{
					exp = sc.nextLine();
					Expression express; 
					boolean iOrP;
					
					try
					{
						iOrP = infixOrPostfix(exp);
					}
					catch(Exception e)
					{
						e.printStackTrace();	
						return;
					}

					if(iOrP)
					{
						express = new PostfixExpression(exp.substring(1));
						System.out.println("Postfix Form: " + express);
					}
					else
					{
						express = new InfixExpression(exp.substring(1));
						System.out.println("Infix Form: " + express);
						InfixExpression in = (InfixExpression) express;
						try
						{
							in.postfix();
						}
						catch(Exception e)
						{
							e.printStackTrace();
							return;
						}
						System.out.println("Postfix Form: " + in.postfixString());
					}

					int count = 0;
					for(int i = 0; i < exp.length(); ++i)
					{
						char currChar = exp.charAt(i);
						if(Expression.isVariable(currChar))
						{
							++count;		
						}
					}

					if(count > 0)
					{
						System.out.println("where");	
						for(int i = 0; i < count; ++i)
						{
							if(sc.hasNextLine())
							{
								String arr[] = sc.nextLine().split("=", 2);
								char c = arr[0].trim().charAt(0);
								int num = Integer.parseInt(arr[1].trim());
								System.out.println(c + " = " + num);
								map.put(c, num);
							}
							else
								break;
						}
						express.setVarTable(map);
					}

					try
					{
						System.out.println("Expression Value: " + express.evaluate());
					}
					catch(Exception e)
					{
						e.printStackTrace();
						return;
					}
				}

				sc = new Scanner(System.in);
			}
			else if(input == 3)
			{
				continue;
			}
			else
			{
				System.out.println("Invalid Input");
				continue;
			}
			++trial;
		}   
		sc.close();				
	}       				

	// helper methods if needed

	private static boolean infixOrPostfix(String exp) throws ExpressionFormatException
	{
		char first = exp.charAt(0);
		if(first == 'P')
			return true;
		else if(first == 'I')
			return false;
		throw new ExpressionFormatException("Invalid Expression Type");
	}	
}
