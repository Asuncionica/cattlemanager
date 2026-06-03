# 🐄 CattleManager

*CattleManager* es una aplicación multiplataforma diseñada para la gestión digital de explotaciones ganaderas.  
Este proyecto ha sido desarrollado como *Proyecto de Fin de Ciclo del Grado Superior en Desarrollo de Aplicaciones Multiplataforma (DAM)*.

La aplicación permite centralizar la información de granjas, animales, eventos sanitarios y reproductivos, facilitando la gestión y trazabilidad del ganado.

---

# 🎯 Objetivo del proyecto

El objetivo principal de *CattleManager* es desarrollar una solución digital basada en una *arquitectura cliente-servidor mediante API REST*, que permita gestionar explotaciones ganaderas de forma eficiente.

La aplicación busca:

- Digitalizar la gestión de granjas
- Centralizar la información del ganado
- Mejorar la trazabilidad animal
- Facilitar el control sanitario y reproductivo
- Gestionar usuarios con distintos roles

---

# 🏗 Arquitectura del sistema

El sistema está compuesto por tres componentes principales:

### Backend
Servidor desarrollado en *Java con Spring Boot* que proporciona una *API REST* para la gestión de datos.

### Aplicación móvil
Aplicación Android desarrollada en *Kotlin*, que consume la API del backend.

### Base de datos
Sistema de persistencia basado en *base de datos relacional (PostgreSQL o MySQL)*.

---

# ⚙️ Tecnologías utilizadas

## Backend
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- API REST

## Base de datos
- PostgreSQL / MySQL

## Aplicación móvil
- Kotlin
- Android Studio

## Herramientas
- Git
- GitHub
- Postman
- IntelliJ IDEA / VS Code

---

# 📊 Funcionalidades principales

## Gestión de granjas
- Registro de explotaciones ganaderas
- Modificación de datos
- Consulta de información
- Eliminación o baja de granjas

## Gestión de animales
- Registro de animales
- Consulta de historial
- Cambio de estado (activo, vendido, fallecido)
- Registro de genealogía

## Gestión sanitaria
- Registro de vacunaciones
- Registro de desparasitaciones
- Registro de enfermedades
- Registro de tratamientos

## Gestión reproductiva
- Registro de celo
- Registro de servicio
- Registro de parto
- Asociación de crías

## Gestión de usuarios
- Sistema de autenticación
- Control de acceso por roles
- Gestión de permisos según el rol del usuario

---

# 🗄 Modelo de datos

El sistema se basa en una base de datos relacional que incluye las siguientes entidades principales:

- Granja
- Usuario
- Rol
- Animal
- EventoSanitario
- EventoReproductivo
- EventoProductivo

---

# 🚀 Instalación del proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/tuusuario/cattlemanager.git
