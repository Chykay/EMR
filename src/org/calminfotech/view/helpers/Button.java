package org.calminfotech.view.helpers;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.calminfotech.utils.AppConfig;

public class Button {

	private AppConfig appConfig;

	public static final String DEFAULT = "btn-default";
	public static final String PRIMARY = "btn-primary";
	public static final String WARNING = "btn-warning";
	public static final String DANGER = "btn-danger";
	public static final String SUCCESS = "btn-success";
	public static final String INFO = "btn-info";
	public static final String LINK = "btn-link";

	public static final String FONTAWESOME = "fa";
	public static final String GLYPHICONS = "glyphicon";

	public static final String SIZE_XS = "btn-xs";
	public static final String SIZE_LG = "btn-lg";
	public static final String SIZE_SM = "btn-sm";
	public static final String SIZE_DEFAULT = "";

	public static String renderLink(String linkType, String buttonSize,
			String url, String text) {
		String buttonStr = "<a href='" + constructUrl(url) + "' class='btn "
				+ buttonSize + " " + linkType + "'>";
		buttonStr += text;
		buttonStr += "</a>";
		return buttonStr;
	}

	/**
	 * Renders button with icon. Icon can either be font awesome or glyphicon
	 * 
	 * @param linkType
	 * @param url
	 * @param iconType
	 * @param iconName
	 * @return
	 */

	public static String renderLink(String linkType, String buttonSize,
			String url, String iconType, String iconName) {

		String buttonStr = "<a href='" + constructUrl(url) + "' class='btn "
				+ buttonSize + " " + linkType + "'>";
		buttonStr += "<i class='" + iconType + " " + iconName + "'></i>";
		buttonStr += "</a>";
		return buttonStr;
	}

	/**
	 * Renders button with icon. Icon can either be font awesome or glyphicon
	 * 
	 * @param linkType
	 * @param url
	 * @param iconType
	 * @param iconName
	 * @param title
	 * @return
	 */

	public static String renderLink(String linkType, String buttonSize,
			String url, String iconType, String iconName, String title) {

		String buttonStr = "<a href='" + constructUrl(url) + "' class='btn "
				+ buttonSize + " " + linkType + "' title='" + title + "'>";

		buttonStr += "<i class='" + iconType + " " + iconName + "'></i>";
		buttonStr += "</a>";
		return buttonStr;
	}

	/**
	 * Sanitize the url and removes the trial slash if exist
	 * 
	 * @param url
	 * @return
	 */

	private static String sanitizeUrl(String url) {
		int occurence = url.indexOf("/", 0);

		if (occurence == 0) {
			url = url.substring(1);
		}

		return url;
	}

	/**
	 * Abstract the construction of URL
	 * 
	 * @param url
	 * @return
	 */

	private static String constructUrl(String url) {
		return AppConfig.APP_URL + "/" + sanitizeUrl(url);
	}

	public static void main(String[] args) {
		sanitizeUrl("/admin/hmos");

		JSONArray array = new JSONArray();
		array.add("Newton Kyari");
		array.add(23);
		
		JSONObject obj = new JSONObject();
		obj.accumulate("sam", "Sample Value");
		obj.accumulate("aaData", array);

		System.out.println(obj.toString());
	}

}
