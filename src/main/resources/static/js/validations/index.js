let btnAddUser = document.getElementById("btn-user");
let myModal;
btnAddUser.addEventListener("click", function() {
    //document.getElementById("userModal").modal("show");
    myModal = new bootstrap.Modal(document.getElementById('userModal'), {
      keyboard: false
    })
    myModal.show()
})

let btnSaveUser = document.getElementById("btn-confirmar");
btnSaveUser.addEventListener("click", function() {
    (async () => {
        try {
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
            bodyRequest.name = name.value;
            bodyRequest.firstName = firstName.value;
            bodyRequest.lastName = lastName.value;
            bodyRequest.userName = userName.value;
            bodyRequest.phoneNumber = phoneNumber.value;
            bodyRequest.email = email.value;
            bodyRequest.password = forge_sha256(password.value);
            bodyRequest.idUserType = userType.value;
            bodyRequest.idCorporation = corporation.value;

            const url = '/addUser';
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


