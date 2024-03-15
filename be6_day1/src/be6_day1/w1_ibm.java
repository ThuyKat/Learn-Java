package be6_day1;

public class w1_ibm {
	 public static void main(String[] args) {
		//Variables height and weight
		
		double h = 1900.0;
		double w = 5500.0;
		
		// bmi formula
		double bmi = (w/100)/Math.pow(h/1000,2);
		// Rounding rule: +1 to the second decimal if the third decimal is in (1,9]
		// This rule is achieved by rounding bmi to the third integer and add the result to 0.009
		double bmi_3d = Double.parseDouble(String.format("%.3f", bmi)) ;
		// Remove the third decimal of bmi_3d 
		double bmi_2d = Math.floor(bmi_3d * 100)/100;
		// check the results
		System.out.println(bmi);
		System.out.println(bmi_3d);
		System.out.println(bmi_2d);
		// Check bmi number and print out the message
		if (bmi_2d < 18.5) {System.out.println("Hoi gay, can boi duong them");
		} else if(bmi_2d >= 18.5 && bmi_2d <24.5) {System.out.println("Dep ko can chinh");
		} else if(bmi_2d >=24.5 && bmi_2d <29.5) {System.out.println("Hoi beo,can tap the duc");
		} else if(bmi_2d >=29.5 && bmi_2d <34.5) {System.out.println("Qua beo, an it tap the duc them");		
		} else {System.out.println("Beo phi, can di bac si");}

	}
		
	}
// push to git


