rs.initiate({
    _id: "rs-shard1",
    version: 1,
    members: [{_id: 0, host: "mongodb-shard1-node-a:27017"}, {_id: 1, host: "mongodb-shard1-node-b:27017"}, {_id: 2, host: "mongodb-shard1-node-c:27017"},]
})