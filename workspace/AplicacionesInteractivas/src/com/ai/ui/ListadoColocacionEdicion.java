package com.ai.ui;

import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import com.ai.controller.Sistema;
import com.ai.ui.models.*;
import javax.swing.JTable;
public class ListadoColocacionEdicion extends JFrame {

	private JPanel contentPane;
	private JTable tabla;
	
	public ListadoColocacionEdicion(Vector<ItemColocacionView> itemscolocacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabla = new JTable();
		DefaultTableModel model = (DefaultTableModel)tabla.getModel();
		model.addColumn("edicion");
		model.addColumn("vendedor");
		model.addColumn("carga");
		tabla.setBounds(15, 170, 702, 250);
		contentPane.add(tabla);
		
		for (int i=0;i<itemscolocacion.size();i++) {
			model.addRow(new Object[] { 
						itemscolocacion.elementAt(i).getIdEdicion(),
						Sistema.getInstance().getPuesto(itemscolocacion.elementAt(i).getCodigoPuesto()).getNombre(),
						itemscolocacion.elementAt(i).getCantidadEjemplares() 
					});;
		}
		
	}
}
