package test;

import java.util.Scanner;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class PostFixConverter {
    private String infix; 
    private Deque<Character> stack = new ArrayDeque<Character>();
    private List<String> postfix = new ArrayList<String>(); 
    public PostFixConverter(String expression){
        infix = expression;
        convertExpression();
    }
    private void convertExpression() {
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i != infix.length(); ++i) {           
            if(Character.isDigit(infix.charAt(i))) {
                temp.append(infix.charAt(i));

                while((i+1) != infix.length() && (Character.isDigit(infix.charAt(i+1)) || infix.charAt(i+1) == '.')) {
                    temp.append(infix.charAt(++i));
                }
                postfix.add(temp.toString());
                temp.delete(0, temp.length());
            }
            else
                inputToStack(infix.charAt(i));
        }
        clearStack();
    }

    private void inputToStack(char input) {
        if(stack.isEmpty() || input == '(')
            stack.addLast(input);
        else {
            if(input == ')') {
                while(!stack.getLast().equals('(')) {
                    postfix.add(stack.removeLast().toString());
                }
                stack.removeLast();
            }
            else {
                if(stack.getLast().equals('('))
                    stack.addLast(input);
                else {
                    while(!stack.isEmpty() && !stack.getLast().equals('(') && getPrecedence(input) <= getPrecedence(stack.getLast())) {
                        postfix.add(stack.removeLast().toString());
                    }
                    stack.addLast(input);
                }
            }
        }
    }

    private int getPrecedence(char op) {
         if (op == '+' || op == '-')
                return 1;
         else if (op == '*' || op == '/')
                return 2;
         else if (op == '^')
                return 3;
         else return 0;
    }

    private void clearStack() {
        while(!stack.isEmpty()) {
            postfix.add(stack.removeLast().toString());
        }
    }

    public void printExpression() {
        for(String str : postfix) {
            System.out.print(str + ' ');
        }
    }

    public List<String> getPostfixAsList() {
        return postfix;
    }
}

class PostFixCalculator {
    private List<String> expression = new ArrayList<String>();
    private Deque<Double> stack = new ArrayDeque<Double>();

    public PostFixCalculator(List<String> postfix) {expression = postfix;}
    public BigDecimal result() {
        for(int i = 0; i != expression.size(); ++i) {
            if(Character.isDigit(expression.get(i).charAt(0))) {
                stack.addLast(Double.parseDouble(expression.get(i)));
            }
            else {
                double tempResult = 0;
                double temp;
                switch(expression.get(i))
                {
                    case "+": temp = stack.removeLast();
                              tempResult = stack.removeLast() + temp;
                              break;

                    case "-": temp = stack.removeLast();
                              tempResult = stack.removeLast() - temp;
                              break;

                    case "*": temp = stack.removeLast();
                              tempResult = stack.removeLast() * temp;
                              break;

                    case "/": temp = stack.removeLast();
                              tempResult = stack.removeLast() / temp;
                              break;
                }
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
    }
}

public class infixToPostfix {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("식을 입력하세요 : ");
		String calc = sc.nextLine();
		PostFixConverter pc = new PostFixConverter(calc);
		System.out.print("후위식 : ");
		pc.printExpression();
		System.out.println();
		PostFixCalculator pfc = new PostFixCalculator(pc.getPostfixAsList());
		System.out.print("결과 : ");
		System.out.println(pfc.result());
		sc.close();
	}
}