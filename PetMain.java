import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

class petMain {
    private static ArrayList<Tutor> tutores = new ArrayList<Tutor>(); // Declara tutores como lista de Tutor

    public static void main(String[] args) {
        // Declara variaveis de controle
        int cont = 1;
        char op = 'n';

        cont = popularCadastro(cont); // Chama a funcao para popular cadastro

        while (op != 'x') { // Criacao do loop para menu (x encerra)
            op = Menu();
            Scanner input = new Scanner(System.in);
            switch (op) {
                case 'c': // Caso opcao desejada for cadastrar tutor + pets
                    cont = cadastro(cont);
                    break;
                    
                case 'i': // Caso a opcao seja imprimir cadastro
                    imprimir();
                    break;
                    
                case 'b': // Caso a opcao seja buscar pets por codigo tutor
                    buscar();
                break;

                case 'e': // Caso a opcao seja excluir pets por codigo tutor
                    excluir();
                    break;

                case 'x': // Caso a opcao desejada seja encerar o programa
                    System.out.println("Programa encerrando");
                    break;
                    
                default: // Caso o usuario digite nenhuma das opcoes
                    System.out.println("Opcao invalida!!!");
                    break;
            }
        }
    }

    static char Menu() {
        System.out.println("*****ESCOLHER UMA OPCAO*****");
        System.out.println("c: cadastrar tutor + pet(s)");
        System.out.println("i: imprimir cadastro");
        System.out.println("b: buscar pets por codigo tutor");
        System.out.println("e: excluir pets por codigo tutor");
        System.out.println("x: encerrar.");
        System.out.println("Opcao escolhida:");
        Scanner sop = new Scanner(System.in); // Scanner do menu
        char op = sop.next().charAt(0);
        return op;
    }

    private static void clearBuffer(Scanner scanner) {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    // Popular cadastro com pelo menos tres tutores e seus pets
    public static int popularCadastro(int cont) { // Metodo para popular cadastro
        Tutor t; // Objeto tutor.

        // int codTut = geraCodTut(); // Cria codigo sequencial inicando em 1.
        t = new Tutor(cont, "Josefina das Gracas", "Rua  Jeronimo Luiz, 123", LocalDate.of(1968, 9, 13)); // Tutor 1
        t.incluiPet("Filomena", "Cachorro");
        tutores.add(t);

        Tutor t2;

        t2 = new Tutor(cont + 1, "Bernardo Ribeirinho", "Rua Presidente Patrick, 456", LocalDate.of(1990, 12, 4)); // Tutor 2
        t2.incluiPet("Assobio", "Passaro");
        tutores.add(t2);

        Tutor t3;

        t3 = new Tutor(cont + 1, "Vasco da Gama", "Avenida Roberto Dinamite, 10", LocalDate.of(1921, 8, 21)); // Tutor 2
        t3.incluiPet("Vasquinho", "cachorro");
        tutores.add(t3);


        return cont + 3;
    }

    private static boolean dataveri(int ano, int mes, int dia) { // Verificador de datas digitadas pelo usuario
        if (ano < 1900) { // Ano digitado menor que 1900
            return false;
        }

        if (mes > 12) { // Mes digitado inexistente
            return false;
        }

        if ((mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) && (dia > 31)) { // Meses que possuem 31 dias
            return false;
        }
        if (ano % 4 == 0 && mes == 2 && dia > 29) { // Ano bissexto
            return false;
        }
        if (ano % 4 != 0 && mes == 2 && dia > 28) { // Verificador fevereiro
            return false;
        }

        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) { // Meses que possuem 30 dias
            return false;
        }
        return true;

    }

    private static int cadastro(int cont) { // Metodo para cadastro
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("Digite nome do tutor (vazio encerra cadastro tutor): ");
            String nome_tutor = input.nextLine();
            if (nome_tutor == "") { // Vazio para encerar loop
                break;
            }
            String[] data_com = {"ano", "mes", "dia"};
            boolean veridata = false;
            while (!veridata) {
                // Recebendo os valores
                System.out.println("Digite dia (dd), mes (mm) e ano (aaaa) de nascimento do tutor:  \n (separados por espacos)");
                String data_nasc = input.nextLine();
                data_com = data_nasc.split(" "); // Split transforma o data_nasc em uma lista, separando por espaço
                veridata = dataveri(Integer.parseInt(data_com[2]), Integer.parseInt(data_com[1]), Integer.parseInt(data_com[0])); // Variavel para guardar data de nascimento de acordo com a posicao
                if (!veridata) { // Verificador para datas invalidas
                    System.out.println("Dava invalida");
                }
            }
            LocalDate data = LocalDate.of(Integer.parseInt(data_com[2]), Integer.parseInt(data_com[1]), Integer.parseInt(data_com[0]));
            System.out.println("Digite endereco do tutor/pet: ");
            String endereco = input.nextLine();
            Tutor tutor = new Tutor(cont, nome_tutor, endereco, data); // Criacao do tutor
            while (true) {
                System.out.println("Digite o nome do pet(vazio encerra cadastro pet: ");
                String nomePet = input.nextLine();
                if (nomePet == "") { // Vazio para encerar loop
                    break;
                }
                System.out.println("Digite o tipo de pet: ");
                String tipoPet = input.nextLine();
                tutor.incluiPet(nomePet, tipoPet); // Criacao do pet
            }
                tutores.add(tutor); // Adiciona o novo tutor a lista
                cont++;
        }
        return cont;
    }
    private static void imprimir(){ // Metodo para imprimir
        for (Tutor ttl : tutores) { // ttl eh um iterador
            System.out.println(ttl.toString()); // Imprime os cadastros
        }
    }
    private static void buscar(){
        Scanner input = new Scanner(System.in);
        System.out.println("Digite codigo do tutor a ser localizado: ");
        int tutor_num = input.nextInt();
        clearBuffer(input);
        boolean verificador = false; // Booleano verificador para controle do loop
        for (Tutor tt : tutores) {
            if (tt.getCodigo() == tutor_num) {
                System.out.println(tt.toString());
                verificador = true; // Atualiza boleano
                break;
            }

        }
        if (verificador == false) { // Se o codigo digitado for diferente do codigo tutor
            System.out.println("codigo nao localizado");
        }
        
    }
    
   public static void excluir(){ // Metodo para excluir
   Scanner input = new Scanner(System.in);
   System.out.println("Digite o codigo do tutor a ser excluido: ");
        int tutor_num_del = input.nextInt(); clearBuffer(input);
        boolean verificadel = false; // Booleano verificador para controle do loop
        for (Tutor ttd: tutores){ // Percorre a lista Tutor
            if (tutor_num_del == ttd.getCodigo()){ // Numero digitado = indice tutor
                tutores.remove(tutores.indexOf(ttd)); // Remove tutor
                System.out.println("Tutor codigo " + tutor_num_del + " excluido");
                verificadel = true; // Atualiza boleano
                break;
            }
        }
        if (verificadel == false){
            System.out.println("esse Codigo nao existe");
        }
   } 
}