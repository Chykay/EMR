package org.calminfotech.system.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQuery;

@Entity
@NamedNativeQuery(name = "spGetAllCategories", query = "{EXEC outerrecursive}", callable = true, resultClass = GlobalItemCategoryOuterRecursive.class)
@Table(name = "b_category_global_item")
@org.hibernate.annotations.Entity(dynamicInsert = true)
public class GlobalItemCategoryOuterRecursive {

	@Id
	private int rowId;

	private String names;

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

}
