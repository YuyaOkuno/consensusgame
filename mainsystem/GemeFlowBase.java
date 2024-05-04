package game.mainsystem;

public interface GemeFlowBase{
	//ゲームの主な流れ
	public void mainFlow(int limittime);
	//ゲームの内容を説明するメソッド
	public void getExplanation();
	//結果を表示するメソッド
	public int getResult();
}