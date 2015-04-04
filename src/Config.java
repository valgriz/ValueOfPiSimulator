import java.awt.Container;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Config extends JFrame {
	static JPanel panel;
	static JButton bSimulate;

	public Config() {
		setSize(300, 200);

		setResizable(false);
		setTitle("Configure simulation");

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		panel = new JPanel(new GridBagLayout());
		add(panel);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(new JLabel("Simulation Duration:"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		JTextField simDur = new JTextField();
		simDur.setColumns(15);
		panel.add(simDur, gbc);

		gbc.gridx = 1;
		gbc.gridy = 3;
		panel.add(new JLabel("Display Size"), gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		JTextField dispSize = new JTextField();
		dispSize.setColumns(15);
		panel.add(dispSize, gbc);

		gbc.gridx = 1;
		gbc.gridy = 5;
		bSimulate = new JButton();
		bSimulate.setText("Run Simulation");
		panel.add(bSimulate, gbc);

		bSimulate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (bSimulate.getText().toLowerCase().equals("run simulation")) {
					bSimulate.setText("Stop Simulation");
					Main.setSimulationParams(Integer.parseInt(simDur.getText()), Integer.parseInt(dispSize.getText()),
							true);
					Main.frame.toFront();
				} else {
					bSimulate.setText("Run Simulation");
					Main.setSimulationParams(Integer.parseInt(simDur.getText()), Integer.parseInt(dispSize.getText()),
							false);
				}
			}
		});

		gbc.gridx = 1;
		gbc.gridy = 6;
		panel.add(new JLabel("Designed by"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		panel.add(new JLabel("Steven Marturano & Vidar Minkovsky"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel.add(new JLabel("ITS 102 - Spring 2015"), gbc);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

	}
}
