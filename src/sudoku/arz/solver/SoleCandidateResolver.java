package sudoku.arz.solver;

import sudoku.arz.domaine.Grille;


public class SoleCandidateResolver extends ResolverByCase {

	

	public boolean resolveAt(Grille grille,int i, int j) {
		
		boolean res = false;
		if (!grille.isCaseResolved(i, j)) {

			if (grille.getPossibleValuesAt(i, j).size() == 1) {

				grille.setValueAt(i, j, grille.getPossibleValuesAt(i, j).get(0));
				//System.out.println("Placé symbole " +grille.getPossibleValuesAt(i, j).get(0)+" , en  L" + (i+1) + "C" + (j+1));
				res = true;

			}

		}

		return res;

	}

}
