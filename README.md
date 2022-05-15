# mobile_sdk_demo project
This Android demo project covers all usages of the library [solutionarchitectstech:mobile_sdk_release](https://github.com/solutionarchitectstech/mobile_sdk_release)

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
[Look at the example here](https://github.com/solutionarchitectstech/mobile_sdk_demo/blob/5d6ce5a898b789641c6f31bf1d82d129881e0da1/app/src/main/kotlin/tech/solutionarchitects/testapplication/InitAdActivity.kt#L30)

## 3. banner view
![banner-view](/readme_content/banner.gif)

### 3.1 XML layout
```xml
<tech.solutionarchitects.advertisingsdk.core.BannerAdvertisement
   android:id="@+id/bannerAd"
   android:layout_width="match_parent"
   android:layout_height="100dp"
   app:layout_constraintEnd_toEndOf="parent"
   app:layout_constraintStart_toStartOf="parent"
   app:layout_constraintTop_toTopOf="parent"/>
```
[Look at the example here](https://github.com/solutionarchitectstech/mobile_sdk_demo/blob/5d6ce5a898b789641c6f31bf1d82d129881e0da1/app/src/main/res/layout/activity_main.xml#L28)

### 3.2 Setup the data

```kotlin
bannerAd.initBanner(
placementId: Int,
sizes: List<Size>,
timeout: Int? = null,
refresh: Int? = null,
closeButtonType: CloseButtonType = CloseButtonType.VISIBLE)
```
[Look at the example here](https://github.com/solutionarchitectstech/mobile_sdk_demo/blob/5d6ce5a898b789641c6f31bf1d82d129881e0da1/app/src/main/kotlin/tech/solutionarchitects/testapplication/MainActivity.kt#L67)

## 4. fullscreen view
![banner-view](/readme_content/fullscreen.gif)

```kotlin
val interstitialAdvertisement = InterstitialAdvertisement(
   activity: Activity,
   placementId: Int,
   sizes: List<Size>,
   timeout: Int? = null,
   refresh: Int? = null,
   closeButtonType: CloseButtonType = CloseButtonType.COUNTDOWN,
)
interstitialAdvertisement.load()
```
[Look at the example here](https://github.com/solutionarchitectstech/mobile_sdk_demo/blob/5d6ce5a898b789641c6f31bf1d82d129881e0da1/app/src/main/kotlin/tech/solutionarchitects/testapplication/MainActivity.kt#L55)







