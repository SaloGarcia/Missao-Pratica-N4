package cadastroee.servlets;

import cadastroee.facades.ProdutoFacadeLocal;
import cadastroee.model.Produto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletProduto")
public class ServletProduto extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            List<Produto> produtos = facade.findAll();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletProduto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ServletProduto at " + request.getContextPath() + "</h1>");

            if (produtos != null && !produtos.isEmpty()) {
                out.println("<ul>");
                for (Produto produto : produtos) {
                    out.println("<li>" + produto.getNome() + " - " + produto.getPrecoVenda() + "</li>");
                }
                out.println("</ul>");
            } else {
                out.println("<p>Nenhum produto encontrado.</p>");
            }

            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            out.println("<html><body><h1>Erro: " + e.getMessage() + "</h1></body></html>");
            e.printStackTrace(); 
        } finally {
            out.close();
        }
    }
}
