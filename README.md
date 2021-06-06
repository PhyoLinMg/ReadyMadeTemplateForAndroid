## Android Template Repository Tech Stack Update

This Repository is intended to use ready made template for hurrying up development code. 

To speed up the development processs, In Files->Settings->Live Template->click + on Android

It is the code for repository ->In define ->choose Kotlin

```kotlin
fun $method$()=
liveData(Dispatchers.IO) {
emit(Resource.loading(data = null))
try {
emit(Resource.success(data = $data$))
} 
catch (exception: SocketTimeoutException) {
emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
}
catch(httpException:HttpException){
emit(Resource.error(data=httpException.code(),message = httpException.message ?: "Error Occurred!"))
}
}
```

It is the code for observing the state of api-> in define ->choose Kotlin

```kotlin
it?.let { resource ->
when (resource.status) {
SUCCESS -> {
//The requested data is here 
}
ERROR -> {
//The Response is Error code
}
LOADING -> {
//The API is Loading
}
}
}
```

- Network Call Retrofit 2.6.0 Updated(Deferred Deprecated by Jake Wharton)

- Dependency Injection Update(Dagger Hilt)(Not done yet but will come soon)

- Will combine with Jetpack Compose 

- Clean Architecture Pattern will be seen in [TemplateAndroid](https://github.com/PhyoLinMg/TemplateAndroid)

- Utils will be the same with [TemplateAndroid](https://github.com/PhyoLinMg/TemplateAndroid)
