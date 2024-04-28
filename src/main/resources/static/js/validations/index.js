let btnAddUser = document.getElementById("btn-user");
let myModal;
btnAddUser.addEventListener("click", function() {
    //document.getElementById("userModal").modal("show");
    myModal = new bootstrap.Modal(document.getElementById('userModal'), {
      keyboard: false
    })
    document.getElementById('name').value = "";
    document.getElementById('firstName').value = "";
    document.getElementById('lastName').value = "";
    document.getElementById('userName').value = "";
    document.getElementById('phoneNumber').value = "";
    document.getElementById('email').value = "";
    document.getElementById('userType').value = "";
    document.getElementById('corporation').value = "";
    document.getElementById("idUser").value = null;
    myModal.show()
})

let btnSaveUser = document.getElementById("btn-confirmar");
btnSaveUser.addEventListener("click", function() {
    (async () => {
        try {
            let idUser      = document.getElementById("idUser");
            let name        = document.getElementById('name');
            let firstName   = document.getElementById('firstName');
            let lastName    = document.getElementById('lastName');
            let userName    = document.getElementById('userName');
            let phoneNumber = document.getElementById('phoneNumber');
            let email       = document.getElementById('email');
            let password    = document.getElementById('password');
            let userType    = document.getElementById('userType');
            let corporation = document.getElementById('corporation');
            //Esta variable es solo para confirmar la contraseña del usuario.
            let passwordConfirm = document.getElementById('passwordConfirm');

            if (password.value !== passwordConfirm.value) {
                console.log('Error contraseñas no coinciden');
                Swal.fire({
                      title: 'Error',
                      text: 'Error contraseñas no coinciden',
                      icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                      confirmButtonText: 'OK' // Texto del botón de confirmación
                    });
                return false;
            }

            let bodyRequest = {};
            bodyRequest.id = idUser.value;
            bodyRequest.name = name.value;
            bodyRequest.firstName = firstName.value;
            bodyRequest.lastName = lastName.value;
            bodyRequest.userName = userName.value;
            bodyRequest.phoneNumber = phoneNumber.value;
            bodyRequest.email = email.value;
            bodyRequest.password = forge_sha256(password.value);
            bodyRequest.idUserType = userType.value;
            bodyRequest.idCorporation = corporation.value;

            const url = '/user';
            const options = getOptionsForPostFetch(bodyRequest);
            const data = await fetchData(url, options);
            console.log('data', data);
            Swal.fire({
                title: 'Exito',
                text: data.message,
                icon: 'success', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                confirmButtonText: 'OK' // Texto del botón de confirmación
            });
            myModal.hide();
            //todo limpiar el form del modal.

        } catch (error) {
            console.error('Error:', error);
            Swal.fire({
            title: 'Error',
            text: error,
            icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
            confirmButtonText: 'OK' // Texto del botón de confirmación
            });
        }
    })();
})

function muestraModal(boton) {
    document.getElementById("exampleModalLabel").textContent = "Editar Usuario";
    myModal = new bootstrap.Modal(document.getElementById('userModal'), {
          keyboard: false
        })
        myModal.show()
    let fila = boton.parentNode.parentNode;
    let celdas = fila.getElementsByTagName("td");
    let id = celdas[0].innerHTML;
    let name = celdas[1].innerHTML;
    let firstName = celdas[2].innerHTML;
    let lastName = celdas[3].innerHTML;
    let userName = celdas[4].innerHTML;
    let phoneNumber = celdas[5].innerHTML;
    let email = celdas[6].innerHTML;
    let idUserType = celdas[7].innerHTML;
    let idCorporation = celdas[8].innerHTML;

    document.getElementById('name').value = name;
    document.getElementById('firstName').value = firstName;
    document.getElementById('lastName').value = lastName;
    document.getElementById('userName').value = userName;
    document.getElementById('phoneNumber').value = phoneNumber;
    document.getElementById('email').value = email;
    document.getElementById('userType').value = idUserType;
    document.getElementById('corporation').value = idCorporation;
    document.getElementById("idUser").value = id;

}
function showDeleteModal(boton){
 myModal = new bootstrap.Modal(document.getElementById('deleteModal'), {
          keyboard: false
        })
        myModal.show()
        let fila = boton.parentNode.parentNode;
        let celdas = fila.getElementsByTagName("td");
        let id = celdas[0].innerHTML;
        let btnDeteleUser = document.getElementById("btn-delete");
        btnDeteleUser.setAttribute("data-id", id);
}


let btnDeteleUser = document.getElementById("btn-delete");
btnDeteleUser.addEventListener("click", function() {
    (async () => {
        try {
            let idUser      = this.getAttribute("data-id");
            let bodyRequest = idUser;

            const url = '/deleteUser';
            const options = getOptionsForPostFetch(bodyRequest);
            const data = await fetchData(url, options);
            console.log('data', data);
            Swal.fire({
                title: 'Exito',
                text: data.message,
                icon: 'success', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                confirmButtonText: 'OK' // Texto del botón de confirmación
            });
            myModal.hide();
            //todo limpiar el form del modal.

        } catch (error) {
            console.error('Error:', error);
            Swal.fire({
            title: 'Error',
            text: error,
            icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
            confirmButtonText: 'OK' // Texto del botón de confirmación
            });
        }
    })();
})