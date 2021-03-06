<%-- 
    Document   : index
    Created on : 22/05/2020, 19:17:26
    Author     : Red
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<jsp:useBean id="usuarios" type="java.util.List<model.Usuario>" scope="request" />
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

        <section class="container mt-4">
            <!-- Content here -->      
            <div class="row">
                <c:forEach var="usuario" items="${usuarios}">
                    <div class="col-md-6">
                        <div class="card mb-3">
                            <div class="row no-gutters">
                                <div class="col-md-4">
                                    <img src="${usuario.urlFoto}" class="card-img img-card" alt="perfil">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body h-100 d-flex flex-column justify-content-between">
                                        <div>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <h5 class="card-title mb-0">${usuario.nomeCompleto}</h5>
                                                <div>
                                                    <c:forEach var="contato" items="${usuario.contatos}">
                                                        <c:choose>
                                                            <c:when test="${contato.tipo == 'site'}">
                                                                <a href="${contato.url}" target="_blank">
                                                                    <i class="fas fa-globe text-realocme icons" aria-hidden="true" title="Site/Blog"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:when test="${contato.tipo == 'linkedin'}">
                                                                <a href="${contato.url}" target="_blank">
                                                                    <i class="fab fa-linkedin text-realocme icons" aria-hidden="true" title="Linkedin"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:when test="${contato.tipo == 'facebook'}">
                                                                <a href="${contato.url}" target="_blank">
                                                                    <i class="fab fa-facebook-square text-realocme icons" aria-hidden="true" title="Facebook"></i>
                                                                </a>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <a href="${contato.url}" target="_blank">
                                                                    <i class="fas fa-globe-americas text-realocme icons" aria-hidden="true" title="Outro/Portfólio"></i>
                                                                </a>   
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                    <a href="mailto:${usuario.email}" target="_blank">
                                                        <i class="fas fa-envelope text-realocme icons" aria-hidden="true" title="E-mail"></i>
                                                    </a>
                                                </div>
                                            </div>
                                            <p class="card-text mb-1 font-weight-bold text-muted">${usuario.profissao}</p>
                                        </div>
                                        <p class="card-text mb-1">${usuario.resumo}</p>
                                        <div class="d-flex flex-wrap justify-content-between align-items-center">
                                            <p class="card-text mb-0"><strong class="text-muted">Objetivo:&nbsp;</strong>${usuario.cargoPretendido}</p>
                                            <a href="indicacao?acao=listar&id=${usuario.id}&nome=${usuario.nomeCompleto}" class="btn btn-success btn-sm mb-0 text-info">Indicações</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
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
