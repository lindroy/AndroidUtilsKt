# AndroidUtilsKt

[![](https://jitpack.io/v/Lindroy/AndroidUtilsKt.svg)](https://jitpack.io/#Lindroy/AndroidUtilsKt)

使用kotlin编写的Android工具类，主要采用扩展函数的方式。目前还在不断补充中。

本说明文档遵循如下的规则：

1. 根据工具类文件名的英文字母顺序排序。
2. 接收类一栏为“/”的表示该方法或属性为全局成员，可在任意地方调用；接收类有具体类名（如Any、String?等）的表示采用扩展成员的写法； 接收类一栏为“—”的表示采用静态类的写法，需要使用类名的形式调用里面的函数或属性，放在包“statics”中；
3. 成员包含函数和属性，函数名称后面需要加“()”，属性则不用。
4. 常量另外写一个表格。

## 配置方法

### 1、在工程gradle中添加：
```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```
### 2、添加如下依赖：

```
dependencies {
    implementation 'com.github.Lindroy:AndroidUtilsKt:latest-version'
}
```
`latest-version`参见图标`jitpack`后面的版本号。

### 3、在Application中初始化

```kotlin
        AndUtil.init(this)
            .setDefaultSpFile()     //设置SharePreferences的默认表名，默认为“sp_util”
            .setServerTimeFormat()  //设置时间格式化中服务器时间格式，默认为“yyyy-MM-dd'T'HH:mm:ss.SSS”
```
## 工具类文档

### [ActivityUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ActivityUtil.kt "ActivityUtil")：Activity工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| launchActivity()  | 启动一个Activity  |  Context  |  启动的Activity作为泛型传入  |

 ------------

### [AppManager](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/statics/AppManager.kt "AppManager")：App管理器

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| addActivity()   | Activity入栈  |  —  |   /  |
| removeActivity()   | Activity出栈  |  —  |  /   |
| finishActivity()   | 关闭一个Activity  |  — |  /  |
| currentActivity()   |  获取当前栈顶Activity |  — |  如果栈内元素为空，则返回null  |
| finishAllActivity()   |  清理栈中所有的Activity | — |    / |
| exitApp()  |  退出应用程序 | — |   /  |

 ------------

### [AppUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/AppUtil.kt "AppUtil")：应用信息工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| getAppVersionName()   | 获取应用版本名称，默认为本应用  | Context  | /  |
| getAppVersionCode()   | 获取应用版本号，默认为本应用  | Context  |  / |
| getAppSize()   | 获取应用大小，默认为本应用  | Context  | 返回值单位为b  |
| getAppIcon()   |获取应用图标，默认为本应用| Context  | 失败时返回null  |

 ------------

### [BitmapUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/BitmapUtil.kt "BitmapUtil")：
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| createBitmapSafely() | 获取应用版本名称，默认为本应用 | /  | / |
| viewToBitmap() | 将View转换为Bitmap  | / |  / |

 ------------

### [BrightnessUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/BrightnessUtil.kt "BrightnessUtil")：屏幕亮度工具类
设置系统屏幕亮度时需要动态申请系统设置权限：

```xml
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
```

```kotlin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //如果当前平台版本大于23平台
            if (!Settings.System.canWrite(mContext)) {
                //未获取权限
                val intent = with(Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)) {
                    data = Uri.parse("package:$packageName")
                    this
                }
                startActivityForResult(intent, 100)
            } else {
               //已经获得了权限
            }
        }
```

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isAutoBrightness  | 是否开启了自动亮度 | / | / |
| setAutoBrightness()  | 设置是否开启自动亮度  | / | 设置成功返回true  |
| systemBrightness |  获取/设置系统屏幕宽度 | / | 亮度范围为0~255 |
| windowBrightness | 获取/设置当前窗口亮度  | Activity | 亮度范围为0~1.0，1为最亮，默认为-1 |

 ------------

### [ClipboardUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ClipboardUtil.kt "ClipboardUtil")： 剪贴板工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | ------------ | :------------: |
| clipPlainText()   | 复制纯文本  | / | /  |

 ------------

### [DensityUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/DensityUtil.kt "DensityUtil")：像素单位转换工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| dp2px() | dp转px | / | / |
| px2dp() | px转dp | / | / |
| sp2px() | sp转px | / | / |
| px2sp() | px转sp | / | / |

 ------------

### [IntentUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/IntentUtil.kt "IntentUtil")：意图工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| launchSystemSetting()   | 打开系统设置界面  | — | /  |
| launchWifiSetting()   | 打开wifi设置界面  | — | / |
| launchDialPage()   | 打开拨号面板  | — | /  |
| callPhone()   | 直接拨打电话  | — | 动态权限  |
| launchBrowse()   | 调用浏览器并打开一个网页  | — | /  |
| launchCamera()   | 启动系统相机  | — | 动态权限  |
| sendSMS()   | 发送短信  | — | 动态权限  |

 ------------

### [KeyboardUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/KeyboardUtil.kt "KeyboardUtil")：软键盘工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| showKeyboard()  | 打开软键盘  | View  | /  |
| hideKeyboard()  | 关闭软键盘  | View  | /  |
| toggleKeyboard()  | 根据当前软键盘的状态做取反操作  | View  | /  |

 ------------

### [LogUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/logcat/LogUtil.kt "LogUtil")：日志工具类

#### 初始配置
`LogUtil`具有默认的配置，你也可以根据自己的需要自行配置。建议在`Application`中调用`setLogGlobalConfig()`做全局配置：
```kotlin
        AndUtil.init(this)
            .setLogGlobalConfig {
               ……
            }
```
配置如下：

| 方法名称 | 作用  | 默认值  | 备注  |
| :------------: | :------------: | :------------: | :------------: |
| setLogEnable() | 设置是否显示日志 | true  | / |
| setMethodCount() | 设置显示的方法数 | 1  | / |
| setMethodOffset() | 设置栈偏移量 | 0 | / |
| setShowThread() | 设置是否显示线程 | true | / |
| setShowGlobalTag() | 设置是否在临时Tag前面显示全局Tag | false  | / |
| setLogStrategy()  | 自定义日志打印策略 | / | / |
| setShowBorder()  | 设置是否显示边框 | true | 为了美观，建议显示 |
| setTag()  | 自定义日志打印策略 | LogUtil | / |

#### 对外API

| 成员名称 | 作用  | 接收类  | 备注  |
| :------------: | ------------ | :------------: | :------------: |
| d() | 打印Debug日志 | String? | 仅支持可空String，为null时打印“null” |
| d() | 打印Debug日志 | Any? | 支持如下类型：List、Array、Set和Map |
| dt()  | 打印带临时性tag的Debug日志 | String? | 仅支持可空String，为null时打印“null” |
| dt() | 打印带临时性tag的Debug日志 | Any? | 支持如下类型：List、Array、Set和Map |
| v() | 打印Verbose日志 | String? | 仅支持可空String，为null时打印“null” |
| vt() | 打印带临时性tag的Verbose日志 | String? | 仅支持可空String，为null时打印“null”  |
| i() | 打印Verbose日志 | String? | 仅支持可空String，为null时打印“null” |
| it() | 打印带临时性tag的Verbose日志 | String? | 仅支持可空String，为null时打印“null”  |
| w() | 打印Verbose日志 | String? | 仅支持可空String，为null时打印“null” |
| wt() | 打印带临时性tag的Verbose日志 | String? | 仅支持可空String，为null时打印“null”  |
| e() | 打印Error日志 | String? | 仅支持可空String，可抛出异常 |
| et() | 打印带临时性tag的Error日志 | String? | 仅支持可空String，可抛出异常 |
| wtf() | 打印wtf日志 | String? | 仅支持可空String，为null时打印“null” |
| wtft() | 打印带临时性tag的wtf日志 | String? | 仅支持可空String，为null时打印“null”  |
| json() | 打印格式化的json | String? | 等级为Debug，仅支持可空String |
| xml() | 打印格式化的xml | String? | 等级为Debug，仅支持可空String |
| buildLogConfig() | 设置LogUtil配置 | Any? | 会优先于AndUtil.setLogGlobalConfig()的全局设置，但不会覆盖它 |
| buildLogTempConfig() | 设置临时性的LogUtil配置 | Any? | 打印一次之后就会失效 |
| clearLogConfigs() | 清除所有的配置 | Any? | 清除后需要重新配置才能打印 |
| resetLogConfig() | 重置LogUtil配置为全局配置 | Any? | / |

`buildLogConfig()`和`buildLogTempConfig()`有两种书写形式，分别是DSL和链式调用：
DSL:
```kotlin
    buildLogConfig {
        setShowThread(false)
    }
```
链式调用（主要供Java代码使用）：
```kotlin
       buildLogTempConfig()
       .setShowThread(true)
       .build()
```
注意：链式调用最后需要调用`build()`方法，DSL则不必。

 ------------

### [NetworkUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/NetworkUtil.kt "NetworkUtil")：网络状态工具类
#### 常量

| 常量名  | 值 | 意义 |
| ------------ |  :------------: | ------------ |
| NETWORK_NONE | -1 | 没有网络 |
| NETWORK_UNKNOWN | -2 | 未知网络 |
| NETWORK_MOBILE | 1  | 移动网络 |
| NETWORK_WIFI | 2  | 无线网络 |

#### 成员名称

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| networkState | 获取当前的网络状态  | /  |  / |
| isWifi  | 是否是wifi  | /  | /  |
| isMobileNet  | 是否是移动网络  | /  | / |
| isNetworkConnect  | 网络是否连接  | /  | / |

 ------------

### [RegexUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/RegexUtil.kt "RegexUtil")

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isMobile | 校验内地手机号码 | String | / |
| isEmail | 校验电子邮箱 | String | / |
| isWebUrl | 校验网络链接 | String | / |
| isNumber | 校验数字 | String | / |
| isPositiveInt | 校验正整数 | String | / |
| isNegativeInt | 校验负整数 | String | / |
| isLetter | 校验字母 | String | / |
| isUpperCaseLetter | 校验大写字母 | String | / |
| isLowerCaseLetter | 校验小写字母 | String | / |
| isChinese | 校验汉字 | String | / |
| isQQ | 校验QQ号码 | String | / |
| isMacaoMobile | 校验澳门手机号码 | String | / |

 ------------

### [ResourceUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ResourceUtil.kt "ResourceUtil")：资源工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| getResColor()   | 获取颜色  | /  | /  |
| getResDrawable()   | 获取图片资源  | / | 返回值可以为null  |

 ------------

### [ScreenUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ScreenUtil.kt "ScreenUtil")：屏幕相关工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| screenWidth  | 获取屏幕宽度  | / | /  |
| screenHeight | 获取屏幕高度  | / | /  |
| screenDensity | 获取屏幕密度  | / | /  |
| screenDPI | 获取屏幕DPI  | Any  | / |
| screenOrientation  | 获取屏幕方向  | / | /  |
| isLandscape | 是否是横屏  | / | / |
| isPortrait | 是否是竖屏  | / | /  |
| setScreenLandscape() | 设置横屏  | Activity  | /  |
| setScreenPortrait() | 设置竖屏  | Activity  | /  |
| toggleScreenOrientation() | 横竖屏切换  | Activity  | /  |
| lockScreenOrientation() | 锁定屏幕方向  | Activity  | /  |
| unlockScreenOrientation() | 取消锁定屏幕方向  | Activity | /  |
| isFullScreen | 判断和设置是否全屏 | Activity | 赋值为true设置成全屏 |
| setFullScreen() | 设置全屏 | Activity | / |
| setNonFullScreen() | 设置非全屏 | Activity | / |
| isScreenOn | 屏幕是否亮屏 | / | / |
| isScreenOff | 屏幕是否熄灭 | / | / |
| isScreenLocked | 屏幕是否锁屏 | / | / |
| isScreenUnlocked | 屏幕是否解锁 | / | / |
| isKeepScreenOn | 判断和设置是否保持屏幕常亮 | Activity | 只作用于当前窗口 |
| setKeepScreenOn() | 保持屏幕常亮 | Activity | 只作用于当前窗口 |
| setNonKeepScreenOn | 取消保持屏幕常亮 | Activity | 只作用于当前窗口 |
| getScreenAutoLockTime() | 获取自动锁屏时间 | / | 需要WRITE_SETTINGS权限 |
| setScreenAutoLockTime() | 设置自动锁屏时间 | / | 需要WRITE_SETTINGS权限 |
| setScreenAutoLockNever() | 设置永不自动锁屏 | / | 需要WRITE_SETTINGS权限 |

 ------------
### [SDCardUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/SDCardUtil.kt "SDCardUtil")：SD卡工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isSDCardMounted | 判断SD卡是否已挂载 | / | / |
| sdCardPath | 获取SD卡路径 | / | 失败时返回空字符 |
| sdCardTotalSize | 获取SD卡的总大小 | / | 失败时返回-1 |
| sdCardAvailableSize | 获取SD卡可用空间大小 | / | 失败时返回-1 |


 ------------

### [SpanUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/SpanUtil.kt "SpanUtil")

#### 单一特效文字
如果给某段文字设置的特效只有一种，可以使用如下比较简单的函数：

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| setFgColorSpan() | 设置前景色 | TextView | / |
| setBgColorSpan() | 设置背景色 | TextView | / |
| setStrikethroughSpan() | 设置删除线 | TextView | / |
| setUnderlineSpan() | 设置下划线 | TextView | / |
| setClickableSpan() | 设置可点击文本 | TextView | / |
| setStyleSpan() | 设置文字样式 | TextView | / |
| setBoldSpan() | 设置粗体字 | TextView | / |
| setItalicSpan() | 设置斜体字 | TextView | / |
| setBoldItalicSpan() | 设置粗体和斜体字 | TextView | / |
| setUrlSpan() | 设置超链接 | TextView | / |
| setRelativeSizeSpan() | 设置文字相对大小 | TextView | / |
| setSuperScriptSpan() | 设置文字上标 | TextView | / |
| setSubscriptSpan() | 设置文字下标 | TextView | / |

#### 多种特效文字
对于需要给同一段文字设置多种特效需求，这里提供了另一套可以链式调用的函数，首先使用如下的扩展函数开启链式调用：

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| buildSpan | 设置前景色 | TextView | / |

然后则可以使用构造类`SpanHelper.Builder`中的函数去设置特效了：

| 函数名称 | 作用 | 备注 |
| ------------ | ------------ | :------------: |
| setStart() | 设置文字特效开始的位置下标 | / |
| setEnd() | 设置文字特效结束的位置下标 | / |
| setStartEnd() | 同时设置文字特效开始和结束的位置 | / |
| setFlag() | 设置标识 | 一共4种 |
| setFgColor() | 设置前景色 | / |
| setBgColor() | 设置背景色 | / |
| setStrikethrough() | 设置删除线 | / |
| setUnderline() | 设置下划线 | / |
| setClickable() | 设置可点击文本 | / |
| setUrl() | 设置超链接 | / |
| setStyle() | 设置文字样式 | / |
| setBold() | 设置文字为粗体 | / |
| setItalic() | 设置文字为斜体 | / |
| setBoldItalic() | 设置文字为粗体和斜体 | / |
| setRelativeSize() | 设置文字相对大小 | / |
| setSuperScript() | 设置文字上标 | / |
| setSubscript() | 设置文字下标 | / |
| create() | 设置特效完毕 | / |

调用示例：

```kotlin
tvSuperScript.buildSpan("设置文字上标：210=1024")
    .setStartEnd(8, 10)
    .setRelativeSize(0.6F)
    .setSuperScript()
    .create()
```

或者使用DSL的风格：

```kotlin
tvSubscript.buildSpan("设置文字下标：H20") {
    setStartEnd(8, 9)
    setRelativeSize(0.6F)
    setSubscript()
    create()
}
```
 ------------

### [SpUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/SpUtil.kt "SpUtil")：SharedPreferences工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| setSpDefaultFile()  |  设置默认的表名 | / | internal  |
| putSpString()  |  存储字符串 | /  | /  |
| getSpString()  | 取出存储的字符串 | / | /  |
| putSpBoolean() | 存储布尔值 | / | /  |
| getSpBoolean() | 取出存储的布尔值 | / |  / |
| putSpInt() | 存储Int值 | / | /  |
| getSpInt() | 取出存储的Int值 | / | / |
| putSpLong() | 存储Long值 | / | /  |
| getSpLong() | 取出存储的Long值 | / | / |
| putSpFloat() | 存储Float值 | / | /  |
| getSpFloat() | 取出存储的Float值 | / | / |
| putSpStrSet() | 存储StringSet | / | / |
| getSpStrSet() | 取出存储的StringSet | / |  /  |
| putSp() | 保存数据 | / | 数据类型由传入的值确定  |
| getSp() |取出数据 | / | 数据类型由传入的默认值确定  |
| deleteSpKey() | 删除某条数据 | / | /  |
| clearSp() | 清除SharedPreferences的数据 | 不输入表名则清除默认表中的数据 | /  |

 ------------

### [StatusBarUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/statusbar/StatusBarUtil.kt "StatusBarUtil")：状态栏工具类

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| getStatusBarHeight()  | 获取状态栏高度  | Context | 返回值单位为px |
| setStatusBarColor | 设置纯颜色状态栏  | Activity  | 参数为ColorInt |
| setStatusBarColorRes()  | 设置纯颜色状态栏 | Activity | 参数为ColorRes  |
| setTransParentStatusBar() | 设置透明状态栏 | Activity | 在界面创建时调用才能生效 |
| setGradientStatusBar() | 设置渐变色状态栏 | Activity | 参数为Drawable或DrawableRes |
| setStatusBarDarkMode() | 设置状态栏白色字体图标(深色模式) | Activity | / |
| setStatusBarLightMode() | 设置状态栏黑色字体图标(浅色模式) | Activity | / |

 ------------

### [TimeUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/TimeUtil.kt "TimeUtil")：时间工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| serverFormat  | 服务器返回的时间格式  | / | internal，仅用于全局配置  |
| currentYear | 获取当前年份  | /  | / |
| currentMonth | 获取当前年份 | /  | / |
| currentDay | 获取当前日 | / | / |
| currentTimeMillis | 获取当前时间戳 | / | / |
| formatCurrentDate  | 获取当前日期 | /  | 默认格式为"yyyy-MM-dd"  |
| formatCurrentDateTime  | 获取当前时间  | / | 默认格式为"yyyy-MM-dd HH:mm" |
| formatCurrentTime | 获取当前时间 | / | "HH:mm" |
| formatTimeYMD | 将服务器时间格式转换为年月日 | String | / |
| formatTimeYMDChinese | 将服务器时间格式转换为年月日（带汉字）  | String | / |
| formatTimeHM | 将服务器时间格式转换为时分 | String | / |
| formatTimeYMDHM | 将服务器时间格式转换为年月日时分 | String | / |
| formatTimeYMDHMS | 将服务器时间格式转换为年月日时分秒 | String | / |
| formatRelativeTime | 获取某个时间与当前时间的比较值 | String | / |

 ------------

### [ToastUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ToastUtil.kt "Toast工具类")：Toast工具类
已去除小米手机自带的应用名称。

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| shortToast()  | 显示短Toast  | / | /  |
| longToast()  | 显示长Toast  | / |  / |

 ------------

###  [VibratorUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/statics/VibratorUtil.kt "VibratorUtil")
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| vibrator  | 获取Vibrator实例 | — | / |
| hasVibrator | 检测设备是否具有振动器 | — | / |
| vibrate() | 开启振动 | — | / |
| cancel() | 取消振动 | — | / |

  ------------

### [ViewUtil](https://github.com/Lindroy/AndroidUtilsKt/blob/master/androidutilskt/src/main/java/com/lindroid/androidutilskt/extension/ViewUtil.kt "ViewUtil")：View工具类
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | :------------: |
| isVisible  | 当前View是否可见  | View | /  |
| isInvisible | 当前View是否不可见  | View | / |
| isGone | 当前View是否隐藏  | View | /  |
| setGone()  | 将View设置为隐藏  | View | /  |
| setVisible()  | 将View设置为可见  | View |  / |
| setInvisible()  | 将View设置为不可见  | View |  / |
| setWidth()  | 设置View的宽度  | View |  / |
| setHeight()  | 设置View的高度  | View |  / |
| setWidthAndHeight()  | 设置View的宽度和高度  | — |  / |
| setPadding() | 设置View的padding  | View |  / |
| viewHeight  | 获取View的高度  | View | 如果是“math_parent”属性则无法获取，值为0。 |
| viewWidth  | 获取View的宽度  | View | 同上 |
| textString | 获取EditText的字符内容  | EditText | 同上 |

## 感谢

[Anko](https://github.com/Kotlin/anko "Anko")      
[AndroidUtilCode](https://github.com/Blankj/AndroidUtilCode "AndroidUtilCode")      
[Qmui](https://qmuiteam.com/android "Qmui")     
[Logger](https://github.com/orhanobut/logger "Logger")
