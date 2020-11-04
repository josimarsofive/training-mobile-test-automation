Feature: Permitirá el ingreso a nuestros clientes al app validando sus credenciales
  para que solo tengan la información correspondiente.

#General:
  #Los tags de los escenarios de test se definiran por los siguientes tipos: @Automatable, @Manual
  #Los escenarios de test seran @HappyPath y @AlternativePath.
  #Los examples se realizará tanto para el environment dev, QA, Prod y para dispositivos mobile IOS y Android


  #Precondiciones: El usuario 34839 ingresa por primera vez app alimarket
  @AM1_001 @josimar.leon @Automatable @HappyPath
  Scenario Outline: Iniciar sesión a Alimarket por primera vez con credenciales validas
    Given Alimarket App se encuentra abierta
    When ingresar "<usuario>" como el usuario en la vista login dentro del flujo login.
    And ingresar "<contrasenia>" como contraseña en la vista login dentro del flujo login.
    And seleccionar el botón "Iniciar Sesión" en la vista login dentro del flujo login.
    #Then se muestra el titulo "Cambiar contraseña" en la vista cambiar contrasenia

    @uat @android
    Examples:
      |usuario|contrasenia|
      | 34839 |34839 |

    @prod @android
    Examples:
      |usuario|contrasenia|
      | 34839 |34839 |

    #Precondiciones: El usuario 397542 ingresa por primera vez app alimarket
  @AM1_002 @josimar.leon @Automatable @HappyPath
  Scenario Outline: Cambiar contraseña de usuario que desea iniciar sesión por primera vez en alimarket
    Given Alimarket App se encuentra abierta
    When ingresar "<usuario>" como el usuario en la vista login dentro del flujo login.
    And ingresar "<contrasenia>" como contraseña en la vista login dentro del flujo login.
    And seleccionar el botón "Iniciar Sesión" en la vista login dentro del flujo login.
    When ingresar "<nuevaContrasenia>" como nueva contraseña en la vista cambiar contraseña
    And ingresar "<confirmarContrasenia>" para confirmar nueva contraseña en la vista cambiar contraseña
    When seleccionar el botón "Guardar" para guardar la nueva contraseña en la vista cambiar contraseña
    Then se muestra el popup "Tu contraseña fue cambiada con éxito" de la confirmación de cambio de contraseña

    @uat @android
    Examples:
      |usuario|contrasenia|nuevaContrasenia|confirmarContrasenia|
      | 397542 |397542 |12345678           |12345678            |

    @prod @android
    Examples:
      |usuario|contrasenia|nuevaContrasenia|confirmarContrasenia|
      | 397542 |397542 |12345678           |12345678            |