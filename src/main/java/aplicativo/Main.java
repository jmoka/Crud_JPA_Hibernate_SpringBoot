package aplicativo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import data.CrudPessoa;
import dominio.Pessoa;

public class Main {

	public static void main(String[] args) {
		
		

		// EntityManager ( conexao e acesso a dados)
		// EntityManagerFactory (intancia EntityManegr)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("percistence-jpa-teste");
		EntityManager em = emf.createEntityManager(); // instanciação , com conexão com o banco

		Pessoa p1 = new Pessoa(null, "jota", "jota@gmail.com");
		Pessoa p2 = new Pessoa(null, "ana", "ana@gmail.com");
		Pessoa p3 = new Pessoa(null, "anaLinda", "ana@gmail.com");

		Integer num = 4;

		// inserir
		if (num == 1) {

			em.getTransaction().begin();
			
			CrudPessoa.InsertPessoa(em, p3);

			em.getTransaction().commit();

		}

		// buscar
		if (num == 2) {
			em.getTransaction().begin();

			Pessoa rP = CrudPessoa.findByIDPessoa(em, 4);
			Pessoa rP1 = CrudPessoa.findByIDPessoa(em, 5);		

			System.out.println(rP);
			System.out.println(rP1);

			em.getTransaction().commit();

		}
		
		

		// remover
		if (num == 3) {
			em.getTransaction().begin();

			CrudPessoa.removePessoa(em, 3);

			em.getTransaction().commit();

		}
		
		
		//atualizar			
		if(num == 4) {
			em.getTransaction().begin();
			
			Pessoa novaP = CrudPessoa.updatePessoa(em, 4, "Manuela", "manu@g,mail.com");
			System.out.println(novaP);
			
			em.getTransaction().commit();
		}
		
		

		em.close();
		emf.close();
		System.out.println("Pronto Persistência Concluida");
	}

}
