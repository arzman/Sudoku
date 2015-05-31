package sudoku.arz.gui;

import java.awt.Button;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sudoku.arz.application.GridLoader;
import sudoku.arz.application.PossibleValueUpdatorProvider;
import sudoku.arz.solver.SudoSolverOne;

public class GrilleUI extends JFrame implements SudokuWriter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5825980521193818083L;

	private JPanel contentPane;

	private CaseGui[][] _zeCase;
	private JTextField txtDelay;

	private int stepDelay = 100;

	private ArrayList<JLabel> _labelList;

	private final JProgressBar _progressBar;

	private JComboBox<String> levelInitComboBox;

	private final Color BLUE_PROGRESS = new Color(187, 210, 225);

	private final Color GREEN_VALID = new Color(127, 221, 76);

	private final Color RED_INVALID = new Color(169, 17, 1);

	/**
	 * Create the frame.
	 */
	public GrilleUI() {

		setTitle("Sudoku Solver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		_labelList = new ArrayList<JLabel>();

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWeights = new double[] { 1.0, 0.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0 };
		contentPane.setLayout(gbl_contentPane);

		JPanel gridPanel = new JPanel();
		GridBagConstraints gbc_gridPanel = new GridBagConstraints();
		gbc_gridPanel.insets = new Insets(0, 0, 5, 5);
		gbc_gridPanel.fill = GridBagConstraints.BOTH;
		gbc_gridPanel.gridx = 0;
		gbc_gridPanel.gridy = 0;
		contentPane.add(gridPanel, gbc_gridPanel);
		gridPanel.setBackground(Color.GRAY);

		GridBagLayout gbl_gridPanel = new GridBagLayout();
		gbl_gridPanel.columnWidths = new int[] { 0, 0, 0 };
		gbl_gridPanel.rowHeights = new int[] { 0, 0 };
		gbl_gridPanel.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_gridPanel.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		gridPanel.setLayout(gbl_gridPanel);

		_zeCase = new CaseGui[9][9];

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				_zeCase[i][j] = new CaseGui(i, j);

				GridBagConstraints gbc_case = new GridBagConstraints();

				int top = 1;

				if (i == 0 || i == 3 || i == 6) {
					top = 2;
				}
				int left = 1;
				if (j == 0 || j == 3 || j == 6) {
					left = 2;
				}
				int bottom = 1;
				if (i == 2 || i == 5 || i == 8) {
					bottom = 2;
				}
				int right = 1;
				if (j == 2 || j == 5 || j == 8) {
					right = 2;
				}

				gbc_case.insets = new Insets(top, left, bottom, right);
				gbc_case.fill = GridBagConstraints.BOTH;
				gbc_case.gridx = j;
				gbc_case.gridy = i;
				gbc_case.anchor = GridBagConstraints.CENTER;
				gbc_case.weightx = 1.0f;
				gbc_case.weighty = 1.0f;

				gridPanel.add(_zeCase[i][j], gbc_case);
			}
		}

		JPanel controlPanel = new JPanel();
		GridBagConstraints gbc_controlPanel = new GridBagConstraints();
		gbc_controlPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_controlPanel.anchor = GridBagConstraints.NORTH;
		gbc_controlPanel.gridx = 1;
		gbc_controlPanel.gridy = 0;
		contentPane.add(controlPanel, gbc_controlPanel);
		GridBagLayout gbl_controlPanel = new GridBagLayout();
		gbl_controlPanel.columnWeights = new double[] { 1.0 };
		gbl_controlPanel.rowWeights = new double[] { 0.0, 1.0, 1.0 };
		controlPanel.setLayout(gbl_controlPanel);

		JPanel solverControlPanel = new JPanel();
		solverControlPanel.setBorder(new TitledBorder(null, "Solver Control", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_solverControlPanel = new GridBagConstraints();
		gbc_solverControlPanel.insets = new Insets(0, 0, 5, 0);
		gbc_solverControlPanel.fill = GridBagConstraints.BOTH;
		gbc_solverControlPanel.gridx = 0;
		gbc_solverControlPanel.gridy = 0;
		controlPanel.add(solverControlPanel, gbc_solverControlPanel);
		GridBagLayout gbl_solverControlPanel = new GridBagLayout();
		gbl_solverControlPanel.columnWeights = new double[] { 1.0 };
		gbl_solverControlPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		solverControlPanel.setLayout(gbl_solverControlPanel);

		JButton btnEffacer = new JButton("Effacer");
		GridBagConstraints gbc_btnEffacer = new GridBagConstraints();
		gbc_btnEffacer.insets = new Insets(0, 0, 5, 0);
		gbc_btnEffacer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnEffacer.gridx = 0;
		gbc_btnEffacer.gridy = 0;
		solverControlPanel.add(btnEffacer, gbc_btnEffacer);
		btnEffacer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						_zeCase[i][j].setText(" ");
					}
				}

			}
		});

		JButton btnDump = new JButton("Dump");
		GridBagConstraints gbc_btnDump = new GridBagConstraints();
		gbc_btnDump.insets = new Insets(0, 0, 5, 0);
		gbc_btnDump.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDump.gridx = 0;
		gbc_btnDump.gridy = 1;
		solverControlPanel.add(btnDump, gbc_btnDump);
		btnDump.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						if (!_zeCase[i][j].getText().equals("")) {

							System.out.println("_zeCase[" + i + "][" + j + "] = " + _zeCase[i][j].getText() + ";");
						}

					}
				}

			}

		});

		JButton btnDemarrer = new JButton("Demarrer");
		GridBagConstraints gbc_btnDemarrer = new GridBagConstraints();
		gbc_btnDemarrer.insets = new Insets(0, 0, 5, 0);
		gbc_btnDemarrer.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDemarrer.gridx = 0;
		gbc_btnDemarrer.gridy = 4;
		solverControlPanel.add(btnDemarrer, gbc_btnDemarrer);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		solverControlPanel.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWeights = new double[] { 0.0, 0.0 };
		gbl_panel.rowWeights = new double[] { 0.0 };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("Step Delay :");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.weighty = 1.0;
		gbc_lblNewLabel.weightx = 2.0;
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		txtDelay = new JTextField();
		txtDelay.setText("100");
		GridBagConstraints gbc_txtDelay = new GridBagConstraints();
		gbc_txtDelay.weighty = 1.0;
		gbc_txtDelay.weightx = 1.0;
		gbc_txtDelay.anchor = GridBagConstraints.EAST;
		gbc_txtDelay.gridx = 1;
		gbc_txtDelay.gridy = 0;
		panel.add(txtDelay, gbc_txtDelay);
		txtDelay.setColumns(4);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 3;
		solverControlPanel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblLevelInit = new JLabel("Level Init :");
		GridBagConstraints gbc_lblLevelInit = new GridBagConstraints();
		gbc_lblLevelInit.weighty = 1.0;
		gbc_lblLevelInit.weightx = 2.0;
		gbc_lblLevelInit.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblLevelInit.anchor = GridBagConstraints.WEST;
		gbc_lblLevelInit.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevelInit.gridx = 0;
		gbc_lblLevelInit.gridy = 0;
		panel_1.add(lblLevelInit, gbc_lblLevelInit);

		levelInitComboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 0, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panel_1.add(levelInitComboBox, gbc_comboBox);
		for (int i = 0; i < PossibleValueUpdatorProvider.getInstance().getAllName().size(); i++) {
			levelInitComboBox.addItem(String.valueOf(i));
		}

		_progressBar = new JProgressBar();
		_progressBar.setStringPainted(true);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.weighty = 1.0;
		gbc_progressBar.weightx = 1.0;
		gbc_progressBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 5;
		_progressBar.setMaximum(81);
		solverControlPanel.add(_progressBar, gbc_progressBar);

		JPanel gridLoadPanel = new JPanel();
		gridLoadPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Grid Loader", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_gridLoadPanel = new GridBagConstraints();
		gbc_gridLoadPanel.insets = new Insets(0, 0, 5, 0);
		gbc_gridLoadPanel.fill = GridBagConstraints.BOTH;
		gbc_gridLoadPanel.gridx = 0;
		gbc_gridLoadPanel.gridy = 1;
		controlPanel.add(gridLoadPanel, gbc_gridLoadPanel);
		GridBagLayout gbl_gridLoadPanel = new GridBagLayout();
		gbl_gridLoadPanel.columnWeights = new double[] { 0.0 };
		gbl_gridLoadPanel.rowWeights = new double[] { 0.0, 0.0 };
		gridLoadPanel.setLayout(gbl_gridLoadPanel);

		final JComboBox gridCombo = new JComboBox();
		GridBagConstraints gbc_gridCombo = new GridBagConstraints();
		gbc_gridCombo.insets = new Insets(5, 0, 0, 0);
		gbc_gridCombo.fill = GridBagConstraints.HORIZONTAL;
		gbc_gridCombo.gridx = 0;
		gbc_gridCombo.gridy = 1;
		gridLoadPanel.add(gridCombo, gbc_gridCombo);
		for (String grid : GridLoader.getInstance().getGrid()) {
			gridCombo.addItem(grid);
		}

		Button btnLoad = new Button("Load Grid");
		GridBagConstraints gbc_btnLoad = new GridBagConstraints();
		gbc_btnLoad.weighty = 1.0;
		gbc_btnLoad.weightx = 1.0;
		gbc_btnLoad.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnLoad.gridx = 0;
		gbc_btnLoad.gridy = 0;
		gridLoadPanel.add(btnLoad, gbc_btnLoad);

		JPanel statePanel = new JPanel();
		statePanel.setBorder(new TitledBorder(null, "Solver State", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_statePanel = new GridBagConstraints();
		gbc_statePanel.fill = GridBagConstraints.BOTH;
		gbc_statePanel.gridx = 0;
		gbc_statePanel.gridy = 2;
		controlPanel.add(statePanel, gbc_statePanel);
		GridBagLayout gbl_statePanel = new GridBagLayout();
		gbl_statePanel.columnWidths = new int[] { 0, 0 };
		gbl_statePanel.rowHeights = new int[] { 0, 0, 0, 0 };
		gbl_statePanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_statePanel.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		statePanel.setLayout(gbl_statePanel);

		int i = 0;
		for (String upName : PossibleValueUpdatorProvider.getInstance().getAllName()) {

			JLabel upLabel = new JLabel(i + " : " + upName);
			GridBagConstraints gbc_alreadyFoundLabel = new GridBagConstraints();
			gbc_alreadyFoundLabel.insets = new Insets(0, 0, 5, 0);
			gbc_alreadyFoundLabel.gridx = 0;
			gbc_alreadyFoundLabel.gridy = i;
			gbc_alreadyFoundLabel.fill = GridBagConstraints.HORIZONTAL;
			gbc_alreadyFoundLabel.anchor = GridBagConstraints.CENTER;
			statePanel.add(upLabel, gbc_alreadyFoundLabel);
			_labelList.add(upLabel);
			i++;

		}

		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				try {
					stepDelay = Integer.parseInt(txtDelay.getText());
				} catch (NumberFormatException ex) {
					stepDelay = 100;
					txtDelay.setText(String.valueOf(stepDelay));
				}

				Integer[][] caseInt = GridLoader.getInstance().loadGrid((String) gridCombo.getSelectedItem());

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {

						if (caseInt[i][j] != null) {
							_zeCase[i][j].setText(String.valueOf(caseInt[i][j]));
						} else {
							_zeCase[i][j].setText(" ");
						}

					}
				}

			}

		});

		btnDemarrer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Integer[][] caseInt = new Integer[9][9];

				int initProgress = 0;

				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						int value = -1;

						try {
							value = Integer.parseInt(_zeCase[i][j].getText());
							initProgress++;
						} catch (NumberFormatException ex) {

						}

						caseInt[i][j] = value;
						_zeCase[i][j].setColor(0);
					}
				}

				int level = 0;
				try {
					level = Integer.parseInt((String) levelInitComboBox.getSelectedItem());
				} catch (NumberFormatException ex) {
					levelInitComboBox.setSelectedItem(String.valueOf(level));
				}

				for (JLabel lbl : _labelList) {
					lbl.setForeground(RED_INVALID);
				}

				_progressBar.setValue(initProgress);
				_progressBar.setMaximum(81);
				_progressBar.setForeground(BLUE_PROGRESS);

				SudoSolverOne t = new SudoSolverOne(caseInt, level, GrilleUI.this, 0);
				t.start();

			}
		});

		setSize(650, 600);
	}

	public CaseGui[][] getTextFields() {
		return _zeCase;
	}

	public void setValueAt(final int i, final int j, int value, final int forkLvl) {

		try {
			Thread.sleep(stepDelay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		_zeCase[i][j].setText(String.valueOf(value));

		if (forkLvl != 0) {
			try {
				SwingUtilities.invokeAndWait(new Runnable() {

					@Override
					public void run() {
						_zeCase[i][j].setColor(forkLvl);

					}
				});
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		_progressBar.setValue(_progressBar.getValue() + 1);

	}

	@Override
	public void removePossibleAt(int i, int j, int value) {

	}

	@Override
	public void setLevel(int state) {

		for (int i = 0; i <= state; i++) {

			_labelList.get(i).setForeground(GREEN_VALID);

		}

		for (int i = state + 1; i < _labelList.size(); i++) {

			if (_labelList.get(i).getForeground().equals(GREEN_VALID)) {
				_labelList.get(i).setForeground(Color.ORANGE);
			}

		}

	}

	@Override
	public void setSolved(boolean solved) {

		if (solved) {
			_progressBar.setForeground(GREEN_VALID);
		} else {
			_progressBar.setForeground(RED_INVALID);
		}

	}

	@Override
	public void forceValue(int i, int j, int value) {

		if (value > 0) {
			_zeCase[i][j].setText(String.valueOf(value));
		} else {
			_zeCase[i][j].setText(" ");
		}

	}

	@Override
	public void doForkOn(int lineNb, int colNb, int possValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void forceProgress() {

		int initProgress = 0;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int value = -1;

				try {
					value = Integer.parseInt(_zeCase[i][j].getText());
					initProgress++;
				} catch (NumberFormatException ex) {

				}

			}
		}

		_progressBar.setValue(0);
		_progressBar.setMaximum(81 - initProgress);

	}

	@Override
	public void setProgress(int i) {

		_progressBar.setValue(i);

	}

}
