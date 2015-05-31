package sudoku.arz.domaine;

import java.util.ArrayList;
import java.util.Iterator;

import sudoku.arz.gui.SudokuWriter;

public class Case {

	private int _value;

	private final ArrayList<Integer> _possibleValues;

	private final int _lineNb;

	private final int _colNb;

	private boolean _isResolved;

	private Line _line;

	private Column _column;

	private Region _region;

	public Case(int lineNb_, int colNb_) {

		_value = -1;
		_lineNb = lineNb_;
		_colNb = colNb_;
		_isResolved = false;
		_possibleValues = new ArrayList<Integer>();

		for (int i = 1; i < 10; i++) {
			_possibleValues.add(i);
		}

	}

	public Case(int lineNb_, int colNb_, int value_) {
		this(lineNb_, colNb_);
		if (value_ > 0 && value_ < 10) {
			setValue(value_);
		}

	}

	public int getLineNb() {

		return _lineNb;
	}

	public int getColNb() {

		return _colNb;
	}

	public int getValue() {

		return _value;
	}

	public boolean isResolved() {

		return _isResolved;
	}

	public boolean removePossibleValues(ArrayList<Integer> valuesInGroupe, SudokuWriter writer) {

		boolean res = false;

		Iterator<Integer> iter = _possibleValues.iterator();

		while (iter.hasNext()) {

			Integer val = iter.next();
			if (valuesInGroupe.contains(val)) {
				iter.remove();
				if (writer != null) {
					writer.removePossibleAt(_lineNb, _colNb, val);
				}
				res = true;
			}

		}

		return res;
	}

	public void setValue(int value) {
		_value = value;
		_isResolved = true;

	}

	public ArrayList<Integer> getPossibleValues() {

		ArrayList<Integer> res = new ArrayList<Integer>();

		if (isResolved()) {
			res.add(_value);
		} else {
			res.addAll(_possibleValues);
		}

		return res;
	}

	public Line getParentLine() {
		return _line;
	}

	public void setParentLine(Line line) {
		_line = line;
	}

	public Column getParentColumn() {
		return _column;
	}

	public void setParentColumn(Column column) {
		_column = column;
	}

	public Region getParentRegion() {

		return _region;
	}

	public void setParentRegion(Region region) {

		_region = region;
	}

}
