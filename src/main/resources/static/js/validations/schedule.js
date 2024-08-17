let btnAddSchedule = document.getElementById("btn-schedule");
let myModal;
btnAddSchedule.addEventListener("click", function () {
    myModal = new bootstrap.Modal(document.getElementById('modalSchedule'), {
        keyboard: false
    })
    document.getElementById('startDate').value = "";
    document.getElementById('endDate').value = "";
    document.getElementById('startHour').value = "";
    document.getElementById('endHour').value = "";
    document.getElementById('client').value = "";
    document.getElementById('spaceType').value = "";
    document.getElementById('rfidCode').value = "";
    document.getElementById("idSchedule").value = null;
    myModal.show()
})

let btnSaveSchedule = document.getElementById("btn-confirmar");
btnSaveSchedule.addEventListener("click", function () {
    (async () => {
        try {
            let idSchedule = document.getElementById("idSchedule");
            let startDate = document.getElementById('startDate');
            let endDate = document.getElementById('endDate');
            let startHour = document.getElementById('startHour');
            let endHour = document.getElementById('endHour');
            let client = document.getElementById('client');
            let spaceType = document.getElementById('spaceType');
            let rfidCode = document.getElementById('rfidCode');

            if (startDate.value == '' || endDate.value == '' || startHour.value == '' || endHour == '' || client == '' || spaceType == '') {
                Swal.fire({
                    title: 'Error',
                    text: 'Llene todos los campos por favor!',
                    icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
                    confirmButtonText: 'OK' // Texto del botón de confirmación
                });
                return false;
            }
            let bodyRequest = {};
            bodyRequest.id = idSchedule.value;
            bodyRequest.idClient = client.value;
            bodyRequest.startDate = startDate.value;
            bodyRequest.endDate = endDate.value;
            bodyRequest.startHour = startHour.value;
            bodyRequest.endHour = endHour.value;
            bodyRequest.idSpace = spaceType.value;
            bodyRequest.rfidCode = rfidCode.value;
            console.log("Enviando datos a la api");
            console.log(bodyRequest);
            const url = '/schedule';
            const options = getOptionsForPostFetch(bodyRequest);
            console.log(options);
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
    document.getElementById("exampleModalLabel").textContent = "Editar Horario";
    myModal = new bootstrap.Modal(document.getElementById('modalSchedule'), {
        keyboard: false
    })
    myModal.show()
    let fila = boton.parentNode.parentNode;
    let celdas = fila.getElementsByTagName("td");
    let id = celdas[0].innerHTML;
    let startDate = celdas[1].innerHTML;
    let endDate = celdas[2].innerHTML;
    let startHour = celdas[3].innerHTML;
    let endHour = celdas[4].innerHTML;
    let client = celdas[5].innerHTML;
    let spaceType = celdas[6].innerHTML;
    let rfidCode = celdas[7].innerHTML;

    document.getElementById("idSchedule").value = id;
    document.getElementById('startDate').value = startDate;
    document.getElementById('endDate').value = endDate;
    document.getElementById('startHour').value = startHour;
    document.getElementById('endHour').value = endHour;
    document.getElementById('client').value = client;
    document.getElementById('spaceType').value = spaceType;
    document.getElementById('rfidCode').value = rfidCode;
}

function showDeleteModal(boton) {
    myModal = new bootstrap.Modal(document.getElementById('deleteModal'), {
        keyboard: false
    })
    myModal.show()
    let fila = boton.parentNode.parentNode;
    let celdas = fila.getElementsByTagName("td");
    let id = celdas[0].innerHTML;
    let btnDeleteSchedule = document.getElementById("btn-delete");
    btnDeleteSchedule.setAttribute("data-id", id);
}


let btnDeleteSchedule = document.getElementById("btn-delete");
btnDeleteSchedule.addEventListener("click", function () {
    (async () => {
        try {
            let idUser = this.getAttribute("data-id");
            let bodyRequest = idUser;

            const url = '/deleteSchedule';
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
    const url = '/getScheduleList';
    let bodyRequest = {}
    const options = getOptionsForPostFetch(bodyRequest);
    const data = await fetchData(url, options);
    console.log('data', data);
    let userTableBody = document.getElementById('scheduleTableBody');
    //Elimino el contenido de la tabla
    document.getElementById('scheduleTableBody').innerHTML = '';
    let tableBody = "";
    for (let schedule of data.message) {
        tableBody += `
            <tr id="${schedule.id}">
                <td class="" >${schedule.id}</td>
                <td class="" >${schedule.startDate}</td>
                <td class="" >${schedule.endDate}</td>
                <td class="" >${schedule.startHour}</td>
                <td class="" >${schedule.endHour}</td>
                <td class="" >${schedule.idClient}</td>
                <td class="" >${schedule.idSpace}</td>
                <td class="" >${schedule.rfidCode}</td>
                <td th:id="${'scheduleUpdate-' + schedule.id}"><button class="btn-info" onclick="showModal(this)" >Editar</button></td>
                <td th:id="${'scheduleDelete-' + schedule.id}"><button class="btn-danger" onclick="showDeleteModal(this)">Eliminar</button></td>
            </tr>
        `;
    }
    // Añado el contenido de 'tableBody' a la tabla
    document.getElementById('scheduleTableBody').innerHTML = tableBody;
}


async function fetchUsers(query) {
    try {
        const response = await fetch(`/completeClientList/${query}`);
        if (!response.ok) {
            throw new Error('Error en la petición a la API');
        }
        const clients = await response.json();
        console.log(clients);
        displaySuggestions(clients.message);
    } catch (error) {
        console.error('Error:', error);
    }
}

function displaySuggestions(clients) {
    const suggestionsSelect = document.getElementById('suggestions');
    suggestionsSelect.innerHTML = '';
    suggestionsSelect.style.display = 'block';

    clients.forEach(client => {
        const option = document.createElement('option');
        option.value = client.name;
        option.textContent = `${client.name} ${client.firstName} - ${client.email}`;
        suggestionsSelect.appendChild(option);
    });

    suggestionsSelect.addEventListener('change', function() {
        const selectedOption = this.options[this.selectedIndex];
        document.getElementById('client').value = selectedOption.value;
        suggestionsSelect.style.display = 'none';
    });
}