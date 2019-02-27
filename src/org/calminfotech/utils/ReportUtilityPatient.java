package org.calminfotech.utils;

import java.util.ArrayList;
import java.util.List;


import org.calminfotech.system.forms.AdmissionSearchForm;
import org.calminfotech.system.forms.AllergySearchForm;
import org.calminfotech.system.forms.BillingSearchForm;

import org.calminfotech.system.forms.DiseasesSearchForm;
import org.calminfotech.system.forms.DrugSearchForm;
import org.calminfotech.system.forms.ExaminationSearchForm;
import org.calminfotech.system.forms.GlobalitemSearchForm;
import org.calminfotech.system.forms.InventorySearchForm;
import org.calminfotech.system.forms.LaboratorySearchForm;
import org.calminfotech.system.forms.SurgerySearchForm;
import org.calminfotech.system.forms.XraySearchForm;
import org.calminfotech.system.models.Diseases;
import org.calminfotech.system.models.Examination;
import org.calminfotech.system.models.GlobalItem;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.Admissionwinsearch;
import org.calminfotech.utils.models.Allergywinsearch;
import org.calminfotech.utils.models.Billingwinsearch;
import org.calminfotech.utils.models.Bloodgenotype;
import org.calminfotech.utils.models.Count;
import org.calminfotech.utils.models.Diseaseswinsearch;
import org.calminfotech.utils.models.Drugwinsearch;
import org.calminfotech.utils.models.Examinationwinsearch;
import org.calminfotech.utils.models.Inventorywinsearch;
import org.calminfotech.utils.models.Laboratorywinsearch;
import org.calminfotech.utils.models.Patientsearch;
import org.calminfotech.utils.models.Patientwinsearch;
import org.calminfotech.utils.models.Personnelwinsearch;
import org.calminfotech.utils.models.Surgerywinsearch;
import org.calminfotech.utils.models.Xraywinsearch;
import org.springframework.stereotype.Service;


import java.util.List;


import org.hibernate.Query;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.calminfotech.hrunit.forms.PersonnelSearchForm;
import org.calminfotech.hrunit.models.StaffRegistration;
//import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.patient.daoInterface.PatientSearchDao;
import org.calminfotech.patient.forms.PatientSearchForm;
import org.calminfotech.patient.models.Patient;

import org.hibernate.Criteria;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//@Repository
//@Transactional
@Service

public class ReportUtilityPatient  {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserIdentity userIdentity;
	
	
	public List PatientList(PatientSearchForm patientsearchForm, HttpSession session) {

	String hsql="select * from vw_patient_search p where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code  + ' ' + p.patient_Fileno + ' ' + p.telnumber + ' ' + p.email like '%' + ? + '%' and p.organisation_Id=? ";
					
			   Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(hsql)
				.addEntity(Patientsearch.class)
			
				.setParameter(0, patientsearchForm.getMycriteriavalue().trim())
				.setParameter(1, userIdentity.getOrganisation().getId());
				
			  query.setFirstResult(patientsearchForm.getMysp());
	          List<Patientsearch>  list	= query.list();  
	 	     
	        
return list;

		
		
	}

	
			
		

	/*
	int batch = 100;
	String sqlct="";
	String hsql="";
	
if 	(patientsearchForm.getMycriteria().equals("name"))
{
sqlct="select count(*) as num from vw_patient_search p  where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code + ' ' + p.patient_fileno + ' ' + p.telnumber + ' ' + p.email like '%' + ? + '%' and p.organisation_id=?";
//hsql="select p.patient_code,p.patient_Fileno,p.surname,p.first_Name,p.other_names,p.email,pt.telnumber from Patient_Profile  p left outer join patient_telephone pt on p.patient_id = pt.patient_id  where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code  + ' ' + p.patient_Fileno + ' ' + pt.telnumber  like '%' + ? + '%' and p.organisation_Id=? and  p.is_Deleted=0";
hsql="select * from vw_patient_search p where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code  + ' ' + p.patient_Fileno + ' ' + p.telnumber + ' ' + p.email like '%' + ? + '%' and p.organisation_Id=? ";
//hsql="from Patient where "
}
		

	Query count = sessionFactory.getCurrentSession()
			.createSQLQuery(sqlct)
			.addEntity(Count.class);
			count.setParameter(0, patientsearchForm.getMycriteriavalue().trim());
			count.setParameter(1, userIdentity.getOrganisation().getId());
		  List<Count> clist =  count.list();
		
		  *
		  *
		      /*
        List<Object[]>  list	= query.list();  
     
        List<Patientsearch> result = new ArrayList<Patientsearch>();
     
        for (Object o : list) {
            result.add((Patientsearch) o);
        }
            
            
      */  
        
	 


}
