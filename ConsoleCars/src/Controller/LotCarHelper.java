package Controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Model.LotCar;

public class LotCarHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleCars");
	
	public void insertCar(LotCar lc) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(lc);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<LotCar> showAllCars() {
		EntityManager em = emfactory.createEntityManager();
		List<LotCar> allCars = em.createQuery("SELECT i FROM LotCar i").getResultList();
		return allCars;
	}
	
	public void deleteCar(LotCar toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<LotCar> typedQuery = em.createQuery("select lc from LotCar lc where lc.make = :selectedMake and lc.model = :selectedModel and lc.year = :selectedYear", LotCar.class);
		
		typedQuery.setParameter("selectedMake", toDelete.getMake());
		typedQuery.setParameter("selectedModel", toDelete.getModel());
		typedQuery.setParameter("selectedYear", toDelete.getYear());
		
		typedQuery.setMaxResults(1);
		
		LotCar result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	public LotCar searchForCarById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		LotCar found = em.find(LotCar.class, idToEdit);
		em.close();
		return found;
	}

	public void updateCar(LotCar toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	public List<LotCar> searchForCarByMake(String makeName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<LotCar> typedQuery = em.createQuery("select lc from LotCar lc where lc.make = :selectedMake", LotCar.class);
		typedQuery.setParameter("selectedMake", makeName);
		
		List<LotCar> foundCar = typedQuery.getResultList();
		em.close();
		return foundCar;
		
	}

	public List<LotCar> searchForCarByModel(String modelName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<LotCar> typedQuery = em.createQuery("select lc from LotCar lc where lc.model = :selectedModel", LotCar.class);
		typedQuery.setParameter("selectedModel", modelName);
		
		List<LotCar> foundCar = typedQuery.getResultList();
		em.close();
		return foundCar;
	}

	public List<LotCar> searchForCarByYear(int yearName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<LotCar> typedQuery = em.createQuery("select lc from LotCar lc where lc.year = :selectedYear", LotCar.class);
		typedQuery.setParameter("selectedYear", yearName);
		
		List<LotCar> foundCar = typedQuery.getResultList();
		em.close();
		return foundCar;
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
