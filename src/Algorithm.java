
import java.awt.event.*;
import javax.swing.*;



public class Algorithm implements KeyListener {
	Num[][] num;
	JPanel panel;

	boolean up, down, left, right, numFlag;
	int winFlag=1;
	int value = 0;

	public Algorithm(JFrame frame) {
		panel = (JPanel) frame.getContentPane();
		num = new Num[4][4];
		up = true;
		down = true;
		left = true;
		right = true;
		numFlag = true;
		addNum();
		setNum();
		frame.addKeyListener(this);
	}

	private void addNum() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				num[i][j] = new Num();
				num[i][j].setHorizontalAlignment(JLabel.CENTER);
				num[i][j].setOpaque(true);
				panel.add(num[i][j]);
			}
		}
	}

	public void setNum() {
		while (numFlag) {
			int indexI = (int) (Math.random() * 4);
			int indexJ = (int) (Math.random() * 4);
			if (num[indexI][indexJ].getValue() == 0) {
				if (Math.random() < 0.5) {
					num[indexI][indexJ].setValue(2);
				} else {
					num[indexI][indexJ].setValue(4);
				}
				break;
			}
		}
	}

	public void judgeNum() {
		int sum = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (num[i][j].getValue() != 0) {
					sum++;
				}
			}

		}
		if (sum == 16)
			numFlag = false;

	}

	public void up() {
		int i;
		int j;
		int tmp;

		for (j = 0; j < 4; j++) {

			for (i = 0; i < 3; i++) {
				if (num[i][j].getValue() == num[i + 1][j].getValue()) {
					num[i][j].setValue(num[i][j].getValue() + num[i + 1][j].getValue());
					num[i + 1][j].setValue(0);
					numFlag = true;
				}
			}
			for (int m = 0; m < 3; m++) {
				for (i = 0; i < 3; i++) {
					if (num[i][j].getValue() == 0) {
						tmp = num[i][j].getValue();
						num[i][j].setValue(num[i + 1][j].getValue());
						num[i + 1][j].setValue(tmp);
					}
				}
			}
		}
	}

	public void down() {
		int i;
		int j;
		int tmp;
		for (j = 0; j < 4; j++) {
			for (i = 2; i >= 0; i--) {
				if (num[i][j].getValue() == num[i + 1][j].getValue()) {
					num[i + 1][j].setValue(num[i][j].getValue() + num[i + 1][j].getValue());
					num[i][j].setValue(0);
					numFlag = true;
				}
			}

			for (int m = 0; m < 3; m++) {
				for (i = 3; i > 0; i--) {
					if (num[i][j].getValue() == 0) {
						tmp = num[i][j].getValue();
						num[i][j].setValue(num[i - 1][j].getValue());
						num[i - 1][j].setValue(tmp);
					}
				}
			}
		}
	}

	public void left() {
		int i;
		int j;
		int tmp;
		for (i = 0; i < 4; i++) {
			for (j = 1; j < 4; j++) {
				if (num[i][j].getValue() == num[i][j - 1].getValue()) {
					num[i][j - 1].setValue(num[i][j].getValue() + num[i][j - 1].getValue());
					num[i][j].setValue(0);
					numFlag = true;
				}
			}

			for (int m = 0; m < 3; m++) {
				for (j = 0; j < 3; j++) {
					if (num[i][j].getValue() == 0) {
						tmp = num[i][j].getValue();
						num[i][j].setValue(num[i][j + 1].getValue());
						num[i][j + 1].setValue(tmp);
					}
				}
			}
		}
	}

	public void right() {
		int i;
		int j;
		int tmp;
		for (i = 0; i < 4; i++) {
			for (j = 2; j >= 0; j--) {
				if (num[i][j].getValue() == num[i][j + 1].getValue()) {
					num[i][j + 1].setValue(num[i][j].getValue() + num[i][j + 1].getValue());
					num[i][j].setValue(0);
					numFlag = true;
				}
			}

			for (int m = 0; m < 3; m++) {
				for (j = 3; j > 0; j--) {
					if (num[i][j].getValue() == 0) {
						tmp = num[i][j].getValue();
						num[i][j].setValue(num[i][j - 1].getValue());
						num[i][j - 1].setValue(tmp);
					}
				}
			}
		}
	}

	public void over() {
		if (numFlag == false & up == false & down == false & left == false & right == false) {
			JOptionPane.showMessageDialog(panel, "GAME OVER!", null, JOptionPane.PLAIN_MESSAGE);
			num[0][0].setText("RESTART");
			num[0][0].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					reStart();
				}
			});
			num[0][1].setText("EXIT!");
			num[0][1].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.exit(0);
				}

			});

		}
	}

	public int val() {
		
		//int value = 0;
		
		if(winFlag == 1&& value == 0){
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++){
					if(num[i][j].getValue() == 1024){
						value =1;
						winFlag = 2;
					}
				}
			return value;
		}
		
		if(winFlag == 2 &&value ==0){
			//value =0;
			for(int i=0;i<4;i++)
				for(int j=0;j<4;j++){
					if(num[i][j].getValue() == 2048){
						value =1;
						//winFlag = 2;
					}
				}
			return value;
		}
		
		return value;
		
	}

	public void win() {

		val();
		if (numFlag == true && up == true && down == true && left == true && right == true && val() == 1) {
			value = 0;
			JOptionPane.showMessageDialog(panel, null, "YOU WIN!", JOptionPane.PLAIN_MESSAGE);
			num[0][0].setText("RESTART!");
			num[0][0].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					reStart();
				}
			});
			num[0][1].setText("EXIT!");
			num[0][1].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					System.exit(0);
				}

			});
			num[0][2].setText("GOON!");
			num[0][2].addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e)
		   	  {
		   		  goon();
		   		  }
		   	  }
		     );
		}
	}

	public void reStart() {
		numFlag = true;
		up = true;
		down = true;
		left = true;
		right = true;
		for (int j = 0; j < 4; j++) {
			for (int i = 0; i < 4; i++)
				num[i][j].setValue(0);
			setNum();
		}
	}
	 public void goon()
	  {
		   for (int i = 0; i < 4; i++) 
			   for (int j = 0; j < 4; j++) 
			    {
					num[i][j].setValue(num[i][j].getValue());
			    }
			}
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if (up) {
				up();
			}
			judgeNum();
			setNum();
			over();

			if (numFlag == false) {
				up = false;
			} else {
				up = true;
				down = true;
				left = true;
				right = true;

			}
			win();
			break;
		case KeyEvent.VK_DOWN:
			if (down) {
				down();
			}
			judgeNum();
			setNum();
			over();

			if (numFlag == false) {
				down = false;
			} else {
				up = true;
				down = true;
				left = true;
				right = true;

			}
			win();
			break;
		case KeyEvent.VK_LEFT:
			if (left) {
				left();
			}
			judgeNum();
			setNum();
			over();

			if (numFlag == false) {
				left = false;
			} else {
				up = true;
				down = true;
				left = true;
				right = true;

			}
			win();
			break;

		case KeyEvent.VK_RIGHT:
			if (right) {
				right();
			}
			judgeNum();
			setNum();
			over();

			if (numFlag == false) {
				right = false;
			} else {
				up = true;
				down = true;
				left = true;
				right = true;

			}
			win();
			break;

		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

}
