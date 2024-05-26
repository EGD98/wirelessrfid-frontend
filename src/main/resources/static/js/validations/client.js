let btnAddClient = document.getElementById("btn-client");
let myModal;
btnAddClient.addEventListener("click", function() {
    myModal = new bootstrap.Modal(document.getElementById('userModal'), {
      keyboard: false
    })
    document.getElementById('name').value = "";
    document.getElementById('firstName').value = "";
    document.getElementById('lastName').value = "";
    document.getElementById('rfidCode').value = "";
    document.getElementById('phoneNumber').value = "";
    document.getElementById('email').value = "";
    document.getElementById('space').value = "";
    document.getElementById('ingressDate').value = "";
    document.getElementById('egressDate').value = "";
    document.getElementById('ingressTime').value = "";
    document.getElementById('egressTime').value = "";
    document.getElementById("idUser").value = null;
    myModal.show()
})

let btnSaveclient = document.getElementById("btn-confirmar");
btnSaveclient.addEventListener("click", function() {
    (async () => {
        try {
            let idClient      = document.getElementById("idUser");
            let name        = document.getElementById('name');
            let firstName   = document.getElementById('firstName');
            let lastName    = document.getElementById('lastName');
            let space    = document.getElementById('space');
            let rfidCode    = document.getElementById('rfidCode');

            let phoneNumber = document.getElementById('phoneNumber');
            let email       = document.getElementById('email');
            let ingressDate    = document.getElementById('ingressDate');
            let egressDate    = document.getElementById('egressDate');
            let ingressTime = document.getElementById('ingressTime');
            let egressTime = document.getElementById('egressTime');
            //Esta variable es solo para confirmar la contraseña del usuario.
            let passwordConfirm = document.getElementById('passwordConfirm');

            let bodyRequest = {};
            bodyRequest.id = idClient.value;
            bodyRequest.name = name.value;
            bodyRequest.firstName = firstName.value;
            bodyRequest.lastName = lastName.value;
            bodyRequest.space = space.value;
            bodyRequest.rfidCode = rfidCode.value;
            bodyRequest.phoneNumber = phoneNumber.value;
            bodyRequest.email = email.value;
            bodyRequest.admissionDate = ingressDate.value;
            bodyRequest.egressDate = egressDate.value;
            bodyRequest.admissionTime = ingressTime.value;
            bodyRequest.egressTime = egressTime.value;

            const url = '/client';
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

function showModal(boton) {
    document.getElementById("exampleModalLabel").textContent = "Editar Cliente";
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
    let phoneNumber = celdas[4].innerHTML;
    let email = celdas[5].innerHTML;
    let rfidCode = celdas[6].innerHTML;
    let space = celdas[7].innerHTML;
    let ingressDate = celdas[8].innerHTML;
    let egressDate = celdas[9].innerHTML;
    let ingressTime = celdas[10].innerHTML;
    let egressTime = celdas[11].innerHTML;

    document.getElementById('name').value = name;
    document.getElementById('firstName').value = firstName;
    document.getElementById('lastName').value = lastName;
    document.getElementById('phoneNumber').value = phoneNumber;
    document.getElementById('email').value = email;
    document.getElementById('rfidCode').value = rfidCode;
    document.getElementById('space').value = space;
    document.getElementById('ingressDate').value = ingressDate;
    document.getElementById('egressDate').value = egressDate;
    document.getElementById('ingressTime').value = ingressTime;
    document.getElementById('egressTime').value = egressTime;
    document.getElementById("idUser").value = id;

}
function showDeleteModal(boton) {
 myModal = new bootstrap.Modal(document.getElementById('deleteModal'), {
          keyboard: false
        })
        myModal.show()
        let fila = boton.parentNode.parentNode;
        let celdas = fila.getElementsByTagName("td");
        let id = celdas[0].innerHTML;
        let btnDeleteClient = document.getElementById("btn-delete");
        btnDeleteClient.setAttribute("data-id", id);
}


let btnDeleteClient = document.getElementById("btn-delete");
btnDeleteClient.addEventListener("click", function() {
    (async () => {
        try {
            let idUser      = this.getAttribute("data-id");
            let bodyRequest = idUser;

            const url = '/deleteClient';
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

let btnUpdateTable = document.getElementById('btnUpdateTable');
btnUpdateTable.addEventListener("click", getUserList);

async function getUserList() {
    const url = '/getClientList';
    let bodyRequest = {}
    const options = getOptionsForPostFetch(bodyRequest);
    const data = await fetchData(url, options);
    console.log('data', data);
    let userTableBody = document.getElementById('userTableBody');
    //Elimino el contenido de la tabla
    document.getElementById('userTableBody').innerHTML = '';
    let tableBody = "";
    for (let client of data.message) {
        tableBody += `
            <tr id="${client.id}">
                <td class="" >${client.id}</td>
                <td class="" >${client.name}</td>
                <td class="" >${client.firstName}</td>
                <td class="" >${client.lastName}</td>
                <td class="" >${client.phoneNumber}</td>
                <td class="" >${client.email}</td>
                <td class="" >${client.rfidCode}</td>
                <td class="" >${client.space}</td>
                <td> ${client.admissionDate}</td>
                <td>${client.egressDate}</td>
                <td>${client.admissionTime}</td>
                <td>${client.egressTime}</td>
                <td th:id="${'userUpdate-' + client.id}"><button class="btn-info" onclick="showModal(this)" >Editar</button></td>
                <td th:id="${'userDelete-' + client.id}"><button class="btn-danger" onclick="showDeleteModal(this)">Eliminar</button></td>
            </tr>
        `;
    }
    // Añado el contenido de 'tableBody' a la tabla
    document.getElementById('userTableBody').innerHTML = tableBody;
}