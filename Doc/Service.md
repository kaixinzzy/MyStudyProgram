## Service分类
1、本地Service(Local Service)

> 运行在主线程的，所以不要做耗时操作，不然会ANR。

> 和当前应用在同一个进程中的service，彼此之间拥有共同的内存区域，所以对于某些数据的共享特别的方便和简单。

2、远程Service(Remote Service)
> 运行在独立进程的,可以做耗时操作。

> 主要牵扯到不同进程间的service访问。因为android的系统安全的原因导致了我们在不同的进程间无法使用一般的方式共享数据。
  在这里android为我们提供了一个AIDL工具。（android interface description language）android接口描述语言。

## 关闭Service
>StartService启动服务，记得stopService关闭服务。

>bindService绑定服务，记得unbindService关闭服务。

## Service 与 Thread的区别
1、从系统回收的 角度
> 当内存不足时，Service的oom_adj比Thread的oom_adj小；Thread会优先被回收。

2、耗时操作 角度：
> LocalService是不可以做耗时操作的。不过RemoteService可以。

3、生命周期 和 与界面交互 角度
> bindService可以一直在后台运行，与所有Activity交互。

> 当创建Thread的Activity关闭，Thread就无法交互数据了。