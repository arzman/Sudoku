package sudoku.arz.possiblevalues;

import sudoku.arz.domaine.Grille;

public abstract class AbstractUpdator implements PossibleValueUpdator {

	
	private boolean _isActive;
	
	
	public AbstractUpdator(boolean isActive) {
		_isActive = isActive;
	}

	
	
	@Override
	public abstract boolean updatePossibleValues(Grille grille);



	public boolean isActive() {
		return _isActive;
	}



	public void setActive(boolean _isActive) {
		this._isActive = _isActive;
	}
}
