package com.androidinstalledapps

import com.facebook.react.bridge.*
import android.content.pm.PackageInfo
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import java.io.File

class AndroidInstalledAppsModule(private val reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String = NAME

    @ReactMethod
    fun getApps(promise: Promise) {
        Thread {
            try {
                val pm = reactContext.packageManager
                val pList = pm.getInstalledPackages(0)
                val list = Arguments.createArray()
                for (packageInfo in pList) {
                    val appInfo = Arguments.createMap()
                    appInfo.putString("packageName", packageInfo.packageName)
                    appInfo.putString("versionName", packageInfo.versionName)
                    appInfo.putDouble("versionCode", packageInfo.versionCode.toDouble())
                    appInfo.putDouble("firstInstallTime", packageInfo.firstInstallTime.toDouble())
                    appInfo.putDouble("lastUpdateTime", packageInfo.lastUpdateTime.toDouble())
                    appInfo.putString("appName", packageInfo.applicationInfo?.loadLabel(pm)?.toString()?.trim())
                    
                    val icon = packageInfo.applicationInfo?.let { pm.getApplicationIcon(it) }
                    appInfo.putString("icon", icon?.let { AndroidInstalledAppsUtility.convert(it) })
                    
                    val apkDir = packageInfo.applicationInfo?.publicSourceDir
                    appInfo.putString("apkDir", apkDir)
                    
                    apkDir?.let {
                        val file = File(it)
                        appInfo.putDouble("size", file.length().toDouble())
                    }
                    
                    list.pushMap(appInfo)
                }
                promise.resolve(list)
            } catch (ex: Exception) {
                promise.reject(ex)
            }
        }.start()
    }

    @ReactMethod
    fun getNonSystemApps(promise: Promise) {
        Thread {
            try {
                val pm = reactContext.packageManager
                val pList = pm.getInstalledPackages(0)
                val list = Arguments.createArray()
                for (packageInfo in pList) {
                    packageInfo.applicationInfo?.let { appInfo ->
                        if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) == 0) {
                            val appInfoMap = Arguments.createMap()
                            appInfoMap.putString("packageName", packageInfo.packageName)
                            appInfoMap.putString("versionName", packageInfo.versionName)
                            appInfoMap.putDouble("versionCode", packageInfo.versionCode.toDouble())
                            appInfoMap.putDouble("firstInstallTime", packageInfo.firstInstallTime.toDouble())
                            appInfoMap.putDouble("lastUpdateTime", packageInfo.lastUpdateTime.toDouble())
                            appInfoMap.putString("appName", appInfo.loadLabel(pm).toString().trim())
                            
                            val icon = pm.getApplicationIcon(appInfo)
                            appInfoMap.putString("icon", AndroidInstalledAppsUtility.convert(icon))
                            
                            val apkDir = appInfo.publicSourceDir
                            appInfoMap.putString("apkDir", apkDir)
                            
                            apkDir?.let {
                                val file = File(it)
                                appInfoMap.putDouble("size", file.length().toDouble())
                            }
                            
                            list.pushMap(appInfoMap)
                        }
                    }
                }
                promise.resolve(list)
            } catch (ex: Exception) {
                promise.reject(ex)
            }
        }.start()
    }

    @ReactMethod
    fun getSystemApps(promise: Promise) {
        Thread {
            try {
                val pm = reactContext.packageManager
                val pList = pm.getInstalledPackages(0)
                val list = Arguments.createArray()
                for (packageInfo in pList) {
                    packageInfo.applicationInfo?.let { appInfo ->
                        if ((appInfo.flags and ApplicationInfo.FLAG_SYSTEM) != 0) {
                            val appInfoMap = Arguments.createMap()
                            appInfoMap.putString("packageName", packageInfo.packageName)
                            appInfoMap.putString("versionName", packageInfo.versionName)
                            appInfoMap.putDouble("versionCode", packageInfo.versionCode.toDouble())
                            appInfoMap.putDouble("firstInstallTime", packageInfo.firstInstallTime.toDouble())
                            appInfoMap.putDouble("lastUpdateTime", packageInfo.lastUpdateTime.toDouble())
                            appInfoMap.putString("appName", appInfo.loadLabel(pm).toString().trim())
                            
                            val icon = pm.getApplicationIcon(appInfo)
                            appInfoMap.putString("icon", AndroidInstalledAppsUtility.convert(icon))
                            
                            val apkDir = appInfo.publicSourceDir
                            appInfoMap.putString("apkDir", apkDir)
                            
                            apkDir?.let {
                                val file = File(it)
                                appInfoMap.putDouble("size", file.length().toDouble())
                            }
                            
                            list.pushMap(appInfoMap)
                        }
                    }
                }
                promise.resolve(list)
            } catch (ex: Exception) {
                promise.reject(ex)
            }
        }.start()
    }

    @ReactMethod
    fun multiply(a: Double, b: Double, promise: Promise) {
        promise.resolve(a * b)
    }

    companion object {
        const val NAME = "AndroidInstalledApps"
    }
}
