package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


import model.Product;


public class ProductDao {

	private EntityManagerFactory entityManagerFactory;

	public void create(Product s) {

		entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		em.persist(s);

		em.getTransaction().commit();
	}

	public List<Product> findAll() {
		entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		List<Product> products = em.createQuery("select s from Product s", Product.class).getResultList();

		return products;
	}

	public Product findOne(String id) {
		entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();

		em.getTransaction().begin();

		Product s = em.find(Product.class, id);
		return s;
	}

	public void viewproducts() {

		List<Product> prod = this.findAll();
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		System.out.printf("%70s\n", "DETAILS OF PRODUCTS");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		for (Product p : prod) {

			System.out.printf("Product ID : %-30s Product Name : %-1s\n", String.valueOf(p.getId()), p.getName());
			System.out.printf("Product Desc: %-27s Product Price : %-1s\n", p.getDesc(), p.getPrice());
			System.out.println("Product Quality : " + p.getQuality());

			System.out.println(
					"----------------------------------------------------------------------------------------------------");
		}
	}

	public void searchproducts(String id) {

		Product p = this.findOne(id);
		System.out.println(
				"----------------------------------------------------------------------------------------------------");
		System.out.printf("%70s\n", "DETAILS OF PRODUCTS");
		System.out.println(
				"----------------------------------------------------------------------------------------------------");

		System.out.printf("Product ID : %-30s Product Name : %-1s\n", String.valueOf(p.getId()), p.getName());
		System.out.printf("Product Desc: %-27s Product Price : %-1s\n", p.getDesc(), p.getPrice());
		System.out.println("Product Quality : " + p.getQuality());

		System.out.println(
				"----------------------------------------------------------------------------------------------------");

	}

	public void delete(String id) {

		entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Product product = em.find(Product.class, id);
		; // this is a function that returns a task by id
		if (product == null) {
			return;
		}

		et.begin();
		em.remove(em.merge(product));
		et.commit();
		em.close();

//		em.getTransaction().begin();
//		
//		Product delete=this.findOne(id);
//		
//	    em.remove(delete);
//		
//		em.getTransaction().commit();
	}
	
	public void update(Product s,String id) {
		entityManagerFactory = HibernateUtil.getEntityManagerFactory();

		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		
	    em.setProperty(id, s);
		
		em.getTransaction().commit();
		
	}
	

}