# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImplTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loan/LoanApplication.java` — `LoanApplicationRepository.count`: Se invoca `count` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `LoanApplicationRepository.findById`: Se invoca `findById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `LoanApplicationRepository.save`: Se invoca `save` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `Applicant.getCreditHistory`: Se invoca `getCreditHistory` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `Applicant.getMonthlyIncome`: Se invoca `getMonthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `CreditHistory.getCreditScore`: Se invoca `getCreditScore` sobre `CreditHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplication.setApplicationDate`: Se invoca `setApplicationDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.save`: Se invoca `save` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.findById`: Se invoca `findById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.findAll`: Se invoca `findAll` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplication.setApprovalDate`: Se invoca `setApprovalDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.deleteById`: Se invoca `deleteById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.fullName`: Se invoca `fullName` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.dateOfBirth`: Se invoca `dateOfBirth` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.monthlyIncome`: Se invoca `monthlyIncome` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.creditScore`: Se invoca `creditScore` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.totalDebt`: Se invoca `totalDebt` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.paymentHistory`: Se invoca `paymentHistory` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.requestedAmount`: Se invoca `requestedAmount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.termMonths`: Se invoca `termMonths` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.status`: Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicationDate`: Se invoca `getApplicationDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `LoanApplication.getApprovalDate`: Se invoca `getApprovalDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — `LoanApplicationService.getApplication`: Se invoca `getApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Trainee

### Brecha de conocimiento
Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. (Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas).

### Misión / candidato
Candidato en etapa Trainee en Backend con Java.

