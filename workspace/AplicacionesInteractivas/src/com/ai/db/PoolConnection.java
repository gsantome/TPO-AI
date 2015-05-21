package com.ai.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;
import java.io.FileInputStream;

public class PoolConnection {
	private Vector<Connection> connections = new Vector<Connection>();
	
	private String jdbc;
	private String server;
	private String user;
	private String password;
	private int countConnections;
	private static PoolConnection instance;
	
	private PoolConnection() {
		getConfiguration();
		for( int c = 0; c < countConnections; c++ ) {
			connections.add(connect());
		}
	}
	
	public static PoolConnection getPoolConnection() {
		if( instance == null ) {
			instance = new PoolConnection();
		}
		
		return instance;
	}
	
	private Connection connect() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String dbConnectString = jdbc + server;
			Connection conn = DriverManager.getConnection(dbConnectString, user, password);
			
			return conn;
		}
		catch(SQLException ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
			
			return null;
		}
		catch(Exception ex) {
			System.err.println("Error: " + ex.getMessage());
			System.err.println(ex.getStackTrace());
			
			return null;
		}
	}
	
	private void getConfiguration() {
		String file = "config.txt";
		Properties props;
		
		try {
			FileInputStream input = new FileInputStream(file);
			props = new Properties();
			props.load(input);
			input.close();
			
			jdbc = props.getProperty("jdbc");
			server = props.getProperty("servidor");
			user = props.getProperty("usuario");
			password = props.getProperty("password");
			countConnections = Integer.parseInt(props.getProperty("conexiones"));
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
			System.err.println(e.getStackTrace());
		}
	}
	
	public void closeConnections() {
		for (int i = 0; i < connections.size(); i++) {
			try {
				connections.elementAt(i).close();
			} catch (Exception e) {
				System.err.println("Error: " + e.getMessage());
				System.err.println(e.getStackTrace());
			}
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		if( connections.size() > 0 ) {
			conn = connections.remove(0);
		}
		else {
			conn = connect();
		}
		
		return conn;
	}
	
	public void releaseConnection(Connection conn) {
		connections.add(conn);
	}
}
