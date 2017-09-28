package edu.uark.uarkregisterapp.models.api.enums;

import java.util.HashMap;
import java.util.Map;

import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public enum EmployeeApiMethod implements PathElementInterface {
	NONE(""),
	EMPLOYEE("employee"),
	BY_LOOKUP_CODE("byLookupCode"),
	EMPLOYEES("employees");

	@Override
	public String getPathValue() {
		return value;
	}

	public static EmployeeApiMethod map(String key) {
		if (valueMap == null) {
			valueMap = new HashMap<>();

			for (EmployeeApiMethod value : EmployeeApiMethod.values()) {
				valueMap.put(value.getPathValue(), value);
			}
		}

		return (valueMap.containsKey(key) ? valueMap.get(key) : EmployeeApiMethod.NONE);
	}

	private String value;

	private static Map<String, EmployeeApiMethod> valueMap = null;

	EmployeeApiMethod(String value) {
		this.value = value;
	}
}
