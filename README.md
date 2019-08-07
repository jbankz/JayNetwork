# JayNetwork
A network library that handles network changes

JayNetwork
============

Library projects
--------------------

To use JayNetwork library in your project, Add the JitPack repository to your build file:

```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
Add the dependency

```groovy
dependencies {
	        implementation 'com.github.jbankz:JayNetwork:v1.0'
	}
```

and then apply it in your module:

```groovy
apply plugin: 'com.android.library'
apply plugin: 'com.jakewharton.butterknife'
```

Now make sure your activity extends the  `NetworkManagerActivity` instead of `AppCompactivity`.

```java
class MainActivity extends NetworkManagerActivity {
        ...
        ...
        ...
}
```

Override this method to get your connectivity state

```java
    @Override
    public void isConnected(boolean status) {
        super.isConnected(status);
        // do something if the network is connected
    }
```

Override this method to get your network speed if its fast or not

```java
    @Override
    public void isConnectionFast(boolean isFast) {
        super.isConnectionFast(isFast);
        // do something if the network is fast
    }
```

Override this method to know the type of network that is been conncted to

```java
     @Override
    public void networkType(ConnectivityType type) {
        super.networkType(type);
        // switch through the type of network connected to e.g WIFI, MOBILE or NONE
    }
```

Override this method to start observing for network change

```groovy
     observeNetwork();
```

Override this method to stop observing for network change

```groovy
     unObserveNetwork();
```
