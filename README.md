# ExiriumViewModelKit

ExiriumViewModelKit es una biblioteca Kotlin diseñada para facilitar la gestión de estados en aplicaciones Android utilizando ViewModel y Jetpack Compose.

La biblioteca proporciona una clase BaseViewModel que se puede extender para crear tus propios ViewModels. Esta clase incorpora una gestión simplificada del estado mutable, eliminando la necesidad de escribir get y set para cada componente de Jetpack Compose en tu ViewModel.

## Características
- Permite registrar campos de estado mutable a través de un Enum.
- Proporciona métodos para cambiar y obtener valores de estado mutable.
- Automatiza la gestión de estado mutable en ViewModel, lo que te permite concentrarte en la lógica de tu aplicación.

## Código de ejemplo
Aquí te mostramos cómo utilizar BaseViewModel en tu aplicación:

``` kotlin
class MyViewModel (private val exampleUseCase: ExampleUseCase): BaseViewModel() {
    private val _user = MutableStateFlow<User>(null)
    val user = _user.asStateFlow()
    
    init {
        registerField(MyFields::class.java)
    }

    fun updateUser() {
        viewModelScope.launch {
            _user.value = exampleUseCase.getUserByName(getFieldValue(FieldId.NAME))
        }
    }
}

enum class MyFields {  
    NAME,
    EMAIL,
    SWITCH
}
```

``` kotlin
@Composable
fun ExampleBaseViewModel(viewModel: MainViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = viewModel.getFieldValue(FieldId.EMAIL) as? String ?: "",
            onValueChange = { newValue -> viewModel.onValueChanged(FieldId.EMAIL, newValue) },
            label = { Text("Label 1") })

        TextField(
            value = viewModel.getFieldValue(FieldId.NAME) as? String ?: "",
            onValueChange = { newValue -> viewModel.onValueChanged(FieldId.NAME, newValue) },
            label = { Text("Label 2") })

        Switch(
            checked = viewModel.getFieldValue(FieldId.SWITCH) as? Boolean ?: false,
            onCheckedChange = { newValue -> viewModel.onValueChanged(FieldId.SWITCH, newValue) },
            colors = SwitchDefaults.colors(checkedThumbColor = Color.Green, uncheckedThumbColor = Color.Gray)
        )

        Button(
            onClick = {
               viewModel.updateUser()
            }) {
            Text(text = "Click me")
        }

    }
}
```
## Importando la libreria
ExiriumViewModelKit está alojada en JitPack, lo que significa que puedes agregar fácilmente la biblioteca a tu proyecto.

Añade lo siguiente a tu archivo build.gradle a nivel de proyecto:

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Y luego, en tu archivo build.gradle a nivel de módulo, agrega la dependencia de la siguiente manera:

```groovy
dependencies {
    implementation 'com.github.gianpaul:ExiriumViewModelKit:1.0.1'
}
```

Esto te permitirá utilizar ExiriumViewModelKit en tu proyecto Android. Recuerda que puede ser necesario sincronizar tu proyecto con los archivos de Gradle después de agregar una nueva dependencia.

## Contribuciones
Las contribuciones a ExiriumViewModelKit son bienvenidas. Si encuentras un problema o tienes una idea para mejorar la biblioteca, por favor, abre un issue o un pull request.

## Licencia
ExiriumViewModelKit está disponible bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.
