package org.calminfotech.utils;

import java.util.List;

import org.calminfotech.utils.models.Country;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CountryList {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Country> fetchAll() {
		List<Country> countryList = sessionFactory.getCurrentSession()
				.createCriteria(Country.class).list();
		/*
		 * List<Country> countryList = sessionFactory.getCurrentSession()
		 * .createQuery("from Country").list();
		 */
		System.out.println("Fetch All here");
		return countryList;
	}

	public Country getCountryById(Long id) {
		List<Country> countryList = sessionFactory.getCurrentSession()
				.createCriteria(Country.class).add(Restrictions.eq("id", id))
				.list();
		if (countryList.size() > 0)
			return countryList.get(0);
		return null;

	}

	public Country getCountryByCode(String code) {
		List<Country> countryList = sessionFactory.getCurrentSession()
				.createCriteria(Country.class)
				.add(Restrictions.eq("countryCode", code)).list();
		if (countryList.size() > 0)
			return countryList.get(0);
		return null;

	}

}
