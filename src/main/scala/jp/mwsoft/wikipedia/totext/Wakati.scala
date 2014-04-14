package jp.mwsoft.wikipedia.totext

import java.lang.Character.UnicodeBlock
import org.atilika.kuromoji.Tokenizer
import scala.collection.JavaConversions._
import org.atilika.kuromoji.Token

object Wakati {

  lazy val tokenizer = Tokenizer.builder.build

  case class TokenInfo(
    val surface: String, val katakana: Boolean,
    val lastName: Boolean, val firstName: Boolean,
    val prefix: Boolean, val suffix: Boolean) {

    def this(token: Token) {
      this(
        token.getSurfaceForm,
        token.getSurfaceForm.forall(c => UnicodeBlock.of(c) == UnicodeBlock.KATAKANA),
        token.getAllFeatures.contains("人名,姓"),
        token.getAllFeatures.contains("人名,名"),
        token.getAllFeatures.startsWith("接頭詞"),
        token.getAllFeatures.contains(",接尾"))
    }
  }

  def tokenize(str: String): String = {
    val tokens = tokenizer.tokenize(str)
    val builder = new StringBuilder()

    var prevTokenInfo: Option[TokenInfo] = None
    for (token <- tokens) {
      val info = new TokenInfo(token)
      for (prev <- prevTokenInfo) {
        val splitStr =
          if (info.katakana && prev.katakana) ""
          else if (info.surface == "・" && prev.katakana) ""
          else if (info.katakana && prev.surface == "・") ""
          else if (info.firstName && prev.lastName) ""
          else if (prev.prefix) ""
          else if (info.suffix) ""
          else " "
        builder.append(splitStr)
      }
      builder.append(token.getSurfaceForm)
      prevTokenInfo = Some(info)
    }
    builder.toString
  }

}