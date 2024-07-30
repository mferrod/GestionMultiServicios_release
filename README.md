# GestionMultiServicios - License

 Gestión MultiServicios © 2024 by Mariano Fernández Rodero is licensed under Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International. To view a copy of this license, visit https://creativecommons.org/licenses/by-nc-nd/4.0/


# GestionMultiServicios - Mariano Fernández Rodero

## Explicación:
### ES 🇪🇸
Este proyecto es el TFG que he realizado para la finalización del grado superior de Desarrollo de Aplicaciones Multiplataforma.
Dicho proyecto consta de las tecnologías Java, JavaFX, SpringBoot, MySQL y Arduino. El proyecto va de una aplicación multiplataforma para la gestión y facturación de una empresa y aparte tiene su propio localizador (con arduino) para saber todo el tiempo la posición de su flota de vehículos.

Si quieres usar este proyecto, en el application.properties (API), deberás cambiar el campo YOUR_OWN_API_KEY por un texto que tu quieras, también los datos de acceso a tu base de datos para que pueda acceder a todos tus datos de clientes, facturas, productos. A parte,deberás cambiar YOUR_IP_SERVER por tu ip de tu servidor, y en los header de cada una de las peticiones crear tu propia API_KEY que está distinguido con el nombre YOUR_OWN_API_KEY y cambiarlo por el que hayas puesto en el API.

El proyecto está acabado, pero aún falta rematar algunas cosas, que se irá actualizando y cambiando a medida que se vaya realizando los cambios.

### EN 🇬🇧/🇺🇸

This project is the TFG that he completed for the completion of the higher degree in Multiplatform Application Development.
This project consists of Java, JavaFX, SpringBoot, MySQL and Arduino technologies. The project is a multi-platform application for the management and billing of a company and it also has its own locator (with Arduino) to know the position of its fleet of vehicles at all times.

If you want to use this project, in the application.properties (API), you must change the YOUR_OWN_API_KEY field with a text that you want, also the access data to your database so that you can access all your customer data, invoices, products. Additionally, you must change YOUR_IP_SERVER to your server's IP, and in the header of each of the requests create your own API_KEY that is distinguished with the name YOUR_OWN_API_KEY and change it to the one you have put in the API.

The project is finished, but some things still need to be finished, which will be updated and changed as the changes are made.

# RoadMAP

## 1.0.1v
* Arreglado un bug en la APP de android a la hora de ver los productos de la factura. 

## 1.0.0v
* APP ACABADA

## 0.9.5v
* Añadida clase Launcher a la App de escritorio para poder iniciarlizarla.

## 0.9.4v
* Arreglado bug con el RecyclerView de los clientes. (Se duplicaba).
* Arreglado bug en la vista ModificarClienteActivity, el botón de salir no funcionaba.

## 0.9.3v
* Añadida contraseña automatizada y enviada al correo del usuario.
* Añadidas comprobaciones para evitar la duplicación de correos.
* Añadidos fechas de logueo.

## 0.9.2v
* API securizada.
* Añadido el acceso a API a la app de Escritorio.
* Añadido el acceso a API a la app de Android.
* Añadido el acceso a API al GPS.

## 0.9.1.1v
* Corregida errata en el nombre de la imagen para la factura.

## 0.9.1v
* Retocada la factura, ahora tiene logos y está maquillada.
* Cambiado el logo de la APP de Android.

## 0.9.0v
* Aplicación de Escritorio terminada.
* API terminada. (FALTA SEGURIDAD QUE SE IMPLEMENTARÁ EN LAS PRÓXIMA VERSIONES).
* Aplicación de Android terminada. (Falta la revisión)
* Programa de Arduino terminado y funcionando.

## 0.5.0v
* Cambiadas todas las peticiones a la URL del servidor. (Escritorio)
* Creada la vista `LoginActivity` en la app. (Android)
* Creada la clase `LoginController` en la app. (Android)
* Creada la clase `Peticion` en la app. (Android)

## 0.4.6v
* Corregido un fallo a la hora de mostrar las facturas en la vista `gms_facturacion_main.fxml`.

