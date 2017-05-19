#! /bin/bash


# 字符串
#string="lalalalala"
#echo ${string:1:3}


# 数组
#array=("111111" "222222" "333333")
#echo "fork ${#array[@]}"


# 传入参数
#echo "command file: $0"
#echo "1st argumrnt: $1"
#echo "2nd argumrnt: $2"
#echo "3rd argumrnt: $3"


# 循环
#for arg in "$@"
#do
#	echo "tovo ${arg}"
#done


#for loop in 9 8 7 6 5
#do
#	echo "The value is: $loop"
#done


# while循环
#int=1
#while(( $int<=10 ))
#do
#	if [ $int == 5 ]
#	then
#		echo "echo for continue"
#		let "int++"
#		continue	# continue
#	fi

#	if [ $int == 8 ]
#	then
#		echo "echo for break"
#		break
#	fi

#	echo $int
#	let "int++"
#done


# 读取键盘输入
#echo '按下 <CTRL-D> 退出'
#echo -n '输入你最喜欢的电影名: '
#while read FILM
#do
#    echo "是的！$FILM 是一部好电影"
#done


# until循环
#int=1
#until(( $int==5 ))
#do
#	echo $int
#	let "int++"
#done


# 运算
#multi=`expr 3 \* 4`
#devide=`expr 12 / 2`
#echo "multi: ${multi}"
#echo "devide: ${devide}"


# 分支
#a=3
#b=4
#if [ $a != $b ]
#then
#	echo "a, b 不相等"
#else 
#	echo "a, b 相等"
#fi

#if [ $a -ge 2 ]
#then
#	echo "a >= 2"
#fi

# -eq:	==
# -ne:	!=
# -gt:	>
# -lt:	<
# -ge:	>=
# -le:	<=

#[ ! false ]			!  : 非
#[ $a -lt 20 -o $b -gt 100 ]	-o : or, | , 或
#[ $a -lt 20 -a $b -gt 100 ]	-a : and, & , 与

#[[ $a -lt 100 && $b -gt 100 ]]	&& : 逻辑与，与 java 中的语义一样
#[[ $a -lt 100 || $b -gt 100 ]]	|| : 逻辑或，与 java 中的语义一样


# case 分支
#echo '输入 1 到 4 之间的数字:'
#echo '你输入的数字为:'
#read aNum
#case $aNum in
#    1)  echo '你选择了 1'
#    ;;
#    2)  echo '你选择了 2'
#    ;;
#    3)  echo '你选择了 3'
#    ;;
#    4)  echo '你选择了 4'
#    ;;
#    *)  echo '你没有输入 1 到 4 之间的数字'
#    ;;
#esac


# 函数的定义与调用
#functionWithParam()
#{
#	echo "1st param: $1"
#	echo "2nd param: $2"
#	echo "3rd param: $3"
#}

#functionWithReturn()
#{
#	echo "这个函数会对输入的两个数字进行相加运算..."
#	echo "输入第一个数字: "
#	read aNum
#	echo "输入第二个数字: "
#	read anotherNum
#	echo "两个数字分别为 $aNum 和 $anotherNum !"
#	return $(($aNum+$anotherNum))
#}

#functionWithParam lala bobo cici
#functionWithReturn
#echo "输入的两个数字之和为 $? !"

# 文件输出
#df -h > cur.txt
#df -h >> add.txt	# 追加
