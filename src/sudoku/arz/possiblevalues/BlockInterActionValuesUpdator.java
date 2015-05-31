package sudoku.arz.possiblevalues;

import java.util.ArrayList;

import sudoku.arz.domaine.Case;
import sudoku.arz.domaine.Column;
import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.Line;
import sudoku.arz.domaine.Region;
import sudoku.arz.gui.SudokuWriter;

public class BlockInterActionValuesUpdator extends AbstractUpdator {

	public BlockInterActionValuesUpdator(boolean isActive) {
		super(isActive);
	
	}

	@Override
	public boolean updatePossibleValues(Grille grille) {

		boolean res = false;

		// interaction entre regions alignées horizontalement
		Integer[] regionToSearch = { 0, 1, 2 };
		res = updateLineForRegionGroup(grille.getRegionsByNumber(regionToSearch), grille.getWriter()) || res;
		Integer[] regionToSearch2 = { 3, 4, 5 };
		res = updateLineForRegionGroup(grille.getRegionsByNumber(regionToSearch2), grille.getWriter()) || res;
		Integer[] regionToSearch3 = { 6, 7, 8 };
		res = updateLineForRegionGroup(grille.getRegionsByNumber(regionToSearch3), grille.getWriter()) || res;

		// interaction entre regions alignées verticalement
		Integer[] regionToSearch4 = { 0, 3, 6 };
		res = updateColumnForRegionGroup(grille.getRegionsByNumber(regionToSearch4), grille.getWriter()) || res;
		Integer[] regionToSearch5 = { 1, 4, 7 };
		res = updateColumnForRegionGroup(grille.getRegionsByNumber(regionToSearch5), grille.getWriter()) || res;
		Integer[] regionToSearch6 = { 2, 5, 8 };
		res = updateColumnForRegionGroup(grille.getRegionsByNumber(regionToSearch6), grille.getWriter()) || res;

		return res;

	}

	private boolean updateColumnForRegionGroup(ArrayList<Region> regions, SudokuWriter writer) {
		boolean res = false;

		res = processAllColumnRegionPair(regions.get(0), regions.get(1), regions.get(2), writer) || res;
		res = processAllColumnRegionPair(regions.get(0), regions.get(2), regions.get(1), writer) || res;
		res = processAllColumnRegionPair(regions.get(1), regions.get(2), regions.get(0), writer) || res;

		return res;
	}

	private boolean processAllColumnRegionPair(Region region, Region region2, Region otherRegion, SudokuWriter writer) {
		boolean res = false;

		// cherche de candidat absent par ligne
		res = processColumnRegionPair(0, region, region2, otherRegion, writer) || res;
		res = processColumnRegionPair(1, region, region2, otherRegion, writer) || res;
		res = processColumnRegionPair(2, region, region2, otherRegion, writer) || res;

		return res;
	}

	private boolean processColumnRegionPair(int numCaseInColumn, Region region, Region region2, Region otherRegion, SudokuWriter writer) {
		Column column = region.getCases()[numCaseInColumn].getParentColumn();

		ArrayList<Integer> possibleValueInLine = new ArrayList<Integer>();

		for (Case aCase : column.getCases()) {

			if (aCase.getParentRegion().equals(region) || aCase.getParentRegion().equals(region2)) {

				for (Integer possValue : aCase.getPossibleValues()) {

					if (!possibleValueInLine.contains(possValue)) {
						possibleValueInLine.add(possValue);
					}

				}

			}

		}

		ArrayList<Integer> absent = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			if (!possibleValueInLine.contains(i)) {
				absent.add(i);
			}
		}

		// les absent ne se trouve que dans l'autre partie de la ligne
		boolean res = otherRegion.forcePossibleValueInColumn(column, absent, writer);

		return res;
	}

	private boolean updateLineForRegionGroup(ArrayList<Region> regions, SudokuWriter writer) {

		boolean res = false;

		res = processAllLineRegionPair(regions.get(0), regions.get(1), regions.get(2), writer) || res;
		res = processAllLineRegionPair(regions.get(0), regions.get(2), regions.get(1), writer) || res;
		res = processAllLineRegionPair(regions.get(1), regions.get(2), regions.get(0), writer) || res;

		return res;
	}

	private boolean processAllLineRegionPair(Region region, Region region2, Region otherRegion, SudokuWriter writer) {

		boolean res = false;

		// cherche de candidat absent par ligne
		res = processLineRegionPair(0, region, region2, otherRegion, writer) || res;
		res = processLineRegionPair(3, region, region2, otherRegion, writer) || res;
		res = processLineRegionPair(6, region, region2, otherRegion, writer) || res;

		return res;
	}

	private boolean processLineRegionPair(int numCaseInLine, Region region, Region region2, Region otherRegion, SudokuWriter writer) {

		Line line = region.getCases()[numCaseInLine].getParentLine();

		ArrayList<Integer> possibleValueInLine = new ArrayList<Integer>();

		for (Case aCase : line.getCases()) {

			if (aCase.getParentRegion().equals(region) || aCase.getParentRegion().equals(region2)) {

				for (Integer possValue : aCase.getPossibleValues()) {

					if (!possibleValueInLine.contains(possValue)) {
						possibleValueInLine.add(possValue);
					}

				}

			}

		}

		ArrayList<Integer> absent = new ArrayList<Integer>();
		for (int i = 1; i < 10; i++) {
			if (!possibleValueInLine.contains(i)) {
				absent.add(i);
			}
		}

		// les absent ne se trouve que dans l'autre partie de la ligne
		boolean res = otherRegion.forcePossibleValueInLine(line, absent, writer);

		return res;

	}

}
