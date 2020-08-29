Lambda：
   
   Lambda 表达式中的基础语法 java8中引入了新的操作符 —> 该操作符称作箭头操作符 或者lambda表达式操作符
   左侧：lambda 表达式的参数列表
   右侧 lambda 表达式中所需要执行的功能 即lambda体
 
   语法格式一： 无参 无返回值
             （） -> System.out.println("hello lambda")
             
   语法格式二： 有一个参数 并且无返回值
             (str) -> System.out.println(str);
             
   语法格式三： 如果lambda中只有一个参数 小括号可以不写
             str -> System.out.println(str)
             
   语法格式四： 两个以上的参数 并且lambda体中有多条语句 有返回值
             Comparator<Integer> comparator  = (x,y) -> {
              System.out.println("函数式接口");
              return  Integer.compare(x,y);
          };
          
   语法格式五： 如果有两个参数 lambda体中只有一个条语句 return和大阔可以省略不写
                    Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 
   语法格式六： lambda的参数列表的数据类型可以省略不写 因为JVM的编译器可以通过上下文推断出数据类型 这个过程我们叫做 “类型推断”
                 (Integer x,Integer y) -> Integer.compare(x,y)
 
  左右遇一括号省
  左侧推断类型省
 
  Lambda 需要函数式接口的支持
  什么是函数式接口： 接口中只有一个抽象方法的接口 称之为函数式接口  可以使用注解@FunctionalInterface
                     可以检查是否是函数式接口
 
------------------------------------------------------------------

 内置函数:
 
 java8 内置四大核心函数式接口
 Consumer<T> : 消费型接口
     void accept(T t);
  
 Supplier<T> : 供给型接口
     T get();
  
 Function<T,R> : 函数型接口
      R apply(T);
  
 Predicate<T>: 断言型接口
      boolean test(T t);
 
  方法引用：

    若Lambda中的内容有方法已经实现了 我们可以使用方法引用
    （可以理解为方法引入是Lambda表达式的另一种表现形式）
 
   主要有三种语法格式
   
    对象::实例方法名
    类::静态方法名
    类::实例方法
 
   注意：
   
    ①Lambda 体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
    ②若Lambda 参数列表中的第一参数是实例方法的调用者 而第二个参数是实例方法的参数时，可以使用ClassName::Method
     
   构造器引用：
   
      ClassName::new
      注意：需要调用的构造器参数列表要与函数式接口中抽象方法的参数列表保持一致
   数组引用:
   
      Type::new
  Stream的三个操作步骤
  
    1.创建Stream
       1.可以通过Collection 系列集合提供的stream() 或者parallelStream()
       2.通过Arrays中的静态方法stream()获取数组流
       3.通过Stream类中的静态方法 of()
       4.创建无限流 迭代
          Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
                  stream4.limit(10)
                          .forEach(System.out::println);
       5.生成
               Stream<Double> doubleStream = Stream.generate(() -> Math.random() * 10);
               doubleStream.limit(10).forEach(System.out::println); 
    2.中间操作  筛选与切片     
              filter 接受lambda,从流中排除某些元素
              limit 截断流 使其元素不超过给定数量
              skip(N) 跳过元素，返回一个扔掉了前N个元素的流 若流中的元素不足N个 则返回一个空流 与limit(N)互补
              distinct 筛选 通过流生成元素的HashCode() 和equals() 去除重复元素
              map--- 接受lambda 将元素抓换成其他形式或提取信息，接受一个函数作为参数，该函数会应用到每个元素上，并将其映射成为一个新的元素
              flatMap --- 接受一个函数作为参数，将流中的每一个值都换成另外一个流，冉后把所有的流连城一个流
              sorted() -- 自然排序----(comparable)
              sorted(Comparator com) 定制排序---(Comparator)    
    3.终止操作（终端操作）   
                 查找与匹配
                    allMath  检查是否匹配所有元素
                    anyMath  检查是否至少匹配一个元素
                    noneMath 检查是否没有匹配所有元素
                    findFirst 返回第一个元素
                    findAny  返回当前流中的任意元素
                    count 返回流中元素最大的总个数
                    max 返回流中最大值
                    min 返回流中最小值  
                    归约 reduce(T identity BinaryOperator) / reduce(BinaryOperator) 可以将流中元素反复结合起来，得到一个值
                    收集 collect 将流转换为其他形式，接受一个collector接口的实现，用于给Stream中元素做汇总的方法
                      ollect(Collectors.xxx()) 核心工具类 需要研究                        