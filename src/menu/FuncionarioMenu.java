package menu;

import dao.FuncionarioDAO;
import model.Funcionario;
import model.Setor;

import java.util.ArrayList;
import java.util.Scanner;

public class FuncionarioMenu {
    private static FuncionarioDAO dao = new FuncionarioDAO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU Funcionário ---");
            System.out.println("[1] Listar Funcionários");
            System.out.println("[2] Buscar Funcionário por ID");
            System.out.println("[3] Cadastrar Funcionário");
            System.out.println("[4] Atualizar Funcionário");
            System.out.println("[5] Remover Funcionário");
            System.out.println("[0] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1:
                    listarFuncionario();
                    break;
                case 2:
                    buscarPorId();
                    break;
                case 3:
                    cadastrarFuncionario();
                    break;
                case 4:
                    atualizarFuncionario();
                    break;
                case 5:
                    removerFuncionario();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // Listar Funcionários
    private static void listarFuncionario() {
        ArrayList<Funcionario> funcionarios = dao.listar();
        if (funcionarios != null && !funcionarios.isEmpty()) {
            for (Funcionario funcionario : funcionarios) {
                System.out.println(funcionario);
            }
        } else {
            System.out.println("Nenhum funcionário encontrado.");
        }
    }

    // Buscar Funcionário por ID
    private static void buscarPorId() {
        System.out.print("Digite o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer
        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario != null) {
            System.out.println(funcionario);
        } else {
            System.out.println("Funcionário não encontrado.");
        }
    }

    // Cadastrar Funcionário
    private static void cadastrarFuncionario() {
        System.out.print("Nome do funcionário: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        System.out.print("cargo do funcionário: ");
        String cargo = scanner.nextLine().trim();
        if (cargo.isEmpty()) {
            System.out.println("Sobrenome não pode ser vazio!");
            return;
        }

        System.out.print("ID do setor: ");
        int idSetor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Setor setor = new Setor(1, "Financeiro", null);


        Funcionario funcionario = new Funcionario(null, nome, cargo, setor);

        if (dao.cadastrar(funcionario)) {
            System.out.println("Funcionário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar funcionário.");
        }
    }

    // Atualizar Funcionário
    private static void atualizarFuncionario() {
        System.out.print("Digite o ID do funcionário a ser atualizado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Funcionario funcionario = dao.buscarPorId(id);
        if (funcionario == null) {
            System.out.println("Funcionário não encontrado.");
            return;
        }

        System.out.print("Novo nome do funcionário: ");
        String nome = scanner.nextLine().trim();
        if (nome.isEmpty()) {
            System.out.println("Nome não pode ser vazio!");
            return;
        }

        System.out.print("Novo cargo: ");
        String cargo = scanner.nextLine().trim();
        if (cargo.isEmpty()) {
            System.out.println("Sobrenome não pode ser vazio!");
            return;
        }

        System.out.print("Novo ID do setor: ");
        int idSetor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer

        Setor setor = new Setor(1, "Financeiro", null);


        funcionario.setNome(nome);
        funcionario.setCargo(cargo);
        funcionario.setSetor(setor);

        if (dao.atualizar(funcionario)) {
            System.out.println("Funcionário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar funcionário.");
        }
    }

    // Remover Funcionário
    private static void removerFuncionario() {
        System.out.print("Digite o ID do funcionário a ser removido: ");
        int id = scanner.nextInt();
        if (dao.remover(id)) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Erro ao remover funcionário.");
        }
    }
}
