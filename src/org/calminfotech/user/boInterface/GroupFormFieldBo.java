package org.calminfotech.user.boInterface;

import java.util.List;

import org.calminfotech.user.models.GroupFormField;

public interface GroupFormFieldBo {

	public void save(GroupFormField groupFormField);

	public void update(GroupFormField groupFormField);

	public void delete(GroupFormField groupFormField);

	public List<GroupFormField> fetchAllByGroupId(int id);

	public List<GroupFormField> fetchAll();

	public GroupFormField getFieldById(int fieldId);

}
