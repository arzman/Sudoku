package sudoku.arz.domaine;


public class Line extends UniteGrille {

	public Line(int index_) {
		super(index_);

	}

	@Override
	public void addCase(Case case_) {
		
		_cases[case_.getColNb()] = case_;
		_cases[case_.getColNb()].setParentLine(this);

	}

	
}
