var params = {
        TableName: "customer",
        KeySchema: [{
            AttributeName: "customerId",
            KeyType: "HASH",
        }],
        AttributeDefinitions: [{
            AttributeName: "customerId",
            AttributeType: "S"
        }],
        ProvisionedThroughput: {
            ReadCapacityUnits: 1,
            WriteCapacityUnits: 1
        }
    }
;
dynamodb.createTable(params, function (err, data) {
    if (err) ppJson(err); // an error occurred
    else ppJson(data);//successful response
});
