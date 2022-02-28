package util;

import java.util.ArrayList;
import java.util.List;

public class Calendario {

	private List<String> lunedi = new ArrayList<String>();
	private List<String> martedi = new ArrayList<String>();
	private List<String> mercoledi = new ArrayList<String>();
	private List<String> giovedi = new ArrayList<String>();
	private List<String> venerdi = new ArrayList<String>();
	private List<String> sabato = new ArrayList<String>();
	private List<String> domenica = new ArrayList<String>();
	
	public Calendario() {
		// TODO Auto-generated constructor stub
		Mese Gennaio = new Mese("gennaio", 31, 6);
		Mese Febbraio = new Mese("febbraio", 28, 2);
		Mese Marzo = new Mese ("marzo", 31, 2);
		Mese Aprile = new Mese("aprile", 30, 5);
		Mese Maggio = new Mese("maggio", 31, 7);
		Mese Giugno = new Mese("giugno", 30, 3);
		Mese Luglio = new Mese("luglio", 31, 5);
		Mese Agosto = new Mese("agosto", 31, 1);
		Mese Settembre = new Mese("settembre", 30, 4);
		Mese Ottobre = new Mese("ottobre", 31, 6);
		Mese Novembre = new Mese("novembre", 30, 2);
		//Mese Dicembre = new Mese("dicembre", 1, 4);
		
		
		//LUNEDI
		for(int i=0; i<Gennaio.getLunedi().size(); i++) {
			if(Gennaio.getLunedi().get(i) >= 10)
				lunedi.add(String.valueOf(Gennaio.getLunedi().get(i)) + "-01-2022");
			else
				lunedi.add("0" + String.valueOf(Gennaio.getLunedi().get(i)) + "-01-2022");
		}
		for(int i=0; i<Febbraio.getLunedi().size(); i++) {
			if(Febbraio.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Febbraio.getLunedi().get(i)) + "-02-2022");
			else
				lunedi.add("0" + String.valueOf(Febbraio.getLunedi().get(i)) + "-02-2022");
		}
		for(int i=0; i<Marzo.getLunedi().size(); i++) {
			if(Marzo.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Marzo.getLunedi().get(i)) + "-03-2022");
			else
				lunedi.add("0" + String.valueOf(Marzo.getLunedi().get(i)) + "-03-2022");
		}
		for(int i=0; i<Aprile.getLunedi().size(); i++) {
			if(Aprile.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Aprile.getLunedi().get(i)) + "-04-2022");
			else
				lunedi.add("0" + String.valueOf(Aprile.getLunedi().get(i)) + "-04-2022");
		}
		for(int i=0; i<Maggio.getLunedi().size(); i++) {
			if(Maggio.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Maggio.getLunedi().get(i)) + "-05-2022");
			else
				lunedi.add("0" + String.valueOf(Maggio.getLunedi().get(i)) + "-05-2022");
		}
		for(int i=0; i<Giugno.getLunedi().size(); i++) {
			if(Giugno.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Giugno.getLunedi().get(i)) + "-06-2022");
			else
				lunedi.add("0" + String.valueOf(Giugno.getLunedi().get(i)) + "-06-2022");
		}

		for(int i=0; i<Luglio.getLunedi().size(); i++) {
			if(Luglio.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Luglio.getLunedi().get(i)) + "-07-2022");
			else
				lunedi.add("0" + String.valueOf(Luglio.getLunedi().get(i)) + "-07-2022");
		}
		for(int i=0; i<Agosto.getLunedi().size(); i++) {
			if(Agosto.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Agosto.getLunedi().get(i)) + "-08-2022");
			else
				lunedi.add("0" + String.valueOf(Agosto.getLunedi().get(i)) + "-08-2022");
		}
		for(int i=0; i<Settembre.getLunedi().size(); i++) {
			if(Settembre.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Settembre.getLunedi().get(i)) + "-09-2022");
			else
				lunedi.add("0" + String.valueOf(Settembre.getLunedi().get(i)) + "-09-2022");
		}
		for(int i=0; i<Ottobre.getLunedi().size(); i++) {
			if(Ottobre.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Ottobre.getLunedi().get(i)) + "-10-2022");
			else
				lunedi.add("0" + String.valueOf(Ottobre.getLunedi().get(i)) + "-10-2022");
		}
		for(int i=0; i<Novembre.getLunedi().size(); i++) {
			if(Novembre.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Novembre.getLunedi().get(i)) + "-11-2022");
			else
				lunedi.add("0" + String.valueOf(Novembre.getLunedi().get(i)) + "-11-2022");
		}
		/*
		for(int i=0; i<Dicembre.getLunedi().size(); i++) {
			if(Dicembre.getLunedi().get(i)>=10)
				lunedi.add(String.valueOf(Dicembre.getLunedi().get(i)) + "-12-2022");
			else
				lunedi.add("0" + String.valueOf(Dicembre.getLunedi().get(i)) + "-12-2022");
		}
		*/
		
		
		//MARTEDI
		
				for(int i=0; i<Gennaio.getMartedi().size(); i++) {
					if(Gennaio.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Gennaio.getMartedi().get(i)) + "-01-2022");
					else
						martedi.add("0" + String.valueOf(Gennaio.getMartedi().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getMartedi().size(); i++) {
					if(Febbraio.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Febbraio.getMartedi().get(i)) + "-02-2022");
					else
						martedi.add("0" + String.valueOf(Febbraio.getMartedi().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getMartedi().size(); i++) {
					if(Marzo.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Marzo.getMartedi().get(i)) + "-03-2022");
					else
						martedi.add("0" + String.valueOf(Marzo.getMartedi().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getMartedi().size(); i++) {
					if(Aprile.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Aprile.getMartedi().get(i)) + "-04-2022");
					else
						martedi.add("0" + String.valueOf(Aprile.getMartedi().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getMartedi().size(); i++) {
					if(Maggio.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Maggio.getMartedi().get(i)) + "-05-2022");
					else
						martedi.add("0" + String.valueOf(Maggio.getMartedi().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getMartedi().size(); i++) {
					if(Giugno.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Giugno.getMartedi().get(i)) + "-06-2022");
					else
						martedi.add("0" + String.valueOf(Giugno.getMartedi().get(i)) + "-06-2022");
				}

				for(int i=0; i<Luglio.getMartedi().size(); i++) {
					if(Luglio.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Luglio.getMartedi().get(i)) + "-07-2022");
					else
						martedi.add("0" + String.valueOf(Luglio.getMartedi().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getMartedi().size(); i++) {
					if(Agosto.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Agosto.getMartedi().get(i)) + "-08-2022");
					else
						martedi.add("0" + String.valueOf(Agosto.getMartedi().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getMartedi().size(); i++) {
					if(Settembre.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Settembre.getMartedi().get(i)) + "-09-2022");
					else
						martedi.add("0" + String.valueOf(Settembre.getMartedi().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getMartedi().size(); i++) {
					if(Ottobre.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Ottobre.getMartedi().get(i)) + "-10-2022");
					else
						martedi.add("0" + String.valueOf(Ottobre.getMartedi().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getMartedi().size(); i++) {
					if(Novembre.getMartedi().get(i) >= 10)
						martedi.add(String.valueOf(Novembre.getMartedi().get(i)) + "-11-2022");
					else
						martedi.add("0" + String.valueOf(Novembre.getMartedi().get(i)) + "-11-2022");
				}
				/*
				for(int i=0; i<Dicembre.getMartedi().size(); i++) {
					martedi.add(String.valueOf(Dicembre.getMartedi().get(i)) + " Dicembre");
				}
				*/
				
				
				//MERCOLEDI
				for(int i=0; i<Gennaio.getMercoledi().size(); i++) {
					if(Gennaio.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Gennaio.getMercoledi().get(i)) + "-01-2022");
					else
						mercoledi.add("0" + String.valueOf(Gennaio.getMercoledi().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getMercoledi().size(); i++) {
					if(Febbraio.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Febbraio.getMercoledi().get(i)) + "-02-2022");
					else
						mercoledi.add("0" + String.valueOf(Febbraio.getMercoledi().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getMercoledi().size(); i++) {
					if(Marzo.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Marzo.getMercoledi().get(i)) + "-03-2022");
					else
						mercoledi.add("0" + String.valueOf(Marzo.getMercoledi().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getMercoledi().size(); i++) {
					if(Aprile.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Aprile.getMercoledi().get(i)) + "-04-2022");
					else
						mercoledi.add("0" + String.valueOf(Aprile.getMercoledi().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getMercoledi().size(); i++) {
					if(Maggio.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Maggio.getMercoledi().get(i)) + "-05-2022");
					else
						mercoledi.add("0" + String.valueOf(Maggio.getMercoledi().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getMercoledi().size(); i++) {
					if(Giugno.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Giugno.getMercoledi().get(i)) + "-06-2022");
					else
						mercoledi.add("0" + String.valueOf(Giugno.getMercoledi().get(i)) + "-06-2022");
				}
				for(int i=0; i<Luglio.getMercoledi().size(); i++) {
					if(Luglio.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Luglio.getMercoledi().get(i)) + "-07-2022");
					else
						mercoledi.add("0" + String.valueOf(Luglio.getMercoledi().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getMercoledi().size(); i++) {
					if(Agosto.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Agosto.getMercoledi().get(i)) + "-08-2022");
					else
						mercoledi.add("0" + String.valueOf(Agosto.getMercoledi().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getMercoledi().size(); i++) {
					if(Settembre.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Settembre.getMercoledi().get(i)) + "-09-2022");
					else
						mercoledi.add("0" + String.valueOf(Settembre.getMercoledi().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getMercoledi().size(); i++) {
					if(Ottobre.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Ottobre.getMercoledi().get(i)) + "-10-2022");
					else
						mercoledi.add("0" + String.valueOf(Ottobre.getMercoledi().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getMercoledi().size(); i++) {
					if(Novembre.getMercoledi().get(i) >= 10)
						mercoledi.add(String.valueOf(Novembre.getMercoledi().get(i)) + "-11-2022");
					else
						mercoledi.add("0" + String.valueOf(Novembre.getMercoledi().get(i)) + "-11-2022");
				}
				/*
				for(int i=0; i<Dicembre.getMercoledi().size(); i++) {
					mercoledi.add(String.valueOf(Dicembre.getMercoledi().get(i)) + " dicembre");
				}
				*/
				
				
				
				//GIOVEDI
				for(int i=0; i<Gennaio.getGiovedi().size(); i++) {
					if(Gennaio.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Gennaio.getGiovedi().get(i)) + "-01-2022");
					else
						giovedi.add("0" + String.valueOf(Gennaio.getGiovedi().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getGiovedi().size(); i++) {
					if(Febbraio.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Febbraio.getGiovedi().get(i)) + "-02-2022");
					else
						giovedi.add("0" + String.valueOf(Febbraio.getGiovedi().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getGiovedi().size(); i++) {
					if(Marzo.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Marzo.getGiovedi().get(i)) + "-03-2022");
					else
						giovedi.add("0" + String.valueOf(Marzo.getGiovedi().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getGiovedi().size(); i++) {
					if(Aprile.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Aprile.getGiovedi().get(i)) + "-04-2022");
					else
						giovedi.add("0" + String.valueOf(Aprile.getGiovedi().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getGiovedi().size(); i++) {
					if(Maggio.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Maggio.getGiovedi().get(i)) + "-05-2022");
					else
						giovedi.add("0" + String.valueOf(Maggio.getGiovedi().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getGiovedi().size(); i++) {
					if(Giugno.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Giugno.getGiovedi().get(i)) + "-06-2022");
					else
						giovedi.add("0" + String.valueOf(Giugno.getGiovedi().get(i)) + "-06-2022");
				}
				for(int i=0; i<Luglio.getGiovedi().size(); i++) {
					if(Luglio.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Luglio.getGiovedi().get(i)) + "-07-2022");
					else
						giovedi.add("0" + String.valueOf(Luglio.getGiovedi().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getGiovedi().size(); i++) {
					if(Agosto.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Agosto.getGiovedi().get(i)) + "-08-2022");
					else
						giovedi.add("0" + String.valueOf(Agosto.getGiovedi().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getGiovedi().size(); i++) {
					if(Settembre.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Settembre.getGiovedi().get(i)) + "-09-2022");
					else
						giovedi.add("0" + String.valueOf(Settembre.getGiovedi().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getGiovedi().size(); i++) {
					if(Ottobre.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Ottobre.getGiovedi().get(i)) + "-10-2022");
					else
						giovedi.add("0" + String.valueOf(Ottobre.getGiovedi().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getGiovedi().size(); i++) {
					if(Novembre.getGiovedi().get(i) >= 10)
						giovedi.add(String.valueOf(Novembre.getGiovedi().get(i)) + "-11-2022");
					else
						giovedi.add("0" + String.valueOf(Novembre.getGiovedi().get(i)) + "-11-2022");
				}
				
				/*
				for(int i=0; i<Dicembre.getGiovedi().size(); i++) {
					giovedi.add(String.valueOf(Dicembre.getGiovedi().get(i)) + " dicembre");
				}
				*/
				
				
				
				//Venerdi
				for(int i=0; i<Gennaio.getVenerdi().size(); i++) {
					if(Gennaio.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Gennaio.getVenerdi().get(i)) + "-01-2022");
					else
						venerdi.add("0" + String.valueOf(Gennaio.getVenerdi().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getVenerdi().size(); i++) {
					if(Febbraio.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Febbraio.getVenerdi().get(i)) + "-02-2022");
					else
						venerdi.add("0" + String.valueOf(Febbraio.getVenerdi().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getVenerdi().size(); i++) {
					if(Marzo.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Marzo.getVenerdi().get(i)) + "-03-2022");
					else
						venerdi.add("0" + String.valueOf(Marzo.getVenerdi().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getVenerdi().size(); i++) {
					if(Aprile.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Aprile.getVenerdi().get(i)) + "-04-2022");
					else
						venerdi.add("0" + String.valueOf(Aprile.getVenerdi().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getVenerdi().size(); i++) {
					if(Maggio.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Maggio.getVenerdi().get(i)) + "-05-2022");
					else
						venerdi.add("0" + String.valueOf(Maggio.getVenerdi().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getVenerdi().size(); i++) {
					if(Giugno.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Giugno.getVenerdi().get(i)) + "-06-2022");
					else
						venerdi.add("0" + String.valueOf(Giugno.getVenerdi().get(i)) + "-06-2022");
				}

				for(int i=0; i<Luglio.getVenerdi().size(); i++) {
					if(Luglio.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Luglio.getVenerdi().get(i)) + "-07-2022");
					else
						venerdi.add("0" + String.valueOf(Luglio.getVenerdi().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getVenerdi().size(); i++) {
					if(Agosto.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Agosto.getVenerdi().get(i)) + "-08-2022");
					else
						venerdi.add("0" + String.valueOf(Agosto.getVenerdi().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getVenerdi().size(); i++) {
					if(Settembre.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Settembre.getVenerdi().get(i)) + "-09-2022");
					else
						venerdi.add("0" + String.valueOf(Settembre.getVenerdi().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getVenerdi().size(); i++) {
					if(Ottobre.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Ottobre.getVenerdi().get(i)) + "-10-2022");
					else
						venerdi.add("0" + String.valueOf(Ottobre.getVenerdi().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getVenerdi().size(); i++) {
					if(Novembre.getVenerdi().get(i) >= 10)
						venerdi.add(String.valueOf(Novembre.getVenerdi().get(i)) + "-11-2022");
					else
						venerdi.add("0" + String.valueOf(Novembre.getVenerdi().get(i)) + "-11-2022");
				}
				/*
				for(int i=0; i<Dicembre.getVenerdi().size(); i++) {
					venerdi.add(String.valueOf(Dicembre.getVenerdi().get(i)) + " Dicembre");
				}
				*/
				
				
				//sabato
				for(int i=0; i<Gennaio.getSabato().size(); i++) {
					if(Gennaio.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Gennaio.getSabato().get(i)) + "-01-2022");
					else
						sabato.add("0" + String.valueOf(Gennaio.getSabato().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getSabato().size(); i++) {
					if(Febbraio.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Febbraio.getSabato().get(i)) + "-02-2022");
					else
						sabato.add("0" + String.valueOf(Febbraio.getSabato().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getSabato().size(); i++) {
					if(Marzo.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Marzo.getSabato().get(i)) + "-03-2022");
					else
						sabato.add("0" + String.valueOf(Marzo.getSabato().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getSabato().size(); i++) {
					if(Aprile.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Aprile.getSabato().get(i)) + "-04-2022");
					else
						sabato.add("0" + String.valueOf(Aprile.getSabato().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getSabato().size(); i++) {
					if(Maggio.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Maggio.getSabato().get(i)) + "-05-2022");
					else
						sabato.add("0" + String.valueOf(Maggio.getSabato().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getSabato().size(); i++) {
					if(Giugno.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Giugno.getSabato().get(i)) + "-06-2022");
					else
						sabato.add("0" + String.valueOf(Giugno.getSabato().get(i)) + "-06-2022");
				}

				for(int i=0; i<Luglio.getSabato().size(); i++) {
					if(Luglio.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Luglio.getSabato().get(i)) + "-07-2022");
					else
						sabato.add("0" + String.valueOf(Luglio.getSabato().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getSabato().size(); i++) {
					if(Agosto.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Agosto.getSabato().get(i)) + "-08-2022");
					else
						sabato.add("0" + String.valueOf(Agosto.getSabato().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getSabato().size(); i++) {
					if(Settembre.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Settembre.getSabato().get(i)) + "-09-2022");
					else
						sabato.add("0" + String.valueOf(Settembre.getSabato().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getSabato().size(); i++) {
					if(Ottobre.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Ottobre.getSabato().get(i)) + "-10-2022");
					else
						sabato.add("0" + String.valueOf(Ottobre.getSabato().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getSabato().size(); i++) {
					if(Novembre.getSabato().get(i) >= 10)
						sabato.add(String.valueOf(Novembre.getSabato().get(i)) + "-11-2022");
					else
						sabato.add("0" + String.valueOf(Novembre.getSabato().get(i)) + "-11-2022");
				}
				/*
				for(int i=0; i<Dicembre.getSabato().size(); i++) {
					sabato.add(String.valueOf(Dicembre.getSabato().get(i)) + " Dicembre");
				}
				*/
				
				
				//domenica
				for(int i=0; i<Gennaio.getDomenica().size(); i++) {
					if(Gennaio.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Gennaio.getDomenica().get(i)) + "-01-2022");
					else
						domenica.add("0" + String.valueOf(Gennaio.getDomenica().get(i)) + "-01-2022");
				}
				for(int i=0; i<Febbraio.getDomenica().size(); i++) {
					if(Febbraio.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Febbraio.getDomenica().get(i)) + "-02-2022");
					else
						domenica.add("0" + String.valueOf(Febbraio.getDomenica().get(i)) + "-02-2022");
				}
				for(int i=0; i<Marzo.getDomenica().size(); i++) {
					if(Marzo.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Marzo.getDomenica().get(i)) + "-03-2022");
					else
						domenica.add("0" + String.valueOf(Marzo.getDomenica().get(i)) + "-03-2022");
				}
				for(int i=0; i<Aprile.getDomenica().size(); i++) {
					if(Aprile.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Aprile.getDomenica().get(i)) + "-04-2022");
					else
						domenica.add("0" + String.valueOf(Aprile.getDomenica().get(i)) + "-04-2022");
				}
				for(int i=0; i<Maggio.getDomenica().size(); i++) {
					if(Maggio.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Maggio.getDomenica().get(i)) + "-05-2022");
					else
						domenica.add("0" + String.valueOf(Maggio.getDomenica().get(i)) + "-05-2022");
				}
				for(int i=0; i<Giugno.getDomenica().size(); i++) {
					if(Giugno.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Giugno.getDomenica().get(i)) + "-06-2022");
					else
						domenica.add("0" + String.valueOf(Giugno.getDomenica().get(i)) + "-06-2022");
				}

				for(int i=0; i<Luglio.getDomenica().size(); i++) {
					if(Luglio.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Luglio.getDomenica().get(i)) + "-07-2022");
					else
						domenica.add("0" + String.valueOf(Luglio.getDomenica().get(i)) + "-07-2022");
				}
				for(int i=0; i<Agosto.getDomenica().size(); i++) {
					if(Agosto.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Agosto.getDomenica().get(i)) + "-08-2022");
					else
						domenica.add("0" + String.valueOf(Agosto.getDomenica().get(i)) + "-08-2022");
				}
				for(int i=0; i<Settembre.getDomenica().size(); i++) {
					if(Settembre.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Settembre.getDomenica().get(i)) + "-09-2022");
					else
						domenica.add("0" + String.valueOf(Settembre.getDomenica().get(i)) + "-09-2022");
				}
				for(int i=0; i<Ottobre.getDomenica().size(); i++) {
					if(Ottobre.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Ottobre.getDomenica().get(i)) + "-10-2022");
					else
						domenica.add("0" + String.valueOf(Ottobre.getDomenica().get(i)) + "-10-2022");
				}
				for(int i=0; i<Novembre.getDomenica().size(); i++) {
					if(Novembre.getDomenica().get(i) >= 10)
						domenica.add(String.valueOf(Novembre.getDomenica().get(i)) + "-11-2022");
					else
						domenica.add("0" + String.valueOf(Novembre.getDomenica().get(i)) + "-11-2022");
				}
				/*
				for(int i=0; i<Dicembre.getDomenica().size(); i++) {
					domenica.add(String.valueOf(Dicembre.getDomenica().get(i)) + " Dicembre");
				}
				*/
				
				
				
		
		
		
	}

	public List<String> getLunedi() {
		return lunedi;
	}

	public void setLunedi(List<String> lunedi) {
		this.lunedi = lunedi;
	}

	public List<String> getMartedi() {
		return martedi;
	}

	public void setMartedi(List<String> martedi) {
		this.martedi = martedi;
	}

	public List<String> getMercoledi() {
		return mercoledi;
	}

	public void setMercoledi(List<String> mercoledi) {
		this.mercoledi = mercoledi;
	}

	public List<String> getGiovedi() {
		return giovedi;
	}

	public void setGiovedi(List<String> giovedi) {
		this.giovedi = giovedi;
	}

	public List<String> getVenerdi() {
		return venerdi;
	}

	public void setVenerdi(List<String> venerdi) {
		this.venerdi = venerdi;
	}

	public List<String> getSabato() {
		return sabato;
	}

	public void setSabato(List<String> sabato) {
		this.sabato = sabato;
	}

	public List<String> getDomenica() {
		return domenica;
	}

	public void setDomenica(List<String> domenica) {
		this.domenica = domenica;
	}
	
	public static void main(String[] args) {
		Calendario c = new Calendario();
		System.out.println(c.getLunedi().get(0));
		for(int i=0; i<c.getLunedi().size(); i++)
			System.out.println(c.getLunedi().get(i));
	}
	

}