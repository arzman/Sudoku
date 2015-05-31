package sudoku.arz.solver;

import java.util.ArrayList;
import java.util.Calendar;

import sudoku.arz.application.PossibleValueUpdatorProvider;
import sudoku.arz.domaine.Case;
import sudoku.arz.domaine.Column;
import sudoku.arz.domaine.Grille;
import sudoku.arz.domaine.Line;
import sudoku.arz.domaine.Region;
import sudoku.arz.gui.SudokuWriter;
import sudoku.arz.possiblevalues.PossibleValueUpdator;

public class SudoSolverOne extends Thread {

	private Grille _zeGrille;

	private ArrayList<PossibleValueUpdator> _updators;

	private ArrayList<ValueResolver> _resolvers;

	private int state;

	private int _forkLvl;

	public SudoSolverOne(Integer[][] zeCase, int aState, SudokuWriter writer, int forkLvl) {

		_zeGrille = new Grille(zeCase.clone(), writer, forkLvl);
		_updators = new ArrayList<PossibleValueUpdator>();
		_updators.addAll(PossibleValueUpdatorProvider.getInstance().getAllUpdators());
		_forkLvl = forkLvl;

		if (aState <= _updators.size() - 1) {
			state = aState;
		}

		if (_forkLvl != 0) {
			_zeGrille.reInitUi();
		}

		_resolvers = new ArrayList<ValueResolver>();
		_resolvers.add(new SoleCandidateResolver());
		_resolvers.add(new CandidateUniqueResolver());

	}

	public void setState(int aState) {
		state = aState;
		PossibleValueUpdatorProvider.getInstance().setActivatedUpdator(state);
		_zeGrille.getWriter().setLevel(state);

	}

	public boolean levelUp() {

		boolean res = true;

		if (state + 1 > _updators.size() - 1) {
			res = false;
		} else {

			setState(state + 1);
		}

		return res;
	}

	@Override
	public void run() {
		super.run();
		resolve();
	}

	public void resolve() {

		long timeDeb = Calendar.getInstance().getTimeInMillis();
		setState(state);
		boolean changed = true;
		System.out.println("Begin");

		while (changed) {

			changed = false;
			// Maj des valeurs possible
			for (PossibleValueUpdator updator : _updators) {
				if (updator.isActive()) {
					updator.updatePossibleValues(_zeGrille);
				}
			}

			// Déduction des valeurs
			for (ValueResolver resolver : _resolvers) {
				changed = resolver.resolvePossible(_zeGrille) || changed;
			}

			if (!changed && !isSolved()) {
				changed = levelUp() || changed;
			}

		}

		if (!isSolved()) {

			Case caseToFork = null;

			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (!_zeGrille.getCaseAt(i, j).isResolved()) {

						if (caseToFork == null) {
							caseToFork = _zeGrille.getCaseAt(i, j);
						} else {

							if (caseToFork.getPossibleValues().size() > _zeGrille.getCaseAt(i, j).getPossibleValues().size()) {
								caseToFork = _zeGrille.getCaseAt(i, j);
							}
						}

					}
				}
			}

			boolean doIt = true;
			if(caseToFork!=null){
			for (int possValue : caseToFork.getPossibleValues()) {
					Integer[][] zeCase = _zeGrille.getIntGrid();
					if (doIt) {
	
						SudoSolverOne fork = new SudoSolverOne(zeCase, state, _zeGrille.getWriter(), _forkLvl + 1);
	
						fork.forkOn(caseToFork.getLineNb(), caseToFork.getColNb(), possValue);
	
						fork.start();
	
						try {
							fork.join();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	
						doIt = !fork.isSolved();
	
						if (fork.isSolved()) {
	
							_zeGrille.updateWith(fork.getIntGrid());
						}
	
					}
	
				}
			}

		}

		if (_forkLvl == 0) {
			_zeGrille.getWriter().setSolved(isSolved());
		}

		long timefine = Calendar.getInstance().getTimeInMillis();
		System.out.println("End in : " + (timefine - timeDeb) + " ms");
	}

	private Integer[][] getIntGrid() {

		return _zeGrille.getIntGrid();
	}

	private void forkOn(int lineNb, int colNb, int possValue) {

		_zeGrille.getWriter().doForkOn(lineNb, colNb, possValue);
		_zeGrille.setValueAt(lineNb, colNb, possValue);

	}

	public boolean isSolved() {

		boolean res = true;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				res = res && _zeGrille.isCaseResolved(i, j);
			}
		}

		// vérif des lignes
		for (Line line : _zeGrille.getLines()) {

			ArrayList<Integer> alreadyUsed = new ArrayList<Integer>();
			for (Case aCase : line.getCases()) {

				if (alreadyUsed.contains(aCase.getValue())) {
					res = false;
				} else {
					alreadyUsed.add(aCase.getValue());
				}
			}
		}

		// vérif des colonnes
		for (Column col : _zeGrille.getColumns()) {

			ArrayList<Integer> alreadyUsed = new ArrayList<Integer>();
			for (Case aCase : col.getCases()) {

				if (alreadyUsed.contains(aCase.getValue())) {
					res = false;
				} else {
					alreadyUsed.add(aCase.getValue());
				}
			}
		}

		// vérif des lignes
		for (Region reg : _zeGrille.getRegions()) {

			ArrayList<Integer> alreadyUsed = new ArrayList<Integer>();
			for (Case aCase : reg.getCases()) {

				if (alreadyUsed.contains(aCase.getValue())) {
					res = false;
				} else {
					alreadyUsed.add(aCase.getValue());
				}
			}
		}

		return res;
	}

	public Grille getGrille() {

		return _zeGrille;
	}

}
