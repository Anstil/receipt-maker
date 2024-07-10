# Receipt maker

This is a console application that implements
functionality for generating a receipt in a store.

## How to Build

To build the packages, follow these steps:

```shell
# Open a terminal (Command Prompt or PowerShell for Windows, Terminal for macOS or Linux)

# Ensure Git is installed
# Visit https://git-scm.com to download and install console Git if not already installed

# Clone the repository
git clone

# Check the installed version of JDK
java -version
# Visit the official Oracle website to install or update it if necessary

#Compile project files
javac .\src\main\java\ru\clevertec\check\*.java

#After compile you can run program by using this command
java -cp src ./src/main/java/ru/clevertec/check/CheckRunner.java id-quantity discountCard=xxxx balanceDebitCard=xxxx
#Description of parameters
id - product identifier from products.csv
quantity - quantity of goods
discountCard=xxxx - number of the discount card from discountCards.csv
balanceDebitCard=xxxx - balance on the debit card
```