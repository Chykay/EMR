package org.calminfotech.utils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.calminfotech.system.boInterface.AuditBo;
import org.calminfotech.system.models.Audit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Auditor {

	/**
	 * 
	 * First method to be called when using auditor. It stores a snap shot of
	 * the form values before the POST Method is called on the form
	 * 
	 * @author Newton
	 * 
	 * */
	@Autowired
	private AuditBo auditBo;

	public void before(HttpServletRequest request, String formName,
			Object oldObject) {

		HttpSession session = request.getSession();

		Map<String, Object> convertedObject = this.convertObject(oldObject,
				request.getRequestURL().toString());

		System.out.println(formName + " - Before");
		
		// Save the mapped methods and their values to a session
		session.setAttribute(formName, convertedObject);
		
	}

	public void after(HttpServletRequest request, String formName,
			Object newObject, String username,Integer recordid) {

		System.out.println(formName + " - After");
		
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		// Ensure the formName exist. Don't blow your codes!!!!
		if (session.getAttribute(formName) != null) {

			map = (Map<String, Object>) session.getAttribute(formName);

			Method[] methods = newObject.getClass().getMethods();

			for (Method method : methods) { // Iterate through the methods in
											// the
											// object to get the getters only

				if (isGetter(method)) {
					try {
						// Check if the value for a method saved in the map
						// object not equal to the current value in the method.
						// so that I can save it...

						if (!map.get(method.getName())
								.toString()
								.equals(method.invoke(newObject, null)
										.toString())) {

							System.out.println(method.getName() + "[oldvalue: "
									+ map.get(method.getName())
									+ ", newValue: "
									+ method.invoke(newObject, null) + "]");
							System.out.print("invokebefore");
	Audit auditObj = new Audit((String) map.get("action-Url"), formName, method.getName().substring(3),
			map.get(method.getName()).toString(),method.invoke(newObject, null).toString(),username,recordid);
		/*
							Audit auditObj= new Audit();
		
							auditObj.setActionUrl((String) map.get("action-Url"));
							auditObj.setFormName(formName);
							auditObj.setFormName(formName);
							auditObj.setFieldName(method.getName().substring(3));
							auditObj.setOldValue(map.get(method.getName()).toString());
							auditObj.setNewValue(method.invoke(newObject, null).toString());
							auditObj.setUpdatedBy(username);
							auditObj.setRecordId(recordid);
							auditObj.setUpdatedDate(new Date(System.currentTimeMillis()));
							auditObj.setCreatedDate(new Date(System.currentTimeMillis()));
			*/				
							
	                  	System.out.print("invokeafter");
							System.out.println(auditObj.toString());

							auditBo.save(auditObj);
							
							System.out.println("saved audit" + auditObj.getRecordId() + " " + auditObj.getOldValue() );
							

						}

					} catch (Exception e) {
						System.out.println("audit error: " + e.getMessage());
					}
				}
			}

			session.setAttribute(formName, null);
		} else {
			try {
				throw new Exception(
						"Invalid Form Name: The form name is different from the first on stored");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Err: " + e.getMessage());
			}
		}
	}

	/**
	 * Map all the getters in the object to their values
	 * 
	 * 
	 * @param oldObject
	 * @return Map<getter, value>
	 */

	public Map<String, Object> convertObject(Object oldObject, String actionUrl) {

		// Use a key value mapping to hold the method name and value.
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("action-Url", actionUrl);

		Method[] methods = oldObject.getClass().getMethods();

		for (Method method : methods) { // Iterate through the methods in the
										// object to get the getters only

			if (isGetter(method)) {
				try {
					// Put the method name and the value in the map
					// The second parameter should theoretically hold any kind
					// of object
					map.put(method.getName(), method.invoke(oldObject, null));

					// System print to test
					// System.out.println("Getter Method Value: "
					// + method.invoke(oldObject, null).toString());

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}

		return map;
	}

	// Check the methods to filter out other methods.
	private static boolean isGetter(Method method) {

		// If method is a getter
		if (!method.getName().startsWith("get"))
			return false;

		// if it is the getClass method which is a standard
		if (method.getName().endsWith("Class")) {
			return false;
		}

		// Check if the method does not take any parameter
		if (method.getParameterTypes().length != 0)
			return false;

		// Make sure it returns a value
		if (void.class.equals(method.getReturnType()))
			return false;

		return true;
	}

	private static boolean isSetter(Method method) {

		// If the method is a setter
		if (!method.getName().startsWith("set"))
			return false;

		// If the Method takes a parameter7
		if (method.getParameterTypes().length != 1)
			return false;

		return true;
	}
}
