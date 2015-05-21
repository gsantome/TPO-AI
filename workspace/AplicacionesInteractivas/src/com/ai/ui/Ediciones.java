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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
		setBounds(100, 100, 748, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.RIGHT);
		lblError.setForeground(Color.RED);
		lblError.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblError.setBounds(15, 96, 702, 14);
		contentPane.add(lblError);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(table.getSelectedRow() != -1)
				{
					lblError.setText("");

					DefaultTableModel model = (DefaultTableModel)table.getModel();
					textFieldTitulo.setText(model.getValueAt(table.getSelectedRow(), 1).toString());
					textFieldPrecio.setText(model.getValueAt(table.getSelectedRow(), 2).toString());
					textFieldNEjemplares.setText(model.getValueAt(table.getSelectedRow(), 3).toString());
					textFieldFecha.setText(model.getValueAt(table.getSelectedRow(), 4).toString());
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(15, 120, 702, 300);
		contentPane.add(table);
		
		final DefaultTableModel model = new DefaultTableModel(0,0) {
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
		contentPane.add(comboBoxPublicaciones);
		
		textFieldTitulo = new JTextField();
		textFieldTitulo.setBounds(227, 31, 202, 20);
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
		textFieldPrecio.setBounds(439, 31, 86, 20);
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
				int vv = Integer.MAX_VALUE;
			}
		});
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
	            	try
	            	{
		            	lblError.setText("");
		            	
		            	//SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
						//Date d = formatter.parse(textFieldFecha.getText());
		            	Edicion edicion = new Edicion (
		            			0,
		            			textFieldTitulo.getText(),
		            			Float.parseFloat(textFieldPrecio.getText()),
		            			new Date(),
		            			Integer.decode(textFieldNEjemplares.getText())
		            			);
						
						Sistema.getInstance().altaEdicion(edicion);
						
						model.addRow(new Object[] {
								edicion.getCodigo(),
								edicion.getTituloDeTapa(),
								edicion.getPrecio(),
								edicion.getCantidadEjemplares(),
								edicion.getFechaSalida()
								});
	            	}
	            	catch(Exception ex)
	            	{
	            		ex.printStackTrace();
	            	}
	            }
		});
		contentPane.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(535, 62, 89, 23);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try
				{
					lblError.setText("");
					if(table.getSelectedRow() == -1)
					{
						lblError.setText("Debe seleccionar una Edici�n");
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
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(436, 62, 89, 23);
		btnEliminar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				try
				{
					lblError.setText("");
					if(table.getSelectedRow() == -1)
					{
						lblError.setText("Debe seleccionar una Edici�n");
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
			ediciones = Sistema.getInstance().getEdiciones();
			
			for (Edicion edicion : ediciones) {
				model.addRow(new Object[] { edicion.getCodigo(), edicion.getTituloDeTapa(), edicion.getPrecio(), edicion.getCantidadEjemplares(), edicion.getFechaSalida()});
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
