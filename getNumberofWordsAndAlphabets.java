package test;

import java.util.Scanner;

class Func {
	public String[] lowerAlpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	public String[] upperAlpha = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	public int[] howmanylower = new int[26];
	public int[] howmanyupper = new int[26];
	
	public Func() {
		for(int i = 0; i < 26; i++) {
			this.howmanylower[i] = 0;
			this.howmanyupper[i] = 0;
		}
	}
	
	public void wordCount(String santance) {
		int res = 0;
		String word = "";
        for(int i = 0; i < santance.length(); i++)  {
            String c = String.valueOf(santance.charAt(i));
            if(c.equals(" "))   {
                word = "";
            }else   {
                if(word.length() == 0)  {
                    res++;
                }
                word = c;
            }
        }
        System.out.print("단어의 개수는 : ");
        System.out.println(res);
	}
	
	public void alphaCount(String santance) {
		Func alpha = new Func();
		for(int i = 0; i < santance.length(); i++) {
			String c = String.valueOf(santance.charAt(i));
			for(int j = 0; j < 26; j++) {
				if(c.equals(alpha.lowerAlpha[j])) {
					int lowercount = alpha.howmanylower[j] + 1;
					alpha.howmanylower[j] = lowercount;
				}
				if(c.equals(alpha.upperAlpha[j])) {
					int uppercount = alpha.howmanyupper[j] + 1;
					alpha.howmanyupper[j] = uppercount;
				}
			}
		}
		for(int i = 0; i < 26; i++) {
			if(alpha.howmanylower[i] != 0) {
				System.out.print(alpha.lowerAlpha[i]);
				System.out.print(" : ");
				System.out.print(alpha.howmanylower[i]);
				System.out.println("개 ");
			}
			if(alpha.howmanyupper[i] != 0) {
				System.out.print(alpha.upperAlpha[i]);
				System.out.print(" : ");
				System.out.print(alpha.howmanyupper[i]);
				System.out.println("개");
			}
		}
	}
}

public class homework {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("문자열을 입력하세요 : ");
        String s = sc.nextLine();
        Func func = new Func();
        func.alphaCount(s);
        func.wordCount(s);
        sc.close();
	}
}
