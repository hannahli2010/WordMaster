package WordMaster;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
class Visual extends JPanel{
	private JButton[][] letters = new JButton[5][5];
	private JButton enter = new JButton();
	
	public Visual(){
		JPanel pnlWord = new JPanel(new GridLayout(5, 5, 10, 30));
		Dimension size1 = new Dimension(500, 500);
	//	pnlWord.setLocation(0, 0);
		
		pnlWord.setPreferredSize(size1);
		add(pnlWord);
		
		for(int x = 0; x < 5; x++){
			for(int y = 0; y<5; y++){
				letters[x][y] = new JButton(x+" "+y);
				letters[x][y].setVerticalTextPosition(JLabel.BOTTOM);
		        letters[x][y].setHorizontalTextPosition(JLabel.RIGHT);
		        letters[x][y].setOpaque(true);
		        letters[x][y].setBackground(Color.GREEN);
		        
				pnlWord.add(letters[x][y]);	
			}
		}
				
		JPanel pnlOther =new JPanel();
		Dimension size2 = new Dimension(200, 500);
		
		pnlOther.setLocation(100,100);
		pnlOther.setPreferredSize(size2);
		pnlOther.setBackground(Color.MAGENTA);
		add(pnlOther);
		
		enter.setText("Play");
		enter.setOpaque(true);
		enter.setBackground(Color.ORANGE);
		pnlOther.add(enter);
		
	}
	
	public void setBackground(int count, int place, int kind){
		switch(kind){
		case 0: letters[count][place].setBackground(Color.RED);
			break;
		case 1: letters[count][place].setBackground(Color.GRAY);
			break;
		case 2: letters[count][place].setBackground(Color.YELLOW);
			break;
		default: letters[count][place].setBackground(Color.WHITE);
			break;
		}
	}
	
	public void enterWord(char[] word, int count){
		for(int x = 0; x < 5; x++){
			letters[count][x].setText(word[x]+"");
		}
		System.out.println("HEY");
	}
	
	
}
