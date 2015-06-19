package com.ai.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ai.business.Colocacion;
import com.ai.business.TiposPautas;
import com.ai.controller.Sistema;
import com.ai.ui.models.ItemColocacionView;
import com.ai.ui.models.PuestoView;

public class NuevaColocaciones extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JComboBox comboBox;
	private JLabel lblCondicional;
	private JButton btnContinuar;
	private JTextField txtCondicional;
	
	private Colocacion colocacion;
	private DefaultTableModel model;
	private int cantidadEjemplares;
	private int idPublicacion;
	private int idEdicion;
	private ArrayList<ItemColocacionView> colocaciones;
	

	/**
	 * Create the frame.
	 */
	public NuevaColocaciones(int idPublicacion, int idEdicion, int cantidadEjemplares, Colocacion colocacion) {
		this.cantidadEjemplares = cantidadEjemplares;
		this.idPublicacion = idPublicacion;
		this.idEdicion = idEdicion;
		this.colocacion = colocacion;
		
		model = new DefaultTableModel();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 704);
		setPreferredSize(new Dimension(521, 704));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblPautaAUtilizar = new JLabel("Pauta a utilizar");
		lblPautaAUtilizar.setBounds(15, 16, 131, 20);
		contentPane.add(lblPautaAUtilizar);
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
				
				if( selectedPauta == TiposPautas.PorDefecto ) {
					hideCondicional();
				}
				else if( selectedPauta == TiposPautas.PorExceso ) {
					hideCondicional();
				}
				else if( selectedPauta == TiposPautas.PorZona ) {
					showCondicional("Zona");
				}
				else if( selectedPauta == TiposPautas.PorAgotado ) {
					showCondicional("Ultimas ediciones");
				}
				
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(TiposPautas.values()));
		comboBox.setBounds(149, 11, 335, 31);
		contentPane.add(comboBox);
		
		table_1 = new JTable();
		table_1.setBounds(15, 144, 469, 443);
		contentPane.add(table_1);
		
		model.addColumn("Puesto");
		model.addColumn("Direccion");
		model.addColumn("Cantidad Ejemplares");
		
		table_1.setModel(model);
		
		btnContinuar = new JButton("Generar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				crearColocacion();
			
			}
		});
		btnContinuar.setBounds(369, 603, 115, 29);
		btnContinuar.setEnabled(false);
		contentPane.add(btnContinuar);
		
		lblCondicional = new JLabel("Zona");
		lblCondicional.setBounds(15, 67, 131, 20);
		contentPane.add(lblCondicional);
		
		txtCondicional = new JTextField();
		txtCondicional.setBounds(149, 61, 335, 26);
		contentPane.add(txtCondicional);
		txtCondicional.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem(); 
				
				try {
					if( selectedPauta == TiposPautas.PorZona || selectedPauta == TiposPautas.PorAgotado) {
						if( isNumeric(txtCondicional.getText()) ) {
							loadDataByPauta();
							
							btnContinuar.setEnabled(true);
						}
						else {
							btnContinuar.setEnabled(false);
							
							JOptionPane.showMessageDialog(null, "El valor ingresado debe ser numerico");
						}
					}
					else {
						loadDataByPauta();
						
						btnContinuar.setEnabled(true);
					}
				}
				catch(Exception ex) {
					TiposPautas selectedPauta2 = (TiposPautas)comboBox.getSelectedItem();
					
					if( selectedPauta2.PorZona == TiposPautas.PorZona ) {
						JOptionPane.showMessageDialog(null, "La zona ingresada no existe");
					}
				}
				
			}
		});
		btnBuscar.setBounds(369, 99, 115, 29);
		contentPane.add(btnBuscar);
		
		hideCondicional();
	}
	
	private void crearColocacion() {
		TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
		
		Sistema.getInstance().crearColocacion(colocacion, cantidadEjemplares, idPublicacion, idEdicion, colocaciones, selectedPauta.toString());
		
		JOptionPane.showMessageDialog(null, "Se ha generado la nueva colocacion.");
		
		this.dispose();
	}
	
	private void showCondicional(String label) {
		this.lblCondicional.setText(label);
		this.lblCondicional.setVisible(true);
		this.txtCondicional.setVisible(true);
	}
	
	private void hideCondicional() {
		if( this.lblCondicional != null ) {
			this.lblCondicional.setVisible(false);
			this.txtCondicional.setVisible(false);
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
	
	private void loadDataByPauta() {
		
		if( table_1 != null ) {
			TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
			
			int condicional = -1;
			
			if( isNumeric(this.txtCondicional.getText()) ) {
				condicional = Integer.parseInt(this.txtCondicional.getText());
			}
			
			colocaciones = Sistema.getInstance().getItemsColocacionByPauta(selectedPauta, condicional, cantidadEjemplares, idPublicacion, idEdicion);
			
			DefaultTableModel dm = (DefaultTableModel)table_1.getModel();
			int rowCount = dm.getRowCount();
			//Remove rows one by one from the end of the table
			for (int i = rowCount - 1; i >= 0; i--) {
			    dm.removeRow(i);
			}
			
			for (ItemColocacionView itemColocacion : colocaciones) {
				
				PuestoView puesto = Sistema.getInstance().getPuesto(itemColocacion.getCodigoPuesto());
				
				model.addRow(new Object[] { 
						puesto.getNombre(), 
						puesto.getDireccion(),
						itemColocacion.getCantidadEjemplares() 
					});
				
			}
			
		}
		
		
	}
}
