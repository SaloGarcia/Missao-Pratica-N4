package cadastroee.servlets;

import java.io.IOException;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cadastroee.model.Produto;
import cadastroee.facades.ProdutoFacadeLocal;

public class ServletProdutoFCC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String destino = "ProdutoLista.jsp"; // Default destination

        try {
            if ("listar".equals(acao)) {
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);

            } else if ("formAlterar".equals(acao)) {
                String id = request.getParameter("id");
                Produto produto = facade.find(Long.parseLong(id));
                request.setAttribute("produto", produto);
                destino = "ProdutoDados.jsp";

            } else if ("formIncluir".equals(acao)) {
                destino = "ProdutoDados.jsp";

            } else if ("excluir".equals(acao)) {
                String id = request.getParameter("id");
                Produto produto = facade.find(Long.parseLong(id));
                facade.remove(produto);
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);

            } else if ("alterar".equals(acao)) {
                String id = request.getParameter("id");
                Produto produto = facade.find(Long.parseLong(id));
                produto.setNome(request.getParameter("nome"));
                produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                facade.edit(produto);
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);

            } else if ("incluir".equals(acao)) {
                Produto produto = new Produto();
                produto.setNome(request.getParameter("nome"));
                produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                produto.setPreco(Double.parseDouble(request.getParameter("preco")));
                facade.create(produto);
                List<Produto> produtos = facade.findAll();
                request.setAttribute("produtos", produtos);
            }

        } catch (Exception e) {
            request.setAttribute("mensagemErro", "Erro: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet Produto Front Controller";
    }
}
