import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LotteryLogic {//负责抽奖逻辑，包括抽取怪物名字、检查是否所有数字已被抽取以及更新怪物名单。
    private String[] monsterList;
    private Set<Integer> drawnNumbers;
    private Random random;

    public LotteryLogic(String[] monsterList) {
        this.monsterList = monsterList;
        this.drawnNumbers = new HashSet<>();
        this.random = new Random();
    }

    public String drawNumber() {
        int newNumber;
        do {
            newNumber = random.nextInt(monsterList.length);
        } while (drawnNumbers.contains(newNumber));
        drawnNumbers.add(newNumber);
        return monsterList[newNumber];
    }

    public Set<Integer> getDrawnNumbers() {
        return drawnNumbers;
    }

    public boolean isAllNumbersDrawn() {
        return drawnNumbers.size() >= monsterList.length;
    }

    public void updateMonsterList(String[] newMonsterList) {
        this.monsterList = newMonsterList;
        drawnNumbers.clear(); // 清空已抽取的数字
    }
}