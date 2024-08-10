import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Aplicacao {

    private static ArrayList<Filmes> filmesLista = new ArrayList<>();
    private static ArrayList<String> locais = new ArrayList<>();
    private static Scanner entradaUsuario = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha;

        do {

            System.out.println("\n++++++ [ BEM VINDOS AO CINE-PUCPR ] ++++++");
            System.out.println("[0] - ENCERRAR                       ");
            System.out.println("[1] - ADICIONAR FILME E SESSÃO     ");
            System.out.println("[2] - BUSCAR FILME            ");
            System.out.println("[3] - LISTAR TODOS OS FILMES     ");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
            System.out.print("DIGITE A OPÇÃO QUE DESEJA: ");

            escolha = entradaUsuario.nextInt();
            entradaUsuario.nextLine();

            switch (escolha){
                case 1:
                    adicionarFilme();
                    break;
                case 2:
                    buscarFilme();
                    break;
                case 3:
                    listarFilme();
                    break;
                default:
                    System.out.println("TCHAU TCHAU...");
            }

        } while (escolha != 0);
        entradaUsuario.close();
    }

    public static void adicionarFilme() {
        String nome, comentarioFilme = "", comentarioSessao = "", localSessao, horarioString;
        int nota, dia, mes, ano, hora, minuto;
        double preco;
        boolean favorito = false;
        char resp;

        System.out.print("\n\n[ INCLUIR FILME ]\n");
        System.out.print("Nome: ");
        nome = entradaUsuario.nextLine();

        System.out.println("\n[ NOTA DO FILME ]");
        System.out.println("[1] - Muito ruim (*)");
        System.out.println("[2] - Ruim (**)");
        System.out.println("[3] - Regular (***)");
        System.out.println("[4] - Bom (***)");
        System.out.println("[5] - Muito bom (*****)");
        System.out.print("NOTA: ");
        nota = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        System.out.println("\n[ FAVORITO ]");
        System.out.print("É um dos favoritos (S/N)? ");
        resp = entradaUsuario.nextLine().charAt(0);
        if (resp =='S' || resp == 's'){
            favorito = true;
        }

        System.out.println("\n[ COMENTÁRIOS DO FILME E SESSÃO ]");
        System.out.print("Deseja complementar com um comentário (S/N)? ");
        resp = entradaUsuario.nextLine().charAt(0);
        if (resp == 'S' || resp == 's') {
            System.out.print("Comentário do Filme: ");
            comentarioFilme = entradaUsuario.nextLine();
            System.out.print("Comentário da Sessão: ");
            comentarioSessao = entradaUsuario.nextLine();
        }

        System.out.println("\n[ DATA DA SESSÃO ]");
        System.out.print("Data (dd/MM/aaaa): ");
        String dataString  = entradaUsuario.nextLine();
        String dataSplit[] = dataString.split("/");
        dia = Integer.valueOf(dataSplit[0]);
        mes = Integer.valueOf(dataSplit[1]);
        ano = Integer.valueOf(dataSplit[2]);

        System.out.println("\n[ HORÁRIO DA SESSÃO ]");
        System.out.print("Horário (hh:mm): ");
        horarioString = entradaUsuario.nextLine();
        String[] horarioSplit = horarioString.split(":");
        hora = Integer.valueOf(horarioSplit[0]);
        minuto = Integer.valueOf(horarioSplit[1]);

        System.out.println("\n[ PREÇO DA SESSÃO ]");
        System.out.print("Preço (R$): ");
        preco = entradaUsuario.nextDouble();
        entradaUsuario.nextLine();

        System.out.println("\n[ ESCOLHA O LOCAL DA SESSAO ]");
        System.out.println("========== [ LOCAIS ] ==========");
        int i = 1;
        for (String local : locais) {
            System.out.println("[" + i + "] " + local);
            i++;
        }
        System.out.println("\n\n[0] - Inserir um novo local");
        System.out.println("================================");

        System.out.print("OPÇÃO: ");
        int opcaoLocal = entradaUsuario.nextInt();
        entradaUsuario.nextLine();
        if (opcaoLocal == 0) {
            System.out.println("\n[ INSERIR UM NOVO LOCAL ]");
            System.out.print("Novo local: ");
            localSessao = entradaUsuario.nextLine();
            locais.add(localSessao);
        } else {
            if (opcaoLocal >= 1 && opcaoLocal <= locais.size()) {
                localSessao = locais.get(opcaoLocal - 1);
            } else {
                System.out.println("Opção inválida!");
                localSessao = locais.get(0);
            }
        }

        LocalDateTime dataHoraSessaoDT = LocalDateTime.of(ano, mes, dia, hora, minuto);
        Filmes filme = new Filmes(nome, nota, favorito, comentarioFilme, comentarioSessao,preco, dataHoraSessaoDT,  localSessao);
        filmesLista.add(filme);
        System.out.println("\nO filme ("+ nome + ") foi cadastrado!");
    }

    public static Filmes buscarFilme(){
        System.out.println("\n[ PROCURAR ]");
        System.out.print("Insira o nome do Filme: ");
        String nomeFilme = entradaUsuario.nextLine();
        boolean encontrado = false;
        for (Filmes filme : filmesLista){
            if (filme.getNomeFilme().equalsIgnoreCase(nomeFilme)){
                System.out.println("\nFilme encontrado: (" + filme.getNomeFilme() + ")");
                System.out.print("\nFilme correto (S/N) ? ");
                String resposta = entradaUsuario.nextLine();
                if (resposta.toUpperCase().charAt(0) == 'S') {
                    System.out.println("\n================================");
                    System.out.println("[ DADOS DO FILME ]");
                    System.out.println("Nome: " + filme.getNomeFilme());
                    System.out.print("Nota: ");
                    int nota = filme.getNotaFilme();
                    switch (nota) {
                        case 1:
                            System.out.println("*");
                            break;
                        case 2:
                            System.out.println("**");
                            break;
                        case 3:
                            System.out.println("***");
                            break;
                        case 4:
                            System.out.println("****");
                            break;
                        case 5:
                            System.out.println("*****");
                            break;
                        default:
                            System.out.println("Nota inválida!");
                    }
                    System.out.println("Favorito: " + (filme.getFavorito() ? "Sim" : "Não"));
                    LocalDateTime dataHoraSessao = filme.getSessao();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy, EEE - HH'h'mm");
                    String dataFormatada = dataHoraSessao.format(formatter);
                    System.out.println("Data e Hora: " + dataFormatada);
                    System.out.println("Preço: R$" + String.format("%.2f", filme.getPreco()));
                    System.out.println("Local: " + filme.getLocal());
                    System.out.println("Comentário do Filme: " + filme.getComentarioFilme());
                    System.out.println("Comentário da Sessão: " + filme.getComentarioSessao());
                    System.out.println("================================");

                    System.out.print("\nDeseja editar as informações do Filme (S/N)? ");
                    resposta = entradaUsuario.nextLine();
                    if (resposta.toUpperCase().charAt(0) == 'S') {
                        editarFilme(filme);
                    }
                    encontrado = true;
                    break;
                }
            }
        } if (!encontrado)
            System.out.println("Filme não encontrado!");
        return null;
    }

    public static void editarFilme(Filmes filme) {
        System.out.println("\n======= [ EDITAR FILME ] =======");
        System.out.println("SELECIONE UMA OPÇÃO: ");
        System.out.println("[1] - Editar a Nota");
        System.out.println("[2] - Editar comentário do Filme");
        System.out.println("[3] - Editar comentário da Sessão");
        System.out.println("================================");
        System.out.print("OPÇÃO: ");
        int opcao = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        switch (opcao) {
            case 1:
                System.out.println("\n[ INSIRA UMA NOVA NOTA DO FILME ]");
                System.out.print("Nova nota do Filme: ");
                int novaNota = entradaUsuario.nextInt();
                entradaUsuario.nextLine();
                filme.setNotaFilme(novaNota);
                System.out.println("Nota do Filme atualizada com sucesso!");
                break;
            case 2:
                System.out.println("\n[ INSIRA UM NOVO COMENTÁRIO DO FILME ]");
                System.out.print("Novo comentário do Filme: ");
                String novoComentarioFilme = entradaUsuario.nextLine();
                filme.setComentarioFilme(novoComentarioFilme);
                System.out.println("Comentário do Filme atualizado com sucesso!");
                break;
            case 3:
                System.out.println("\n[ INSIRA UM NOVO COMENTÁRIO DA SESSÃO ]");
                System.out.print("Novo comentário da Sessão: ");
                String novoComentarioSessao = entradaUsuario.nextLine();
                filme.setComentarioSessao(novoComentarioSessao);
                System.out.println("Comentário da Sessão atualizado com sucesso!");
                break;
            default:
                System.out.println("Opção inválida!");
        }

    }

    public static void listarFilme() {
        System.out.println("\n\n======= LISTAR FILMES =======");
        System.out.println("Escolha uma ordem de listagem: ");
        System.out.println("[1] - A a Z");
        System.out.println("[2] - Z a A");
        System.out.println("[3] - Por Avaliação");
        System.out.println("[4] - Ordem Cronológica");
        System.out.println("[5] - Favoritos");
        System.out.println("================================");
        System.out.print("OPÇÃO: ");
        int opcao = entradaUsuario.nextInt();
        entradaUsuario.nextLine();

        switch (opcao) {
            case 1:
                listarAZ();
                break;
            case 2:
                listarZA();
                break;
            case 3:
                listarAval();
                break;
            case 4:
                listarTempo();
                break;
            case 5:
                listarFav();
                break;
            default:
                System.out.println("Opção Inválida!");
        }
    }

    public static void listarAZ() {
        System.out.println("\n[ LISTAGEM DE A - Z ]");
        Collections.sort(filmesLista, Comparator.comparing(Filmes :: getNomeFilme));
        for (Filmes filme : filmesLista) {
            String dataFormatada = filme.getDataHoraSessaoString();
            System.out.println(filme.getNomeFilme() + "(" + dataFormatada + ") - " + filme.getLocal());
        }
    }

    public static void listarZA() {
        System.out.println("\n [ ORDENAÇÃO DE Z - A ]");
        Collections.sort(filmesLista, Comparator.comparing(Filmes :: getNomeFilme).reversed());
        for (Filmes filme : filmesLista) {
            String dataFormatada = filme.getDataHoraSessaoString();
            System.out.println(filme.getNomeFilme() + "(" + dataFormatada  + ") - " + filme.getLocal());
        }
    }

    public static void listarAval() {
        System.out.println("\n[ ORDENAÇÃO POR AVALIAÇÃO ]");
        Collections.sort(filmesLista, Comparator.comparingInt(Filmes :: getNotaFilme).reversed());
        int notaAtual = -1;
        for (Filmes filme : filmesLista) {
            int nota = filme.getNotaFilme();
            if (nota != notaAtual) {
                notaAtual = nota;
                String estrelas = "*".repeat(nota);
                System.out.println("\nNota " + nota + " (" + estrelas + "):");
            }
            String dataFormatada = filme.getDataHoraSessaoString();
            System.out.println(filme.getNomeFilme() + " (" + dataFormatada + ") - " + filme.getLocal());
        }
    }


    public static void listarTempo() {
        System.out.println("\n[ ORDENAÇÃO POR ORDEM CRONOLÓGICA ]");
        Collections.sort(filmesLista, Comparator.comparing(Filmes :: getSessao));
        int mesAtual = 0;
        int anoAtual = 0;

        DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("MMMM/yyyy");
        DateTimeFormatter formatterDia = DateTimeFormatter.ofPattern("EEE");


        for (Filmes filme : filmesLista) {
            LocalDateTime dataHoraSessao = filme.getSessao();
            int mes = dataHoraSessao.getMonthValue();
            int ano = dataHoraSessao.getYear();
            if (mes != mesAtual || ano != anoAtual) {
                mesAtual = mes;
                anoAtual = ano;
                System.out.println("\n" + formatterData.format(dataHoraSessao));
            }

            String dia = String.format("%02d", dataHoraSessao.getDayOfMonth());
            String diaSemana = formatterDia.format(dataHoraSessao);
            String hora = formatterHora.format(dataHoraSessao.toLocalTime());
            System.out.println(dia + " (" + diaSemana + ", " + hora + ") - " + filme.getNomeFilme() + " - " + filme.getLocal());
        }
    }

    public static void listarFav() {
        System.out.println("\n[ ORDENAÇÃO DE FAVORITOS ]");
        for (Filmes filme : filmesLista) {
            if (filme.getFavorito()) {
                String dataFormatada = filme.getDataHoraSessaoString();
                System.out.println(filme.getNomeFilme() + "(" + dataFormatada + ") - " + filme.getLocal());
            }
        }
    }



}