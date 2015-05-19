package com.ai;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.ai.business.Colocacion;
import com.ai.ui.Colocaciones;
import com.ai.ui.Ediciones;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 304, 220);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAbmEdiciones = new JButton("ABM Ediciones");
		btnAbmEdiciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ediciones edicionesFrame = new Ediciones();
				edicionesFrame.pack();
				edicionesFrame.setVisible(true);
			}
		});
		btnAbmEdiciones.setBounds(15, 16, 252, 59);
		frame.getContentPane().add(btnAbmEdiciones);
		
		JButton btnAbmColocaciones = new JButton("ABM Colocaciones");
		btnAbmColocaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Colocaciones colocacionFrame = new Colocaciones();
				colocacionFrame.pack();
				colocacionFrame.setVisible(true);
			}
		});
		btnAbmColocaciones.setBounds(15, 91, 252, 59);
		frame.getContentPane().add(btnAbmColocaciones);
	}
}
