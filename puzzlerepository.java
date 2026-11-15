package coderoom.entities.puzzle;

import coderoom.shared.db.StorageException;

import java.util.List;

/**
 * 담당: E
 * 역할: 도메인 저장소 계약.
 * 직접 선언한 상속·구현 관계 없음.
 */
public interface PuzzleRepository {

    /**
     * 맵에 속한 퍼즐 목록을 조회한다.
     *
     * @param mapId 조회할 맵 ID
     * @return 해당 맵의 퍼즐 목록 (없으면 빈 리스트)
     * @throws StorageException 조회 장애가 발생한 경우
     */
    List<Puzzle> findByMapId(String mapId) throws StorageException;
}