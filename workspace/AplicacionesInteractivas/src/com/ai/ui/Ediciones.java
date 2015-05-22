package com.ai.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.ai.controller.Sistema;
import com.ai.models.Edicion;
import com.ai.models.Publicacion;

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
	private JLabel lblError;
	
	private Vector<Publicacion> publicaciones;
	private Vector<Edicion> ediciones;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	private JButton btnLimpiar;

	/**
	 * Create the frame.
	 */
	public Ediciones() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 748, 490);
		setPreferredSize(new Dimension(748, 490));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.RIGHT);
		lblError.setForeground(Color.RED);
		lblError.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblError.setBounds(15, 108, 702, 14);
		contentPane.add(lblError);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow() != -1)
				{
					lblError.setText("");
					//comboBoxPublicaciones.disable();

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					textFieldTitulo.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
					textFieldPrecio.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
					textFieldNEjemplares.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
					textFieldFecha.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(15, 170, 702, 250);
		contentPane.add(table);
		
		DefaultTableModel model = new DefaultTableModel(0,0) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Float.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			};
			@Override
		    public boolean isCellEditable(int row, int column) {
		       //all cells false
		       return false;
		    }
		};
		
		model.setColumnIdentifiers(new String[] { "Codigo", "Titulo", "Ejemplares", "Precio", "Fecha salida" });
		table.setModel(model);
		
		comboBoxPublicaciones = new JComboBox<Publicacion>();
		comboBoxPublicaciones.setBounds(15, 31, 202, 20);
		comboBoxPublicaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadEdiciones(model);
			}
		});
		contentPane.add(comboBoxPublicaciones);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(15, 77, 202, 20);
		contentPane.add(textFieldTitulo);
		textFieldTitulo.setColumns(10);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c = (int)e.getKeyChar();
				if(c >= 46 && c <= 57)
				{
					if(c == 46)
					{
						if(textFieldPrecio.getText().contains("."))
							e.setKeyChar((char)KeyEvent.VK_CLEAR);
						else if(textFieldPrecio.getText().length() == 0)
							textFieldPrecio.setText("0");
					}
					if(c == 47)
						e.setKeyChar((char)KeyEvent.VK_CLEAR);
				}
				else
				{
					e.setKeyChar((char)KeyEvent.VK_CLEAR);
					e.consume();
				}
			}
		});
		textFieldPrecio.setBounds(227, 77, 86, 20);
		contentPane.add(textFieldPrecio);
		textFieldPrecio.setColumns(10);
		
		textFieldNEjemplares = new JTextField();
		textFieldNEjemplares.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int c = (int)e.getKeyChar();
				if(c < 48 || c > 57)
				{
					e.setKeyChar((char)KeyEvent.VK_CLEAR);
					e.consume();
				}
			}
		});
		
		textFieldNEjemplares.setBounds(323, 77, 86, 20);
		contentPane.add(textFieldNEjemplares);
		textFieldNEjemplares.setColumns(10);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(419, 77, 86, 20);
		contentPane.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		lblPublicacion = new JLabel("Publicaci\u00F3n");
		lblPublicacion.setBounds(15, 16, 80, 14);
		contentPane.add(lblPublicacion);
		
		lblTitulo = new JLabel("Titulo");
		lblTitulo.setBounds(15, 62, 46, 14);
		contentPane.add(lblTitulo);
		
		lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(227, 62, 46, 14);
		contentPane.add(lblPrecio);
		
		lblNEjemplares = new JLabel("N\u00B0 ejemplares");
		lblNEjemplares.setBounds(323, 62, 80, 14);
		contentPane.add(lblNEjemplares);
		
		lblFechaDeSalida = new JLabel("Fecha de salida");
		lblFechaDeSalida.setBounds(419, 62, 120, 14);
		contentPane.add(lblFechaDeSalida);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(631, 133, 86, 23);
		btnAgregar.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent ae) {
	            	try
	            	{
		            	lblError.setText("");
		            	
		            	if(!isThisDateValid(textFieldFecha.getText(), "dd/MM/yyyy"))
		            		lblError.setText("Formato de fecha inválida. Formato correcto dd/MM/yyyy");
		            	else
		            	{
			            	Edicion edicion = new Edicion (
			            			0,
			            			textFieldTitulo.getText(),
			            			Float.parseFloat(textFieldPrecio.getText()),
			            			new SimpleDateFormat("dd/MM/yyyy").parse(textFieldFecha.getText()),
			            			Integer.decode(textFieldNEjemplares.getText())
			            			);
			            	
			    			Publicacion publicacion = (Publicacion)comboBoxPublicaciones.getSelectedItem();
							
							Sistema.getInstance().altaEdicion(edicion, publicacion.getCodigo());
							
							model.addRow(new Object[] {
									edicion.getCodigo(),
									edicion.getTituloDeTapa(),
									edicion.getPrecio(),
									edicion.getCantidadEjemplares(),
									edicion.getFechaSalida()
									});
		            	}
	            	}
	            	catch(Exception ex)
	            	{
	            		ex.printStackTrace();
	            	}
	            }
		});
		contentPane.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(532, 133, 89, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try
				{
					lblError.setText("");
					
					if(table.getSelectedRow() == -1)
					{
						lblError.setText("Debe seleccionar una Edición");
					}
					else if(textFieldTitulo.getText().length() == 0)
					{
						lblError.setText("El titulo no puede estar vacio");
					}
					else if(textFieldPrecio.getText().length() == 0)
					{
						lblError.setText("El precio no puede estar vacio");
					}
					else if(textFieldNEjemplares.getText().length() == 0)
					{
						lblError.setText("La cantidad de ejemplares no puede estar vacio");
					}
					else if(!isThisDateValid(textFieldFecha.getText(), "dd/MM/yyyy"))
					{
						lblError.setText("Formato de fecha inválida. Formato correcto dd/MM/yyyy");
					}
					else
					{
						//DefaultTableModel model = (DefaultTableModel)table.getModel();
						model.setValueAt(textFieldTitulo.getText(), table.getSelectedRow(), 1);
						model.setValueAt(Float.parseFloat(textFieldPrecio.getText()), table.getSelectedRow(), 2);
						model.setValueAt(Integer.decode(textFieldNEjemplares.getText()), table.getSelectedRow(), 3);
						model.setValueAt(textFieldFecha.getText(), table.getSelectedRow(), 4);
						
						//Llamar Modificacion
						Edicion edicion = new Edicion (
		            			Integer.decode(model.getValueAt(table.getSelectedRow(), 0).toString()),
		            			textFieldTitulo.getText(),
		            			Float.parseFloat(textFieldPrecio.getText()),
		            			new Date(),
		            			Integer.decode(textFieldNEjemplares.getText())
		            			);
						
						Sistema.getInstance().modificacionEdicion(edicion);
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(433, 133, 89, 23);
		btnEliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				try
				{
					lblError.setText("");
					if(table.getSelectedRow() == -1)
					{
						lblError.setText("Debe seleccionar una Ediciï¿½n");
					}
					else if(textFieldTitulo.getText().length() == 0)
					{
						lblError.setText("El titulo no puede estar vacio");
					}
					else if(textFieldPrecio.getText().length() == 0)
					{
						lblError.setText("El precio no puede estar vacio");
					}
					else if(textFieldNEjemplares.getText().length() == 0)
					{
						lblError.setText("La cantidad de ejemplares no puede estar vacio");
					}
					else
					{
						//Llamar BAJA
						DefaultTableModel model = (DefaultTableModel)table.getModel();
						Sistema.getInstance().bajaEdicion(Integer.decode(model.getValueAt(table.getSelectedRow(), 0).toString()));
						
						model.removeRow(table.getSelectedRow());
					}
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		});
		contentPane.add(btnEliminar);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(334, 133, 89, 23);
		btnLimpiar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				table.getSelectionModel().clearSelection();
				//comboBoxPublicaciones.enable();
				
				textFieldTitulo.setText("");
				textFieldPrecio.setText("");
				textFieldNEjemplares.setText("");
				textFieldFecha.setText("");
				comboBoxPublicaciones.setSelectedIndex(0);
			}
		});
		contentPane.add(btnLimpiar);
		
		loadPublicaciones();
		
		loadEdiciones(model);
	}
	
	private void loadPublicaciones() {
		try
		{
			publicaciones = Sistema.getInstance().getPublicaciones();
			
			for (Publicacion publicacion : publicaciones) {
				comboBoxPublicaciones.addItem(publicacion);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
	
	private void loadEdiciones(DefaultTableModel model)
	{
		try
		{
			model.getDataVector().removeAllElements();
			
			Publicacion publicacion = (Publicacion)comboBoxPublicaciones.getSelectedItem();
			ediciones = Sistema.getInstance().getEdicionesByPublicacion(publicacion.getCodigo());
			
			for (Edicion edicion : ediciones) {
				model.addRow(new Object[] { edicion.getCodigo(), edicion.getTituloDeTapa(), edicion.getPrecio(), edicion.getCantidadEjemplares(), edicion.getFechaSalida()});
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
		 
	public boolean isThisDateValid(String dateToValidate, String dateFromat){
 
		if(dateToValidate == null){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
 
		} catch (ParseException e) {
 
			e.printStackTrace();
			return false;
		}
 
		return true;
	}
}
