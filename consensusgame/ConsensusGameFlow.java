package game.consensusgame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import game.mainsystem.GemeFlowBase;

public class ConsensusGameFlow extends Thread implements GemeFlowBase{
	//フィールド宣言
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	List<Integer> record = new ArrayList<>();
	List<Integer> ranking = new ArrayList<>();
	static ConsensusGameStructure consensus = null;
	static boolean flag = true;
	static int limittime;
	static int score;
	static byte choice;
	String filepass = null;

	//メソッド宣言
	//コンセンサスゲームの主な流れ
	@Override
	public void mainFlow(int limittime) {
		ConsensusGameFlow.limittime = limittime;
		//フィールドを選択
		choice = chooseMord();
		consensus = new ConsensusGameStructure(choice);
		//状況を説明
		consensus.getExplanation();
		//タイマー有りの場合
		if(limittime > 0) {
			Thread timer = new ConsensusGameFlow();
			timer.start();
			//スレッド表示の為、メインスレッドは一時停止
			getSleep(5);
		}
		//優先度順にアイテムを選択
		consensus.chooseItem();
		//flagをfalseにしてスレッドを終了させる
		flag = false;
		//結果を表示
		getResult();
		//解説を表示
		consensus.getReason();
		//スコアを記録してランキングを表示
		Record();
	}

	//タイマー(スレッド)
	@Override
	public void run() {
		System.out.println("\n----------------------------------------------------------------------------\n");
		System.out.println("制限時間は" + limittime + "分です。");
		System.out.println("ディスカッションを開始してください。");
		outer:
			for(int i = 1; i <= limittime; i++) {
				//1秒ごとにflagを確認
				for(int j = 0; j < 60; j++) {
					getSleep(1000);
					if(!flag) {
						break outer;
					}
				}
				//メインスレッドが終了していない場合(タイマー発動)
				if(flag) {
					if(i < limittime) {
						System.out.println(i + "分経過しました。");
						System.out.println("残り" + (limittime - i) + "分です。");
						System.out.println("\n----------------------------------------------------------------------------\n");
						//指定の時間を経過したら審議の終了を伝える
					} else {
						System.out.println(i + "分経過しました。");
						consensus.flag = false;
						System.out.println("制限時間終了です。");
						System.out.println("回答を入力してください。");
						System.out.println("\n----------------------------------------------------------------------------\n");
						break;
					}
					//メインスレッドが終了している場合(タイマー不動)
				} else {
					break;
				}
			}
	}

	//ゲームの内容を説明するメソッド
	@Override
	public void getExplanation() {
		System.out.println("(コンセンサスゲーム)");
		System.out.println("このゲームの目的はグループで話し合い、全員が納得できる結論を導き出すことです。\n"
				+ "ある課題についてチームのメンバーで合意形成を行ってください。\n"
				+ "正解との誤差が小さいほど、より良い解答になります。");
		System.out.println("\n----------------------------------------------------------------------------");
	}

