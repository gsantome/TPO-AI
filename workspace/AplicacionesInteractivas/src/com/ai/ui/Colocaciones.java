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

public class Colocaciones extends JFrame {

	private JPanel contentPane;
	
	private JComboBox<Publicacion> comboBoxPublicaciones;
	private JComboBox<Edicion> comboBoxEdiciones;
	
	private Vector<Publicacion> publicaciones;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Colocaciones frame = new Colocaciones();
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
	public Colocaciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 388, 154);
		setPreferredSize(new Dimension(388, 154));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSeleccionesLaPublicacion = new JLabel("Seleccione la Publicacion");
		lblSeleccionesLaPublicacion.setBounds(10, 11, 131, 14);
		contentPane.add(lblSeleccionesLaPublicacion);
		
		comboBoxPublicaciones = new JComboBox<Publicacion>();
		comboBoxPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Publicacion publicacion = (Publicacion)comboBoxPublicaciones.getSelectedItem();
				
				loadEdicion(publicacion);
			}
		});
		comboBoxPublicaciones.setBounds(151, 8, 202, 20);
		contentPane.add(comboBoxPublicaciones);
		
		JLabel lblSeleccioneLaEdicion = new JLabel("Seleccione la Edicion");
		lblSeleccioneLaEdicion.setBounds(10, 50, 131, 14);
		contentPane.add(lblSeleccioneLaEdicion);
		
		comboBoxEdiciones = new JComboBox<Edicion>();
		comboBoxEdiciones.setBounds(151, 47, 202, 20);
		contentPane.add(comboBoxEdiciones);
		
		final JFrame self = this;
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ResultadoColocaciones resultadoColocaciones = new ResultadoColocaciones();
				resultadoColocaciones.pack();
				resultadoColocaciones.setVisible(true);
				
				self.dispose();
			}
		});
		btnContinuar.setBounds(264, 80, 89, 23);
		contentPane.add(btnContinuar);
		
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
		for (Edicion edicion : publicacion.getEdiciones()) {
			comboBoxEdiciones.addItem(edicion);
		}
	}
	
	private void loadEdicionInicial() {
		
		Vector<Edicion> ediciones = publicaciones.get(0).getEdiciones();
		
		for (Edicion edicion : ediciones) {
			comboBoxEdiciones.addItem(edicion);
		}
		
	}
}
