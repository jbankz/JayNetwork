# JayNetwork
A network library that handles network changes

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
	        implementation 'com.github.jbankz:JayNetwork:v1.0.0'
	}
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

Call this method to start observing for network change

```groovy
     observeNetwork();
```

Call this method to stop observing for network change

```groovy
     unObserveNetwork();
```


# MIT License

Copyright (c) 2019 Jaycee Victor

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

