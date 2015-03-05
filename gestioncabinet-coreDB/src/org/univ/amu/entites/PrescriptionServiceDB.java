package org.univ.amu.entites;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;

import org.univ.amu.ApplicationService;
import org.univ.amu.PrescriptionService;

@Stateless
@LocalBean
public class PrescriptionServiceDB extends PrescriptionService{

	@EJB
	private ApplicationService appli;
	
	@Override
	public ApplicationService getApplicationService() {
		return appli;
	}

	@Override
	public Produit getProduitInstance() {
		return new ProduitDB();
	}

	@Override
	public Interaction getInteractionInstance() {
		return new InteractionDB();
	}

}
