package org.calminfotech.patient.daoImpl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.patient.daoInterface.PatientSearchDao;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.patient.models.Patient;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PatientSearchDaoImpl implements PatientSearchDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List searchPatient(PatientSearchForm patientsearchForm, HttpSession session) {
		
		List list;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Patient.class);
		
		//Surname Criteria
		if (patientsearchForm.getMycriteria().equals("surname")) {
						
			
			criteria.add(Restrictions.like("surname", "%" + patientsearchForm.getMycriteriavalue().trim() + "%").ignoreCase());
		}
		
		if (patientsearchForm.getMycriteria().equals("email")) {
			
			criteria.add(Restrictions.like("email", "%" + patientsearchForm.getMycriteriavalue().trim() + "%").ignoreCase());
		}
		
		/*
		Criteria c = session.createCriteria(Owner.class, "owner");
		c.createAlias("owner.cats", "cat");
		c.add(Restrictions.eq("cat.eyeColor", "blue");
		*/
		
		
		
	if (patientsearchForm.getMysp().intValue()==0)
	{
		criteria.setFirstResult(0); 

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
		list =	criteria.list();
		 
		session.setAttribute("count", list.size());
		
		criteria.setMaxResults(3);
		 list =	criteria.list();
	}
		
	else
	{
		
		criteria.setFirstResult(patientsearchForm.getMysp()); 

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(3);
		 list =	criteria.list();

	}
	

	
	//increment and set it
	
 Integer fm=patientsearchForm.getMysp()+3;
	
	if ( fm > (Integer) session.getAttribute("count") +3)
		
	{
		fm=0;
	}
	
	patientsearchForm.setMysp(fm);
	

	
return list;
	}

}
