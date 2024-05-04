package game.consensusgame.fields;

public interface Field {//フィールド宣言

	//アイテム(正解順)
	String fieldname  = null;
	String[] answerItems = {};
	String explanation  = null;

	//メソッド宣言
	//状況のフィールドを与えるメソッド
	String getExplanation();
	//配列の要素を与えるメソッド
	String getAnswerItems(int i);
	//配列の長さを与えるメソッド
	int getLength();
	//配列の要素(正解)を与えるメソッド
	String getItem(int i);
	//配列の要素(理由)を与えるメソッド
	String getreason(int i);
	//スコア記録ファイルのパスを返すメソッド
	String getPass();
}
