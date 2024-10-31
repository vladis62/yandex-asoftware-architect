rs.initiate({
    _id: "rs-shard2",
    version: 1,
    members: [{_id: 0, host: "mongodb-shard2-node-a:27017"}, {_id: 1, host: "mongodb-shard2-node-b:27017"}, {_id: 2, host: "mongodb-shard2-node-c:27017"},]
})