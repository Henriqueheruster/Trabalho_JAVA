/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aula_23_02.Model;

import java.util.Objects;

/**
 *
 * @author Android
 */
public class Aluno {
    
    private String nome;
    private String ra;

    //sobrecarga de construtores
    public Aluno(String nome, String ra) {
        this.nome = nome;
        this.ra = ra;
    }

    public Aluno() {
    }
    
    
    //getters e setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }
    
    //métodos equals ( para comparar igualdade em métodos como contains
   // método hashcode ( para usar Sets
    // metodo to String ( para retornar a String equivalente ao objeto

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
         if(obj instanceof Aluno ){
            Aluno other = (Aluno) obj;
            if (!Objects.equals(this.nome, other.nome)) {
            return false;
            }
            return Objects.equals(this.ra, other.ra);
           
            
         }
         return false;
    }

    @Override
    public String toString() {
       return nome + "-"+ ra +"\n";// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
