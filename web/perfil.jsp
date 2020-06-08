<%-- 
    Document   : index
    Created on : 22/05/2020, 19:17:26
    Author     : Red
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
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
                                        <a class="dropdown-item" href="usuario?acao=listar&id=${sessionScope.id}">Minha Conta</a>
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

        <section class="container mt-4 text-center">
            <!-- Content here -->
            <h3>Cadastro Usuário</h3>
            <form id="formAtualizar">
                <input type="hidden" name="id" id="id" value="${usuario.id}" required>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="nomeCompleto">Nome:</label>
                                    <input type="text" name="nomeCompleto" id="nomeCompleto" class="form-control" value="${usuario.nomeCompleto}" maxlength="100" required>
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <input type="email" name="email" id="email" class="form-control" value="${usuario.email}" maxlength="100" required>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="senha">Senha:</label>
                                    <input type="password" name="senha" id="senha" class="form-control" value="${usuario.senha}" maxlength="50">
                                </div>
                            </div>
                            <div class="col-sm-6">
                                <div class="form-group">
                                    <label for="confirmaSenha">Confirma Senha:</label>
                                    <input type="password" name="confirmaSenha" id="confirmaSenha" class="form-control" value="${usuario.senha}" maxlength="50">
                                    <span class="invalid-feedback">
                                        Senhas não conferem! Tente novamente.
                                    </span>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="profissao">Profissão:</label>
                            <input type="text" name="profissao" id="profissao" class="form-control" value="${usuario.profissao}" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="resumo">Resumo:</label>
                            <textarea name="resumo" id="resumo" class="form-control" rows="3" maxlength="140" required>${usuario.resumo}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="cargoPretendido">Cargo Pretendido:</label>
                            <input type="text" name="cargoPretendido" id="cargoPretendido" class="form-control" value="${usuario.cargoPretendido}" maxlength="100" required>
                        </div>
                        <div class="form-group">
                            <label for="urlFoto">Foto:</label>
                            <input type="text" name="urlFoto" id="urlFoto" class="form-control" value="${usuario.urlFoto}" required>
                        </div>
                    </div>
                </div>
                <div class="row mt-2">
                    <div class="col-sm-12">
                        <div class="form-inline d-flex flex-row justify-content-center align-self-center mb-2">
                            <div class="form-group">
                                <input type="checkbox" name="perfilAtivo" id="perfilAtivo" <c:if test="${usuario.perfilAtivo == 1}">checked</c:if>>
                                <p class="mt-3 ml-2">Perfil ativo para busca?</p>
                            </div>
                        </div>
                                                                       
                        <button id="buttonAtualizar" class="btn btn-success btn-lg" type="submit">Atualizar</button>
                        <a href="/realocme">
                            <button id="buttonVoltar" class="btn btn-dark btn-lg" type="button">Voltar</button>
                        </a>
                    </div>
                </div>
            </form>
        </section>

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
