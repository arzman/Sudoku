package sudoku.arz.possiblevalues;

import java.util.ArrayList;
import java.util.Arrays;

import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.UniteGrille;
import sudoku.arz.gui.SudokuWriter;

public class NakedPairValuesUpdator extends AbstractUpdator {

	public NakedPairValuesUpdator(boolean isActive) {
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

		//on cherche les paires
		for (UniteGrille unite : group) {

			for (int indexFirst=0 ;indexFirst<9; indexFirst++) {

				for (int indexSec=indexFirst+1 ;indexSec<9; indexSec++) {

					if(indexFirst!=indexSec){
						
						ArrayList<Integer> possValue1 = unite.getCases()[indexFirst].getPossibleValues();
						ArrayList<Integer> possValue2 = unite.getCases()[indexSec].getPossibleValues();
						
						
						if(possValue1.size()==2 && possValue2.size()==2 && possValue1.containsAll(possValue2) && possValue2.containsAll(possValue1) ){
							
							
							res = unite.removePossibleForAllExcept(Arrays.asList(indexFirst,indexSec),possValue1,writer)|| res;
							
						}
						
					}
					
					
				}
			}

			
		}

		return res;

	}
	
}
