# Diseño y Desarrollo de Sistema con Patrones GRASP

El equipo de desarrollo de una fintech necesita implementar un sistema para gestionar solicitudes de préstamos personales. El sistema debe registrar las solicitudes con información del solicitante (nombre, edad, ingresos, historial crediticio) y decidir si aprueba o rechaza cada solicitud basándose en reglas de negocio. El objetivo es aplicar al menos dos patrones GRASP en el diseño y desarrollo del sistema para asegurar una arquitectura limpia, mantenible y escalable.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | aplicación de patrones grasp en el desarrollo de sistemas |
| **Nivel** | trainee-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Modelado Inicial del Dominio

**Objetivo:** Definir las entidades y relaciones básicas del sistema.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las entidades clave del dominio (solicitud, solicitante, reglas de negocio).
- Establecer las relaciones entre las entidades.
- Aplicar el patrón 'Experto en Información' para asignar responsabilidades.

**Entregable:** Diagrama de clases inicial con entidades y relaciones.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo cada entidad debe conocer su propia información.
- Piensa en cómo las entidades interactúan entre sí.

</details>

### Fase 2: Implementación de la Lógica de Negocio

**Objetivo:** Implementar la lógica para aprobar o rechazar solicitudes.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Desarrollar la lógica que evalúa las solicitudes de préstamo.
- Aplicar el patrón 'Creador' para manejar la creación de solicitudes.
- Asegurar que el sistema siga el patrón 'Alta Cohesión y Bajo Acoplamiento'.

**Entregable:** Código que implementa la lógica de aprobación/rechazo de solicitudes.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo separar la creación de solicitudes de su procesamiento.
- Considera cómo mantener la cohesión dentro de las clases y el bajo acoplamiento entre ellas.

</details>

### Fase 3: Integración y Testing

**Objetivo:** Integrar todas las partes del sistema y realizar pruebas.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Integrar el modelo de dominio con la lógica de negocio.
- Realizar pruebas unitarias y de integración para asegurar la funcionalidad.
- Aplicar el patrón 'Polimorfismo' para manejar diferentes tipos de solicitudes.

**Entregable:** Sistema integrado y funcionando con pruebas unitarias y de integración.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo puedes usar el polimorfismo para manejar diferentes tipos de solicitudes.
- Considera cómo realizar pruebas efectivas para asegurar la calidad del código.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son los patrones GRASP y por qué son importantes en el diseño de sistemas?
- **paraQueSirve**: ¿Cómo aplicas el patrón 'Experto en Información' en el modelado del dominio?
- **comoSeUsa**: ¿Cómo integras la lógica de negocio con el modelo de dominio?
- **erroresComunes**: ¿Cuáles son los errores comunes al aplicar patrones GRASP y cómo los evitas?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica la aplicación de patrones GRASP en este sistema?

## Criterios de Evaluacion

- Aplicación correcta del patrón 'Experto en Información' en el modelado del dominio.
- Implementación de la lógica de negocio siguiendo el patrón 'Creador'.
- Uso del patrón 'Alta Cohesión y Bajo Acoplamiento' en la estructura del código.
- Integración exitosa del modelo de dominio con la lógica de negocio.
- Realización de pruebas unitarias y de integración efectivas.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
