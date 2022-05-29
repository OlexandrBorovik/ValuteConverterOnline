package converterMoney;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Converter {

	final String usd = "https://minfin.com.ua/currency/usd/";
	final String euro = "https://minfin.com.ua/currency/eur/";
	final String pln = "https://minfin.com.ua/currency/pln/";
	private int index = 0;
	private double buy;
	private double sell;
	private int summ;
	private String v;

	public Converter(int index, double buy, double sell, int summ, String v) {
		super();
		this.index = index;
		this.buy = buy;
		this.sell = sell;
		this.summ = summ;
		this.v = v;
	}

	public Converter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getBuy() {
		return buy;
	}

	public void setBuy(double buy) {
		this.buy = buy;
	}

	public double getSell() {
		return sell;
	}

	public void setSell(double sell) {
		this.sell = sell;
	}

	public int getSumm() {
		return summ;
	}

	public void setSumm(int summ) {
		this.summ = summ;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public String getUsd() {
		return usd;
	}

	public String getEuro() {
		return euro;
	}

	public String getPln() {
		return pln;
	}

	public void request(String valuta) throws IOException {
		if (valuta.equals("usd")) {
			this.getFromURL("https://minfin.com.ua/currency/usd/");
		}
		if (valuta.equals("euro")) {
			this.getFromURL(euro);
		}
		if (valuta.equals("pln")) {
			this.getFromURL(pln);
		}

		else {
			System.out.println("Not available valute.");

		}
	}

	public void getFromURL(String name) throws IOException {

		URL url = new URL(name);
		String temp = "";
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {

			for (;;) {

				temp = br.readLine();
				if (temp == null) {
					break;
				}
				String[] array = temp.split("\"");

				for (int i = 0; i < array.length; i++) {
					if (array[i] != null && array[i].equalsIgnoreCase("mfm-posr") && index != 2) {
						String[] cost = array[2].split("\\>");
						double cost1 = Double.parseDouble(cost[1]);
						index++;
						if (index == 1) {
							buy = cost1;
						} else {
							sell = cost1;
						}

					}
				}

			}
			double s = summ * sell;
			String se = String.format("%.2f",s);
			double b = summ * buy;
			String bu = String.format("%.2f",b);
			
			
			System.out.println("Buy: " + buy + " Sell: " + sell);
			System.out.println("Buy " + summ + " " + v + " cost " + se + " UAH.");
			System.out.println("Sell " + summ + " " + v + " cost " + bu + "  UAH.");

		}
	}

	@Override
	public String toString() {
		return "Converter [usd=" + usd + ", euro=" + euro + ", pln=" + pln + ", index=" + index + ", buy=" + buy
				+ ", sell=" + sell + ", summ=" + summ + ", v=" + v + "]";
	}

}
