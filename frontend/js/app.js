// Simulação de dados (futuro: API REST Java)
const LIMITE_ESTOQUE = 5;

const produtos = [
    { id: 1, nome: "Mouse", quantidade: 3, preco: 80 },
    { id: 2, nome: "Teclado", quantidade: 10, preco: 150 },
    { id: 3, nome: "Monitor", quantidade: 2, preco: 900 }
];

// Dashboard
function carregarDashboard() {
    const totalProdutos = produtos.length;
    const valorTotal = produtos.reduce(
        (total, p) => total + p.quantidade * p.preco, 0
    );
    const estoqueBaixo = produtos.filter(p => p.quantidade < LIMITE_ESTOQUE).length;

    document.getElementById("totalProdutos").innerText = totalProdutos;
    document.getElementById("valorTotal").innerText =
        valorTotal.toLocaleString("pt-BR", { style: "currency", currency: "BRL" });
    document.getElementById("estoqueBaixo").innerText = estoqueBaixo;
}

// Tabela de Produtos
function carregarTabela() {
    const tabela = document.getElementById("tabelaProdutos");
    if (!tabela) return;

    tabela.innerHTML = "";

    produtos.forEach(produto => {
        const tr = document.createElement("tr");

        if (produto.quantidade < LIMITE_ESTOQUE) {
            tr.classList.add("low-stock");
        }

        tr.innerHTML = `
        <td>${produto.id}</td>
        <td>${produto.nome}</td>
        <td>${produto.quantidade}</td>
        <td>${produto.preco.toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</td>
        <td>${(produto.quantidade * produto.preco).toLocaleString("pt-BR", { style: "currency", currency: "BRL" })}</td>
        <td>
            <button onclick="editarProduto(${produto.id})">Editar</button>
            <button onclick="excluirProduto(${produto.id})">Excluir</button>
        </td>
    `;

        tabela.appendChild(tr);
    });
}

function excluirProduto(id) {
    if (confirm("Deseja realmente excluir este produto?")) {
        alert("Produto excluído (simulação)");
    }
}

function editarProduto(id) {
    alert("Editar produto ID " + id);
}

// Inicialização
document.addEventListener("DOMContentLoaded", () => {
    carregarDashboard();
    carregarTabela();
});
