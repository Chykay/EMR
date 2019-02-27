package org.calminfotech.system.forms;

import java.math.BigInteger;

public class DataTableForm {

	// Information for DataTables to use for rendering.
	private Integer sEcho;

	// Start returning rows from this point
	private Integer iDisplayStart;

	// Total amount of rows to be displayed
	private Integer iDisplayLength;

	// Column being sorted on (you will need to decode this number for your
	// database)
	private Integer iSortCol_0;

	// Direction to be sorted - "desc" or "asc".
	private String sSortDir_0;

	// Global search field
	private String sSearch;

	// Number of columns being displayed (useful for getting individual column
	// search info)
	private String iColumns;

	/**
	 * @return the sEcho
	 */
	public Integer getsEcho() {
		return sEcho;
	}

	/**
	 * @param sEcho
	 *            the sEcho to set
	 */
	public void setsEcho(Integer sEcho) {
		this.sEcho = sEcho;
	}

	/**
	 * @return the iDisplayStart
	 */
	public Integer getiDisplayStart() {
		return iDisplayStart;
	}

	/**
	 * @param iDisplayStart
	 *            the iDisplayStart to set
	 */
	public void setiDisplayStart(Integer iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}

	/**
	 * @return the iDisplayLength
	 */
	public Integer getiDisplayLength() {
		return iDisplayLength;
	}

	/**
	 * @param iDisplayLength
	 *            the iDisplayLength to set
	 */
	public void setiDisplayLength(Integer iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}

	/**
	 * @return the iSortCol_0
	 */
	public Integer getiSortCol_0() {
		return iSortCol_0;
	}

	/**
	 * @param iSortCol_0
	 *            the iSortCol_0 to set
	 */
	public void setiSortCol_0(Integer iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}

	/**
	 * @return the sSortDir_0
	 */
	public String getsSortDir_0() {
		return sSortDir_0;
	}

	/**
	 * @param sSortDir_0
	 *            the sSortDir_0 to set
	 */
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}

	/**
	 * @return the sSearch
	 */
	public String getsSearch() {
		return sSearch;
	}

	/**
	 * @param sSearch
	 *            the sSearch to set
	 */
	public void setsSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	/**
	 * @return the iColumns
	 */
	public String getiColumns() {
		return iColumns;
	}

	/**
	 * @param iColumns
	 *            the iColumns to set
	 */
	public void setiColumns(String iColumns) {
		this.iColumns = iColumns;
	}

}
