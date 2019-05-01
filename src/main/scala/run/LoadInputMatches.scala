package run

import scala.io.Source
import rankingSim.dao.InputMatchRepo
import rankingSim.models.InputMatch

import scala.concurrent.Await
import scala.concurrent.duration._
import java.io.File
import scala.io.Source

object LoadInputMatches extends App{

  val RELATIVE_DIRECTORY = "/loadData/matchresults"
  val inputMatchRep = new InputMatchRepo

  val directory = new File(getClass.getResource(RELATIVE_DIRECTORY).getPath)
  val files = directory.listFiles()

  if(files.size == 0) throw new Exception("File not found in relative path: "+RELATIVE_DIRECTORY)
  println("Found files to load: ")
  files.foreach(f => println(f.getName))

  for(file <- files){
    println("Processing File: "+file.getName)

    val linesInFile = Source.fromFile(file).getLines().toSeq
    val matches = linesInFile.splitAt(1)._2 //skipping the header column names

    val itterator = matches.grouped(1000)
    var count = 0

    while(itterator.hasNext) {
      try {
        val nextBatch = itterator.next()
        Await.ready(
          inputMatchRep.save(
            nextBatch
                .filter(s => !s.contains("#N/B"))
                .map(s => InputMatch.fromStringSequence(s.split(";"))))
          , 1 minute)
        count += nextBatch.size
        println(s"$count matches written from " + file.getName)
      } catch {
        case ex: Exception => {
          println("Error saving matches from " + file.getName + "\n" + ex); throw ex
        }
      }
    }
  }

  //TODO: write validate file method, converting all file lines to inputmatch object without save
}
