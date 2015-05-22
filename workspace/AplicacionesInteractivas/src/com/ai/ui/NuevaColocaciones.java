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
	
	private DefaultTableModel model;
	private int cantidadEjemplares;
	private int idPublicacion;
	private int idEdicion;
	private ArrayList<ItemColocacion> colocaciones;
	private int cantAgregados = 0;
	private Pauta pauta;

	/**
	 * Create the frame.
	 */
	public NuevaColocaciones(int idPublicacion, int idEdicion, int cantidadEjemplares) {
		this.cantidadEjemplares = cantidadEjemplares;
		this.idPublicacion = idPublicacion;
		this.idEdicion = idEdicion;
		
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
		comboBox.setModel(new DefaultComboBoxModel(TiposPautas.values()));
		comboBox.setBounds(149, 11, 335, 31);
		contentPane.add(comboBox);
		
		table_1 = new JTable();
		table_1.setBounds(15, 58, 469, 529);
		contentPane.add(table_1);
		
		model.addColumn("Puesto");
		model.addColumn("Direccion");
		model.addColumn("Cantidad Ejemplares");
		
		table_1.setModel(model);
		
		JButton btnContinuar = new JButton("Generar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				crearColocacion();
			
			}
		});
		btnContinuar.setBounds(369, 603, 115, 29);
		contentPane.add(btnContinuar);
		
		loadDataByPauta();
	}
	
	private void crearColocacion() {
		TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
		
		Colocacion colocacion = new Colocacion(idEdicion, selectedPauta.toString(), new Date());
		
		for (ItemColocacion itemColocacion : colocaciones) {
			itemColocacion.setIdPublicacion(idPublicacion);
			itemColocacion.setIdEdicion(idEdicion);
			
			colocacion.addItemColocacion(itemColocacion);
		}
		
		Sistema.getInstance().guardarColocacion(colocacion);
		
		//chequeo si la cantidad de ejemplares esta bien o necesito notificar para que manden mas
		if( pauta.getEjemplaresNecesarios() > 0 ) {
			//notifico a los observers
		}
		
		JOptionPane.showMessageDialog(null, "Se ha generado la nueva colocacion.");
		
		this.dispose();
	}
	
	private void loadDataByPauta() {
		
		TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
		
		pauta = null;
		
		if( selectedPauta == TiposPautas.PorDefecto ) {
			pauta = new PautaXDefecto();
		}
		else if( selectedPauta == TiposPautas.PorExceso ) {
			pauta = new PautaXExcesoDevolucion();
		}
		else if( selectedPauta == TiposPautas.PorZona ) {
			pauta = new PautaXZona();
		}
		else if( selectedPauta == TiposPautas.PorAgotado ) {
			pauta = new PautaXAgotado();
		}
		
		if( pauta != null ) {
			
			//TODO Como todas las pautas estan con arraylist y nosotros manejamos vector entionces aca es donde lo casteo
			ArrayList<Puesto> puestos = new ArrayList<Puesto>(Sistema.getInstance().getPuestos());
			
			colocaciones = pauta.procesarColocaciones(puestos, this.cantidadEjemplares, this.idPublicacion, this.idEdicion);
			
			for (int i = 0; i < cantAgregados; i++) {
				model.removeRow(i);
			}
			
			cantAgregados = colocaciones.size();
			
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
