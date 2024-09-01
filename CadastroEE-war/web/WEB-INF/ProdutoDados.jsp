<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dados do Produto</title>
</head>
<body>
    <h1>Dados do Produto</h1>
    <form action="ServletProdutoFC" method="post">
        <c:set var="acao" value="${empty produto ? 'incluir' : 'alterar'}" />
        <input type="hidden" name="acao" value="${acao}" />
        
        <c:if test="${acao == 'alterar'}">
            <input type="hidden" name="id" value="${produto.id}" />
        </c:if>

        <div>
            <label>Nome:</label>
            <input type="text" name="nome" value="${produto.nome}" required />
        </div>
        <div>
            <label>Quantidade:</label>
            <input type="number" name="quantidade" value="${produto.quantidade}" required />
        </div>
        <div>
            <label>Preço:</label>
            <input type="number" step="0.01" name="preco" value="${produto.preco}" required />
        </div>
        <div>
            <button type="submit">${acao == 'incluir' ? 'Incluir' : 'Alterar'}</button>
        </div>
    </form>
</body>
</html>
