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
        "packageId": String("0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4"),
        "version": String("1"),
        "digest": String("5qhhGyb9GgWvdaJHJ42KsSzxzfMCizYVBMWX9nXE5XEA"),
        "modules": Array [
            String("donation"),
            //...
        ],
    },
    Object {
        //...
        "objectType": String("0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4::platform::Platform"),
        "objectId": String("0xabfeabd218a3862e2ea5c1b20428f57f788aaba4ad0932a7d6efeefac5e3acb7"),
    },
```

```shell
sui client call --package 0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4 --module project_aggregate --function create \
--type-args '0x2::sui::SUI' \
--args \"0xabfeabd218a3862e2ea5c1b20428f57f788aaba4ad0932a7d6efeefac5e3acb7\" '"Mango Orchard Crowdfunding"' '"This is a test!"' \"1000000000\" '""' \
--gas-budget 1000000000
```

```text
----- Object changes ----
Array [
    //...
    Object {
        "type": String("created"),
        //...
        "objectType": String("0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4::project::Project<0x2::sui::SUI>"),
        "objectId": String("0xd6f05460d0e7929f8a90976581fd4ce7ca1a264588090567bc0b3dea806d0d6b"),
        //...
    },
]
```

```text
https://suiexplorer.com/object/0xd6f05460d0e7929f8a90976581fd4ce7ca1a264588090567bc0b3dea806d0d6b?network=devnet
```

```shell
sui client call --package 0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4 --module project_aggregate --function start \
--type-args '0x2::sui::SUI' \
--args \"0xd6f05460d0e7929f8a90976581fd4ce7ca1a264588090567bc0b3dea806d0d6b\" '0x6' \
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
sui client call --package 0x6d9f0a6644c2c03ccf872bc75f7f7bf7cc745ec055c05d302a82987bf2ff31c4 --module project_service --function donate \
--type-args '0x2::sui::SUI' \
--args \"0xd6f05460d0e7929f8a90976581fd4ce7ca1a264588090567bc0b3dea806d0d6b\" '0x7b983b339f612c9ebb47727da6e1351725b90e73521c4da826226ab7b453af5a' '0x6' '1000000000' \
--gas-budget 1000000000
```

