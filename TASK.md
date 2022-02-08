# Cron Expression Parser

Command line application which parses a cron string and expands each field
to show the times at which it will run. 

App follows the standard cron format with five time fields (minute, hour, day of
month, month, and day of week) plus a command. The input should be on a single line.

The cron string will be passed to the application as a single argument.

Usage
```
~$ cron-parser "*/15 0 1,15 * 1-5 /usr/bin/find"
```
The output is formatted as a table with the field name taking the first 14 columns and
the times as a space-separated list following it.

For example, the following input argument:
```
*/15 0 1,15 * 1-5 /usr/bin/find
```
Should yield the following output:
```
minute 0 15 30 45
hour 0
day of month 1 15
month 1 2 3 4 5 6 7 8 9 10 11 12
day of week 1 2 3 4 5
command /usr/bin/find
```
You should spend no more than three hours on this exercise. If you do not have time to
handle all possible cron strings then an app which handles a subset of them correctly is
better than one which does not run or produces incorrect results. You will be asked to extend
the solution with additional features while onsite.

You should see your project reviewer as a new team member you are handing the project
over to. Provide everything you feel would be relevant for them to ramp up quickly, such as
tests, a README and instructions for how to run your project in a clean OS X/Linux environment.