<%-- 
    Document   : index
    Created on : 22/05/2020, 19:17:26
    Author     : Red
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="indicacoes" type="java.util.List<model.Indicacao>" scope="request" />
<jsp:useBean id="usuario" type="model.Usuario" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Realoc.me - Home</title>

        <!-- Material Design for Bootstrap fonts and icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

        <!-- Material Design for Bootstrap CSS -->
        <link rel="stylesheet" href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css" 
              integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">

        <!-- Custom CSS -->
        <link rel="stylesheet" href="assets/css/styles.css">
    </head>
    <body>        
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="home">
                    <img src="assets/images/realocme.svg" width="150" height="35" class="d-inline-block align-top" alt="Realoc.me">
                </a>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="nav navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="home">Home</a>
                        </li>
                        <c:if test="${sessionScope.id == null}">
                            <li class="nav-item active">
                                <a class="nav-link" href="cadastro.html">Cadastrar</a>
                            </li>
                        </c:if>
                        <c:choose>
                            <c:when test="${sessionScope.id != null}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle font-weight-bold" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                        ${sessionScope.nome}
                                    </a>
                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                        <a class="dropdown-item" href="#">Minha Conta</a>
                                        <a class="dropdown-item" href="#">Ajuda</a>
                                        <div class="dropdown-divider"></div>
                                        <a class="dropdown-item" href="usuario?acao=logoff" id="logoff">Sair</a>
                                    </div>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="nav-item active">
                                    <a class="nav-link" href="login.html">Login</a>
                                </li>
                            </c:otherwise>
                        </c:choose>                     
                    </ul>
                    <!-- <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
                    </form> -->
                </div>
            </nav>
        </header>

        <section class="container mt-4">
            <div class="row">
                <div class="w-100 text-center">
                    <c:choose>
                        <c:when test="${usuario.id == sessionScope.id}">
                            <h3>Suas indicações</h3>
                        </c:when>
                        <c:when test="${sessionScope.id == null}">
                            <h3>Indicações de ${usuario.nomeCompleto}</h3>
                            <p class="text-warning">Logue para adionar uma indicação!</p>
                        </c:when>
                        <c:otherwise>
                            <h3>Indicações de ${usuario.nomeCompleto}</h3>
                            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalNovaIndicacao">
                                Adicionar nova indicação
                            </button>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:forEach var="indicacao" items="${indicacoes}">
                    <div class="col-sm-6">
                        <div class="card mt-4">
                            <div class="card-header">Indicação</div>
                            <div class="card-body">
                                <blockquote class="blockquote mb-0">
                                    <p>${indicacao.comentario}</p>
                                    <footer class="blockquote-footer">Por <cite title="Source Title">${indicacao.nomePessoa}</cite></footer>
                                </blockquote>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- Modal -->
        <div class="modal fade" id="modalNovaIndicacao" tabindex="-1" role="dialog" aria-labelledby="modalNovaIndicacaoCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalNovaIndicacaoTitulo">Cadastrar Indicação</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form id="formIndicacao" method="POST" action="indicacao?acao=cadastrar">
                            <input type="hidden" name="id" id="id" value="${usuario.id}" required>
                            <c:choose>
                                <c:when test="${sessionScope.id != null}">
                                    <div class="form-group">
                                        <label for="nome">Seu nome:</label>
                                        <input type="text" name="nome" id="nome" class="form-control" value="${sessionScope.nome}" maxlength="100" required>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-group">
                                        <label for="nome">Seu nome:</label>
                                        <input type="text" name="nome" id="nome" class="form-control" placeholder="Nome" maxlength="100" required>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                            <div class="form-group">
                                <label for="resumo">Comentário:</label>
                                <textarea name="comentario" id="comentario" class="form-control" rows="8" placeholder="Seu comentário" required></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer mb-3">
                        <button type="submit" form="formIndicacao" class="btn btn-primary mr-3">Enviar Indicação</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS, then FontAwesome -->
        <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/popper.js@1.12.6/dist/umd/popper.js" integrity="sha384-fA23ZRQ3G/J53mElWqVJEGJzU0sTs+SvzG8fXVWP+kJQ1lwFAOkcUOysnlKJC33U" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/bootstrap-material-design@4.1.1/dist/js/bootstrap-material-design.js" integrity="sha384-CauSuKpEqAFajSpkdjv3z9t8E7RlpJ1UP0lKM/+NdtSarroVKu069AlsRPKkFBz9" crossorigin="anonymous"></script>
        <script src="https://kit.fontawesome.com/4f1d3e52f2.js" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <!-- Custom JavaScript -->
        <script src="assets/js/scripts.js"></script>
    </body>
</html>
