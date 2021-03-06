package baseball.players;

import static baseball.utils.Constant.*;
import static camp.nextstep.edu.missionutils.Console.*;

import java.util.ArrayList;

public class User implements Player {
	private final ArrayList<Integer> numbers;

	public User() {
		this.numbers = new ArrayList<>();
	}

	/**
	 * 콘솔로부터 세자리 수를 입력받아서 저장한다.
	 * 
	 * @throws IllegalArgumentException 잘못된 입력이 들어오면 발생시킨다.
	 */
	public void inputNumbers() throws IllegalArgumentException {
		numbers.clear();
		System.out.print(INPUT_MSG);
		String[] input = readLine().split("");
		checkInputIsNumber(readLine());

		for (int i = 0; i < input.length; i++) {
			numbers.add(Integer.parseInt(input[i]));
		}

		if (input.length != LENGTH_OF_NUMBERS) {
			throw new IllegalArgumentException(ERR_INPUT_MSG);
		}

		if (isOverlapped(numbers)) {
			throw new IllegalArgumentException(ERR_INPUT_MSG);
		}
	}

	private static boolean isOverlapped(ArrayList<Integer> numbers) {
		return numbers.size() != numbers.stream().distinct().count();
	}

	private static void checkInputIsNumber(String ch) {
		try {
			Integer.parseInt(ch);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException(ERR_INPUT_MSG);
		}
	}

	@Override
	public int getNumber(int index) {
		return numbers.get(index);
	}
}
