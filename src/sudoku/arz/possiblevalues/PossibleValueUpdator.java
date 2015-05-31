package sudoku.arz.possiblevalues;

import sudoku.arz.domaine.Grille;

public interface PossibleValueUpdator {

	boolean updatePossibleValues(Grille grille );
	
	boolean isActive();
	
	void setActive(boolean active);


}
