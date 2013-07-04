databases_jdbc
==============

This commit includes two files:
- DataBaseCalls.java
- DataBaseConnectionSingleton.java

DataBaseCalls creates a new connection singleton and allows us to run a SQL query.

DataBaseConnectionSingleton is a pattern used to make sure we only have one of the connection
open at one time.
