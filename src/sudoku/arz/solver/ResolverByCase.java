package sudoku.arz.solver;

import sudoku.arz.domaine.Grille;


public abstract class ResolverByCase implements ValueResolver {

	@Override
	public boolean resolvePossible(Grille grille ) {

		boolean res = false;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {

				res = resolveAt(grille, i, j) || res;

			}
		}

		return res;
	}

	protected abstract boolean resolveAt(Grille grille, int i, int j);

}
