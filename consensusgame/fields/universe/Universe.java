package game.consensusgame.fields.universe;

import game.consensusgame.fields.Field;

public class Universe implements Field{
	//フィールド宣言
	//アイテム(正解順)
	private String[] answerItems = {"酸素ボンベ", "水19リットル", "月面用の星座表", "宇宙食", 
			"ソーラー発電式FM送受信機", "ナイロン製ロープ", "注射器入り救急箱", "パラシュート", "自動的に膨らむ救命ボート",
			"照明弾", "45口径ピストル×2丁", "粉ミルク×1ケース", "ソーラー発電式の携帯用ヒーター" , "方位磁石" , "箱に入ったマッチ"};

	//テストコード
	//private String[] answerItems = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};

	//解説(正解順)
	private String[] reason = {"酸素は人が生きていく上で絶対に必要。重力が小さいため重さは考慮する必要はない。",
			"水も人が生きていく上で絶対に必要。特に日光があたる月面では大量の水分が失われるので水分補給が欠かせない。", 
			"母艦に向かうとき等、進むべき方向を確かめるのに必要。", "エネルギーを補給するために必要。", 
			"母船との通信に使うことができる。ただし距離が離れていると通信は困難。近距離での通信しかでない。",
			"崖の高さを測定したり、けが人の運搬に使うことができる。", "宇宙服の特殊孔からビタミン剤や薬を注入することができる。", 
			"物を運ぶ時や日よけに利用可能。", "運搬、日よけ、ガスが推進力になる。",	"母船が見えた時に避難信号を送れる。", 
			"発射の振動が推進力になる。", "宇宙食と重複。使うには水が必要。", "月の温かさがあるため、陽が当たらないときのみ有効。", 
			"月では磁気が無いので意味がない。" , "酸素が無い宇宙では使えない。"};

	//状況説明の内容
	private String explanation  = "あなたは宇宙船に乗って月面に着陸しようとしている宇宙飛行士です。"
			+ "\n月面には母船が待っていますが、機械の故障で母船から約200km離れた所に不時着してしまいました。"
			+ "\nまた、搭載していた機械の多くも破損してしまい、使えそうなものは" + answerItems.length + "アイテムしかありません。"
			+ "\nこれらのアイテムの中で生存に必要なものは何か、優先順位をつけてください。";

	//スコア記録用のテキストファイルの相対パス
	private String passname = "games/game/consensusgame/fields/universe/UniverseRecord";

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
