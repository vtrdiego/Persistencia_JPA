package dao;
import model.Aluno;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaBootstrapAluno {

    public static EntityManager getEM() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("tdeDois");
        return emf.createEntityManager();
    }

    public static Aluno inserirAluno(Aluno aluno) {

        //chama o método ManagerFactory
        EntityManager em = getEM();

        try {

            em.getTransaction().begin();

            if(aluno.getMatricula()==null) {
                em.persist(aluno);
            }else{
                aluno=em.merge(aluno);
            }
            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            em.close();
        }

        return aluno;
    }

    public Aluno consultarAluno(Long matricula){
        //chama o método ManagerFactory
        EntityManager em = getEM();

        Aluno a=null;

        try{
            a=em.find(Aluno.class, matricula);
        }finally{
            em.close();
        }
        return a;
    }


}
