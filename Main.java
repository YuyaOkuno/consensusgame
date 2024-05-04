package game;

import game.consensusgame.ConsensusGameFlow;
import game.mainsystem.GemeFlowBase;
import game.mainsystem.Menue;

public class Main {
	public static void main(String[] args) {
		Menue menue = new Menue();
		GemeFlowBase gemeflow = new ConsensusGameFlow();
		//ゲームについての説明
		gemeflow.getExplanation();
		while(true) {
			int limittime = 0;
			//タイマーの有無を決定
			//タイマー選択メニューをなくしたい場合、以下の1行をコメントアウトする
			limittime = menue.limit();
			//ゲームの開始
			gemeflow.mainFlow(limittime);
			//継続の確認
			if(!(menue.continueCheck())) {
				break;
			}
		}
	}
}
