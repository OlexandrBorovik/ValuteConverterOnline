package converterMoney;

import java.io.IOException;
import java.util.Scanner;

public class Beggin {

	private Converter c = new Converter();

	public void firsStep() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter valute:");

		String valuta = (sc.nextLine());
		System.out.println("Enter sum:");
		int summ = sc.nextInt();
		c.setSumm(summ);
		c.setV(valuta);
		try {
			c.request(valuta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
