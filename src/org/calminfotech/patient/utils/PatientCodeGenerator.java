package org.calminfotech.patient.utils;

import org.calminfotech.patient.boInterface.PatientBo;
import org.calminfotech.patient.utils.RandomString.RandomStringMode;
import org.calminfotech.patient.models.Patient;
import org.calminfotech.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientCodeGenerator {

	@Autowired
	PatientBo patientBo;
	//SubscriberBo subscriberBo;

	ReverseMonthLetter reverseMntLetter = new ReverseMonthLetter();

	public String generateNewCodeFromTop(String birthday) {
		String year = birthday.substring(2, 4);
		String month = birthday.substring(5, 7);
		String monthLetter = MonthLetters.monthInLetters(Integer
				.parseInt(month));
		String day = birthday.substring(8, 10);
		String subId = "01";
		String generatedId = null;
		String alphabetCode;
		alphabetCode = RandomString.generateRandomString(3,
				RandomStringMode.ALPHA);
//		while (true) {
//			alphabetCode = RandomString.generateRandomString(3,
//					RandomStringMode.ALPHA);
//		if(!subscriberBo.isAlphabetCodeExist(alphabetCode))
//				break;
//		}
		generatedId = day + alphabetCode + year + subId + monthLetter;

		return generatedId;
	}

	public String increment(String birthday, String numbaId) {
		String generatedId = null;
		String birth = birthday;
		String numberId = numbaId;

		String hold1 = numberId.substring(0, 7);
		String sequenceId = numberId.substring(7, 9);
		String hold3 = numberId.substring(9, 10);
		int sequenceIdNew = Integer.parseInt(sequenceId) + 1;
		String sequenceIdNewStr = "" + sequenceIdNew;

		if (sequenceIdNewStr.length() < 2) {
			sequenceIdNewStr = "0" + sequenceIdNewStr;
			sequenceId = sequenceIdNewStr;
			generatedId = hold1 + sequenceId + hold3;
		}

		else if (sequenceIdNewStr.length() == 2) {
			sequenceId = sequenceIdNewStr;
			generatedId = hold1 + sequenceId + hold3;
		}

		else if (sequenceIdNewStr.length() > 2) {

			generatedId = this.generateNewCodeFromTop(birth);
		}
		return generatedId;
	}

	public boolean checkIfPatientIdCodeExist(String patientId) {
		Patient patient = patientBo.checkIfPatientIdExist(patientId);				
		if (patient == null)
			return false;
		return true;

	}

	public String decrypt(String patientId) {
		String birthday = "";
		// Decrypt date
		String dd = patientId.substring(0, 2);
		// Decrypt month
		String mm = reverseMntLetter.monthInLetters(patientId.substring(9,
				10));
		// Decrypt year
		String yy = "20" + patientId.substring(5, 7);
		birthday = yy + "-" + mm + "-" + dd;
		return birthday;
	}

	public String generateNumber(String birthday) {
		String generatedId = null;
		System.out.println("Patient DOB: " + birthday);
		Patient patient = patientBo.findByBirthDay(birthday);
				//subscriberBo.findByBirthDay(birthday);

		// Test is the subscriber exist and the decrypted subscriber Id is
		if (patient != null
				&& this.decrypt(DateUtils.formatDateToString(patient.getDob())).equals(birthday) ) {
			generatedId = this
					.increment(birthday, Integer.toString(patient.getPatientId()));
		} else {
			generatedId = this.generateNewCodeFromTop(birthday);
		}
		return generatedId;
	}

	public String processNumber(String birthday) {

		String generatedId = this.generateNumber(birthday);
		while (true) {
			if (checkIfPatientIdCodeExist(generatedId)) {
				if (generatedId.substring(7, 9).equals("01")) {
					// If number is
					generatedId = generateNumber(birthday);
				} else {
					generatedId = increment(birthday, generatedId);
				}
			} else {
				break;
			}

		}
		return generatedId;
	}
}
