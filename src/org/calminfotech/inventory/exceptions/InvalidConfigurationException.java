/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.exceptions;

/**
 *
 * @author Lala
 */
public class InvalidConfigurationException extends RuntimeException {

    private String exceptionMsg;

    public InvalidConfigurationException(String customMsg) {
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