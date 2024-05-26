$(document).ready(function(){

    $("#myInput").on("keyup", function() {

        var value = $(this).val().toLowerCase();

        busqueda = {

            'busqueda':value
        };

        $.post("../db/busquedaA.php", busqueda, function(respuesta){

            console.log(respuesta);
            /*$("#tabla1 tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });*/
            respuesta = JSON.parse(respuesta);

            var text = "";
            if (!(respuesta[0]['resp']==true)) {

                for(i = 0; i<respuesta.length; i++){
                    text += `
                        <tr id="${respuesta[i]['phone']}">
                            <td class="bg-danger " >${respuesta[i]['name']}</td>
                            <td >${respuesta[i]['userName']}</td>
                            <td >${respuesta[i]['email']}</td>
                            <td >${respuesta[i]['tipo_user']}</td>
                            <td ><button  class="btn btn-outline-danger  btn-sm delete" name="btn-del" id = "btn-delete">Eliminar</button></td>
                        </tr>`;

                        }
                        $('#res').empty();
                        $('#tablaA').empty();
                        $('#tablaA').append(text);
            }
            else {

                var res="";
                res += `<h3>No hay resultados</h3>`;

                $('#tablaA').empty();
                $('#res').empty();
                $('#res').append(res);
            }

        });
    });
});
$(document).on('click', '#btn-delete', function(){
    $("#deleteAModal").modal("show");
    var atributo = {'id_usuario' : $(this).parent().parent().attr('id')};
    console.log(atributo);
   $.post('../db/modifyADB.php', atributo, function(respuesta){
      console.log(respuesta);
      data = JSON.parse(respuesta);
       console.log(data[0]['id_user']);

        $('#id_deleteA').val(data[0]['id_user']);

   });


});
$('#eliminarA').on('click',function(){
    //console.log("eliminando");
       var id = $('#id_deleteA').val();

        var datos_enviar = {
            'id' : id

        };
       //console.log(datos_enviar);
       $.post('../db/eliminar_adminDB.php', datos_enviar, function(respuesta){
        console.log(respuesta);
        res = JSON.parse(respuesta);
        //console.log(res);
        if (res[0]['resp'] == true) {
            $('#deleteAModal').modal('hide');
            var msj="";
            msj +=`
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <strong>Hola SaulMa!</strong> Has eliminado un Admin.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                                `;
                $('#alertEA').empty();
                $('#alertEA').append(msj);
                getUserList();
        }
        else{
            $('#deleteAModal').modal('hide');
            var msj="";
            msj +=`
                    <div class="alert alert-warning alert-dismissible fade show" role="alert">
                        <strong>Hola SaulMa!</strong> No se ha podido eliminar el Admin.
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                                `;
                $('#alertEA').empty();
                $('#alertEA').append(msj);
        }
       });
});
