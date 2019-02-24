package com.deviceMonitor.model.syn;

/**
 * 接口调用返回信息
 *
 */
public class SynResultMessage {
    private Object code; 
    private String msg;
    
    /**
     * @return the code
     */
    public Object getReturn() {
        return code;
    }
    /**
     * @param code the code to set
     */
    public void setReturn(Object code) {
        this.code = code;
    }
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }
    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
