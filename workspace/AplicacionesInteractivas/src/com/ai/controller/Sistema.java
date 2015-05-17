package com.ai.controller;

public class Sistema {
	private static Sistema _sistema;
	
	public Sistema()
	{
		
	}
	
	public static Sistema getInstance()
	{
		if( _sistema == null ) {
			_sistema = new Sistema();
		}
		
		return _sistema;
	}
}
