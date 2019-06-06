package org.calminfotech.ledger.utility;

public class LedgerException extends Exception {

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String exceptionMsg;
    

    public LedgerException(String exceptionMsg) {
    	 this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
    
	

}
