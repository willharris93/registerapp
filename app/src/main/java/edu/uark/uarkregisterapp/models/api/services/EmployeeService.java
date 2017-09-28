package edu.uark.uarkregisterapp.models.api.services;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uark.uarkregisterapp.models.api.Employee;
import edu.uark.uarkregisterapp.models.api.EmployeeListing;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.ProductListing;
import edu.uark.uarkregisterapp.models.api.enums.ApiLevel;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeApiMethod;
import edu.uark.uarkregisterapp.models.api.enums.EmployeeApiRequestStatus;
import edu.uark.uarkregisterapp.models.api.enums.ProductApiMethod;
import edu.uark.uarkregisterapp.models.api.interfaces.PathElementInterface;

public class EmployeeService extends BaseRemoteService {
	public Employee getEmployee(UUID employeeId) {
		JSONObject rawJsonObject = this.requestSingle(
			(new PathElementInterface[] { EmployeeApiMethod.EMPLOYEE, ApiLevel.ONE }), employeeId
		);

		if (rawJsonObject != null) {
			return (new Employee()).loadFromJson(rawJsonObject);
		} else {
			return new Employee().setApiRequestStatus(EmployeeApiRequestStatus.UNKNOWN_ERROR);
		}
	}

	public List<Employee> getEmployees() {
		List<Employee> activities;
		JSONObject rawJsonObject = this.requestSingle(
				(new PathElementInterface[] { ProductApiMethod.PRODUCT, ApiLevel.ONE, ProductApiMethod.PRODUCTS })
		);

		if (rawJsonObject != null) {
			activities = (new EmployeeListing()).loadFromJson(rawJsonObject).getEmployees();
		} else {
			activities = new ArrayList<>(0);
		}

		return activities;
	}

	public Employee putEmployee(Employee employee) {
		JSONObject rawJsonObject = this.putSingle(
			(new PathElementInterface[]{ EmployeeApiMethod.EMPLOYEE, ApiLevel.ONE }), employee.convertToJson()
		);

		if (rawJsonObject != null) {
			return (new Employee()).loadFromJson(rawJsonObject);
		} else {
			return new Employee().setApiRequestStatus(EmployeeApiRequestStatus.UNKNOWN_ERROR);
		}
	}

}
