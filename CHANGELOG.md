# Changelog

All notable changes to this project will be documented in this file. See [standard-version](https://github.com/conventional-changelog/standard-version) for commit guidelines.

### [0.5.1](https://github.com/GruppOne/stalker-mobile-app/compare/v0.5.0...v0.5.1) (2020-04-06)


### Bug Fixes

* add hook to start location updates ([26eeb98](https://github.com/GruppOne/stalker-mobile-app/commit/26eeb98aa7e0be8d923b35871a560d47ad04d0b4))
* correct plural in endpoint ([ebf8cbe](https://github.com/GruppOne/stalker-mobile-app/commit/ebf8cbef62a85ef88b777b37558890209be13bae))

## [0.5.0](https://github.com/GruppOne/stalker-mobile-app/compare/v0.4.0...v0.5.0) (2020-04-06)


### Features

* add api key header to requests ([c3eeac2](https://github.com/GruppOne/stalker-mobile-app/commit/c3eeac283ad15c196c0111c22480ba6fdaf91c49))


### Bug Fixes

* correct typo in function call ([ec8b816](https://github.com/GruppOne/stalker-mobile-app/commit/ec8b8166f1e150f83d8e8e3b56c69463e0044601))


### Configuration

* add api key to BuildConfig ([4001793](https://github.com/GruppOne/stalker-mobile-app/commit/400179325fcea8d3ac5b0656cc4086398e25f5eb))

## [0.4.0](https://github.com/GruppOne/stalker-mobile-app/compare/v0.3.1...v0.4.0) (2020-04-05)


### Features

* add locationUpdate call to server ([6beeedc](https://github.com/GruppOne/stalker-mobile-app/commit/6beeedc0b39d1447b5437ea369ad96f96d23af63))
* add locationUpdateInside to webSingleton ([551d00e](https://github.com/GruppOne/stalker-mobile-app/commit/551d00ea946434a413169a3b9fd9fb9859d12360))
* change getInsideId method in CurrentSessionSingleton ([eeda979](https://github.com/GruppOne/stalker-mobile-app/commit/eeda979fbc49cd95b6f5c8221fc074d49c313cfb))
* change the type of id in user class ([6531dfe](https://github.com/GruppOne/stalker-mobile-app/commit/6531dfe70a6dcf87ccd8930fc35bfd8a4b48813a))
* modify Organization class after merge ([c85f06e](https://github.com/GruppOne/stalker-mobile-app/commit/c85f06e5a4eccce6182eb454eb9efc3f773ed5a2))
* remove break from getInsidePlaces ([0433e79](https://github.com/GruppOne/stalker-mobile-app/commit/0433e795f52c0fc76832009284236450063e298f))

### [0.3.1](https://github.com/GruppOne/stalker-mobile-app/compare/v0.3.0...v0.3.1) (2020-04-05)


### Bug Fixes

* change algo for point inside polyline ([839d8f9](https://github.com/GruppOne/stalker-mobile-app/commit/839d8f9e0f0c80add6e89e7ee0711cc12230761b))


### Configuration

* **deps:** add vectorial math support ([da0c2f4](https://github.com/GruppOne/stalker-mobile-app/commit/da0c2f49e379d4ca683f9ed3f5afd7a9e8ab6f80))


### Documentation

* add todo comments ([5565bb5](https://github.com/GruppOne/stalker-mobile-app/commit/5565bb5ec407aa1e59007cb6a6589a42af37c75f))

## [0.3.0](https://github.com/GruppOne/stalker-mobile-app/compare/v0.2.0...v0.3.0) (2020-04-05)


### Features

* add chain of methods for LiveData implementation ([931b5bc](https://github.com/GruppOne/stalker-mobile-app/commit/931b5bcaa3bedcca841e9401b08e46ebdc6b6af5))
* add class to represent a Point ([4a657f9](https://github.com/GruppOne/stalker-mobile-app/commit/4a657f98186533904fc6d4e84eb127fbc9f06c3f))
* add constructors for JSON parsing ([a3517a0](https://github.com/GruppOne/stalker-mobile-app/commit/a3517a01163530fb178ae6956d8349a87453bcf6))
* add Marcator projection helper functions ([416b8a1](https://github.com/GruppOne/stalker-mobile-app/commit/416b8a1e017cf2ab9b58c614b3f033dd753920d7))
* change launcher entry point ([1ec2c1a](https://github.com/GruppOne/stalker-mobile-app/commit/1ec2c1a51948eda50b9f7376915e5f7f9f20e839))
* implement organization list reception ([a521ebc](https://github.com/GruppOne/stalker-mobile-app/commit/a521ebcffb08efafb58584ee3b47e2e749822955))


### Bug Fixes

* bypass ssl security to allow http requests (not https) ([e53de3d](https://github.com/GruppOne/stalker-mobile-app/commit/e53de3dc569422e956bd7a9af637b8bb40b6086a))
* set correct server url ([65edb19](https://github.com/GruppOne/stalker-mobile-app/commit/65edb19258073cbe1da6b38c1b1f83c80d7f6dec))
* update json names according to api ([bfc11b7](https://github.com/GruppOne/stalker-mobile-app/commit/bfc11b70a551edf94ec07c397d8353ceb8c71546))
* wrap single org layout in a FrameLayout ([c426607](https://github.com/GruppOne/stalker-mobile-app/commit/c42660747bb8159e036fda1b88194d9cd17a04ec))

## [0.2.0](https://github.com/GruppOne/stalker-mobile-app/compare/v0.1.0...v0.2.0) (2020-04-04)


### Features

* add base activity class ([f141bca](https://github.com/GruppOne/stalker-mobile-app/commit/f141bca04ed83a3961b0254b88b0e8069abb069d))
* add check for no orgs ([25479cf](https://github.com/GruppOne/stalker-mobile-app/commit/25479cf673dcec24ce3cacf32f48871bd6643071))
* add check for permissions in base activity ([0932e88](https://github.com/GruppOne/stalker-mobile-app/commit/0932e88b72b567e50ed3fe6e1d60e67b9df73d5e))
* add class representing the user ([6af3293](https://github.com/GruppOne/stalker-mobile-app/commit/6af329358790f0f1c52562a57833599117235150))
* add class to handle position changes ([84c7488](https://github.com/GruppOne/stalker-mobile-app/commit/84c74885ab1e4ec49323d943450e5cf9083e6679))
* add constructors for Organization and Place ([4c62801](https://github.com/GruppOne/stalker-mobile-app/commit/4c628018f71e1e3eb3e3ab710458bcc20918c31b))
* add CurrentSessioSingleton class ([5a42a5c](https://github.com/GruppOne/stalker-mobile-app/commit/5a42a5cb166c98b4abd620ee01f2345dc7e24a49))
* add custom Application class ([763d6a5](https://github.com/GruppOne/stalker-mobile-app/commit/763d6a5ffa50f8279291a274ad30834cb077528f))
* add getter for place id ([1e92c91](https://github.com/GruppOne/stalker-mobile-app/commit/1e92c910d3df2a8f327d5162e49152f585f75d72))
* add login activity ([273ea1e](https://github.com/GruppOne/stalker-mobile-app/commit/273ea1e04d2c3d6d7fb249274380df92d6b926f6))
* add methods for server requests ([218b926](https://github.com/GruppOne/stalker-mobile-app/commit/218b926fd0c19628fad0df1aa657fdd50a959531))
* add methods to check if a point is inside an organization ([adcce03](https://github.com/GruppOne/stalker-mobile-app/commit/adcce038e65cef15ab2fbee46b94c36864af44ac))
* **login:** add MVVM implementation for Login ([aed5c43](https://github.com/GruppOne/stalker-mobile-app/commit/aed5c430b6765948a265f18cf836f818cb6c1141))
* **mainpage:** add mainPage ([f5a34f7](https://github.com/GruppOne/stalker-mobile-app/commit/f5a34f78694730433b937ece31c4e062e93af24e))
* **mainpage:** add MVVM implementation for MainPage ([c0b77e4](https://github.com/GruppOne/stalker-mobile-app/commit/c0b77e4492d92aea4c944b0971fc8f3531b71557))
* add model class to login activity/viewmodel ([df20411](https://github.com/GruppOne/stalker-mobile-app/commit/df2041122f26ad557f735595c909eb8fb3ebdd68))
* add Organization class ([5b26636](https://github.com/GruppOne/stalker-mobile-app/commit/5b26636851fd70abed806e677b2406e544670691))
* add Place class ([663f49c](https://github.com/GruppOne/stalker-mobile-app/commit/663f49c5394fb4e4ae63598ee1e7dd9d2741a86b))
* add viewmodel to login activity ([9264176](https://github.com/GruppOne/stalker-mobile-app/commit/9264176242594189ba191d8f0e19988cfe2f6af3))
* add web request handling class ([7fd72c6](https://github.com/GruppOne/stalker-mobile-app/commit/7fd72c620bb8315a34e823d8bfcbdf8044191578))
* changed base activity ([0749f7e](https://github.com/GruppOne/stalker-mobile-app/commit/0749f7e193c8b76a24cb69346b065b946ab6fd04))


### Bug Fixes

* load fonts for bootstrap ([95c0faa](https://github.com/GruppOne/stalker-mobile-app/commit/95c0faa2398bc8bc32494c86d5ec4a4343c46db6))
* remove check for organizations in LocationNotifier ([3a5764a](https://github.com/GruppOne/stalker-mobile-app/commit/3a5764a6e4c4594031712251faefc59d68de05a1))
* set server port to 11111 ([c467fc9](https://github.com/GruppOne/stalker-mobile-app/commit/c467fc9eb226b755e7be258d22b630034ac9d6c0))


### Documentation

* add login uml diagram ([671b6ba](https://github.com/GruppOne/stalker-mobile-app/commit/671b6ba82bab21d8266ce50b2ef3f268325d7298))


### Configuration

* **android:** add adroid module for mockito ([a8839d5](https://github.com/GruppOne/stalker-mobile-app/commit/a8839d57b89ae16bb24501d60d292f6c05babd40))
* **android:** add missing package for testing ([39eb6d0](https://github.com/GruppOne/stalker-mobile-app/commit/39eb6d0d97335c7bf0209d9f94d34238fcd8da88))
* **deps:** add dependencies for viewModel and recyclerView ([b9c2200](https://github.com/GruppOne/stalker-mobile-app/commit/b9c2200437e813783cbe8a8773b77b35ab9fc57c))
* **deps:** add lombok ([9cde55d](https://github.com/GruppOne/stalker-mobile-app/commit/9cde55d7cc9bf2124fddd36bced8e550a0e31d17))
* **deps:** add mockito dependency for testing ([e81d415](https://github.com/GruppOne/stalker-mobile-app/commit/e81d415e0ea734947633dc975eefcd1914ec333f))
* **deps:** add powermock to mock static methods ([7629350](https://github.com/GruppOne/stalker-mobile-app/commit/7629350d02843df0105169a6f1a668074161d183))
* silence (some) useless warnings ([cf17ddd](https://github.com/GruppOne/stalker-mobile-app/commit/cf17dddd4d989703343506ba73c541b2f264a6f5))
* **geo:** add dependency to google location services ([6ef4aaf](https://github.com/GruppOne/stalker-mobile-app/commit/6ef4aaf795cb63c310a32359a90ac2a46d34723f))

## 0.1.0 (2020-03-28)


### Configuration

* **gradle:** bump gradle wrapper and add android lint options ([55228af](https://github.com/GruppOne/stalker-mobile-app/commit/55228af22edbce261c29063e7bcc202299532452))
* **idea:** change project settings ([347a34a](https://github.com/GruppOne/stalker-mobile-app/commit/347a34aa1a75bba2052c059b5bfe1ffb60368cd6))
* **idea:** include required idea plugins in project configuration ([10f2863](https://github.com/GruppOne/stalker-mobile-app/commit/10f28637b3a6514b7b97e79c457864dbfd02b3c4))
* add commitizen settings ([ac49f42](https://github.com/GruppOne/stalker-mobile-app/commit/ac49f425f542143dfceb97da68f3291f1cf6c69c))
* add dependencies ([72f45c9](https://github.com/GruppOne/stalker-mobile-app/commit/72f45c9014cb1ae20ff7af059021fac7497cb650))
* add project codestyle settings for ide ([052512c](https://github.com/GruppOne/stalker-mobile-app/commit/052512cf6126b297e75b23c25057fb6690c6bf9c))
* add scripts to simplify release process ([7f6e297](https://github.com/GruppOne/stalker-mobile-app/commit/7f6e297a1b64616e6a0095e81fb78698c0a4737c))
* add some required dependencies for intellij and vscode config ([eeb65c3](https://github.com/GruppOne/stalker-mobile-app/commit/eeb65c32c5832f9fa2c54f7e536f61a8653284ea))
* add user permissions ([b290aed](https://github.com/GruppOne/stalker-mobile-app/commit/b290aed511529614f80ec62956f7b444b3c2bff6))
* change commitizen adapter ([a218567](https://github.com/GruppOne/stalker-mobile-app/commit/a21856781770e270f8851a709f0ead7790b63750))
* ignore warnings ([afe8602](https://github.com/GruppOne/stalker-mobile-app/commit/afe8602eb5074b97649eb02422ebb8ece21687c3))
* init ([5b9e1ff](https://github.com/GruppOne/stalker-mobile-app/commit/5b9e1ff55bb5df3551fb35adb757461252796343))
* reformat files ([8468deb](https://github.com/GruppOne/stalker-mobile-app/commit/8468deb0fb3feea1d80c1d114fc37bd2cc6b1c1e))
* reformat files ([cdfc56b](https://github.com/GruppOne/stalker-mobile-app/commit/cdfc56ba0c05e86dafc284f97a4bacd7249ab203))
* set java version to 1.8 ([c4b73db](https://github.com/GruppOne/stalker-mobile-app/commit/c4b73db4e92724fded5c5d3951eb24979f6ef551))
