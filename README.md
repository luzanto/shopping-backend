# shopping-backend
Backend de la iteración 0. Mingeso 2018-1. Grupo 6 Seccion A-1

## Pasos para levantar el ambiente de integración continua.

Obtener las herramientas necesarias:
- [Instalar docker] (https://docs.docker.com/install/linux/docker-ce/ubuntu/).
- [Instalar docker-compose] (https://docs.docker.com/compose/install/#install-compose).

Clonar el repositorio github:
```
git clone https://github.com/luzanto/shopping-backend
cd shopping-backend
```
Ejecutar el comando de docker-compose y esperar que se descarguen las imagenes y que inicie el sistema:
```
docker-compose up
```
Este comando solo instala Jenkins y SonarQube, para levantar el sistema completo (Testlink, Selenium (Zalenium), Mantis Bug Tracker, Jenkins y SonarQube) usar el siguiente comando (**ADVERTENCIA: Este comando no ha sido probado adecuadamente**):
```
docker-compose -f docker-compose-complete up
```

### Configurar el Jenkins para que funcione SonarQube:

#### Agregar el SonarQube Scanner
- Manage Jenkins -> Global Tool Configuration -> SonarQube Scanner
- Apretar Add SonarQube Scanner:
- Name: sonarRunner
- Install automatically
- Save

#### Agregar el servidor de SonarQube
- Manage Jenkins -> Configure System -> SonarQube servers
- Checkear "Enable injection of SonarQube server configuration as build environment variables"
- Add SonarQube
- Name: SonarServer
- Server URL: http://sonarqube:9000
- Server Authentication Token: Se debe obtener ingresando a SonarQube en http://localhost:9000

### Ejecutar la pipeline en Jenkins (guardada en Jenkinsfile)

Entrar al proyecto carro_compras y apretar el botón "Build Now".

Para ver la ejecución de la pipeline se recomienda entrar a Blue Ocean usando el botón en la barra lateral.

La primera vez que se ejecute el proyecto tarda más porque Jenkins debe primero descargar todas las dependencias.
