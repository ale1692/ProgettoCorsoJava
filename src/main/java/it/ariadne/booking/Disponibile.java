package it.ariadne.booking;

import java.util.Date;
import java.util.Iterator;
import java.util.TreeMap;

import it.ariadne.resources.Resource;

public class Disponibile {

	TreeMap<Long, Booking> prenotazioni;

	public Disponibile(TreeMap<Long, Booking> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public int verificaDisponibilità(Resource risorsa, Date startPrenotazione) {

//		//codice 2: risosrsa libera quindi prenotabile
//		//codice 1: risorsa occupata
//		
//		for (Iterator<Long> iterator = prenotazioni.keySet().iterator(); iterator.hasNext();) {
//			long id = iterator.next();
//
//			if (prenotazioni.get(id).getRisorsa().getCodice() == (risorsa.getCodice())) {
//
//				if (prenotazioni.get(id).getEndRisorsa().before(startPrenotazione)
//						|| prenotazioni.get(id).getEndRisorsa().equals(startPrenotazione)) {
//					
//					return 2;
//
//				}
//
//				else {
//					return 1;
//				}
//
//			}
//
//			else {
//
//			}
//
//		}
		
		return 0;
	}

}
