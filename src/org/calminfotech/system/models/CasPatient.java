package org.calminfotech.system.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;



@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true)
@Table(name = "cpatients")
@SQLDelete(sql = "UPDATE cpatients SET is_deleted = 1 WHERE id = ?")
@Where(clause = "is_deleted <> 1")
public class CasPatient {

}
