package com.isi.exceptions;

public class CannotLoadImageException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CannotLoadImageException(){
		super("CannotLoadImageException: Image == null!");
	}
}