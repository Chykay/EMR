/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.error.custom.exception;

/**
 *
 * @author Lala
 */
public class InvalidStockLevelException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;

    public InvalidStockLevelException(String customMsg) {
        this.exceptionMsg = customMsg;
    }

    //getter and setter methods
    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}