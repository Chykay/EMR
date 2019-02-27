package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.List;

import org.calminfotech.system.boInterface.DiseasesBo;
import org.calminfotech.system.daoInterface.DiseasesCategoryDao;

import org.calminfotech.system.daoInterface.DiseasesDao;
import org.calminfotech.system.forms.DiseasesForm;

import org.calminfotech.system.models.Diseases;


//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
//import org.calminfotech.system.models.DiseasesCategoryOuterRecursive;
import org.calminfotech.user.utils.UserIdentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DiseasesBoImpl implements DiseasesBo {
	@Autowired
	private DiseasesDao ItemDao;


	@Override
	public Diseases getDiseasesById(int id)  {
	// TODO Auto-generated method stub
		return ItemDao.getDiseasesById(id);
	}

	@Override
	public void save(Diseases Item) {
		// TODO Auto-generated method stub

		ItemDao.save(Item);
	}

	@Override
	public void update(Diseases Item) {
		// TODO Auto-generated method stub

		ItemDao.update(Item);
	}

	@Override
	public void delete(Diseases category) {
		// TODO Auto-generated method stub

		ItemDao.delete(category);

	}

	/*@Override
	public List<DiseasesCategoryOuterRecursive> fetchAllTypes() {
		// TODO Auto-generated method stub
		return null;
	}*/

	/*@Override
	public List<DiseasesCategoryOuterRecursive> fetchAllTypesNew() {
		// TODO Auto-generated method stub
		System.out.println("Inside the BO");
		return ItemDao.fetchAllTypesNew();

	}
*/
	@Override
	public List<Diseases> fetchAllByOgranisation(int organisationId) {
		// TODO Auto-generated method stub
		
		return ItemDao.fetchAllByOgranisation(organisationId);
	}

	@Override
	public void save(DiseasesForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(DiseasesForm iForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Diseases> fetchAllByOrganisation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Diseases> fetchAll(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchAll(id);
	}

	@Override
	public List<Diseases> fetchTop50byOrganisation(Integer id) {
		// TODO Auto-generated method stub
		return ItemDao.fetchTop50byOrganisation(id);
	}

	@Override
	public List<Diseases> fetchAll() {
		// TODO Auto-generated method stub
		return null;
	}

	


}
