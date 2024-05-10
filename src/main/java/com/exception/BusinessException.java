package com.exception;

public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(String message, Exception e) {
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
