package service;

import repository.ProdutoRepository;
import model.Produto;

import java.util.List;

public class ProdutoService {
    
    private ProdutoRepository repository;

    public ProdutoService(){
        this.repository = new ProdutoRepository();
    }

    // ========== CADASTRAR ==========
    public void cadastrarProduto(Produto produto){

        if (produto == null) {
            System.out.println("❌ Produto não pode ser nulo.");
            return;
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            System.out.println("❌ Nome do produto é obrigatório.");
            return;
        }

        if (produto.getQuantidade() < 0 ) {
            System.out.println("❌ Quantidade não pode ser negativa.");
            return;
        }

        if (produto.getPreco() <= 0) {
            System.out.println("❌ Preço deve ser maior que zero.");
            return;
        }

        repository.salvar(produto);
    }

    // ========== LISTAR ==========
    public List<Produto> listarProduto(){
        return repository.listarTodos();
    }

     // ========== BUSCAR POR ID ==========
    public Produto buscarProdutoPorId(int id){
        if (id <= 0 ) {
            System.out.println("❌ ID inválido.");
            return null;
        }

        return repository.buscarPorId(id);
    }

       // ========== ATUALIZAR ==========
    public void atualizarProduto(Produto produto){
        
        if (produto == null || produto.getId() <= 0) {
            System.out.println("❌ Produto inválido para atualização.");
            return;
        }

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            System.out.println("❌ Nome é obrigatório.");
            return;
        }

        if (produto.getQuantidade() < 0) {
            System.out.println("❌ Quantidade inválida.");
            return;
        }

        if (produto.getPreco() <= 0) {
            System.out.println("❌ Preço inválido.");
            return;
        }

        repository.atualizar(produto);
    }

     // ========== DELETAR ==========
    public void deletarProduto(int id){

        if (id <= 0 ) {
            System.out.println("❌ ID inválido.");
            return;
        }

        repository.deletar(id);
    }

}
