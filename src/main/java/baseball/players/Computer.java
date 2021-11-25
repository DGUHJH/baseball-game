package baseball.players;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;

import static baseball.utils.Constant.*;

public class Computer {

    private final ArrayList<Integer> numbers;

    /**
     * 생성자가 호출될 때 자동으로 숫자 배열을 생성하고
     * 배열 안에 들어갈 숫자들을 생성한다.
     */
    public Computer() {
        this.numbers = new ArrayList<>();
        createNumbers();
    }

    /**
     * 컴퓨터 플레이어가 가지는 세 자리 수를 생성한다.
     * 각 자리수의 숫자는 서로 다른 수이며 각 자리수를 ArrayList 에 저장한다.
     */
    public void createNumbers() {
        numbers.clear();
        do {
            int number = Randoms.pickNumberInRange(MIN_NUM, MAX_NUM);
            if (!numbers.contains(number)) {
                numbers.add(number);
            }
        } while (numbers.size() < LENGTH_OF_NUMBERS);
    }

    public ArrayList<Integer> getNumbers() {
        return numbers;
    }
}
