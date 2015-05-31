package sudoku.arz.possiblevalues;

import java.util.ArrayList;

import sudoku.arz.domaine.Case;
import sudoku.arz.domaine.Column;
import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.Line;
import sudoku.arz.domaine.Region;
import sudoku.arz.gui.SudokuWriter;

public class TwinsValuesUpdators extends AbstractUpdator {

	public TwinsValuesUpdators(boolean isActive) {
		super(isActive);

	}

	@Override
	public boolean updatePossibleValues(Grille grille) {

		boolean res = false;

		for (Region region : grille.getRegions()) {

			res = updateForGroup(region, grille.getWriter()) || res;
		}

		return res;
	}

	private boolean updateForGroup(Region region, SudokuWriter writer) {

		boolean res = false;

		// premiere ligne
		Integer[] caseToSearch = { 0, 1, 2 };
		res = processLine(caseToSearch, region, writer) || res;
		// deuxieme ligne
		Integer[] caseToSearch2 = { 3, 4, 5 };
		res = processLine(caseToSearch2, region, writer) || res;
		// troisieme ligne
		Integer[] caseToSearch3 = { 6, 7, 8 };
		res = processLine(caseToSearch3, region, writer) || res;

		// premiere colonne
		Integer[] caseToSearch4 = { 0, 3, 6 };
		res = processColumn(caseToSearch4, region, writer) || res;
		// deuxieme colonne
		Integer[] caseToSearch5 = { 1, 4, 7 };
		res = processColumn(caseToSearch5, region, writer) || res;
		// troisieme colonne
		Integer[] caseToSearch6 = { 2, 5, 8 };
		res = processColumn(caseToSearch6, region, writer) || res;

		return res;
	}

	private boolean processLine(Integer[] caseToSearch, Region region, SudokuWriter writer) {

		boolean res = false;

		Line line = region.getCases()[caseToSearch[0]].getParentLine();
		ArrayList<Case> trueCase = new ArrayList<Case>();
		for (Integer index : caseToSearch) {
			trueCase.add(region.getCases()[index]);
		}

		ArrayList<Integer> possibleValueInSubRegion = region.getPossibleValueFor(caseToSearch);
		ArrayList<Integer> otherPossibleValueInSubRegion = region.getPossibleValueWithout(caseToSearch);
		ArrayList<Integer> twinValueInSubRegion = new ArrayList<Integer>();

		// recherche de jumeaux
		for (Integer possTwin : possibleValueInSubRegion) {

			if (!otherPossibleValueInSubRegion.contains(possTwin) && !twinValueInSubRegion.contains(possTwin)) {
				// System.out.println("Found twin :"+possTwin+" in L:"+line.getIndex());
				twinValueInSubRegion.add(possTwin);
			}

		}

		// suppression des jumeaux dans la suite de la ligne
		for (Case aCase : line.getCases()) {

			if (!aCase.isResolved() && !trueCase.contains(aCase)) {

				res = aCase.removePossibleValues(twinValueInSubRegion, writer) || res;
			}

		}

		return res;
	}

	private boolean processColumn(Integer[] caseToSearch, Region region, SudokuWriter writer) {

		boolean res = false;

		Column column = region.getCases()[caseToSearch[0]].getParentColumn();

		// on récupère les case
		ArrayList<Case> trueCase = new ArrayList<Case>();
		for (Integer index : caseToSearch) {
			trueCase.add(region.getCases()[index]);
		}

		// on récupère les valeur possible
		ArrayList<Integer> possibleValueInSubRegion = region.getPossibleValueFor(caseToSearch);
		ArrayList<Integer> otherPossibleValueInSubRegion = region.getPossibleValueWithout(caseToSearch);
		ArrayList<Integer> twinValueInSubRegion = new ArrayList<Integer>();

		// recherche de jumeaux
		for (Integer possTwin : possibleValueInSubRegion) {

			if (!otherPossibleValueInSubRegion.contains(possTwin) && !twinValueInSubRegion.contains(possTwin)) {
				// System.out.println("Found twin :"+possTwin+" in C:"+column.getIndex());
				twinValueInSubRegion.add(possTwin);
			}

		}

		// suppression des jumeaux dans la suite de la ligne
		for (Case aCase : column.getCases()) {

			if (!aCase.isResolved() && !trueCase.contains(aCase)) {

				res = aCase.removePossibleValues(twinValueInSubRegion, writer) || res;
			}

		}

		return res;
	}
}
