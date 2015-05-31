package sudoku.arz.domaine;

import java.util.ArrayList;

import sudoku.arz.gui.SudokuWriter;

public class Region extends UniteGrille {

	public Region(int index_) {
		super(index_);

	}

	@Override
	public void addCase(Case case_) {

		for (int i = 0; i < 9; i++) {
			if (_cases[i] == null) {
				_cases[i] = case_;
				_cases[i].setParentRegion(this);
				break;

			}
		}

	}

	public boolean forcePossibleValueInLine(Line line, ArrayList<Integer> absent, SudokuWriter writer) {

		boolean res = false;

		for (Case aCase : _cases) {

			if (!aCase.getParentLine().equals(line)) {
				res = aCase.removePossibleValues(absent, writer) || res;
			}

		}

		return res;
	}

	public boolean forcePossibleValueInColumn(Column column, ArrayList<Integer> absent, SudokuWriter writer) {

		boolean res = false;

		for (Case aCase : _cases) {

			if (!aCase.getParentColumn().equals(column)) {
				res = aCase.removePossibleValues(absent, writer) || res;
			}

		}

		return res;
	}

}
