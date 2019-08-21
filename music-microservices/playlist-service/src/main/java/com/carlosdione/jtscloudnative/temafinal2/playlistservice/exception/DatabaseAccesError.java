package com.carlosdione.jtscloudnative.temafinal2.playlistservice.exception;

public class DatabaseAccesError extends RuntimeException{
	public DatabaseAccesError(String msg) {
		super(msg);
	}
}
