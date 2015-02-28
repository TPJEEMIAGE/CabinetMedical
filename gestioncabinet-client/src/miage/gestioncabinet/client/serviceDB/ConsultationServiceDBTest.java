/**
 * 
 */
package miage.gestioncabinet.client.serviceDB;

import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Produit;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

/**
 * @author Moe1
 *
 */
public class ConsultationServiceDBTest {
	
	private static ConsultationRemoteService ejb;

	/**
	 * Constructeur avec lookup pour récupérer le proxy de l'EJB
	 * @see java.lang.Object#Object()
	 */
	@BeforeClass
	public static void initialization() {
		String service = "ejb:gestioncabinet/gestioncabinet-coreDB//ConsultationServiceDB!miage.gestioncabinet.api.ConsultationRemoteService?stateful";
		
		try{
			ServiceLocator locator = ServiceLocator.INSTANCE;
			ConsultationServiceDBTest.ejb = (ConsultationRemoteService) locator.getRemoteInterface(service);
		}
		catch(ServiceLocatorException e){
			System.out.println("Le service "+service+" est introuvable");
			e.printStackTrace();
		}
	}

	@Test
	public void creerRdv() {
		try{
			Calendar date = Calendar.getInstance();
			Consultation consultation = ConsultationServiceDBTest.ejb.creerRdv(date);
			Assert.assertTrue(date.equals(consultation.getDebut()));
		}catch(Exception e){
			e.printStackTrace();
			System.err.println(this + " n'a pas réussi à créer un rdv");
		}
		
	}

	
	public void rechercherMedicament(String keyword)
			throws GestionCabinetException {
		//TODO Not implemented yet
	}

	public void analyserPrescription() {
		//TODO Not implemented yet
		
	}

	public void enregistrer() {
	}

	public void supprimer() {
		// TODO Auto-generated method stub
		
	}
	
}
