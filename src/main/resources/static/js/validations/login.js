
let boton = document.getElementById("sendLoginRequest");

boton.addEventListener('click', function(event) {
  (async () => {
    try {
        let user = document.getElementById("user");
        let password = document.getElementById("password");
        debugger;
        let loginRequest = {};
        loginRequest.emailUser = user.value;
        loginRequest.password = forge_sha256(password.value);

        const url = '/login';
        const requestBody = loginRequest;

        const options = {
            method: 'POST', // Método de la solicitud (POST en este caso)
            headers: {
              'Content-Type': 'application/json', // Tipo de contenido esperado
              // Puedes agregar más encabezados aquí si es necesario
            },
            body: JSON.stringify(requestBody), // Convertimos el objeto a formato JSON
            // Otros parámetros de configuración como mode, cache, etc., según lo necesites
        };

        const data = await fetchData(url, options);
        debugger
        console.log('Datos obtenidos:', data);
        if (data.status == 200) {
            location.replace("/index");
        } else {
            Swal.fire({
              title: 'Error',
              text: data.error,
              icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
              confirmButtonText: 'OK' // Texto del botón de confirmación
            });
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
});