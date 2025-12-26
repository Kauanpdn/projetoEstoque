package repository;

import config.DatabaseConnection;
import model.Produto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    
     // ================= INSERT =================
    public void salvar(Produto produto){

        String sql = "INSERT INTO produtos (nome,quantidade,preco) Values (?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, produto.getNome());
            stmt.setInt(2, produto.getQuantidade());
            stmt.setDouble(3, produto.getPreco());

            stmt.executeUpdate();
            System.out.println("✅ Produto inserido com sucesso!");

        
        } catch (SQLException e) {
            // TODO: handle exception
            System.out.println("❌ Erro ao inserir produto");
            e.printStackTrace();
        }
    }
    
     // ================= SELECT ALL =================n
        public List<Produto> listarTodos(){
            List<Produto> produtos = new ArrayList<>();
            String sql = "SELECT * FROM produtos";
            
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
                
                while (rs.next()) {
                    Produto produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                    );
                    produtos.add(produto);
                }
                
            } catch (SQLException e) {
                // TODO: handle exception
                System.out.println(" \"❌ Erro ao listar produtos\"");
                e.printStackTrace();
            }
            
            return produtos;
        }

          // ================= SELECT BY ID =================
        public Produto buscarPorId(int id){
            String sql = "SELECT * FROM produtos WHERE id = ?";
            Produto produto = null;

            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
                
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    produto = new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco")
                    );
                }
                
            } catch (SQLException e) {
                // TODO: handle exception
                System.out.println("❌ Erro ao buscar produto");
                e.printStackTrace();
            }
            return produto;
        }

          // ================= UPDATE =================
        public void atualizar(Produto produto){
            String sql = "UPDATE produtos set nome = ? , quantidade = ? , preco = ? WHERE id = ?";

            try ( Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            
                stmt.setString(1, produto.getNome());
                stmt.setInt(2, produto.getQuantidade());
                stmt.setDouble(3, produto.getPreco());
                stmt.setInt(4, produto.getId());

                int linhas = stmt.executeUpdate();

                if (linhas > 0) {
                    System.out.println("✅ Produto atualizado com sucesso!");
                }else{
                    System.out.println("⚠️ Produto não encontrado.");
                }

            } catch (SQLException e) {
                // TODO: handle exception
                System.out.println("❌ Erro ao atualizar produto");
                e.printStackTrace();
            }
        }

         // ================= DELETE =================
        public void deletar(int id){
            
            String sql = "DELETE FROM produtos WHERE id = ?";
            
            try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)){
                
                stmt.setInt(1, id);
                int linhas = stmt.executeUpdate();

                if (linhas > 0 ) {
                    System.out.println("🗑️ Produto deletado com sucesso!");
                }else{
                    System.out.println("⚠️ Produto não encontrado.");
                }

            } catch (SQLException e) {
                // TODO: handle exception
                System.out.println("❌ Erro ao deletar produto");
                e.printStackTrace();
            }



    
        }
}
