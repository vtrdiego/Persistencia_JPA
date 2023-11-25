package controller;

import dao.JpaBootstrapAluno;
import dao.JpaBootstrapCurso;
import model.Aluno;
import model.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static dao.JpaBootstrapAluno.inserirAluno;
import static dao.JpaBootstrapCurso.inserirCurso;

public class Menu {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;

        while(rodando){
            //Menu + input o usuário
            System.out.println("[1] - Cadastrar curso");
            System.out.println("[2] - Cadastrar aluno");
            System.out.println("[3] - Consultar cursos de aluno");
            System.out.println("[4] - Listar alunos de um curso");
            System.out.println("[5] - Sair");
            System.out.println("\nDigite a opção:");
            String opcao = scanner.nextLine();

            //Processar o input do usuário
            switch(opcao){
                case "1": {

                    System.out.println("Digite o nome do curso:");
                    String nome_curso=scanner.nextLine();

                    System.out.println("Digite a carga horária:");
                    String carga=scanner.nextLine();
                    int carga_horaria=Integer.parseInt(carga);

                    Curso c=new Curso();
                    c.setNome(nome_curso);
                    c.setCarga_horaria(carga_horaria);


                    JpaBootstrapCurso daoCurso = new JpaBootstrapCurso();
                    daoCurso.inserirCurso(c);

                    System.out.println("\nCurso cadastrado com sucesso!\n");
                    break;
                }
                case "2": {

                    System.out.println("Digite o nome do aluno:");
                    String nome_aluno=scanner.nextLine();


                    System.out.println("Informe o código do curso do aluno: ");
                    String cod=scanner.nextLine();
                    Long codigo=Long.parseLong(cod);

                    Curso c = new Curso();
                    c.setCodigo(codigo);

                    List<Curso> cursos = new ArrayList();
                    cursos.add(c);

                    Aluno a =new Aluno();
                    a.setNome(nome_aluno);
                    a.setCursos(cursos);

                    JpaBootstrapAluno daoAluno = new JpaBootstrapAluno();
                    daoAluno.inserirAluno(a);

                    System.out.println("\nAluno cadastrado com sucesso!\n");
                    break;
                }
                case "3": {

                    System.out.println("\nDigite a matrícula do aluno para exibir cursos:");
                    String mat=scanner.nextLine();
                    Long matricula=Long.parseLong(mat);

                    JpaBootstrapAluno daoAluno = new JpaBootstrapAluno();
                    Aluno aluno = daoAluno.consultarAluno(matricula);
                    System.out.println("\n-- Cursos do aluno "+aluno.getNome()+" --");
                    for(Curso curso: aluno.getCursos()){
                        System.out.println("Código do curso: "+curso.getCodigo());
                        System.out.println("Nome do curso: "+curso.getNome());
                        System.out.println("Carga horaria: "+curso.getCarga_horaria()+"\n");
                    }
                    break;
                }
                case "4": {

                    System.out.println("\nDigite o código do curso para exibir alunos:");
                    String cod=scanner.nextLine();
                    Long codigo=Long.parseLong(cod);

                    JpaBootstrapCurso daoCurso = new JpaBootstrapCurso();
                    Curso curso = daoCurso.consultarCurso(codigo);
                    System.out.println("\n-- Alunos de "+curso.getNome()+" --");
                    for(Aluno aluno:curso.getAlunos()){
                        System.out.println("Matrícula do aluno: "+aluno.getMatricula());
                        System.out.println("Nome do aluno: "+aluno.getNome()+"\n");

                    }
                    break;
                }
                case "5": {
                    rodando= false;
                    System.out.println("\nPrograma encerrado!\n");
                    break;
                }
                default: {
                    break;
                }
            }


        }//while




    }
}


