import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class Tutor{
   // Atributos
   private int codigo;
   private String nome;
   private String endereco;
   private LocalDate dataNasc;
   private ArrayList<Pet> pet=new ArrayList<Pet>();
   
   // Construtor
   public Tutor(int c, String n, String e, LocalDate d){
      codigo = c;
      nome = n;
      endereco = e;
      dataNasc = d;
   }
   
   // Getters
   public int getCodigo() {return codigo;}
   public String getNome() {return nome;}
   public String getEndereco() {return endereco;}
   
   public String getDataNasc() {
      DateTimeFormatter fmt=DateTimeFormatter.ofPattern("dd/MM/yyyy");
      return dataNasc.format(fmt);
   }
   public int getIdade(){
      LocalDate dataSistema=LocalDate.now();
      int idade=Period.between(dataNasc,dataSistema).getYears();
      return idade;
   }
   
   // Adiciona pet
   public void incluiPet(String nomePet, String tipoPet){
      pet.add(new Pet(nomePet,tipoPet, dataNasc));
   }
   
   // Geração de string com os dados do tutor   
   public String toString(){
      String ts = String.format("Cod. do tutor: %d\n", codigo);
      ts += String.format("  Nome...........: %s\n", nome);
      ts += "  Data nascimento: "+getDataNasc()+ " ("+ getIdade() +" anos)\n";
      ts += String.format("  Endereco.......: %s\n", endereco);
      ts += "  Relacao de pets:\n";
      if (pet.size() == 0)
         ts+="Nenhum pet cadastrado.\n";
      else{
         for(Pet p:pet){ 
            ts+= "  - Nome do pet: "+p.getNomePet()+"; ";
            ts+= "Tipo: "+p.getTipoPet()+".\n";
            }
      }
      return ts;
      }

   
}
