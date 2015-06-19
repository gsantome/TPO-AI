package com.ai.ui;

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

import com.ai.business.Colocacion;
import com.ai.controller.Sistema;
import com.ai.ui.models.*;

import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.Dimension;
public class ListadoColocacion extends JFrame {

	private JPanel contentPane;
	private JComboBox<PublicacionView> comboBoxPublicaciones;
	private JComboBox<EdicionView> comboBoxEdiciones;
	private JComboBox<ColocacionView> comboBoxColocaciones;
	private Vector<PublicacionView> publicaciones;
	private Vector<ColocacionView> colocaciones;
	
	public ListadoColocacion() {
		setPreferredSize(new Dimension(400, 230));
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 367, 219);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBoxEdiciones = new JComboBox<EdicionView>();
		comboBoxEdiciones.setBounds(121, 60, 199, 20);
		contentPane.add(comboBoxEdiciones);
		comboBoxEdiciones.addActionListener(new ActionListener(){
		     public void actionPerformed(ActionEvent ee) {
		    	 	EdicionView edicion = (EdicionView)comboBoxEdiciones.getSelectedItem();
		    	 	loadColocaciones(edicion);
		 		}
		     
		});
		
		JLabel lblColocaciones = new JLabel("Ediciones");
		lblColocaciones.setBounds(42, 63, 70, 14);
		contentPane.add(lblColocaciones);
		
		JLabel lblColocacin = new JLabel("Colocaci\u00F3n");
		lblColocacin.setBounds(42, 94, 59, 14);
		contentPane.add(lblColocacin);
		
		comboBoxColocaciones = new JComboBox<ColocacionView>();
		comboBoxColocaciones.setBounds(121, 91, 199, 20);
		contentPane.add(comboBoxColocaciones);		
		
		JButton btnGenerarListado = new JButton("Generar Listado");
		btnGenerarListado.setBounds(121, 135, 138, 23);
		btnGenerarListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Colocacion colocacion = (Colocacion)comboBoxColocaciones.getSelectedItem();
				ListadoColocacionEdicion resultado = new ListadoColocacionEdicion(colocacion.getCodigo(),colocacion.getFecha(),colocacion.getPauta(),colocacion.getItemsColocaciones());
				resultado.pack();
				resultado.setVisible(true);
			}
		});
		contentPane.add(btnGenerarListado);
		
		JLabel lblPublicacin = new JLabel("Publicaci\u00F3n");
		lblPublicacin.setBounds(42, 32, 70, 14);
		contentPane.add(lblPublicacin);
		comboBoxPublicaciones = new JComboBox<PublicacionView>();
		comboBoxPublicaciones.setBounds(121, 29, 199, 20);
		contentPane.add(comboBoxPublicaciones);
		loadPublicaciones();
		comboBoxPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PublicacionView publicacion = (PublicacionView)comboBoxPublicaciones.getSelectedItem();
				
				loadEdicion(publicacion);
			}
		});
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
	
	private void loadColocaciones(EdicionView edicion) {
		comboBoxColocaciones.removeAllItems();
		colocaciones = Sistema.getInstance().getColocaciones(edicion.getCodigo());
		
		for (ColocacionView colocacion : colocaciones) {
			comboBoxColocaciones.addItem(colocacion);
		}
		
	}
	
}
