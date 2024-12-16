# react-native-android-get-installed-apps

A React Native module for Android that provides information about installed applications on the device.
안드로이드에 디베이스에 설치된 앱 리스트를 가져옵니다.

## Installation

```sh
npm install react-native-android-get-installed-apps
```

```sh
yarn add react-native-android-get-installed-apps
```

## Usage

```js
import {
  getApps,
  getNonSystemApps,
  getSystemApps,
} from 'react-native-android-get-installed-apps';

//getting all installed app
const getInstalledAppName = () => {
  console.log('getting App');
  getApps()
    .then((app) => {
      if (app) {
        console.log('apps::List::', app);
      } else {
        console.warn('getting Apps data fail');
      }
    })
    .catch((err) => console.error(err));
};

//getting all non system apps
const getNonSystemAppName = () => {
  console.log('getting App');
  getNonSystemApps()
    .then((app) => {
      if (app) {
        console.log('apps::List::', app);
      } else {
        console.warn('getting Apps data fail');
      }
    })
    .catch((err) => console.error(err));
};

//getting all system apps
const getSystemAppName = () => {
  console.log('getting App');
  getSystemApps()
    .then((app) => {
      if (app) {
        console.log('apps::List::', app);
      } else {
        console.warn('getting Apps data fail');
      }
    })
    .catch((err) => console.error(err));
};
```

## getApps, getNonSystemApps, getSystemAppName Properties

| Property Name    | Type   | Description (KR)      | Description (EN)    |
| ---------------- | ------ | --------------------- | ------------------- |
| packageName      | string | 패키지 이름           | package name        |
| versionName      | string | 버전 이름             | version name        |
| versionCode      | number | 버전 코드             | version code        |
| firstInstallTime | number | 처음 설치 시간        | first install time  |
| lastUpdateTime   | number | 마지막 업데이트 시간  | last update time    |
| appName          | string | 앱 이름               | app name            |
| icon             | string | 아이콘(base64 이미지) | icon (base64 image) |
| apkDir           | string | apk 경로              | apk path            |
| size             | number | 앱 크기               | app size            |

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
