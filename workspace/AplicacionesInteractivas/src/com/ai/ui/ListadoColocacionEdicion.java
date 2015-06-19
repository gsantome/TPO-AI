package com.ai.ui;

import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ai.controller.Sistema;
import com.ai.models.ItemColocacion;
import com.ai.ui.models.*;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
public class ListadoColocacionEdicion extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JTextField fieldColocacion;
	private JTextField fieldFechaCol;
	private JTextField fieldPauta;
	
	public ListadoColocacionEdicion(int idcolocacion,Date fechacolocacion,String pauta,Vector<ItemColocacion> itemscolocacion) {
		setPreferredSize(new Dimension(582, 450));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 618, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("edicion");
		model.addColumn("vendedor");
		model.addColumn("carga");
		for (int i=0;i<itemscolocacion.size();i++) {
			model.addRow(new Object[] { 
						itemscolocacion.elementAt(i).getIdEdicion(),
						Sistema.getInstance().getPuesto(itemscolocacion.elementAt(i).getCodigoPuesto()).getNombre(),
						itemscolocacion.elementAt(i).getCantidadEjemplares() 
					});;
		}
		tabla = new JTable(model);
		tabla.setBounds(10, 55, 555, 334);
		contentPane.add(tabla);
		
		JLabel lblColocacin = new JLabel("Colocaci\u00F3n:");
		lblColocacin.setBounds(10, 11, 74, 14);
		contentPane.add(lblColocacin);
		
		fieldColocacion = new JTextField();
		fieldColocacion.setEditable(false);
		fieldColocacion.setBounds(94, 8, 74, 20);
		contentPane.add(fieldColocacion);
		fieldColocacion.setColumns(10);
		fieldColocacion.setText(Integer.toString(idcolocacion));
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(188, 11, 46, 14);
		contentPane.add(lblFecha);
		
		fieldFechaCol = new JTextField();
		fieldFechaCol.setEditable(false);
		fieldFechaCol.setBounds(234, 8, 86, 20);
		contentPane.add(fieldFechaCol);
		fieldFechaCol.setColumns(10);
		fieldFechaCol.setText(fechacolocacion.toString());
		
		JLabel lblPauta = new JLabel("Pauta:");
		lblPauta.setBounds(341, 11, 46, 14);
		contentPane.add(lblPauta);
		
		fieldPauta = new JTextField();
		fieldPauta.setEditable(false);
		fieldPauta.setBounds(388, 8, 86, 20);
		contentPane.add(fieldPauta);
		fieldPauta.setColumns(10);
		fieldPauta.setText(pauta);
		
	}
}
