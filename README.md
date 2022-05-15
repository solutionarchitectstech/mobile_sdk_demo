# mobile_sdk_demo project
This Android demo project covers all usages of the library [solutionarchitectstech:mobile_sdk_release](https://github.com/solutionarchitectstech/mobile_sdk_release){:target="_blank"}

## 1. Add the dependency to your project
Before usage of the SDK Developer has to add the following dependency to app-module gradle file (the dependency block):
```groovy
dependencies
{
    //... other dependencies
    implementation 'com.github.solutionarchitectstech:mobile_sdk_release:<VERSION>'
    //... other dependencies
}
```

## 2. initialization of the library
```kotlin
TechAdvertising.init(
    context: Context,
    storeUrl: String,
    apiKey: String,
    apiSecret: String,
    publisherId: Int,
    tagId: String? = null,
    userId: String? = null,
    hashType: HashType = HashType.SHA_1,
    requestType: RequestType = RequestType.POST,
    enableExternalExceptionHandler: Boolean = false,
    enableCompression: Boolean = false,
    baseUrl: String? = null,
    postUrl: String? = null,
    getUrl: String? = null,
    enableLogsHandler: Boolean = false)
```
[Look at the example here](https://github.com/solutionarchitectstech/mobile_sdk_demo/blob/5d6ce5a898b789641c6f31bf1d82d129881e0da1/app/src/main/kotlin/tech/solutionarchitects/testapplication/InitAdActivity.kt#L30){:target="_blank"}



