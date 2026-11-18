# language: es
Característica: Flujo interactivo y recorrido clínico en SmartMedi
  Como usuario autorizado de la plataforma médica SmartMedi
  Quiero ingresar desde la página principal e interactuar con los módulos clínicos
  Para verificar el correcto despliegue de vistas, modelos 3D y expedientes

  Escenario: Recorrido interactivo del Médico por todos los módulos clínicos
    Dado que el usuario abre el navegador en la página principal de SmartMedi
    Cuando espera 4 segundos y hace clic en Ingresar al Sistema desde el inicio
    Y ingresa el usuario "saul" y la contraseña "1234"
    Y hace clic en el botón Ingresar al Sistema
    Entonces el sistema debe redirigirlo al panel principal del médico
    Cuando recorre los módulos médicos interactuando con vistas y deslizando 10 segundos por pantalla
    Entonces cierra la sesión del médico correctamente

  Escenario: Inicio de sesión exitoso con rol Farmacéutico
    Dado que el usuario abre el navegador en la página de inicio de sesión de SmartMedi
    Cuando ingresa el usuario "aracely" y la contraseña "1234"
    Y hace clic en el botón Ingresar al Sistema
    Entonces el sistema debe redirigirlo al panel principal del farmacéutico

  Escenario: Intento de inicio de sesión con credenciales incorrectas
    Dado que el usuario abre el navegador en la página de inicio de sesión de SmartMedi
    Cuando ingresa el usuario "saul" y la contraseña "clave_erronea"
    Y hace clic en el botón Ingresar al Sistema
    Entonces el sistema debe mantener al usuario en la pantalla de inicio de sesión