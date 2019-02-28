# AndroidUtilsKt

1. 根据工具类文件名的英文字母顺序排序；
2. 接收类为“/”的表示该工具类并非扩展方式的写法，需要使用类名的形式调用里面的函数或属性；
3. 成员包含函数和属性，函数名称后面需要加“()”，属性则不用；
4. 常量另外写一个表格。


### Activity工具类：ActivityUtil

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| launchActivity()  | 启动一个Activity  |  Context  |  启动的Activity作为泛型传入  |

------------

### App管理器：AppManager

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| addActivity()   | Activity入栈  |  /  |    |
| removeActivity()   | Activity出栈  |  /  |    |
| finishActivity()   | 关闭一个Activity  |  /  |   |
| currentActivity()   |  获取当前栈顶Activity |  /  |   |
| finishAllActivity()   |  清理栈中所有的Activity | /  |    |
| exitApp()  |  退出应用程序 | /  |    |

------------

### 应用信息工具类：AppUtil
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| getAppVersionName()   | 获取应用版本名称，默认为本应用  | Context  |   |
| getAppVersionCode()   | 获取应用版本号，默认为本应用  | Context  |   |
| getAppSize()   | 获取应用大小，默认为本应用  | Context  | 返回值单位为b  |
| getAppIcon()   |获取应用图标，默认为本应用| Context  | 失败时返回null  |

------------

### 剪贴板工具类：ClipboardUtil

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | ------------ | ------------ |
| clipPlainText()   | 复制纯文本  | Context |   |

------------

### 像素单位转换工具类：DensityUtil
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| dp2px()   | dp转px  | Context  |   |
| px2dp()   | px转dp  | Context  |   |
| sp2px()   | sp转px  | Context  |   |
| px2sp()   | px转sp  | Context  |   |

------------

### 软键盘工具类：KeyboardUtil

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| showKeyboard()  | 打开软键盘  | View  |   |
| hideKeyboard()  | 关闭软键盘  | View  |   |
| toggleSoftInput()  | 根据当前软键盘的状态做取反操作  | View  |   |


------------

### 网络状态工具类：NetworkUtil
#### 常量

| 常量名  | 值 | 意义 |
| ------------ | ------------ | ------------ |
| NETWORK_NONE | -1 | 没有网络 |
| NETWORK_UNKNOWN | -2 | 未知网络 |
| NETWORK_MOBILE | 1  | 移动网络 |
| NETWORK_WIFI | 2  | 无线网络 |

#### 成员名称

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| getNetworkState()   | 获取当前的网络状态  | Context  |   |
| isWifi  | 是否是wifi  | Context  |   |
| isMobileNet  | 是否是移动网络  | Context  |   |
| isNetworkConnect  | 网络是否连接  | Context  |   |

------------

### 资源工具类：ResourceUtil
| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| getResColor()   | 获取颜色  | Context  |   |
| getResDrawable()   | 获取图片资源  | Context  | 返回值可以为null  |

------------

### 屏幕相关工具类：ScreenUtil

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| getScreenWidth()  | 获取屏幕宽度  | Context  |  |
| getScreenHeight()  | 获取屏幕高度  | Context  |  |
| getScreenDensity()  | 获取屏幕密度  | /  |  |
| getScreenDPI()  | 获取屏幕DPI  | /  |  |
| setScreenLandscape()  | 设置横屏  | Activity  |  |
| setScreenPortrait()  | 设置竖屏  | Activity  |  |
| screenOrientation  | 获取屏幕方向  | Context  |  |
| isLandscape  | 是否是横屏  | Context  |  |
| isPortrait  | 是否是竖屏  | Context  |  |
| setScreenBrightness()  | 设置屏幕亮度  | Activity  | 亮度范围为0~1，1为最亮，默认为-1 |

------------

### SharedPreferences工具类：SpUtil

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| setSpDefaultFile()  |  设置默认的表名 | /  | internal  |
| putSpString()  |  存储字符串 | Context  |   |
| getSpString()  | 取出存储的字符串 | Context |   |
| putSpBoolean() | 存储布尔值 | Context |   |
| getSpBoolean() | 取出存储的布尔值 | Context |   |
| putSpInt() | 存储Int值 | Context |   |
| getSpInt() | 取出存储的Int值 | Context |  |
| putSpLong() | 存储Long值 | Context |   |
| getSpLong() | 取出存储的Long值 | Context |  |
| putSpFloat() | 存储Float值 | Context |   |
| getSpFloat() | 取出存储的Float值 | Context |  |
| putSpStrSet() | 存储StringSet | Context | |
| getSpStrSet() | 取出存储的tringSet | Context |   |
| putSpNumber() | 保存Long、Float和Int数据 | Context | 不包含Double  |
| getSpNumber() | 取出保存Long、Float和Int数据 | Context |   |
| deleteSpKey() | 删除某条数据 | Context |   |
| clearSp() | 清除SharedPreferences中的数据 | 不输入表名则清除默认表中的 |   |

------------

### Toast工具类：ToastUtil
已去除小米手机自带的应用名称。

| 成员名称 | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| shortToast()  | 显示短Toast  | Context  |   |
| longToast()  | 显示长Toast  | Context  |   |