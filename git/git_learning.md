## git部分使用命令总览
https://git-scm.com/book/zh/v2

#### 获取帮助
git help

#### 获取git仓库
通常有两种方式获取git项目的仓库方式
1. 尚未进行版本控制的转化为git仓库
git init

2. 比如从其他服务器克隆一个
git clone https://github.com/mikeyou/day_day_study.git

#### 检查当前文件状态
git status 
输出简略信息
git status -s

#### 跟踪文件
git add 【文件信息】

#### 忽略文件
创建.gitignore文件
touch / vi  .gitignore
部分规则如下:
星号(*)        匹配零个或多个任意字符
方括号[abc]    要么匹配一个a 或b 或c, 如果方括号里面有短横线，则匹配范围内的
问号(?)        只匹配一个任意字符
两个星号(**)   匹配任意中间目录

##### 例子
忽略所有的.a的文件   
*.a
在忽略.a的所有文件情况下，但跟踪lib.a     
!lib.a
只忽略当前目录下的TODO文件 而不忽略其他文件夹下的TODO
/TODO
忽略任何目录名下为build的文件夹
build/
忽略doc目录下所有的.txt 但不忽略doc/server/arch.txt
doc/*.txt
忽略doc下目录及其子目录的所有.pdf文件
doc/**/.pdf

#### 查看已暂存和未暂存的修改
git diff 可以查看具体那些做了更改或者需要去暂存的

git diff --staged 用于查看已暂存的将要添加到下次提交的里的内容

git diff --cached 用于查看已经暂存起来的变化

#### 提交
git commit

跳过使用暂存区域
git commit -a 
添加注释
git commit -m '注释'


#### 移除文件
