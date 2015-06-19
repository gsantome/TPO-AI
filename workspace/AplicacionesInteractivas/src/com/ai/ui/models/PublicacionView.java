package com.ai.ui.models;

import java.util.Vector;

public class PublicacionView {
	private Vector<EdicionView> ediciones;
	private int codigo;
	private String titulo;
	private String tipoPublicacion;
	private String editor;
	private String tema;
	private String subtema;
	private Boolean sePublico;
	private String periodicidad;
	private String idioma;
	private String paisOrigen;
	
	public PublicacionView() {
		this.ediciones = new Vector<EdicionView>();
	}
	
	public PublicacionView(int codigo, String titulo, String tipoPublicacion, String editor, String tema, String subtema, Boolean sePublico, String periodicidad, String idioma, String paisOrigen) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.tipoPublicacion = tipoPublicacion;
		this.editor = editor;
		this.tema = tema;
		this.subtema = subtema;
		this.sePublico = sePublico;
		this.periodicidad = periodicidad;
		this.idioma = idioma;
		this.paisOrigen = paisOrigen;
		
		this.ediciones = new Vector<EdicionView>();
	}
	
	public void addEdicion(EdicionView edicion) {
		this.ediciones.addElement(edicion);
	}

	public Vector<EdicionView> getEdiciones() {
		return ediciones;
	}

	public void setEdiciones(Vector<EdicionView> ediciones) {
		this.ediciones = ediciones;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(String tipoPublicacion) {
		this.tipoPublicacion = tipoPublicacion;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public String getSubtema() {
		return subtema;
	}

	public void setSubtema(String subtema) {
		this.subtema = subtema;
	}

	public Boolean getSePublico() {
		return sePublico;
	}

	public void setSePublico(Boolean sePublico) {
		this.sePublico = sePublico;
	}

	public String getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getPaisOrigen() {
		return paisOrigen;
	}

	public void setPaisOrigen(String paisOrigen) {
		this.paisOrigen = paisOrigen;
	}
	
	@Override
	public String toString() {
		return this.codigo + ": " + this.titulo + " - " + this.tema;
	}
}
