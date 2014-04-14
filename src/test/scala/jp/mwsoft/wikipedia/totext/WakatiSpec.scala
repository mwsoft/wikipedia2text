package jp.mwsoft.wikipedia.totext

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.joda.time.format.DateTimeFormat

@RunWith(classOf[JUnitRunner])
class WakatiSpec extends FlatSpec with ShouldMatchers {

  "tokenize" should "concat prefixno whitespace" in {
    println(Wakati.tokenize("お休み なさい"))
    Wakati.tokenize("お休みなさい") should be === "お休み なさい"
  }

  "tokenize" should "concat suffix no whitespace" in {
    println(Wakati.tokenize("可能性がある"))
    Wakati.tokenize("可能性がある") should be === "可能性 が ある"
  }

  "tokenize" should "concat multiple katakana no whitespace" in {
    println(Wakati.tokenize("セミファイナルの結果"))
    Wakati.tokenize("セミファイナルの結果") should be === "セミファイナル の 結果"
  }

  "tokenize" should "concat nakaguro with katakana no whitespace" in {
    println(Wakati.tokenize("バラク・オバマ大統領"))
    Wakati.tokenize("バラク・オバマ大統領") should be === "バラク・オバマ 大統領"
  }

  "tokenize" should "concat lastname - firstname no whitespace" in {
    println(Wakati.tokenize("山田太郎さん"))
    Wakati.tokenize("山田太郎さん") should be === "山田太郎さん"
  }
}

