import io.vertx.kotlin.pgclient.PgConnectOptions
import io.vertx.kotlin.sqlclient.PoolOptions
import io.vertx.pgclient.PgPool


fun main(args: Array<String>) {

        // Connect options
        var connectOptions = PgConnectOptions(
            port = 5432,
            host = "localhost",
            database = "mydb",
            user = "postgres",
            password = "Postgres2019!")

// Pool options
        var poolOptions = PoolOptions(
            maxSize = 5)

// Create the client pool
        var client = PgPool.pool(connectOptions, poolOptions)

// A simple query
        client.query("SELECT * FROM mytable").execute({ ar ->
            if (ar.succeeded()) {
                var result = ar.result()
                println("Got ${result.size()} rows ")
            } else {
                println("Failure: ${ar.cause().stackTrace}")
            }

            // Now close the pool
            client.close()
        })
    }
