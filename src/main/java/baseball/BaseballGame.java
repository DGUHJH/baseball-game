package baseball;

import static baseball.utils.Constant.*;

import baseball.players.Computer;
import baseball.players.User;
import camp.nextstep.edu.missionutils.Console;

public class BaseballGame {
	private Computer computer;
	private User user;
	private int ballCount;
	private int strikeCount;

	public BaseballGame() {
		this.computer = new Computer();
		this.user = new User();
		this.ballCount = 0;
		this.strikeCount = 0;
	}

	/**
	 * 전체 게임을 플레이하는 메소드. 각 회차가 종료되면 게임 재시작 여부를 입력받아서 재시작 또는 종료를 한다.
	 */
	public void run() {
		try {
			System.out.println(START_GAME_MSG);
			do {
				playGame();
			} while (checkRetry());
			System.out.println(END_MSG);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 한 회차의 게임 플레이를 하는 메소드.
	 * 
	 * @throws IllegalArgumentException User에서 input 도중 IllegalArgumentException이
	 *                                  발생할 경우 그대로 예외를 던져서 상위 메소드에서 처리한다.
	 */
	public void playGame() throws IllegalArgumentException {
		do {
			user.inputNumbers();
			printHint();
		} while (!countResult());
	}

	/**
	 * 스트라이크와 볼의 개수를 센다.
	 */
	private boolean countResult() {
		strikeCount = 0;
		ballCount = 0;
		for (int i = 0; i < LENGTH_OF_NUMBERS; i++) {
			if (user.getNumber(i) == computer.getNumber(i)) {
				strikeCount++;
			} else if (computer.getIndex(user.getNumber(i)) != i && computer.getIndex(user.getNumber(i)) != -1) {
				ballCount++;
			}
		}
		if (strikeCount != LENGTH_OF_NUMBERS) {
			return false;
		}
		return true;
	}

	/**
	 * 스트라이크 / 볼 결과를 바탕으로 힌트를 출력한다.
	 */
	private void printHint() {
		String resultString = "";
		if (ballCount != 0) {
			resultString += ballCount + BALL;
		} else if (ballCount == strikeCount) {
			resultString = NOTHING;
		}
		if (strikeCount != 0) {
			resultString += strikeCount + STRIKE;
		}
		System.out.println(resultString);
	}

	/**
	 * 사용자에게 게임 재시작 여부를 입력받는다.
	 * 
	 * @return 1을 입력받으면 true, 2를 입력받으면 false를 반환하고, 그 외의 경우
	 *         IllegalArgumentException을 발생시킨다.
	 */
	private boolean checkRetry() {
		System.out.println(CHECK_END_MSG);
		String input = Console.readLine();
		if (input.equals("1")) {
			return true;
		}
		if (input.equals("2")) {
			return false;
		}
		throw new IllegalArgumentException(ERR_INPUT_MSG);
	}
}
