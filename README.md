# Sui Crowdfunding Example

This is a Sui Move sample project for teaching purposes. The client is a Web3 developer education institution.

## Prerequisites

Currently, the dddappp low-code tool is published as a Docker image for developers to experience.

So before getting started, you need to:

* Install [Sui](https://docs.sui.io/build/install).

* Install [Docker](https://docs.docker.com/engine/install/).


## Programming

### Write DDDML Model File

In the `dddml` directory in the root of the repository, create a DDDML file like [this](./dddml/crowdfunding.yaml).


> **Tip**
>
> About DDDML, here is an introductory article: ["Introducing DDDML: The Key to Low-Code Development for Decentralized Applications"](https://github.com/wubuku/Dapp-LCDP-Demo/blob/main/IntroducingDDDML.md).


### Run dddappp Project Creation Tool

#### Update dddappp Docker Image

Since the dddappp v0.0.1 image is updated frequently, you may be required to manually delete the image and pull it again before `docker run`.

```shell
# If you have already run it, you may need to Clean Up Exited Docker Containers first
docker rm $(docker ps -aq --filter "ancestor=wubuku/dddappp:0.0.1")
# remove the image
docker image rm wubuku/dddappp:0.0.1
# pull the image
git pull wubuku/dddappp:0.0.1
```

---

In repository root directory, run:

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

The command parameters above are straightforward:

* This line `-v .:/myapp \` indicates mounting the local current directory into the `/myapp` directory inside the container.
* `dddmlDirectoryPath` is the directory where the DDDML model files are located. It should be a directory path that can be read in the container.
* Understand the value of the `boundedContextName` parameter as the name of the application you want to develop. When the name has multiple parts, separate them with dots and use the PascalCase naming convention for each part. Bounded-context is a term in Domain-driven design (DDD) that refers to a specific problem domain scope that contains specific business boundaries, constraints, and language. If you cannot understand this concept for the time being, it is not a big deal.
* `boundedContextJavaPackageName` is the Java package name of the off-chain service. According to Java naming conventions, it should be all lowercase and the parts should be separated by dots.
* `boundedContextSuiPackageName` is the package name of the on-chain Sui contracts. According to the Sui development convention, it should be named in snake_case style with all lowercase letters.
* `javaProjectsDirectoryPath` is the directory path where the off-chain service code is placed. The off-chain service consists of multiple modules (projects). It should be a readable and writable directory path in the container.
* `javaProjectNamePrefix` is the name prefix of each module of the off-chain service. It is recommended to use an all-lowercase name.
* `pomGroupId` is the GroupId of the off-chain service. We use Maven as the project management tool for off-chain service. It should be all lowercase and the parts should be separated by dots.
* `suiMoveProjectDirectoryPath` is the directory path where the on-chain Sui contract code is placed. It should be a readable and writable directory path in the container.

After the above command is successfully executed, two directories `sui-java-service` and `sui-contracts` should be added to the local current directory.


### Implementing Business Logic

The tool has generated some files with the suffix `_logic.move` in the directory `sui-contracts/sources`.

Generally, these files contain the scaffolding code of functions that implement business logic,
namely the signature part of the functions.
You just need to fill in the implementation part of the functions.

You need to fill in the business logic in the following files:

* [platform_add_project_logic](./sui-contracts/sources/platform_add_project_logic.move). This file implements the business logic for "Adding a Project Id to the Platform".
* [project_create_logic.move](./sui-contracts/sources/project_create_logic.move). Implement the business logic for "Create Project" in this file.
* [project_donate_logic.move](./sui-contracts/sources/project_donate_logic.move). Implement the "Donate to Project" business logic in this file.
* [project_refund_logic.move](./sui-contracts/sources/project_refund_logic.move). Implement the "Refund from Project" business logic in this file.
* [project_start_logic.move](./sui-contracts/sources/project_start_logic.move). Implement the "Start Project" business logic in this file.
* [project_update_logic.move](./sui-contracts/sources/project_update_logic.move). Implement the "Update Project" business logic in this file.
* [project_withdraw_logic.move](./sui-contracts/sources/project_withdraw_logic.move). Implement the "Withdraw Project Funds" business logic in this file.

Don't worry, as you can see, the function body part of these files that you need to fill in is very short.

You need to check the client's input in the `verify` function, capture the information from input and context to generate an "event object".

Then, in the `mutate` function, you need to modify the state of the objects, primarily by applying the information from the event object.

---

In [the model file](./dddml/crowdfunding.yaml), we define three methods, `Donate`, `Withdraw`, and `Refund`,
which use `Balance`, a resource type, as type of parameters or return values.
This makes these methods very combinable - as a developer of Move, a "resource-oriented programming" language,
you will already know this.

However, it's not easy to call them directly from clients.
So, we added this file [project_service.move](./sui-contracts/sources/project_service.move).
In this file, three entry functions are provided to facilitate clients to use the corresponding features directly.

That's the whole programming routine, isn't it simple?

## Test Application

Below we will publish and test the application in test environment using the sui CLI client.

View the currently active environment of the sui CLI client:

```shell
sui client envs
```

The output is similar to the following:

```text
╭──────────┬─────────────────────────────────────┬────────╮
│ alias    │ url                                 │ active │
├──────────┼─────────────────────────────────────┼────────┤
│ devnet   │ https://fullnode.devnet.sui.io:443  │ *      │
│ testnet  │ https://fullnode.testnet.sui.io:443 │        │
╰──────────┴─────────────────────────────────────┴────────╯
```

See what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

Make sure you have at least 1 SUI Coin with a minimum balance of 2000000000 smallest units in the current environment.


### Publish

In the test environment, before publishing the contract, you can change the crowdfunding time limit to 1 minute.

You can change the value of the `FIFTEEN_DAYS_IN_MS` const as prompted by the comments in the file `sui-contracts/sources/project_start_logic.move`.

Execute the following command in the directory `sui-contracts` to publish the contract on the chain:

```shell
sui client publish --gas-budget 1000000000 --skip-dependency-verification
```

The output is similar to the following:

```text
----- Object changes ----
Array [
    //...
    Object {
        "type": String("published"),
        "packageId": String("0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3"),
        //...
        "modules": Array [
            String("donation"),
            //...
        ],
    },
    Object {
        //...
        "objectType": String("0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3::platform::Platform"),
        "objectId": String("0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a"),
        //...
    },
    //...
```

Record the package Id of your published contract, as in the example above,
the `packageId` is `0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3`;
as well as the Id of the object that represents the crowdfunding platform, as in the example above,
the `objectId` is `0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a`.
In the following example commands we will use these IDs directly,
in your test environment you will need to replace them with your actual values.


### Create & Start & Donate & Withdraw

#### Create

Create a crowdfunding project:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function create \
--type-args '0x2::sui::SUI' \
--args \"0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a\" '"Mango Orchard Crowdfunding"' '"This is a test!"' \"1000000000\" '""' \
--gas-budget 1000000000
```

The output is similar to the following:

```text
----- Object changes ----
Array [
    //...
    Object {
        "type": String("created"),
        //...
        "objectType": String("0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3::project::Project<0x2::sui::SUI>"),
        "objectId": String("0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50"),
        //...
    },
]
```

Make a note of the object Id of the project.
In the above example, the object Id of the created project is `0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50`.
In the following example commands we will use this Id directly,
in your test environment you will need to replace it with your actual value.

You can view the state of an object like this:

```shell
sui client object 0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50
```

#### Start

Start the project:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function start \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x6' \
--gas-budget 1000000000
```

Next, let's prepare to donate to this project.

#### Donate

First, see what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

If you only have a Sui coin, then the output is similar to the following:

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 6849858536 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

You need to split this coin into two.
This is because you need to use one coin to donate to the project,
and the other coin to pay for the gas.

View currently active sui CLI wallet address:

```shell
sui client active-address
```

The output is similar to the following:

```text
0x7ee2bbe3b6519d920720a97099373d4bd76a3fcda4e146f60052dd3b9518895e
```

Use the following command to transfer 1.1 SUI to this address:

```shell
sui client pay-sui --amounts 1100000000 \
--recipients 0x7ee2bbe3b6519d920720a97099373d4bd76a3fcda4e146f60052dd3b9518895e \
--input-coins 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a \
--gas-budget 1000000000
```

Which will split the original one Coin into two.

Then see what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

The output is similar to the following:

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 5595504036 │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1100000000 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

In less than 1 minute after having started the project, you can donate it:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function donate \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a' '0x6' '1000000000' \
--gas-budget 1000000000
```

You donated 1 SUI to the project using the above command. This makes the project's funding goal already reached.

#### Withdraw

Waiting for one minute, as the owner of the project, you can take out the funds.

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function withdraw \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x6' \
--gas-budget 1000000000
```

#### Merge coins

See what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

The output is similar to the following:


```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x1d1065a86b85900f5918accb97567bcf57fd02b9b94222793474826b26b890a4 │ 1000000000 │
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 4443196732 │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1094578412 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

You can see that there is an additional Sui coin in the wallet and the balance is the amount raised by the project.

You can merge two of the coins like below, too many coins may not be convenient for more testing:

```shell
sui client merge-coin \
--coin-to-merge 0x1d1065a86b85900f5918accb97567bcf57fd02b9b94222793474826b26b890a4 \
--primary-coin 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a \
--gas-budget 1000000000
```


### Create & Start & Donate & Refund

#### Create

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function create \
--type-args '0x2::sui::SUI' \
--args \"0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a\" '"Mango Orchard Crowdfunding2"' '"This is a test2"' \"1000000000\" '""' \
--gas-budget 1000000000
```

The output is similar to the following:

```text
----- Object changes ----
Array [
    //...
    Object {
        "type": String("created"),
        //...
        "objectType": String("0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3::project::Project<0x2::sui::SUI>"),
        "objectId": String("0x74569d20afb16698a02deb46d196c628189d18666a1786bf3e85b4f41a111f91"),
        //...
    },
]
```

#### Start

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function start \
--type-args '0x2::sui::SUI' \
--args \"0x74569d20afb16698a02deb46d196c628189d18666a1786bf3e85b4f41a111f91\" '0x6' \
--gas-budget 1000000000
```

#### Donate

In less than 1 minute after having started the project, you can donate it:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function donate \
--type-args '0x2::sui::SUI' \
--args \"0x74569d20afb16698a02deb46d196c628189d18666a1786bf3e85b4f41a111f91\" '0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a' '0x6' '500000000' \
--gas-budget 1000000000
```

You donated 0.5 SUI to the project using the above command.

#### The withdrawals should fail

If within a minute after the project has started, the owner of the project wants to withdraw the funds:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function withdraw \
--type-args '0x2::sui::SUI' \
--args \"0x74569d20afb16698a02deb46d196c628189d18666a1786bf3e85b4f41a111f91\" '0x6' \
--gas-budget 1000000000
```

Well, it can't be successful. The output is similar to the following:

```text
Error executing transaction: Failure {
    error: "MoveAbort(MoveLocation { module: ModuleId { address: f033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3, name: Identifier(\"project_withdraw_logic\") }, function: 0, instruction: 26, function_name: Some(\"verify\") }, 184) in command 0",
}
```

It is assumed that no one else will donate to the project. One minute later, the project owner tries to withdraw the funds again. The output is similar to the following:

```text
Error executing transaction: Failure {
    error: "MoveAbort(MoveLocation { module: ModuleId { address: f033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3, name: Identifier(\"project_withdraw_logic\") }, function: 0, instruction: 40, function_name: Some(\"verify\") }, 185) in command 0",
}
```

You can see that the error code in the output has changed (`184` -> `185`). You can find what these codes represent in the source file `project_withdraw_logic.move`.

#### Refund

Because the deadline has been reached and the funding goal was not met, the donor can now refund:

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function refund \
--type-args '0x2::sui::SUI' \
--args \"0x74569d20afb16698a02deb46d196c628189d18666a1786bf3e85b4f41a111f91\" '0x6' \
--gas-budget 1000000000
```

#### View state of the platform

You can view the state of the crowdfunding `Platform` object like this:

```shell
sui client object 0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a
```

After the above test, you should see two project object IDs in the output message.

#### Merge coins

See what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

The output is similar to the following:

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 4936293540 │
│ 0x9179736ef65f706dbd34268265c16dbbd85f8d175b7f71550eb363bbea11e747 │ 500000000  │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1091209500 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

Merge two of the coins like below:

```shell
sui client merge-coin \
--coin-to-merge 0x9179736ef65f706dbd34268265c16dbbd85f8d175b7f71550eb363bbea11e747 \
--primary-coin 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a \
--gas-budget 1000000000
```

See what SUI coins are currently in your sui CLI wallet:

```shell
sui client gas
```

The output is similar to the following:

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 5436293540 │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1091167860 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

##  Further Reading

### Requirements

Below are the project requirements.

**Overview**: We want to create a dApp for crowdfunding projects. Users can list their projects with a specific funding target and deadline to achieve it.

For example, Dan wants to raise 5,000 Mango tokens for his Mango Orchard by November 10th. The project will be listed and other users can fund it by paying Mango tokens. If the target of 5,000 Mango tokens is not reached by November 10th, the funding amount will be returned to the supporters. If the target is met, the amount will be sent to Dan.

We only need the smart contract, without a frontend.

*  The smart contract should enable users to create project listings, receive funds from supporters, and distribute funds to the project creator if the funding goals are met.
*  Funds will be locked in the smart contract until a specified funding goal, set by the project creator, is reached. If not met, funds will be returned to the supporters.
*  Each project must have a 15-day time limit from the date it starts, which will be managed by the smart contract.
*  The smart contract code must include comments to explain how it works.
*  The dApp should have its own native token that users can use to fund a project.


### Sui Blog Example

Repository: https://github.com/dddappp/sui-blog-example

It only requires 30 or so lines of code (all of which is a description of the domain model) to be written by the developer, and then generates a blog example that emulates [RoR Getting Started](https://guides.rubyonrails.org/getting_started.html) in one click, without requiring the developer to write a single line of other code.


### A More Complex Sui Demo

If you are interested, you can find a more complex Sui Demo here: ["A Sui Demo"](https://github.com/dddappp/A-Sui-Demo).


### Rooch Blog Example

Here is a Rooch version like above Sui blog example: https://github.com/rooch-network/rooch/blob/main/examples/blog/README.md

