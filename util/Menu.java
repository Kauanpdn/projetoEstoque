package util;

import model.Produto;
import service.ProdutoService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private ProdutoService service;
    private Scanner scanner;

    public Menu() {
        this.service = new ProdutoService();
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE ESTOQUE =====");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar produtos");
            System.out.println("3 - Buscar produto por ID");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Deletar produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> listarProdutos();
                case 3 -> buscarProdutoPorId();
                case 4 -> atualizarProduto();
                case 5 -> deletarProduto();
                case 0 -> System.out.println("👋 Saindo do sistema...");
                default -> System.out.println("❌ Opção inválida.");
            }

        } while (opcao != 0);
    }

    // ========= CADASTRAR =========
    private void cadastrarProduto() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setQuantidade(quantidade);
        produto.setPreco(preco);

        service.cadastrarProduto(produto);
    }

    // ========= LISTAR =========
    private void listarProdutos() {
        List<Produto> produtos = service.listarProduto();

        if (produtos.isEmpty()) {
            System.out.println("📦 Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n--- PRODUTOS CADASTRADOS ---");
        for (Produto p : produtos) {
            System.out.println(
                "ID: " + p.getId() +
                " | Nome: " + p.getNome() +
                " | Qtd: " + p.getQuantidade() +
                " | Preço: R$ " + p.getPreco()
            );
        }
    }

    // ========= BUSCAR POR ID =========
    private void buscarProdutoPorId() {
        System.out.print("Digite o ID do produto: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produto = service.buscarProdutoPorId(id);

        if (produto == null) {
            System.out.println("❌ Produto não encontrado.");
        } else {
            System.out.println(
                "ID: " + produto.getId() +
                " | Nome: " + produto.getNome() +
                " | Qtd: " + produto.getQuantidade() +
                " | Preço: R$ " + produto.getPreco()
            );
        }
    }

    // ========= ATUALIZAR =========
    private void atualizarProduto() {
        System.out.print("ID do produto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produtoExistente = service.buscarProdutoPorId(id);

        if (produtoExistente == null) {
            System.out.println("❌ Produto não encontrado.");
            return;
        }

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();

        System.out.print("Nova quantidade: ");
        int quantidade = scanner.nextInt();

        System.out.print("Novo preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        produtoExistente.setNome(nome);
        produtoExistente.setQuantidade(quantidade);
        produtoExistente.setPreco(preco);

        service.atualizarProduto(produtoExistente);
    }

    // ========= DELETAR =========
    private void deletarProduto() {
        System.out.print("ID do produto a deletar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        service.deletarProduto(id);
    }
}
