# systemBarX [![](https://jitpack.io/v/Tobeyr1/systemBarX.svg)](https://jitpack.io/#Tobeyr1/systemBarX)
---------------------------
**systemBarX is a system status bar tool. The current version is not stable**
# Quick Setup
**Add it in your root build.gradle at the end of repositories:**

```java
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
**Then add the dependency:**
```java
dependencies {
	        implementation 'com.github.Tobeyr1:systemBarX:1.0.0-alpha'
	}
```
**External methods**

|setStatusBarColor() | 设置状态栏颜色|
|--|--|
|  setStatusBarTextColor()| 设置状态栏图标文字黑白色|
|--|--|
|  fitsSystemWindows()| 页面全面屏 |
|--|--|
|  setNavigationBarColor()| 设置顶部系统栏颜色黑白|
|--|--|
|  immersiveNavigationBar()| 是否隐藏底部系统栏|
|--|--|
|  getImeVisible()| 软键盘是否可见 |
|--|--|
|  immersiveStatusBar()| 是否隐藏状态栏 |
|--|--|
|  immersiveSystemBars()| 是否隐藏系统栏 |
|--|--|
|  setContentImmersion()| 将内容填充至状态栏（沉浸式） |
|--|--|
|  getStatusBarHeight()| 获取状态栏高度 |
|--|--|
|  getNavigationBarHeight()| 获取底部系统栏高度 |
|--|--|
