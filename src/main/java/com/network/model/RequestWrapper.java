package com.network.model;

public class RequestWrapper<T> {

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	int codeStatus;
	Object data;

	String responseMessage;
	public int getCodeStatus() {
		return codeStatus;
	}
	public void setCodeStatus(int codeStatus) {
		this.codeStatus = codeStatus;
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	
}
