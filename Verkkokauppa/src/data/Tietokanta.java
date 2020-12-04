package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Tietokanta {

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet resultSet = null;

	// Yhteyden tiedot
	private static String db = "trtkp20a3";
	private static String url = "jdbc:mysql://shell.hamk.fi/" + db + "?useSSL=false";
	private static String username = "trtkp20a3";
	private static String password = "trtkp20a3passwd";

	// Yhteys tietokantaan
	private static void yhdistaTietokanta() {
		try {
			connection = DriverManager.getConnection(url, username, password);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private static void suljeYhteys(ResultSet result, Statement statement, Connection connection) {
		if (result != null)
			try {
				resultSet.close();
			} catch (SQLException ignore) {
			}
		if (statement != null)
			try {
				statement.close();
			} catch (SQLException ignore) {
			}
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException ignore) {
		}
	}
	//************************************************************
	// tulostaa kaikki tuotteet tietokannasta ja kaikki niiden tiedot
	// Yll�pito
	public static void haeTuotteet() {



		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten

		int id;
		String nimi;
		String kuvaus;
		double hinta;

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String querySelect = "SELECT * FROM ryhma1_tuotteet";

			// Suorita kysely
			resultSet = statement.executeQuery(querySelect);

			// Vastauksen kÃ¤sittely
			System.out.println("tuoteID\t\tTuote\t\tHinta €\t\tKuvaus");

			while (resultSet.next()) {
				id = resultSet.getInt("tuoteID");
				nimi = resultSet.getString("nimi");
				kuvaus = resultSet.getString("kuvaus");
				hinta = resultSet.getDouble("hinta");

				System.out.println(id + "\t\t" + nimi + "\t\t" + hinta + "\t\t" + kuvaus);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
	}
	//************************************************************
	// hakee tieokannasta tuotteen nimen ja kuvauksen tuotenro:n perusteella
	// Asiakas
	public static void naytaTuotteenKuvaus(int tuotenro) {

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten

		int id;
		String nimi;
		String kuvaus;
		double hinta;

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String querySelect = "SELECT nimi, kuvaus FROM ryhma1_tuotteet where tuoteID = " + tuotenro + "";

			// Suorita kysely
			resultSet = statement.executeQuery(querySelect);

			// Vastauksen kÃ¤sittely
			System.out.println("Tuote\tKuvaus");

			while (resultSet.next()) {
	
				nimi = resultSet.getString("nimi");
				kuvaus = resultSet.getString("kuvaus");
				
				System.out.println(nimi + "\t\t" + kuvaus);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/*Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
	}
	//************************************************************
	// hakee tietokannasta tuotteen tuotenro:n, nimen ja hinnan
	// Asiakas
	public static void naytaTuotelista() {

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten

		int id;
		String nimi;
		String kuvaus;
		double hinta;

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String querySelect = "SELECT tuoteID, nimi, hinta FROM ryhma1_tuotteet";

			// Suorita kysely
			resultSet = statement.executeQuery(querySelect);

			// Vastauksen kÃ¤sittely
			System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println("Tuotenro\t\tTuote\t\tHinta");
			

			while (resultSet.next()) {
				id = resultSet.getInt("tuoteID");
				nimi = resultSet.getString("nimi");
				hinta = resultSet.getDouble("hinta");

				System.out.println(id + "\t\t" +nimi + "\t\t" + hinta);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------");
	}
	//************************************************************
	// hakee tuotteen nimen ja hinnan tuotenumeron perusteella
	// Asiakas
	public static String naytaTuotteenTiedot(int tuotenro) {

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten
		int id;
		String nimi;
		String kuvaus;
		double hinta;
		String palautus = "";

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String querySelect = "SELECT tuoteID, nimi, hinta FROM ryhma1_tuotteet where tuoteID =" + tuotenro;

			// Suorita kysely
			resultSet = statement.executeQuery(querySelect);

			// Vastauksen kÃ¤sittely

			while (resultSet.next()) {
		
				id = resultSet.getInt("tuoteID");
				nimi = resultSet.getString("nimi");
				hinta = resultSet.getDouble("hinta");
				palautus = (id + ", " + nimi + ", " + hinta);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
		return palautus;
	}
	//************************************************************
	public static void muutaHintaa(double h, int tuotenro) {

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten
		int id = tuotenro;
		String nimi;
		String kuvaus;
		double hinta = h;

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();
			String queryInsert = "UPDATE ryhma1_tuotteet SET hinta = " + h + " where ryhma1_tuotteet.tuoteID = "
					+ tuotenro + "";

			// Suorita kysely
			statement.executeUpdate(queryInsert);

		
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
	}
	
	//************************************************************
	public static void lisaaUusiTuote(String n, String k, double h) {

		// Ota yhteys tietokantaan
		yhdistaTietokanta();

		// Tietokannan taulun kentÃ¤t tulostusta varten

		String nimi = n;
		String kuvaus = k;
		double hinta = h;

		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String queryInsert = "INSERT INTO ryhma1_tuotteet (nimi, kuvaus, hinta) VALUES ('" + nimi + "','" + kuvaus
					+ "'," + hinta + ")";
			
			// Suorita kysely
			statement.executeUpdate(queryInsert);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
				}*/
		}
	}
	
	public static int lisaaTilaus(String enimi, String snimi, String sposti, String puh, String ost) {
		yhdistaTietokanta();
		
		int tilausID = 0;
		
		try {

			// Luo MySQL-kysely
			statement = connection.createStatement();

			String queryInsert = "INSERT INTO ryhma1_tilaus (etunimi, sukunimi, sahkoposti, puhelin, osoite)"
					+ "VALUES ('"+enimi+"','"+snimi+"','"+sposti+"','"+puh+"','"+ost+"')";

			// Suorita kysely
			statement.execute(queryInsert, Statement.RETURN_GENERATED_KEYS);
			resultSet = statement.getGeneratedKeys();
			if(resultSet.next()) {
				tilausID = resultSet.getInt(1);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			suljeYhteys(resultSet, statement, connection);
			
			/* Sulje yhteys ja nollaa kyselyt
			if (resultSet != null)
				try {
					resultSet.close();
				} catch (SQLException ignore) {
					
				}
			if (statement != null)
				try {
					statement.close();
				} catch (SQLException ignore) {
					
				}
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException ignore) {
					
				}*/
		}
		
		return tilausID;
	}
	
	public static void lisaaTuoteTilaukseen(int tilausID, int tuoteID, int kpl) {
		yhdistaTietokanta();
			
			try {

				// Luo MySQL-kysely
				statement = connection.createStatement();

				String queryInsert = "INSERT INTO ryhma1_tilauksen_tuote (tilausID, tuoteID, kpl)"
						+ "VALUES ("+tilausID+","+tuoteID+","+kpl+")"; 

				// Suorita kysely
				statement.executeUpdate(queryInsert);

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				suljeYhteys(resultSet, statement, connection);
				
				/* Sulje yhteys ja nollaa kyselyt
				if (resultSet != null)
					try {
						resultSet.close();
					} catch (SQLException ignore) {
						
					}
				if (statement != null)
					try {
						statement.close();
					} catch (SQLException ignore) {
						
					}
				if (connection != null)
					try {
						connection.close();
					} catch (SQLException ignore) {
						
					}*/
			}
	}
}
