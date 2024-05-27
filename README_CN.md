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

#### 创建项目

#### 启动项目

项目的截止时间，是从启动的时间开始计算的。

#### 捐助项目

#### 提款

作为项目发起人，可以在项目募集时间结束后，并且项目募集金额达到了目标后，提取资金。

### 清理工作：合并 Coin 对象

To facilitate the next test, we can Merge the excess coin objects in the wallet.

See what coin objects are currently in your Sui CLI wallet:

```shell
sui client gas
```

The output is similar to the following:

```text
╭────────────────────────────────────────────────────────────────────┬────────────────────┬──────────────────╮
│ gasCoinId                                                          │ mistBalance (MIST) │ suiBalance (SUI) │
├────────────────────────────────────────────────────────────────────┼────────────────────┼──────────────────┤
│ {COIN_OBJECT_1}                                                    │ xxxxxxxxxx         │ x.xx             │
│ {COIN_OBJECT_2}                                                    │ yyyyyyyyyy         │ y.yy             │
│ {COIN_OBJECT_3}                                                    │ zzzzzzzzzz         │ z.zz             │
╰────────────────────────────────────────────────────────────────────┴────────────────────┴──────────────────╯
```

You can see that there is an additional coin object in the wallet and the balance is the amount raised by the project.

You can merge two of the coins like below, too many coins may not be convenient for more testing:

```shell
sui client merge-coin \
--coin-to-merge {COIN_OBJECT_2} \
--primary-coin {COIN_OBJECT_1} \
--gas-budget 20000000
```


### 创建项目、启动项目、捐助项目、退款

#### 创建另一个项目

#### 启动项目

#### 捐助项目

#### 退款



### 测试链下服务（indexer）

#### 配置链下服务

Open the `application-test.yml` file located in the directory
`sui-java-service/suicrowdfundingexample-service-rest/src/main/resources` and set the publishing transaction digest.

After setting, it should look like this:

```yaml
# ...
server:
  port: 8024

sui:
  contract:
    jsonrpc:
      url: "https://sui.devnet.m2.movementlabs.xyz/"
    package-publish-transaction: "HvTguer3s3ha1vYEqZAa5azpRWeCcadDuBptW4KiRtP1"
```

This is the only place where off-chain service need to be configured, and it's that simple.


#### 创建及初始化链下服务数据库

Use a MySQL client to connect to the local MySQL server and execute the following script to create an empty database (assuming the name is `suicrowdfundingexample_m2`):

```sql
CREATE SCHEMA `suicrowdfundingexample_m2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
```

Go to the `sui-java-service` directory and package the Java project:

```shell
mvn package
```

Then, run a command-line tool to initialize the database:

```shell
java -jar ./suicrowdfundingexample-service-cli/target/suicrowdfundingexample-service-cli-0.0.1-SNAPSHOT.jar ddl -d "./scripts" -c "jdbc:mysql://127.0.0.1:3306/suicrowdfundingexample_m2?enabledTLSProtocols=TLSv1.2&characterEncoding=utf8&serverTimezone=GMT%2b0&useLegacyDatetimeCode=false" -u root -p 123456
```

#### 启动链下服务

In the `sui-java-service` directory, run the following command to start the off-chain service:

```shell
mvn -pl suicrowdfundingexample-service-rest -am spring-boot:run
```

现在，你可以使用浏览器打开链下服务的 Swagger (Open API) 文档，看看都有哪些接口已经可以开箱即用：
http://localhost:8024/api/swagger-ui/index.html



##  延伸阅读

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