### Reto
- Tema: aplicación de patrones grasp en el desarrollo de sistemas
- Seniority: trainee-l2
- Tipo: practical
- Título: Diseño y Desarrollo de Sistema con Patrones GRASP
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Modelado Inicial del Dominio — objetivo: Definir las entidades y relaciones básicas del sistema. — entregable (NO resolver): Diagrama de clases inicial con entidades y relaciones.
- Fase 2: Implementación de la Lógica de Negocio — objetivo: Implementar la lógica para aprobar o rechazar solicitudes. — entregable (NO resolver): Código que implementa la lógica de aprobación/rechazo de solicitudes.
- Fase 3: Integración y Testing — objetivo: Integrar todas las partes del sistema y realizar pruebas. — entregable (NO resolver): Sistema integrado y funcionando con pruebas unitarias y de integración.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>
    
    <groupId>com.fintech</groupId>
    <artifactId>loan</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loan</name>
    <description>Sistema de gestión de solicitudes de préstamos personales</description>
    
    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
    
    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.30</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- Jakarta Persistence API -->
        <dependency>
            <groupId>jakarta.persistence</groupId>
            <artifactId>jakarta.persistence-api</artifactId>
            <version>3.1.0</version>
        </dependency>
        
        <!-- PostgreSQL -->
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/fintech/loan/LoanApplication.java ===
package com.fintech.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.domain.service.impl.LoanApprovalServiceImpl;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fintech.loan.infrastructure.repository")
@EnableTransactionManagement
@EnableConfigurationProperties
@EnableAsync
public class LoanApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(LoanApplication.class, args);
    }
    
    @Configuration
    static class AppConfig {
        
        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
        
        @Bean
        public LoanApprovalService loanApprovalService(LoanApplicationRepository loanApplicationRepository) {
            return new LoanApprovalServiceImpl(loanApplicationRepository);
        }
        
        @Bean
        public LoanApplicationService loanApplicationService(LoanApprovalService loanApprovalService,
                                                          LoanApplicationRepository loanApplicationRepository) {
            return new LoanApplicationService(loanApprovalService, loanApplicationRepository);
        }
    }
    
    @Bean
    public CommandLineRunner initData(LoanApplicationRepository repository) {
        return args -> {
            // Inicialización de datos de prueba si es necesario
            if (repository.count() == 0) {
                // Crear algunas solicitudes de préstamo de ejemplo
                // Esto es solo para fines de demostración y pruebas
            }
        };
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loan-service
    
  datasource:
    url: jdbc:postgresql://localhost:5432/loan_db
    username: postgres
    password: postgres
    driver-class-name: org.postgresql.Driver
    hikari:
      maximum-pool-size: 10
      minimum-idle: 2
      idle-timeout: 30000
      connection-timeout: 30000
      max-lifetime: 600000
    
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    
  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

server:
  port: 8080
  servlet:
    context-path: /api

logging:
  level:
    root: INFO
    com.fintech.loan: DEBUG
    org.springframework.web: INFO
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE

// === ARCHIVO: src/main/java/com/fintech/loan/domain/model/Applicant.java ===
package com.fintech.loan.domain.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class Applicant {
    private UUID id;

    @NotNull(message = "El nombre completo no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre completo debe tener entre 2 y 100 caracteres")
    private String fullName;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    private LocalDate dateOfBirth;

    @NotNull(message = "El ingreso mensual no puede ser nulo")
    @Positive(message = "El ingreso mensual debe ser positivo")
    private BigDecimal monthlyIncome;

    @NotNull(message = "El historial crediticio no puede ser nulo")
    private CreditHistory creditHistory;

    public Applicant() {
        this.id = UUID.randomUUID();
    }

    public Applicant(String fullName, LocalDate dateOfBirth, BigDecimal monthlyIncome, CreditHistory creditHistory) {
        this.id = UUID.randomUUID();
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.monthlyIncome = monthlyIncome;
        this.creditHistory = creditHistory;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth.getYear();
    }

    public boolean hasStableIncome() {
        return monthlyIncome.compareTo(new BigDecimal("1000")) >= 0;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/domain/model/CreditHistory.java ===
package com.fintech.loan.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class CreditHistory {
    private UUID id;

    @NotNull(message = "El puntaje crediticio no puede ser nulo")
    private Integer creditScore;

    @NotNull(message = "La deuda total no puede ser nula")
    private BigDecimal totalDebt;

    @NotNull(message = "El historial de pagos no puede ser nulo")
    private Integer paymentHistory;

    private LocalDate lastCreditCheckDate;

    public CreditHistory() {
        this.id = UUID.randomUUID();
    }

    public CreditHistory(Integer creditScore, BigDecimal totalDebt, Integer paymentHistory) {
        this.id = UUID.randomUUID();
        this.creditScore = creditScore;
        this.totalDebt = totalDebt;
        this.paymentHistory = paymentHistory;
        this.lastCreditCheckDate = LocalDate.now();
    }

    public boolean hasGoodCreditScore() {
        return creditScore != null && creditScore >= 700;
    }

    public boolean hasLowDebt() {
        return totalDebt != null && totalDebt.compareTo(new BigDecimal("5000")) < 0;
    }

    public boolean hasPositivePaymentHistory() {
        return paymentHistory != null && paymentHistory >= 0;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/domain/service/LoanApprovalService.java ===
package com.fintech.loan.domain.service;

import com.fintech.loan.domain.model.Applicant;

import java.util.UUID;

public interface LoanApprovalService {
    /**
     * Evalúa una solicitud de préstamo y determina si debe ser aprobada o rechazada.
     * @param loanApplicationId Identificador único de la solicitud de préstamo.
     * @return true si la solicitud es aprobada, false si es rechazada.
     */
    boolean evaluateLoanApplication(UUID loanApplicationId);

    /**
     * Calcula el monto máximo de préstamo que puede ser aprobado para un solicitante.
     * @param applicant Solicitante para el cual se calcula el monto.
     * @return Monto máximo aprobable.
     */
    double calculateMaximumLoanAmount(Applicant applicant);
}

// === ARCHIVO: src/main/java/com/fintech/loan/domain/model/LoanApplication.java ===
package com.fintech.loan.domain.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan_applications")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id", nullable = false)
    private Applicant applicant;

    @Column(name = "requested_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal requestedAmount;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatus status;

    @Column(name = "interest_rate", precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(name = "approved_amount", precision = 15, scale = 2)
    private BigDecimal approvedAmount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "rejection_reason")
    private String rejectionReason;

    public LoanApplication() {
        this.createdAt = LocalDateTime.now();
        this.status = LoanStatus.PENDING;
    }

    public LoanApplication(Applicant applicant, BigDecimal requestedAmount, Integer termMonths) {
        this();
        this.applicant = applicant;
        this.requestedAmount = requestedAmount;
        this.termMonths = termMonths;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getApprovedAt() {
        return approvedAt;
    }

    public void setApprovedAt(LocalDateTime approvedAt) {
        this.approvedAt = approvedAt;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public void approve(BigDecimal approvedAmount, BigDecimal interestRate) {
        this.status = LoanStatus.APPROVED;
        this.approvedAmount = approvedAmount;
        this.interestRate = interestRate;
        this.approvedAt = LocalDateTime.now();
    }

    public void reject(String reason) {
        this.status = LoanStatus.REJECTED;
        this.rejectionReason = reason;
    }

    public boolean isPending() {
        return this.status == LoanStatus.PENDING;
    }

    public boolean isApproved() {
        return this.status == LoanStatus.APPROVED;
    }

    public boolean isRejected() {
        return this.status == LoanStatus.REJECTED;
    }

    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java ===
package com.fintech.loan.domain.service.impl;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoanApprovalServiceImpl implements LoanApprovalService {

    private static final Logger logger = LoggerFactory.getLogger(LoanApprovalServiceImpl.class);

    private static final BigDecimal MIN_INCOME_THRESHOLD = new BigDecimal("500.0");
    private static final BigDecimal MAX_DEBT_TO_INCOME_RATIO = new BigDecimal("0.4");
    private static final BigDecimal MIN_CREDIT_SCORE = new BigDecimal("600");
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 70;
    private static final BigDecimal BASE_INTEREST_RATE = new BigDecimal("0.12");
    private static final BigDecimal EXCELLENT_INTEREST_RATE = new BigDecimal("0.08");
    private static final BigDecimal GOOD_INTEREST_RATE = new BigDecimal("0.10");

    private final LoanApplicationRepository repository;

    public LoanApprovalServiceImpl(LoanApplicationRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean evaluateLoanApplication(UUID loanApplicationId) {
        logger.info("Evaluando solicitud de préstamo con ID: {}", loanApplicationId);

        Optional<LoanApplication> optionalApplication = repository.findById(loanApplicationId);
        if (optionalApplication.isEmpty()) {
            logger.warn("No se encontró la solicitud de préstamo con ID: {}", loanApplicationId);
            return false;
        }

        LoanApplication application = optionalApplication.get();

        if (!application.isPending()) {
            logger.info("La solicitud {} ya fue procesada previamente", loanApplicationId);
            return application.isApproved();
        }

        Applicant applicant = application.getApplicant();

        if (!validateApplicantAge(applicant)) {
            application.reject("El solicitante no cumple con la edad requerida (18-70 años)");
            repository.save(application);
            logger.info("Solicitud {} rechazada: edad fuera del rango permitido", loanApplicationId);
            return false;
        }

        if (!validateIncomeStability(applicant)) {
            application.reject("El solicitante no tiene ingresos estables suficientes");
            repository.save(application);
            logger.info("Solicitud {} rechazada: ingresos insuficientes", loanApplicationId);
            return false;
        }

        if (!validateCreditHistory(applicant.getCreditHistory())) {
            application.reject("El historial crediticio no cumple con los requisitos mínimos");
            repository.save(application);
            logger.info("Solicitud {} rechazada: historial crediticio insuficiente", loanApplicationId);
            return false;
        }

        if (!validateDebtToIncomeRatio(applicant)) {
            application.reject("La relación deuda/ingresos excede el límite permitido");
            repository.save(application);
            logger.info("Solicitud {} rechazada: deuda/ingresos muy alta", loanApplicationId);
            return false;
        }

        BigDecimal maximumAmount = calculateMaximumLoanAmount(applicant);
        BigDecimal requestedAmount = application.getRequestedAmount();

        if (requestedAmount.compareTo(maximumAmount) > 0) {
            application.reject("El monto solicitado excede el límite máximo aprobado: " + maximumAmount);
            repository.save(application);
            logger.info("Solicitud {} rechazada: monto solicitado {} mayor al máximo {}", 
                loanApplicationId, requestedAmount, maximumAmount);
            return false;
        }

        BigDecimal approvedAmount = requestedAmount;
        BigDecimal interestRate = determineInterestRate(applicant.getCreditHistory());

        application.approve(approvedAmount, interestRate);
        repository.save(application);

        logger.info("Solicitud {} aprobada por monto {} con tasa de interés {}%", 
            loanApplicationId, approvedAmount, interestRate.multiply(BigDecimal.valueOf(100)));
        return true;
    }

    @Override
    public double calculateMaximumLoanAmount(Applicant applicant) {
        if (applicant == null || applicant.getMonthlyIncome() == null) {
            return 0.0;
        }

        BigDecimal monthlyIncome = applicant.getMonthlyIncome();
        CreditHistory creditHistory = applicant.getCreditHistory();

        BigDecimal baseMultiplier = new BigDecimal("10");
        BigDecimal creditScoreBonus = BigDecimal.ZERO;

        if (creditHistory != null) {
            if (creditHistory.hasGoodCreditScore()) {
                creditScoreBonus = new BigDecimal("5");
            }
            if (creditHistory.hasGoodCreditScore() && creditHistory.hasLowDebt()) {
                creditScoreBonus = new BigDecimal("8");
            }
            if (creditHistory.hasPositivePaymentHistory()) {
                creditScoreBonus = creditScoreBonus.add(new BigDecimal("2"));
            }
        }

        BigDecimal totalMultiplier = baseMultiplier.add(creditScoreBonus);
        BigDecimal maximumAmount = monthlyIncome.multiply(totalMultiplier);

        return maximumAmount.setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private boolean validateApplicantAge(Applicant applicant) {
        int age = applicant.getAge();
        return age >= MIN_AGE && age <= MAX_AGE;
    }

    private boolean validateIncomeStability(Applicant applicant) {
        return applicant.hasStableIncome() && 
               applicant.getMonthlyIncome() != null && 
               applicant.getMonthlyIncome().compareTo(MIN_INCOME_THRESHOLD) >= 0;
    }

    private boolean validateCreditHistory(CreditHistory creditHistory) {
        if (creditHistory == null) {
            return false;
        }

        boolean hasGoodScore = creditHistory.hasGoodCreditScore();
        boolean hasLowDebt = creditHistory.hasLowDebt();
        boolean hasPositiveHistory = creditHistory.hasPositivePaymentHistory();

        int passingCriteria = 0;
        if (hasGoodScore) passingCriteria++;
        if (hasLowDebt) passingCriteria++;
        if (hasPositiveHistory) passingCriteria++;

        return passingCriteria >= 2;
    }

    private boolean validateDebtToIncomeRatio(Applicant applicant) {
        BigDecimal monthlyIncome = applicant.getMonthlyIncome();
        BigDecimal totalDebt = applicant.getCreditHistory() != null ? 
            applicant.getCreditHistory().getTotalDebt() : BigDecimal.ZERO;

        if (monthlyIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        BigDecimal debtToIncome = totalDebt.divide(monthlyIncome, 4, RoundingMode.HALF_UP);
        return debtToIncome.compareTo(MAX_DEBT_TO_INCOME_RATIO) < 0;
    }

    private BigDecimal determineInterestRate(CreditHistory creditHistory) {
        if (creditHistory == null) {
            return BASE_INTEREST_RATE;
        }

        if (creditHistory.hasGoodCreditScore() && 
            creditHistory.getCreditScore() >= 750 && 
            creditHistory.hasPositivePaymentHistory()) {
            return EXCELLENT_INTEREST_RATE;
        }

        if (creditHistory.hasGoodCreditScore() && creditHistory.hasPositivePaymentHistory()) {
            return GOOD_INTEREST_RATE;
        }

        return BASE_INTEREST_RATE;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/infrastructure/repository/LoanApplicationRepository.java ===
package com.fintech.loan.infrastructure.repository;


import com.fintech.loan.domain.model.LoanStatus;
import com.fintech.loan.domain.model.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, UUID> {

    List<LoanApplication> findByStatus(LoanApplication.LoanStatus status);

    @Query("SELECT la FROM LoanApplication la WHERE la.applicant.id = :applicantId")
    List<LoanApplication> findByApplicantId(@Param("applicantId") UUID applicantId);

    @Query("SELECT la FROM LoanApplication la WHERE la.createdAt BETWEEN :startDate AND :endDate")
    List<LoanApplication> findByCreatedAtBetween(
        @Param("startDate") LocalDateTime startDate, 
        @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT la FROM LoanApplication la WHERE la.status = :status AND la.requestedAmount >= :minAmount")
    List<LoanApplication> findByStatusAndMinimumAmount(
        @Param("status") LoanApplication.LoanStatus status,
        @Param("minAmount") BigDecimal minAmount
    );

    @Query("SELECT COUNT(la) FROM LoanApplication la WHERE la.status = :status")
    long countByStatus(@Param("status") LoanApplication.LoanStatus status);

    @Query("SELECT la FROM LoanApplication la WHERE la.applicant.id = :applicantId AND la.status = :status")
    List<LoanApplication> findByApplicantIdAndStatus(
        @Param("applicantId") UUID applicantId,
        @Param("status") LoanApplication.LoanStatus status
    );
}

// === ARCHIVO: src/main/java/com/fintech/loan/infrastructure/repository/JpaLoanApplicationRepository.java ===
package com.fintech.loan.infrastructure.repository;

import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.model.Applicant;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaLoanApplicationRepository implements LoanApplicationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public LoanApplication save(LoanApplication loanApplication) {
        if (loanApplication.getId() == null) {
            loanApplication.setId(UUID.randomUUID());
            entityManager.persist(loanApplication);
            return loanApplication;
        } else {
            return entityManager.merge(loanApplication);
        }
    }

    @Override
    public Optional<LoanApplication> findById(UUID id) {
        LoanApplication result = entityManager.find(LoanApplication.class, id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<LoanApplication> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(root);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<LoanApplication> findByStatus(String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(root).where(cb.equal(root.get("status"), status));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<LoanApplication> findByApplicantId(UUID applicantId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LoanApplication> query = cb.createQuery(LoanApplication.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        Join<LoanApplication, Applicant> applicantJoin = root.join("applicant", JoinType.INNER);
        query.select(root).where(cb.equal(applicantJoin.get("id"), applicantId));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public long countByStatus(String status) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<LoanApplication> root = query.from(LoanApplication.class);
        query.select(cb.count(root)).where(cb.equal(root.get("status"), status));
        return entityManager.createQuery(query).getSingleResult();
    }

    @Override
    public void deleteById(UUID id) {
        LoanApplication loanApplication = findById(id).orElse(null);
        if (loanApplication != null) {
            entityManager.remove(loanApplication);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        LoanApplication result = entityManager.find(LoanApplication.class, id);
        return result != null;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/application/LoanApplicationService.java ===
package com.fintech.loan.application;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import com.fintech.loan.infrastructure.repository.LoanApplicationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class LoanApplicationService {

    private final LoanApplicationRepository repository;
    private final LoanApprovalService approvalService;

    public LoanApplicationService(LoanApplicationRepository repository, LoanApprovalService approvalService) {
        this.repository = repository;
        this.approvalService = approvalService;
    }

    public LoanApplication createApplication(String fullName, LocalDate dateOfBirth, 
                                              BigDecimal monthlyIncome, Integer creditScore,
                                              BigDecimal totalDebt, Integer paymentHistory,
                                              BigDecimal requestedAmount, Integer termMonths) {
        CreditHistory creditHistory = new CreditHistory(creditScore, totalDebt, paymentHistory);
        Applicant applicant = new Applicant(fullName, dateOfBirth, monthlyIncome, creditHistory);
        
        LoanApplication application = new LoanApplication();
        application.setId(UUID.randomUUID());
        application.setApplicant(applicant);
        application.setRequestedAmount(requestedAmount);
        application.setTermMonths(termMonths);
        application.setStatus("PENDING");
        application.setApplicationDate(LocalDate.now());
        
        return repository.save(application);
    }

    public Optional<LoanApplication> findById(UUID id) {
        return repository.findById(id);
    }

    public List<LoanApplication> findAll() {
        return repository.findAll();
    }

    public List<LoanApplication> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public boolean evaluateAndApprove(UUID applicationId) {
        Optional<LoanApplication> optApp = repository.findById(applicationId);
        if (optApp.isEmpty()) {
            return false;
        }
        
        LoanApplication application = optApp.get();
        if (!"PENDING".equals(application.getStatus())) {
            return false;
        }
        
        boolean approved = approvalService.evaluateLoanApplication(applicationId);
        application.setStatus(approved ? "APPROVED" : "REJECTED");
        application.setApprovalDate(LocalDate.now());
        repository.save(application);
        
        return approved;
    }

    public double getMaximumLoanAmount(Applicant applicant) {
        return approvalService.calculateMaximumLoanAmount(applicant);
    }

    public LoanApplication updateApplicationStatus(UUID id, String newStatus) {
        Optional<LoanApplication> optApp = repository.findById(id);
        if (optApp.isEmpty()) {
            throw new IllegalArgumentException("Application not found: " + id);
        }
        
        LoanApplication application = optApp.get();
        application.setStatus(newStatus);
        if ("APPROVED".equals(newStatus) || "REJECTED".equals(newStatus)) {
            application.setApprovalDate(LocalDate.now());
        }
        
        return repository.save(application);
    }

    public void deleteApplication(UUID id) {
        repository.deleteById(id);
    }

    public long countByStatus(String status) {
        return repository.countByStatus(status);
    }

    public List<LoanApplication> findByApplicantId(UUID applicantId) {
        return repository.findByApplicantId(applicantId);
    }
}

// === ARCHIVO: src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java ===
package com.fintech.loan.infrastructure.controller;

import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.model.LoanApplication;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/loan-applications")
public class LoanController {

    private final LoanApplicationService service;

    public LoanController(LoanApplicationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LoanApplicationResponse> createApplication(
            @Valid @RequestBody CreateLoanApplicationRequest request) {
        LoanApplication application = service.createApplication(
            request.fullName(),
            request.dateOfBirth(),
            request.monthlyIncome(),
            request.creditScore(),
            request.totalDebt(),
            request.paymentHistory(),
            request.requestedAmount(),
            request.termMonths()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(LoanApplicationResponse.from(application));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoanApplicationResponse> getById(@PathVariable UUID id) {
        return service.findById(id)
            .map(app -> ResponseEntity.ok(LoanApplicationResponse.from(app)))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<LoanApplicationResponse>> getAll() {
        List<LoanApplicationResponse> responses = service.findAll()
            .stream()
            .map(LoanApplicationResponse::from)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanApplicationResponse>> getByStatus(@PathVariable String status) {
        List<LoanApplicationResponse> responses = service.findByStatus(status)
            .stream()
            .map(LoanApplicationResponse::from)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @PostMapping("/{id}/evaluate")
    public ResponseEntity<EvaluationResponse> evaluateApplication(@PathVariable UUID id) {
        boolean approved = service.evaluateAndApprove(id);
        return ResponseEntity.ok(new EvaluationResponse(approved, approved ? "APPROVED" : "REJECTED"));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<LoanApplicationResponse> updateStatus(
            @PathVariable UUID id,
            @RequestBody UpdateStatusRequest request) {
        try {
            LoanApplication updated = service.updateApplicationStatus(id, request.status());
            return ResponseEntity.ok(LoanApplicationResponse.from(updated));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/maximum-amount")
    public ResponseEntity<MaximumAmountResponse> getMaximumAmount(@PathVariable UUID id) {
        return service.findById(id)
            .map(app -> {
                double maxAmount = service.getMaximumLoanAmount(app.getApplicant());
                return ResponseEntity.ok(new MaximumAmountResponse(maxAmount));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable UUID id) {
        service.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<CountResponse> countByStatus(@PathVariable String status) {
        long count = service.countByStatus(status);
        return ResponseEntity.ok(new CountResponse(status, count));
    }

    public record CreateLoanApplicationRequest(
        @NotBlank String fullName,
        @NotNull LocalDate dateOfBirth,
        @NotNull @Positive BigDecimal monthlyIncome,
        @NotNull Integer creditScore,
        @NotNull BigDecimal totalDebt,
        @NotNull Integer paymentHistory,
        @NotNull @Positive BigDecimal requestedAmount,
        @NotNull @Positive Integer termMonths
    ) {}

    public record UpdateStatusRequest(@NotBlank String status) {}

    public record LoanApplicationResponse(
        UUID id,
        String applicantName,
        BigDecimal requestedAmount,
        Integer termMonths,
        String status,
        LocalDate applicationDate,
        LocalDate approvalDate
    ) {
        public static LoanApplicationResponse from(LoanApplication app) {
            return new LoanApplicationResponse(
                app.getId(),
                app.getApplicant().getFullName(),
                app.getRequestedAmount(),
                app.getTermMonths(),
                app.getStatus(),
                app.getApplicationDate(),
                app.getApprovalDate()
            );
        }
    }

    public record EvaluationResponse(boolean approved, String status) {}

    public record MaximumAmountResponse(double maximumAmount) {}

    public record CountResponse(String status, long count) {}
}

// === ARCHIVO: src/test/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImplTest.java ===
package com.fintech.loan.domain.service.impl;

import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.service.LoanApprovalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanApprovalServiceImplTest {

    @Mock
    private LoanApprovalService loanApprovalService;

    @InjectMocks
    private LoanApprovalServiceImpl loanApprovalServiceImpl;

    @Test
    void evaluateLoanApplication_shouldApprove_whenApplicantMeetsCriteria() {
        UUID applicationId = UUID.randomUUID();
        CreditHistory creditHistory = new CreditHistory(750, new BigDecimal("5000"), 95);
        Applicant applicant = new Applicant(
            "Juan Pérez",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("5000"),
            creditHistory
        );

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(true);

        boolean result = loanApprovalService.evaluateLoanApplication(applicationId);

        assertTrue(result);
        verify(loanApprovalService, times(1)).evaluateLoanApplication(applicationId);
    }

    @Test
    void evaluateLoanApplication_shouldReject_whenApplicantDoesNotMeetCriteria() {
        UUID applicationId = UUID.randomUUID();
        CreditHistory creditHistory = new CreditHistory(500, new BigDecimal("20000"), 60);
        Applicant applicant = new Applicant(
            "María García",
            LocalDate.of(1995, 8, 22),
            new BigDecimal("1500"),
            creditHistory
        );

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(false);

        boolean result = loanApprovalService.evaluateLoanApplication(applicationId);

        assertFalse(result);
        verify(loanApprovalService, times(1)).evaluateLoanApplication(applicationId);
    }

    @Test
    void calculateMaximumLoanAmount_shouldReturnCorrectAmount() {
        CreditHistory creditHistory = new CreditHistory(720, new BigDecimal("3000"), 90);
        Applicant applicant = new Applicant(
            "Carlos López",
            LocalDate.of(1988, 3, 10),
            new BigDecimal("6000"),
            creditHistory
        );

        double expectedAmount = 30000.0;
        when(loanApprovalService.calculateMaximumLoanAmount(applicant)).thenReturn(expectedAmount);

        double result = loanApprovalService.calculateMaximumLoanAmount(applicant);

        assertEquals(expectedAmount, result, 0.01);
        verify(loanApprovalService, times(1)).calculateMaximumLoanAmount(applicant);
    }

    @Test
    void calculateMaximumLoanAmount_shouldReturnZero_whenIncomeIsLow() {
        CreditHistory creditHistory = new CreditHistory(600, new BigDecimal("10000"), 50);
        Applicant applicant = new Applicant(
            "Ana Martínez",
            LocalDate.of(2000, 1, 1),
            new BigDecimal("800"),
            creditHistory
        );

        when(loanApprovalService.calculateMaximumLoanAmount(applicant)).thenReturn(0.0);

        double result = loanApprovalService.calculateMaximumLoanAmount(applicant);

        assertEquals(0.0, result, 0.01);
    }

    @Test
    void evaluateLoanApplication_shouldHandleNullApplication() {
        UUID applicationId = UUID.randomUUID();

        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenThrow(
            new IllegalArgumentException("Application ID cannot be null")
        );

        assertThrows(IllegalArgumentException.class, () -> {
            loanApprovalService.evaluateLoanApplication(applicationId);
        });
    }
}

// === ARCHIVO: src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java ===
package com.fintech.loan.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.loan.application.LoanApplicationService;
import com.fintech.loan.domain.model.Applicant;
import com.fintech.loan.domain.model.CreditHistory;
import com.fintech.loan.domain.model.LoanApplication;
import com.fintech.loan.domain.service.LoanApprovalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LoanApplicationService loanApplicationService;

    @MockBean
    private LoanApprovalService loanApprovalService;

    private LoanApplication sampleLoanApplication;
    private Applicant sampleApplicant;

    @BeforeEach
    void setUp() {
        CreditHistory creditHistory = new CreditHistory(750, new BigDecimal("5000"), 95);
        sampleApplicant = new Applicant(
            "Juan Pérez",
            LocalDate.of(1990, 5, 15),
            new BigDecimal("5000"),
            creditHistory
        );

        sampleLoanApplication = new LoanApplication();
        sampleLoanApplication.setId(UUID.randomUUID());
        sampleLoanApplication.setApplicant(sampleApplicant);
        sampleLoanApplication.setRequestedAmount(new BigDecimal("10000"));
        sampleLoanApplication.setStatus("PENDING");
    }

    @Test
    void createApplication_shouldReturn201_whenValidRequest() throws Exception {
        when(loanApplicationService.createApplication(any(LoanApplication.class)))
            .thenReturn(sampleLoanApplication);

        mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleLoanApplication)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void getApplication_shouldReturn404_whenNotFound() throws Exception {
        UUID applicationId = UUID.randomUUID();
        when(loanApplicationService.getApplication(applicationId)).thenReturn(null);

        mockMvc.perform(get("/api/loans/{id}", applicationId))
            .andExpect(status().isNotFound());
    }

    @Test
    void getApplication_shouldReturn200_whenExists() throws Exception {
        UUID applicationId = sampleLoanApplication.getId();
        when(loanApplicationService.getApplication(applicationId)).thenReturn(sampleLoanApplication);

        mockMvc.perform(get("/api/loans/{id}", applicationId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(applicationId.toString()))
            .andExpect(jsonPath("$.requestedAmount").value(10000));
    }

    @Test
    void evaluateApplication_shouldReturn200_withApprovalResult() throws Exception {
        UUID applicationId = sampleLoanApplication.getId();
        when(loanApprovalService.evaluateLoanApplication(applicationId)).thenReturn(true);

        mockMvc.perform(post("/api/loans/{id}/evaluate", applicationId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.approved").value(true));
    }

    @Test
    void evaluateApplication_shouldReturn400_whenInvalidId() throws Exception {
        UUID invalidId = UUID.randomUUID();
        when(loanApprovalService.evaluateLoanApplication(invalidId))
            .thenThrow(new IllegalArgumentException("Invalid application ID"));

        mockMvc.perform(post("/api/loans/{id}/evaluate", invalidId))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getMaximumAmount_shouldReturn200_withCalculatedAmount() throws Exception {
        when(loanApprovalService.calculateMaximumLoanAmount(any(Applicant.class)))
            .thenReturn(30000.0);

        mockMvc.perform(post("/api/loans/maximum-amount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleApplicant)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.maximumAmount").value(30000.0));
    }

    @Test
    void createApplication_shouldReturn400_whenInvalidRequest() throws Exception {
        LoanApplication invalidApplication = new LoanApplication();
        invalidApplication.setRequestedAmount(new BigDecimal("-1000"));

        mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidApplication)))
            .andExpect(status().isBadRequest());
    }
}
```
