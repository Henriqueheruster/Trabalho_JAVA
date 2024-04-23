/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aula_23_02.Controller;

import com.mycompany.aula_23_02.Model.Aluno;
import com.mycompany.aula_23_02.Model.DAOAluno;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Android
 */
public class AlunoController {
    
    private static DAOAluno dao = null;
    
    public AlunoController(){
        if(dao==null){
            dao = new DAOAluno();
        }
    }  
    public boolean salvar(String nome, String ra) throws IOException{
        Aluno aluno = new Aluno(nome,ra);
         return dao.cadastra(aluno);
        
    }
    public void carregaAlunos() throws IOException{
        dao.carregaAlunos();
    }
    //Gera o modelo de Modelo de dados para o Jtable
    public void geraTableModel(JTable tabelaAlunos){
        DefaultTableModel modelo = (DefaultTableModel) tabelaAlunos.getModel();
        Object []objetos = new Object[2];//2 é a quantidade de colunas
        //Obter Lista de alunos
        List<Aluno> alunos = dao.listaAlunos();
        //Para cada aluno da lista colocar nome e ra dentro do vetor de objetos
        //vetor objetos
        for(Aluno aluno:alunos){
            objetos[0] = aluno.getNome();
            objetos[1] = aluno.getRa();
            modelo.addRow(objetos);
        }
         
    }
   //---------------------------------- Buscar Aluno pelo nome ------------------------------     
    public void buscarAluno(String nome,JTable tabelaAlunos) throws IOException{
        List<Aluno> alunos = dao.listaAlunos();
        
        DefaultTableModel modelo = (DefaultTableModel) tabelaAlunos.getModel();
        
        boolean flag = false;
        for(Aluno aluno:alunos){
            if (aluno.getNome().equalsIgnoreCase(nome)){
                Object []objetos = new Object[2];
                objetos[0] = aluno.getNome();
                objetos[1] = aluno.getRa();
                modelo.addRow(objetos);
                flag= true;
            }
        }
        if(!flag){
            JOptionPane.showMessageDialog(null, "Aluno não encontrado");
        }
    }
  
    public Aluno buscarAlunoPeloNome(String nome) {
        List<Aluno> alunos = dao.listaAlunos();
        
        for (Aluno aluno : alunos) {
            if (aluno.getNome().equalsIgnoreCase(nome)) { 
                return aluno; 
            }
        }
        return null;
    }
    //-------------------- ------ Remover Aluno da tabela -------------------------
      
    public void removeAluno(Aluno aluno){
        dao.deletaAluno(aluno);
    }
    
    
///------------------------------- Buscar Aluno pelo RA ----------------------------------------    
    public void buscarAlunoPeloRa(String ra, JTable tabelaAlunos) throws IOException {
        if (ra.isEmpty()) {
            JOptionPane.showMessageDialog(null, "RA não pode estar vazio");
            return;
        }

        DefaultTableModel modelo = (DefaultTableModel) tabelaAlunos.getModel();
        modelo.setRowCount(0); // Limpa todas as linhas da tabela(Chat que me mostrou esse kkkk)

        List<Aluno> alunos = dao.listaAlunos();
        boolean flag = false;
        for (Aluno aluno : alunos) {
            //System.out.println("RA do aluno na lista: " + aluno.getRa());
            if (aluno.getRa().equals(ra)) {
                Object[] objetos = new Object[2];
                objetos[0] = aluno.getNome();
                objetos[1] = aluno.getRa();
                modelo.addRow(objetos);
                flag = true;
                break; // como ra é como um id, quando achar podemos dar um break
            }
        }
            if (!flag) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado");
            }
} 
    public Aluno buscarAlunoPeloRA(String ra) {
        List<Aluno> alunos = dao.listaAlunos();
        for (Aluno aluno : alunos) {
            if (aluno.getRa().equals(ra)) { 
                return aluno; 
            }
        }
        return null;
    }
    
    
    ///------------------------------- Edita o Aluno ----------------------------------------  
    
       public boolean editaAluno(Aluno antigo, Aluno novo) {
        try {
            return dao.editaAluno(antigo, novo);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
