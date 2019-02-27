package org.calminfotech.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.calminfotech.hrunit.forms.PersonnelSearchForm;
import org.calminfotech.hrunit.models.StaffRegistration;
//import org.calminfotech.inventory.models.StockCurrentBalance;
import org.calminfotech.patient.forms.PatientSearchForm;
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
import org.calminfotech.utils.models.Admissionwinsearch;
import org.calminfotech.utils.models.Allergywinsearch;
import org.calminfotech.utils.models.Billingwinsearch;
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
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Repository
//@Transactional
@Service
public class SearchUtility {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private UserIdentity userIdentity;

	public List searchPatient(PatientSearchForm patientsearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		if (patientsearchForm.getMycriteria().equals("name")) {
			sqlct = "select count(*) as num from vw_patient_search p  inner join organisations o on p.organisation_id=o.id where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code + ' ' + p.patient_fileno + ' ' + p.telnumber + ' ' + p.email like '%' + ? + '%' and o.company_id=?";
			// hsql="select p.patient_code,p.patient_Fileno,p.surname,p.first_Name,p.other_names,p.email,pt.telnumber from Patient_Profile  p left outer join patient_telephone pt on p.patient_id = pt.patient_id  where p.surname + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code  + ' ' + p.patient_Fileno + ' ' + pt.telnumber  like '%' + ? + '%' and p.organisation_Id=? and  p.is_Deleted=0";
			hsql = "select * from vw_patient_search p  inner join organisations o on p.organisation_id=o.id where p.surname  + ' ' + p.first_name  + ' ' + p.other_names + ' ' + p.patient_code  + ' ' + p.patient_Fileno + ' ' + p.telnumber + ' ' + p.email like '%' + ? + '%' and o.company_id=? ";
			// hsql="from Patient where "
		}

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, patientsearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getOrganisation().getOrgCoy()
				.getId());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(hsql)
				.addEntity(Patientsearch.class)

				.setParameter(0, patientsearchForm.getMycriteriavalue().trim())
				.setParameter(1,
						userIdentity.getOrganisation().getOrgCoy().getId())

				.setMaxResults(batch);
		query.setFirstResult(patientsearchForm.getMysp());
		List<Patientsearch> list = query.list();

		/*
		 * List<Object[]> list = query.list();
		 * 
		 * List<Patientsearch> result = new ArrayList<Patientsearch>();
		 * 
		 * for (Object o : list) { result.add((Patientsearch) o); }
		 */

		patientsearchForm.setMysp(patientsearchForm.getMysp() + batch);

		if (patientsearchForm.getMysp() > clist.get(0).getNum()) {
			patientsearchForm.setMysp(0);
		}

