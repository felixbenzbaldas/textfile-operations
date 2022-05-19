# textfile operations

Demo code for textfile operations with Java 8+.

Including:
* java.util.regex package (Matcher, Pattern, named group)
* read and write to file
* NumberFormat
* File operations (rename)

This demo program does the following:
* get file path from command line argument (args[0])
* rename the old file
* create new file and do some textoperation:
    * replace [numberOne/numberTwo] by [numberOne + numberTwo/numberTwo]

Example:

the following text

```
Test
testABC [4,1/2,5] sdfdsf
[62,4/2,4]---[45/2] sf
     [asf/sdf]
```

will be replaced by

```
Test
testABC [6,6/2,5] sdfdsf
[64,8/2,4]---[47/2] sf
     [asf/sdf]
```

Summarized: This programs adds the second number to the first number in square brackets.