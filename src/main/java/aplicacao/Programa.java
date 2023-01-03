package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Programa {

	public static void main(String[] args) {

		//DADOS PARA SER INSERIDO NO BANCO DE DADOS
		//Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
		//Pessoa p2 = new Pessoa(null, "Joaquim Torres", "joaquim@gmail.com");
		//Pessoa p3 = new Pessoa(null, "Ana Maria", "ana@gmail.com");

		//CONEXÃO AO BANCO DE DADOS
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		//INSERINDO DADOS NO BANCO DE DADOS
		//em.getTransaction().begin();
		//em.persist(p1);
		//em.persist(p2);
		//em.persist(p3);
		//em.getTransaction().commit();
		
		//RECUPERANDO DADOS SALVOS NO BANCO DE DADOS
		
		//Pessoa p = em.find(Pessoa.class, 2);
		
		//System.out.println(p);
		
		//EXCLUINDO DADOS DO BANCO DE DADOS
		//SEMPRE QUE FOR FAZER UMA OPERAÇÃO QUE NAO SEJA SIMPLES CONSULTA, COLOCAR TRANSAÇÃO
		em.getTransaction().begin();
		Pessoa p = em.find(Pessoa.class, 2);
		em.remove(p);
		em.getTransaction().commit();
		System.out.println("Concluido");
		em.close();
		emf.close();
		//3306
		//http://localhost/dashboard/
	}

}
