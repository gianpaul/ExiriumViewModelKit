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
class MyViewModel : BaseViewModel() {
    init {
        registerField(MyFields::class.java)
    }

    fun updateField(id: MyFields, newValue: Any?) {
        onValueChanged(id, newValue)
    }

    fun getField(id: MyFields): Any? {
        return getFieldValue(id)
    }
}

enum class MyFields {
    FIELD_ONE,
    FIELD_TWO,
    //...
}
```

## Contribuciones
Las contribuciones a ExiriumViewModelKit son bienvenidas. Si encuentras un problema o tienes una idea para mejorar la biblioteca, por favor, abre un issue o un pull request.

## Licencia
ExiriumViewModelKit está disponible bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.
