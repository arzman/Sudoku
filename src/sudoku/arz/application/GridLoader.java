package sudoku.arz.application;

import java.util.HashMap;
import java.util.Set;

public class GridLoader {

	private static GridLoader _instance;

	private HashMap<String, Integer[][]> _gridMap;

	private GridLoader() {

		_gridMap = new HashMap<String, Integer[][]>();

		fillMap();

	}

	private void fillMap() {

		_gridMap.put("Test Interaction", getTestInteraction());
		_gridMap.put("Test Twins", getTestTwins());
		_gridMap.put("Test Naked Single", getTestNakedSingle());
		_gridMap.put("Test Disjoint Chain", getTestDisjointChain());
		_gridMap.put("Test Nishio", getTestNishio());
		_gridMap.put("Escargot", getEscargot());
		_gridMap.put("Level 9",getLevelNine());

	}

	private Integer[][] getTestNakedSingle() {
		Integer[][] _zeCase = new Integer[9][9];
		_zeCase[0][1] = 4;
		_zeCase[0][3] = 5;
		_zeCase[0][5] = 8;
		_zeCase[0][7] = 9;
		_zeCase[1][2] = 8;
		_zeCase[1][3] = 4;
		_zeCase[1][4] = 6;
		_zeCase[1][5] = 3;
		_zeCase[1][6] = 5;
		_zeCase[3][1] = 8;
		_zeCase[3][2] = 9;
		_zeCase[3][6] = 3;
		_zeCase[3][7] = 6;
		_zeCase[4][1] = 1;
		_zeCase[4][2] = 2;
		_zeCase[4][6] = 9;
		_zeCase[4][7] = 8;
		_zeCase[5][1] = 6;
		_zeCase[5][2] = 5;
		_zeCase[5][6] = 7;
		_zeCase[5][7] = 2;
		_zeCase[7][2] = 4;
		_zeCase[7][3] = 2;
		_zeCase[7][4] = 5;
		_zeCase[7][5] = 9;
		_zeCase[7][6] = 1;
		_zeCase[8][1] = 3;
		_zeCase[8][3] = 1;
		_zeCase[8][5] = 4;
		_zeCase[8][7] = 5;

		return _zeCase;
	}

	private Integer[][] getTestDisjointChain() {

		Integer[][] _zeCase = new Integer[9][9];
		_zeCase[0][0] = 4;
		_zeCase[0][4] = 8;
		_zeCase[0][8] = 5;
		_zeCase[1][0] = 6;
		_zeCase[1][3] = 5;
		_zeCase[1][5] = 4;
		_zeCase[1][6] = 3;
		_zeCase[2][1] = 9;
		_zeCase[2][2] = 5;
		_zeCase[2][3] = 7;
		_zeCase[3][6] = 6;
		_zeCase[3][7] = 8;
		_zeCase[3][8] = 2;
		_zeCase[4][2] = 3;
		_zeCase[4][3] = 2;
		_zeCase[4][5] = 5;
		_zeCase[4][6] = 1;
		_zeCase[5][0] = 2;
		_zeCase[5][1] = 6;
		_zeCase[5][2] = 9;
		_zeCase[6][5] = 8;
		_zeCase[6][6] = 9;
		_zeCase[6][7] = 1;
		_zeCase[7][2] = 8;
		_zeCase[7][3] = 1;
		_zeCase[7][5] = 6;
		_zeCase[7][8] = 4;
		_zeCase[8][0] = 5;
		_zeCase[8][4] = 3;
		_zeCase[8][8] = 6;

		return _zeCase;
	}

