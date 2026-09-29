# productos-ci

Proyecto en Java + Maven con pruebas unitarias en JUnit 5 y automatización CI/CD con pipeline en Jenkins y notificaciones en Slack.

---

## 👥 Integrantes y roles

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
* **Gestor de dependencias:** Maven 3.9+
* **Framework de Pruebas:** JUnit Jupiter 5.10.2 (Unitarias y Parametrizadas)
* **Integración Continua:** Jenkins (Pipeline Declarativo)
* **Notificaciones:** Slack

---

## 🧪 Matriz de Casos de Prueba (`ProductoTest.java`)

| ID | Tipo | Caso de Prueba | Iteraciones | Resultado Esperado |
|:---:|:---:|---|:---:|---|
| **CP01** | Unitaria | Validar precio mayor a cero | 1 | Éxito (`assertTrue`) |
| **CP02** | Parametrizada (`@ValueSource`) | Validar precios inválidos (0.0, -1.0, -50.0) | 3 | Lanza `IllegalArgumentException` |
| **CP03** | Unitaria | Validar stock mayor o igual a cero | 1 | Éxito (`assertTrue`) |
| **CP04** | Unitaria | Validar stock negativo (-5) | 1 | Lanza `IllegalArgumentException` |
| **CP05** | Parametrizada (`@CsvSource`) | Calcular precio con descuentos (10%, 20%, 0%, 50%) | 4 | Precios calculados exactos |
| **CP06** | Unitaria | Descuento fuera de rango (120%) | 1 | Lanza `IllegalArgumentException` |
| **CP07** | Unitaria | Calcular precio con IGV (18%) | 1 | Total con impuesto calculado |
| **CP08** | Unitaria | Envío gratis por monto >= S/ 100 | 1 | Retorna `true` |
| **CP09** | Unitaria | Envío gratis por cantidad >= 5 unidades | 1 | Retorna `true` |
| **CP10** | Unitaria | No califica a envío gratis (monto < 100 y cantidad < 5) | 1 | Retorna `false` |

### 📊 Desglose Matemático de Ejecución:
* **Casos de prueba diseñados:** 10 casos de prueba.
* **Métodos `@Test` unitarios simples:** 8 métodos (8 ejecuciones).
* **Métodos `@ParameterizedTest`:** 2 métodos con 7 ejecuciones (3 en CP02 + 4 en CP05).
* **Total de pruebas ejecutadas en consola:** **15 pruebas (`Tests run: 15, Failures: 0, Errors: 0, Skipped: 0`)**.

---

## 🚀 Ejecución de Pruebas en Maven

```bash
mvn clean test
```

Salida esperada:
```text
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running ProductoTest
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.170 s -- in ProductoTest
[INFO] 
[INFO] Results:
[INFO] Tests run: 15, Failures: 0, Errors: 0, Skipped: 0
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## ✉️ Automatizacion con Jenkins
```bash
pipeline {
    agent any

    stages {
        stage('1. Checkout SCM') {
            steps {
                echo '=== Descargando codigo fuente desde GitHub ==='
                git branch: 'develop', url: 'https://github.com/LuisAngelSanchezRomero/T10_SLACK.git'
            }
        }

        stage('2. Compilacion') {
            steps {
                echo '=== Compilando el proyecto de Gestion de Productos ==='
                bat 'mvn clean compile'
            }
        }

        stage('3. Pruebas Unitarias y Parametrizadas') {
            steps {
                echo '=== Ejecutando pruebas unitarias (JUnit 5 + Mockito) y parametrizadas (@ParameterizedTest) ==='
                bat 'mvn test'
            }
        }
    }

    post {
    success {
        slackSend(
            channel: '#jenkins',
            color: 'good',
            message: """ *BUILD SUCCESS* 
            *Job:* ${env.JOB_NAME}
            *Build:* #${env.BUILD_NUMBER}
            *Resultado:* SUCCESS 
            *Duración:* ${currentBuild.durationString}"""
        )
    }
    failure {
        slackSend(
            channel: '#jenkins',
            color: 'danger',
            message: """ *BUILD FAILURE* 
            *Job:* ${env.JOB_NAME}
            *Build:* #${env.BUILD_NUMBER}
            *Resultado:* FAILURE 
            *Duración:* ${currentBuild.durationString}"""
        )
    }
}

}
```

Salida esperada:
```text
[Pipeline] }
[Pipeline] // stage
[Pipeline] stage
[Pipeline] { (Declarative: Post Actions)
[Pipeline] slackSend
Slack Send Pipeline step running, values are - baseUrl: <empty>, teamDomain: pswt10, channel: #jenkins, color: good, botUser: false, tokenCredentialId: CredentialID, notifyCommitters: false, iconEmoji: <empty>, username: <empty>, timestamp: <empty>
[Pipeline] }
[Pipeline] // stage
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```


