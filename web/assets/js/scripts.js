/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
  aplicarMaterialDesign();
  addCampoContato();
  cadastrarUsuario();
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
    })

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
      url: "UsuarioController?acao=cadastrar",
      cache: false,
      data: dados,
      type: "POST",
      success: () => {
        this.reset();
        swal("Bem vindo!", "Cadastrado com sucesso", "success")
        .then(() => {
            window.location.href = "index.jsp";
        });
      },
      error: (response) => {
        swal("Oops!", "Algum problema com seu cadastro: " + response.responseText, "warning");
      }
    })
  });
}

function aplicarMaterialDesign() {
    $('body').bootstrapMaterialDesign();
}