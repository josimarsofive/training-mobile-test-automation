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
      | 34839 |34839 |


    @prod @ios
    Examples:
      |usuario|contrasenia|
      | 34839 |34839 |

