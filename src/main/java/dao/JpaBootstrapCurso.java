package dao;
import model.Aluno;
import model.Curso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaBootstrapCurso {

    public static EntityManager getEM() {
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("tdeDois");
        return emf2.createEntityManager();
    }

    public static Curso inserirCurso(Curso curso) {

        //chama o método ManagerFactory
        EntityManager em2 = getEM();

        try {

            em2.getTransaction().begin();

            if(curso.getCodigo() == null) {
                //insert
                em2.persist(curso);
            }else{
                //update
                curso=em2.merge(curso);
            }
            em2.getTransaction().commit();

        } catch (Exception e) {

            em2.getTransaction().rollback();
            e.printStackTrace();

        } finally {
            em2.close();
        }

        return curso;
    }

    public Curso consultarCurso(Long codigo){
        //chama o método ManagerFactory
        EntityManager em = getEM();

        Curso c=null;

        try{
            c=em.find(Curso.class, codigo);
        }finally{
            em.close();
        }
        return c;
    }


}
