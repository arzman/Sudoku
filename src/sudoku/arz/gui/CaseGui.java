package sudoku.arz.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CaseGui extends JPanel implements KeyListener, FocusListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7076719313515862787L;

	private JLabel _label;

	private  Font fontNormal = new Font("TimesRoman ", Font.BOLD, 30);;

	public CaseGui(int i, int j) {

		_label = new JLabel("");
		_label.setFont(fontNormal);
		setBackground(Color.WHITE);
		add(_label);
		addKeyListener(this);
		addMouseListener(this);
		addFocusListener(this);
		setFocusable(true);
		setSize(50, 50);

	}

	public void setText(String string) {
		_label.setText(string);

	}

	public String getText() {
		return _label.getText();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		try {
			Integer.parseInt("" + e.getKeyChar());
			setText("" + e.getKeyChar());

		} catch (NumberFormatException ex) {
			
			try{
				Integer.parseInt(getText());
			}catch(NumberFormatException ex2){
				setText("");
			}
			
			
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void focusGained(FocusEvent arg0) {
		setBackground(Color.LIGHT_GRAY);

	}

	@Override
	public void focusLost(FocusEvent arg0) {
		setBackground(Color.WHITE);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		requestFocus();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void setColor(int forkLvl) {
		
		if(forkLvl==0){
			_label.setForeground(Color.BLACK);
		}
		if(forkLvl==1){
			_label.setForeground(Color.DARK_GRAY);
		}
		if(forkLvl==2){
			_label.setForeground(Color.BLUE);
		}
		if(forkLvl==3){
			_label.setForeground(Color.MAGENTA);
		}
		if(forkLvl==4){
			_label.setForeground(Color.GREEN);
		}
		if(forkLvl==5){
			_label.setForeground(Color.ORANGE);
		}
		if(forkLvl==6){
			_label.setForeground(Color.RED);
		}
		if(forkLvl==7){
			_label.setForeground(Color.GRAY);
		}
		if(forkLvl==8){
			_label.setForeground(Color.YELLOW);
		}
		if(forkLvl==9){
			_label.setForeground(Color.CYAN);
		}
		if(forkLvl==10){
			_label.setForeground(Color.PINK);
		}
		

	}

}
