# 一个众筹去中心应用的示例

在这个示例中，我们将使用 dddappp 低代码工具来开发一个众筹去中心应用，并将它发布到 Movement M2 devnet 网络上。

## 需求

见英文版 README.md

## 准备工作

* Install [Sui](https://docs.sui.io/build/install).
* Install [Docker](https://docs.docker.com/engine/install/).
* [配置你的 Sui CLI 工具](https://docs.movementlabs.xyz/developers/sui-developers/using-sui-cli)，
    这样，如果你是一个 Sui 开发者，你基本不需要改变你的工作流程，就可以将你的应用部署到 Movement 网络上。

### 确认你的 Sui CLI 客户端环境

查看你的 Sui CLI 钱包中当前有多少币：

```shell
sui client gas
```

如果你没有任何币，你可以从 Movement devnet 水龙头获取一些测试币：
https://faucet.movementlabs.xyz/?network=devnet


### 给自己准备两个 Coin 对象

参考英文版 README.md

## 编码工作

### 编写 DDDML 模型文件

在 `dddml` 目录中，创建一个 DDDML 文件，内容参考 [这个](./dddml/crowdfunding.yaml)。

### 运行 dddappp 项目创建工具

在代码库的根目录，执行：

```shell
docker run \
-v .:/myapp \
wubuku/dddappp:0.0.1 \
--dddmlDirectoryPath /myapp/dddml \
--boundedContextName Test.SuiCrowdfundingExample \
--suiMoveProjectDirectoryPath /myapp/sui-contracts \
--boundedContextSuiPackageName sui_crowdfunding_example \
--boundedContextJavaPackageName org.test.suicrowdfundingexample \
--javaProjectsDirectoryPath /myapp/sui-java-service \
--javaProjectNamePrefix suicrowdfundingexample \
--pomGroupId test.suicrowdfundingexample
```

在以上命令成功执行后，本地当前目录应该会新增两个目录 `sui-java-service` 和 `sui-contracts`。

### 实现模型“方法”的业务逻辑

参考英文版 README.md

## 测试应用

### 发布合约


### 创建项目、启动项目、捐助项目、提款


### 创建项目、启动项目、捐助项目、退款


### 测试链下服务（indexer）

你可以使用浏览器打开链下服务的 Swagger (Open API) 文档，看看都有哪些接口已经可以开箱即用：
http://localhost:8024/api/swagger-ui/index.html



##  进一步阅读

### Sui Blog Example

Repository: https://github.com/dddappp/sui-blog-example

It only requires 30 or so lines of code (all of which is a description of the domain model) 
to be written by the developer, 
and then generates a blog example that emulates [RoR Getting Started](https://guides.rubyonrails.org/getting_started.html) in one click, 
without requiring the developer to write a single line of other code.


### A More Complex Sui Demo

If you are interested, you can find a more complex Sui Demo here: ["A Sui Demo"](https://github.com/dddappp/A-Sui-Demo).


### Rooch Blog Example

Here is a Rooch version like above Sui blog example: https://github.com/rooch-network/rooch/blob/main/examples/blog/README.md

