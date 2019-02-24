package com.deviceMonitor.model;

/**
 * 接口调用返回信息
 *
 */
public class ResultMessage {
	private boolean success;
    private Object result; 
    private String message;
    
    public ResultMessage() {
        
    }
    
    public ResultMessage(boolean success, Object result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }
    
    public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
     * @return the result
     */
    public Object getResult() {
        return result;
    }
    /**
     * @param result the result to set
     */
    public void setResult(Object result) {
        this.result = result;
    }
    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }
    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
