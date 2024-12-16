import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-android-get-installed-apps' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const AndroidInstalledApps = NativeModules.AndroidInstalledApps
  ? NativeModules.AndroidInstalledApps
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export type installedAppInfo = {
  packageName: string; // 패키지 이름 package name
  versionName: string; // 버전 이름 version name
  versionCode: number; // 버전 코드 version code
  firstInstallTime: number; // 처음 설치 시간 first install time
  lastUpdateTime: number; // 마지막 업데이트 시간 last update time
  appName: string; // 앱 이름 app name
  icon: string; // 아이콘(base64 이미지) icon(base64 image)
  apkDir: string; // apk 경로 apk path
  size: number; // 앱 크기 app size
};

export function multiply(a: number, b: number): Promise<number> {
  return AndroidInstalledApps.multiply(a, b);
}

export function getApps(): Promise<installedAppInfo[]> {
  return AndroidInstalledApps.getApps();
}

export function getNonSystemApps(): Promise<installedAppInfo[]> {
  return AndroidInstalledApps.getNonSystemApps();
}

export function getSystemApps(): Promise<installedAppInfo[]> {
  return AndroidInstalledApps.getSystemApps();
}
