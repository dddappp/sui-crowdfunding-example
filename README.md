# A Sui Crowdfunding Example

```shell
sui client publish --gas-budget 1000000000 --skip-dependency-verification
```

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

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function create \
--type-args '0x2::sui::SUI' \
--args \"0xa38aa80e7b09ca8614ef402520deca48c4821d193ae507c97418898d7d49c05a\" '"Mango Orchard Crowdfunding"' '"This is a test!"' \"1000000000\" '""' \
--gas-budget 1000000000
```

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

```shell
sui client object 0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50
```

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_aggregate --function start \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x6' \
--gas-budget 1000000000
```

```shell
sui client gas
```

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 6849858536 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

```shell
sui client active-address
```

```text
0x7ee2bbe3b6519d920720a97099373d4bd76a3fcda4e146f60052dd3b9518895e
```

```shell
sui client pay-sui --amounts 1100000000 \
--recipients 0x7ee2bbe3b6519d920720a97099373d4bd76a3fcda4e146f60052dd3b9518895e \
--input-coins 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a \
--gas-budget 1000000000
```


```shell
sui client gas
```

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 5595504036 │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1100000000 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function donate \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a' '0x6' '1000000000' \
--gas-budget 1000000000
```


```shell
sui client call --package 0xf033ec079b048af82289a10c5e54caa177f1fda8804504014999f548f512caa3 --module project_service --function withdraw \
--type-args '0x2::sui::SUI' \
--args \"0x130d617826ebcc38d7f72e1eee7de72bc7053ddffbba45c0d43ba864031a0f50\" '0x6' \
--gas-budget 1000000000
```

```shell
sui client gas
```

```text
╭────────────────────────────────────────────────────────────────────┬────────────╮
│ gasCoinId                                                          │ gasBalance │
├────────────────────────────────────────────────────────────────────┼────────────┤
│ 0x1d1065a86b85900f5918accb97567bcf57fd02b9b94222793474826b26b890a4 │ 1000000000 │
│ 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a │ 4443196732 │
│ 0xf05fef49807794c12253978c49074b13e2f151dc31775f1cf2374a4f7e3e000b │ 1094578412 │
╰────────────────────────────────────────────────────────────────────┴────────────╯
```

```shell
sui client merge-coin \
--coin-to-merge 0x1d1065a86b85900f5918accb97567bcf57fd02b9b94222793474826b26b890a4 \
--primary-coin 0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a \
--gas-budget 1000000000
```

