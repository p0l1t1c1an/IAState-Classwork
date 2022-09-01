package edu.iastate.cs228.hw4;

/**
 *  
 * @author
 *
 */

import java.util.HashMap;

/**
 * 
 * This class represents an infix expression. It implements infix to postfix conversion using 
 * one stack, and evaluates the converted postfix expression.    
 *
 */

public class InfixExpression extends Expression 
{
	private String infixExpression;   	// the infix expression to convert		
	private boolean postfixReady = false;   // postfix already generated if true
	private int rankTotal = 0;		// Keeps track of the cumulative rank of the infix expression.
	
	private PureStack<Operator> operatorStack; 	  // stack of operators 
	
	
	/**
	 * Constructor stores the input infix string, and initializes the operand stack and 
	 * the hash map.
	 * 
	 * @param st  input infix string. 
	 * @param varTbl  hash map storing all variables in the infix expression and their values. 
	 */
	public InfixExpression (String st, HashMap<Character, Integer> varTbl)
	{
		this(st);
		varTable.putAll(varTbl);
	}
	

	/**
	 * Constructor supplies a default hash map. 
	 * 
	 * @param s
	 */
	public InfixExpression (String st)
	{
		infixExpression = st;
		varTable = new HashMap<Character, Integer>();
		operatorStack = new ArrayBasedStack<Operator>();
	}
	

	/**
	 * Outputs the infix expression according to the format in the project description.
	 */
	@Override
	public String toString()
	{ 
		return removeExtraSpaces(infixExpression); 
	}
	
	
	/** 
	 * @return equivalent postfix expression, or  
	 * 
	 *         a null string if a call to postfix() inside the body (when postfixReady 
	 * 		   == false) throws an exception.
	 */
	public String postfixString() 
	{
		if(postfixReady == true)
			return postfixExpression;

		return null; 
	}


	/**
	 * Resets the infix expression. 
	 * 
	 * @param st
	 */
	public void resetInfix (String st)
	{
		infixExpression = st; 
	}


	/**
	 * Converts infix expression to an equivalent postfix string stored at postfixExpression.
	 * If postfixReady == false, the method scans the infixExpression, and does the following
	 * (for algorithm details refer to the relevant PowerPoint slides): 
	 * 
	 *     1. Skips a whitespace character.
	 *     2. Writes a scanned operand to postfixExpression. 
	 *     3. When an operator is scanned, generates an operator object.  In case the operator is 
	 *        determined to be a unary minus, store the char '~' in the generated operator object.
	 *     4. If the scanned operator has a higher input precedence than the stack precedence of 
	 *        the top operator on the operatorStack, push it onto the stack.   
	 *     5. Otherwise, first calls outputHigherOrEqual() before pushing the scanned operator 
	 *        onto the stack. No push if the scanned operator is ). 
     *     6. Keeps track of the cumulative rank of the infix expression. 
     *     
     *  During the conversion, catches errors in the infixExpression by throwing 
     *  ExpressionFormatException with one of the following messages:
     *  
     *      -- "Operator expected" if the cumulative rank goes above 1;
     *      -- "Operand expected" if the rank goes below 0; 
     *      -- "Missing '('" if scanning ')' results in popping the stack empty with no '(';
     *      -- "Missing ')'" if a '(' is left unmatched on the stack at the end of the scan; 
     *      -- "Invalid character" if a scanned char is neither a digit nor an operator; 
     *   
     *  If an error is not one of the above types, throw the exception with a message you define.
     *      
     *  Sets postfixReady to true.  
	 */
	public void postfix() throws ExpressionFormatException
	{
		if(postfixReady == false)
		{
			rankTotal = 0;
			postfixExpression = "";
			for(int i = 0; i < infixExpression.length(); ++i)
			{
				char currChar = infixExpression.charAt(i);
				if(!(Character.isWhitespace(currChar)))
				{
					if(Character.isDigit(currChar))
					{
						do
						{
							postfixExpression += currChar;
							++i;
							if(i >= infixExpression.length()) 
								break;
							currChar = infixExpression.charAt(i);
							//System.out.println(currChar);
						}
						while(Character.isDigit(currChar));
						
						//System.out.println(postfixExpression);
						postfixExpression += " ";
						rankTotal += 1;
						--i;
						//System.out.println(currChar + "\t" + rankTotal);
						if(rankTotal > 1)
							throw new ExpressionFormatException("Operator Expected");
					}
					else if(Expression.isVariable(currChar))
					{
						postfixExpression += currChar + " ";
						rankTotal += 1;
						if(rankTotal > 1)
							throw new ExpressionFormatException("Operator Expected");
					}
					else if(Expression.isOperator(currChar))
					{
					//	System.out.println(currChar);
					//	System.out.println(rankTotal);
						Operator op;
						if(rankTotal == 0)
						{
							if(currChar == '-')
								op = new Operator('~');
							else if (currChar == '(')
								op = new Operator(currChar);
							else
								throw new ExpressionFormatException("Operand Expected");
						}
						else
							op = new Operator(currChar);

						if(op.getOp() != '(' && op.getOp() != ')' && op.getOp() != '~')
						{
							rankTotal -= 1;
							if(rankTotal < 0)
								throw new ExpressionFormatException("Operand Expected");
						}

						if(operatorStack.isEmpty())
						{
							if(currChar == ')')
								throw new ExpressionFormatException("Missing (");
							operatorStack.push(op);
						}
						else if(currChar == ')')
						{
							Operator top;
							while((top = operatorStack.pop()).getOp() != '(')
							{
								postfixExpression += top.getOp() + " ";
								if(operatorStack.isEmpty())
									break;
							}
							if(top.getOp() != '(')
								throw new ExpressionFormatException("Missing (");
						}
						else
						{
							outputHigherOrEqual(op);
							operatorStack.push(op);
						}
					}
					else
					{
						throw new ExpressionFormatException("Invalid Character");
					}
				}
			}
			while(!operatorStack.isEmpty())
			{
				Operator top = operatorStack.pop();
				if(top.getOp() == '(')
					throw new ExpressionFormatException("Missing )");

				postfixExpression += top.getOp() + " ";
			}
			postfixReady = true;
		}
	}


	/**
	 * This function first calls postfix() to convert infixExpression into postfixExpression. Then 
	 * it creates a PostfixExpression object and calls its evaluate() method (which may throw  
	 * an exception).  It also passes any exception thrown by the evaluate() method of the 
	 * PostfixExpression object upward the chain. 
	 * 
	 * @return value of the infix expression 
	 * @throws ExpressionFormatException, UnassignedVariableException
	 */
	public int evaluate() 
	{
		try
		{
			postfix();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		PostfixExpression pE = new PostfixExpression(postfixExpression, varTable);  
		//System.out.println(pE.postfixExpression);
		int value = 0;

		try
		{
			value = pE.evaluate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return value;
	}


	/**
	 * Pops the operator stack and output as long as the operator on the top of the stack has a 
	 * stack precedence greater than or equal to the input precedence of the current operator op.  
	 * Writes the popped operators to the string postfixExpression.  
	 * 
	 * If op is a ')', and the top of the stack is a '(', also pops '(' from the stack but does 
	 * not write it to postfixExpression. 
	 * 
	 * @param op  current operator
	 */
	private void outputHigherOrEqual(Operator op)
	{
		Operator top = operatorStack.peek();
		if(top.getOp() == '(' && op.getOp() == ')')
			operatorStack.pop();
		else if(top.compareTo(op) >= 0)
			postfixExpression += operatorStack.pop().getOp() + " ";
	}

	// other helper methods if needed
}
