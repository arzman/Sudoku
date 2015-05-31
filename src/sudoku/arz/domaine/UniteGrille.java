package sudoku.arz.domaine;

import java.util.ArrayList;
import java.util.List;

import sudoku.arz.gui.SudokuWriter;

public abstract class UniteGrille {

	protected Case[] _cases;

	private int _index;

	public UniteGrille(int index_) {

		_cases = new Case[9];
		_index = index_;
	}

	public abstract void addCase(Case case_);

	public Case[] getCases() {
		return _cases;
	}

	public int getIndex() {

		return _index;
	}

	public ArrayList<Integer> getPossibleValues() {

		ArrayList<Integer> res = new ArrayList<Integer>();

		for (Case aCase : _cases) {

			if (aCase != null) {

				ArrayList<Integer> possValue = aCase.getPossibleValues();

				for (Integer possVal : possValue) {

					if (!res.contains(possVal)) {
						res.add(possVal);
					}

				}

			}

		}

		return res;
	}

	public ArrayList<Integer> getPossibleValuesWithout(int i, int j) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for (Case aCase : _cases) {

			if (aCase != null) {

				if (!(aCase.getLineNb() == i && aCase.getColNb() == j)) {

					ArrayList<Integer> possValue = aCase.getPossibleValues();

					for (Integer possVal : possValue) {

						if (!res.contains(possVal)) {
							res.add(possVal);
						}

					}
				}

			}

		}

		return res;
	}

	public ArrayList<Integer> getPossibleValueWithout(Integer[] bannedCase) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		ArrayList<Integer> banned = new ArrayList<Integer>();
		for (Integer ban : bannedCase) {
			banned.add(ban);
		}

		for (int i = 0; i < 9; i++) {

			if (!banned.contains(i)) {
				Case aCase = _cases[i];

				if (aCase != null) {

					ArrayList<Integer> possValue = aCase.getPossibleValues();

					for (Integer possVal : possValue) {

						if (!res.contains(possVal)) {
							res.add(possVal);
						}

					}

				}
			}

		}
		return res;
	}

	public ArrayList<Integer> getPossibleValueFor(Integer[] caseToSearch) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int i : caseToSearch) {

			Case aCase = _cases[i];
			if (aCase != null) {

				ArrayList<Integer> possValue = aCase.getPossibleValues();

				for (Integer possVal : possValue) {

					if (!res.contains(possVal)) {
						res.add(possVal);
					}

				}

			}

		}

		return res;
	}

	public ArrayList<Integer> getPossibleValueFrom(int min, int max) {

		ArrayList<Integer> res = new ArrayList<Integer>();

		for (int i = min; i < max; i++) {

			Case aCase = _cases[i];

			if (aCase != null) {

				ArrayList<Integer> possValue = aCase.getPossibleValues();

				for (Integer possVal : possValue) {

					if (!res.contains(possVal)) {
						res.add(possVal);
					}

				}

			}

		}
		return res;
	}

	public ArrayList<Case> getCasesFrom(int min, int max) {

		ArrayList<Case> res = new ArrayList<Case>();

		for (int i = min; i < max; i++) {

			res.add(_cases[i]);
		}

		return res;
	}

	public boolean removePossibleForAllExcept(List<Integer> integers, ArrayList<Integer> possValue, SudokuWriter writer) {

		boolean res = false;

		for (int i = 0; i < 9; i++) {

			if (!integers.contains(i)) {
				res = _cases[i].removePossibleValues(possValue, writer) || res;
			}

		}

		return res;
	}

}
