package sudoku.test;

import static org.junit.Assert.*;

import org.junit.Test;

import sudoku.arz.gui.SudokuWriter;
import sudoku.arz.solver.SudoSolverOne;

public class SudokuTests {

	@Test
	public void TwinsTest() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][4] = 6;
		_zeCase[0][6] = 8;
		_zeCase[0][8] = 2;
		_zeCase[1][1] = 5;
		_zeCase[2][6] = 7;
		_zeCase[3][3] = 5;
		_zeCase[3][5] = 9;
		_zeCase[3][7] = 3;
		_zeCase[4][0] = 6;
		_zeCase[4][8] = 1;
		_zeCase[5][3] = 7;
		_zeCase[6][3] = 3;
		_zeCase[6][6] = 5;
		_zeCase[6][7] = 9;
		_zeCase[7][0] = 8;
		_zeCase[7][2] = 2;
		_zeCase[8][0] = 1;

		final Integer[][] _zeCaseSoluce = new Integer[9][9];

		_zeCaseSoluce[0][0] = 3;
		_zeCaseSoluce[0][1] = 4;
		_zeCaseSoluce[0][2] = 7;
		_zeCaseSoluce[0][3] = 9;
		_zeCaseSoluce[0][4] = 6;
		_zeCaseSoluce[0][5] = 5;
		_zeCaseSoluce[0][6] = 8;
		_zeCaseSoluce[0][7] = 1;
		_zeCaseSoluce[0][8] = 2;
		_zeCaseSoluce[1][0] = 2;
		_zeCaseSoluce[1][1] = 5;
		_zeCaseSoluce[1][2] = 6;
		_zeCaseSoluce[1][3] = 1;
		_zeCaseSoluce[1][4] = 7;
		_zeCaseSoluce[1][5] = 8;
		_zeCaseSoluce[1][6] = 3;
		_zeCaseSoluce[1][7] = 4;
		_zeCaseSoluce[1][8] = 9;
		_zeCaseSoluce[2][0] = 9;
		_zeCaseSoluce[2][1] = 8;
		_zeCaseSoluce[2][2] = 1;
		_zeCaseSoluce[2][3] = 2;
		_zeCaseSoluce[2][4] = 4;
		_zeCaseSoluce[2][5] = 3;
		_zeCaseSoluce[2][6] = 7;
		_zeCaseSoluce[2][7] = 6;
		_zeCaseSoluce[2][8] = 5;
		_zeCaseSoluce[3][0] = 4;
		_zeCaseSoluce[3][1] = 2;
		_zeCaseSoluce[3][2] = 8;
		_zeCaseSoluce[3][3] = 5;
		_zeCaseSoluce[3][4] = 1;
		_zeCaseSoluce[3][5] = 9;
		_zeCaseSoluce[3][6] = 6;
		_zeCaseSoluce[3][7] = 3;
		_zeCaseSoluce[3][8] = 7;
		_zeCaseSoluce[4][0] = 6;
		_zeCaseSoluce[4][1] = 7;
		_zeCaseSoluce[4][2] = 3;
		_zeCaseSoluce[4][3] = 4;
		_zeCaseSoluce[4][4] = 8;
		_zeCaseSoluce[4][5] = 2;
		_zeCaseSoluce[4][6] = 9;
		_zeCaseSoluce[4][7] = 5;
		_zeCaseSoluce[4][8] = 1;
		_zeCaseSoluce[5][0] = 5;
		_zeCaseSoluce[5][1] = 1;
		_zeCaseSoluce[5][2] = 9;
		_zeCaseSoluce[5][3] = 7;
		_zeCaseSoluce[5][4] = 3;
		_zeCaseSoluce[5][5] = 6;
		_zeCaseSoluce[5][6] = 2;
		_zeCaseSoluce[5][7] = 8;
		_zeCaseSoluce[5][8] = 4;
		_zeCaseSoluce[6][0] = 7;
		_zeCaseSoluce[6][1] = 6;
		_zeCaseSoluce[6][2] = 4;
		_zeCaseSoluce[6][3] = 3;
		_zeCaseSoluce[6][4] = 2;
		_zeCaseSoluce[6][5] = 1;
		_zeCaseSoluce[6][6] = 5;
		_zeCaseSoluce[6][7] = 9;
		_zeCaseSoluce[6][8] = 8;
		_zeCaseSoluce[7][0] = 8;
		_zeCaseSoluce[7][1] = 9;
		_zeCaseSoluce[7][2] = 2;
		_zeCaseSoluce[7][3] = 6;
		_zeCaseSoluce[7][4] = 5;
		_zeCaseSoluce[7][5] = 4;
		_zeCaseSoluce[7][6] = 1;
		_zeCaseSoluce[7][7] = 7;
		_zeCaseSoluce[7][8] = 3;
		_zeCaseSoluce[8][0] = 1;
		_zeCaseSoluce[8][1] = 3;
		_zeCaseSoluce[8][2] = 5;
		_zeCaseSoluce[8][3] = 8;
		_zeCaseSoluce[8][4] = 9;
		_zeCaseSoluce[8][5] = 7;
		_zeCaseSoluce[8][6] = 4;
		_zeCaseSoluce[8][7] = 2;
		_zeCaseSoluce[8][8] = 6;

		SudokuWriter writer = new SimpleSudoWriter(_zeCaseSoluce,true);

		SudoSolverOne solver = new SudoSolverOne(_zeCase, 2, writer, 0);
		solver.resolve();

	}

	@Test
	public void NishioTest() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][1] = 3;
		_zeCase[0][2] = 9;
		_zeCase[1][3] = 7;
		_zeCase[1][7] = 6;
		_zeCase[2][1] = 8;
		_zeCase[2][6] = 5;
		_zeCase[3][0] = 7;
		_zeCase[3][1] = 4;
		_zeCase[3][7] = 2;
		_zeCase[4][5] = 9;
		_zeCase[4][8] = 7;
		_zeCase[5][4] = 8;
		_zeCase[6][4] = 3;
		_zeCase[6][6] = 8;
		_zeCase[7][0] = 4;
		_zeCase[7][3] = 2;
		_zeCase[8][0] = 5;

		final Integer[][] zeCaseSoluce = new Integer[9][9];

		zeCaseSoluce[0][0] = 1;
		zeCaseSoluce[0][1] = 3;
		zeCaseSoluce[0][2] = 9;
		zeCaseSoluce[0][3] = 6;
		zeCaseSoluce[0][4] = 5;
		zeCaseSoluce[0][5] = 2;
		zeCaseSoluce[0][6] = 7;
		zeCaseSoluce[0][7] = 8;
		zeCaseSoluce[0][8] = 4;
		zeCaseSoluce[1][0] = 2;
		zeCaseSoluce[1][1] = 5;
		zeCaseSoluce[1][2] = 4;
		zeCaseSoluce[1][3] = 7;
		zeCaseSoluce[1][4] = 1;
		zeCaseSoluce[1][5] = 8;
		zeCaseSoluce[1][6] = 3;
		zeCaseSoluce[1][7] = 6;
		zeCaseSoluce[1][8] = 9;
		zeCaseSoluce[2][0] = 6;
		zeCaseSoluce[2][1] = 8;
		zeCaseSoluce[2][2] = 7;
		zeCaseSoluce[2][3] = 9;
		zeCaseSoluce[2][4] = 4;
		zeCaseSoluce[2][5] = 3;
		zeCaseSoluce[2][6] = 5;
		zeCaseSoluce[2][7] = 1;
		zeCaseSoluce[2][8] = 2;
		zeCaseSoluce[3][0] = 7;
		zeCaseSoluce[3][1] = 4;
		zeCaseSoluce[3][2] = 5;
		zeCaseSoluce[3][3] = 3;
		zeCaseSoluce[3][4] = 6;
		zeCaseSoluce[3][5] = 1;
		zeCaseSoluce[3][6] = 9;
		zeCaseSoluce[3][7] = 2;
		zeCaseSoluce[3][8] = 8;
		zeCaseSoluce[4][0] = 8;
		zeCaseSoluce[4][1] = 6;
		zeCaseSoluce[4][2] = 1;
		zeCaseSoluce[4][3] = 5;
		zeCaseSoluce[4][4] = 2;
		zeCaseSoluce[4][5] = 9;
		zeCaseSoluce[4][6] = 4;
		zeCaseSoluce[4][7] = 3;
		zeCaseSoluce[4][8] = 7;
		zeCaseSoluce[5][0] = 3;
		zeCaseSoluce[5][1] = 9;
		zeCaseSoluce[5][2] = 2;
		zeCaseSoluce[5][3] = 4;
		zeCaseSoluce[5][4] = 8;
		zeCaseSoluce[5][5] = 7;
		zeCaseSoluce[5][6] = 1;
		zeCaseSoluce[5][7] = 5;
		zeCaseSoluce[5][8] = 6;
		zeCaseSoluce[6][0] = 9;
		zeCaseSoluce[6][1] = 2;
		zeCaseSoluce[6][2] = 6;
		zeCaseSoluce[6][3] = 1;
		zeCaseSoluce[6][4] = 3;
		zeCaseSoluce[6][5] = 4;
		zeCaseSoluce[6][6] = 8;
		zeCaseSoluce[6][7] = 7;
		zeCaseSoluce[6][8] = 5;
		zeCaseSoluce[7][0] = 4;
		zeCaseSoluce[7][1] = 1;
		zeCaseSoluce[7][2] = 8;
		zeCaseSoluce[7][3] = 2;
		zeCaseSoluce[7][4] = 7;
		zeCaseSoluce[7][5] = 5;
		zeCaseSoluce[7][6] = 6;
		zeCaseSoluce[7][7] = 9;
		zeCaseSoluce[7][8] = 3;
		zeCaseSoluce[8][0] = 5;
		zeCaseSoluce[8][1] = 7;
		zeCaseSoluce[8][2] = 3;
		zeCaseSoluce[8][3] = 8;
		zeCaseSoluce[8][4] = 9;
		zeCaseSoluce[8][5] = 6;
		zeCaseSoluce[8][6] = 2;
		zeCaseSoluce[8][7] = 4;
		zeCaseSoluce[8][8] = 1;

		SudokuWriter writer = new SimpleSudoWriter(zeCaseSoluce,true);

		SudoSolverOne solver = new SudoSolverOne(_zeCase, 2, writer, 0);
		solver.resolve();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
			assertTrue(solver.getGrille().getValueAt(i, j)==zeCaseSoluce[i][j]);
			}
		}

	}
	
	@Test
	public void Level9Test() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][1] = 3;
		_zeCase[0][4] = 9;
		_zeCase[0][6] = 1;
		_zeCase[1][2] = 9;
		_zeCase[1][3] = 5;
		_zeCase[1][7] = 8;
		_zeCase[1][8] = 7;
		_zeCase[2][2] = 4;
		_zeCase[2][4] = 6;
		_zeCase[2][5] = 8;
		_zeCase[3][0] = 3;
		_zeCase[3][1] = 7;
		_zeCase[3][6] = 9;
		_zeCase[3][7] = 4;
		_zeCase[4][0] = 9;
		_zeCase[4][8] = 5;
		_zeCase[5][2] = 6;
		_zeCase[5][7] = 1;
		_zeCase[6][0] = 5;
		_zeCase[6][2] = 7;
		_zeCase[6][5] = 6;
		_zeCase[6][8] = 9;
		_zeCase[7][3] = 8;
		_zeCase[7][4] = 2;
		_zeCase[7][8] = 1;
		_zeCase[8][1] = 1;
		_zeCase[8][4] = 5;
		_zeCase[8][6] = 3;		
		
		final Integer[][] zeCaseSoluce = new Integer[9][9];

		zeCaseSoluce[0][0] = 8;
		zeCaseSoluce[0][1] = 3;
		zeCaseSoluce[0][2] = 5;
		zeCaseSoluce[0][3] = 7;
		zeCaseSoluce[0][4] = 9;
		zeCaseSoluce[0][5] = 2;
		zeCaseSoluce[0][6] = 1;
		zeCaseSoluce[0][7] = 6;
		zeCaseSoluce[0][8] = 4;
		zeCaseSoluce[1][0] = 1;
		zeCaseSoluce[1][1] = 6;
		zeCaseSoluce[1][2] = 9;
		zeCaseSoluce[1][3] = 5;
		zeCaseSoluce[1][4] = 4;
		zeCaseSoluce[1][5] = 3;
		zeCaseSoluce[1][6] = 2;
		zeCaseSoluce[1][7] = 8;
		zeCaseSoluce[1][8] = 7;
		zeCaseSoluce[2][0] = 7;
		zeCaseSoluce[2][1] = 2;
		zeCaseSoluce[2][2] = 4;
		zeCaseSoluce[2][3] = 1;
		zeCaseSoluce[2][4] = 6;
		zeCaseSoluce[2][5] = 8;
		zeCaseSoluce[2][6] = 5;
		zeCaseSoluce[2][7] = 9;
		zeCaseSoluce[2][8] = 3;
		zeCaseSoluce[3][0] = 3;
		zeCaseSoluce[3][1] = 7;
		zeCaseSoluce[3][2] = 1;
		zeCaseSoluce[3][3] = 6;
		zeCaseSoluce[3][4] = 8;
		zeCaseSoluce[3][5] = 5;
		zeCaseSoluce[3][6] = 9;
		zeCaseSoluce[3][7] = 4;
		zeCaseSoluce[3][8] = 2;
		zeCaseSoluce[4][0] = 9;
		zeCaseSoluce[4][1] = 8;
		zeCaseSoluce[4][2] = 2;
		zeCaseSoluce[4][3] = 4;
		zeCaseSoluce[4][4] = 7;
		zeCaseSoluce[4][5] = 1;
		zeCaseSoluce[4][6] = 6;
		zeCaseSoluce[4][7] = 3;
		zeCaseSoluce[4][8] = 5;
		zeCaseSoluce[5][0] = 4;
		zeCaseSoluce[5][1] = 5;
		zeCaseSoluce[5][2] = 6;
		zeCaseSoluce[5][3] = 2;
		zeCaseSoluce[5][4] = 3;
		zeCaseSoluce[5][5] = 9;
		zeCaseSoluce[5][6] = 7;
		zeCaseSoluce[5][7] = 1;
		zeCaseSoluce[5][8] = 8;
		zeCaseSoluce[6][0] = 5;
		zeCaseSoluce[6][1] = 4;
		zeCaseSoluce[6][2] = 7;
		zeCaseSoluce[6][3] = 3;
		zeCaseSoluce[6][4] = 1;
		zeCaseSoluce[6][5] = 6;
		zeCaseSoluce[6][6] = 8;
		zeCaseSoluce[6][7] = 2;
		zeCaseSoluce[6][8] = 9;
		zeCaseSoluce[7][0] = 6;
		zeCaseSoluce[7][1] = 9;
		zeCaseSoluce[7][2] = 3;
		zeCaseSoluce[7][3] = 8;
		zeCaseSoluce[7][4] = 2;
		zeCaseSoluce[7][5] = 7;
		zeCaseSoluce[7][6] = 4;
		zeCaseSoluce[7][7] = 5;
		zeCaseSoluce[7][8] = 1;
		zeCaseSoluce[8][0] = 2;
		zeCaseSoluce[8][1] = 1;
		zeCaseSoluce[8][2] = 8;
		zeCaseSoluce[8][3] = 9;
		zeCaseSoluce[8][4] = 5;
		zeCaseSoluce[8][5] = 4;
		zeCaseSoluce[8][6] = 3;
		zeCaseSoluce[8][7] = 7;
		zeCaseSoluce[8][8] = 6;

		SudokuWriter writer = new SimpleSudoWriter(zeCaseSoluce,true);

		SudoSolverOne solver = new SudoSolverOne(_zeCase, 2, writer, 0);
		solver.resolve();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(solver.getGrille().getValueAt(i, j)==zeCaseSoluce[i][j]);
			}
		}

	}
	
	
	@Test
	public void EscargotTest() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][0] = 1;
		_zeCase[0][5] = 7;
		_zeCase[0][7] = 9;
		_zeCase[1][1] = 3;
		_zeCase[1][4] = 2;
		_zeCase[1][8] = 8;
		_zeCase[2][2] = 9;
		_zeCase[2][3] = 6;
		_zeCase[2][6] = 5;
		_zeCase[3][2] = 5;
		_zeCase[3][3] = 3;
		_zeCase[3][6] = 9;
		_zeCase[4][1] = 1;
		_zeCase[4][4] = 8;
		_zeCase[4][8] = 2;
		_zeCase[5][0] = 6;
		_zeCase[5][5] = 4;
		_zeCase[6][0] = 3;
		_zeCase[6][7] = 1;
		_zeCase[7][1] = 4;
		_zeCase[7][8] = 7;
		_zeCase[8][2] = 7;
		_zeCase[8][6] = 3;

		final Integer[][] zeCaseSoluce = new Integer[9][9];

		zeCaseSoluce[0][0] = 1;
		zeCaseSoluce[0][1] = 6;
		zeCaseSoluce[0][2] = 2;
		zeCaseSoluce[0][3] = 8;
		zeCaseSoluce[0][4] = 5;
		zeCaseSoluce[0][5] = 7;
		zeCaseSoluce[0][6] = 4;
		zeCaseSoluce[0][7] = 9;
		zeCaseSoluce[0][8] = 3;
		zeCaseSoluce[1][0] = 5;
		zeCaseSoluce[1][1] = 3;
		zeCaseSoluce[1][2] = 4;
		zeCaseSoluce[1][3] = 1;
		zeCaseSoluce[1][4] = 2;
		zeCaseSoluce[1][5] = 9;
		zeCaseSoluce[1][6] = 6;
		zeCaseSoluce[1][7] = 7;
		zeCaseSoluce[1][8] = 8;
		zeCaseSoluce[2][0] = 7;
		zeCaseSoluce[2][1] = 8;
		zeCaseSoluce[2][2] = 9;
		zeCaseSoluce[2][3] = 6;
		zeCaseSoluce[2][4] = 4;
		zeCaseSoluce[2][5] = 3;
		zeCaseSoluce[2][6] = 5;
		zeCaseSoluce[2][7] = 2;
		zeCaseSoluce[2][8] = 1;
		zeCaseSoluce[3][0] = 4;
		zeCaseSoluce[3][1] = 7;
		zeCaseSoluce[3][2] = 5;
		zeCaseSoluce[3][3] = 3;
		zeCaseSoluce[3][4] = 1;
		zeCaseSoluce[3][5] = 2;
		zeCaseSoluce[3][6] = 9;
		zeCaseSoluce[3][7] = 8;
		zeCaseSoluce[3][8] = 6;
		zeCaseSoluce[4][0] = 9;
		zeCaseSoluce[4][1] = 1;
		zeCaseSoluce[4][2] = 3;
		zeCaseSoluce[4][3] = 5;
		zeCaseSoluce[4][4] = 8;
		zeCaseSoluce[4][5] = 6;
		zeCaseSoluce[4][6] = 7;
		zeCaseSoluce[4][7] = 4;
		zeCaseSoluce[4][8] = 2;
		zeCaseSoluce[5][0] = 6;
		zeCaseSoluce[5][1] = 2;
		zeCaseSoluce[5][2] = 8;
		zeCaseSoluce[5][3] = 7;
		zeCaseSoluce[5][4] = 9;
		zeCaseSoluce[5][5] = 4;
		zeCaseSoluce[5][6] = 1;
		zeCaseSoluce[5][7] = 3;
		zeCaseSoluce[5][8] = 5;
		zeCaseSoluce[6][0] = 3;
		zeCaseSoluce[6][1] = 5;
		zeCaseSoluce[6][2] = 6;
		zeCaseSoluce[6][3] = 4;
		zeCaseSoluce[6][4] = 7;
		zeCaseSoluce[6][5] = 8;
		zeCaseSoluce[6][6] = 2;
		zeCaseSoluce[6][7] = 1;
		zeCaseSoluce[6][8] = 9;
		zeCaseSoluce[7][0] = 2;
		zeCaseSoluce[7][1] = 4;
		zeCaseSoluce[7][2] = 1;
		zeCaseSoluce[7][3] = 9;
		zeCaseSoluce[7][4] = 3;
		zeCaseSoluce[7][5] = 5;
		zeCaseSoluce[7][6] = 8;
		zeCaseSoluce[7][7] = 6;
		zeCaseSoluce[7][8] = 7;
		zeCaseSoluce[8][0] = 8;
		zeCaseSoluce[8][1] = 9;
		zeCaseSoluce[8][2] = 7;
		zeCaseSoluce[8][3] = 2;
		zeCaseSoluce[8][4] = 6;
		zeCaseSoluce[8][5] = 1;
		zeCaseSoluce[8][6] = 3;
		zeCaseSoluce[8][7] = 5;
		zeCaseSoluce[8][8] = 4;


		SudokuWriter writer = new SimpleSudoWriter(zeCaseSoluce,true);

		SudoSolverOne solver = new SudoSolverOne(_zeCase, 2, writer, 0);
		solver.resolve();
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				assertTrue(solver.getGrille().getValueAt(i, j)==zeCaseSoluce[i][j]);
			}
		}

	}
	

	

}
