package org.calminfotech.utils.controllers;

import java.util.List;
import java.util.Set;

import org.calminfotech.utils.CountryList;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.models.Country;
import org.calminfotech.utils.models.LocalGovernmentArea;
import org.calminfotech.utils.models.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/utilities/statesandlgas")
public class StatesAndLGAController {

	@Autowired
	private StatesList statesList;
	@Autowired
	private LocalGovernmentAreaList localGovtAreaList;
	@Autowired
	private CountryList countryList;

	@RequestMapping(value = "/states", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getStates() {
		String statesStr = "<option value=''>Select States</option>";
		List<State> stateList = statesList.fetchAll();
		for (State state : stateList) {
			statesStr += "<option value='" + state.getStateId() + "'>"
					+ state.getStateName() + "</option>";
		}
		return statesStr;
	}

	@RequestMapping(value = "/lgas", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgas() {
		String lgasStr = "<option value=''>Select LGA</option>";
		List<LocalGovernmentArea> list = localGovtAreaList.fetchAll();
		for (LocalGovernmentArea lga : list) {
			lgasStr += "<option value='" + lga.getLocalGovernmentAreaId()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";
		}
		return lgasStr;
	}

	@RequestMapping(value = "/state/id/{stateId}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgasByStateIdCodethag(@PathVariable("stateId") Integer id) {
		String lgasStr = "<option value='0'>Select LGA</option>";

		State state = statesList.getStateById(id);

		if (state == null)
			return lgasStr;

		Set<LocalGovernmentArea> list = state.getLocalGovernmentArea();

		for (LocalGovernmentArea lga : list) {
			lgasStr += "<option value='" + lga.getLocalGovernmentAreaId()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";
		}
		return lgasStr;
	}

	@RequestMapping(value = "/lgabystate/{statecode}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getLgasByStateCode(@PathVariable("statecode") String statecode) {
		String lgasStr = "<option value='0'>Select ...</option>";

		State state = statesList.getStateByCode(statecode);

		if (state == null)
			return lgasStr;

		Set<LocalGovernmentArea> list = state.getLocalGovernmentArea();
		lgasStr = "<option value='0'>Select LGA...</option>";
		for (LocalGovernmentArea lga : list) {

			lgasStr += "<option value='" + lga.getLocalGovernmentAreasCode()
					+ "'>" + lga.getLocalGovernmentAreasName() + "</option>";

		}
		return lgasStr;
	}

	@RequestMapping(value = "/statebycountry/{countrycode}", method = RequestMethod.GET, produces = "text/html")
	@ResponseBody
	public String getstatesByCountry(
			@PathVariable("countrycode") String countrycode) {
		String lgasStr = "<option value='0'>Select...</option>";

		Country country = countryList.getCountryByCode(countrycode);

		if (country == null)
			return lgasStr;

		Set<State> list = country.getState();
		lgasStr = "<option value='0'>Select State...</option>";
		for (State state : list) {

			lgasStr += "<option value='" + state.getStateCode() + "'>"
					+ state.getStateName() + "</option>";
		}
		return lgasStr;
	}
}
