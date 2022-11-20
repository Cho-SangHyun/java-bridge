package bridge.domain;

import java.util.List;

/**
 * 다리 건너기 게임을 관리하는 클래스
 * 인스턴스 변수 추가 가능, 패키지 변경 가능
 * 메서드 이름 변경 불가, 인자 및 리턴타입은 추가 또는 변경 가능
 * 메서드 추가 가능
 * InputView, OutputView 사용 불가
 */
public class BridgeGame {
    private final List<String> bridge;
    private final Player player;
    private int tryCount;

    public BridgeGame(List<String> bridge, Player player) {
        this.bridge = bridge;
        this.player = player;
        this.tryCount = 1;
    }

    public boolean isFinished() {
        if (player.isArrived(bridge) || player.isDead()) {
            return true;
        }
        return false;
    }

    public boolean isFailed() {
        if (player.isDead()) {
            return true;
        }
        return false;
    }

    /**
     * 사용자가 칸을 이동할 때 사용하는 메서드
     * <p>
     * 이동을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void move(String spaceToMove) {
        validate(spaceToMove);
        player.move(bridge, spaceToMove);
    }

    private void validate(String spaceToMove) { // 플레이어의 무브에서 U도 D도 아니면 오류는 내는 식으로 고치기
        if (!spaceToMove.equals("U") && !spaceToMove.equals("D")) {
            throw new IllegalArgumentException("[ERROR] 입력된 값이 U나 D가 아닙니다");
        }
    }

    /**
     * 사용자가 게임을 다시 시도할 때 사용하는 메서드
     * <p>
     * 재시작을 위해 필요한 메서드의 반환 타입(return type), 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void retry() {
        player.reVive();
        tryCount += 1;
    }

    public List<String> getBridge() {
        return bridge;
    }

    public int getPlayerLocation() {
        return player.getCurrentSpaceIndex();
    }
}
