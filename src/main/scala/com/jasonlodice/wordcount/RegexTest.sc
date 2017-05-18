val pattern = raw"\b([aA-zZ'\-]+)\b".r.unanchored
//val line = "The quick red-brown fox 88 ##jfjkd jumped over the lazy dog's bed."
val line = "The quick red-brown fox 88 ##jfjkd jumped over the lazy dog's bed."
pattern.findAllIn(line).toList
//(for (m <- pattern.findAllMatchIn(line)) yield m.group(1)).toList

val word = "despicable"
val s = word.groupBy(_.toChar)
  .map(c => (c._1, c._2.length))
  .reduceLeft((x,y) => if(y._2 > x._2) y else x)