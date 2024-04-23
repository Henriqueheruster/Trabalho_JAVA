/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aula_23_02.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Android
 */
public class DAOAluno {
    private List<Aluno> alunos = new ArrayList<Aluno>();
    
    public boolean cadastra(Aluno novo) throws IOException{

        cadastraEmArq(novo);
        
        return alunos.add(novo);
        
    }
    
    public void cadastraEmArq(Aluno novo) throws IOException{
        FileWriter file = new FileWriter("alunos.txt", true);
        PrintWriter writer = new PrintWriter(file);
        writer.print(novo);
        writer.close();
        file.close();
        
    }
    
    public void carregaAlunos() throws IOException{
        FileReader file  = new FileReader("alunos.txt");
        Scanner leitor = new Scanner(file);
        String dados;
        String[] aux;
        String nome;
        String ra;
        
        // enquanto houver linhas no arquivo
        while(leitor.hasNext()){
            //leia os dados do aluno
            dados = leitor.nextLine();
            aux = dados.split("-");
            nome = aux[0];
            ra = aux[1]+"-"+aux[2];
            
            // montar  "Franke" ..alino
            Aluno aluno = new Aluno(nome,ra);
            //cadastrar no array
            alunos.add(aluno);
            
        }
        leitor.close();
        file.close();
           
    }
    
    public List<Aluno> listaAlunos(){
        return  alunos;
    }
    
    public List<Aluno> buscarAluno(){
        return alunos;
    }
    public List<Aluno> buscarAlunoRA(){
        return alunos;
    }
    
   
    
     public boolean editaAluno(Aluno antigo, Aluno novo) throws IOException {
         // pega o index e o nome antigo para ser alterado
    if (alunos.contains(antigo)) {
        int posicao = alunos.indexOf(antigo);
        alunos.set(posicao, novo); // Atualiza o aluno na lista
        
        // recria o arquivo 

        File file = new File("alunos.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Aluno a : alunos) {
                writer.write(a.getNome() + "-" + a.getRa() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    return false;
}
    
    public boolean deletaAluno(Aluno aluno){
       // Remover o aluno da lista em memória
        boolean deletar = alunos.remove(aluno);
        if (!deletar) {
            return false; // Se o aluno não foi encontrado na lista, retornar false
        }

        // refaz o arquivo com os nomes que sobraram
        File file = new File("alunos.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Aluno a : alunos) {
                writer.write(a.getNome() + "-" + a.getRa() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
    
 
        
    
    
    
}
