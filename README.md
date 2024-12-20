# Servicio REST de Información de Clientes

## Descripción
Este proyecto es un servicio REST desarrollado con **Java Spring Boot** que permite consultar la información básica de un cliente. La aplicación utiliza datos quemados (sin conexión a base de datos) y está diseñada para manejar códigos de respuesta HTTP y errores comunes.

## Características
- Manejo de códigos HTTP:
  - **200**: Petición exitosa.
  - **400**: Datos de entrada inválidos.
  - **404**: Cliente no encontrado.
  - **500**: Error interno del servidor.
- Validación de datos de entrada:
  - Tipo de documento: Solo se aceptan **C** (Cédula de Ciudadanía) y **P** (Pasaporte).
  - Número de documento: Obligatorio.
- Datos retornados:
  - Primer Nombre
  - Segundo Nombre
  - Primer Apellido
  - Segundo Apellido
  - Teléfono
  - Dirección
  - Ciudad de Residencia
- Manejo de logs para seguimiento.
- Control de excepciones centralizado fuera del controlador.

## Requisitos
- **Java 17**
- **Maven 3.9.4**
- **Spring Boot 3.4**

## Instalación
1. Acceder al directorio del proyecto:
   ```bash
   cd servicio-clientes
   ```
2. Construir el proyecto:
   ```bash
   mvn clean install
   ```
3. Ejecutar la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## Uso
El servicio está disponible en el endpoint:
```
GET /api/clientes?tipoDocumento={tipo}&numeroDocumento={numero}
```
### Ejemplo de petición
```bash
curl -X GET "http://localhost:8090/api/clientes?tipoDocumento=C&numeroDocumento=23445322"
```
### Ejemplo de respuesta
**HTTP 200**
```json
{
  "tipoDocumento": "C",
  "numeroDocumento": "23445322",
  "primerNombre": "Jhenny",
  "segundoNombre": "Maria",
  "primerApellido": "Pérez",
  "segundoApellido": "Gómez",
  "telefono": "831297",
  "direccion": "Calle del foquito Puerta cafe",
  "ciudadResidencia": "Macondo"
}
```

### Ejemplo de errores
**HTTP 400**: Datos de entrada inválidos
```json
{
    "message": "El tipo de documento debe ser 'C' (Cédula) o 'P' (Pasaporte).",
    "status_code": 400,
    "uri": "/api/clientes"
}
```

**HTTP 404**: Cliente no encontrado
```json
{
    "message": "Cliente no encontrado.",
    "status_code": 404,
    "uri": "/api/clientes"
}
```

**HTTP 500**: Error interno del servidor
```json
{
    "message": "Error interno del servidor. - Required request parameter 'numeroDocumento' for method parameter type String is not present",
    "status_code": 500,
    "uri": "/api/clientes"
}
```
## Configuración de Mensajes
Los mensajes del sistema están centralizados en el archivo `messages.properties`, ubicado en el directorio `src/main/resources`.

## Contacto
En caso de preguntas o sugerencias:
- **Autor**: [Rodrigo Soto Piedrahita]
- **Correo**: [roso_p@yahoo.com]

