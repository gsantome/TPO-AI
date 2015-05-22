package com.ai.db;

import java.util.Vector;

public class PersistenciaColocaciones extends Persistencia {
	
	public static PersistenciaColocaciones instance;
	
	private PersistenciaColocaciones() {
		
	}
	
	public static PersistenciaColocaciones getInstance() {
		
		if( instance == null ) {
			instance = new PersistenciaColocaciones();
		}
		
		return instance;
	}
	
	@Override
	public void insert(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<Object> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object selectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
