package org.calminfotech.system.boImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.calminfotech.system.boInterface.OrganisationCategoryBo;
import org.calminfotech.system.boInterface.OrganisationCompanyBo;
import org.calminfotech.system.boInterface.OrganisationTypeBo;
import org.calminfotech.system.boInterface.ResourceBo;
import org.calminfotech.system.daoInterface.OrganisationCompanyDao;
import org.calminfotech.system.forms.OrganisationCompanyForm;
import org.calminfotech.system.models.Organisation;
import org.calminfotech.system.models.OrganisationCompany;
import org.calminfotech.system.models.SysSettings;
import org.calminfotech.user.boInterface.RoleBo;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.daoInterface.RoleDao;
import org.calminfotech.user.models.Role;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utils.CountryList;
import org.calminfotech.utils.DateUtils;
import org.calminfotech.utils.EmailDomain;
import org.calminfotech.utils.Encryptor;
import org.calminfotech.utils.LocalGovernmentAreaList;
import org.calminfotech.utils.NotificationCentre;
import org.calminfotech.utils.StatesList;
import org.calminfotech.utils.UserTypesList;
import org.calminfotech.utils.email.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrganisationCompanyBoImpl implements OrganisationCompanyBo {

	@Autowired
	private OrganisationCompanyDao organisationDao;

	@Autowired
	private OrganisationTypeBo OrganisationTypeBo;

	@Autowired
	private OrganisationCategoryBo OrganisationCategoryBo;

	@Autowired
	private RoleBo roleBo;

	@Autowired
	private UserTypesList typesListDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private Encryptor encryptor;

	@Autowired
	private UserBo userBo;

	@Autowired
	private EmailSend emailSend;

	@Autowired
	private UserTypesList userTypeList;

	@Autowired
	private ResourceBo resourceBo;

	@Autowired
	private CountryList countryBo;

	@Autowired
	private StatesList stateBo;

	@Autowired
	private LocalGovernmentAreaList lgaBo;

	@Autowired
	private NotificationCentre notificationCentre;

	@Autowired
	private EmailDomain emailDomain;

	@Autowired
	private UserIdentity userIdentity;

	@Override
	public List<OrganisationCompany> fetchAll(int id) {
		// TODO Auto-generated method stub
		return this.organisationDao.fetchAll(id);
	}

	public List<OrganisationCompany> Top50fetchAll(int id) {
		// TODO Auto-generated method stub
		return this.organisationDao.Top50fetchAll(id);
	}

	@Override
	public OrganisationCompany getOrganisationById(int id) {
		// TODO Auto-generated method stub
		return this.organisationDao.getOrganisationById(id);
	}

	@Override
	@Transactional
	public OrganisationCompany save(OrganisationCompanyForm organisationForm) {
		/**
		 * Create the organisation, the system admin user, and all the necessary
		 * roles {adminstrator}
		 */

		// Organisation
		OrganisationCompany organisation = new OrganisationCompany();
		organisation.setName(organisationForm.getName());
		organisation.setDescription(organisationForm.getDescription());
		organisation.setAddress(organisationForm.getAddress());
		organisation.setEmail(organisationForm.getEmail());
		organisation.setDomain(organisationForm.getDomain());
		organisation.setPhoneno(organisationForm.getPhoneno());
		organisation.setOrganisationCategory(this.OrganisationCategoryBo
				.getOrganisationCategoryById(organisationForm
						.getParentCategoryId()));
		organisation.setOrganisationCategory(OrganisationCategoryBo
				.getOrganisationCategoryById(organisationForm
						.getParentCategoryId()));

		organisation.setOrganisationType(OrganisationTypeBo
				.getOrganisationTypeById(organisationForm
						.getOrganisationTypeId()));

		organisation.setCountry(countryBo.getCountryByCode(organisationForm
				.getCountryCode()));

		organisation.setState(stateBo.getStateByCode(organisationForm
				.getStateCode()));

		organisation.setLga(lgaBo.getLgaByCode(organisationForm.getLgaCode()));

		organisation.setCreatedBy(this.userIdentity.getUsername());
		if (!organisationForm.getEstablishedYear().equals("")
				&& DateUtils.isValidDate(organisationForm.getEstablishedYear()))

		{
			organisation.setEstablishedYear(DateUtils
					.formatStringToDate(organisationForm.getEstablishedYear()));
		}

		Set<Role> srole = new HashSet<Role>();
		Role role = new Role();

		role.setOrganisation(organisation);
		role.setAdmin(true);
		role.setRoleDescription("Default Administrator");
		role.setRoleName("Default Adinistrator");
		role.setCreatedDate(new Date(System.currentTimeMillis()));
		role.setCreatedBy("system@primary.com");
		srole.add(role);
		organisation.setRole(srole);

		Set<Organisation> sbranch = new HashSet<Organisation>();

		Organisation branch = new Organisation();

		branch.setCountry(countryBo.getCountryByCode(organisationForm
				.getCountryCode()));

		branch.setState(stateBo.getStateByCode(organisationForm.getStateCode()));

		branch.setLga(lgaBo.getLgaByCode(organisationForm.getLgaCode()));

		branch.setDescription(organisation.getDescription());
		branch.setAddress(organisation.getAddress());
		branch.setEmail(organisation.getEmail());
		branch.setEstablishedYear(organisation.getEstablishedYear());
		branch.setName("Head Office - " + organisation.getName());
		branch.setPhoneno(organisation.getPhoneno());
		sbranch.add(branch);
		this.organisationDao.save(organisation);
		// Set s= new Set();
		// s.add(arg0)

		// get domain
		// @SuppressWarnings("static-access")
		// String domain =
		// this.emailDomain.fetchAt(organisationForm.getSystemEmail());
		// organisation.setDomain(domain);

		// Create the first user
		// User user = new User();
		// user.setUsername(organisationForm.getSystemUserEmail());
		// user.setPassword(organisationForm.getSystemUserPassword());
		// user.setOrganisation(organisation);
		// user.setActivation(true);
		// user.setStatus(true);

		// Set the default user type for organisation admin
		// UserType userType = this.userTypeList
		// .getUserTypeByType(UserType.HOSP_ADMIN);
		// user.setUserType(userType);

		// The default behavior for an organisation first user
		// user.setRole(roleDao.getRoleById(2));
		// organisation.getUsers().add(user);

		// Encrypt password
		// Set<UserActivation> userActivations = user.getUserActivation();
		// Add new activation code here
		// Create the encrypted code
		// String activationCode =
		// encryptor.createActivationCode(organisationForm.getSystemUserEmail());
		// System.out.println("activationCode :"+activationCode);
		// Save activation code
		// UserActivation userActivation = new UserActivation();
		// userActivation.setActivationCode(activationCode);
		// userActivation.setUser(user);
		// userActivation.setUsed(false);
		// userActivation.setExpired(false);
		// userActivation.setCreatedDate(new Date(System.currentTimeMillis()));
		// userActivations.add(userActivation);
		// save UserActivation through user
		// user.setUserActivation(userActivations);
		// update user
		// userBo.update(user);
		// String url =
		// AppConfig.DEVELOPMENTAL_URL+"/user/setup/password/"+activationCode;
		// String url =
		// AppConfig.APP_URL+"/user/setup/password/"+activationCode;
		// Send the new user an email
		// String message = "Thank you for choosing to use our platform. \n";
		// message +=
		// "Use the following details to login and configure your organisation account \n";
		// message += "Username: " + organisationForm.getSystemUserEmail() +
		// "\n";
		// message +=
		// "Password: "+organisationForm.getSystemUserPassword()+"\n\n";
		// message +=
		// "To activate your account and set your password, click the lick: <a href='"+
		// url +"' title = 'Cllick to register'>Activate Account Now</a> \n";

		// message += "EMR Team";
		// Send mail
		// System.out.println("Before mail send");
		// emailSend.processMail(AppConfig.NO_REPLY, null , user.getUsername(),
		// "Opening of EMR Portal for " + organisation.getName(), message);
		System.out.println("After mail sent");
		/*
		 * this.notificationCentre.sendEmailAfter mail sent(user.getUsername(),
		 * "Opening of EMR Portal for " + organisation.getName(), message);
		 */
		return organisation;
	}

	@Override
	public void delete(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.organisationDao.delete(organisation);
	}

	@Override
	public void update(OrganisationCompanyForm organisationForm) {
		// TODO Auto-generated method stub
		OrganisationCompany organisation = this
				.getOrganisationById(organisationForm.getId());

		organisation.setName(organisationForm.getName());
		organisation.setDescription(organisationForm.getDescription());
		organisation.setAddress(organisationForm.getAddress());
		organisation.setEmail(organisationForm.getEmail());
		organisation.setCountry(countryBo.getCountryByCode(organisationForm
				.getCountryCode()));

		organisation.setState(stateBo.getStateByCode(organisationForm
				.getStateCode()));
		organisation.setLga(lgaBo.getLgaByCode(organisationForm.getLgaCode()));

		organisation.setLga(lgaBo.getLgaById(organisationForm.getLgaId()));
		organisation.setModifiedBy(userIdentity.getUsername());
		organisation.setModifiedDate(new Date(System.currentTimeMillis()));

		// organisation.setState(stateBo.getStateById(10));
		// organisation.setLga(lgaBo.getLgaById(17));

		this.organisationDao.update(organisation);

	}

	@Override
	public List<SysSettings> fetchallsettings(Integer id) {
		// TODO Auto-generated method stub

		return organisationDao.fetchallsetting(id);
	}

	@Override
	public void update(SysSettings syssettings) {
		// TODO Auto-generated method stub
		this.organisationDao.update(syssettings);
	}

	@Override
	public SysSettings getSettingsById(Integer id) {
		// TODO Auto-generated method stub
		return organisationDao.getSettingsById(id);

	}

	@Override
	public void update(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.organisationDao.update(organisation);
	}

	@Override
	public void save(OrganisationCompany organisation) {
		// TODO Auto-generated method stub
		this.organisationDao.update(organisation);
	}

}
