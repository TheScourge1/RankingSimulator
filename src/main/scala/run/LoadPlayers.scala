package run

import rankingSim.dao.PlayerRepo
import rankingSim.models.{InputMatch, Player}

import scala.concurrent.Await
import scala.concurrent.duration._
import scala.io.Source

object LoadPlayers extends App{

  def readFile(fname: String): Seq[String] = {
    val fileHandle = getClass.getResourceAsStream("/loadData/"+fname)
    if(fileHandle == null) throw new Exception("File not found: "+"/"+fname)
    return Source.fromInputStream(fileHandle).getLines.toSeq
  }

  val inputPlayerDao = new PlayerRepo

  var players = readFile("BVLA_HE_20190213-103912_2.csv")
  println("Loading player data: "+players.size)
  players = players.splitAt(1)._2//skipping the header column names

  val itterator = players.grouped(1000)
  var count = 0

  while(itterator.hasNext) {
    try {
      val nextBatch = itterator.next()
      val result = Await.ready(
        inputPlayerDao.save(
          nextBatch.map(s => Player.fromStringSequence(s.split(";"))))
        , 1 minute)
      count+=nextBatch.size
      println(s"$count players written")
    } catch {
      case ex: Exception => println("Error saving players\n" + ex,ex.printStackTrace())
    }
  }
}
