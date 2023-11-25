package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="alunos")
public class Aluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matricula;
    private String nome;

    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name ="alunos_cursos",
            joinColumns = @JoinColumn(name="curso_fk"),
            inverseJoinColumns = @JoinColumn(name="aluno_fk"))
    private List<Curso> cursos;

    public Long getMatricula() {
        return matricula;
    }
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Curso> getCursos( ) {
        return cursos;
    }
    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }


}
