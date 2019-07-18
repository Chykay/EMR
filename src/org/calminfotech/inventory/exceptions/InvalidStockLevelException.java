/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.exceptions;

import org.calminfotech.inventory.utils.Text;

/**
 *
 * @author Lala
 */
public class InvalidStockLevelException extends Exception {

    private String exceptionMsg;
    
    public InvalidStockLevelException() {
        this.exceptionMsg = Text.INVALID_STOCK_LEVEL;
    }

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