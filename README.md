wikipedia2text
==============

## 概要 ##

word2vecに日本語版Wikipediaの情報を渡そうと思った時に書いたコードです。

jawiki-latest-pages-articles.xml.bz2からテキストを抽出し、適当にWiki記法とかHTMLタグとかいらなそうな表記とかを削って、１ファイルとして出力します。あと、Kuromojiでわかち書きするオプションも付いてます。

## 必要環境 ##

JDK6以上がインストールされていること。

## 実行方法 ##

下記ページから　jawiki-latest-pages-articles.xml.bz2 を落としてくる。

        http://dumps.wikimedia.org/jawiki/latest/

コマンドの引数に落としたファイルのパスを指定して実行する。下記は同一ディレクトリに当該ファイルが存在する場合のコマンド。

       ./activator 'run jawiki-latest-pages-articles.xml.bz2'

わかち書きも行う場合は、--wakatiオプションを付ける。

       ./activator 'run -wakati jawiki-latest-pages-articles.xml.bz2'

出力されるファイルはplain textで6GBくらい、わかち書き版でGBくらい。

実行にかかる時間はうちの数年前に買ったPC（AthronⅡ X4 640。安物）で、plain textの出力が40分くらい、わかち書き版で

## 注意点 ##

それっぽい文書を出力できるように、手作業であれこれやってます。

Wiki記法をかなりざっくりとした書き方で消したり、HTMLタグを消したり、URLも除去したり、Helpとか曖昧さ回避とかのページは出力対象から外したり、CategoryとかREDIRECTとかDEFAULTSORTとか邪魔そうなところを消したり。

適当にやってるので、気になる人は適当にソース書き換えてください。

形態素解析についても、微妙に手を加えてます。具体的には姓名を連結したり、カタカナの連続を連結したり。そうしないとデフォルトの辞書だと「ポパイ」ですら「ポ | パイ」に分割されてしまうので。あと接頭語とか接尾語をくっつけたりもしています。人名の接尾（○○さんとか）はくっつけなくても良かった気がするけど、くっつけた方が良い場合もあるような気もする。

## ライセンス ##

MIT LICENSE

