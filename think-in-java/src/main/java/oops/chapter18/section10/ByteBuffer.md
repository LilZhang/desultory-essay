# ByteBuffer 速记

## 备忘
如果要从头读/写，一定要将position 推回到0。参考如下：

### 供channel读
- 可新分配空间(allocate)
- 若要重复利用ByteBuffer，一定要先clear()
- 读完以后，用flip()精简一下

### 供channel写
- 可直接用待写内容wrap(wrap)
- 若要重复利用ByteBuffer，一定要先clear(),好像clear并不管用。

## 四元素
### mark
- 可以在数组感兴趣的地方(index)调用mark()，打下一个标记。
- 可以调用reset()回到上一个标记处。

### position
- 指针游标，默认停在数组第一个元素
- 可用 position(int pos) 手动调节
- ByteBuffer的初始化不会移动指针游标，批量添加会移动游标
- 每次读（get()）/写（put()），都会把指针游标顶到下一个index
- 指定序号(index)的读（get(int index)）/写（put(int index, T t)），不会移动游标

### limit
- 该ByteBuffer可用空间的大小，默认与capacity相同
- 可用 limit(int lim) 手动调节
- 准备读ByteBuffer之前可以用 flip() 调节一下limit并将position推回0，避免打出后面的0, 0, 0, 0.....


### capacity
- 该ByteBuffer内部数组的真实大小
- 自然也无法改变大小

## 方法
### clear()
- clear the buffer, set `position` 0 and set `limit` the value of capacity. usually invoked before read and write.
- `buffer = [all 0], pos = 0, lim = cap`

### flip()
- set `limit` the current value of position, then set position 0. usually invoked after channel.read(ByteBuffer buffer) and required to load data from this buffer.
- `lim = pos`; then `pos = 0`

### remaining()
- return (lim - pos)

### hasRemaining()
- return if elements exists between `position` and `limit`