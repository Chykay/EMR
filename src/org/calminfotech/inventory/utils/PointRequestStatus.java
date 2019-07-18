package org.calminfotech.inventory.utils;

public enum PointRequestStatus {

	Pending('P'), Approved('A'),Issued('I'),Processing('R');

	private final char code;

	private PointRequestStatus(char code) {
		this.code = code;
	}

	public static PointRequestStatus fromCode(char code) {
		if (code == 'P' || code == 'p') {
			return Pending;
		}else if (code == 'A' || code == 'a') {
			return Approved;
		}else if (code == 'I' || code == 'i') {
			return Issued;
		}else if (code == 'R' || code == 'r') {
			return Processing;
		}
		return null;
	}

	public char getCode() {
		return code;
	}

	public char getId() {
		return this.getCode();
	}

	public String getName() {
		return this.name();
	}

}
