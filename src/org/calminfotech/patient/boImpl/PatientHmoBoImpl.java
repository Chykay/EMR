package org.calminfotech.patient.boImpl;

import java.util.List;
import org.calminfotech.patient.boInterface.PatientHmoBo;
import org.calminfotech.patient.daoInterface.PatientHmoDao;
import org.calminfotech.patient.models.PatientHmo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

	@Service
	@Transactional
	public class PatientHmoBoImpl implements PatientHmoBo {

		@Autowired
		private PatientHmoDao patientHmoDao;

		@Override
		public List <PatientHmo> fetchAll() {
			// TODO Auto-generated method stub
			return patientHmoDao.fetchAll();
		}

		@Override
		public PatientHmo getPatientHmoById(int id) {
			// TODO Auto-generated method stub
			return this.patientHmoDao.getPatientHmoById(id);
		}

		@Override
		public void save(PatientHmo patientHmo) {
		this.patientHmoDao.save(patientHmo);
		}

		@Override
		public void delete(PatientHmo patientHmo) {
			// TODO Auto-generated method stub
		this.patientHmoDao.delete(patientHmo);
		}

		@Override
		public void update(PatientHmo patientHmo) {
			/*PatientHmo patientHmo = this.patientHmoDao.getPatientHmoById(id);
			patientHmo.setDescription(ehmoCategoryList.getNames());
			*/
	        this.patientHmoDao.update(patientHmo);
		}

	}

