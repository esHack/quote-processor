# quote-processor

Follow the below the steps:

run the below maven command after downloading the code
```sh
mvn clean install -U
```

After that navigate to target folder , there should be a quote-processor.jar

Run the below command
```sh
java -jar quote-processor.jar [absolute path tocsv file] [loan amount required]

java -jar quote-processor.jar C:\Users\iames\OneDrive\Desktop\market.csv 1000
```

Output should be printed on the console

```sh
Requested amount:£1000
Rate:7.0%
Monthly repayment:£30.88
Total repayment:£1111.68
```
