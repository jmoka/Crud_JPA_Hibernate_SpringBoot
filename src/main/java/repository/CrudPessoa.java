package repository;

import javax.persistence.EntityManager;

import dominio.Pessoa;

public class CrudPessoa {
	
	public static void InsertPessoa(EntityManager em, Pessoa p) {
		em.persist(p);		
	}

	public static Pessoa findByIDPessoa(EntityManager em, int id) {
		Pessoa p = em.find(Pessoa.class, id);
		return p;
	}
	
	public static void removePessoa(EntityManager em, int id) {
		Pessoa p = em.find(Pessoa.class, id);		
		em.remove(p);
	}
	
	public static Pessoa updatePessoa(EntityManager em, int id, String nome, String email) {
		Pessoa p = em.find(Pessoa.class, id);
		p.setNome(nome);
		p.setEmail(email);
		
		return p;
		
	}

	

}
