package jp.mwsoft.wikipedia.totext

import org.junit.runner.RunWith
import org.scalatest.matchers.ShouldMatchers
import org.scalatest.FlatSpec
import org.scalatest.junit.JUnitRunner
import org.joda.time.format.DateTimeFormat

@RunWith(classOf[JUnitRunner])
class UtilsSpec extends FlatSpec with ShouldMatchers {

  "removeWikiSymbols" should "remove all wiki syntax" in {
    val data1 = """{{WikipediaPage|ウィキペディアでのテスト編集や試し書きには、[[Wikipedia:サンドボックス]]をご利用ください。}}"""
    val result1 = """WikipediaPage ウィキペディアでのテスト編集や試し書きには、Wikipedia:サンドボックスをご利用ください。"""
    Utils.removeWikiSymbols(data1) should be === result1

    val data2 = """* [[TEST (x86命令)]] - [[x86]][[アセンブリ言語]]の命令。"""
    val result2 = """TEST (x86命令) - x86アセンブリ言語の命令。"""
    Utils.removeWikiSymbols(data2) should be === result2
  }

  "removeTag" should "remove html tag" in {
    val data1 = """<ul><li>14:46 2004年4月30日 [[利用者:Oxhop|Oxhop]] "[[:画像:LocationMacedonia.png|LocationMacedonia.png]]"をアップロードしました。 <em>(マケドニアの位置 - 英語版より)</em></li>"""
    val result1 = """14:46 2004年4月30日 [[利用者:Oxhop|Oxhop]] "[[:画像:LocationMacedonia.png|LocationMacedonia.png]]"をアップロードしました。 (マケドニアの位置 - 英語版より)"""
    Utils.removeTags(data1) should be === result1
  }

  "removeDisusedText" should "remove DEFAULTSORT line" in {
    val data1 = """DEFAULTSORT:けんこCategory:言語 *"""
    val result1 = ""
    Utils.removeDisusedText(data1) should be === result1
  }

  "removeDisusedText" should "remove Category line" in {
    val data1 = """Category:言語学Category:民族"""
    val result1 = "言語学民族"
    Utils.removeDisusedText(data1) should be === result1
  }

  "removeDisusedText" should "remove url" in {
    val data1 = """生物学的な観点から言語の起源を探ろうという試みもある。最近の分子生物学的研究によれば、FOXP2と名づけられている遺伝子に生じたある種の変異が言語能力の獲得につながった可能性があるhttp://www.ncbi.nlm.nih.gov/pubmed/11586359?dopt=Abstract Nature. 413(6855):519-23.。"""
    val result1 = "生物学的な観点から言語の起源を探ろうという試みもある。最近の分子生物学的研究によれば、FOXP2と名づけられている遺伝子に生じたある種の変異が言語能力の獲得につながった可能性がある Nature. 413(6855):519-23.。"
    Utils.removeDisusedText(data1) should be === result1
  }

}

