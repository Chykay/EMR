/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calminfotech.inventory.domains;

/**
 *
 * @author Lala
 */
public class MenuItem {
    
    private String id;
    private String name;

    
    public MenuItem() {
    }

    public MenuItem(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
