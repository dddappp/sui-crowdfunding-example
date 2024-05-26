# Sui Client CLI Cheatsheet

[ToC]

## Project aggregate

### Create method

```shell
sui client call --package _PACKAGE_ID_ --module project_aggregate --function create \
--type-args 'T' \
--args \"_PLATFORM_OBJECT_ID_\" '"string_title"' '"string_description"' \"u64_target\" '"string_image"' \
--gas-budget 100000
```

### Update method

```shell
sui client call --package _PACKAGE_ID_ --module project_aggregate --function update \
--type-args 'T' \
--args project_Object_ID '"string_title"' '"string_description"' \"u64_target\" '"string_image"' \
--gas-budget 100000
```

### Start method

```shell
sui client call --package _PACKAGE_ID_ --module project_aggregate --function start \
--type-args 'T' \
--args project_Object_ID \"_CLOCK_OBJECT_ID_\" \
--gas-budget 100000
```

## Platform singleton object


---

## Tips

You can escape single quotes in string arguments by using the following method when enclosing them within single quotes in a shell:

1. Close the current single quote.
2. Use a backslash `\` to escape the single quote.
3. Open a new set of single quotes to continue the string.

Here is an example of how to escape a single quote within a string enclosed by single quotes in a shell:

```shell
echo 'It'\''s a beautiful day'
```

