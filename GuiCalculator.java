package test;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
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
                double temp = 0;
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
                    case "^": temp = stack.removeLast();
                }
                stack.addLast(tempResult);
            }
        }
        BigDecimal big = new BigDecimal(stack.removeLast()).setScale(3, BigDecimal.ROUND_HALF_UP);
        return big;
    }
}

class homeworkgui extends JFrame {
	public String[] buttons = {"1", "2", "3", "*", "/", "4", "5", "6", "+", "-", "7", "8", "9", "(", ")", ".", "0", "BS", "=", "Clear"};
	public String[] number = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "+", "-", "*", "/", ".", "(", ")"};
	public JTextField infix = new JTextField(50);
	public JTextField result = new JTextField(50);
	
	homeworkgui() {
		setTitle("GridLayout");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel buttonpanel = new JPanel();
		GridLayout grid = new GridLayout(4, 5);
		grid.setVgap(5);
		buttonpanel.setLayout(grid);
		MyActionListener listener = new MyActionListener();
		for(int i = 0; i < 20; i++) {
			JButton button = new JButton(this.buttons[i]);
			button.addActionListener(listener);
			buttonpanel.add(button);
		}
		add(this.infix, BorderLayout.NORTH);
		add(this.result, BorderLayout.SOUTH);
		add(buttonpanel, BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
	}
	
	class MyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			for(int i = 0; i < 17; i++) {
				if(b.getText().equals(number[i])) {
					String sick = infix.getText();
					sick += number[i];
					infix.setText(sick);
				}
			}
			if(b.getText().equals("BS")) {
				String sick = infix.getText();
				if(sick.length() > 0) {
					sick = sick.substring(0, sick.length()-1);	
				}
				infix.setText(sick);
			}
			if(b.getText().equals("=")) {
				String sick = infix.getText();
				PostFixConverter pc = new PostFixConverter(sick);
				PostFixCalculator pfc = new PostFixCalculator(pc.getPostfixAsList());
				result.setText(pfc.result().toString());
			}
			if(b.getText().equals("Clear")) {
				infix.setText("");
				result.setText("");
			}
		}
	}
	
	public static void main(String[] args) {
		new homeworkgui();
	}
}
