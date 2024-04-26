package be6_day11.utilities;

import java.util.ArrayList;

import be6_day11.dto.Users;

public class UtilityPrint {
	static public void printUserList(ArrayList<Users>userList) {

		String uName = "User Name";
		String phoneNum = "Phone Number";
		String DOB = "Date of Birth";
		int maxName = uName.length() + 2;
		int maxMobile = phoneNum.length() + 2;
		int maxDOB = DOB.length() + 2;
		
		for (Users object : userList) {
			if (object.name.length() + 2 > maxName) {
				maxName = object.name.length() + 2;
			}
			if (object.mobile.length() + 2 > maxMobile) {
				maxMobile = object.mobile.length() + 2;
			}
			if (object.date.length() + 2 > maxDOB) {
				maxDOB = object.date.length() + 2;
			}
		}
		
		int lengthofString = maxName + maxMobile + maxDOB + 1;
		// ++++++++++++
		for (int i = 0; i < lengthofString; i++) {
			System.out.printf("%-1s", "+");
		}
		System.out.print("\n");
		// first table row
		System.out.printf("%-" + maxName + "s" + "%-" + maxMobile + "s" + "%-" + maxDOB + "s" + "%-1s", "| " + uName,
				"| " + phoneNum, "| " + DOB, "|");
		System.out.print("\n");
		// ============
		for (int i = 0; i < lengthofString; i++) {
			System.out.printf("%-1s", "=");
		}
		System.out.print("\n");
		// object info rows
		if (userList != null) {
			for (int i = 0; i < userList.size() - 1; i++) {
				System.out.printf("%-" + maxName + "s" + "%-" + maxMobile + "s" + "%-" + maxDOB + "s" + "%-1s",
						"| " + userList.get(i).name, "| " + userList.get(i).mobile, "| " + userList.get(i).date, "|");
				System.out.print("\n");
				for (int j = 0; j < lengthofString; j++) {
					System.out.printf("%-1s", "=");

				}
				System.out.print("\n");
			}
		}
		// last object info row
		if(userList.size()>0) {
		int lastIndex = userList.size() - 1;
		System.out.printf("%-" + maxName + "s" + "%-" + maxMobile + "s" + "%-" + maxDOB + "s" + "%-1s",
				"| " + userList.get(lastIndex).name, "| " + userList.get(lastIndex).mobile,
				"| " + userList.get(lastIndex).date, "|");
		System.out.print("\n");
		// ================
		for (int j = 0; j < lengthofString; j++) {
			System.out.printf("%-1s", "+");

		}
		System.out.print("\n");
		}

	}
}