	//モードを選択するメソッド
	public byte chooseMord() {
		byte choice;
		while(true) {
			System.out.println("場面を選択してください(1～3の数字で選択してください)");
			System.out.println("1:宇宙　2:砂漠　3:雪山");
			try {
				choice = Byte.parseByte(reader.readLine());
				if (choice > 0 && choice < 4) {
					System.out.println("\n----------------------------------------------------------------------------\n");
					break;
				} else {
					System.out.println("入力値が不正です。");
					System.out.println("1～3の数字から選択して入力してください。");
					System.out.println("\n----------------------------------------------------------------------------\n");
				}
			} catch (NumberFormatException e) {
				System.out.println("入力値が不正です。");
				System.out.println("1～3の数字から選択して入力してください。");
				System.out.println("\n----------------------------------------------------------------------------\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return choice;
	}

	//結果を表示するメソッド
	@Override
	public int getResult() {
		System.out.println("採点を開始します");
		for(int i = 0; i < 3; i++) {
			getSleep(500);
			System.out.print("・");
		}
		System.out.println("\nスコアが小さいほど正解の回答と近く、生き残る確率が高まります。\n");
		getSleep(1000);
		//回答のスコアを算出
		score = consensus.getScore();
		//最悪の答えを出した時のスコアを算出
		int fullscore = consensus.getFullScore();
		System.out.println("スコアは" + score  + "/" + fullscore + "点です。");
		if(score == 0) {
			System.out.println("すごい！！完璧な生存戦略です。");
		} else if (score < fullscore  /2){
			System.out.println("生き残る確率が高いです。");
		} else if (score == fullscore / 2){
			System.out.println("生き残る確率が有ります。");
		} else if (score < fullscore){
			System.out.println("死ぬ確率が高いです。");
		} else if (score == fullscore){
			System.out.println("残念！！殆どの確率で死にます。");
		}
		return score;
	}

	//sleepメソッドのtry/catchをまとめたもの
	private void getSleep(int i) {
		try {
			sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	//歴代スコアの取得、ランキングの表示、スコアの記録を行なうメソッド
	private void Record() {
		//記録用テキストファイルの相対パスを取得
		filepass = consensus.getPass();
		//歴代のスコアをリストに代入
		getRecord();
		//ランキングを表示
		getRanking();
		//スコアを記録
		updateRecord();
		System.out.println("\n----------------------------------------------------------------------------\n");
	}

	//ファイルの相対パスを用いて歴代スコアをrecordリストに代入するメソッド
	private void getRecord() {
		try {
			BufferedReader filereader = new BufferedReader(new FileReader(filepass));
			String line;
			//recordリストに歴代の記録を代入
			while((line = filereader.readLine()) != null) {
				//文字列をint型の整数に変換してrecordリストに代入
				record.add(Integer.parseInt(line));
			}
			filereader.close();
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが見つかりません。");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	//rankingリストを並び替えてランキングを表示するメソッド
	private void getRanking() {
		System.out.println("(あなたのスコア)\n" + score);
		//recordリストの中身をrankingリストにコピー
		for(int i = 0; i < record.size(); i++) {
			ranking.add(record.get(i));
		}
		//rankingリストを降順に並び替え
		Collections.sort(ranking);
		System.out.println("\n(歴代のスコアランキング)");
		Map<Integer, Integer> map = new LinkedHashMap<>();
		//rankはランキングの番号
		int rank = 1;
		try {
			map.put(ranking.get(0), rank);
			//iは通し番号
			System.out.println(rank + "位:" + ranking.get(0));
			//ランク付け(点数が同じものは同じランキング)
			for (int i = 1; i < ranking.size(); i++) {
				//今回表示する点数が前回の点数と異なる場合、通し番号を設定
				if (ranking.get(i) != ranking.get(i-1)) {
					rank = i + 1;
					map.put(ranking.get(i), rank);
					System.out.print(rank + "位:");
				} else {
					map.put(ranking.get(i), rank);
					System.out.print(rank + "位:");
				}
				System.out.println(ranking.get(i));
			}
			//ファイルにスコアが記録されていない場合
		} catch (IndexOutOfBoundsException e) {
			System.out.println("なし");
		}
		ranking.clear();
	}

	//相対パスを用いてスコアを得てリストに追加するメソッド
	private void updateRecord() {
		//リストに今回のスコアを代入する
		record.add(score);
		//ファイルにリストの中身を書き込む
		try {
			PrintWriter filewriter = new PrintWriter(new BufferedWriter(new FileWriter(filepass)));
			for(int i = 0; i < record.size(); i++) {
				filewriter.println(record.get(i));
			}
			filewriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが見つかりません。");
		} catch (IOException e) {
			System.out.println(e);
		}
		//リストの中身を全て消す
		record.clear();
	}
}
