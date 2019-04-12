package run

import scala.io.Source
import rankingSim.dao.InputMatchDAO
import rankingSim.models.InputMatch

import scala.concurrent.Await
import scala.concurrent.duration._

object LoadInputMatches extends App{

  def readFile(fname: String): Seq[String] = {
    val fileHandle = getClass.getResourceAsStream("/loadData/"+fname)
    if(fileHandle == null) throw new Exception("File not found: "+"/"+fname)
    return Source.fromInputStream(fileHandle).getLines.toSeq
  }

  val inputMatchDao = InputMatchDAO

 // var matches = readFile("PBA_COMP_20182019.csv")
  var matches = readFile("PBO_20172018.csv")
  println("Loading match data: "+matches.size)
  matches = matches.splitAt(1)._2//skipping the header column names

  val itterator = matches.grouped(1000)
  var count = 0

  while(itterator.hasNext) {
    try {
      val nextBatch = itterator.next()
      Await.ready(
        inputMatchDao.save(
          nextBatch.map(s => InputMatch.fromStringSequence(s.split(";"))))
        , 1 minute)
      count+=nextBatch.size
      println(s"$count matches written")
    } catch {
      case ex: Exception => println("Error saving matches\n" + ex)
    }
  }
}
