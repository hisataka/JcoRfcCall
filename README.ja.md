JcoRfcCall
======================
JavaからSAPの汎用モジュールをRFC経由で呼び出すサンプルです。  
使用するには、SAP提供のJco3.0のドライバ（sapjco3.dll、sapjco3.jar）、  
Microsoft Visual Studio 2005 C/C++ランタイムライブラリ、  
JRE1.5もしくはJRE1.6が必要です。  

大まかな処理
------------
１．接続プロパティファイルを作成  
２．destinationを取得  
３．リポジトリからファンクションを取得  
４．パラメータを設定  
５．実行  
６．結果処理  