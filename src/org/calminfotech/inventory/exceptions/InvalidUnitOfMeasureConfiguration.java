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
public class InvalidUnitOfMeasureConfiguration extends Exception {

    private String exceptionMsg="Invalid unit of measure";
    
    public InvalidUnitOfMeasureConfiguration() {
    }

    public InvalidUnitOfMeasureConfiguration(String customMsg) {
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