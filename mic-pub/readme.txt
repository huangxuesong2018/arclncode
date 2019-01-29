第二节课
  一，运行时生成汇编指令
    1. 解压压缩文件，讲解压的内容放到`JRE_HOME/bin/server`路径下

    2. 在运行main函数之前，
     加入虚拟机参数:
     VM options  -> -server -Xcomp -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -XX:CompileCommand=compileonly,*App.getInstance（替换成实际运行的代码）
     指向第一步的 $JRE_HOME 目录
     JRE ->   D:\Program Files\Java\jre1.8.0_181