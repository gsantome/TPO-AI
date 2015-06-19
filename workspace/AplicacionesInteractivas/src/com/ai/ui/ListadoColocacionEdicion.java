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
public class ListadoColocacionEdicion extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	private JTextField textField;
	private JTextField fieldFechaCol;
	private JTextField fieldPauta;
	
	public ListadoColocacionEdicion(Date fechacolocacion,String pauta,Vector<ItemColocacion> itemscolocacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		tabla.setBounds(10, 55, 582, 334);
		contentPane.add(tabla);
		
		JLabel lblColocacin = new JLabel("Colocaci\u00F3n:");
		lblColocacin.setBounds(10, 11, 64, 14);
		contentPane.add(lblColocacin);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(70, 8, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblFecha = new JLabel("Fecha:");
		lblFecha.setBounds(179, 11, 46, 14);
		contentPane.add(lblFecha);
		
		fieldFechaCol = new JTextField();
		fieldFechaCol.setEditable(false);
		fieldFechaCol.setBounds(218, 8, 86, 20);
		contentPane.add(fieldFechaCol);
		fieldFechaCol.setColumns(10);
		fieldFechaCol.setText(fechacolocacion.toString());
		
		JLabel lblPauta = new JLabel("Pauta:");
		lblPauta.setBounds(330, 11, 46, 14);
		contentPane.add(lblPauta);
		
		fieldPauta = new JTextField();
		fieldPauta.setEditable(false);
		fieldPauta.setBounds(367, 8, 86, 20);
		contentPane.add(fieldPauta);
		fieldPauta.setColumns(10);
		fieldPauta.setText(pauta);
		
	}
}
