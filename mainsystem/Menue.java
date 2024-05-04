package game.mainsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menue {
	//インスタンス生成
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	//メソッド宣言
	//タイムリミットの有無を選択して、時間を決定するメソッド
	public int limit() {
		int limittime = 0;
		System.out.println("\n制限時間を設けますか?");
		if(select()) {
			System.out.println("\n制限時間を設けます。");
			System.out.println("何分にしますか？1～127の整数で入力してください。(推奨15分)");
			while(true) {
				try {
					limittime = Byte.parseByte(reader.readLine());
					if (limittime < 1 ) {
						System.out.println("値が小さすぎます");
						System.out.println("1～127の整数で入力してください");
					} else if (limittime > 127) {
						System.out.println("値が大きすぎます");
						System.out.println("1～127の整数で入力してください");
					} else {
						System.out.println("制限時間を" + limittime + "分に設定しました。");
						System.out.println("\n----------------------------------------------------------------------------\n");
						break;
					}
				} catch (NumberFormatException e) {
					System.out.println("入力値が不正です。");
					System.out.println("1～127の整数で入力してください");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			System.out.println("制限時間は設けません。");
			System.out.println("\n----------------------------------------------------------------------------\n");
		}
		return limittime;
	}

	//継続確認メソッド
	public boolean continueCheck() {
		System.out.println("もう一度遊びますか？");
		boolean select = select();
		if(select) {
			System.out.println("ゲームを続行します");
			System.out.println("\n----------------------------------------------------------------------------");
		} else {
			System.out.println("ゲームを終了します。");
			System.out.println("\n----------------------------------------------------------------------------");
		}
		return select;
	}

	//Yes(1)かNo(2)を選択してboolean型の戻り値を返すメソッド
	private boolean select() {
		byte choice;
		while(true) {
			System.out.println("Yes→1 | No→2");
			try {
				choice = Byte.parseByte(reader.readLine());
				if (choice < 1 || choice > 2) {
					System.out.println("入力値が不正です。");
					System.out.println("1か2のどちらかで再入力してください");
				} else {
					if(choice == 1) {
						return true;
					} else if(choice == 2) {
						return false;
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("入力値が不正です。");
				System.out.println("1か2のどちらかで再入力してください");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
