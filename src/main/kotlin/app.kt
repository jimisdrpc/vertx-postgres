import io.vertx.kotlin.pgclient.PgConnectOptions
import io.vertx.kotlin.sqlclient.PoolOptions




    fun main(args: Array<String>) {

        // Connect options
        var connectOptions = PgConnectOptions(
            port = 5432,
            host = "the-host",
            database = "the-db",
            user = "user",
            password = "secret")

// Pool options
        var poolOptions = PoolOptions(
            maxSize = 5)

// Create the client pool
        var client = PgPool.pool(connectOptions, poolOptions)

// A simple query
        client.query("SELECT * FROM users WHERE id='julien'").execute({ ar ->
            if (ar.succeeded()) {
                var result = ar.result()
                println("Got ${result.size()} rows ")
            } else {
                println("Failure: ${ar.cause().getMessage()}")
            }

            // Now close the pool
            client.close()
        })
    }
