package sudoku.arz.solver;

import sudoku.arz.domaine.Grille;


public interface ValueResolver {

	boolean resolvePossible(Grille grille );

}
