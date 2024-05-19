CREATE TABLE Account (
    accountId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    balance DOUBLE
);
CREATE TABLE Transaction (
    transactionId INT AUTO_INCREMENT PRIMARY KEY,
    amount DOUBLE,
    originatingAccountId INT,
    resultingAccountId INT,
    transactionReason VARCHAR(255),
    fee DOUBLE
);
CREATE TABLE Bank (
    bankId INT AUTO_INCREMENT PRIMARY KEY,
    bankName VARCHAR(255),
    totalTransactionFeeAmount DOUBLE,
    totalTransferAmount DOUBLE,
    transactionFlatFeeAmount DOUBLE,
    transactionPercentFeeValue DOUBLE
);
