package com.ai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTree;

import com.ai.controller.Sistema;

public class ListadoColocacion extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ListadoColocacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(162, 36, 103, 20);
		contentPane.add(comboBox);
		Sistema.getInstance().
		
		
		JLabel lblColocaciones = new JLabel("Colocaciones");
		lblColocaciones.setBounds(82, 39, 70, 14);
		contentPane.add(lblColocaciones);
		
		JTree tree = new JTree();
		tree.setBounds(10, 97, 72, 64);
		contentPane.add(tree);
	}
}
