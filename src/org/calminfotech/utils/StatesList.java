package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.hibernatesupport.CustomHibernateDaoSupport;
import org.calminfotech.utils.models.State;
import org.springframework.stereotype.Service;

@Service
public class StatesList extends CustomHibernateDaoSupport {

	public List<State> fetchAll() {
		List list = getHibernateTemplate().find("from State");
		return (List<State>) list;
	}

	public State getStateById(int id) {
		List list = getHibernateTemplate().find("from State where stateId = ?",
				id);
		if (list.size() > 0)
			return (State) list.get(0);
		return null;
	}

	public List<State> fetchStateByCountryCode(String code) {
		List list = getHibernateTemplate().find(
				"from State where country.countryCode = ?", code);

		return (List<State>) list;
	}

	public State getStateByCode(String code) {
		List list = getHibernateTemplate().find(
				"from State where stateCode = ?", code);
		if (list.size() > 0)
			return (State) list.get(0);
		return null;
	}
}
