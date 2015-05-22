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
import com.ai.models.ItemColocacion;
import com.ai.models.Pauta;
import com.ai.models.PautaXAgotado;
import com.ai.models.PautaXDefecto;
import com.ai.models.PautaXExcesoDevolucion;
import com.ai.models.PautaXZona;
import com.ai.models.Puesto;

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
	private ArrayList<ItemColocacion> colocaciones;
	private Pauta pauta;
	

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
				
				loadDataByPauta();
				
				btnContinuar.setEnabled(true);
				
			}
		});
		btnBuscar.setBounds(369, 99, 115, 29);
		contentPane.add(btnBuscar);
		
		hideCondicional();
	}
	
	private void crearColocacion() {
		//Chequeo si necesito mas ejemplares
		if( (this.cantidadEjemplares - pauta.getEjemplaresNecesarios()) < 0 ) {
			colocacion.notifyObservers(this.cantidadEjemplares - pauta.getEjemplaresNecesarios());
		}
		
		TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
		
		colocacion.setIdEdicion(idEdicion);
		colocacion.setPauta(selectedPauta.toString());
		colocacion.setFecha(new Date());
		
		for (ItemColocacion itemColocacion : colocaciones) {
			itemColocacion.setIdPublicacion(idPublicacion);
			itemColocacion.setIdEdicion(idEdicion);
			
			colocacion.addItemColocacion(itemColocacion);
		}
		
		Sistema.getInstance().guardarColocacion(colocacion);
		
		JOptionPane.showMessageDialog(null, "Se ha generado la nueva colocacion.");
		
		colocacion.removeObservers();
		
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
	
	private void loadDataByPauta() {
		
		if( table_1 != null ) {
			TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
			
			pauta = null;
			
			if( selectedPauta == TiposPautas.PorDefecto ) {
				this.hideCondicional();
				
				pauta = new PautaXDefecto();
			}
			else if( selectedPauta == TiposPautas.PorExceso ) {
				this.hideCondicional();
				
				pauta = new PautaXExcesoDevolucion();
			}
			else if( selectedPauta == TiposPautas.PorZona ) {
				this.showCondicional("Zona");
				int zona = Integer.parseInt(this.txtCondicional.getText());
				pauta = new PautaXZona( zona );
				
			}
			else if( selectedPauta == TiposPautas.PorAgotado ) {
				this.showCondicional("Ultimas ediciones");
				int cantUltimasEdiciones = Integer.parseInt(this.txtCondicional.getText());
				pauta = new PautaXAgotado(cantUltimasEdiciones);
			}
			
			if( pauta != null ) {
				
				//TODO Como todas las pautas estan con arraylist y nosotros manejamos vector entionces aca es donde lo casteo
				ArrayList<Puesto> puestos = new ArrayList<Puesto>(Sistema.getInstance().getPuestos());
				
				colocaciones = pauta.procesarColocaciones(puestos, this.cantidadEjemplares, this.idPublicacion, this.idEdicion);
				
				DefaultTableModel dm = (DefaultTableModel)table_1.getModel();
				int rowCount = dm.getRowCount();
				//Remove rows one by one from the end of the table
				for (int i = rowCount - 1; i >= 0; i--) {
				    dm.removeRow(i);
				}
				
				for (ItemColocacion itemColocacion : colocaciones) {
					
					Puesto puesto = Sistema.getInstance().getPuesto(itemColocacion.getCodigoPuesto());
					
					model.addRow(new Object[] { 
							puesto.getNombre(), 
							puesto.getDireccion(),
							itemColocacion.getCantidadEjemplares() 
						});
					
				}
				
			}
		}
		
		
	}
}
