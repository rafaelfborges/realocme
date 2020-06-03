<%-- 
    Document   : cadastro
    Created on : 25/05/2020, 19:37:39
    Author     : Red
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Realoc.me - Cadastrar</title>
        
        <!-- Material Design for Bootstrap fonts and icons -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Material+Icons">

        <!-- Material Design for Bootstrap CSS -->
        <link rel="stylesheet" href="https://unpkg.com/bootstrap-material-design@4.1.1/dist/css/bootstrap-material-design.min.css" 
              integrity="sha384-wXznGJNEXNG1NFsbm0ugrLFMQPWswR3lds2VeinahP8N0zJw9VWSopbjv2x7WCvX" crossorigin="anonymous">
    </head>
    <body>        
        <header>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <a class="navbar-brand" href="http://www.realoc.me">
                  <img src="assets/images/realocme.svg" width="150" height="35" class="d-inline-block align-top" alt="Realoc.me">
                </a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
                    <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li class="nav-item active">
                          <a class="nav-link" href="HomeController?opcao=cadastrar">Cadastrar</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link" href="#">Link</a>
                        </li>
                        <li class="nav-item">
                          <a class="nav-link disabled" href="#">Disabled</a>
                        </li>
                    </ul>
                    <form class="form-inline my-2 my-lg-0">
                        <input class="form-control mr-sm-2" type="search" placeholder="Pesquisar">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
                    </form>
                </div>
            </nav>
        </header>
        
        <section class="container mt-4 text-center">
            <!-- Content here -->
            <h3>Cadastro Usuário</h3>
            <form id="formCadastro">
                <div class="row">
                    <div class="col-sm-6" id="formColumnA">
                        <div class="form-group">
                            <label for="nomeCompleto">Nome:</label>
                            <input type="text" name="nomeCompleto" id="nomeCompleto" class="form-control" placeholder="Nome completo" required>
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" name="email" id="email" class="form-control" placeholder="E-mail" required>
                        </div>
                        <div class="form-group">
                            <label for="profissao">Profissão:</label>
                            <input type="text" name="profissao" id="profissao" class="form-control" placeholder="Ex: Desenvolvedor(a) Java" required>
                        </div>
                        <div class="form-group">
                            <label for="resumo">Resumo:</label>
                            <textarea name="resumo" id="resumo" class="form-control" rows="3" maxlength="140" placeholder="Resumo sobre você" required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="cargoPretendido">Cargo Pretendido:</label>
                            <input type="text" name="cargoPretendido" id="cargoPretendido" class="form-control" placeholder="Cargo pretendido" required>
                        </div>
                    </div>
                    <div class="col-sm-6" id="formColumnB">
                        <div class="form-group">
                            <label for="urlFoto">Foto:</label>
                            <input type="text" name="urlFoto" id="urlFoto" class="form-control" placeholder="Link para foto sua" required>
                        </div>
                        <div class="row">
                            <div class="col-sm-4">
                                <div class="form-group">
                                    <label for="Contato">Contato:</label>
                                    <select class="custom-select" id="tipoContato" name="tipoContato" required>
                                        <option value="">Selecione</option>
                                        <option value="site">Site</option>
                                        <option value="linkedin">Linkedin</option>
                                        <option value="facebook">Facebook</option>
                                        <option value="outro">Outro</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-sm-8">
                                <div class="form-group">
                                    <label for="urlContato">Link Contato:</label>
                                    <div class="input-group">
                                        <input type="text" name="urlContato" id="urlContato" class="form-control" placeholder="Link para o contato" required>
                                        <div class="input-group-append">
                                            <button type="button" id="addContato" class="btn btn-dark"><i class="fas fa-plus-circle"></i></button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row mt-4">
                    <div class="col-sm-12">
                        <button id="buttonCadastrar" class="btn btn-success btn-lg" type="submit">Cadastrar</button>
                        <button id="buttonCadastrar" class="btn btn-dark btn-lg" type="button">Voltar</button>
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
