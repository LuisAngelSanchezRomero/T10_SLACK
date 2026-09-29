# productos-ci

Proyecto en Java + Maven con pruebas unitarias en JUnit 5 y automatización CI/CD con Pipeline en Jenkins y notificaciones en Slack en tiempo real.

---

## 👥 Integrantes y Roles

| Integrante | Rol |
|---|---|
| PALOMINO BENITO, Jhoss Andy | QA Tester |
| SANCHEZ ROMERO, Luis Angel | QA Tester |

---

## 📁 Estructura del Proyecto

```text
productos-ci/
│
├── pom.xml
├── README.md
├── Jenkinsfile
│
└── src/
    ├── main/
    │   └── java/
    │       └── Producto.java
    └── test/
        └── java/
            └── ProductoTest.java
```

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 17 (LTS)
* **Gestor de Dependencias:** Maven 3.9+
* **Framework de Pruebas:** JUnit Jupiter 5.10.2 (Pruebas Unitarias)
* **Integración Continua (CI/CD):** Jenkins (Pipeline Declarativo)
* **Notificaciones en Tiempo Real:** Slack

---

## 🧪 Matriz de Casos de Prueba (`ProductoTest.java`)

Se implementaron **5 casos de prueba unitarios** con JUnit 5 validando las reglas de negocio de la clase `Producto.java`:

| ID | Tipo de Prueba | Descripción del Escenario | Iteraciones | Resultado Esperado |
|:---:|:---:|---|:---:|---|
| **CP01** | Unitaria (`@Test`) | Validar precio correcto mayor a cero (25.50) | 1 | Éxito (`assertTrue`) |
| **CP02** | Unitaria (`@Test`) | Validar stock correcto mayor o igual a cero (10) | 1 | Éxito (`assertTrue`) |
| **CP03** | Unitaria (`@Test`) | Calcular precio final con 10% de descuento aplicado | 1 | Retorna 90.0 (`assertEquals`) |
| **CP04** | Unitaria (`@Test`) | Calcular precio con IGV del 18% (Base: 100 -> 118) | 1 | Retorna 118.0 (`assertEquals`) |
| **CP05** | Unitaria (`@Test`) | Calificar a envío gratis por monto >= S/ 100 (150.0) | 1 | Retorna `true` (`assertTrue`) |

### 📊 Resumen de Ejecución:
* **Casos de prueba diseñados:** 5 casos de prueba unitarios.
* **Métodos `@Test` ejecutados:** 5 métodos.
* **Resultado en consola Surefire:** **`Tests run: 5, Failures: 0, Errors: 0, Skipped: 0`**.

---

## 🚀 Ejecución Local de Pruebas

Para compilar y ejecutar las pruebas unitarias localmente:

```bash
mvn clean test
```

### Salida esperada en terminal:
```text
[INFO] Scanning for projects...
[INFO] ------------------< pe.edu.vallegrande:productos-ci >-------------------
[INFO] Building Productos CI 1.0.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO] --- clean:3.2.0:clean (default-clean) @ productos-ci ---
[INFO] --- compiler:3.12.1:compile (default-compile) @ productos-ci ---
[INFO] --- compiler:3.12.1:testCompile (default-testCompile) @ productos-ci ---
[INFO] --- surefire:3.2.5:test (default-test) @ productos-ci ---
[INFO] Running ProductoTest
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.057 s -- in ProductoTest
[INFO] 
[INFO] Results:
[INFO] Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

---

## ⚙️ Automatización CI/CD con Jenkins

El archivo `Jenkinsfile` define un pipeline declarativo estructurado en tres etapas:

1. **Checkout:** Clona el código fuente desde el repositorio en GitHub (`T10_SLACK`).
2. **Build:** Compila el proyecto mediante `mvn clean compile`.
3. **Test:** Ejecuta la suite de pruebas unitarias mediante `mvn clean test`.

### Notificaciones Automáticas en Slack:
* 🟢 **SUCCESS:** Envía alerta con resultado `BUILD SUCCESS` al canal de Slack.
* 🔴 **FAILURE:** Envía alerta con resultado `BUILD FAILURE` en caso de fallos de compilación o aserción.
* 🟢 **BACK TO NORMAL (Recuperado):** Envía alerta de recuperación validada cuando el pipeline vuelve a pasar exitosamente.
