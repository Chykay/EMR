package org.calminfotech.user.boImpl;

import java.util.List;

import org.calminfotech.user.boInterface.GroupFormFieldBo;
import org.calminfotech.user.daoInterface.GroupFormFieldDao;
import org.calminfotech.user.models.GroupFormField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GroupFormFieldBoImpl implements GroupFormFieldBo {

	@Autowired
	private GroupFormFieldDao groupFormFieldDao;
	
	@Override
	public void save(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.groupFormFieldDao.save(groupFormField);
	}

	@Override
	public void update(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.groupFormFieldDao.update(groupFormField);
	}

	@Override
	public void delete(GroupFormField groupFormField) {
		// TODO Auto-generated method stub
		this.groupFormFieldDao.delete(groupFormField);
	}

	@Override
	public List<GroupFormField> fetchAllByGroupId(int id) {
		// TODO Auto-generated method stub
		return this.groupFormFieldDao.fetchAllByGroupId(id);
	}

	@Override
	public List<GroupFormField> fetchAll() {
		// TODO Auto-generated method stub
		return this.groupFormFieldDao.fetchAll();
	}

	@Override
	public GroupFormField getFieldById(int id) {
		// TODO Auto-generated method stub
		return this.groupFormFieldDao.getFieldById(id);
	}

}
