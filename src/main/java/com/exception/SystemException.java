package com.exception;

public class SystemException extends RuntimeException {

	public SystemException() {
		super();
	}

	public SystemException(String message, Throwable cause) {
		super(message, cause);
	}

	public SystemException(String message) {
		super(message);
	}

	public SystemException(Throwable cause) {
		super(cause);
	}

	public SystemException(String message, Exception e) {
		super(message);
		errDetail = e;
	}

	//エラー詳細情報
	private Exception errDetail;

	public void setErrDetail(Exception e) {
		errDetail = e;
	}

	public Exception getErrDetail() {
		return errDetail;
	}
}
