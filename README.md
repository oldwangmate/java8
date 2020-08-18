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
 