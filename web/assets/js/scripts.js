/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
  aplicarMaterialDesign();
  addCampoContato();
  cadastrarUsuario();
  atualizarUsuario();
  validarSenha();
  autenticarUsuario();
});

function addCampoContato(){
    var cont = 1;
    $("#addContato").click(() => {
        if(cont < 4){
            cont++;
            $("#formColumnB").append(`
                <div class="row" id="contactRow${cont}">
                    <div class="col-sm-4">
                        <div class="form-group bmd-form-group">
                            <label for="Contato" class="bmd-label-static">Contato:</label>
                            <select class="custom-select" id="tipoContato" name="tipoContato" required>
                                <option selected>Selecione</option>
                                <option value="site">Site</option>
                                <option value="linkedin">Linkedin</option>
                                <option value="facebook">Facebook</option>
                                <option value="outro">Outro</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-sm-8">
                        <div class="form-group bmd-form-group">
                            <label for="urlContato" class="bmd-label-static">Link Contato:</label>
                            <div class="input-group">
                                <input type="text" name="urlContato" id="urlContato" class="form-control" placeholder="Link para o contato" required>
                                <div class="input-group-append">
                                    <button type="button" id="${cont}" class="btn btn-dark btn-apagar"><i class="fas fa-minus-circle"></i></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            `);
        }
    });

    $("#formCadastro").on("click", ".btn-apagar", function() {
        var btnId = $(this).attr("id");
        $("#contactRow" + btnId).remove();
        cont--;
    });    
}

function cadastrarUsuario() {
  $("#formCadastro").submit(function () {
    event.preventDefault();
    let dados = $(this).serializeArray();
    $.ajax({
      url: "usuario?acao=cadastrar",
      cache: false,
      data: dados,
      type: "POST",
      success: () => {
        this.reset();
        swal("Bem vindo!", "Cadastrado com sucesso! Vamos te redirecionar para o login.", "success")
        .then(() => {
            window.location.href = "login.html";
        });
      },
      error: (response) => {
        swal("Oops!", "Algum problema com seu cadastro: " + response.responseText, "warning");
      }
    });
  });
}

function removerUsuario(idUsuario) {
    event.preventDefault();
    swal({
        title: "Tem certeza?",
        text: "Deletar sua conta não poderá ser revertida!",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            $.ajax({
                url: "usuario?acao=deletar&id=" + idUsuario,
                cache: false,
                type: "POST",
                success: () => {
                    swal("Adeus!", "Conta deletada com sucesso!", "success")
                    .then(() => {
                        window.location.href = "home";
                    });
                },
                error: (response) => {
                    swal("Oops!", "Algum problema ao remover conta: " + response.responseText, "warning");
                }
            });
        }
    });
}

function atualizarUsuario() {
  $("#formAtualizar").submit(function () {
    event.preventDefault();
    let dados = $(this).serializeArray();
    $.ajax({
      url: "usuario?acao=atualizar",
      cache: false,
      data: dados,
      type: "POST",
      success: () => {
        swal("Bem vindo!", "Atualizado com sucesso!", "success");
      },
      error: (response) => {
        swal("Oops!", "Algum problema ao atualizar cadastro: " + response.responseText, "warning");
      }
    });
  });
}

function autenticarUsuario() {
  $("#formLogin").submit(function () {
    event.preventDefault();
    let dados = $(this).serializeArray();
    $.ajax({
      url: "usuario?acao=login",
      cache: false,
      data: dados,
      type: "POST",
      success: () => {
        this.reset();
        window.location.href = "home";
      },
      error: (response) => {
        swal("Oops!", "Algum problema ao autenticar: " + response.responseText, "warning");
      }
    });
  });
}

function validarSenha() {
  $("#confirmaSenha").blur(() => {
    const senhaA = $("#senha");
    const senhaB = $("#confirmaSenha");

    if (senhaA.val() === senhaB.val()) {
      
      if(senhaA.hasClass("is-invalid")) {
        senhaA.removeClass("is-invalid");
        senhaB.removeClass("is-invalid");
      }
      senhaA.toggleClass("is-valid");
      senhaB.toggleClass("is-valid");
      $("#buttonCadastrar").removeAttr("disabled");
    } else {
      if (senhaA.hasClass("is-valid")) {
        senhaA.removeClass("is-valid");
        senhaB.removeClass("is-valid");
      }
      senhaA.toggleClass("is-invalid");
      senhaB.toggleClass("is-invalid");
      $("#buttonCadastrar").attr("disabled", true);
    }
  });
}

function aplicarMaterialDesign() {
    $('body').bootstrapMaterialDesign();
}