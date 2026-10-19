# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Diseño y Desarrollo de Sistema con Patrones GRASP**.

| | |
|---|---|
| Tema | aplicación de patrones grasp en el desarrollo de sistemas |
| Nivel | trainee-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | capas estándar |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-test n/a
- org.postgresql:postgresql n/a
- org.projectlombok:lombok 1.18.30
- jakarta.persistence:jakarta.persistence-api 3.1.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Modelado Inicial del Dominio**: Diagrama de clases inicial con entidades y relaciones.
- **Fase 2 — Implementación de la Lógica de Negocio**: Código que implementa la lógica de aprobación/rechazo de solicitudes.
- **Fase 3 — Integración y Testing**: Sistema integrado y funcionando con pruebas unitarias y de integración.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImplTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (26)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loan/LoanApplication.java` — `LoanApplicationRepository.count`
      Se invoca `count` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `LoanApplicationRepository.findById`
      Se invoca `findById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `LoanApplicationRepository.save`
      Se invoca `save` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `Applicant.getCreditHistory`
      Se invoca `getCreditHistory` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `Applicant.getMonthlyIncome`
      Se invoca `getMonthlyIncome` sobre `Applicant`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java` — `CreditHistory.getCreditScore`
      Se invoca `getCreditScore` sobre `CreditHistory`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplication.setApplicationDate`
      Se invoca `setApplicationDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.save`
      Se invoca `save` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.findById`
      Se invoca `findById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.findAll`
      Se invoca `findAll` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplication.setApprovalDate`
      Se invoca `setApprovalDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/application/LoanApplicationService.java` — `LoanApplicationRepository.deleteById`
      Se invoca `deleteById` sobre `LoanApplicationRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.fullName`
      Se invoca `fullName` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.dateOfBirth`
      Se invoca `dateOfBirth` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.monthlyIncome`
      Se invoca `monthlyIncome` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.creditScore`
      Se invoca `creditScore` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.totalDebt`
      Se invoca `totalDebt` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.paymentHistory`
      Se invoca `paymentHistory` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.termMonths`
      Se invoca `termMonths` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `UpdateStatusRequest.status`
      Se invoca `status` sobre `UpdateStatusRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `LoanApplication.getApplicationDate`
      Se invoca `getApplicationDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java` — `LoanApplication.getApprovalDate`
      Se invoca `getApprovalDate` sobre `LoanApplication`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java` — `LoanApplicationService.getApplication`
      Se invoca `getApplication` sobre `LoanApplicationService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (14)

- `pom.xml`
- `src/main/java/com/fintech/loan/LoanApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/fintech/loan/domain/model/Applicant.java`
- `src/main/java/com/fintech/loan/domain/model/CreditHistory.java`
- `src/main/java/com/fintech/loan/domain/service/LoanApprovalService.java`
- `src/main/java/com/fintech/loan/domain/model/LoanApplication.java`
- `src/main/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImpl.java`
- `src/main/java/com/fintech/loan/infrastructure/repository/LoanApplicationRepository.java`
- `src/main/java/com/fintech/loan/infrastructure/repository/JpaLoanApplicationRepository.java`
- `src/main/java/com/fintech/loan/application/LoanApplicationService.java`
- `src/main/java/com/fintech/loan/infrastructure/controller/LoanController.java`
- `src/test/java/com/fintech/loan/domain/service/impl/LoanApprovalServiceImplTest.java`
- `src/test/java/com/fintech/loan/infrastructure/controller/LoanControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/loan`
- `src/main/java/com/fintech/loan/domain`
- `src/main/java/com/fintech/loan/domain/model`
- `src/main/java/com/fintech/loan/domain/service`
- `src/main/java/com/fintech/loan/application`
- `src/main/java/com/fintech/loan/infrastructure`
- `src/main/java/com/fintech/loan/infrastructure/controller`
- `src/main/java/com/fintech/loan/infrastructure/repository`
- `src/main/resources`
- `src/test/java/com/fintech/loan`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **capas estándar**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Trainee
- Brecha que el reto ataca: Aplica al menos dos patrones GRASP (Patrones de Software para la Asignación de Responsabilidades Generales) en el diseño y desarrollo de un sistema. (Entre ellos: Experto en Información, Creador, Controlador, Alta Cohesión y Bajo Acoplamiento, Polimorfismo, Fabricación Pura, Indirección y Variaciones Protegidas).
- Mision: Candidato en etapa Trainee en Backend con Java.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
