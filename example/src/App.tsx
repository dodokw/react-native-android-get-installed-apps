import { useState, useEffect } from 'react';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';
import {
  multiply,
  getApps,
  getNonSystemApps,
  getSystemApps,
} from 'react-native-android-get-installed-apps';

export default function App() {
  const [result, setResult] = useState<number | undefined>();

  useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

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

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
      <TouchableOpacity style={styles.button} onPress={getInstalledAppName}>
        <Text>Get Installed Apps</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={getNonSystemAppName}>
        <Text>Get Non System Apps</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={getSystemAppName}>
        <Text>Get System Apps</Text>
      </TouchableOpacity>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  button: {
    padding: 20,
    marginTop: 20,
    backgroundColor: 'blue',
  },
});

// package com.androidinstalledapps

// import com.facebook.react.bridge.ReactApplicationContext
// import com.facebook.react.bridge.ReactContextBaseJavaModule
// import com.facebook.react.bridge.ReactMethod
// import com.facebook.react.bridge.Promise

// class AndroidInstalledAppsModule(reactContext: ReactApplicationContext) :
//   ReactContextBaseJavaModule(reactContext) {

//   override fun getName(): String {
//     return NAME
//   }

//   // Example method
//   // See https://reactnative.dev/docs/native-modules-android
//   @ReactMethod
//   fun multiply(a: Double, b: Double, promise: Promise) {
//     promise.resolve(a * b)
//   }

//   companion object {
//     const val NAME = "AndroidInstalledApps"
//   }
// }
