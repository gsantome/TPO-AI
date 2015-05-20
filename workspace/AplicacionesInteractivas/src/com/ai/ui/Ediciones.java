package com.ai.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ai.controller.Sistema;
import com.ai.models.Edicion;
import com.ai.models.Publicacion;

import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

public class Ediciones extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JComboBox<Publicacion> comboBoxPublicaciones;
	private JTextField textFieldTitulo;
	private JTextField textFieldPrecio;
	private JTextField textFieldNEjemplares;
	private JTextField textFieldFecha;
	private JLabel lblPublicacion;
	private JLabel lblTitulo;
	private JLabel lblPrecio;
	private JLabel lblNEjemplares;
	private JLabel lblFechaDeSalida;
	
	private Vector<Publicacion> publicaciones;
	private Vector<Edicion> ediciones;
	private JButton btnAgregar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ediciones frame = new Ediciones();
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
	public Ediciones() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 733);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(15, 108, 702, 553);
		contentPane.add(table);
		
		DefaultTableModel model = new DefaultTableModel(0,0) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Object.class, Integer.class, Float.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		
		model.setColumnIdentifiers(new String[] { "Titulo", "Ejemplares", "Precio", "Fecha salida" });
		table.setModel(model);
		
		comboBoxPublicaciones = new JComboBox<Publicacion>();
		comboBoxPublicaciones.setBounds(15, 31, 202, 20);
		contentPane.add(comboBoxPublicaciones);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(227, 31, 202, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setBounds(439, 31, 86, 20);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldNEjemplares = new JTextField();
		textFieldNEjemplares.setBounds(535, 31, 86, 20);
		contentPane.add(textFieldNEjemplares);
		textFieldNEjemplares.setColumns(10);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(631, 31, 86, 20);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		lblPublicacion = new JLabel("Publicaci\u00F3n");
		lblPublicacion.setBounds(15, 16, 80, 14);
		contentPane.add(lblPublicacion);
		
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(227, 16, 46, 14);
		contentPane.add(lblTitulo);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(439, 16, 46, 14);
		contentPane.add(lblPrecio);
		
		lblNEjemplares = new JLabel("N\u00B0 ejemplares");
		lblNEjemplares.setBounds(535, 16, 80, 14);
		contentPane.add(lblNEjemplares);
		
		lblFechaDeSalida = new JLabel("Fecha de salida");
		lblFechaDeSalida.setBounds(631, 16, 120, 14);
		contentPane.add(lblFechaDeSalida);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(631, 62, 86, 23);
		btnAgregar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	//SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
	            	
					//Date d = formatter.parse(textFieldFecha.getText());
					model.addRow(new Object[] { textFieldTitulo.getText(), Integer.decode(textFieldPrecio.getText()), Integer.decode(textFieldNEjemplares.getText()), new Date()});
	            }
		});
		contentPane.add(btnAgregar);
		
		loadPublicaciones();
		loadEdiciones(model);
	}
	
	private void loadPublicaciones() {
		
		publicaciones = Sistema.getInstance().getPublicaciones();
		
		for (Publicacion publicacion : publicaciones) {
			comboBoxPublicaciones.addItem(publicacion);
		}
		
	}
	
	private void loadEdiciones(DefaultTableModel model)
	{
		ediciones = Sistema.getInstance().getEdiciones();
		/*int cantEdiciones = ediciones.size();
		
		Object [][] data = new Object[cantEdiciones][4];
		
		for (int i = 0; i < cantEdiciones; i++) {
			data[i][0] = ediciones.elementAt(i).getTituloDeTapa();
			data[i][1] = ediciones.elementAt(i).getCantidadEjemplares();
			data[i][2] = ediciones.elementAt(i).getPrecio();
			data[i][3] = ediciones.elementAt(i).getFechaSalida();
		}*/
		
		for (Edicion edicion : ediciones) {
			model.addRow(new Object[] { edicion.getTituloDeTapa(), edicion.getCantidadEjemplares(), edicion.getPrecio(), edicion.getFechaSalida()});
		}
		
		/*table.setModel(new DefaultTableModel(
				null,
				new String[] {
					"Titulo", "Ejemplares", "Precio", "Fecha salida"
				}
			) {
				private static final long serialVersionUID = 1L;
				Class[] columnTypes = new Class[] {
					Object.class, Integer.class, Float.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});*/
	}
}
