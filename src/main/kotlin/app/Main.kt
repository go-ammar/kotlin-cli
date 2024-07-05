package app

import di.appModule
import io.github.cdimascio.dotenv.dotenv
import org.koin.core.context.GlobalContext.startKoin


fun main(args: Array<String>) {
//        System.getProperty("BASE_URL") //everything
//    println(System.getProperty("BASE_URL"))
     val dotenv = dotenv()
     val apiKey = dotenv["BASE_URL"]
    println(apiKey)
    CLI().run()

}