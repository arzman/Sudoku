package sudoku.arz.domaine;

public class Column extends UniteGrille {

	public Column(int index_) {
		super(index_);
	}

	@Override
	public void addCase(Case case_) {
		
		_cases[case_.getLineNb()] = case_;
		_cases[case_.getLineNb()].setParentColumn(this);

	}

	

}
