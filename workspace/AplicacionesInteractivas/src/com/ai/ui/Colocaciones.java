package com.ai.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ai.business.Colocacion;
import com.ai.controller.Sistema;
import com.ai.ui.models.EdicionView;
import com.ai.ui.models.PublicacionView;

public class Colocaciones  extends JFrame {

	private JPanel contentPane;
	
	private JComboBox<PublicacionView> comboBoxPublicaciones;
	private JComboBox<EdicionView> comboBoxEdiciones;
	
	private Vector<PublicacionView> publicaciones;
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
		
		comboBoxPublicaciones = new JComboBox<PublicacionView>();
		comboBoxPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PublicacionView publicacion = (PublicacionView)comboBoxPublicaciones.getSelectedItem();
				
				loadEdicion(publicacion);
			}
		});
		comboBoxPublicaciones.setBounds(196, 16, 274, 33);
		contentPane.add(comboBoxPublicaciones);
		
		JLabel lblSeleccioneLaEdicion = new JLabel("Seleccione la Edicion");
		lblSeleccioneLaEdicion.setBounds(10, 64, 171, 14);
		contentPane.add(lblSeleccioneLaEdicion);
		
		comboBoxEdiciones = new JComboBox<EdicionView>();
		comboBoxEdiciones.setBounds(196, 55, 274, 33);
		contentPane.add(comboBoxEdiciones);
		
		final JFrame self = this;
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if( isNumeric(txtCantidadEjemplares.getText()) ) {
					int idPublicacion = ((PublicacionView)comboBoxPublicaciones.getSelectedItem()).getCodigo();
					int idEdicion = ((EdicionView)comboBoxEdiciones.getSelectedItem()).getCodigo();
					int cantEjemplares = Integer.parseInt(txtCantidadEjemplares.getText());
					
					Colocacion colocacion = new Colocacion();
					
					inicializarObserver( colocacion );
					
					NuevaColocaciones resultadoColocaciones = new NuevaColocaciones(idPublicacion, idEdicion, cantEjemplares, colocacion);
					resultadoColocaciones.pack();
					resultadoColocaciones.setVisible(true);
					
					self.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "La cantidad de ejemplares debe ser un valor numerico");
				}
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
	
	private void inicializarObserver(Colocacion colocacion) {
		PublicacionView publicacion = (PublicacionView)comboBoxPublicaciones.getSelectedItem();
		
		Sistema.getInstance().inicializarObservers(colocacion, publicacion.getEditor());
	}
	
	private void loadPublicaciones() {
		
		publicaciones = Sistema.getInstance().getPublicaciones();
		
		for (PublicacionView publicacion : publicaciones) {
			comboBoxPublicaciones.addItem(publicacion);
		}
		
	}
	
	private void loadEdicion(PublicacionView publicacion) {
		comboBoxEdiciones.removeAllItems();
		
		Vector<EdicionView> ediciones = Sistema.getInstance().getEdiciones(publicacion.getCodigo());
		
		for (EdicionView edicion : ediciones) {
			comboBoxEdiciones.addItem(edicion);
		}
	}
	
	public static boolean isNumeric(String str)  
	{  
		try {  
			double d = Double.parseDouble(str);  
		}  
		catch(NumberFormatException nfe) {  
			return false; 
		}  
		return true;  
	}
}
