package sudoku.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import sudoku.arz.gui.SudokuWriter;

public class SimpleSudoWriter implements SudokuWriter {

	private Integer[][] zeCaseSoluce;

	private boolean canFork;

	public SimpleSudoWriter(Integer[][] soluce, boolean doFork) {
		zeCaseSoluce = soluce;
		canFork = doFork;
	}

	@Override
	public void setValueAt(int i, int j, int value, int fork) {

		if (zeCaseSoluce[i][j] != value) {
			if (!canFork) {
				fail("Expected :" + zeCaseSoluce[i][j] + " in  L" + (i + 1) + "C" + (j + 1) + " got " + value);
			}
		}
	}

	@Override
	public void removePossibleAt(int i, int j, int value) {

	}

	@Override
	public void setLevel(int state) {

	}

	@Override
	public void setSolved(boolean solved) {
		assertTrue(solved);

	}

	@Override
	public void forceValue(int i, int j, int value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doForkOn(int lineNb, int colNb, int possValue) {

	}

	@Override
	public void forceProgress() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setProgress(int i) {
		// TODO Auto-generated method stub

	}

}
