package com.ai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class ResultadoColocaciones extends JFrame {

	private JPanel contentPane;
	private JTable colocacionesTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultadoColocaciones frame = new ResultadoColocaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ResultadoColocaciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 338, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblPauta = new JLabel("Pauta a utilizar");
		lblPauta.setBounds(10, 11, 78, 14);
		contentPane.add(lblPauta);
		
		JComboBox comboBoxPautas = new JComboBox();
		comboBoxPautas.setBounds(102, 8, 209, 20);
		contentPane.add(comboBoxPautas);
		
		colocacionesTable = new JTable();
		colocacionesTable.setBounds(10, 54, 301, 413);
		contentPane.add(colocacionesTable);
	}
}
