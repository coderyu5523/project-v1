package shop.mtcoding.projectjobplan.skill;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.projectjobplan.user.UserRequest;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class SkillRepository {

    private final EntityManager entityManager;

    @Transactional
    public void saveResume(String skill, int resumeId) {
        String q = """
                INSERT INTO skill_tb(resume_id, skill_name) VALUES (?, ?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, resumeId);
        query.setParameter(2, skill);
        query.executeUpdate();

    }
    @Transactional
    public void saveBoard(String skill, int boardId) {
        String q = """
                INSERT INTO skill_tb(board_id, skill_name) VALUES (?, ?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1, boardId);
        query.setParameter(2, skill);
        query.executeUpdate();

    }

    @Transactional
    public void save(String skill, int userId) {
        String q = """
                INSERT INTO skill_tb(user_id, skill_name) VALUES (?, ?)
                """;
        Query query = entityManager.createNativeQuery(q);
        query.setParameter(1,userId);
        query.setParameter(2,skill);
        query.executeUpdate();
    }

    @Transactional
    public void uploadUser(List<String> skills, Integer userId) {
        String q1 = """
            delete from skill_tb where user_id = ? 
        """;
        Query query1 = entityManager.createNativeQuery(q1);
        query1.setParameter(1, userId);
        query1.executeUpdate();

        for(String skill : skills) {
            String q2 =
                    """
                INSERT INTO skill_tb(user_id, skill_name)
                    VALUES (?, ?)
                """;
            Query query2 = entityManager.createNativeQuery(q2);
            query2.setParameter(1, userId);
            query2.setParameter(2, skill);
            query2.executeUpdate();
        }
    }

    @Transactional
    public void uploadEmployer(List<String> skills,Integer boardId) {
        String q1 = """
            delete from skill_tb where board_id = ? 
        """;
        Query query1 = entityManager.createNativeQuery(q1);
        query1.setParameter(1, boardId);
        query1.executeUpdate();

        for(String skill : skills) {
            String q2 =
                    """
                INSERT INTO skill_tb(board_id, skill_name)
                    VALUES (?, ?)
                """;
            Query query2 = entityManager.createNativeQuery(q2);
            query2.setParameter(1, boardId);
            query2.setParameter(2, skill);
            query2.executeUpdate();
        }
    }


    public List<Skill> findByIdWithBoardId(int boardId) {
        String q = """
                select * from skill_tb where board_id = ?
                """;
        Query query = entityManager.createNativeQuery(q,Skill.class);
        query.setParameter(1,boardId);

        try {
           List<Skill> skillList = query.getResultList();
           return skillList;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Skill> findByIdWithUserId(int userId) {
        String q = """
                select * from skill_tb where user_id = ?
                """;
        Query query = entityManager.createNativeQuery(q,Skill.class);
        query.setParameter(1,userId);

        try {
            List<Skill> skillList = query.getResultList();
            return skillList;
        } catch (Exception e) {
            return null;
        }
    }
}
