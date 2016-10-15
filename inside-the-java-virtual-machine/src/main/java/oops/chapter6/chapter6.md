# ClassFile



## magic[u4]
- 0x CA FE BA BE

## minor_version[u2]
- eg. 0x 00 00 : 0

## major_version[u2]
- eg. 0x 00 33 : 51

## constant_pool_count[u2]
- eg. 0x 00 2b : 43

## constant_pool[cp_info] * (constant_pool_count - 1)
- index 从 #1 开始计数，但缺失的 #0 仍被计入`constant_pool_count`内
- 详见: info_table > cp_info

## access_flag[u2]
- 修饰符
- eg. 0x00 20 - ACC_SUPER(0x0020)
- 详见: options > access_flags (for class file)

## this_class[u2]
- blank: 		0x00
- class_index[u1]: 	CONSTANT_Class
- eg. 0x00 0a - #10

## super_class[u2]
- blank: 		0x00
- class_index[u1]: 	CONSTANT_Class
- eg. 0x00 0b - #11

## interfaces_count[u2]
- eg. 0x00 00 - 0

## interfaces[u2] * interfaces_count
- blank: 		0x00
- class_index[u1]: 	CONSTANT_Class
- 仅按顺序列出 implements 与 extends 关键字后面的接口
- 

## fields_count[u2]
- static 与 non-static 属性的总数
- 不列出父类属性
- 编译器可能会添加属性(比如 inner class 里指向 outter class 的实例属性)
- 被添加的实例属性拥有 attribute - `Synthetic` 
- eg. 0x00 00 - 0

## fields[field_info] * fields_count
- 详见: info_table > field_info

## methods_count[u2]
- static 与 non-static 方法的总数
- 不列出父类方法

## methods[method_info]
- 详见: info_table > method_info

## attributes_count[u2]
- attribute_info 总数

## attributes[attribute_info]
- jvm实现了两种属性: SourceCode, InnerClass

===
# type
## base_type
- B	byte
- C	char
- D	double
- F	float
- I	int
- J	long
- S	short
- Z	boolean

## object_type
- Ljava/lang/Object;	java.lang.Object
- Ljava/util/List;	java.util.List

