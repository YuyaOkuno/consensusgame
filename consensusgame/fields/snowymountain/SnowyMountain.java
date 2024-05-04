package game.consensusgame.fields.snowymountain;

import game.consensusgame.fields.Field;

public class SnowyMountain implements Field{
	//フィールド宣言
	//アイテム(正解順)
	private String[] answerItems = {"大箱入りのマッチ", "板チョコ10枚", "固形油の入った金属缶", "大型懐中電灯", 
			"新聞紙朝刊５日分", "ライフル一丁", "サバイバルナイフ", "スキーセット一式", "ウイスキーひと瓶",	"方位磁石"};

	//テストコード
	//private String[] answerItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

	//解説(正解順)
	private String[] reason = {"枯れ木等に火をつけて暖を取るために絶対必要。また明かりを生み出し捜索隊へ位置を知らせることもできる。", 
			"寒さをしのぐためのカロリー補給・エネルギー源として必要。板チョコ一枚（100ｇ）でおよそ550キロカロリー。", 
			"固形油を体に塗ることによって寒さを防ぐことができる。燃料として使うこともできる。また、缶のふたで太陽を反射させることによって捜索隊への目印にもなる。", 
			"捜索隊に知らせる合図として役立つ。近くを移動する時にも便利。ただし、温度が低いので電池は通常と比べて長持ちしない。", 
			"火をつける時に役に立つ。また服の下に入れると寒さを防ぐ効果がある。大きく広げることにより、目印にももなる。", 
			"捜索隊に知らせる時の音の合図として使える。しかし、野性動物を仕留めるのには熟練が必要なので素人には難しい。極限状態になり、イライラしてくると殺し合いの凶器にもなるので危険。", 
			"枝を切って薪を作るぐらいしか利用価値はない。またライフル同様、殺し合いの凶器になる。", 
			"助けを求めに行くのに使えそうだが、移動は危険なので必要度は高くない。旗を立てる棒や燃やす材料ぐらいには使える", 
			"飲めば体が温まると言う意見もあるが、それは間違い。実際は、眠くなるので危険。消毒薬の代わりとしては使える。", 
	"移動する時に使えそうでだが、移動せず助けを待つので、実際は何の役にも立たない。"};

	//状況説明の内容
	private String explanation  = "みなさんは雪山に不時着したスキー客です。\n"
			+ "あなたの乗っている飛行機が、アメリカとカナダの国境付近の雪山に墜落しました。\n"
			+ "残念ながら操縦士たちは死亡。機体は湖の底に沈没したようです。\n"
			+ "しかし、幸いなことにあなた達は、無傷で飛行機からの脱出に成功しています。\n"
			+ "墜落地点から一番近い街は、ここから32キロ離れたところにあります。\n"
			+ "このあたりの夜の気温は最大でマイナス40度まで下がります。\n"
			+ "周りを見渡すと、枯れ木が沢山落ちていることがわかります。\n"
			+ "あなた達が生き残るため、以下の" + answerItems.length + "アイテムの中で生存に必要なものは何か、優先順位をつけてください。";

	//スコア記録用のテキストファイルの相対パス
	private String passname = "games/game/consensusgame/fields/snowymountain/SnowyMountainRecord";

	//メソッド宣言	
	//状況のフィールドを与えるメソッド
	@Override
	public String getExplanation() {
		return explanation;
	}
	//配列の要素を与えるメソッド
	@Override
	public String getAnswerItems(int i) {
		return answerItems[i];
	}
	//配列の長さを与えるメソッド
	@Override
	public int getLength() {
		return answerItems.length;
	}
	//配列の要素(正解)を与えるメソッド
	@Override
	public String getItem(int i) {
		return answerItems[i];
	}
	//配列の要素(理由)を与えるメソッド
	@Override
	public String getreason(int i) {
		return reason[i];
	}
	//スコア記録ファイルのパスを返すメソッド
	@Override
	public String getPass() {
		return passname;
	}
}
