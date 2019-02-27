# AndroidUtilsKt

接收类为“/”的表示该工具类并非扩展方式的写法，需要使用类名的形式调用里面的函数或熟悉。

### Activity工具类：ActivityUtil
| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| launchActivity  | 启动一个Activity  |  Context  |  启动的Activity作为泛型传入  |

------------

### App管理器：AppManager

| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| addActivity  | Activity入栈  |  /  |    |
| finishActivity  | Activity出栈  |  /  |   |
| currentActivity  |  获取当前栈顶Activity |  /  |   |
| finishAllActivity  |  清理栈中所有的Activity | /  |    |
| exitApp |  退出应用程序 | /  |    |

------------

### 应用信息工具类：AppUtil
| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| getAppVersionName  | 获取应用版本名称，默认为本应用  | Context  |   |
| getAppVersionCode  | 获取应用版本号，默认为本应用  | Context  |   |
| getAppSize  | 获取应用大小，默认为本应用  | Context  | 返回值单位为b  |
| getAppIcon  |获取应用图标，默认为本应用| Context  | 失败时返回null  |

------------

### 像素单位转换工具类：DensityUtil
| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| dp2px  | dp转px  | Context  |   |
| px2dp  | px转dp  | Context  |   |
| sp2px  | sp转px  | Context  |   |
| px2sp  | px转sp  | Context  |   |

------------

### Toast工具类：ToastUtil
已去除小米手机自带的应用名称。

| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: |------------ |
| shortToast  | 显示短Toast  | Context  |   |
| longToast  | 显示长Toast  | Context  |   |

------------

### 资源工具类：ResourceUtil
| 方法名  | 作用  | 接收类  | 备注  |
| ------------ | ------------ | :------------: | ------------ |
| getResColor  | 获取颜色  | Context  |   |
| getResDrawable  | 获取图片资源  | Context  | 返回值可以为null  |
