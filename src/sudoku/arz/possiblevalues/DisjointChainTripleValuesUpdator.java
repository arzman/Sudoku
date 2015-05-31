package sudoku.arz.possiblevalues;

import java.util.ArrayList;
import java.util.Arrays;

import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.UniteGrille;
import sudoku.arz.gui.SudokuWriter;

public class DisjointChainTripleValuesUpdator extends AbstractUpdator {

	public DisjointChainTripleValuesUpdator(boolean isActive) {
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

	private boolean updateForGroup(UniteGrille[] group, SudokuWriter writer) {

		boolean res = false;

		// on cherche les paires
		for (UniteGrille unite : group) {

			for (int indexFirst = 0; indexFirst < 9; indexFirst++) {

				for (int indexSec = indexFirst + 1; indexSec < 9; indexSec++) {

					for (int indexTer = indexSec + 1; indexTer < 9; indexTer++) {

						ArrayList<Integer> possValue1 = unite.getCases()[indexFirst].getPossibleValues();
						ArrayList<Integer> possValue2 = unite.getCases()[indexSec].getPossibleValues();
						ArrayList<Integer> possValue3 = unite.getCases()[indexTer].getPossibleValues();

						if (possValue1.size() == 3 && possValue2.size() <= 3 && possValue3.size() <= 3) {

							ArrayList<Integer> sumLittle = new ArrayList<Integer>();
							sumLittle.addAll(possValue2);
							sumLittle.addAll(possValue3);

							if (possValue1.containsAll(sumLittle) && sumLittle.containsAll(possValue1)) {

								res = unite.removePossibleForAllExcept(Arrays.asList(indexFirst, indexSec, indexTer), possValue1, writer) || res;

							}
						} else {
							if (possValue2.size() == 3 && possValue1.size() <= 3 && possValue3.size() <= 3) {

								ArrayList<Integer> sumLittle = new ArrayList<Integer>();
								sumLittle.addAll(possValue1);
								sumLittle.addAll(possValue3);

								if (possValue2.containsAll(sumLittle) && sumLittle.containsAll(possValue2)) {

									res = unite.removePossibleForAllExcept(Arrays.asList(indexFirst, indexSec, indexTer), possValue2, writer) || res;
								}
							} else {

								if (possValue3.size() == 3 && possValue2.size() <= 3 && possValue1.size() <= 3) {

									ArrayList<Integer> sumLittle = new ArrayList<Integer>();
									sumLittle.addAll(possValue2);
									sumLittle.addAll(possValue1);

									if (possValue3.containsAll(sumLittle) && sumLittle.containsAll(possValue3)) {

										res = unite.removePossibleForAllExcept(Arrays.asList(indexFirst, indexSec, indexTer), possValue3, writer) || res;
									}

								}
							}

						}

					}
				}
			}

		}

		return res;

	}

}
