package com.jasonlodice.wordcount

import scala.io.Source

/**
  * Created by jason on 4/14/17.
  */
object WordCount {
  private val wordPattern = raw"\b([aA-zZ'\-]+)\b".r.unanchored

  def main(args: Array[String]): Unit = {

    if (args.length < 1) return

    val fileName: String = args(0)

    def compareWords(word1: (String, Char, Int), word2: (String, Char, Int)) = {
      if (word2._3 > word1._3) word2 else word1
    }

    def countMaxChar(word: String): (String, Char, Int) = {
      val maxChar = word.groupBy(_.toChar)
        .map(c => (c._1, c._2.length))
        .reduceLeft((x, y) => if (y._2 > x._2) y else x)

      (word, maxChar._1, maxChar._2)
    }

    def extractWords(line: String) = {
      wordPattern.findAllIn(line).toList
    }

    val maxWord = Source.fromFile(fileName)
      .getLines()
      .flatMap(extractWords)
      .map(countMaxChar)
      .foldLeft(("", '\0', 0))(compareWords)

    println(maxWord._1)
  }
}
