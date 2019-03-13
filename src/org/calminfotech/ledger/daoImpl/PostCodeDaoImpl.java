package org.calminfotech.ledger.daoImpl;

import java.util.List;

import org.calminfotech.ledger.models.PostCode;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PostCodeDaoImpl {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<PostCode> fetchAll() {
		List<PostCode> postCodes = sessionFactory.getCurrentSession()
				  .createQuery("from PostCode")
				  .list();
		
	return postCodes;
	}
}