## 0.4.5v
* Corregido un error al mostrar la información de la factura en la vista `gms_facturacion_main.fxml`.
* Ahora cuando se hace una factura, se actualizan los datos en la BBDD.
* Desde la vista ver, ahora se puede eliminar facturas.
* Desde la vista ver, ahora se puede modificar facturas.
* Añadidos nuevos métodos para las clases `Peticion` y `Respuesta`.
* Añadidos nuevos `EndPoints` a la API.
* Creada la clase `GmsPDFController` para la realización de PDFs.
* Creada una interfaz para una optimización de líneas de código.
* Añadidos 2 nuevos módulos para la realización de PDFs.
* Ahora se pueden hacer PDFs en la vista de ver facturas.
* Cambiado el modo para ver el id de la factura ( se le ha añadido la hora y la fecha).
* Modificada la vista `gms_facturacion_ver_factura.fxml`.

## 0.4.4v
* Empezada la vsita `gms_facturacion_ver_facturas.fxml`.
* Corregido un fallo en la clase `GmsFacturacionMain`.

## 0.4.3v
* Corregido un problema con la API para subir la factura en la BBDD.
* Corregido el listview para mostrar las facturas en la vista `gms_facturacion_main.fxml`.
* Corregido un problema con los decimales a la hora de mostrar la información de la factura en la vista `gms_facturacion_main.fxml`.
* Vista crear facturas terminada. 


## 0.4.2v
* Vista crear facturas casi terminada.
* Añadidos nuevos métodos para las clases Respuesta y Petición.

## 0.4.1v
* Empezada la vista para crear facturas. (Escritorio)

## 0.4.0v
* Cambiadas todas las vistas a JavaFX 21.
* Añadidas en la API dos nuevas funciones para recibir y subir facturas.
* Terminada la vista main de facturación. (Escritorio).
* Creado el modelo `ProductoFactura`.
* Creada un nuevo método en la clase `Petición`. (Escritorio)
* Creada un nuevo método en la clase `Respuesta`. (Escritorio)
* Añadida información de la factura en la vista. (Escritorio)
* Creado el modelo `InfoFactura`.
* Creada una clase `AlertController` para optimizar la cantidad de código en otras clases. (Escritorio)
* Añadido el botón `Crear Factura` para la vista `gms_facturacion_main.fxml`. (Escritorio)
* Creada la vista `gms_facturacion_crear_factura.fxml`. (Escritorio)
* Modificada la vista `gms_facturacion_main.fxml`. (Escritorio)
* Añadido al `Navigator` un nuevo método. (Escritorio)

## 0.3.2v
* Añadido un indicativo en la coordenada exacta donde está el vehículo para la vista `gms_vehLocation.fxml`.

## 0.3.1v
* Realizados cambios varios en el docker-compose para securizar el intento de conexiones maliciosas.
* Añadido un `Dockerfile`.
* Añadida protección ssl al tomcat.


## 0.3.0v
* Creados todos los `EndPoint` para la vista de GPS para la APP. (API)
* Creada clase con `TimerTask` para cada 5 segundos pedir la posición a la API.
* Ajustado el mapa de la vista para mostrar la posición exacta del vehículo.
* Optimizada el método de obtener las coordenadas.
* Eliminado el botón de recargar de la vista de `gms_veh_location.fxml`

## 0.2.1v
* Añadido botón salir en la vista de modificar producto.

## 0.2.0v
* Creados todos los `EndPoint` para las vistas de Gestión para la APP. (API)
* Creados todos los métodos en la clase `Petición` de Gestión para la APP. (Escritorio)
* Creados todos los métodos en la clase `Respuesta` de Gestión para la APP. (Escritorio)
* Creada la clase `Navigator` con Singleton, para ahorro de código para la APP. (Escritorio)
* Creados todos los controladores con `Singleton` para todas las clases de `Gestión` para la APP. (Escritorio)
* Creados dos nuevos modelos `Proveedor` y `Producto` para un mejor manejor de las clases de `Gestión` para la APP. (Escritorio)
* Apartado de gestión funcionando correctamente.

## 0.1.0v
* Creado y funcionando el Login del cliente y API. (Escritorio)
* Creado y funcionando el menú principal de la APP. (Escritorio)
* Creada la vista para la parte de Gestión de la APP. (Escritorio)
* Creada la vista para la parte de Facturación de la APP. (Escritorio)
* Creada la vista para la parte de GPS de la APP. (Escritorio)
* Creada y funcionando la visa de Salir de la APP. (Escritorio)
* Creados los modelos necesarios hasta ahora de la APP. (Escritorio)

## 0.0.0v
* Creado proyecto IntelliJ con Spring Boot para la API.
* Creado proyecto IntelliJ con JavaFX para la APP de escritorio.
* Creado proyecto Android Stuido para la APP de móvil.
* Creado docker-compose.yml para la puesta en marcha del servidor (Tomcat y MariaDB).
