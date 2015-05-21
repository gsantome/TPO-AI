package com.ai.db;

import java.util.Vector;

public abstract class Persistencia {
	public abstract void insert(Object obj);
	public abstract void update(Object obj);
	public abstract void delete(Object obj);
	public abstract Vector<Object> selectAll();
	public abstract Object selectById(int id);
}
