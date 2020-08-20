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
git rm 【文件】
移除文件换句话说就是从暂存区域移除该文件不再跟踪该文件

仍然保存在工作空间，只移除缓存区域的
git rm --cached 【文件】

#### 移动文件
git mv

#### 查看提交历史
git log
	-p 显示每次提交所引入的差异
	-num 显示最近提交的次数
	--stat 显示最近提交的简略统计信息
	--pretty 使用其他格式显示历史提交信息,可用选项[online,short,full,fuller,format]

#### 撤销操作
git commit --amend

#### 查看远程仓库
git remote
	-v 查看对应url

#### 添加远程仓库
git remote add
#### 从远程仓库中抓取与拉去
git fetch <remote>
这个命令值会抓取数据到你的本地，并不会比较合并
git pull通常会从最初克隆的服务器上抓取数据并自动尝试合并到当前的所在分支

#### 推送
git push origin master

#### 查看某个仓库更多信息
git remote show origin

#### 远程仓库的重命名与移除
git remote rename a b
git remote remove b

#### 打标签
 列出标签  git tag -l
 寻找特定标签 git tag -l "v.1.8.5*"        --即寻找1.8.5系列
 创建附注标签 git tag -a v1.4 -m 'my version 1.4'
 创建轻量级标签 git tag v1.4.1
 后期打标签（后面跟上提交的部分校验和） git tag -a v1.2 9fceb02
 删除标签 git tag -d <tagName>

#### 分支创建
 git branch <分支名>
 git checkout <分支名> 切换分支
 
 https://git-scm.com/book/zh/v2/Git-%E5%88%86%E6%94%AF-%E5%88%86%E6%94%AF%E7%9A%84%E6%96%B0%E5%BB%BA%E4%B8%8E%E5%90%88%E5%B9%B6

 git branch -d <分支名>
 git branch --no-merged 查看所有未合并的分支

