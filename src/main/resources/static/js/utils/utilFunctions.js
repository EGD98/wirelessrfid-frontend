async function fetchData(url, options) {
debugger
  try {
    const response = await fetch(url, options); // Pasamos las opciones al método fetch
    const data = await response.json(); // Espera a que los datos se conviertan en formato JSON
    return data; // Devuelve los datos obtenidos
  } catch (error) {
    console.error('Error al obtener los datos:', error);
    Swal.fire({
      title: 'Error',
      text: error,
      icon: 'error', // Icono (puede ser 'success', 'error', 'warning', 'info' o 'question')
      confirmButtonText: 'OK' // Texto del botón de confirmación
    });
    throw error; // Propaga el error para que sea manejado fuera de esta función
  }
}

function getOptionsForPostFetch(requestBody) {
    const options = {
        method: 'POST', // Método de la solicitud (POST en este caso)
        headers: {
          'Content-Type': 'application/json', // Tipo de contenido esperado
          // Puedes agregar más encabezados aquí si es necesario
        },
        body: JSON.stringify(requestBody), // Convertimos el objeto a formato JSON
        // Otros parámetros de configuración como mode, cache, etc., según lo necesites
    };
    return options;
}

