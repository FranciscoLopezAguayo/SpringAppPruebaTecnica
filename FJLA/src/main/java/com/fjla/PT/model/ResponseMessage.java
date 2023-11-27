package com.fjla.PT.model;

public class ResponseMessage {
	private Object mensaje;

	public Object getMensaje() {
		return mensaje;
	}

	
	public ResponseMessage() {
	}
	
	public ResponseMessage(Object mensaje) {
		this.mensaje = mensaje;
	}
}