	private Integer[][] getTestTwins() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][4] = 6;
		_zeCase[0][6] = 8;
		_zeCase[0][8] = 2;
		_zeCase[1][1] = 5;
		_zeCase[2][6] = 7;
		_zeCase[3][3] = 5;
		_zeCase[3][5] = 9;
		_zeCase[3][7] = 3;
		_zeCase[4][0] = 6;
		_zeCase[4][8] = 1;
		_zeCase[5][3] = 7;
		_zeCase[6][3] = 3;
		_zeCase[6][6] = 5;
		_zeCase[6][7] = 9;
		_zeCase[7][0] = 8;
		_zeCase[7][2] = 2;
		_zeCase[8][0] = 1;

		return _zeCase;
	}

	private Integer[][] getTestInteraction() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][2] = 4;
		_zeCase[0][3] = 3;
		_zeCase[0][5] = 1;
		_zeCase[0][6] = 2;
		_zeCase[1][1] = 3;
		_zeCase[1][7] = 7;
		_zeCase[2][3] = 4;
		_zeCase[2][5] = 7;
		_zeCase[3][0] = 6;
		_zeCase[3][2] = 2;
		_zeCase[3][4] = 7;
		_zeCase[3][6] = 9;
		_zeCase[3][8] = 1;
		_zeCase[4][2] = 1;
		_zeCase[4][3] = 9;
		_zeCase[4][5] = 2;
		_zeCase[4][6] = 5;
		_zeCase[5][0] = 3;
		_zeCase[5][2] = 9;
		_zeCase[5][4] = 4;
		_zeCase[5][6] = 6;
		_zeCase[5][8] = 7;
		_zeCase[6][3] = 8;
		_zeCase[6][5] = 4;
		_zeCase[7][1] = 9;
		_zeCase[7][7] = 4;
		_zeCase[8][2] = 6;
		_zeCase[8][3] = 7;
		_zeCase[8][5] = 5;
		_zeCase[8][6] = 1;

		return _zeCase;
	}

	private Integer[][] getTestNishio() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][1] = 3;
		_zeCase[0][2] = 9;
		_zeCase[1][3] = 7;
		_zeCase[1][7] = 6;
		_zeCase[2][1] = 8;
		_zeCase[2][6] = 5;
		_zeCase[3][0] = 7;
		_zeCase[3][1] = 4;
		_zeCase[3][7] = 2;
		_zeCase[4][5] = 9;
		_zeCase[4][8] = 7;
		_zeCase[5][4] = 8;
		_zeCase[6][4] = 3;
		_zeCase[6][6] = 8;
		_zeCase[7][0] = 4;
		_zeCase[7][3] = 2;
		_zeCase[8][0] = 5;

		return _zeCase;
	}

	private Integer[][] getEscargot() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][0] = 1;
		_zeCase[0][5] = 7;
		_zeCase[0][7] = 9;
		_zeCase[1][1] = 3;
		_zeCase[1][4] = 2;
		_zeCase[1][8] = 8;
		_zeCase[2][2] = 9;
		_zeCase[2][3] = 6;
		_zeCase[2][6] = 5;
		_zeCase[3][2] = 5;
		_zeCase[3][3] = 3;
		_zeCase[3][6] = 9;
		_zeCase[4][1] = 1;
		_zeCase[4][4] = 8;
		_zeCase[4][8] = 2;
		_zeCase[5][0] = 6;
		_zeCase[5][5] = 4;
		_zeCase[6][0] = 3;
		_zeCase[6][7] = 1;
		_zeCase[7][1] = 4;
		_zeCase[7][8] = 7;
		_zeCase[8][2] = 7;
		_zeCase[8][6] = 3;

		return _zeCase;
	}

	private Integer[][] getLevelNine() {

		Integer[][] _zeCase = new Integer[9][9];

		_zeCase[0][1] = 3;
		_zeCase[0][4] = 9;
		_zeCase[0][6] = 1;
		_zeCase[1][2] = 9;
		_zeCase[1][3] = 5;
		_zeCase[1][7] = 8;
		_zeCase[1][8] = 7;
		_zeCase[2][2] = 4;
		_zeCase[2][4] = 6;
		_zeCase[2][5] = 8;
		_zeCase[3][0] = 3;
		_zeCase[3][1] = 7;
		_zeCase[3][6] = 9;
		_zeCase[3][7] = 4;
		_zeCase[4][0] = 9;
		_zeCase[4][8] = 5;
		_zeCase[5][2] = 6;
		_zeCase[5][7] = 1;
		_zeCase[6][0] = 5;
		_zeCase[6][2] = 7;
		_zeCase[6][5] = 6;
		_zeCase[6][8] = 9;
		_zeCase[7][3] = 8;
		_zeCase[7][4] = 2;
		_zeCase[7][8] = 1;
		_zeCase[8][1] = 1;
		_zeCase[8][4] = 5;
		_zeCase[8][6] = 3;
		return _zeCase;
	}

	public static GridLoader getInstance() {

		if (_instance == null) {
			_instance = new GridLoader();
		}

		return _instance;
	}

	public Integer[][] loadGrid(String key) {

		Integer[][] _zeCase = new Integer[9][9];

		if (_gridMap.containsKey(key)) {
			_zeCase = _gridMap.get(key);
		}

		return _zeCase;
	}

	public Set<String> getGrid() {

		return _gridMap.keySet();
	}

}
