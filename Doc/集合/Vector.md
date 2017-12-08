##### 一、特点：
> 1. 线程安全
> 2. 变长（长度自动调节）
> 3. 最大容量是 2^32 – 1

##### 二、继承、实现接口
> 1. Vector 实现 List 接口，继承 AbstractList 类，所以我们可以将其看做队列，支持相关的添加、删除、修改、遍历等功能。
> 2. Vector 实现 RandmoAccess 接口，即提供了随机访问功能，提供提供快速访问功能。在 Vector 我们可以直接访问元素。
> 3. Vector 实现了 Cloneable 接口，支持 clone() 方法，可以被克隆。

##### 三、构造函数
```
//无参构造函数
Vector()

//capacity是Vector的默认容量大小。当由于增加数据导致容量增加时，每次容量会增加一倍。
Vector(int capacity)

//capacity是Vector的默认容量大小，capacityIncrement是每次Vector容量增加时的增量值。
Vector(int capacity, int capacityIncrement)

//创建一个包含collection的Vector
Vector(Collection<? extends E> collection)
```

##### 四、遍历
1. 随机访问【速度最快】--因为 Vector 实现了 RandmoAccess 接口，可以通过下标来进行随机访问。
```
for(int i = 0 ; i < vec.size() ; i++){
    value = vec.get(i);
}
```
2. 迭代器
```
Iterator it = vec.iterator();
while(it.hasNext()){
    value = it.next();
    //do something
}
```
3. for 循环
```
for(Integer value:vec){
    //do something
}
```
4. Enumeration 循环
```
Vector vec = new Vector<>();
Enumeration enu = vec.elements();
while (enu.hasMoreElements()) {
    value = (Integer)enu.nextElement();
}
```
