$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/java/alicorp/userstory/login/AM1_Login.feature");
formatter.feature({
  "line": 1,
  "name": "Permitirá el ingreso a nuestros clientes al app validando sus credenciales",
  "description": "para que solo tengan la información correspondiente.",
  "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "comments": [
    {
      "line": 4,
      "value": "#General:"
    },
    {
      "line": 5,
      "value": "#Los tags de los escenarios de test se definiran por los siguientes tipos: @Automatable, @Manual"
    },
    {
      "line": 6,
      "value": "#Los escenarios de test seran @HappyPath y @AlternativePath."
    },
    {
      "line": 7,
      "value": "#Los examples se realizará tanto para el environment dev, QA, Prod y para dispositivos mobile IOS y Android"
    },
    {
      "line": 10,
      "value": "#Precondiciones: El usuario 34839 ingresa por primera vez app alimarket"
    }
  ],
  "line": 12,
  "name": "Iniciar sesión a Alimarket por primera vez con credenciales validas",
  "description": "",
  "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales;iniciar-sesión-a-alimarket-por-primera-vez-con-credenciales-validas",
  "type": "scenario_outline",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 11,
      "name": "@AM1_001"
    },
    {
      "line": 11,
      "name": "@josimar.leon"
    },
    {
      "line": 11,
      "name": "@Automatable"
    },
    {
      "line": 11,
      "name": "@HappyPath"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "Alimarket App se encuentra abierta",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "ingresar \"\u003cusuario\u003e\" como el usuario en la vista login dentro del flujo login.",
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "ingresar \"\u003ccontrasenia\u003e\" como contraseña en la vista login dentro del flujo login.",
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "seleccionar el botón \"Iniciar Sesión\" en la vista login dentro del flujo login.",
  "keyword": "And "
});
formatter.examples({
  "comments": [
    {
      "line": 17,
      "value": "#Then se muestra el titulo \"Cambiar contraseña\" en la vista cambiar contrasenia"
    }
  ],
  "line": 20,
  "name": "",
  "description": "",
  "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales;iniciar-sesión-a-alimarket-por-primera-vez-con-credenciales-validas;",
  "rows": [
    {
      "cells": [
        "usuario",
        "contrasenia"
      ],
      "line": 21,
      "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales;iniciar-sesión-a-alimarket-por-primera-vez-con-credenciales-validas;;1"
    },
    {
      "cells": [
        "34839",
        "34839"
      ],
      "line": 22,
      "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales;iniciar-sesión-a-alimarket-por-primera-vez-con-credenciales-validas;;2"
    }
  ],
  "keyword": "Examples"
});
formatter.before({
  "duration": 10830550032,
  "status": "passed"
});
formatter.scenario({
  "line": 22,
  "name": "Iniciar sesión a Alimarket por primera vez con credenciales validas",
  "description": "",
  "id": "permitirá-el-ingreso-a-nuestros-clientes-al-app-validando-sus-credenciales;iniciar-sesión-a-alimarket-por-primera-vez-con-credenciales-validas;;2",
  "type": "scenario",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "line": 11,
      "name": "@HappyPath"
    },
    {
      "line": 11,
      "name": "@AM1_001"
    },
    {
      "line": 11,
      "name": "@Automatable"
    },
    {
      "line": 11,
      "name": "@josimar.leon"
    }
  ]
});
formatter.step({
  "line": 13,
  "name": "Alimarket App se encuentra abierta",
  "keyword": "Given "
});
formatter.step({
  "line": 14,
  "name": "ingresar \"34839\" como el usuario en la vista login dentro del flujo login.",
  "matchedColumns": [
    0
  ],
  "keyword": "When "
});
formatter.step({
  "line": 15,
  "name": "ingresar \"34839\" como contraseña en la vista login dentro del flujo login.",
  "matchedColumns": [
    1
  ],
  "keyword": "And "
});
formatter.step({
  "line": 16,
  "name": "seleccionar el botón \"Iniciar Sesión\" en la vista login dentro del flujo login.",
  "keyword": "And "
});
formatter.match({
  "location": "LoginSteps.alimarketAppSeEncuentraAbierta()"
});
formatter.result({
  "duration": 1673161667,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "34839",
      "offset": 10
    }
  ],
  "location": "LoginSteps.ingresarComoElUsuarioEnLaVistaLoginDentroDelFlujoLogin(String)"
});
formatter.result({
  "duration": 616212626,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "34839",
      "offset": 10
    }
  ],
  "location": "LoginSteps.ingresarComoContraseñaEnLaVistaLoginDentroDelFlujoLogin(String)"
});
formatter.result({
  "duration": 1410494786,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Iniciar Sesión",
      "offset": 22
    }
  ],
  "location": "LoginSteps.seleccionarElBotonEnLaVistaLoginDentroDelFlujoLogin(String)"
});
formatter.result({
  "duration": 628482003,
  "status": "passed"
});
formatter.after({
  "duration": 318843453,
  "status": "passed"
});
});