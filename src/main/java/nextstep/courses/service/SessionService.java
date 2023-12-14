package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.*;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.CoverImageDAO;
import nextstep.courses.infrastructure.SessionDAO;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.Teacher;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SessionService {
    @Resource(name = "sessionDAO")
    private SessionDAO sessionDAO;

    @Resource(name = "sessionDAO")
    private CoverImageDAO coverImageDAO;

    public void save(Session session, List<CoverImage> coverImages) {
        sessionDAO.save(session);
        coverImageDAO.saveAll(coverImages);
    }

    public void enroll(Payment payment) throws CannotEnrollException {
        NsUserSessions nsUserSessions =new NsUserSessions(sessionDAO.findNsUserSessionsBySessionId(payment.getSessionId()));
        Teacher teacher = new Teacher(nsUserSessions);
        Session session = sessionDAO.findById(payment.getSessionId());

        sessionDAO.updateNsUserSession(session.enroll(payment, teacher));
    }
}
