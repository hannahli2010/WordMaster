package WordMaster;

//import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WordGame extends JPanel{
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String [] args){
		
		play();
	}
	
	public static void play(){
		int count = 0;
		Visual view = new Visual();
		String [] vocab = getVocab();
		

		if(vocab == null){
			System.out.println("Error, please check to make sure the file is in the right location.");
			return;
		}
		
		char [] mystery = vocab[(int) (Math.random()*vocab.length)].toCharArray();
		char [] guess = new char[5];
		printWord(mystery);
		
		createGUI(view);
		
		for(int x = 0; x < 5; x++){
			guess = guessWord(mystery, view, vocab);
			view.enterWord(guess, count);
			
			if (check(mystery, guess, view, count) == true)
				x = 5;
			count++;
		}
		
		if (count == 5){
			System.out.print("Nice try, the word was: ");
			printWord(mystery);
		}
		else
			System.out.println("Nice!");
				
	}
	
	@SuppressWarnings("exports")
	public static char[] guessWord(char [] word, Visual game, String []vocab){
		String guess;
		char [] input = new char[5];
		
		System.out.println("Enter one letter at a time");
		do{
			guess = scan.next();
		}while(guess.length() != 5 || findWord(vocab, guess, 0, vocab.length) == -1);
		
		for(int x = 0; x<5;x++){
			input[x] = guess.toLowerCase().charAt(x);
		}
		
		return input;
	
	}
	// merge into check and output whether the word is right or not
	
	@SuppressWarnings("exports")
	public static boolean check(char[] word, char[] guess, Visual game, int turn){
		char [] result = new char[5];
		char [] mystery = new char[5];
		boolean same = true;
		
		copyWord(word, mystery);
		
		for (int x = 0; x < mystery.length; x++){
			for(int y = 0; y < 5; y++){
				if (mystery[x] == guess[y] && x == y){
					game.setBackground(turn, x, 2);
					result[x]='2';
					mystery[x] = ' ';
					guess[y] = ' ';
					// removes the letters from checking because they are already used and are certainly in the right place
				}
			}
		}
		
		for (int x = 0; x < 5; x++){
			for(int y = 0; y < 5; y++){
				if (mystery[x] == guess[y] && mystery[x] != ' '){
					// not ' ' because those are in the right place from previous for loop
					result[y]='1';
					game.setBackground(turn, y, 1);
					mystery[x] = ' ';
					guess[y] = ' ';
					same = false;
					// removes the letters from checking because they are already used
				}
			}
		}
		
		for (int x = 0; x < 5; x++){
			if(result[x] != '1' && result[x] != '2'){
				game.setBackground(turn, x, 0);
				result[x]='0';
				same = false;
			}
			// if not in word
		}
		printWord(result);
		
		return same;
	}
	
	public static String[] getVocab() {
		// gets mystery word
		
		try {

			File file = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "WordMaster" + File.separator +"vocab.txt");
			BufferedReader in = new BufferedReader(new FileReader(file));
			int n = getNumLines(file);
			String [] vocab = new String[n];
					
			for(int x = 0; x < getNumLines(file); x++){
				vocab[x] = in.readLine();
			} 
			
				
			in.close();			
			return vocab;
				
		}
		catch (IOException e) {
			System.out.println("file not found");
		}
	
		return null;		
	}
	
	public static int findWord(String [] vocab, String target, int l, int h){
		if(h < l) {
			return -1;
		}
		int m = l + (h-l)/2;
		int compare = target.compareTo(vocab[m]);
		if(compare == 0) {
			return m;
		} else if(compare > 0) {
			l = m + 1;
		} else {
			h = m - 1;
		}
		
		return findWord(vocab, target, l, h);
	}

	public static int getNumLines(File path) throws IOException{
		// for input, this way there is no need to know/count how many lines there are in the text file
		
		int numLines = 0;
		try{
			BufferedReader in = new BufferedReader(new FileReader(path));
			while(in.readLine() != null){
				numLines++;
			}
			in.close();
		}
		
		catch (IOException e) {
			System.out.println("file not found");
		}
		return numLines;
	}
	
	public static char[] copyWord(char[] word, char[] copy){
		for(int x = 0; x < word.length; x++){
			copy[x] = word[x];
		}
		
		return copy;
	}

	public static void printWord(char[] word){
		for(int x = 0; x < 5; x++){
			System.out.print(word[x]);
		}
		System.out.println("");
	}
	
	public static boolean equalWord(char[] guess, String compare){
		// don't need this
		for(int x = 0; x< 5; x++){
			if (guess[x] != compare.charAt(x)){
				return false;
			}
		}
		return true;
	}
	
	public static boolean blankWord(char [] word){
		// makes sure that there is indeed a mystery word
		boolean blank = true;
		for (int x = 0; x < 5; x++){
			if (word[x] != ' '){
				blank = false;
			}
		}
		return blank;
	}

	@SuppressWarnings("exports")
	public static void createGUI(Visual game){
		JFrame frame = new JFrame("Word Master");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1200, 600);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		
		frame.setVisible(true);
	}
	
}