package jp.mwsoft.wikipedia.totext

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.joda.time.format.DateTimeFormat

@RunWith(classOf[JUnitRunner])
class PageArticleParserSpec extends FlatSpec with ShouldMatchers {

  val testfile = getClass.getResource("test.xml.bz2").getFile
  val dateParser = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

  val parser = new PageArticleParser(testfile)
  "first page" should "text.xml title, id, timestamp, text" in {
    val article = parser.next
    article.title should be === "Wikipedia:アップロードログ 2004年4月"
    article.id should be === 1
    article.lastUpdate should be === dateParser.parseMillis("2004-04-30T14:46:00Z")
    article.text should startWith("<ul><li>14:46 2004年4月30日")
  }

  "second page" should "text.xml title, id, timestamp, text" in {
    val article = parser.next
    article.title should be === "Wikipedia:削除記録/過去ログ 2002年12月"
    article.id should be === 2
    article.lastUpdate should be === dateParser.parseMillis("2002-12-06T09:23:16Z")
    article.text should startWith("Below is a list of the most recent deletions.")
  }

}

