# Cron Expression Parser

Command line application which parses a cron string and expands each field
to show the times at which it will run. 

App follows the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command. The input should be on a single line.

The cron string will be passed to the application as a single argument.

### Build
```
./gradelw build
```

### Usage

```
~$ java -jar cron-parser-0.0.1-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```
The output is formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```
Should yield the following output:
```
expression    */15 0 1,15 * 1-5 /usr/bin/find
minute        0 15 30 45
hour          0
day of month  1 15
month         1 2 3 4 5 6 7 8 9 10 11 12
day of week   1 2 3 4 5
command       /usr/bin/find
```