		return list;

	}

	public List searchPatientwin(PatientSearchForm patientsearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_patient_list v inner join organisations o on v.organisation_id=o.id where v.name like '%' + ? + '%' and o.company_id=? ";
		hsql = "from Patientwinsearch where name  like '%' + ? + '%' and organisation.orgCoy.Id =?";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, patientsearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getOrganisation().getOrgCoy()
				.getId());

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0, patientsearchForm.getMycriteriavalue().trim())
				.setParameter(1,
						userIdentity.getOrganisation().getOrgCoy().getId())

				.setMaxResults(batch)

				.setFirstResult(patientsearchForm.getMysp());
		List<Patientwinsearch> list = query.list();

		patientsearchForm.setMysp(patientsearchForm.getMysp() + batch);

		if (patientsearchForm.getMysp() > clist.get(0).getNum()) {
			patientsearchForm.setMysp(0);
		}

		return list;
	}

	public List searchAllergywin(AllergySearchForm allergySearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_allergy_list where name like '%' + ? + '%'";
		hsql = "from Allergywinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, allergySearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory.getCurrentSession().createQuery(hsql)
				.setParameter(0, allergySearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(allergySearchForm.getMysp());
		List<Allergywinsearch> list = query.list();

		allergySearchForm.setMysp(allergySearchForm.getMysp() + batch);

		if (allergySearchForm.getMysp() > clist.get(0).getNum()) {
			allergySearchForm.setMysp(0);
		}

		return list;
	}

	public List searchBillingitemwin(BillingSearchForm billingSearchForm,
			HttpSession session) {
		// 1 equip 3 service 8 === Specific
		// List list=null;
		int batch = 100;
		String sqlct = "";
		String hsql = "";
		String p = "";
		if (billingSearchForm.getBillingtypeId().intValue() == 1
				|| billingSearchForm.getBillingtypeId().intValue() == 3
				|| billingSearchForm.getBillingtypeId().intValue() == 8) {

			sqlct = "select count(*) as num from vw_billitem_list  v inner join organisations o on v.organisation_id=o.id where v.name  like '%' + ? + '%'  and v.globalitemtype_id = ?  and o.company_id= ?";
			hsql = "from Billingwinsearch where name  like '%' + ? + '%' and globalitemtypeId = ?  and organisation.orgCoy.Id= ? ";
			p = "3";
		}

		// 2 Drug 4 Laboratory 5 Surgery 6 xray 7 Allergy 9 Admision === General
		if (billingSearchForm.getBillingtypeId().intValue() == 2
				|| billingSearchForm.getBillingtypeId().intValue() == 4
				|| billingSearchForm.getBillingtypeId().intValue() == 5
				|| billingSearchForm.getBillingtypeId().intValue() == 6
				|| billingSearchForm.getBillingtypeId().intValue() == 7
				|| billingSearchForm.getBillingtypeId().intValue() == 9) {

			sqlct = "select count(*) as num from vw_billitem_list  where name  like '%' + ? + '%'  and globalitemtype_id = ? ";
			hsql = "from Billingwinsearch where name  like '%' + ? + '%' and globalitemtypeId = ?  ";
			p = "2";
		}

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, billingSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, billingSearchForm.getBillingtypeId());
		if (p.equals("3")) {
			count.setParameter(2, userIdentity.getOrganisation().getOrgCoy()
					.getId());
		}

		List<Count> clist = count.list();

		Query query = sessionFactory.getCurrentSession().createQuery(hsql)
				.setParameter(0, billingSearchForm.getMycriteriavalue().trim())
				.setParameter(1, billingSearchForm.getBillingtypeId());

		if (p.equals("3")) {
			query.setParameter(2, userIdentity.getOrganisation().getOrgCoy()
					.getId());
		}

		query.setMaxResults(batch);
		query.setFirstResult(billingSearchForm.getMysp());
		List<Billingwinsearch> list = query.list();
		System.out.print("sizing" + list.size());

		billingSearchForm.setMysp(billingSearchForm.getMysp() + batch);

		if (billingSearchForm.getMysp() > clist.get(0).getNum()) {
			billingSearchForm.setMysp(0);
		}

		return list;

	}

	public List searchGlobalitem(GlobalitemSearchForm globalitemSearchForm,
			HttpSession session) {

		// 1 equip 3 service 8 Admision===
		// List list=null;
		int batch = 100;
		String sqlct = "";
		String hsql = "";
		String p = "";
		// not shared 1. Equipment 3. Services 8. Admission

		if (globalitemSearchForm.getGlobaltypeId().intValue() == 1
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 3
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 8) {

			sqlct = "select count(*) as num from globalitem_item  v inner join organisations o on v.organisation_id=o.id where globalitem_name   like '%' + ? + '%'  and globalitemtype_id = ?  and o.company_id= ? and is_deleted=0";
			hsql = "from GlobalItem where GlobalitemName  like '%' + ? + '%' and globalitemtype.globalitemTypeId = ?  and organisation.orgCoy.Id= ? and is_deleted=0";
			p = "3";
		}

		// 2 Drug 4 Laboratory 5 Surgery 6 xray 7 Allergy === General
		if (globalitemSearchForm.getGlobaltypeId().intValue() == 2
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 4
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 5
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 6
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 7
				|| globalitemSearchForm.getGlobaltypeId().intValue() == 9) {
			sqlct = "select count(*) as num from globalitem_item where globalitem_name  like '%' + ? + '%'  and globalitemtype_id = ? and is_deleted=0";
			hsql = "from GlobalItem where GlobalitemName  like '%' + ? + '%' and globalitemtype.globalitemTypeId = ? and is_deleted=0";
			p = "2";
		}

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, globalitemSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, globalitemSearchForm.getGlobaltypeId());
		if (p.equals("3")) {
			count.setParameter(2, userIdentity.getOrganisation().getId());
		}

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						globalitemSearchForm.getMycriteriavalue().trim())
				.setParameter(1, globalitemSearchForm.getGlobaltypeId());

		if (p.equals("3")) {
			query.setParameter(2, userIdentity.getOrganisation().getId());
		}
		query.setMaxResults(batch);
		query.setFirstResult(globalitemSearchForm.getMysp());
		List<GlobalItem> list = query.list();

		globalitemSearchForm.setMysp(globalitemSearchForm.getMysp() + batch);

		if (globalitemSearchForm.getMysp() > clist.get(0).getNum()) {
			globalitemSearchForm.setMysp(0);
		}

		return list;

	}

	public List searchExamination(ExaminationSearchForm examinationSearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from examination where examination_name  like '%' + ? + '%'  and examinationtype_id = ?  and is_deleted=0";
		hsql = "from Examination where examinationName  like '%' + ? + '%' and examinationType.examinationTypeId = ?  and is_deleted=0";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, examinationSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, examinationSearchForm.getExaminationtypeId());

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						examinationSearchForm.getMycriteriavalue().trim())
				.setParameter(1, examinationSearchForm.getExaminationtypeId())
				.setMaxResults(batch);
		query.setFirstResult(examinationSearchForm.getMysp());
		List<Examination> list = query.list();

		examinationSearchForm.setMysp(examinationSearchForm.getMysp() + batch);

		if (examinationSearchForm.getMysp() > clist.get(0).getNum()) {
			examinationSearchForm.setMysp(0);
		}

		return list;

	}

	public List searchExaminationwin(
			ExaminationSearchForm examinationSearchForm, HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_examination_list where name like '%' + ? + '%'";
		hsql = "from Examinationwinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, examinationSearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						examinationSearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(examinationSearchForm.getMysp());
		List<Examinationwinsearch> list = query.list();

		examinationSearchForm.setMysp(examinationSearchForm.getMysp() + batch);

		if (examinationSearchForm.getMysp() > clist.get(0).getNum()) {
			examinationSearchForm.setMysp(0);
		}

		return list;
	}

	public List searchLaboratorywin(LaboratorySearchForm laboratorySearchForm,
			HttpSession session) {
		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_laboratory_list where name like '%' + ? + '%'";
		hsql = "from Laboratorywinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, laboratorySearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						laboratorySearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(laboratorySearchForm.getMysp());
		List<Laboratorywinsearch> list = query.list();

		laboratorySearchForm.setMysp(laboratorySearchForm.getMysp() + batch);

		if (laboratorySearchForm.getMysp() > clist.get(0).getNum()) {
			laboratorySearchForm.setMysp(0);
		}

		return list;
	}

	public List searchSurgerywin(SurgerySearchForm surgerySearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_surgery_list where name like '%' + ? + '%'";
		hsql = "from Surgerywinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, surgerySearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory.getCurrentSession().createQuery(hsql)
				.setParameter(0, surgerySearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(surgerySearchForm.getMysp());
		List<Surgerywinsearch> list = query.list();

		surgerySearchForm.setMysp(surgerySearchForm.getMysp() + batch);

		if (surgerySearchForm.getMysp() > clist.get(0).getNum()) {
			surgerySearchForm.setMysp(0);
		}

		return list;
	}

	public List searchDrugwin(DrugSearchForm drugSearchForm, HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_drug_list where name like '%' + ? + '%'";
		hsql = "from Drugwinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, drugSearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory.getCurrentSession().createQuery(hsql)
				.setParameter(0, drugSearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(drugSearchForm.getMysp());
		List<Drugwinsearch> list = query.list();

		drugSearchForm.setMysp(drugSearchForm.getMysp() + batch);

		if (drugSearchForm.getMysp() > clist.get(0).getNum()) {
			drugSearchForm.setMysp(0);
		}

		return list;
	}

	public List searchXraywin(XraySearchForm xraySearchForm, HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_xray_list where name like '%' + ? + '%'";
		hsql = "from Xraywinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, xraySearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory.getCurrentSession().createQuery(hsql)
				.setParameter(0, xraySearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(xraySearchForm.getMysp());
		List<Xraywinsearch> list = query.list();

		xraySearchForm.setMysp(xraySearchForm.getMysp() + batch);

		if (xraySearchForm.getMysp() > clist.get(0).getNum()) {
			xraySearchForm.setMysp(0);
		}

		return list;
	}

	public List searchAdmissionwin(AdmissionSearchForm admissionSearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_admission_list where name like '%' + ? + '%' and organisation_id = ? ";
		hsql = "from Admissionwinsearch where name  like '%' + ? + '%' and organisation_id = ? ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, admissionSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getOrganisation().getId());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						admissionSearchForm.getMycriteriavalue().trim())
				.setParameter(1, userIdentity.getOrganisation().getId())
				.setMaxResults(batch);
		query.setFirstResult(admissionSearchForm.getMysp());
		List<Admissionwinsearch> list = query.list();

		admissionSearchForm.setMysp(admissionSearchForm.getMysp() + batch);

		if (admissionSearchForm.getMysp() > clist.get(0).getNum()) {
			admissionSearchForm.setMysp(0);
		}

		return list;
	}

	public List searchDiseases(DiseasesSearchForm diseasesSearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from diseases where diseases_name  like '%' + ? + '%'  and diseasestype_id = ? and  organisation_id =? and is_deleted=0";
		hsql = "from Diseases where diseasesName  like '%' + ? + '%' and diseasesType.diseasesTypeId = ? and  organisation_id =? and is_deleted=0 ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, diseasesSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, diseasesSearchForm.getDiseasestypeId());
		count.setParameter(2, userIdentity.getOrganisation().getId());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0, diseasesSearchForm.getMycriteriavalue().trim())
				.setParameter(1, diseasesSearchForm.getDiseasestypeId())
				.setParameter(2, userIdentity.getOrganisation().getId())
				.setMaxResults(batch);
		query.setFirstResult(diseasesSearchForm.getMysp());
		List<Diseases> list = query.list();

		diseasesSearchForm.setMysp(diseasesSearchForm.getMysp() + batch);

		if (diseasesSearchForm.getMysp() > clist.get(0).getNum()) {
			diseasesSearchForm.setMysp(0);
		}

		return list;

	}

	public List searchDiseaseswin(DiseasesSearchForm diseasesSearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_diseases_list where name like '%' + ? + '%'";
		hsql = "from Diseaseswinsearch where name  like '%' + ? + '%' ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, diseasesSearchForm.getMycriteriavalue().trim());
		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0, diseasesSearchForm.getMycriteriavalue().trim())
				.setMaxResults(batch);
		query.setFirstResult(diseasesSearchForm.getMysp());
		List<Diseaseswinsearch> list = query.list();

		diseasesSearchForm.setMysp(diseasesSearchForm.getMysp() + batch);

		if (diseasesSearchForm.getMysp() > clist.get(0).getNum()) {
			diseasesSearchForm.setMysp(0);
		}

		return list;
	}

	public List searchPersonnelwin(PersonnelSearchForm personnelSearchForm,
			HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from vw_staff_list where name like '%' + ? + '%' and organisation_id = ?";
		hsql = "from Personnelwinsearch where name  like '%' + ? + '%' and organisation_id = ?";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, personnelSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getOrganisation().getId());

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						personnelSearchForm.getMycriteriavalue().trim())
				.setParameter(1, userIdentity.getOrganisation().getId())

				.setMaxResults(batch);

		query.setFirstResult(personnelSearchForm.getMysp());
		List<Personnelwinsearch> list = query.list();

		personnelSearchForm.setMysp(personnelSearchForm.getMysp() + batch);

		if (personnelSearchForm.getMysp() > clist.get(0).getNum()) {
			personnelSearchForm.setMysp(0);
		}

		return list;
	}

	public List searchStaffRegistration(
			PersonnelSearchForm personnelSearchForm, HttpSession session) {

		int batch = 100;
		String sqlct = "";
		String hsql = "";

		sqlct = "select count(*) as num from staff_registration where  staff_code + ' - ' + last_name + '  ' + first_name + other_name  like '%' + ? + '%' and organisation_id = ? and is_deleted=0";
		hsql = "from StaffRegistration where staff_code + ' - ' + last_name + '  ' + first_name + other_name  like '%' + ? + '%'  and organisation_id = ? and is_deleted=0";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, personnelSearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getOrganisation().getId());

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						personnelSearchForm.getMycriteriavalue().trim())
				.setParameter(1, userIdentity.getOrganisation().getId())

				.setMaxResults(batch);
		query.setFirstResult(personnelSearchForm.getMysp());
		List<StaffRegistration> list = query.list();

		personnelSearchForm.setMysp(personnelSearchForm.getMysp() + batch);

		if (personnelSearchForm.getMysp() > clist.get(0).getNum()) {
			personnelSearchForm.setMysp(0);
		}

		return list;

	}

	public List searchInventorywin(InventorySearchForm inventorySearchForm,
			HttpSession session) {
		// 1 equip 3 service 8 bed room=== Specific
		// List list=null;
		int batch = 100;
		String sqlct = "";
		String hsql = "";
		String p = "";
		if (inventorySearchForm.getInventorytypeId().intValue() == 1
				|| inventorySearchForm.getInventorytypeId().intValue() == 3
				|| inventorySearchForm.getInventorytypeId().intValue() == 8) {

			sqlct = "select count(*) as num from vw_inventory_list where name  like '%' + ? + '%'  and globalitemtype_id = ?  and organisation_id= ?";
			hsql = "from Inventorywinsearch where name  like '%' + ? + '%' and globalitemtypeId = ?  and organisation_id= ? ";
			p = "3";
		}

		// 2 Drug 4 Laboratory 5 Surgery 6 xray 7 Allergy 9 Admision === General
		if (inventorySearchForm.getInventorytypeId().intValue() == 2
				|| inventorySearchForm.getInventorytypeId().intValue() == 4
				|| inventorySearchForm.getInventorytypeId().intValue() == 5
				|| inventorySearchForm.getInventorytypeId().intValue() == 6
				|| inventorySearchForm.getInventorytypeId().intValue() == 7
				|| inventorySearchForm.getInventorytypeId().intValue() == 9) {

			sqlct = "select count(*) as num from vw_inventory_list  where name  like '%' + ? + '%'  and globalitemtype_id = ? ";
			hsql = "from Inventorywinsearch where name  like '%' + ? + '%' and globalitemtypeId = ?  ";
			p = "2";
		}

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, inventorySearchForm.getMycriteriavalue().trim());
		count.setParameter(1, inventorySearchForm.getInventorytypeId());
		if (p.equals("3")) {
			count.setParameter(2, userIdentity.getOrganisation().getId());
		}

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						inventorySearchForm.getMycriteriavalue().trim())
				.setParameter(1, inventorySearchForm.getInventorytypeId());

		if (p.equals("3")) {
			query.setParameter(2, userIdentity.getOrganisation().getId());
		}
		query.setMaxResults(batch);
		query.setFirstResult(inventorySearchForm.getMysp());
		List<Inventorywinsearch> list = query.list();

		inventorySearchForm.setMysp(inventorySearchForm.getMysp() + batch);

		if (inventorySearchForm.getMysp() > clist.get(0).getNum()) {
			inventorySearchForm.setMysp(0);
		}

		return list;

	}

	public void searchStockBalance(InventorySearchForm inventorySearchForm,
			HttpSession session) {
		// 1 equip 3 service 8 bed room=== Specific
		// List list=null;
		int batch = 100;
		String sqlct = "";
		String hsql = "";
		String p = "";

		sqlct = "select count(*) as num from inventory_stock_current_balances a inner join globalitem_item b on a.global_item_id=b.item_id where b.globalitem_name  like '%' + ? + '%' and a.unit_id=?  and b.globalitemtype_id = ?  and a.organisation_id= ?";
		hsql = "from StockCurrentBalance a where a.globalItem.GlobalitemName  like '%' + ? + '%' and unit_id=? and a.globalItem.globalitemtype.globalitemTypeId = ?   and a.organisation.Id= ? ";

		Query count = sessionFactory.getCurrentSession().createSQLQuery(sqlct)
				.addEntity(Count.class);
		count.setParameter(0, inventorySearchForm.getMycriteriavalue().trim());
		count.setParameter(1, userIdentity.getCurrentUnitId());
		count.setParameter(2, inventorySearchForm.getInventorytypeId());
		count.setParameter(3, userIdentity.getOrganisation().getId());

		List<Count> clist = count.list();

		Query query = sessionFactory
				.getCurrentSession()
				.createQuery(hsql)
				.setParameter(0,
						inventorySearchForm.getMycriteriavalue().trim())
				.setParameter(1, userIdentity.getCurrentUnitId())
				.setParameter(2, inventorySearchForm.getInventorytypeId());
		query.setParameter(3, userIdentity.getOrganisation().getId());
		query.setMaxResults(batch);
		query.setFirstResult(inventorySearchForm.getMysp());
//		List<StockCurrentBalance> list = query.list();

		inventorySearchForm.setMysp(inventorySearchForm.getMysp() + batch);

		if (inventorySearchForm.getMysp() > clist.get(0).getNum()) {
			inventorySearchForm.setMysp(0);
		}

//		return list;

	}

}
