# DSY1103-RedSaludPatag-nica
Proyecto Semestral EP2 — DSY1103 — RedSaludPatagónica

# RedSaludPatagónica — DSY1103 Desarrollo FullStack 1

## Descripción
RedSaludPatagónica es una organización privada sin fines de lucro que gestiona una red de postas
rurales y centros de salud familiar (CESFAM) en territorios aislados de las provincias de Chiloé y
Palena. Opera 8 postas rurales en localidades de difícil acceso como Melinka, Raúl Marín
Balmaceda, Caleta Tortel y Puerto Gala, más un CESFAM en Chaitén y un hospital de baja
complejidad en Futaleufú.
Las postas son atendidas por 1 o 2 TENS (Técnicos en Enfermería) que dependen médicamente de
médicos remotos en Chaitén. El sistema de telemedicina está siendo implementado, y requiere una
plataforma que coordine las consultas remotas, el stock de medicamentos en cada posta, el traslado
de pacientes cuando es necesario (en avioneta o lancha) y el registro de las fichas clínicas de una
población rural dispersa.

El sistema resuelve la dificultad de acceso a la atención médica para adultos mayores
y personas que no cuentan con RUT, permitiendo su registro mediante datos alternativos como nombre, 
dirección y fecha de nacimiento.


## Equipo
| Nombre | GitHub |
|--------|--------|
| Raúl Ferrini | @Potasio230 |
| Francisco Molina | @FcoINF |


## Microservicios Implementados
| #  | Microservicio      | Puerto | Descripción                                                                                                   |
|----|--------------------|--------|---------------------------------------------------------------------------------------------------------------|
| 1  | ms-profesionales   | 8081   | Profesionales capacitados para asistir a los pacientes de manera exitosa.                                     |
| 2  | ms-pacientes       | 8082   | Usuarios del centro de salud que necesitan de los servicios profesionales.                                    |
| 3  | ms-postas          | 8083   | Centro de salud con implementaciones necesarias e insumos para la comunidad.                                  |
| 4  | ms-recetas         | 8084   | Preinscripciones en base a consultas médicas enfocadas en solventar problemáticas.                            |
| 5  | ms-consultas       | 8085   | Registro de consultas médicas, presenciales y remotas para los pacientes.                                     |
| 6  | ms-registro-fichas | 8086   | Fichas clínicas con información de vital importancia, junto con antecedentes médicos.                         |
| 7  | ms-derivaciones    | 8087   | Traslado a centros clínicos de mayor complejidad para atender situaciones de riesgo vital.                    |
| 8  | ms-traslados       | 8088   | Medio de traslado para los pacientes y rutas designadas para llegar al destino (avioneta, lancha, ambulancia) |
| 9  | ms-programas       | 8089   | Programas preventivos eventuales para cada situación de salubridad (vacunas, controles, etc).                 |
| 10 | ms-alertas         | 8090   | Alertas vitales en epidemias, stock crítico, situaciones de riesgo vital y vencimiento de medicamentos.       |
| 11 | ms-farmacias       | 8091   | Centro de distribución de medicamentos para los locatarios en base a ms-recetas.                              |



## Tecnologías Utilizadas
- Java 17 / Spring Boot 3.x
- JPA + Hibernate
- MySQL / PostgreSQL
- WebClient / Feign Client
- SLF4J para logs

## Cómo Ejecutar el Proyecto
1. Clonar el repositorio: `git clone [URL]`
2. Configurar la base de datos en `application.properties`
3. Ejecutar cada microservicio: `./mvnw spring-boot:run`

## Estado del Proyecto
🔄 En desarrollo — EP2 2025

