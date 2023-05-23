package de.htwg.se.minesweeper.fileIO
import com.mongodb.{ServerApi, ServerApiVersion}
import org.mongodb.scala.{ConnectionString, MongoClient, MongoClientSettings, MongoDatabase}
import org.mongodb.scala.bson.Document
import org.mongodb.scala.model.Updates.set
import org.mongodb.scala.*
import org.mongodb.scala.bson.ObjectId
import org.mongodb.scala.model.Filters.equal
import org.mongodb.scala.model.Projections.excludeId
import com.mongodb.client.model.UpdateOptions
import org.mongodb.scala.result.{DeleteResult, InsertOneResult, UpdateResult}
import de.htwg.se.minesweeper.model.grid._
import de.htwg.se.minesweeper.model.cell.{HiddenCell, FlagCell, OpenCell, ICell}
import de.htwg.se.minesweeper.fileIO.IFileIO
import scala.concurrent.Await
import scala.util.Using
import scala.concurrent.duration._
import org.mongodb.scala.MongoCollection
import scala.util.Success
import scala.util.Failure
import org.mongodb.scala.SingleObservable
import com.mongodb.client.model.UpdateOptions
import scala.util.Try

class MongoDAO extends IFileIO {
    private val databaseUrl: String = "mongodb://root:root@mongodb:27017/?authSource=admin"        

    private val client: MongoClient                       = MongoClient(databaseUrl)
    private val db: MongoDatabase                         = client.getDatabase("mongodb")
    private val gridCollection: MongoCollection[Document] = db.getCollection("grid")
    val ping = db.runCommand(Document("ping" -> 1)).head()
    Await.result(ping, 10.seconds)
    System.out.println("Pinged your deployment. You successfully connected to MongoDB!")    
    private val waitTime = Duration(15, SECONDS)

    private var idCounter = 1

    override def save(grid: IGrid): Unit = {
        update(idCounter, grid)
    }

    def update(id: Int, grid: IGrid): Unit = {
        val json = grid.toString
        println(json)
        Await.result(
          gridCollection
              .updateOne(equal("_id", id), set("grid", json), UpdateOptions().upsert(true))
              .asInstanceOf[SingleObservable[Unit]]
              .head(),
          waitTime
        )
    }

    override def load(id: Int): Option[IGrid] = {
        val document = Await.result(
          gridCollection
              .find(equal("_id", id))
              .projection(excludeId())
              .first()
              .head(),
          waitTime
        )
        Some(new Grid(0,0).fromString(document.get("grid").get.asString().getValue).get)
    }

    def delete(id:Int): Unit = {
       
        val deleteId =  Await.result(
                  gridCollection.find().sort(Document("_id" -> -1)).first().head(),
                  waitTime
                )
                .get("_id")
                .get
                .asInt32()
                .getValue
        

        Await.result(
          gridCollection
              .deleteOne(equal("_id", deleteId))
              .asInstanceOf[SingleObservable[Unit]]
              .head(),
          waitTime
        )
    }
}
