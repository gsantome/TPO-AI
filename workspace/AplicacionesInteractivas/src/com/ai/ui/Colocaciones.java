package com.ai.ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.ai.controller.Sistema;
import com.ai.models.Edicion;
import com.ai.models.Publicacion;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Colocaciones extends JFrame {

	private JPanel contentPane;
	
	private JComboBox<Publicacion> comboBoxPublicaciones;
	private JComboBox<Edicion> comboBoxEdiciones;
	
	private Vector<Publicacion> publicaciones;
	private JLabel lblCantEjemplares;
	private JTextField txtCantidadEjemplares;

	/**
	 * Create the frame.
	 */
	public Colocaciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 254);
		setPreferredSize(new Dimension(530, 254));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSeleccionesLaPublicacion = new JLabel("Seleccione la Publicacion");
		lblSeleccionesLaPublicacion.setBounds(10, 25, 184, 14);
		contentPane.add(lblSeleccionesLaPublicacion);
		
		comboBoxPublicaciones = new JComboBox<Publicacion>();
		comboBoxPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Publicacion publicacion = (Publicacion)comboBoxPublicaciones.getSelectedItem();
				
				loadEdicion(publicacion);
			}
		});
		comboBoxPublicaciones.setBounds(196, 16, 274, 33);
		contentPane.add(comboBoxPublicaciones);
		
		JLabel lblSeleccioneLaEdicion = new JLabel("Seleccione la Edicion");
		lblSeleccioneLaEdicion.setBounds(10, 64, 171, 14);
		contentPane.add(lblSeleccioneLaEdicion);
		
		comboBoxEdiciones = new JComboBox<Edicion>();
		comboBoxEdiciones.setBounds(196, 55, 274, 33);
		contentPane.add(comboBoxEdiciones);
		
		final JFrame self = this;
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int idPublicacion = ((Publicacion)comboBoxPublicaciones.getSelectedItem()).getCodigo();
				int idEdicion = ((Edicion)comboBoxEdiciones.getSelectedItem()).getCodigo();
				int cantEjemplares = Integer.parseInt(txtCantidadEjemplares.getText());
				
				NuevaColocaciones resultadoColocaciones = new NuevaColocaciones(idPublicacion, idEdicion, cantEjemplares);
				resultadoColocaciones.pack();
				resultadoColocaciones.setVisible(true);
				
				self.dispose();
			}
		});
		btnContinuar.setBounds(362, 168, 108, 23);
		contentPane.add(btnContinuar);
		
		lblCantEjemplares = new JLabel("Cant. Ejemplares");
		lblCantEjemplares.setBounds(10, 103, 131, 20);
		contentPane.add(lblCantEjemplares);
		
		txtCantidadEjemplares = new JTextField();
		txtCantidadEjemplares.setBounds(196, 97, 274, 33);
		contentPane.add(txtCantidadEjemplares);
		txtCantidadEjemplares.setColumns(10);
		
		loadPublicaciones();
	}
	
	private void loadPublicaciones() {
		
		publicaciones = Sistema.getInstance().getPublicaciones();
		
		for (Publicacion publicacion : publicaciones) {
			comboBoxPublicaciones.addItem(publicacion);
		}
		
	}
	
	private void loadEdicion(Publicacion publicacion) {
		comboBoxEdiciones.removeAllItems();
		
		Vector<Edicion> ediciones = Sistema.getInstance().getEdiciones(publicacion.getCodigo());
		
		for (Edicion edicion : ediciones) {
			comboBoxEdiciones.addItem(edicion);
		}
	}
}
