package sudoku.arz.domaine;

import java.util.ArrayList;

import sudoku.arz.gui.SudokuWriter;

public class Grille {

	private Case[][] _zeCase;

	private Line[] _lineGroupe;

	private Column[] _colGroupe;

	private Region[] _regionGroupe;

	private SudokuWriter _grilleUI;

	private int _forkLvl;

	public Grille(Integer[][] caseInit_, SudokuWriter writer, int forkLvl) {

		_lineGroupe = new Line[9];
		_colGroupe = new Column[9];
		_regionGroupe = new Region[9];
		_zeCase = new Case[9][9];
		_grilleUI = writer;
		_forkLvl = forkLvl;

		for (int index = 0; index < 9; index++) {

			_lineGroupe[index] = new Line(index);
			_colGroupe[index] = new Column(index);
			_regionGroupe[index] = new Region(index);

		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				if (caseInit_[i][j] == null) {
					_zeCase[i][j] = new Case(i, j);
				} else {

					_zeCase[i][j] = new Case(i, j, caseInit_[i][j]);

				}

				_lineGroupe[i].addCase(_zeCase[i][j]);
				_colGroupe[j].addCase(_zeCase[i][j]);
				_regionGroupe[getRegionIndex(i, j)].addCase(_zeCase[i][j]);

			}
		}

	}

	public int getValueAt(int i, int j) {

		return _zeCase[i][j].getValue();

	}

	private int getRegionIndex(int i, int j) {
		int z = -1;

		if (i >= 0 && i <= 2) {

			if (j >= 0 && j <= 2) {
				z = 0;

			} else {

				if (j >= 3 && j <= 5) {
					z = 1;
				} else {

					if (j >= 6 && j <= 8) {
						z = 2;
					}

				}

			}

		} else {

			if (i >= 3 && i <= 5) {

				if (j >= 0 && j <= 2) {
					z = 3;
				} else {

					if (j >= 3 && j <= 5) {
						z = 4;
					} else {

						if (j >= 6 && j <= 8) {
							z = 5;
						}

					}

				}

			} else {

				if (i >= 6 && i <= 8) {

					if (j >= 0 && j <= 2) {
						z = 6;
					} else {

						if (j >= 3 && j <= 5) {
							z = 7;
						} else {

							if (j >= 6 && j <= 8) {
								z = 8;
							}

						}

					}

				}

			}

		}
		return z;
	}

	public boolean isCaseResolved(int i, int j) {

		return _zeCase[i][j].isResolved();
	}

	public void setValueAt(int i, int j, int value) {
		_zeCase[i][j].setValue(value);
		if (_grilleUI != null) {
			_grilleUI.setValueAt(i, j, value, _forkLvl);
		}

	}

	public Line[] getLines() {
		return _lineGroupe;
	}

	public Column[] getColumns() {

		return _colGroupe;
	}

	public Region[] getRegions() {

		return _regionGroupe;
	}

	public ArrayList<Integer> getPossibleValuesAt(int i, int j) {
		return _zeCase[i][j].getPossibleValues();
	}

	public Case getCaseAt(int i, int j) {
		return _zeCase[i][j];
	}

	public SudokuWriter getWriter() {

		return _grilleUI;
	}

	public ArrayList<Region> getRegionsByNumber(Integer[] regionNumToSearch) {

		ArrayList<Region> res = new ArrayList<Region>(regionNumToSearch.length);

		for (int index : regionNumToSearch) {
			res.add(_regionGroupe[index]);
		}

		return res;
	}

	public Integer[][] getIntGrid() {

		Integer[][] zeCase = new Integer[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				zeCase[i][j] = _zeCase[i][j].getValue();

			}
		}

		return zeCase;
	}

	public void reInitUi() {
		
		_grilleUI.setProgress(0);

		int progress= 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				
				_grilleUI.forceValue(i,j,_zeCase[i][j].getValue());
				
				if(_zeCase[i][j].getValue()!=-1){
					progress++;
				}
			}
		}
		
		_grilleUI.setProgress(progress);
		
		

	}

	public void updateWith(Integer [][]  intGrid) {
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				_zeCase[i][j].setValue(intGrid[i][j]);

			}
		}
		
	}

}
