package sudoku.arz.solver;

import java.util.ArrayList;

import sudoku.arz.domaine.Case;
import sudoku.arz.domaine.Grille;

public class CandidateUniqueResolver  extends ResolverByCase {


	public boolean resolveAt(Grille grille, int i, int j) {
		
		boolean res = false;
		if (!grille.isCaseResolved(i, j)) {

			Case aCase = grille.getCaseAt(i,j);
			
			ArrayList<Integer> tmp = aCase.getPossibleValues();
			
			boolean found = false;
			int index=0;
					
			while(index<tmp.size() && !found){
				
				int possValue = tmp.get(index);
				
				
				boolean uniqueLine = !aCase.getParentLine().getPossibleValuesWithout(i,j).contains(possValue) ;
				boolean uniqueCol = !aCase.getParentColumn().getPossibleValuesWithout(i,j).contains(possValue) ;
				boolean uniqueRegion = !aCase.getParentRegion().getPossibleValuesWithout(i,j).contains(possValue) ;
				
				if (uniqueLine || uniqueCol|| uniqueRegion) {
					found = true;
					grille.setValueAt(i, j, possValue);
					
					String lieu ="";
					if(uniqueLine){
						lieu = "ligne "+(aCase.getParentLine().getIndex()+1);
						
					}
					if(uniqueCol){
						lieu = "colonne "+(aCase.getParentColumn().getIndex()+1);
					}
					if(uniqueRegion){
						lieu = "région "+(aCase.getParentRegion().getIndex()+1);
					}
					
					//System.out.println("Placé symbole " +possValue+" , seul dans "+lieu + ", en  L" + (i+1) + "C" + (j+1));
					res = true;

				}
				
				index++;
				
				
			}
			
			
			

		}

		return res;
	}

}
