package com.ai.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ai.business.TiposPautas;
import com.ai.controller.Sistema;
import com.ai.models.IPauta;
import com.ai.models.ItemColocacion;
import com.ai.models.PautaXAgotado;
import com.ai.models.PautaXDefecto;
import com.ai.models.PautaXExcesoDevolucion;
import com.ai.models.PautaXZona;
import com.ai.models.Puesto;

public class NuevaColocaciones extends JFrame {

	private JPanel contentPane;
	private JTable table_1;
	private JComboBox comboBox;
	
	private DefaultTableModel tableModel;
	
	private int cantidadEjemplares;
	private int idPublicacion;
	private int idEdicion;

	/**
	 * Create the frame.
	 */
	public NuevaColocaciones(int idPublicacion, int idEdicion, int cantidadEjemplares) {
		this.cantidadEjemplares = cantidadEjemplares;
		this.idPublicacion = idPublicacion;
		this.idEdicion = idEdicion;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 521, 704);
		setPreferredSize(new Dimension(521, 704));
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);
//		setContentPane(contentPane);
//		
//		JLabel lblPautaAUtilizar = new JLabel("Pauta a utilizar");
//		lblPautaAUtilizar.setBounds(15, 16, 131, 20);
//		contentPane.add(lblPautaAUtilizar);
//		
//		comboBox = new JComboBox();
//		comboBox.setModel(new DefaultComboBoxModel(TiposPautas.values()));
//		comboBox.setBounds(149, 11, 335, 31);
//		contentPane.add(comboBox);
////		
////		table_1 = new JTable();
////		table_1.setBounds(15, 58, 469, 529);
////		contentPane.add(table_1);
//		
//		JButton btnContinuar = new JButton("Generar");
//		btnContinuar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//			}
//		});
//		btnContinuar.setBounds(369, 603, 115, 29);
//		contentPane.add(btnContinuar);
//		
//		initTable();
//		loadDataByPauta();
	}
	
	private void initTable() {
		String[] columnsNames = {"Puesto"};
		
		this.tableModel = new DefaultTableModel();
		
		this.tableModel.addColumn("test");
		
		this.table_1.setModel(this.tableModel);
		
	}
	
	private void loadDataByPauta() {
		
		TiposPautas selectedPauta = (TiposPautas)comboBox.getSelectedItem();
		
		IPauta pauta = null;
		
		if( selectedPauta == TiposPautas.PorDefecto ) {
			pauta = new PautaXDefecto();
		}
		else if( selectedPauta == TiposPautas.PorExceso ) {
//			pauta = new PautaXExcesoDevolucion();
		}
		else if( selectedPauta == TiposPautas.PorZona ) {
			pauta = new PautaXZona();
		}
		else if( selectedPauta == TiposPautas.PorAgotado ) {
//			pauta = new PautaXAgotado();
		}
		
		if( pauta != null ) {
			
			//TODO Como todas las pautas estan con arraylist y nosotros manejamos vector entionces aca es donde lo casteo
			ArrayList<Puesto> puestos = new ArrayList<Puesto>(Sistema.getInstance().getPuestos());
			
			ArrayList<ItemColocacion> colocaciones = pauta.procesarColocaciones(puestos, this.cantidadEjemplares);
		}
		
		
	}
}
