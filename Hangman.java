package test;

import java.util.Arrays;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hangman extends JFrame {
	
	public String[] words = {"apple", "address", "account", "accept", "adapt", "adopt", "adjust", "addmit", "become", "because",
			"bargain", "banana", "bachelor", "banjo", "beaver", "batter", "capacity", "cycle", "credit", "charge", "choose", 
			"command", "comedy", "compare", "demand", "diamond", "danger", "dynamic", "draft", "during", "duplicate", "estimate",
			"economy", "erase", "effect", "emerge", "enable", "either", "feature", "facility", "finder", "favor",
			"failure", "forward", "generate", "grail", "gundam", "grinder", "gather", "government", "ghostly", "happen",
			"health", "helloworld", "however", "household", "hypothesis", "hygiene", "implement", "internet", "interest",
			"identify", "iatrogenic", "ignore", "innovator", "jealous", "judge", "journal", "january", "jurisdiction",
			"jacket", "jeopardize", "korean", "knowledge", "knock", "kindly", "kitchen", "kingdom", "knight", "learning",
			"laboratory", "landscape", "locate", "london", "lebanon", "lateran", "morning", "making", "metro", "marvel",
			"manage", "mistake", "mouse", "notebook", "necessary", "nature", "neither", "november", "nimble",
			"nighemare", "operation", "overhead", "oddeye", "oasis", "organic", "overwatch", "ordermade", "partty",
			"parking", "packing", "package", "particular", "perform", "picking", "quality", "quantity", "queue", "quest",
			"quirkiness", "quietly", "quarrel", "random", "radiation", "ridiculous", "readme", "railroad", "recognize",
			"rather", "string", "saint", "scheme", "schedule", "satisfy", "secure", "several", "terminal", "tight",
			"teacher", "testing", "toward", "tension", "typical", "underware", "ultimate", "usually", "ubertexi",
			"unbreakable", "ultra", "ulceration", "violet", "vacation", "verify", "vessel", "virtually", "valuable",
			"vandalism", "weather", "whereas", "whatever", "wander", "welcome", "wirebound", "withdraw", "xylophone",
			"xenophobic", "xylene", "xanadu", "xilinx", "xenoglossy", "xizang", "yesterday", "yourself", "yeomanly",
			"yiddisher", "yorkist", "yodeler", "yearday", "zodiac", "zoology", "zigzag", "zeitgeist", "zygote", "zipper",
			"zombie", "zookeeper", "drama", "esencia", "freedom", "goldmine", "homeless", "intern", "javascript", "kamping",
			"lognation", "mongolian", "notepad", "oracle", "python", "quarter", "rainbow", "short", "torpedo", "underway",
			"victory", "wonder", "xerostomia", "youngman", "zakat"};
	public boolean[] selected = new boolean[words.length];
	
	public String realWord = "";
	
	public JButton[] alphaButtons = {new JButton("a"), new JButton("b"), new JButton("c"), new JButton("d"), new JButton("e"), new JButton("f"), new JButton("g"),
			new JButton("h"), new JButton("i"), new JButton("j"), new JButton("k"), new JButton("l"), new JButton("m"), new JButton("n"), new JButton("o"),
			new JButton("p"), new JButton("q"), new JButton("r"), new JButton("s"), new JButton("t"), new JButton("u"), new JButton("v"), new JButton("w"),
			new JButton("x"), new JButton("y"), new JButton("z")};
	public JButton low = new JButton("low");
	public JButton middle = new JButton("middle");
	public JButton high = new JButton("high");
	public JButton start = new JButton("start");
	
	public JLabel guess = new JLabel("0");
	public JLabel win = new JLabel("0");
	public JLabel lose = new JLabel("0");
	public JLabel ratio = new JLabel("0.00");
	public JLabel levelHow = new JLabel("low");
	public JLabel word = new JLabel("--------");
	
	public String randomWord() {
		Random random = new Random();
		int randomNum = random.nextInt(words.length);
		while(selected[randomNum]) {
			randomNum = random.nextInt(words.length);
		}
		selected[randomNum] = true;
		return words[randomNum];
	}
	
	public String percent(int a, int b) {
		if(a == 0 || b == 0) {
			if(a > 0 && b == 0) {
				return "100.00";
			}
			else if(a == 0 && b > 0) {
				return "0.00";
			}
			else if(a == 0 && b == 0) {
				return "0.00";
			}
		}
		double win = a;
		double lose = b;
		double result1 = win / (win + lose);
		result1 = result1 * 100;
		String result2 = String.format("%.2f", result1);
		return result2;
	}
	
	Hangman() {
		Arrays.fill(selected, false);
		setTitle("Hangman");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel alphaButton = new JPanel();
		JPanel levelButton = new JPanel();
		JPanel startButton = new JPanel();
		GridLayout alphaGrid = new GridLayout(3, 9);
		GridLayout levelGrid = new GridLayout(1, 4);
		GridLayout startGrid = new GridLayout(7, 2);
		alphaGrid.setVgap(5);
		levelGrid.setVgap(5);
		startGrid.setVgap(5);
		alphaButton.setLayout(alphaGrid);
		levelButton.setLayout(levelGrid);
		startButton.setLayout(startGrid);
		HangmanListener listener = new HangmanListener();
		for(int i = 0; i < 26; i++) {
			alphaButtons[i].addActionListener(listener);
			alphaButton.add(alphaButtons[i]);
		}
		for(int i = 0; i < 26; i++) {
			alphaButtons[i].setEnabled(false);
		}
		JLabel levelSelect = new JLabel("Select");
		levelButton.add(levelSelect);
		low.addActionListener(listener);
		middle.addActionListener(listener);
		high.addActionListener(listener);
		levelButton.add(low);
		levelButton.add(middle);
		levelButton.add(high);
		low.setEnabled(false);
		JLabel guessText = new JLabel("Hanging Remain :");
		JLabel winText =  new JLabel("Win :");
		JLabel loseText = new JLabel("Lose :");
		JLabel levelText = new JLabel("LEVEL :");
		JLabel ratioText = new JLabel("Win/Lose(%) :");
		JLabel startText = new JLabel("Start!");
		start.addActionListener(listener);
		startButton.add(winText);
		startButton.add(win);
		startButton.add(loseText);
		startButton.add(lose);
		startButton.add(ratioText);
		startButton.add(ratio);
		startButton.add(guessText);
		startButton.add(guess);
		startButton.add(levelText);
		startButton.add(levelHow);
		startButton.add(startText);
		startButton.add(start);
		startButton.add(word);
		add(startButton, BorderLayout.NORTH);
		add(alphaButton, BorderLayout.CENTER);
		add(levelButton, BorderLayout.SOUTH);
		setSize(600, 600);
		setVisible(true);
	}
	
	class HangmanListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("start")) {
				realWord = randomWord();
				String str = "";
				for(int i = 0; i < realWord.length(); i++) {
					str += "-";
				}
				word.setText(str);
				start.setEnabled(false);
				low.setEnabled(false);
				middle.setEnabled(false);
				high.setEnabled(false);
				for(int i = 0; i < 26; i++) {
					alphaButtons[i].setEnabled(true);
				}
				if(levelHow.getText().equals("low")) {
					guess.setText("14");
				}
				else if(levelHow.getText().equals("middle")) {
					guess.setText("12");
				}
				else {
					guess.setText("10");
				}
			}
			if(b.getText().equals("low")) {
				low.setEnabled(false);
				middle.setEnabled(true);
				high.setEnabled(true);
				levelHow.setText("low");
			}
			if(b.getText().equals("middle")) {
				middle.setEnabled(false);
				low.setEnabled(true);
				high.setEnabled(true);
				levelHow.setText("middle");
			}
			if(b.getText().equals("high")) {
				high.setEnabled(false);
				middle.setEnabled(true);
				low.setEnabled(true);
				levelHow.setText("high");
			}
			for(int i = 0; i < 26; i++) {
				if(b.getText().equals(alphaButtons[i].getText())) {
					alphaButtons[i].setEnabled(false);
					String strstrip = word.getText();
					for(int j = 0; j < realWord.length(); j++) {
						if(realWord.charAt(j) == b.getText().charAt(0)) {
							strstrip = strstrip.substring(0, j) + realWord.charAt(j) + strstrip.substring(j + 1, strstrip.length());
						}
					}
					int guessInt = Integer.parseInt(guess.getText()) - 1;
					if(guessInt == 0) {
						for(int k = 0; k < 26; k++) {
							alphaButtons[k].setEnabled(false);
						}
						start.setEnabled(true);
						if(levelHow.getText().equals("low")) {
							low.setEnabled(false);
							middle.setEnabled(true);
							high.setEnabled(true);
						}
						if(levelHow.getText().equals("middle")) {
							low.setEnabled(true);
							middle.setEnabled(false);
							high.setEnabled(true);
						}
						if(levelHow.getText().equals("high")) {
							low.setEnabled(true);
							middle.setEnabled(true);
							high.setEnabled(false);
						}
						if(strstrip.equals(realWord)) {
							int winInt = Integer.parseInt(win.getText()) + 1;
							int loseInt = Integer.parseInt(lose.getText());
							ratio.setText(percent(winInt, loseInt));
							win.setText(String.valueOf(winInt));
							strstrip += "  승리!";
						}
						else {
							int winInt = Integer.parseInt(win.getText());
							int loseInt = Integer.parseInt(lose.getText()) + 1;
							ratio.setText(percent(winInt, loseInt));
							lose.setText(String.valueOf(loseInt));
							strstrip += "  정답: " + realWord + ", 패배!";
						}
					}
					else {
						if(strstrip.equals(realWord)) {
							int winInt = Integer.parseInt(win.getText()) + 1;
							int loseInt = Integer.parseInt(lose.getText());
							ratio.setText(percent(winInt, loseInt));
							win.setText(String.valueOf(winInt));
							strstrip += "  승리!";
							for(int k = 0; k < 26; k++) {
								alphaButtons[k].setEnabled(false);
							}
							start.setEnabled(true);
							if(levelHow.getText().equals("low")) {
								low.setEnabled(false);
								middle.setEnabled(true);
								high.setEnabled(true);
							}
							if(levelHow.getText().equals("middle")) {
								low.setEnabled(true);
								middle.setEnabled(false);
								high.setEnabled(true);
							}
							if(levelHow.getText().equals("high")) {
								low.setEnabled(true);
								middle.setEnabled(true);
								high.setEnabled(false);
							}
						}
					}
					word.setText(strstrip);
					guess.setText(String.valueOf(guessInt));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Hangman();
	}
}