## array_type
- [I			int[] iArray;
- [[J			long[][] lArray;
- [Ljava/lang/Object;	Object[] objects;

# descriptor
## field_descriptor
- ()I			int getInt();
- ()Ljava/lang/String;	String getString();
- ([Ljava/lang/String;)V void main(String[] args);
- ()V			void wait();
- (JI)V			void wait(long timeout, int nanos);
- (ZILjava/lang/String;II)Z boolean regionMatches(boolean ignoreCase, int to, 
String other, int offset, int len);
- ([BII)I		int read(byte[] b, int off, int len);

## method_descriptor

# options

## access_flags (for class file)
- ACC_PUBLIC	0x 00 01
- ACC_FINAL	0x 00 10	// class only
- ACC_SUPER	0x 00 20
- ACC_INTERFACE	0x 02 00	// interface only
- ACC_ABSTRACT	0x 04 00

## access_flags (for field_info)
- ACC_PUBLIC	0x 00 01
- ACC_PRIVATE	0x 00 02	// class field only
- ACC_PROTECTED	0x 00 04	// class field only
- ACC_STATIC	0x 00 08
- ACC_FINAL	0x 00 10
- ACC_VOLATILE	0x 00 40	// class field only
- ACC_TRANSIENT	0x 00 80	// class field only

## access_flags (for method_info)
- ACC_PUBLIC	0x 00 01	// class method and all interface method
- ACC_PRIVATE	0x 00 02	// class method only
- ACC_PROTECTED	0x 00 04	// class method only
- ACC_STATIC	0x 00 08	// class method only
- ACC_FINAL	0x 00 10	// class method only
- ACC_SYNCHRONIZED	0x 00 20// class method only
- ACC_NATIVE	0x 01 00	// class method only
- ACC_ABSTRACT	0x 04 00	// class method and all interface method
- ACC_STRICT	0x 08 00	// <clinit>

## access_flags (for inner_classes_info)
- ACC_PUBLIC	0x 00 01
- ACC_PRIVATE	0x 00 02
- ACC_PROTECTED	0x 00 04
- ACC_STATIC	0x 00 08
- ACC_FINAL	0x 00 10
- ACC_INTERFACE	0x 02 00
- ACC_ABSTRACT	0x 04 00

## constant_tag
- CONSTANT_Utf8		0x 01 - 1
- CONSTANT_Integer	0x 03 - 2
- CONSTANT_Float	0x 04 - 4
- CONSTANT_Long		0x 05 - 5
- CONSTANT_Double	0x 06 - 6
- CONSTANT_Class	0x 07 - 7
- CONSTANT_String	0x 08 - 8
- CONSTANT_Fieldref	0x 09 - 9
- CONSTANT_Methodref	0x 0a - 10
- CONSTANT_InterfaceMethodref	0x 0b - 11
- CONSTANT_NameAndType	0x 0c - 12

## content_in_constant_pool
- 字面量
- 类与接口的全限定名
- 字段的名称和描述符
- 方法的名称和描述符

## attribute_name
  name			user			desc
- Code			method_info		方法的字节码和其他数据
- ConstantValue		field_info		final 变量的值
- Deprecated		field_info, method_info	字段或方法过时
- Exceptions		method_info		方法可能抛出的异常
- InnerClass		ClassFile		内部，外部类的列表
- LineNumberTable	Code_attr		方法的行号与字节码的映射
- LocalVariableTable	Code_attr		方法的局部变量的描述
- SourceFile		ClassFile		源文件名
- Synthetic		field_info, method_info	编译器自行添加的字段或方法
===

# info_table

## cp_info

### CONSTANT_Utf8
- tag[u1]: 		constant_tag
- length[u2]
- bytes[u1] * length: 	chars
- 空字符用两个字节表示: 0x00 00

### CONSTANT_Integer
- tag[u1]: 		constant_tag
- bytes[u4]:		int_value

### CONSTANT_Float
- tag[u1]:		constant_tag
- bytes[u4]

### CONSTANT_Long
- tag[u1]:		constant_tag
- bytes[u8]

### CONSTANT_Double
- tag[u1]:		constant_tag
- bytes[u8]

### CONSTANT_Class
- tag[u1]:		constant_tag
- name_index[u2]:	CONSTANT_Utf8	// java/lang/Object
					// [Ljava/lang/Object;

### CONSTANT_String
- tag[u1]:		constant_tag
- string_index[u2]:	CONSTANT_Utf8	// Hello World

### CONSTANT_Fieldref
- tag[u1]: 		constant_tag
- class_index[u2]: 	CONSTANT_Class
- name_and_type_index[u2]: CONSTANT_NameAndType

- 对于(别的或自己的)类的字段的引用
- eg. 若代码中有 System.out 则该 CONSTANT_Fieldref 的
 Class 为 java/lang/System, name 为 out, type 为 Ljava/io/PrintStream;
- eg. 若代码中有对自身属性 int intField 的引用，则该 CONSTANT_Fieldref 的 
Class 与 this_class 指向相同, name 为 intField, type 为 I 
- memo中有关于静态最终变量的笔记

### CONSTANT_Methodref
- tag[u1]: 		constant_tag
- class_index[u2]: 	CONSTANT_Class
- name_and_type_index[u2]: CONSTANT_NameAndType

- 对于(别的或自己的)类的方法的引用
- eg. 若代码中有 System.out.println(int i) 方法则该 CONSTANT_Methodref 的
 Class 为 java/io/PrintStream, 切记是该方法的 class, name 为 println, type 为 (I)V
- eg. 若代码中有对自身方法 int addInt(int i) 的引用，则该 CONSTANT_Methodref 的 
Class 与 this_class 指向相同, name 为 addInt, type 为 (I)I 
- 默认初始化方法名为`<init>`


### CONSTANT_InterfaceMethodref
- tag[u1]: 		constant_tag
- class_index[u2]: 	CONSTANT_Class
- name_and_type_index[u2]: CONSTANT_NameAndType

- 大致和 CONSTANT_Methodref 类似

### CONSTANT_NameAndType
- tag[u1]: 		constant_tag
- name_index[u2]: 	CONSTANT_Utf8
- descriptor_index[u2]: CONSTANT_Utf8

## field_info
- access_flag[u2]:	access_flags (for field_info)
- name_index[u2]:	CONSTANT_Utf8
- descriptor_index[u2]:	CONSTANT_Utf8
- attributes_count[u2]
- attributes[attribute_info] * attributes_count

- 同一个类中不能存在相同名称及描述符的字段
- 可能有的 attribute: 
1. ConstantValue
2. Deprecated
3. Synthetic

## method_info
- access_flag[u2]:	access_flags (for method_info)
- name_index[u2]:	CONSTANT_Utf8
- descriptor_index[u2]:	CONSTANT_Utf8
- attributes_count[u2]
- attributes[attribute_info] * attributes_count

- 实例初始化方法: <init>
- 类与接口初始化方法: <clinit>
- 同一个类中不能存在相同名称及描述符的方法
- 可能有的 attribute: 
1. Code
2. Deprecated
3. Exceptions
4. Syhthetic

## attribute_info

- 可能出现的位置
1. ClassFile
2. field_info
3. method_info
4. Code_attribute

- 所有jvm必须识别的属性
1. Code
2. ConstantValue
3. Exception
4. InnerClass
5. Synthetic

### Code (Code_attribute)
- attribute_name_index[u2]:	CONSTANT_Utf8	// Code
- attribute_length[u4]				// 本项之后该 attribute 的字节数
- max_stack[u2]					// 操作数栈字数(不是字节数)
- max_locals[u2]				// 局部变量字数(不是字节数)
- code_length[u4]				// 字节码长度
- code[u1] * code_length			// 字节码
- exception_table_length[u2]			// exception_info 数量
- exception_table[exception_info] * exception_table_length 

- 常见于 method_info

#### exception_info
- start_pc[u2]:			// 从代码数组起始处到异常处理器起始处的offset
- end_pc[u2]:			// 从代码数组起始处到异常处理器结束后一个字节的offset
- handler_pc[u2]:		// 从代码数组起始处到异常处理器第一条指令的offset
- catch_type[u2]:	CONSTANT_Class	// java.lang.Throwable 及其子类

### ConstantValue
- attribute_name_index[u2]:	CONSTANT_Utf8	// ConstantValue
- attribute_length[u4]				// 本项之后该 attribute 的字节数 (该属性固定为 2 )
- constantvalue_index[u2]	CONSTANT_X	// X: Integer, Long, Float, Double, String

- 常见于 field_info, 每个 field_info 仅有一个 ConstantValue 属性
- 该 field 必须有 ACC_STATIC, 并不必须 ACC_FINAL

### Deprecated
- attribute_name_index[u2]:	CONSTANT_Utf8	// Deprecated
- attribute_length[u4]				// 本项之后该 attribute 的字节数 (该属性固定为 0 )

### Exceptions
- attribute_name_index[u2]:	CONSTANT_Utf8	// Exceptions
- attribute_length[u4]:				// 本项之后该 attribute 的字节数
- number_of_exceptions[u2]:			// 该方法 throws 子句中的异常数
- exception_index_table[u2]:	CONSTANT_Class	// 该方法 throws 子句中的异常类的列表

- 常见于 method_info

### InnerClass
- attribute_name_index[u2]:	CONSTANT_Utf8	// InnerClass
- attribute_length[u4]:				// 本项之后该 attribute 的字节数
- number_of_classes[u2]:			// 内部类的数量
- classes[inner_classes_info] * number_of_classes	// 内部类

- 内部类会被单独编译成 class 文件，该类的外部类内有 InnerClass 属性，指向该内部类 // 待验证

#### inner_classes_info	// 待验证
- inner_class_info_index[u2]:	CONSTANT_Class
- outer_class_info_index[u2]:	CONSTANT_Class
- inner_name_index[u2]:		CONSTANT_Utf8
- inner_class_access_flags[u2]:	access_flags (for inner_classes_info)

### LineNumberTable
- attribute_name_index[u2]:	CONSTANT_Utf8	// LineNumberTable
- attribute_length[u4]:				// 本项之后该 attribute 的字节数
- line_number_table_length[u2]
- line_number_table[line_number_info] * line_number_table_length

- 字节码与源代码行号的映射
- 常见于 Code_attribute

#### line_number_info // 待验证
- start_pc[u2]:		// 代码数组的offset
- line_number[u2]:	// 对应的源码行号

### LocalVariableTable
- attribute_name_index[u2]:	CONSTANT_Utf8	// LocalVariableTable
- attribute_length[u4]:				// 本项之后该 attribute 的字节数
- local_variable_table_length[u2]
- local_variable_table[local_variable_info] * local_variable_table_length

- 方法的栈帧中局部变量和源代码中局部变量的关系
- 常见于 Code_attribute

#### local_variable_info //  待验证
- start_pc[u2]
- length[u2]
- name_index[u2]
- descriptor_index[u2]
- index[u2]

### SourceFile
- attribute_name_index[u2]:	CONSTANT_Utf8	// SourceFile
- attribute_length[u4]				// 本项之后该 attribute 的字节数 (该属性固定为 2 )
- sourcefile_index[u2]	CONSTANT_Utf8

### Syhthetic
- attribute_name_index[u2]:	CONSTANT_Utf8	// Syhthetic
- attribute_length[u4]				// 本项之后该 attribute 的字节数 (该属性固定为 0 )


# memo
## 关于 static final 变量
1. 变量值已知
- 编译时所有指向该变量的引用都会被替换成该已知值，和该变量的拥有者类几乎没有关系。
- 引用该变量不会导致拥有者类被装载？初始化？

2. 变量值在运行时方能确定
- 编译时所有指向该变量的引用都是一个 CONSTANT_Fieldref

## 尚未验证内部类
## 尚未验证局部变量























