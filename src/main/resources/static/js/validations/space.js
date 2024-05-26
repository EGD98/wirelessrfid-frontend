let btnAddSpace = document.getElementById("btn-space");
let myModal;
btnAddSpace.addEventListener("click", function() {
    myModal = new bootstrap.Modal(document.getElementById('spaceModal'), {
      keyboard: false
    })
    document.getElementById('roomName').value = "";
    document.getElementById('places').value = "";
    document.getElementById('registrationDate').value = "";
    document.getElementById("idSpace").value = null;
    myModal.show()
})

let btnSaveSpace = document.getElementById("btn-confirmar");
btnSaveSpace.addEventListener("click", function() {
    (async () => {
        try {
            let idSpace             = document.getElementById("idSpace");
            let roomName            = document.getElementById('roomName');
            let places              = document.getElementById('places');
            let registrationDate    = document.getElementById('registrationDate');
            if (roomName.value == '' || places.value == '' || registrationDate.value == '') {
                Swal.fire({
                    title: 'Error',
                    text: 'Llene todos los campos por favor!',
                    icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                    confirmButtonText: 'OK' // Texto del botón de confirmación
                });
                return false;
            }
            let bodyRequest = {};
            bodyRequest.id = idSpace.value;
            bodyRequest.roomName = roomName.value;
            bodyRequest.places = places.value;
            bodyRequest.registrationDate = registrationDate.value;

            const url = '/space';
            const options = getOptionsForPostFetch(bodyRequest);
            const data = await fetchData(url, options);
            console.log('data', data);
            if (data.status != 200) {
                Swal.fire({
                    title: 'Error',
                    text: 'Ha ocurrido un error inesperado intentelo mas tarde',
                    icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                    confirmButtonText: 'OK' // Texto del botón de confirmación
                });
            } else {
                Swal.fire({
                    title: 'Exito',
                    text: data.message,
                    icon: 'success', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                    confirmButtonText: 'OK' // Texto del botón de confirmación
                });
                myModal.hide();
            }
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
    document.getElementById("exampleModalLabel").textContent = "Editar Espacio";
    myModal = new bootstrap.Modal(document.getElementById('spaceModal'), {
          keyboard: false
        })
        myModal.show()
    let fila = boton.parentNode.parentNode;
    let celdas = fila.getElementsByTagName("td");
    let id = celdas[0].innerHTML;
    let roomName = celdas[1].innerHTML;
    let places = celdas[2].innerHTML;
    let registrationDate = celdas[3].innerHTML;

    document.getElementById('roomName').value = roomName;
    document.getElementById('places').value = places;
    document.getElementById('registrationDate').value = registrationDate;
    document.getElementById("idSpace").value = id;

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

            const url = '/deleteSpace';
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
    const url = '/getSpaceList';
    let bodyRequest = {}
    const options = getOptionsForPostFetch(bodyRequest);
    const data = await fetchData(url, options);
    console.log('data', data);
    let userTableBody = document.getElementById('spaceTableBody');
    //Elimino el contenido de la tabla
    document.getElementById('spaceTableBody').innerHTML = '';
    let tableBody = "";
    for (let space of data.message) {
        tableBody += `
            <tr id="${space.id}">
                <td class="" >${space.id}</td>
                <td class="" >${space.roomName}</td>
                <td class="" >${space.places}</td>
                <td class="" >${space.registrationDate}</td>
                <td th:id="${'spaceUpdate-' + space.id}"><button class="btn-info" onclick="showModal(this)" >Editar</button></td>
                <td th:id="${'spaceDelete-' + space.id}"><button class="btn-danger" onclick="showDeleteModal(this)">Eliminar</button></td>
            </tr>
        `;
    }
    // Añado el contenido de 'tableBody' a la tabla
    document.getElementById('spaceTableBody').innerHTML = tableBody;
}