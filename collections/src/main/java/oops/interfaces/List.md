## 接口 List

1. subList 方法返回一个List，对该List所做的改动会反映到原List上

## 接口 ListIterator
请注意两个变量：cursor 和 lastRet

1. nextIndex(): 返回下个next() 返回元素的 index，如果已经到List尾则返回该List的size()
    cursor

2. previousIndex(): 返回下个previous() 返回元素的 index，如果已经到List头则返回 -1
    cursor - 1

3. remove(): 删除上次next()或previous()返回的元素。调用完next()或previous()后只能调用一次remove()
    remove element in lastRet

4. set(): 设置上次next()或previous()返回的元素。add()/remove()后无法set()
    set element in lastRet

5. cursor 初始位置为0，
    next(): 返回cursor位置元素，cursor++，lastRet为cursor的上一次位置
    previous(): 返回 cursor - 1 位置元素，cursor--，lastRet = cusor

6. add(): 添加于cursor位置，原cursor位置及以后元素往后移一位,cursor++,lastRet = -1