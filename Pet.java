//import java.util.ArrayList;

import java.time.LocalDate;
import java.time.Period;

public class Pet{ //Cria a classe Pet
    //Atributos
    private String nomePet;
    private String tipoPet;
    private LocalDate nascPet;
    //Construtor
    public Pet(String nomePet, String tipoPet, LocalDate nascPet){
       this.nomePet = nomePet;
       this.tipoPet = tipoPet;
       this.nascPet = nascPet;
    }

    public String getNomePet(){ //Recebe o nome do Pet
       return nomePet;
    }
    public String getTipoPet(){ //Recebe o tipo do Pet
       return tipoPet;
    }
    public int getIdade(){ // Idade do pet
       LocalDate hoje=LocalDate.now();
       Period tempo=Period.between(nascPet,hoje);
       return tempo.getYears();
    }
 }