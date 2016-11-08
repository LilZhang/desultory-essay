## 接口Map

1. Map 的前身是一个抽象类: java.util.Directionary

2. Map 提供三个视图: 
- key set
- value collection
- kv set

3. 危险操作：将 Map的key 内某个元素更改为与别的 key eqauls() (比如更改该元素使其 HashCode 与另一个 key 相等)，这样的操作会使该 Map 处与不可预料的状态

4. 默认两个构造器：一个无参构造器，一个接受其它 Map 的构造器
