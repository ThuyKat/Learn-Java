package be6_day12.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import be6_day12.entities.User;

public class UserService extends Service<User> {
// logInUser
//updateUserFile
//getUserInput

	public ArrayList<User> getAll() {
		ArrayList<User> datas = null;
		try {
			datas = new ArrayList<User>();
			File myObj = new File("resource/user.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] splitDatas = data.split(",");
				User x = new User(splitDatas[0].trim(), splitDatas[1].trim(), splitDatas[2].trim(), Integer.parseInt(splitDatas[3].trim()),
						Integer.parseInt(splitDatas[4].trim()));
				datas.add(x);
			}
			myReader.close();
			return datas;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return datas;

	}

	public User getUserByUserIdAndPassword(String userId, String userPassword) {
		ArrayList<User> users = getAll();
		for (User user : users) {
			if (user.ID.equals(userId) && user.password.equals(userPassword)) {
				return user;
			}
		}
		return null;
	}

	public User logInUser() {
		// USER INPUT USERID AND PASSWORD
		User user = null;
		while (user == null) {
			Scanner scanInput = new Scanner(System.in);
			System.out.println("UserID : ");
			String idInput = scanInput.nextLine();
			System.out.println("Password: ");
			String passInput = scanInput.nextLine();
			user = getUserByUserIdAndPassword(idInput, passInput);
			if (user == null) {
				System.out.println("Invalid username or password!Try again");
			}
		}
		return user;
	}

	public void updateUserFile(User currentU) {
		ArrayList<User> userList = getAll();
		for (User u : userList) {
			if (u.ID.equals(currentU.ID)) {
				u.point = currentU.point;
				break;
			}
		}
		FileWriter writer; // moi lan write file thi write tu dau, cac du lieu cua file trc do deu bi
		// delete het
		try {
			writer = new FileWriter("resource/user.txt");
			for (User user : userList) {
				writer.write(user.ID + "," + user.password + "," + user.name + "," + user.shopID + "," + user.point
						+ System.lineSeparator());
			}
			writer.close();
			System.out.println("Point is updated");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int GetUserInput() {
		int option;
		while (true) {
			System.out.println("Enter your selection: ");
			Scanner scanOption = new Scanner(System.in);
			if (scanOption.hasNextInt()) {
//				if(scanOption.nextInt() != 0) {
				option = scanOption.nextInt();
				if (option > 6 || option < 1) {
					System.out.println("Invalid Input.Please try again");

				} else {
					break;
				}

			} else {
				System.out.println("Invalid Input.Please try again");
				scanOption.next();
			}
		}
		return option;
	}

	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
