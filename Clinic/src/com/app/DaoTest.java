package com.app;
/*package com.app;

*//**
 * 
 *//*


import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.app.model.impl.Country;
import com.app.model.impl.LfUser;
import com.app.model.impl.LocationType;
import com.app.model.impl.ProductCategory;
import com.app.model.impl.Role;
import com.app.security.crypto.password.StandardPasswordEncoder;

*//**
 * @author jomu
 *//*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "e:/testing.xml" })
public class DaoTest {

	@Resource
	private GenericDao dao;

	private static Country country;

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Rollback(false)
	public void testGenerateCountries() {
		country = new Country();
		country.setCountrycode("uk");
		country.setName("United Kingdom");
		dao.persist(country);

		ProductCategory cat1 = getProductCategory(country, "en", "balloon gas", 1);
		dao.persist(cat1);

		ProductCategory cat2 = getProductCategory(country, "en", "industrial gas", 2);
		dao.persist(cat2);

		ProductCategory cat3 = getProductCategory(country, "en", "equipment", 3);
		dao.persist(cat3);

		LocationType locationType1 = getLocationType(country, "en", "Store");
		dao.persist(locationType1);

		LocationType locationType2 = getLocationType(country, "en", "Agent");
		dao.persist(locationType2);

	}

	@Test
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Rollback(false)
	public void testGenerateRolesAndUsers() {
		Role role1 = getRole("com.app.model.role.super_admin", "ROLE_SUPER_ADMIN");
		Role role2 = getRole("com.app.model.role.support_admin", "ROLE_SUPPORT_ADMIN");
		Role role3 = getRole("com.app.model.role.location_admin", "ROLE_LOCATION_ADMIN");

		dao.persist(role1);
		dao.persist(role2);
		dao.persist(role3);

		LfUser user = getUser("Test", "Tester", "locfind", "locfind01");
		user.getCountries().add(country);

		user.getGrantedAuthorities().add(role1);
		user.getGrantedAuthorities().add(role2);
		user.getGrantedAuthorities().add(role3);

		dao.persist(user);
		// INSERT INTO LFUSER (userid, firstname, name, login, password) VALUES
		// (-10, 'Test', 'Tester', 'locfind',
		// 'b08284197fe2d00be941a7807aa9d18c1f1cc2135cfb491c52fb22ad91f049315643c08aba883cc8');

		// INSERT INTO USER_TO_COUNTRY(lfuser_id, country_code) VALUES (-10,
		// 'uk');

	}

	private static Role getRole(String messageKey, String authority) {
		Role role = new Role();
		role.setName(messageKey);
		role.setAuthority(authority);
		return role;
	}

	private static LfUser getUser(String firstname, String name, String login, String password) {
		LfUser user = new LfUser();
		user.setFirstname(firstname);
		user.setName(name);
		user.setLogin(login);
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		user.setPassword(encoder.encode(password));
		return user;

	}

	private static ProductCategory getProductCategory(Country country, String language, String name, Integer theOrder) {
		ProductCategory cat = new ProductCategory();
		cat.setCountry(country);
		cat.setLanguage(language);
		cat.setName(name);
		cat.setTheOrder(theOrder);
		return cat;
	}

	private static LocationType getLocationType(Country country, String language, String locationTypeName) {
		LocationType locationType = new LocationType();
		locationType.setCountry(country);
		locationType.setLanguage(language);
		locationType.setLocationType(locationTypeName);
		return locationType;
	}

}
*/