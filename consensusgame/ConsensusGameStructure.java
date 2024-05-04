package game.consensusgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import game.consensusgame.fields.Field;
import game.consensusgame.fields.desert.Desert;
import game.consensusgame.fields.snowymountain.SnowyMountain;
import game.consensusgame.fields.universe.Universe;

public class ConsensusGameStructure {
	//フィールド宣言
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	List<String> answeritems = new ArrayList<>();
	List<String> shuffleditems = new ArrayList<>();
	List<String> choiceditems = new ArrayList<>();
	Field field;
	boolean flag = true;

	//コンストラクタ
	public ConsensusGameStructure(int choice){
		switch (choice) {
		case 1: {
			field = new Universe();
		}
		break;
		case 2: {
			field = new Desert();
		}
		break;
		case 3: {
			field = new SnowyMountain();
		}
		break;
		}
	}

	//メソッド宣言
	//状況を説明するメソッド
	public void getExplanation() {
		System.out.println("（状況）");
		System.out.println(field.getExplanation());
		//Listに配列の要素を追加
		for(int i = 0; i < field.getLength() ; i++) {
			answeritems.add(field.getItem(i));
			shuffleditems.add(field.getItem(i));
		}
		System.out.println("\n(手元にあるアイテム)");
		//要素をシャッフルして表示
		shuffleItems();
		for(int i = 0; i < field.getLength(); i++) {
			System.out.println(shuffleditems.get(i));
		}
	}

	//配列をシャッフルして表示するメソッド
	public void  shuffleItems() {
		if (field.getLength() > 1) {
			// ランダムに並び替える
			for (int i = 0; i < shuffleditems.size(); i++) {
				//0～要素数(限界値を含まない)までのランダムな値を得るメソッド
				int index = ThreadLocalRandom.current().nextInt(shuffleditems.size());
				//要素入れ替える
				String shuffleditem = shuffleditems.get(index);
				shuffleditems.set(index, shuffleditems.get(i));
				shuffleditems.set(i, shuffleditem);
			}
		}
	}

	//アイテムを選択するメソッド
	public void chooseItem() {
		System.out.println("\n----------------------------------------------------------------------------\n");
		System.out.println("上記のアイテムのうち、優先度が高いと思うものから順番に入力してください。(入力例:"
				+ shuffleditems.get((int)(Math.random() * shuffleditems.size())) + ")");
		//選択したアイテムの入力
		for(int i = 0; i < field.getLength(); i++) {
			if(flag) {
				String choice;
				System.out.println("優先度" + (i + 1));
				while(true) {
					try {
						choice = reader.readLine();
						if(judgementItem(choice)) {
							choiceditems.add(choice);
							break;
						} else {
							System.out.println("再入力してください。");
						}
					} catch (IOException e)  {
						e.printStackTrace();
					}
				}
				//選択したアイテムを順に表示
				System.out.println("\n(優先順位)");
				for(int j = 0; j < choiceditems.size(); j++) {
					System.out.println(j + 1 + "." + choiceditems.get(j));
				}
				//ランダムなアイテムのリストから選択したアイテムを削除
				shuffleditems.remove(choice);
				//残りのアイテムを表示
				System.out.println("\n(残りのアイテム)");
				for(int j = 0; j < shuffleditems.size(); j++) {
					System.out.println(shuffleditems.get(j));
				}
				System.out.println("\n----------------------------------------------------------------------------\n");
			} else {
				break;
			}
		}
	}

	//引数の文字列が正解のリストの中に含まれており、同じ選択を繰り返していないか確認するメソッド
	public boolean judgementItem(String choice) {
		//正解のリストの要素に入力した文字列が含まれているか確認する。
		if(answeritems.contains(choice)) {
			//同じ回答を繰り返していないか確認する
			for(int i = 0; i < field.getLength(); i++) {
				if(choiceditems.contains(choice)) {
					System.out.println("同じものは選択できません。");
					return false;
				}
			}
			return true;
		}
		System.out.println("選択肢に含まれていません。");
		return false;
	}

	//回答の得点を返すメソッド
	public int getScore() {
		int score = 0;
		for(int i = 0; i < field.getLength(); i++) {
			//正解の配列の添字 - 選択した要素の添え字 の絶対値
			score += Math.abs(i - choiceditems.indexOf(answeritems.get(i)));
		}
		return score;
	}

	//(最悪の答えをした時の)最大点数を返すメソッド
	public int getFullScore() {
		int fullscore = 0;
		int stack = field.getLength() - 1;
		for(int i = 0; i < field.getLength() / 2; i++) {
			fullscore += (stack * 2);
			stack = stack - 2;
		}
		return fullscore;
	}

	//選択肢ごとの正当順位を示すメソッド
	public void getReason() {
		getSleep(2000);
		System.out.println("\n----------------------------------------------------------------------------\n");
		System.out.println("正解の優先順位は以下の通りです。\n");
		for(int i = 0; i < field.getLength(); i++) {
			System.out.println((i + 1) + "." + field.getAnswerItems(i));
			System.out.println(field.getreason(i) + "\n");
			getSleep(100);
		}
		System.out.println("----------------------------------------------------------------------------\n");
		getSleep(3000);
	}

	//sleepメソッドのtry/catchをまとめたメソッド
	private void getSleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//ファイルの相対パスを得るメソッド
	public String getPass() {
		return field.getPass();
	}
}
