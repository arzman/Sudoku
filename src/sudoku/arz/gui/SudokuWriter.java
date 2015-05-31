package sudoku.arz.gui;

public interface SudokuWriter {
	
	
	
	public void setValueAt(int i, int j, int value, int forkLvl);
	
	
	public void removePossibleAt(int i, int j, int value);


	public void setLevel(int state);


	public void setSolved(boolean solved);


	public void forceValue(int i, int j, int value);


	public void doForkOn(int lineNb, int colNb, int possValue);


	public void forceProgress();


	public void setProgress(int i);


}
