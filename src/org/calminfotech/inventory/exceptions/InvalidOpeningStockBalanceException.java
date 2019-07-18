/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.exceptions;

/**
 *
 * @author Olalekan
 */
public class InvalidOpeningStockBalanceException extends Exception {

    private String exceptionMsg;

    public InvalidOpeningStockBalanceException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
