# appium-java <img src="https://github.com/devicons/devicon/blob/master/icons/androidstudio/androidstudio-original.svg" title="Android Studio" alt="Android Studio" width="35" height="35"/> <img src="https://seekicon.com/free-icon-download/appium_1.svg" title="Appium" alt="Appium" width="35" height="35"/> <img src="https://github.com/devicons/devicon/blob/master/icons/java/java-original-wordmark.svg" title="Java" alt="Java" width="40" height="40"/> <img src="https://static-00.iconduck.com/assets.00/browserstack-icon-512x511-xfk7rgj2.png" title="BrowserStack" alt="BrowserStack" width="40" height="40"/>

## 📱Introduction📱
This project demonstrates my expertise of using **Appium** to develop automated testing for simple Android mobile app: Calculator. The .apk file is attached in this repo for installation before testing

The tests can be run on local using emulator devices from **Android Studio**, or on cloud service (**BrowserStack**)

Tests are written in **Java**. **TestNG** is used as test framework and **Maven** is used as build tool and for triggering test from CLI

## 📖Knowledge📖
- Use Vertual Device Manager (VDM) of Android Studio to create and start emulator Android devices
- Use Appium Viewer to capture app elements
- Use BrowserStack to run tests on cloud

## ☁️How to run test on cloud (BrowserStack)☁️
- Install Java and set environment path
- Clone this repo
- Upload the calculator app .apk file into your browserStack account
- Open `browserstack-config.json` file under `src/test/resources`, update username and access_key to your BrowserStack's username and access_key
- Open CLI and run the following command `mvn test -DsuiteXmlFile="src/test/resources/testrun-cloud.xml"`
## 💻How to run test on local💻
- Install Java and Android Studio
- Set JAVA_HOME and ANDROID_HOME environment paths
- Install Appium and start Appium server
- Clone this repo
- Open AVD Manager of Android Studio to create some virtual devices. Make sure that the device configuration matches `config.json` file under `src/test/resources` folder
- Start devices. Download `Calculator_8.4.1 (520193683)_Apkpure.apk` file from the repo and install it to those devices
- Open CLI and run the following command `mvn test -DsuiteXmlFile="src/test/resources/testrun-local.xml"`
