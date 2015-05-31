package sudoku.arz.possiblevalues;

import java.util.ArrayList;
import java.util.Arrays;

import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.UniteGrille;
import sudoku.arz.gui.SudokuWriter;

public class DisjointChainQuadValuesUpdator extends AbstractUpdator{

	public DisjointChainQuadValuesUpdator(boolean isActive) {
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

						for (int indexQuad = indexTer + 1; indexQuad < 9; indexQuad++) {

							ArrayList<Integer> possValue1 = unite.getCases()[indexFirst].getPossibleValues();
							ArrayList<Integer> possValue2 = unite.getCases()[indexSec].getPossibleValues();
							ArrayList<Integer> possValue3 = unite.getCases()[indexTer].getPossibleValues();
							ArrayList<Integer> possValue4 = unite.getCases()[indexQuad].getPossibleValues();

							if (possValue1.size() == 4 && possValue2.size() == 4 && possValue3.size() == 4 && possValue4.size() == 4
									&& possValue1.containsAll(possValue2) && possValue2.containsAll(possValue1) && possValue1.containsAll(possValue3)
									&& possValue3.containsAll(possValue1) && possValue1.containsAll(possValue4) && possValue4.containsAll(possValue1)) {

								res = unite.removePossibleForAllExcept(Arrays.asList(indexFirst, indexSec, indexTer, indexQuad), possValue1, writer) || res;

							}
						}

					}
				}
			}

		}

		return res;

	}

}
