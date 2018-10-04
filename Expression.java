package Expression;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Expression {
	
    public static void main (String[] args){
    	Scanner input = new Scanner(System.in);
    	   	    	
    	String infix = input.next();
    	
        ArrayList<Object> tokens = initialize(infix);    	
        int ans = evaluate(tokens);
        
        System.out.println(ans);
        
    	input.close();
    }
    
    public static void calculate(Stack<Integer> numStack, String operand) {
    	if(operand.equals("#")) {
    		int num = numStack.pop().intValue();
    	    Integer temp = ( 0 - num);
    		numStack.push(temp);
    	}
    	else if(operand.equals("+")) {
    		int num1 = numStack.pop();
    		int num2 = numStack.pop();
    		Integer temp = (num2 + num1);
    		numStack.push(temp);
    	}
    	else if(operand.equals("-")) {
    		int num1 = numStack.pop();
    		int num2 = numStack.pop();
    		Integer temp = (num2 - num1);
    		numStack.push(temp);
    	}
    	else if(operand.equals("*")) {
    		int num1 = numStack.pop();
    		int num2 = numStack.pop();
    		Integer temp = (num2 * num1);
    		numStack.push(temp);
    	}
    	else if(operand.equals("/")) {
    		int num1 = numStack.pop();
    		int num2 = numStack.pop();
    		Integer temp = (num2 / num1);
    		numStack.push(temp);
    	}
    	else if(operand.equals("%")) {
    		int num1 = numStack.pop();
    		int num2 = numStack.pop();
    		Integer temp = (num2 % num1);
    		numStack.push(temp);

    	}
    }
    
    public static int evaluate(ArrayList<Object> tokens) {
    	Stack<Integer> numStack = new Stack<Integer>();
    	Stack<String> operatorStack = new Stack<String>();
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	priority(map);
    	
    	for(Object i : tokens) {
    		String t = i.toString();	
    		if(t.equals("(")) {
    			operatorStack.push(t);
    		}
    		else if(isOperator(t) == true) {
    			if(operatorStack.isEmpty()) {
    				operatorStack.push(t);
    			}
    			else {
    				int curPr = map.get(t);
    				int topPr = map.get(operatorStack.peek());
    				if(curPr > topPr) {
    					operatorStack.push(t);
    				}
    				else {
    					while(curPr <= topPr) {
    						String topOpt = operatorStack.pop();
    						calculate(numStack, topOpt);
    						if(operatorStack.size() > 0) {
    							topPr = map.get(operatorStack.peek());
    						}
    						else break;
    					}
    					operatorStack.push(t);
    				}
    			}
    		}
    		else if(t.equals(")")) {
    			while(!operatorStack.peek().equals("(")) {
    				String topOpt = operatorStack.pop();
    				calculate(numStack, topOpt);
    			}
    			operatorStack.pop();
    		}
    		else {
    			Integer temp = Integer.parseInt(t);
    			numStack.push(temp);
    		}
    	}
    	
    	while(operatorStack.size() > 0) {
    		String topOpt = operatorStack.pop();
    		calculate(numStack, topOpt);
    	}
    	
    	return numStack.peek();
    }
    
    public static boolean isOperator(String s) {
    	if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("%") || s.equals("#"))
    		return true;
    	else return false;
    }
    
    public static void priority(Map<String, Integer> map) {
    	map.put("#", 4);
    	map.put("*", 3);
    	map.put("/", 3);
    	map.put("%", 3);
    	map.put("+", 2);
    	map.put("-", 2);
    	map.put("(", 1);  	
    }
    
    public static ArrayList<Object> initialize(String infix){
        ArrayList<Object> temp = new ArrayList<Object>();
        
        int i = 0;
        while(i < infix.length()) {
        	char c = infix.charAt(i);

        	switch(c) {
        	    case '+':
        	    case '*':
        	    case '%':
        	    case '/':
        	    case '(':
        	    case ')':{
        	    	temp.add(c);
        	    	break;
        	    }
        	    case '-':{
        	    	if(i == 0) {
        	    		temp.add('#');
        	    	}
        	    	else if(infix.charAt(i-1) == ')' || isdigit(infix.charAt(i-1))) {
        	    		temp.add(c);
        	    	}
        	    	else {
        	    		temp.add('#');
        	    	}
        	        break;
        	    }
        	    default:{
        	    	int j = i;
        	    	while(isdigit(infix.charAt(j)) && j<infix.length()) {
        	    		j++;
        	    		if(j == infix.length())
        	    			break;
        	    	}
        	    	String s = new String(infix.substring(i, j));
        	    	temp.add(s);
        	    	i = j - 1;
        	    	break;
        	    }
        	}
        	i++;
        }
        return temp;
    }
    
    public static boolean isdigit(char c) {
    	if( c>= '0' && c <= '9') return true;
    	else return false;
    }
    
    
}
