package sudoku.arz.possiblevalues;

import java.util.ArrayList;

import sudoku.arz.domaine.Case;
import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.UniteGrille;
import sudoku.arz.gui.SudokuWriter;

public class AlreadyFoundValuesUpdator extends AbstractUpdator {

	public AlreadyFoundValuesUpdator(boolean isActive) {
		super(isActive);
		
	}

	@Override
	public boolean updatePossibleValues(Grille grille) {

		boolean res = false;

		res = updateForGroup(grille.getLines(), grille.getWriter()) || res;
		res = updateForGroup(grille.getColumns(), grille.getWriter()) || res;
		res = updateForGroup(grille.getRegions(), grille.getWriter()) || res;

		return res;
	}

	private boolean updateForGroup(UniteGrille[] lines, SudokuWriter writer) {

		boolean res = false;

		for (UniteGrille unite : lines) {

			ArrayList<Integer> valuesInGroupe = new ArrayList<Integer>();

			for (Case aCase : unite.getCases()) {

				if (aCase.isResolved() && !valuesInGroupe.contains(aCase.getValue())) {
					valuesInGroupe.add(aCase.getValue());
				}
			}

			for (Case aCase : unite.getCases()) {

				if (!aCase.isResolved()) {

					res = aCase.removePossibleValues(valuesInGroupe, writer) || res;

				}

			}
		}

		return res;

	}

}
