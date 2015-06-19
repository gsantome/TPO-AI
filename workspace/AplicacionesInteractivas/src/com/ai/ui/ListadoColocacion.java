package com.ai.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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
import com.ai.ui.models.*;

import javax.swing.JButton;
public class ListadoColocacion extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public ListadoColocacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(107, 33, 103, 20);
		contentPane.add(comboBox);
		Vector<EdicionView> ediciones = Sistema.getInstance().getEdiciones();
		for (int i = 0;i<ediciones.size();i++) {
			comboBox.addItem(ediciones.elementAt(i).getTituloDeTapa());
		}
		
		JLabel lblColocaciones = new JLabel("Ediciones");
		lblColocaciones.setBounds(42, 36, 70, 14);
		contentPane.add(lblColocaciones);
		
		JLabel lblColocacin = new JLabel("Colocaci\u00F3n");
		lblColocacin.setBounds(42, 76, 59, 14);
		contentPane.add(lblColocacin);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(107, 73, 103, 20);
		contentPane.add(comboBox_1);
		comboBox.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent ee) {
		          /*Sistema.getInstance() colocaciones para la edicion seleccionda
		    	 for (int i = 0;i<colocaciones.size();i++) {
		 			comboBox_1.addItem(colocaciones.elementAt(i).get);
		 		}
		 		*/
		     }
		});
		
		
		JButton btnGenerarListado = new JButton("Generar Listado");
		btnGenerarListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListadoColocacionEdicion resultado = new ListadoColocacionEdicion(colocacion.);
				resultado.pack();
				resultado.setVisible(true);
			}
		});
		btnGenerarListado.setBounds(95, 121, 115, 23);
		contentPane.add(btnGenerarListado);
	}
}
