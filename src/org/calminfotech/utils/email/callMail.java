/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.calminfotech.utils.email;

/**
 *
 * @author Ajiboye
 */
public class callMail {
    public static void main(String [] sade){
      
       
    	EmailSend send = new EmailSend();
        Boolean gt = send.processMail("ntkyari@gmail.com", "my subject", "Hello folashdade");
        System.out.println(gt);
    }
}
