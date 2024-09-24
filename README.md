<h1 align="center">Kotlin Multiplatform && Firebase App Sample</h1>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=24"><img alt="API" src="https://img.shields.io/badge/API-24%2B-brightgreen.svg?style=flat"/></a>
</p>
<p align="center">  
  This app is a crypto portfolio mobile app that demonstrates modern Kotlin Multiplatform development with Firebase based on MVI architecture. Included features: authentication, realtime database, crashlytics
</p>

## Overview
### Portfolio
<img width="835" alt="Снимок экрана 2024-09-16 в 14 40 31" src="https://github.com/user-attachments/assets/17672d5d-3c42-44f4-9ecf-581873f35cb9">

### Add coin
<img width="856" alt="Снимок экрана 2024-09-16 в 14 40 52" src="https://github.com/user-attachments/assets/755cea42-6141-4c52-922e-2347426bbfe8">

### Auth
<img width="849" alt="image" src="https://github.com/user-attachments/assets/6e25bb50-ac2b-411e-ac6b-5f1ff4fcefdc">

## How to start
- Put your google-service.json into [androidApp folder](https://github.com/larkes-cyber/KmpFirebaseAppSample/tree/main/androidApp)
- Put your GoogleService-info.plist into [iosApp folder](https://github.com/larkes-cyber/KmpFirebaseAppSample/tree/main/iosApp/iosApp)
- Configure firebase params in [Constants](https://github.com/larkes-cyber/KmpFirebaseAppSample/blob/main/shared/src/commonMain/kotlin/com/larkes/firebasecryptoportfoliosample/utils/Constants.kt)
- Configure GIDClientID and GIDServerClientID in [Info.plist](https://github.com/larkes-cyber/KmpFirebaseAppSample/blob/main/iosApp/iosApp/Info.plist)
- Add REVERSED_CLIENT_ID into URL_TYPES in [Info.plist](https://github.com/larkes-cyber/KmpFirebaseAppSample/blob/main/iosApp/iosApp/Info.plist)
- Enjoy the app!

