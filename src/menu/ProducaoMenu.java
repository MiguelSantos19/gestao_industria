package menu;

import dao.ProducaoDAO;
import dao.ProdutoDAO;
import dao.FuncionarioDAO;
import model.Producao;
import model.Produto;
import model.Funcionario;

import java.util.ArrayList;
import java.util.Scanner;

public class ProducaoMenu {
    private static ProducaoDAO producaoDAO = new ProducaoDAO();
    private static ProdutoDAO produtoDAO = new ProdutoDAO();
    private static FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU PRODUÇÃO ---");
            System.out.println("[1] Listar produções");
            System.out.println("[2] Buscar produção por ID");
            System.out.println("[3] Cadastrar produção");
            System.out.println("[4] Atualizar produção");
            System.out.println("[5] Remover produção");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    listarProducoes();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarProducao();
                    break;
                case 4:
                    atualizarProducao();
                    break;
                case 5:
                    removerProducao();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    public static void listarProducoes() {
        ArrayList<Producao> producoes = producaoDAO.listar();
        if (producoes != null && !producoes.isEmpty()) {
            for (Producao p : producoes) {
                System.out.println(p);
            }
        } else {
            System.out.println("Nenhuma produção encontrada.");
        }
    }

    public static void buscarPorId() {
        System.out.print("Digite o ID da produção: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producao p = producaoDAO.buscarPorId(id);

        if (p != null) {
            System.out.println(p);
        } else {
            System.out.println("Produção não encontrada.");
        }
    }

    public static void cadastrarProducao() {
        System.out.print("ID do Produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();
        Produto produto = produtoDAO.buscarPorId(idProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("ID do Funcionário responsável: ");
        int idFuncionario = scanner.nextInt();
        scanner.nextLine();
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Data da produção (YYYY-MM-DD): ");
        String dataProducao = scanner.nextLine();

        System.out.print("Quantidade produzida: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Producao producao = new Producao();

        if (producaoDAO.cadastrar(producao)) {
            System.out.println("Produção cadastrada com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar produção.");
        }
    }

    public static void atualizarProducao() {
        System.out.print("Digite o ID da produção a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Producao producao = producaoDAO.buscarPorId(id);
        if (producao == null) {
            System.out.println("Produção não encontrada.");
            return;
        }

        System.out.print("Novo ID do Produto: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine();
        Produto produto = produtoDAO.buscarPorId(idProduto);

        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo ID do Funcionário responsável: ");
        int idFuncionario = scanner.nextInt();
        scanner.nextLine();
        Funcionario funcionario = funcionarioDAO.buscarPorId(idFuncionario);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Nova data da produção (YYYY-MM-DD): ");
        String dataProducao = scanner.nextLine();

        System.out.print("Nova quantidade produzida: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        producao.setProduto(produto);
        producao.setFuncionario(funcionario);
        producao.setDataProducao(dataProducao);
        producao.setQuantidade(quantidade);

        if (producaoDAO.atualizar(producao)) {
            System.out.println("Produção atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar produção.");
        }
    }

    public static void removerProducao() {
        System.out.print("Digite o ID da produção que deseja remover: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (producaoDAO.remover(id)) {
            System.out.println("Produção removida com sucesso.");
        } else {
            System.out.println("Erro ao remover produção.");
        }
    }
}